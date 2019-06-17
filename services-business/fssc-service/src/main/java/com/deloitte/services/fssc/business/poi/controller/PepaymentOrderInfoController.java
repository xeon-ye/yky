package com.deloitte.services.fssc.business.poi.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayVo;
import com.deloitte.platform.api.fssc.poi.param.PepaymentOrderInfoQueryForm;
import com.deloitte.platform.api.fssc.poi.vo.PepaymentOrderInfoForm;
import com.deloitte.platform.api.fssc.poi.vo.PepaymentOrderInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.service.IGeExpenseBorrowPrepayService;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import com.deloitte.services.fssc.business.pay.service.IPyPamentOrderInfoService;
import com.deloitte.services.fssc.business.poi.entity.PepaymentOrderInfo;
import com.deloitte.services.fssc.business.poi.service.IPepaymentOrderInfoService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.dockingEbs.service.IAccountVoucherToEbsService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Author : hjy
 * @Date : Create in 2019-05-13
 * @Description :   PepaymentOrderInfo控制器实现类
 * @Modified :
 */
@Api(tags = "还款单操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/poi/pepayment-order-info")
public class PepaymentOrderInfoController {

    @Autowired
    public IPepaymentOrderInfoService pepaymentOrderInfoService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;

    @Autowired
    private BpmTaskBiz bpmTaskBiz;

    @Autowired
    public IPyPamentOrderInfoService pyPamentOrderInfoService;

    @Autowired
    private IGeExpenseBorrowPrepayService prepayService;
    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;


