package com.deloitte.services.fssc.business.pay.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.pay.param.PyAllDocumentQueryForm;
import com.deloitte.platform.api.fssc.pay.param.PyPamentOrderInfoQueryForm;
import com.deloitte.platform.api.fssc.pay.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentInfoService;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.service.IBmBorrowBankService;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyInfoService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.business.contract.service.ICrbContractReimburseBillService;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.service.IGeExpensePaymentListService;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseService;
import com.deloitte.services.fssc.business.labor.entity.GePrivatePaymentList;
import com.deloitte.services.fssc.business.labor.service.IGePrivatePaymentListService;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostService;
import com.deloitte.services.fssc.business.pay.entity.PyPamentBusinessLine;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import com.deloitte.services.fssc.business.pay.entity.PyPamentPublicLine;
import com.deloitte.services.fssc.business.pay.service.IPyPamentBusinessLineService;
import com.deloitte.services.fssc.business.pay.service.IPyPamentOrderInfoService;
import com.deloitte.services.fssc.business.pay.service.IPyPamentPrivateLineService;
import com.deloitte.services.fssc.business.pay.service.IPyPamentPublicLineService;
import com.deloitte.services.fssc.business.travle.service.ITasTravelReimburseService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.util.FsscCommonUtil;
import com.deloitte.services.fssc.engine.dockingEbs.service.IAccountVoucherToEbsService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
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

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description :   PyPamentOrderInfo控制器实现类
 * @Modified :
 */
@Api(tags = "付款单操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/pay/py_pament_order_info")
public class PyPamentOrderInfoController {

    @Autowired
    public IPyPamentOrderInfoService pyPamentOrderInfoService;

    @Autowired
    public IPyPamentBusinessLineService pyPamentBusinessLineService;

    @Autowired
    public IPyPamentPublicLineService pyPamentPublicLineService;

    @Autowired
    public IPyPamentPrivateLineService pyPamentPrivateLineService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;

    @Autowired
    private BpmTaskBiz bpmTaskBiz;

    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;

    @Autowired
    IGeExpensePaymentListService geExpensePaymentListService;//对公付款
    @Autowired
    private IBmBorrowBankService bmBorrowBankService;//对私，公务卡
    @Autowired
    IGePrivatePaymentListService gePrivatePaymentListService;//劳务报账对私

    @Autowired
    public IGeGeneralExpenseService geGeneralExpenseService;//通用报账
    @Autowired
    public ITasTravelReimburseService tasTravelReimburseService;//差旅报账
    @Autowired
    public IAdvancePaymentInfoService bmAdvancePaymentInfoService;//对公预付款
    @Autowired
    public IBorrowMoneyInfoService borrowMoneyInfoService;//个人借款
    @Autowired
    public ILcLaborCostService lcLaborCostService;//劳务报账
    @Autowired
    public ICrbContractReimburseBillService crbContractReimburseBillService;//合同报账


