package com.deloitte.services.fssc.budget.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.budget.param.BudgetPublicAdjustQueryForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetPublicAdjustForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetPublicAdjustLineVo;
import com.deloitte.platform.api.fssc.budget.vo.BudgetPublicAdjustVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.budget.entity.BudgetPublicAdjust;
import com.deloitte.services.fssc.budget.entity.BudgetPublicAdjustLine;
import com.deloitte.services.fssc.budget.service.IBudgetPublicAdjustLineService;
import com.deloitte.services.fssc.budget.service.IBudgetPublicAdjustService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
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
 * @Date : Create in 2019-04-30
 * @Description :   对公费用预算调整 控制器实现类
 * @Modified :
 */
@Api(tags = "对公费用预算调整 操作接口")
@Slf4j
@RestController
@RequestMapping("/budget/adjust/public")
public class BudgetPublicAdjustController {

    @Autowired
    IBudgetPublicAdjustService adjustService;

    @Autowired
    IBudgetPublicAdjustLineService adjustLineService;

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
    @ResponseBody
    @Transactional
    public Result deleteById(@ApiParam(value = "单据ID")@RequestBody Map<String,Long> map) {
        Long documentId = map.get("adjustId");
        if (documentId != null) {
            BudgetPublicAdjust adjust = adjustService.getById(documentId);
            String documentStatus = adjust.getDocumentStatus();
            AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus)||
                    FsscEums.RECALLED.getValue().equals(documentStatus)||
                    FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
            adjustService.removeById(documentId);
            QueryWrapper<BudgetPublicAdjustLine> lineDeleteWrapper = new QueryWrapper<>();
            lineDeleteWrapper.eq(BudgetPublicAdjustLine.DOCUMENT_ID, documentId);
            adjustLineService.remove(lineDeleteWrapper);
            return Result.success();
        }
        throw new FSSCException(FsscErrorType.NOT_SAVE_NOT_DELETE);
    }

    @PostMapping(value = "/doSaveOrUpdate")
    @ApiOperation(value = "新增修改保存单据", notes = "新增修改保存单据")
    @ResponseBody
    @Transactional
    public Result<BudgetPublicAdjustVo> saveOrUpdate(@RequestBody @Valid BudgetPublicAdjustForm adjustForm) {
        //todo 待验证参数 加注解
        log.info("adjustForm:{}", adjustForm.toString());
        BudgetPublicAdjust adjust = new BeanUtils<BudgetPublicAdjust>().copyObj(adjustForm, BudgetPublicAdjust.class);
        List<BudgetPublicAdjustLine> lineList = new BeanUtils<BudgetPublicAdjustLine>().copyObjs(adjustForm.getLineFormList(),
                BudgetPublicAdjustLine.class);
        return saveOrUpdateForm(adjust, lineList, adjustForm.getFileIds());
    }

    @PostMapping(value = "/doSubmitProcess")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result<BudgetPublicAdjustVo> doSubmitProcess(@RequestBody @Valid BudgetPublicAdjustForm adjustForm){
        //todo 待验证参数 加注解
        log.info("BudgetPublicAdjustForm:{}", adjustForm.toString());
        BudgetPublicAdjust adjust = new BeanUtils<BudgetPublicAdjust>().copyObj(adjustForm, BudgetPublicAdjust.class);
        List<BudgetPublicAdjustLine> lineList = new BeanUtils<BudgetPublicAdjustLine>().copyObjs(adjustForm.getLineFormList(),
                BudgetPublicAdjustLine.class);
        Result<BudgetPublicAdjustVo> result = saveOrUpdateForm(adjust, lineList, adjustForm.getFileIds());
        BudgetPublicAdjustVo adjustVo = result.getData();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(adjustVo.getDocumentStatus())||
                FsscEums.REJECTED.getValue().equals(adjustVo.getDocumentStatus())||
                FsscEums.RECALLED.getValue().equals(adjustVo.getDocumentStatus()),FsscErrorType.SUBMIT_NEW_REJECTED);
        BudgetPublicAdjustQueryForm queryForm = new BudgetPublicAdjustQueryForm();
        queryForm.setUnitCode(adjustForm.getUnitCode());
        queryForm.setDeptCode(adjustForm.getDeptCode());
        List<BudgetPublicAdjust> adjustList = adjustService.selectList(queryForm);
        if (CollectionUtils.isNotEmpty(adjustList)){
            BudgetPublicAdjust existsAdjust = adjustList.get(0);
            if (!(FsscEums.NEW.getValue().equals(existsAdjust.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(existsAdjust.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(existsAdjust.getDocumentStatus())
                    || FsscEums.APPROVED.getValue().equals(existsAdjust.getDocumentStatus()))){
                //如果存在流程中的调整单据,不允许提交
                throw new FSSCException(FsscErrorType.ADJUSTED_EXISTS_COMMIT_DOCUMENT);
            }
        }
        ReSubmitProcessForm startForm = new ReSubmitProcessForm();
        startForm.setDocumentId(Long.valueOf(adjustVo.getId()));
        startForm.setDocumentNum(adjustVo.getDocumentNum());
        startForm.setDocumentType(FsscTableNameEums.BUDGET_PUBLIC_ADJUST.getValue());
        startForm.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        if(FsscEums.FIRST_SUBMIT.getValue().equals(adjustForm.getReSubmitType())){
            bpmProcessBiz.startProcess(startForm);
        }else {
            startForm.setReSubmitType(adjustForm.getReSubmitType());
            bpmProcessBiz.reSubmit(startForm);
        }
        return result;
    }

    @GetMapping(value = "/page/conditions")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    public Result<IPage<BudgetPublicAdjustVo>> pageCondition(BudgetPublicAdjustQueryForm queryForm) {
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        OrganizationVo organizationVo = commonServices.getCurrentOrg();
        queryForm.setDeptCode(organizationVo.getCode());
        UserVo userVo = commonServices.getCurrentUser();
        queryForm.setApplyForPerson(userVo.getEmpNo());
        IPage<BudgetPublicAdjust> adjustIPage = adjustService.selectPage(queryForm);
        for (BudgetPublicAdjust adjust : adjustIPage.getRecords()) {
            if (FsscEums.NEW.getValue().equals(adjust.getDocumentStatus()) ||
                    FsscEums.REJECTED.getValue().equals(adjust.getDocumentStatus()) ||
                    FsscEums.RECALLED.getValue().equals(adjust.getDocumentStatus())) {
                adjustService.readLastBasicBudget(adjust, null);
            }
        }
        IPage<BudgetPublicAdjustVo> adjustPageVo =
                new BeanUtils<BudgetPublicAdjustVo>().copyPageObjs(adjustIPage, BudgetPublicAdjustVo.class);
        return Result.success(adjustPageVo);
    }

    @GetMapping(value = "/get/initAdjust")
    @ApiOperation(value = "获取初始化调整数据", notes = "获取初始化调整数据")
    @ResponseBody
    public Result<BudgetPublicAdjustVo> getInitAdjust(){
        DeptVo deptVo = commonServices.getCurrentDept();
        OrganizationVo organizationVo = commonServices.getCurrentOrg();
        UserVo userVo = commonServices.getCurrentUser();
        String annual = DateUtil.dateToString(Calendar.getInstance().getTime(),DateUtil.FM_YYYY);
        BudgetPublicAdjust adjust = adjustService.getInitAdjust(deptVo,organizationVo,annual);
        adjust.setApplyForPerson(userVo.getEmpNo());
        adjust.setApplyForPersonName(userVo.getName());
        BudgetPublicAdjustVo adjustVo = new BeanUtils<BudgetPublicAdjustVo>().copyObj(adjust,BudgetPublicAdjustVo.class);
        List<BudgetPublicAdjustLine> lineList = adjustService.selectInitLine(deptVo.getDeptCode(),organizationVo.getCode(),annual);
        List<BudgetPublicAdjustLineVo> lineVoList = new BeanUtils<BudgetPublicAdjustLineVo>().copyObjs(lineList,BudgetPublicAdjustLineVo.class);
        adjustVo.setLineVoList(lineVoList);
        return Result.success(adjustVo);
    }

    @GetMapping(value = "/loadDataByDocumentId")
    @ApiOperation(value = "查询详情,根据单据ID", notes = "查询详情,根据单据ID")
    @ResponseBody
    public Result<BudgetPublicAdjustVo> loadDataByDocumentId(@ApiParam(value = "单据ID") @RequestParam(value = "documentId") Long documentId) {
        BudgetPublicAdjustVo adjustVo = adjustService.findInfoById(documentId,false);
        if(FsscEums.NEW.getValue().equals(adjustVo.getDocumentStatus())||
                FsscEums.REJECTED.getValue().equals(adjustVo.getDocumentStatus())||
                FsscEums.RECALLED.getValue().equals(adjustVo.getDocumentStatus())){
            adjustVo = adjustService.findInfoById(documentId,true);
        }
        List<BpmProcessTaskVo> processTaskVoList = bpmTaskBiz.findHistoryNoException(documentId,FsscTableNameEums.BUDGET_PUBLIC_ADJUST.getValue());
        adjustVo.setBpmProcessTaskVos(processTaskVoList);
        return Result.success(adjustVo);
    }

    @ApiOperation(value = "导出对公费用调整", notes = "导出对公费用调整")
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(BudgetPublicAdjustQueryForm queryForm)
            throws IOException {
        log.info("search with BudgetPublicAdjustQueryForm:{}", JSON.toJSONString(queryForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        DeptVo deptVo = commonServices.getCurrentDept();
        queryForm.setUnitCode(deptVo.getDeptCode());
        OrganizationVo organizationVo = commonServices.getCurrentOrg();
        queryForm.setDeptCode(organizationVo.getCode());
        UserVo userVo = commonServices.getCurrentUser();
        queryForm.setApplyForPerson(userVo.getEmpNo());
        List<BudgetPublicAdjust> records = adjustService.selectPage(queryForm).getRecords();
        String[] title = {"序号", "单据编号", "申请人", "归属单位", "归属部门", "年度预算总额", "当期预算金额"
                , "当期可用预算金额", "申请日期", "单据状态"};
        String fileName = "对公费用调整";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH + 1000));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            BudgetPublicAdjust adjust = records.get(i);
            if (FsscEums.NEW.getValue().equals(adjust.getDocumentStatus()) ||
                    FsscEums.REJECTED.getValue().equals(adjust.getDocumentStatus()) ||
                    FsscEums.RECALLED.getValue().equals(adjust.getDocumentStatus())) {
                adjustService.readLastBasicBudget(adjust, null);
            }
            content[i][0] = i + 1 + "";
            content[i][1] = adjust.getDocumentNum();
            content[i][2] = adjust.getApplyForPersonName();
            content[i][3] = adjust.getUnitName();
            content[i][4] = adjust.getDeptName();
            content[i][5] = StringUtil.objectToString(adjust.getAnnualBudgetAmount());
            content[i][6] = StringUtil.objectToString(adjust.getPeriodBudgetAmount());
            content[i][7] = StringUtil.objectToString(adjust.getPeriodUsableAmount());
            if (adjust.getCreateTime() != null) {
                content[i][8] = LocalDateTimeUtils.formatTime(adjust.getCreateTime(), DateUtil.FM_YYYY_MM_DD);
            }
            content[i][9] = docStatusMap.get(adjust.getDocumentStatus());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    /**
     * 验证单据
     * @param adjust
     * @param lineList
     */
    private void validateForm(BudgetPublicAdjust adjust, List<BudgetPublicAdjustLine> lineList) {
        //金额验证
        if(CollectionUtils.isEmpty(lineList)){
            throw new FSSCException(FsscErrorType.ADJUSTED_LINE_NOT_FIND);
        }
        for(BudgetPublicAdjustLine line : lineList){
            if (line.getAdjustAmount() == null){
                line.setAdjustAmount(BigDecimal.ZERO);
            }
            AssertUtils.asserts(line.getAdjustedPeriodUsableAmount().compareTo(BigDecimal.ZERO) >= 0,
                    FsscErrorType.ADJUSTED_USABLE_AMOUNT_LESS_THAN_ZERO);
        }
    }

    /**
     * 保存
     * @param adjust
     * @param lineList
     * @param fileIds
     * @return
     */
    private Result<BudgetPublicAdjustVo> saveOrUpdateForm(BudgetPublicAdjust adjust, List<BudgetPublicAdjustLine> lineList,List<Long> fileIds) {
        Long documentId = adjust.getId();
        if (documentId == null){
            DeptVo deptVo = commonServices.getCurrentDept();
            OrganizationVo organizationVo = commonServices.getCurrentOrg();
            adjust.setUnitCode(deptVo.getDeptCode());
            adjust.setUnitName(deptVo.getDeptName());
            adjust.setUnitId(Long.valueOf(deptVo.getDeptId()));
            adjust.setDeptCode(organizationVo.getCode());
            adjust.setDeptName(organizationVo.getName());
            adjust.setDeptId(StringUtil.castTolong(organizationVo.getId()));
            UserVo userVo = commonServices.getCurrentUser();
            adjust.setApplyForPerson(userVo.getEmpNo());
            adjust.setApplyForPersonName(userVo.getName());
        }else {
            BudgetPublicAdjust oldAdjust = adjustService.getById(documentId);
            if (oldAdjust == null){
                throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
            }
            oldAdjust.setRemark(adjust.getRemark());
            adjust = oldAdjust;
        }
        //验证单据
        validateForm(adjust,lineList);
        adjustService.readLastBasicBudget(adjust,lineList);
        for(BudgetPublicAdjustLine line : lineList){
            AssertUtils.asserts(line.getAdjustedPeriodUsableAmount().compareTo(BigDecimal.ZERO) > 0,
                    FsscErrorType.ADJUSTED_USABLE_AMOUNT_LESS_THAN_ZERO);
        }
        boolean adjustSaveResult = adjustService.saveOrUpdate(adjust);
        AssertUtils.asserts(adjustSaveResult, FsscErrorType.SAVE_FAIL);
        //行信息保存 先删除原有的，再新增
        QueryWrapper<BudgetPublicAdjustLine> lineQueryWrapper = new QueryWrapper<>();
        lineQueryWrapper.eq(BudgetPublicAdjustLine.DOCUMENT_ID, adjust.getId());
        List<Long> lineIds = lineList.stream().map(line -> line.getId()).collect(Collectors.toList());
        lineIds.removeAll(Collections.singleton(null));
        for(BudgetPublicAdjustLine line : lineList){
            line.setDocumentId(adjust.getId());
        }
        //删除前端没有传递的行数据
        if (CollectionUtils.isNotEmpty(lineIds)) {
            lineQueryWrapper.notIn("id", lineIds);
        }
        adjustLineService.remove(lineQueryWrapper);
        if(CollectionUtils.isNotEmpty(lineList)){
            adjustLineService.saveOrUpdateBatch(lineList);
        }
        //文件列表保存
        if(fileIds != null){
            fileIds.removeAll(Collections.singleton(null));
        }
        if(CollectionUtils.isNotEmpty(fileIds)){
            QueryWrapper<File> fileQueryWrapper= new QueryWrapper<>();
            fileQueryWrapper.eq(File.DOCUMENT_ID,adjust.getId())
                    .eq(File.DOCUMENT_TYPE,FsscTableNameEums.BUDGET_PUBLIC_ADJUST.getValue())
                    .notIn(File.ID,fileIds);
            fileService.remove(fileQueryWrapper);
            Collection<File> files = fileService.listByIds(fileIds);
            AssertUtils.asserts(CollectionUtils.isNotEmpty(files),FsscErrorType.FILE_IS_NULL);
            for (File file : files) {
                file.setDocumentId(adjust.getId());
            }
            fileService.saveOrUpdateBatch(files);
        }
        return Result.success(adjustService.findInfoById(adjust.getId(),false));
    }
}



