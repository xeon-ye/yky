package com.deloitte.services.fssc.budget.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.budget.param.BudgetDetailingAdjustHeadQueryForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetDetailingAdjustHeadForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetDetailingAdjustHeadVo;
import com.deloitte.platform.api.fssc.edu.param.FundsApplyLineQueryForm;
import com.deloitte.platform.api.fssc.edu.vo.FundsApplyLineVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.budget.entity.BudgetDetailingAdjustHead;
import com.deloitte.services.fssc.budget.entity.BudgetDetailingAdjustLine;
import com.deloitte.services.fssc.budget.service.IBudgetDetailingAdjustHeadService;
import com.deloitte.services.fssc.budget.service.IBudgetDetailingAdjustLineService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.edu.entity.FundsApplyHead;
import com.deloitte.services.fssc.business.edu.entity.FundsApplyLine;
import com.deloitte.services.fssc.business.edu.service.IFundsApplyHeadService;
import com.deloitte.services.fssc.business.edu.service.IFundsApplyLineService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.DateUtil;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author : jaws
 * @Date : Create in 2019-05-06
 * @Description :   BudgetDetailingAdjustHead控制器实现类
 * @Modified :
 */
@Api(tags = "预算细化调整 操作接口")
@Slf4j
@RestController
@RequestMapping("/budget/adjust/detailing")
public class BudgetDetailingAdjustController {

    @Autowired
    private IBudgetDetailingAdjustHeadService adjustHeadService;

    @Autowired
    private IBudgetDetailingAdjustLineService adjustLineService;

    @Autowired
    private IFundsApplyHeadService applyHeadService;

    @Autowired
    private IFundsApplyLineService applyLineService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;

    @Autowired
    private IFileService fileService;

    @Autowired
    private BpmTaskBiz bpmTaskBiz;