    @GetMapping(value = "/queryById")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result<PepaymentOrderInfoVo> queryById(Long id) {
        log.info("id" + id);
        PepaymentOrderInfo pepaymentOrderInfo = pepaymentOrderInfoService.getById(id);
        AssertUtils.asserts(pepaymentOrderInfo != null, FsscErrorType.DOCUMENT_NOT_FIND);
        PepaymentOrderInfoVo pepaymentOrderInfoVo = new BeanUtils<PepaymentOrderInfoVo>().copyObj(pepaymentOrderInfo, PepaymentOrderInfoVo.class);
        //核销明细
        QueryWrapper<GeExpenseBorrowPrepay> borrowPrepayQueryWrapper = new QueryWrapper<>();
        borrowPrepayQueryWrapper.eq("DOCUMENT_ID", id)
                .eq("DOCUMENT_TYPE", FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
        List<GeExpenseBorrowPrepay> geExpenseBorrowPrepays = prepayService.list(borrowPrepayQueryWrapper);
        // prepayService.paddingHexiaomingxi(geExpenseBorrowPrepays);
        pepaymentOrderInfoVo.setExpenseBorrowPrepayVos(new BeanUtils<GeExpenseBorrowPrepayVo>().copyObjs(geExpenseBorrowPrepays, GeExpenseBorrowPrepayVo.class));
        //审批历史
        try {
            List<BpmProcessTaskVo> history = bpmTaskBiz.findHistory(id, FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
            pepaymentOrderInfoVo.setBpmProcessTaskVos(new BeanUtils<BpmProcessTaskVo>().copyObjs(history, BpmProcessTaskVo.class));
        } catch (FSSCException e) {
            e.getMessage();
        }
        return new Result<PepaymentOrderInfoVo>().success(pepaymentOrderInfoVo);
    }

    @PostMapping(value = "/doSaveOrUpdate")
    @ApiOperation(value = "新增或修改", notes = "新增或修改")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    @Transactional
    public Result doSaveOrUpdate(@RequestBody @Valid PepaymentOrderInfoForm pepaymentOrderInfoForm) {
        log.info("pepaymentOrderInfoForm:" + pepaymentOrderInfoForm.toString());
        return saveOrUpdate(pepaymentOrderInfoForm);
    }

    public Result saveOrUpdate(PepaymentOrderInfoForm pepaymentOrderInfoForm) {
        PepaymentOrderInfo pepaymentOrderInfo = new BeanUtils<PepaymentOrderInfo>().copyObj(pepaymentOrderInfoForm, PepaymentOrderInfo.class);
        boolean saveOrUpdateSuccess = pepaymentOrderInfoService.saveOrUpdate(pepaymentOrderInfo);
        if (saveOrUpdateSuccess) {
            //关联借款或预付款表
            QueryWrapper<GeExpenseBorrowPrepay> borrowPrepayQueryWrapper = new QueryWrapper<>();
            borrowPrepayQueryWrapper.eq("DOCUMENT_ID", pepaymentOrderInfo.getId()).eq("DOCUMENT_TYPE", FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
            List<GeExpenseBorrowPrepayForm> geExpenseBorrowPrepayForms = pepaymentOrderInfoForm.getBorrowPrepayFormList();
            if (CollectionUtils.isNotEmpty(geExpenseBorrowPrepayForms)) {
                List<Long> listIds = geExpenseBorrowPrepayForms.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                listIds.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(listIds)) {
                    borrowPrepayQueryWrapper.notIn("id", listIds);
                }
                prepayService.remove(borrowPrepayQueryWrapper);
                List<GeExpenseBorrowPrepay> geExpenseBorrowPrepayList = new BeanUtils<GeExpenseBorrowPrepay>().copyObjs(geExpenseBorrowPrepayForms, GeExpenseBorrowPrepay.class);
                for (GeExpenseBorrowPrepay geExpenseBorrowPrepay : geExpenseBorrowPrepayList) {
                    geExpenseBorrowPrepay.setDocumentId(pepaymentOrderInfo.getId());
                    geExpenseBorrowPrepay.setDocumentType(FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
                    geExpenseBorrowPrepay.setConnectDocumentType(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
                    geExpenseBorrowPrepay.setDocumentNum(pepaymentOrderInfo.getDocumentNum());
                    geExpenseBorrowPrepay.setVerficationDate(LocalDateTime.now());
                    Long borrowOrPrepayId = geExpenseBorrowPrepay.getBorrowOrPrepayId();
                    String budgetAccountCode = prepayService.getPaymentOrderBudgetAccountCode(borrowOrPrepayId);
                    geExpenseBorrowPrepay.setPaymentBudgetAccountCode(budgetAccountCode);
                }
                if (CollectionUtils.isNotEmpty(geExpenseBorrowPrepayList)) {
                    prepayService.saveOrUpdateBatch(geExpenseBorrowPrepayList);
                }

            } else {
                prepayService.remove(borrowPrepayQueryWrapper);
            }
            return Result.success(new BeanUtils<PepaymentOrderInfoVo>().copyObj(pepaymentOrderInfoService.getById(pepaymentOrderInfo.getId()), PepaymentOrderInfoVo.class));
        }
        throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
    }

    @ApiOperation(value = "根据id删除", notes = "根据url的id来指定删除对象")
    @Transactional
    @DeleteMapping(value = "/deleteId")
    public Result deleteId(@RequestBody Long id) {
        log.info("ids" + id);
        PepaymentOrderInfo pepaymentOrderInfo = pepaymentOrderInfoService.getById(id);
        AssertUtils.asserts(pepaymentOrderInfo != null, FsscErrorType.NOT_FIND_DATA);
        String documentStatus = pepaymentOrderInfo.getDocumentStatus();
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus) ||
                FsscEums.RECALLED.getValue().equals(documentStatus) ||
                FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
        pepaymentOrderInfoService.removeById(id);
        //删除关联付款借款
        QueryWrapper<GeExpenseBorrowPrepay> prepayQueryWrapper = new QueryWrapper<>();
        prepayQueryWrapper.eq("DOCUMENT_TYPE", FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue()).eq("DOCUMENT_ID", id);
        prepayService.remove(prepayQueryWrapper);
        //停止流程
        bpmProcessBiz.stopProcess(id);
        return Result.success();
    }

    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    @PostMapping(value = "/sumbitAdvance")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ResponseBody
    @Transactional
    public Result<PepaymentOrderInfoVo> sumbitAdvance(@RequestBody @Valid PepaymentOrderInfoForm pepaymentOrderInfoForm) {
        Result<PepaymentOrderInfoVo> queryDatas = saveOrUpdate(pepaymentOrderInfoForm);
        PepaymentOrderInfoVo pepaymentOrderInfoVo = queryDatas.getData();
        Long id = pepaymentOrderInfoVo.getId();
        if (id == null) {
            throw new FSSCException(FsscErrorType.TRAVLE_APPLY_NO_EMPTY);
        }
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(pepaymentOrderInfoVo.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(pepaymentOrderInfoVo.getDocumentStatus()) ||
                FsscEums.RECALLED.getValue().equals(pepaymentOrderInfoVo.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);
        prepayService.modifyAfterSubmit(true, pepaymentOrderInfoVo.getId(), FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
        changeCaiOrYu(id);
        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest("N");
        valueForm.setProjectId(pepaymentOrderInfoVo.getProjectId());
        valueForm.setTotalAmount(pepaymentOrderInfoVo.getReAmountTatol().toString());
        valueForm.setDocumentId(pepaymentOrderInfoVo.getId());
        valueForm.setDocumentType(FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
        valueForm.setCreateBy(pepaymentOrderInfoVo.getCreateBy());
        valueForm.setDeptCode(pepaymentOrderInfoVo.getDeptCode());
        valueForm.setProjectCode(pepaymentOrderInfoVo.getProjectCode());
        valueForm.setUnitCode(pepaymentOrderInfoVo.getUnitCode());
        Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);

        ReSubmitProcessForm form = new ReSubmitProcessForm();
        form.setProcessVariables(andAddProcessValue);
        form.setDocumentId(id);
        form.setDocumentType(FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
        form.setDocumentNum(pepaymentOrderInfoVo.getDocumentNum());
        form.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        form.setBudgetWarningCheck(pepaymentOrderInfoForm.getBudgetWarningCheck());
        accountVoucherToEbsService.generatePrefabricatedCredentials(FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue(), id);
        if (FsscEums.FIRST_SUBMIT.getValue().equals(pepaymentOrderInfoForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(form);
        } else {
            form.setReSubmitType(pepaymentOrderInfoForm.getReSubmitType());
            bpmProcessBiz.reSubmit(form);
        }
        return Result.success();
    }

    public void changeCaiOrYu(Long id){
        QueryWrapper<GeExpenseBorrowPrepay> pyWrapper = new QueryWrapper<>();
        pyWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
        List<GeExpenseBorrowPrepay> line = prepayService.list(pyWrapper);
        //List<GeExpenseBorrowPrepayVo> lineVos =
               // new BeanUtils<GeExpenseBorrowPrepayVo>().copyObjs(line, GeExpenseBorrowPrepayVo.class);
         if(CollectionUtils.isNotEmpty(line)){
             for(GeExpenseBorrowPrepay geExpenseBorrowPrepay:line){
               String payDocumentNum=geExpenseBorrowPrepay.getConnectDocumentNum();
               QueryWrapper<PyPamentOrderInfo>  peWrapper=new QueryWrapper<>();//pyPamentOrderInfoService
                 peWrapper.eq("PAY_DOCUMENT_NUM",payDocumentNum);
                 PyPamentOrderInfo pyPamentOrderInfo=pyPamentOrderInfoService.getOne(peWrapper);
                 geExpenseBorrowPrepay.setPaymentAccountCode(pyPamentOrderInfo.getAccountCode());
                 prepayService.updateById(geExpenseBorrowPrepay);
             }

         }
    }


    /*@ApiOperation(value = "列表查询PepaymentOrderInfo", notes = "根据条件查询PepaymentOrderInfo列表数据")
    public Result<List<PepaymentOrderInfoVo>> list(@Valid @RequestBody @ApiParam(name="pepaymentOrderInfoQueryForm",value="PepaymentOrderInfo查询参数",required=true) PepaymentOrderInfoQueryForm pepaymentOrderInfoQueryForm) {
        log.info("search with pepaymentOrderInfoQueryForm:", pepaymentOrderInfoQueryForm.toString());
        List<PepaymentOrderInfo> pepaymentOrderInfoList=pepaymentOrderInfoService.selectList(pepaymentOrderInfoQueryForm);
        List<PepaymentOrderInfoVo> pepaymentOrderInfoVoList=new BeanUtils<PepaymentOrderInfoVo>().copyObjs(pepaymentOrderInfoList,PepaymentOrderInfoVo.class);
        return new Result<List<PepaymentOrderInfoVo>>().sucess(pepaymentOrderInfoVoList);
    }*/

    @GetMapping(value = "/page/conditions")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    public Result<IPage<PepaymentOrderInfoVo>> search(@Valid PepaymentOrderInfoQueryForm pepaymentOrderInfoQueryForm) {
        log.info("search with pepaymentOrderInfoQueryForm:", pepaymentOrderInfoQueryForm.toString());
        IPage<PepaymentOrderInfo> pepaymentOrderInfoPage = pepaymentOrderInfoService.selectPage(pepaymentOrderInfoQueryForm);
        IPage<PepaymentOrderInfoVo> pepaymentOrderInfoVoPage = new BeanUtils<PepaymentOrderInfoVo>().copyPageObjs(pepaymentOrderInfoPage, PepaymentOrderInfoVo.class);
        return new Result<IPage<PepaymentOrderInfoVo>>().sucess(pepaymentOrderInfoVoPage);
    }

    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, PepaymentOrderInfoQueryForm pepaymentOrderInfoQueryForm)
            throws IOException {
        log.info("search with pepaymentOrderInfoQueryForm:{}", JSON.toJSONString(pepaymentOrderInfoQueryForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAY_STATUS.getValue()));
        List<PepaymentOrderInfo> records = pepaymentOrderInfoService.selectPage(pepaymentOrderInfoQueryForm).getRecords();
        String[] title = {"序号", "单据编号", "还款人", "归属单位", "归属部门",
                "还款金额", "单据状态", "申请日期"};
        String fileName = "差旅申请单列表.xls";
        String sheetName = "差旅申请单列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            PepaymentOrderInfo vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = vo.getCreateUserName();
            content[i][3] = vo.getUnitName();
            content[i][4] = vo.getDeptName();
            content[i][5] = StringUtil.objectToString(vo.getReAmountTatol());
            content[i][6] = docStatusMap.get(vo.getDocumentStatus());
            if (vo.getCreateTime() != null) {
                content[i][7] = LocalDateTimeUtils.formatTime(vo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            }
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }


}