    /**
     * 通用报账、
     * 差旅报账、
     * 对公预付款(无对私)、
     * 个人借款、公务卡号取的是银行账号  borrowAmount(还款金额，付款金额)
     * 劳务报账（无对公付款,无公务卡）、
     * 合同报账（无对私）
     * PUBLIC("对公付款","PUBLIC"),
     * PRIVATE("对私付款","PRIVATE"),
     * BUSINESS_CODE("公务卡","BUSINESS_CODE"),
     *
     * @param
     * @return
     */
    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @ResponseBody
    public Result<List<PyPamentOverListVo>> list(@Valid PyAllDocumentQueryForm pyAllDocumentQueryForm) {
        log.info("search with pyAllDocumentQueryForm:", pyAllDocumentQueryForm.toString());
        //对公付款单
        if (pyAllDocumentQueryForm.getConnectDocumentType().equals(FsscTableNameEums.PUBLIC.getValue())) {
            List<PyPamentOverListVo> pyPamentOverListVos = pyPamentOrderInfoService.findDocumentAll(pyAllDocumentQueryForm);
            return Result.success(pyPamentOverListVos);
        }
        //对私付款单
        if (pyAllDocumentQueryForm.getConnectDocumentType().equals(FsscTableNameEums.PRIVATE.getValue())) {
            List<PyPamentOverListVo> pyPamentPrivateListVos = pyPamentOrderInfoService.findPrivateAll(pyAllDocumentQueryForm);
            return Result.success(pyPamentPrivateListVos);
        }
        //公务卡
        if (pyAllDocumentQueryForm.getConnectDocumentType().equals(FsscTableNameEums.BUSINESS_CARD.getValue())) {
            List<PyPamentOverListVo> pyBussinessCardListVos = pyPamentOrderInfoService.findBussinessCardAll(pyAllDocumentQueryForm);
            return Result.success(pyBussinessCardListVos);
        }
        throw new FSSCException(FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
    }


    @DeleteMapping(value = "/deleteById")
    @ApiOperation(value = "根据id删除", notes = "根据url的id来指定删除对象")
    @Transactional
    public Result deleteById(@RequestBody Long id) {
        if (id != null) {
            PyPamentOrderInfo pyPamentOrderInfo = pyPamentOrderInfoService.getById(id);
            String documentStatus = pyPamentOrderInfo.getDocumentStatus();
            AssertUtils.asserts(FsscEums.NEW.getValue().equals(documentStatus) ||
                    FsscEums.RECALLED.getValue().equals(documentStatus) ||
                    FsscEums.REJECTED.getValue().equals(documentStatus), FsscErrorType.ONLY_DELETE_NEW_DOCUMENT);
            removeLock(id);
            pyPamentOrderInfoService.removeById(id);
            //删除对公付款，对私，公务
            QueryWrapper<PyPamentPublicLine> publiceLineQuery = new QueryWrapper<>();
            publiceLineQuery.eq("DOCUMENT_ID", id);
            pyPamentPublicLineService.remove(publiceLineQuery);

            //停止流程
            bpmProcessBiz.stopProcess(id);
            return Result.success();
        }
        throw new FSSCException(FsscErrorType.NOT_SAVE_NOT_DELETE);
    }

    /**
     * 根据单据id 获取信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getPyPamentById")
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    public Result<PyPamentOrderInfoVo> getPyPamentById(Long id) {
        log.info("id:" + id);
        PyPamentOrderInfo pyPamentOrderInfo = pyPamentOrderInfoService.getById(id);
        AssertUtils.asserts(pyPamentOrderInfo != null, FsscErrorType.DOCUMENT_NOT_FIND);
        PyPamentOrderInfoVo pyPamentOrderInfoVo = new BeanUtils<PyPamentOrderInfoVo>().copyObj(pyPamentOrderInfo, PyPamentOrderInfoVo.class);
        //查询公务卡
        QueryWrapper<PyPamentBusinessLine> businessWrapper = new QueryWrapper<>();
        businessWrapper.eq("DOCUMENT_ID", id).
                eq("DOCUMENT_TYPE", FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue()).eq("LINE_TYPE", FsscTableNameEums.BUSINESS_CARD.getValue());
        List<PyPamentBusinessLine> pyPamentBusinessLines = pyPamentBusinessLineService.list(businessWrapper);
        List<PyPamentBusinessLineVo> pyPamentBusinessLineVos =
                new BeanUtils<PyPamentBusinessLineVo>().copyObjs(pyPamentBusinessLines, PyPamentBusinessLineVo.class);
        pyPamentOrderInfoVo.setPyPamentBusinessLineVos(pyPamentBusinessLineVos);
        //查询对私付款
        QueryWrapper<PyPamentBusinessLine> privateWrapper = new QueryWrapper<>();
        privateWrapper.eq("DOCUMENT_ID", id).
                eq("DOCUMENT_TYPE", FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue()).eq("LINE_TYPE", FsscTableNameEums.PRIVATE.getValue());
        List<PyPamentBusinessLine> privateLine = pyPamentBusinessLineService.list(privateWrapper);
        List<PyPamentBusinessLineVo> privateLineVos =
                new BeanUtils<PyPamentBusinessLineVo>().copyObjs(privateLine, PyPamentBusinessLineVo.class);
        pyPamentOrderInfoVo.setPyPamentPrivateLineVos(privateLineVos);
        //查询对公付款
        QueryWrapper<PyPamentBusinessLine> publicWrapper = new QueryWrapper<>();
        publicWrapper.eq("DOCUMENT_ID", id)
                .eq("DOCUMENT_TYPE", FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue()).eq("LINE_TYPE", FsscTableNameEums.PUBLIC.getValue());
        List<PyPamentBusinessLine> publicLine = pyPamentBusinessLineService.list(publicWrapper);
        List<PyPamentBusinessLineVo> publicLineVos =
                new BeanUtils<PyPamentBusinessLineVo>().copyObjs(publicLine, PyPamentBusinessLineVo.class);
        pyPamentOrderInfoVo.setPyPamentPublicLineVos(publicLineVos);
        //审批历史
        try {
            List<BpmProcessTaskVo> history = bpmTaskBiz.findHistory(id, FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
            pyPamentOrderInfoVo.setBpmProcessTaskVos(new BeanUtils<BpmProcessTaskVo>().copyObjs(history, BpmProcessTaskVo.class));
        } catch (FSSCException e) {
            e.getMessage();
        }
        return new Result<PyPamentOrderInfoVo>().success(pyPamentOrderInfoVo);
    }

    @GetMapping(value = "/page/conditions")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    public Result<IPage<PyPamentOrderInfoVo>> search(@Valid PyPamentOrderInfoQueryForm pyPamentOrderInfoQueryForm) {
        log.info("search with pyPamentOrderInfoQueryForm:", pyPamentOrderInfoQueryForm.toString());
        IPage<PyPamentOrderInfo> pyPamentOrderInfoPage = pyPamentOrderInfoService.selectPage(pyPamentOrderInfoQueryForm);
        IPage<PyPamentOrderInfoVo> pyPamentOrderInfoVoPage = new BeanUtils<PyPamentOrderInfoVo>().copyPageObjs(pyPamentOrderInfoPage, PyPamentOrderInfoVo.class);
        return new Result<IPage<PyPamentOrderInfoVo>>().sucess(pyPamentOrderInfoVoPage);
    }

    @PostMapping(value = "/doSaveOrUpdate")
    @ApiOperation(value = "新增或修改", notes = "新增或修改")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    @Transactional
    public Result doSaveOrUpdate(@RequestBody @Valid PyPamentOrderInfoForm pyPamentOrderInfoForm) {
        log.info("tasTravelApplyInfoForm:" + pyPamentOrderInfoForm.toString());
        return saveOrUpdate(pyPamentOrderInfoForm);
    }

    public Result saveOrUpdate(PyPamentOrderInfoForm pyPamentOrderInfoForm) {
        //验证单据是否存在
        FsscCommonUtil.valiHasData(pyPamentOrderInfoForm.getId()
                , FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
        PyPamentOrderInfo pyPamentOrderInfo = new BeanUtils<PyPamentOrderInfo>().copyObj(pyPamentOrderInfoForm, PyPamentOrderInfo.class);
        boolean saveOrUpdateSuccess = pyPamentOrderInfoService.saveOrUpdate(pyPamentOrderInfo);
        //修改关联的差旅申请锁定状态
        changeOthreConnect(pyPamentOrderInfoForm);
        if (saveOrUpdateSuccess) {
            //保存或修改行公务卡，对公信息，对私信息  先删除原有的，再新增
            QueryWrapper<PyPamentBusinessLine> bussinseWrapper = new QueryWrapper<>();
            bussinseWrapper.eq("DOCUMENT_ID", pyPamentOrderInfo.getId()).eq("DOCUMENT_TYPE", FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
            List<PyPamentBusinessLineForm> bussinseFormList = pyPamentOrderInfoForm.getPyPamentBusinessLineForms();
            if (CollectionUtils.isNotEmpty(bussinseFormList)) {
                List<PyPamentBusinessLine> pyPamentBusinessLines = new BeanUtils<PyPamentBusinessLine>().copyObjs(bussinseFormList, PyPamentBusinessLine.class);
                List<Long> longList = pyPamentBusinessLines.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                longList.removeAll(Collections.singleton(null));
                if (CollectionUtils.isNotEmpty(longList)) {
                    bussinseWrapper.notIn("id", longList);
                }
                pyPamentBusinessLineService.remove(bussinseWrapper);
                for (PyPamentBusinessLine pyPamentBusinessLine : pyPamentBusinessLines) {
                    pyPamentBusinessLine.setDocumentId(pyPamentOrderInfo.getId());
                    pyPamentBusinessLine.setDocumentType(FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
                }
                if (CollectionUtils.isNotEmpty(pyPamentBusinessLines)) {
                    pyPamentBusinessLineService.saveOrUpdateBatch(pyPamentBusinessLines);
                }

            } else {
                pyPamentBusinessLineService.remove(bussinseWrapper);
            }

            /*//对公信息
            QueryWrapper<PyPamentPublicLine> leaveaBjWrapper=new QueryWrapper<>();
            leaveaBjWrapper.eq("DOCUMENT_ID", pyPamentOrderInfo.getId()).eq("DOCUMENT_TYPE",FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
            List<PyPamentPublicLineForm> pyPamentPublicLineForms = pyPamentOrderInfoForm.getPyPamentPublicLineForms();
            if (CollectionUtils.isNotEmpty(pyPamentPublicLineForms)) {
                List<PyPamentPublicLine> pyPamentPublicLines= new BeanUtils<PyPamentPublicLine>().copyObjs(pyPamentPublicLineForms, PyPamentPublicLine.class);
                List<Long> longList = pyPamentPublicLines.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                longList.removeAll(Collections.singleton(null));
                if(CollectionUtils.isNotEmpty(longList)) {
                    leaveaBjWrapper.notIn("id", longList);
                }
                pyPamentPublicLineService.remove(leaveaBjWrapper);

                for (PyPamentPublicLine pyPamentPublicLine : pyPamentPublicLines) {
                    pyPamentPublicLine.setDocumentId(pyPamentOrderInfo.getId());
                    pyPamentPublicLine.setDocumentType(FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
                }
                pyPamentPublicLineService.saveOrUpdateBatch(pyPamentPublicLines);
            }else {
                pyPamentPublicLineService.remove(leaveaBjWrapper);
            }

            //对私信息
            QueryWrapper<PyPamentPrivateLine> privateWrapper=new QueryWrapper<>();
            privateWrapper.eq("DOCUMENT_ID", pyPamentOrderInfo.getId()).eq("DOCUMENT_TYPE",FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
            List<PyPamentPrivateLineForm> pyPamentPrivateLineForms = pyPamentOrderInfoForm.getPyPamentPrivateLineForms();
            if (CollectionUtils.isNotEmpty(pyPamentPrivateLineForms)) {
                List<PyPamentPrivateLine> pyPamentPrivateLines= new BeanUtils<PyPamentPrivateLine>().copyObjs(pyPamentPrivateLineForms, PyPamentPrivateLine.class);
                List<Long> longList = pyPamentPrivateLines.stream().map(bk -> bk.getId()).collect(Collectors.toList());
                longList.removeAll(Collections.singleton(null));
                if(CollectionUtils.isNotEmpty(longList)) {
                    leaveaBjWrapper.notIn("id", longList);
                }
                pyPamentPrivateLineService.remove(privateWrapper);

                for (PyPamentPrivateLine pyPamentPrivateLine : pyPamentPrivateLines) {
                    pyPamentPrivateLine.setDocumentId(pyPamentOrderInfo.getId());
                    pyPamentPrivateLine.setDocumentType(FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
                }
                pyPamentPrivateLineService.saveOrUpdateBatch(pyPamentPrivateLines);
            }else {
                pyPamentPrivateLineService.remove(privateWrapper);
            }*/
            //文件列表保存
            List<Long> fileIds = pyPamentOrderInfoForm.getFileIds();
            if (fileIds != null) {
                fileIds.removeAll(Collections.singleton(null));
            }
            if (CollectionUtils.isNotEmpty(fileIds)) {
                QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
                fileQueryWrapper.eq(File.DOCUMENT_ID, pyPamentOrderInfo.getId())
                        .eq(File.DOCUMENT_TYPE, FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue())
                        .notIn(File.ID, fileIds);
                fileService.remove(fileQueryWrapper);

                Collection<File> files = fileService.listByIds(fileIds);
                files.stream().forEach(ka -> ka.setDocumentId(pyPamentOrderInfo.getId()));
                fileService.saveOrUpdateBatch(files);
            }
            return Result.success(new BeanUtils<PyPamentOrderInfoVo>().copyObj(pyPamentOrderInfoService.getById(pyPamentOrderInfo.getId()), PyPamentOrderInfoVo.class));
        }
        throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
    }

    /**
     * @param pyPamentOrderInfoForm
     * @Autowired IGeExpensePaymentListService geExpensePaymentListService;//对公付款
     * @Autowired private IBmBorrowBankService bmBorrowBankService;//对私，公务卡
     * @Autowired IGePrivatePaymentListService gePrivatePaymentListService;//劳务报账对私
     */
    public void changeOthreConnect(PyPamentOrderInfoForm pyPamentOrderInfoForm) {
        List<PyPamentBusinessLineForm> pyPamentBusinessLines = pyPamentOrderInfoForm.getPyPamentBusinessLineForms();
        if (CollectionUtils.isNotEmpty(pyPamentBusinessLines)) {
            for (PyPamentBusinessLineForm pyPamentBusinessLineForm : pyPamentBusinessLines) {
                Long connectDocumentLindId = pyPamentBusinessLineForm.getConnectDocumentIdLine();
                GeExpensePaymentList geExpensePaymentList = geExpensePaymentListService.getById(connectDocumentLindId);
                if (geExpensePaymentList != null) {
                    geExpensePaymentList.setEx1("Y");
                    geExpensePaymentListService.updateById(geExpensePaymentList);
                }
                BmBorrowBank bmBorrowBank = bmBorrowBankService.getById(connectDocumentLindId);
                if (bmBorrowBank != null) {
                    bmBorrowBank.setEx1("Y");
                    bmBorrowBankService.updateById(bmBorrowBank);
                }
                GePrivatePaymentList gePrivatePaymentList = gePrivatePaymentListService.getById(connectDocumentLindId);
                if (gePrivatePaymentList != null) {
                    gePrivatePaymentList.setEx1("Y");
                    gePrivatePaymentListService.updateById(gePrivatePaymentList);
                }
            }
        }

    }


    @PostMapping(value = "/sumbitAdvance")
    @ApiOperation(value = "提交流程", notes = "提交流程")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    @Transactional
    public Result<PyPamentOrderInfoVo> sumbitAdvance(@RequestBody @Valid PyPamentOrderInfoForm pyPamentOrderInfoForm) {
        Result<PyPamentOrderInfoVo> queryDatas = saveOrUpdate(pyPamentOrderInfoForm);
        PyPamentOrderInfoVo pyPamentOrderInfoVo = queryDatas.getData();
        Long id = pyPamentOrderInfoVo.getId();
        AssertUtils.asserts(id != null, FsscErrorType.TRAVLE_APPLY_NO_EMPTY);
        //验证付款单子表对应其他单据的子表是否全选（必须全选）
        vaildOverDocumentLineTable(pyPamentOrderInfoVo);
        // payDocumentNum（待支付单据编号）
        /*String payDocumentNum = pyPamentOrderInfoVo.getPayDocumentNum();
        //回写支付状态
        List<PyPamentBusinessLineVo> lineVos = pyPamentOrderInfoVo.getPyPamentBusinessLineVos();

        if (CollectionUtils.isNotEmpty(lineVos)) {

            for (PyPamentBusinessLineVo vo : lineVos) {
                String connectDocumentNum = vo.getConnectDocumentNum();
                if(StringUtil.isNotEmpty(payDocumentNum)){
                    AssertUtils.asserts(payDocumentNum.equalsIgnoreCase(connectDocumentNum), FsscErrorType.DOCUENT_NUM_NOT_EQ);
                }
                if(StringUtil.isNotEmpty(connectDocumentNum)){
                    String simpleDoc = payDocumentNum.substring(3, 5);
                    if ("TY".equals(simpleDoc)) {
                        QueryWrapper<GeGeneralExpense> geQueryWrapper = new QueryWrapper<>();
                        geQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                        GeGeneralExpense generalExpense = geGeneralExpenseService.getOne(geQueryWrapper);
                        if (generalExpense != null) {
                            generalExpense.setIsGeneratePayOrder("Y");
                            generalExpense.setPayMentId(id);
                            generalExpense.setPayStatus(vo.getPayStatus());
                            BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(generalExpense.getNoPaidAmount());
                            BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(generalExpense.getPaidAmount());
                            BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                            BigDecimal yizhifu = generalExpensePaidAmount.add(payAmount);
                            generalExpense.setPaidAmount(generalExpensePaidAmount.add(payAmount));
                            generalExpense.setNoPaidAmount(generalExpenseNoPaidAmount.subtract(yizhifu));
                            geGeneralExpenseService.saveOrUpdate(generalExpense);
                        }
                    }
                    if ("CL".equals(simpleDoc)) {//差旅888CL20190400141
                        QueryWrapper<TasTravelReimburse> traveQueryWrapper = new QueryWrapper<>();
                        traveQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                        TasTravelReimburse tasTravelReimburse = tasTravelReimburseService.getOne(traveQueryWrapper);
                        if (tasTravelReimburse != null) {
                            tasTravelReimburse.setIsGeneratePayOrder("Y");
                            tasTravelReimburse.setPayMentId(id);
                            tasTravelReimburse.setPayStatus(vo.getPayStatus());
                            BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(tasTravelReimburse.getNoPaidAmount());
                            BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(tasTravelReimburse.getPaidAmount());
                            BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                            BigDecimal yizhifu = generalExpensePaidAmount.add(payAmount);
                            tasTravelReimburse.setPaidAmount(generalExpensePaidAmount.add(payAmount));
                            tasTravelReimburse.setNoPaidAmount(generalExpenseNoPaidAmount.subtract(yizhifu));
                            tasTravelReimburseService.updateById(tasTravelReimburse);
                        }
                    }

                    if ("YF".equals(simpleDoc)) {//对公预付款888YF20190400261
                        QueryWrapper<AdvancePaymentInfo> adQueryWrapper = new QueryWrapper<>();
                        adQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                        AdvancePaymentInfo advancePaymentInfo = bmAdvancePaymentInfoService.getOne(adQueryWrapper);
                        if (advancePaymentInfo != null) {
                            advancePaymentInfo.setIsGeneratePayOrder("Y");
                            advancePaymentInfo.setPayMentId(id);
                            advancePaymentInfo.setPayStatus(vo.getPayStatus());
                            BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(advancePaymentInfo.getUnpaidAmount());
                            BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(advancePaymentInfo.getAmountPaid());
                            BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                            BigDecimal yizhifu = generalExpensePaidAmount.add(payAmount);
                            advancePaymentInfo.setAmountPaid(generalExpensePaidAmount.add(payAmount));
                            advancePaymentInfo.setUnpaidAmount(generalExpenseNoPaidAmount.subtract(yizhifu));
                            bmAdvancePaymentInfoService.updateById(advancePaymentInfo);
                        }
                    }

                    if ("JK".equals(simpleDoc)) {//个人借款888JK20190400145
                        QueryWrapper<BorrowMoneyInfo> brQueryWrapper = new QueryWrapper<>();
                        brQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                        BorrowMoneyInfo borrowMoneyInfo = borrowMoneyInfoService.getOne(brQueryWrapper);
                        if (borrowMoneyInfo != null) {
                            borrowMoneyInfo.setIsGeneratePayOrder("Y");
                            borrowMoneyInfo.setPayMentId(id);
                            borrowMoneyInfo.setPayStatus(vo.getPayStatus());
                            BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(borrowMoneyInfo.getNoPaidAmount());
                            BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(borrowMoneyInfo.getPaidAmount());
                            BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                            BigDecimal yizhifu = generalExpensePaidAmount.add(payAmount);
                            borrowMoneyInfo.setPaidAmount(generalExpensePaidAmount.add(payAmount));
                            borrowMoneyInfo.setNoPaidAmount(generalExpenseNoPaidAmount.subtract(yizhifu));
                            borrowMoneyInfoService.updateById(borrowMoneyInfo);
                        }
                    }

                    if ("HT".equals(simpleDoc)) {//合同报账888HT20190400081
                        QueryWrapper<CrbContractReimburseBill> crbQueryWrapper = new QueryWrapper<>();
                        crbQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                        CrbContractReimburseBill crbContractReimburseBill = crbContractReimburseBillService.getOne(crbQueryWrapper);
                        if (crbContractReimburseBill != null) {
                            crbContractReimburseBill.setIsGeneratePayOrder("Y");
                            crbContractReimburseBill.setPayMentId(id);
                            crbContractReimburseBill.setPayStatus(vo.getPayStatus());
                            BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(crbContractReimburseBill.getUnpaidAmount());
                            BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(crbContractReimburseBill.getAmountPaid());
                            BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                            BigDecimal yizhifu = generalExpensePaidAmount.add(payAmount);
                            crbContractReimburseBill.setAmountPaid(generalExpensePaidAmount.add(payAmount));
                            crbContractReimburseBill.setUnpaidAmount(generalExpenseNoPaidAmount.subtract(yizhifu));
                            crbContractReimburseBillService.updateById(crbContractReimburseBill);
                        }
                    }
                }
            }

        }

        //公务卡
        List<PyPamentBusinessLineVo> pyPamentBusinessLineVos = pyPamentOrderInfoVo.getPyPamentBusinessLineVos();
        //对私
        List<PyPamentBusinessLineVo> pyPamentPrivateLineVos = pyPamentOrderInfoVo.getPyPamentPrivateLineVos();
        //对公
        List<PyPamentBusinessLineVo> pyPamentPublicLineVos = pyPamentOrderInfoVo.getPyPamentPublicLineVos();

        if (CollectionUtils.isNotEmpty(pyPamentBusinessLineVos)) {
            for (PyPamentBusinessLineVo pyPamentBusinessLineVo : pyPamentBusinessLineVos) {
                String lineType = pyPamentBusinessLineVo.getLineType();
                if ("BUSINESS_CARD".equals(lineType)) {
                    Long connectDoucmentIdLine = pyPamentBusinessLineVo.getConnectDocumentIdLine();//获取关联的行ID；
                    // bmBorrowBankService
                    BmBorrowBank bmBorrowBank = bmBorrowBankService.getById(connectDoucmentIdLine);
                    bmBorrowBank.setEx1("Y");//此目的在于判断该行表信息是否被关联过；
                    bmBorrowBank.setPayStatus(pyPamentBusinessLineVo.getPayStatus());
                    bmBorrowBankService.updateById(bmBorrowBank);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(pyPamentPrivateLineVos)) {
            for (PyPamentBusinessLineVo pyPamentBusinessLineVo : pyPamentPrivateLineVos) {
                String lineType = pyPamentBusinessLineVo.getLineType();
                String connectDocumentType = pyPamentBusinessLineVo.getConnectDocumentType();//关联的单据类型
                if ("PRIVATE".equals(lineType)) {
                    Long connectDoucmentIdLine = pyPamentBusinessLineVo.getConnectDocumentIdLine();//获取关联的行ID；
                    if (connectDocumentType.equals(FsscTableNameEums.LC_LABOR_COST.getValue())) {
                        //gePrivatePaymentListService
                        GePrivatePaymentList gePrivatePaymentList = gePrivatePaymentListService.getById(connectDoucmentIdLine);
                        gePrivatePaymentList.setEx1("Y");//此目的在于判断该行表信息是否被关联过；
                        gePrivatePaymentList.setPayStatus(pyPamentBusinessLineVo.getPayStatus());
                        gePrivatePaymentListService.updateById(gePrivatePaymentList);
                    } else {
                        // bmBorrowBankService
                        BmBorrowBank bmBorrowBank = bmBorrowBankService.getById(connectDoucmentIdLine);
                        bmBorrowBank.setEx1("Y");//此目的在于判断该行表信息是否被关联过；
                        bmBorrowBank.setPayStatus(pyPamentBusinessLineVo.getPayStatus());
                        bmBorrowBankService.updateById(bmBorrowBank);
                    }
                }
            }

        }
        if (CollectionUtils.isNotEmpty(pyPamentPublicLineVos)) {
            for (PyPamentBusinessLineVo pyPamentBusinessLineVo : pyPamentPublicLineVos) {
                String lineType = pyPamentBusinessLineVo.getLineType();
                if ("PUBLIC".equals(lineType)) {
                    Long connectDoucmentIdLine = pyPamentBusinessLineVo.getConnectDocumentIdLine();//获取关联的行ID；
                    // geExpensePaymentListService
                    GeExpensePaymentList geExpensePaymentList = geExpensePaymentListService.getById(connectDoucmentIdLine);
                    geExpensePaymentList.setEx1("Y");//此目的在于判断该行表信息是否被关联过；
                    geExpensePaymentList.setPayStatus(pyPamentBusinessLineVo.getPayStatus());
                    geExpensePaymentListService.updateById(geExpensePaymentList);
                }
            }
        }*/
        AssertUtils.asserts(FsscEums.NEW.getValue().equals(pyPamentOrderInfoVo.getDocumentStatus()) ||
                FsscEums.REJECTED.getValue().equals(pyPamentOrderInfoVo.getDocumentStatus()) ||
                FsscEums.RECALLED.getValue().equals(pyPamentOrderInfoVo.getDocumentStatus()), FsscErrorType.SUBMIT_NEW_REJECTED);
        ProcessValueForm valueForm = new ProcessValueForm();
        valueForm.setRequest("N");
        valueForm.setTotalAmount(pyPamentOrderInfoVo.getTotalAmount().toString());
        valueForm.setDocumentId(pyPamentOrderInfoVo.getId());
        valueForm.setDocumentType(FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
        valueForm.setCreateBy(pyPamentOrderInfoVo.getCreateBy());
        valueForm.setDeptCode(pyPamentOrderInfoVo.getDeptCode());
        valueForm.setUnitCode(pyPamentOrderInfoVo.getUnitCode());
        Map<String, String> andAddProcessValue = baseBpmProcessValService.getAndSaveProcessValue(valueForm);


        ReSubmitProcessForm form = new ReSubmitProcessForm();
        form.setProcessVariables(andAddProcessValue);
        form.setDocumentId(id);
        form.setDocumentType(FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
        form.setDocumentNum(pyPamentOrderInfoVo.getDocumentNum());
        form.setProcessType(FsscEums.NORMAL_APPROVAL.getValue());
        form.setBudgetWarningCheck(pyPamentOrderInfoForm.getBudgetWarningCheck());
        accountVoucherToEbsService.generatePrefabricatedCredentials(form.getDocumentType(), form.getDocumentId());
        if (FsscEums.FIRST_SUBMIT.getValue().equals(pyPamentOrderInfoForm.getReSubmitType())) {
            bpmProcessBiz.startProcess(form);
        } else {
            form.setReSubmitType(pyPamentOrderInfoForm.getReSubmitType());
            bpmProcessBiz.reSubmit(form);
        }
        return Result.success();
    }

    @Autowired
    private IBaseBpmProcessValService baseBpmProcessValService;

    public void vaildOverDocumentLineTable(PyPamentOrderInfoVo pyPamentOrderInfoVo) {
       /* Result<PyPamentOrderInfoVo> queryDatas = saveOrUpdate(pyPamentOrderInfoForm);
        PyPamentOrderInfoVo pyPamentOrderInfoVo = queryDatas.getData();*/
        QueryWrapper<PyPamentBusinessLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DOCUMENT_ID", pyPamentOrderInfoVo.getId()).eq("DOCUMENT_TYPE", FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
        //公务卡,工资卡，对公付款清单（选单子必须全部选完）
        List<PyPamentBusinessLine> pyPamentBusinessLines = pyPamentBusinessLineService.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(pyPamentBusinessLines)) {
            for (PyPamentBusinessLine pyPamentBusinessLine:pyPamentBusinessLines) {
            //PyPamentBusinessLine pyPamentBusinessLine = pyPamentBusinessLine.get(0);
            pyPamentOrderInfoVo.setEx2(pyPamentBusinessLine.getConnectDocumentId() + "");
            QueryWrapper<PyPamentBusinessLine> queryWrapperTY = new QueryWrapper<>();
            queryWrapperTY.like("CONNECT_DOCUMENT_NUM", "TY").eq("DOCUMENT_ID", pyPamentOrderInfoVo.getId())
                    .eq("CONNECT_DOCUMENT_ID",pyPamentBusinessLine.getConnectDocumentId());
            int pyPamentBusinessLinesTY = pyPamentBusinessLineService.count(queryWrapperTY);
            int ty = pyPamentOrderInfoService.geExpenseAllDocument(pyPamentOrderInfoVo).intValue();
            if (pyPamentBusinessLinesTY - ty != 0) {
                throw new FSSCException(FsscErrorType.DOCUMENT_ALL_SELECT);
            }
            QueryWrapper<PyPamentBusinessLine> queryWrapperCL = new QueryWrapper<>();
            queryWrapperCL.like("CONNECT_DOCUMENT_NUM", "CL").eq("DOCUMENT_ID", pyPamentOrderInfoVo.getId())
                    .eq("CONNECT_DOCUMENT_ID",pyPamentBusinessLine.getConnectDocumentId());;
            int pyPamentBusinessLinesCL = pyPamentBusinessLineService.count(queryWrapperCL);
            int cy = pyPamentOrderInfoService.tavelAllDocument(pyPamentOrderInfoVo).intValue();
            if (pyPamentBusinessLinesCL - cy != 0) {
                throw new FSSCException(FsscErrorType.DOCUMENT_ALL_SELECT);
            }

            QueryWrapper<PyPamentBusinessLine> queryWrapperYF = new QueryWrapper<>();
            queryWrapperYF.like("CONNECT_DOCUMENT_NUM", "YF").eq("DOCUMENT_ID", pyPamentOrderInfoVo.getId())
                    .eq("CONNECT_DOCUMENT_ID",pyPamentBusinessLine.getConnectDocumentId());;
            int pyPamentBusinessLinesYF = pyPamentBusinessLineService.count(queryWrapperYF);
            int yf = pyPamentOrderInfoService.advanceAllDocument(pyPamentOrderInfoVo).intValue();
            if (pyPamentBusinessLinesYF - yf != 0) {
                throw new FSSCException(FsscErrorType.DOCUMENT_ALL_SELECT);
            }

            QueryWrapper<PyPamentBusinessLine> queryWrapperJK = new QueryWrapper<>();
            queryWrapperJK.like("CONNECT_DOCUMENT_NUM", "JK").eq("DOCUMENT_ID", pyPamentOrderInfoVo.getId())
                    .eq("CONNECT_DOCUMENT_ID",pyPamentBusinessLine.getConnectDocumentId());;
            int pyPamentBusinessLinesJK = pyPamentBusinessLineService.count(queryWrapperJK);
            int jk = pyPamentOrderInfoService.borrowAllDocument(pyPamentOrderInfoVo).intValue();
            if (pyPamentBusinessLinesJK - jk != 0) {
                throw new FSSCException(FsscErrorType.DOCUMENT_ALL_SELECT);
            }

            QueryWrapper<PyPamentBusinessLine> queryWrapperHT = new QueryWrapper<>();
            queryWrapperHT.like("CONNECT_DOCUMENT_NUM", "HT").eq("DOCUMENT_ID", pyPamentOrderInfoVo.getId())
                    .eq("CONNECT_DOCUMENT_ID",pyPamentBusinessLine.getConnectDocumentId());;
            int pyPamentBusinessLinesHT = pyPamentBusinessLineService.count(queryWrapperHT);
            int ht = pyPamentOrderInfoService.contractAllDocument(pyPamentOrderInfoVo).intValue();
            if (pyPamentBusinessLinesHT - ht != 0) {
                throw new FSSCException(FsscErrorType.DOCUMENT_ALL_SELECT);
            }

            QueryWrapper<PyPamentBusinessLine> queryWrapperLW = new QueryWrapper<>();
            queryWrapperLW.like("CONNECT_DOCUMENT_NUM", "LW").eq("DOCUMENT_ID", pyPamentOrderInfoVo.getId())
                    .eq("CONNECT_DOCUMENT_ID",pyPamentBusinessLine.getConnectDocumentId());;
            int pyPamentBusinessLinesLW = pyPamentBusinessLineService.count(queryWrapperLW);
            int lw = pyPamentOrderInfoService.laborAllDocument(pyPamentOrderInfoVo).intValue();
            if (pyPamentBusinessLinesLW - lw != 0) {
                throw new FSSCException(FsscErrorType.DOCUMENT_ALL_SELECT);
            }
        }
        }

    }

    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(PyPamentOrderInfoQueryForm pyPamentOrderInfoQueryForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(pyPamentOrderInfoQueryForm));
        Map<String, String> docStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.DOCUMENT_STATUS.getValue()));
        Map<String, String> payStatusMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.PAY_STATUS.getValue()));
        Map<String, String> currencyCodeMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.CURRENCY_CODE.getValue()));
        Map<String, String> bankTypeMap = commonServices.getValueByCode(commonServices.findDicValueList(FsscEums.BANK_TYPE.getValue()));
        List<PyPamentOrderInfo> records = pyPamentOrderInfoService.selectPage(pyPamentOrderInfoQueryForm).getRecords();
        String[] title = {"序号", "单据编号", "单据状态", "创建人", "归属单位", "归属部门", "合计金额", "币种 "
                , "已支付金额", "未支付金额", "创建日期", "是否事后补单", "银行账号", "账户性质"};
        String fileName = "借款信息列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            PyPamentOrderInfo vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentNum();
            content[i][2] = docStatusMap.get(vo.getDocumentStatus());
            content[i][3] = vo.getCreateUserName();
            content[i][4] = vo.getUnitName();
            content[i][5] = vo.getDeptName();
            content[i][6] = StringUtil.objectToString(vo.getTotalAmount());
            content[i][7] = currencyCodeMap.get(vo.getCurrencyCode());
            content[i][8] = StringUtil.objectToString(vo.getPaidAmount());
            content[i][9] = StringUtil.objectToString(vo.getNoPaidAmount());
            if (vo.getCreateTime() != null) {
                content[i][10] = LocalDateTimeUtils.formatTime(vo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            }
            content[i][11] = ("Y".equals(vo.getIsAfterPatch()) ? "是" : "否");
            //content[i][12] = payStatusMap.get(vo.getPayStatus());
            content[i][12] = vo.getBankAccount();
            content[i][13] = bankTypeMap.get(vo.getBankType());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    public void removeLock(Long id) {
        Result<PyPamentOrderInfoVo> queryDatas = getPyPamentById(id);
        PyPamentOrderInfoVo pyPamentOrderInfoVo = queryDatas.getData();
        // PyPamentOrderInfoVo pyPamentOrderInfoVo=new BeanUtils<PyPamentOrderInfoVo>().copyObj(pyPamentOrderInfo,PyPamentOrderInfoVo.class);
        List<PyPamentBusinessLineVo> pyPamentBusinessLineVos = pyPamentOrderInfoVo.getPyPamentBusinessLineVos();
        List<PyPamentBusinessLineVo> pyPamentPrivateLineVos = pyPamentOrderInfoVo.getPyPamentPrivateLineVos();
        List<PyPamentBusinessLineVo> pyPamentPublicLineVos = pyPamentOrderInfoVo.getPyPamentPublicLineVos();
        if (CollectionUtils.isNotEmpty(pyPamentBusinessLineVos)) {
            for (PyPamentBusinessLineVo pyPamentBusinessLineVo : pyPamentBusinessLineVos) {
                String lineType = pyPamentBusinessLineVo.getLineType();
                if ("BUSINESS_CARD".equals(lineType)) {
                    Long connectDoucmentIdLine = pyPamentBusinessLineVo.getConnectDocumentIdLine();//获取关联的行ID；
                    BmBorrowBank bmBorrowBank = bmBorrowBankService.getById(connectDoucmentIdLine);
                    bmBorrowBank.setEx1("N");//此目的在于判断该行表信息是否被关联过；
                    bmBorrowBankService.updateById(bmBorrowBank);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(pyPamentPrivateLineVos)) {
            for (PyPamentBusinessLineVo pyPamentBusinessLineVo : pyPamentPrivateLineVos) {
                String lineType = pyPamentBusinessLineVo.getLineType();
                String connectDocumentType = pyPamentBusinessLineVo.getConnectDocumentType();//关联的单据类型
                if ("PRIVATE".equals(lineType)) {
                    Long connectDoucmentIdLine = pyPamentBusinessLineVo.getConnectDocumentIdLine();//获取关联的行ID；
                    if (connectDocumentType.equals(FsscTableNameEums.LC_LABOR_COST.getValue())) {
                        //gePrivatePaymentListService
                        GePrivatePaymentList gePrivatePaymentList = gePrivatePaymentListService.getById(connectDoucmentIdLine);
                        gePrivatePaymentList.setEx1("N");//此目的在于判断该行表信息是否被关联过；
                        gePrivatePaymentListService.updateById(gePrivatePaymentList);
                    } else {
                        // bmBorrowBankService
                        BmBorrowBank bmBorrowBank = bmBorrowBankService.getById(connectDoucmentIdLine);
                        bmBorrowBank.setEx1("N");//此目的在于判断该行表信息是否被关联过；
                        bmBorrowBankService.updateById(bmBorrowBank);
                    }
                }
            }

        }
        if (CollectionUtils.isNotEmpty(pyPamentPublicLineVos)) {
            for (PyPamentBusinessLineVo pyPamentBusinessLineVo : pyPamentPublicLineVos) {
                String lineType = pyPamentBusinessLineVo.getLineType();
                if ("PUBLIC".equals(lineType)) {
                    Long connectDoucmentIdLine = pyPamentBusinessLineVo.getConnectDocumentIdLine();//获取关联的行ID；
                    GeExpensePaymentList geExpensePaymentList = geExpensePaymentListService.getById(connectDoucmentIdLine);
                    geExpensePaymentList.setEx1("N");//此目的在于判断该行表信息是否被关联过；
                    geExpensePaymentListService.updateById(geExpensePaymentList);
                }
            }
        }
    }

}