    @DeleteMapping(value = "/deleteById")
    @ApiOperation(value = "删除", notes = "删除")
    @Transactional
    public Result deleteById(@ApiParam(value = "单据ID") @RequestBody Map<String, Long> map) {
        Long documentId = map.get("adjustId");
        if (documentId != null) {
            BudgetDetailingAdjustHead adjust = adjustHeadService.getById(documentId);
            String documentStatus = adjust.getDocumentStatus();
            AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus) ||
                    FsscEums.RECALLED.getValue().equals(documentStatus) ||
                    FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
            adjustHeadService.removeById(documentId);
            QueryWrapper<BudgetDetailingAdjustLine> lineDeleteWrapper = new QueryWrapper<>();
            lineDeleteWrapper.eq(BudgetDetailingAdjustLine.DOCUMENT_ID, documentId);
            adjustLineService.remove(lineDeleteWrapper);
            return Result.success();
        }
        throw new FSSCException(FsscErrorType.NOT_SAVE_NOT_DELETE);
    }

    @PostMapping(value = "/doSaveOrUpdate")
    @ApiOperation(value = "新增修改保存单据", notes = "新增修改保存单据")
    @Transactional
    public Result<BudgetDetailingAdjustHeadVo> saveOrUpdate(@RequestBody @Valid BudgetDetailingAdjustHeadForm adjustForm) {
        //todo 待验证参数 加注解
        log.info("adjustForm:{}", adjustForm.toString());
        BudgetDetailingAdjustHead adjustHead = new BeanUtils<BudgetDetailingAdjustHead>().copyObj(adjustForm, BudgetDetailingAdjustHead.class);
        List<BudgetDetailingAdjustLine> lineList = new BeanUtils<BudgetDetailingAdjustLine>().copyObjs(adjustForm.getLineFormList(),
                BudgetDetailingAdjustLine.class);
        return saveOrUpdateForm(adjustHead, lineList, adjustForm.getFileIds());
    }

    @PostMapping(value = "/doSubmitProcess")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @Transactional
    public Result<BudgetDetailingAdjustHeadVo> doSubmitProcess(@RequestBody @Valid BudgetDetailingAdjustHeadForm adjustForm) {
        //todo 待验证参数 加注解
        log.info("BudgetPublicAdjustForm:{}", adjustForm.toString());
        BudgetDetailingAdjustHead adjust = new BeanUtils<BudgetDetailingAdjustHead>().copyObj(adjustForm, BudgetDetailingAdjustHead.class);
        List<BudgetDetailingAdjustLine> lineList = new BeanUtils<BudgetDetailingAdjustLine>().copyObjs(adjustForm.getLineFormList(),
                BudgetDetailingAdjustLine.class);
        Result<BudgetDetailingAdjustHeadVo> result = saveOrUpdateForm(adjust, lineList, adjustForm.getFileIds());
        BudgetDetailingAdjustHeadVo adjustVo = result.getData();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(adjustVo.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(adjustVo.getDocumentStatus()) ||
                FsscEums.RECALLED.getValue().equals(adjustVo.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);
        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setDocumentId(Long.valueOf(adjustVo.getId()));
        startForm.setDocumentNum(adjustVo.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.BUDGET_DETAILING_ADJUST_HEAD.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        if (FsscEums.FIRST_SUBMIT.getValue().equals(adjustForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(startForm);
        } else {
            startForm.setReSubmitType(adjustForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }
        return result;
    }

    @GetMapping(value = "/page/conditions")
    @ApiParam(name = "queryForm", value = "细化调整查询参数", required = true)
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public Result<IPage<BudgetDetailingAdjustHeadVo>> pageCondition(BudgetDetailingAdjustHeadQueryForm queryForm) {
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        OrganizationVo organizationVo = commonServices.getCurrentOrg();
        queryForm.setDeptCode(organizationVo.getCode());
        UserVo userVo = commonServices.getCurrentUser();
        queryForm.setApplyForPerson(userVo.getEmpNo());
        IPage<BudgetDetailingAdjustHead> adjustIPage = adjustHeadService.selectPage(queryForm);
        IPage<BudgetDetailingAdjustHeadVo> adjustPageVo =
                new BeanUtils<BudgetDetailingAdjustHeadVo>().copyPageObjs(adjustIPage, BudgetDetailingAdjustHeadVo.class);
        return Result.success(adjustPageVo);
    }

    @GetMapping(value = "/page/applyLine")
    @ApiParam(name = "queryForm", value = "教育经费行信息查询参数", required = true)
    @ApiOperation(value = "分页查询教育经费行信息")
    public Result<IPage<FundsApplyLineVo>> pageForApplyLine(FundsApplyLineQueryForm queryForm) {
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitId(Long.valueOf(deptVo.getDeptId()));
        OrganizationVo organizationVo = commonServices.getCurrentOrg();
        queryForm.setSchoolId(StringUtil.castTolong(organizationVo.getId()));
        UserVo userVo = commonServices.getCurrentUser();
        queryForm.setRecieveUserId(Long.valueOf(userVo.getId()));
        IPage<FundsApplyLineVo> applyLineIPage = applyLineService.selectVoPage(queryForm);
        IPage<FundsApplyLineVo> adjustPageVo =
                new BeanUtils<FundsApplyLineVo>().copyPageObjs(applyLineIPage, FundsApplyLineVo.class);
        return Result.success(adjustPageVo);
    }

    @GetMapping(value = "/loadDataByDocumentId")
    @ApiOperation(value = "查询详情,根据单据ID", notes = "查询详情,根据单据ID")
    public Result<BudgetDetailingAdjustHeadVo> loadDataByDocumentId(@ApiParam(value = "单据ID") @RequestParam(value = "documentId") Long documentId) {
        BudgetDetailingAdjustHeadVo adjustVo = adjustHeadService.findInfoById(documentId);
        if (FsscEums.NEW.getValue().equals(adjustVo.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(adjustVo.getDocumentStatus()) ||
                FsscEums.RECALLED.getValue().equals(adjustVo.getDocumentStatus())) {
            adjustVo = adjustHeadService.findInfoById(documentId);
        }
        List<BpmProcessTaskVo> processTaskVoList = bpmTaskBiz.findHistoryNoException(documentId, FsscTableNameEums.BUDGET_DETAILING_ADJUST_HEAD.getValue());
        adjustVo.setBpmProcessTaskVos(processTaskVoList);
        return Result.success(adjustVo);
    }

    @ApiOperation(value = "导出对预算细化调整", notes = "导出对预算细化调整")
    @GetMapping(value = "/exportExcel")
    public void exportExcel(BudgetDetailingAdjustHeadQueryForm queryForm)
            throws IOException {
        log.info("search with BudgetPublicAdjustQueryForm:{}", JSON.toJSONString(queryForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        List<BudgetDetailingAdjustHead> records = adjustHeadService.selectPage(queryForm).getRecords();
        String[] title = {"序号", "单据编号", "申请人", "归属单位", "归属部门", "申请金费", "分配总额", "申请日期", "单据状态"};
        String fileName = "预算细化调整申请";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            BudgetDetailingAdjustHead vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getApplyForPersonName();
            content[i][3] = vo.getUnitName();
            content[i][4] = vo.getDeptName();
            content[i][5] = StringUtil.objectToString(vo.getFundType());
            content[i][6] = StringUtil.objectToString(vo.getApplyTotal());
            if (vo.getCreateTime() != null) {
                content[i][7] = LocalDateTimeUtils.formatTime(vo.getCreateTime(), DateUtil.FM_YYYY_MM_DD);
            }
            content[i][8] = docStatusMap.get(vo.getDocumentStatus());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    /**
     * 保存
     *
     * @param adjustHead
     * @param lineList
     * @param fileIds
     * @return
     */
    private Result<BudgetDetailingAdjustHeadVo> saveOrUpdateForm(BudgetDetailingAdjustHead adjustHead,
                                                                 List<BudgetDetailingAdjustLine> lineList, List<Long> fileIds) {
        Long documentId = adjustHead.getId();
        if (documentId == null) {
            DeptVo deptVo = commonServices.getCurrentDept();
            OrganizationVo organizationVo = commonServices.getCurrentOrg();
            adjustHead.setUnitCode(deptVo.getDeptCode());
            adjustHead.setUnitName(deptVo.getDeptName());
            adjustHead.setUnitId(Long.valueOf(deptVo.getDeptId()));
            adjustHead.setDeptCode(organizationVo.getCode());
            adjustHead.setDeptName(organizationVo.getName());
            adjustHead.setDeptId(StringUtil.castTolong(organizationVo.getId()));
            UserVo userVo = commonServices.getCurrentUser();
            adjustHead.setApplyForPerson(userVo.getEmpNo());
            adjustHead.setApplyForPersonName(userVo.getName());
        } else {
            BudgetDetailingAdjustHead oldAdjustHead = adjustHeadService.getById(documentId);
            if (oldAdjustHead == null) {
                throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
            }
            oldAdjustHead.setRemark(adjustHead.getRemark());
            adjustHead = oldAdjustHead;
        }
        //验证单据
        validateForm(adjustHead, lineList);
        boolean adjustSaveResult = adjustHeadService.saveOrUpdate(adjustHead);
        AssertUtils.asserts(adjustSaveResult, FsscErrorType.SAVE_FAIL);
        //行信息保存 先删除原有的，再新增
        QueryWrapper<BudgetDetailingAdjustLine> lineQueryWrapper = new QueryWrapper<>();
        lineQueryWrapper.eq(BudgetDetailingAdjustLine.DOCUMENT_ID, adjustHead.getId());
        List<Long> lineIds = lineList.stream().map(line -> line.getId()).collect(Collectors.toList());
        lineIds.removeAll(Collections.singleton(null));
        for (BudgetDetailingAdjustLine line : lineList) {
            line.setDocumentId(adjustHead.getId());
        }
        //删除前端没有传递的行数据
        if (CollectionUtils.isNotEmpty(lineIds)) {
            lineQueryWrapper.notIn("id", lineIds);
        }
        adjustLineService.remove(lineQueryWrapper);
        if (CollectionUtils.isNotEmpty(lineList)) {
            adjustLineService.saveOrUpdateBatch(lineList);
        }
        //文件列表保存
        if (fileIds != null) {
            fileIds.removeAll(Collections.singleton(null));
        }
        if (CollectionUtils.isNotEmpty(fileIds)) {
            QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
            fileQueryWrapper.eq(File.DOCUMENT_ID, adjustHead.getId())
                    .eq(File.DOCUMENT_TYPE, FsscTableNameEums.BUDGET_DETAILING_ADJUST_HEAD.getValue())
                    .notIn(File.ID, fileIds);
            fileService.remove(fileQueryWrapper);
            Collection<File> files = fileService.listByIds(fileIds);
            AssertUtils.asserts(CollectionUtils.isNotEmpty(files), FsscErrorType.FILE_IS_NULL);
            files.stream().forEach(file -> file.setDocumentId(documentId));
            fileService.saveOrUpdateBatch(files);
        }
        return Result.success(adjustHeadService.findInfoById(adjustHead.getId()));
    }

    /**
     * 验证单据
     *
     * @param adjust
     * @param lineList
     */
    private void validateForm(BudgetDetailingAdjustHead adjust, List<BudgetDetailingAdjustLine> lineList) {
        //金额验证
        if (CollectionUtils.isEmpty(lineList)) {
            throw new FSSCException(FsscErrorType.ADJUSTED_LINE_NOT_FIND);
        }
        FundsApplyHead applyHead = applyHeadService.getById(adjust.getRelatedDocumentId());
        AssertUtils.asserts(FsscEums.APPROVED.getValue().equals(applyHead.getDocumentStatus()),FsscErrorType.ADJUSTED_EDU_APPLY_HEAD_NOT_APPROVED);
        FundsApplyLine applyLine = applyLineService.getByKeyWord(adjust.getRelatedDocumentId(),adjust.getLineNum());
        AssertUtils.asserts(applyLine != null,FsscErrorType.ADJUSTED_EDU_APPLY_LINE_NOT_FIND);
        AssertUtils.asserts(applyLine.getApplyAmount().compareTo(adjust.getApplyTotal()) == 0,FsscErrorType.ADJUSTED_EDU_APPLY_LINE_NOT_MATCH);
        AssertUtils.asserts(adjust.getApplyTotal() != null && adjust.getApplyTotal().compareTo(BigDecimal.ZERO) >= 0,
                FsscErrorType.ADJUSTED_ALLOCATION_TOTAL_LESS_THAN_ZERO);

        for (BudgetDetailingAdjustLine line : lineList) {
            line.setDocumentId(adjust.getId());
            AssertUtils.asserts(line.getAllocationAmount().compareTo(BigDecimal.ZERO) >= 0,
                    FsscErrorType.ADJUSTED_ALLOCATION_TOTAL_LESS_THAN_ZERO);
        }
        BigDecimal allocationAmountSum = lineList.stream().map(BudgetDetailingAdjustLine::getAllocationAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        AssertUtils.asserts(adjust.getApplyTotal().compareTo(allocationAmountSum) == 0,
                FsscErrorType.ADJUSTED_ALLOCATION_TOTAL_NOT_MATCH_APPLY);
    }

}



