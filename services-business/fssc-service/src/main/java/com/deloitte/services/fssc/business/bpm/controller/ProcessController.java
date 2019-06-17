package com.deloitte.services.fssc.business.bpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.bpm.param.TaskQueryForm;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessPassOrRejectForm;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessStartForm;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessVo;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.engine.manual.param.AvBusinessForEnterAccountForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentBusinessLineVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.budget.entity.BudgetDetailingAdjustHead;
import com.deloitte.services.fssc.budget.entity.BudgetPublicAdjust;
import com.deloitte.services.fssc.budget.service.IBudgetDetailingAdjustHeadService;
import com.deloitte.services.fssc.budget.service.IBudgetPublicAdjustService;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentInfo;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentInfoService;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;
import com.deloitte.services.fssc.business.borrow.service.IBmBorrowBankService;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyInfoService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.BpmTaskBiz;
import com.deloitte.services.fssc.business.bpm.biz.impl.BpmProcessBizImpl;
import com.deloitte.services.fssc.business.bpm.entity.BaseBpmRejectReason;
import com.deloitte.services.fssc.business.bpm.entity.Process;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmRejectReasonService;
import com.deloitte.services.fssc.business.bpm.service.IProcessService;
import com.deloitte.services.fssc.business.bpm.vo.FsscBpmTaskVo;
import com.deloitte.services.fssc.business.contract.entity.CrbContractReimburseBill;
import com.deloitte.services.fssc.business.contract.service.ICrbContractReimburseBillService;
import com.deloitte.services.fssc.business.edu.entity.FundsApplyHead;
import com.deloitte.services.fssc.business.edu.service.IFundsApplyHeadService;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;
import com.deloitte.services.fssc.business.general.service.IGeExpensePaymentListService;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseService;
import com.deloitte.services.fssc.business.ito.entity.IncomeTurnedOver;
import com.deloitte.services.fssc.business.ito.service.IIncomeTurnedOverService;
import com.deloitte.services.fssc.business.labor.entity.GePrivatePaymentList;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;
import com.deloitte.services.fssc.business.labor.service.IGePrivatePaymentListService;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostService;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import com.deloitte.services.fssc.business.pay.service.IPyPamentOrderInfoService;
import com.deloitte.services.fssc.business.poi.entity.PepaymentOrderInfo;
import com.deloitte.services.fssc.business.poi.service.IPepaymentOrderInfoService;
import com.deloitte.services.fssc.business.ppc.entity.ProjectConfirmation;
import com.deloitte.services.fssc.business.ppc.service.IProjectConfirmationService;
import com.deloitte.services.fssc.business.rep.entity.RecievePayment;
import com.deloitte.services.fssc.business.rep.service.IRecievePaymentService;
import com.deloitte.services.fssc.business.travle.entity.TasTravelApplyInfo;
import com.deloitte.services.fssc.business.travle.entity.TasTravelReimburse;
import com.deloitte.services.fssc.business.travle.service.ITasTravelApplyInfoService;
import com.deloitte.services.fssc.business.travle.service.ITasTravelReimburseService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherHead;
import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherHeadService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description :   Process控制器实现类
 * @Modified :
 */
@Api(tags = "工作流操作接口")
@Slf4j
@RestController
@RequestMapping(value = "fsscBpm")
public class ProcessController {

    @Autowired
    private BpmTaskBiz bpmTaskBiz;

    @Autowired
    private BpmProcessBiz bpmProcessBiz;

    @Autowired
    private FsscCommonServices fsscCommonServices;

    @Autowired
    private IProcessService processService;
    @Autowired
    private IBudgetDetailingAdjustHeadService budgetDetailingAdjustHeadService;

    @Autowired
    private IFundsApplyHeadService fundsApplyHeadService;

    @Autowired
    private IRecievePaymentService recievePaymentService;
    @Autowired
    private IBudgetPublicAdjustService budgetPublicAdjustService;
    @Autowired
    private IPepaymentOrderInfoService pepaymentOrderInfoService;
    @Autowired
    private IProjectConfirmationService projectConfirmationService;
    @Autowired
    private IAvManualVoucherHeadService manualVoucherHeadService;
    @Autowired
    private IIncomeTurnedOverService turnedOverService;

    @Autowired
    public ITasTravelReimburseService tasTravelReimburseService;//差旅报账

    @Autowired
    public IBorrowMoneyInfoService borrowMoneyInfoService;//个人借款
    @Autowired
    public IGeGeneralExpenseService geGeneralExpenseService;//通用报账
    @Autowired
    public IAdvancePaymentInfoService bmAdvancePaymentInfoService;//对公预付款
    @Autowired
    public ILcLaborCostService lcLaborCostService;//劳务报账
    @Autowired
    public ICrbContractReimburseBillService crbContractReimburseBillService;//合同报账

    @Autowired
    public ITasTravelApplyInfoService tasTravleApplyInfoService;
    @Autowired
    public IPyPamentOrderInfoService pyPamentOrderInfoService;

    @Autowired
    IGeExpensePaymentListService geExpensePaymentListService;//对公付款
    @Autowired
    private IBmBorrowBankService bmBorrowBankService;//对私，公务卡
    @Autowired
    IGePrivatePaymentListService gePrivatePaymentListService;//劳务报账对私


    /**
     * 提交流程，启动工作流
     *
     * @param form
     * @return
     */
    @PostMapping(value = "startProcess")
    @ApiOperation(value = "启动流程", notes = "启动流程,目前暂未使用")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result startProcess(@Valid @RequestBody ProcessStartForm form) {

        bpmProcessBiz.startProcess(form);

        return Result.success();
    }

    /**
     * 冲销
     *
     * @return
     */
    @PostMapping(value = "doChargeAgainst")
    @ApiOperation(value = "冲销", notes = "冲销")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result doChargeAgainst(@RequestBody AvBusinessForEnterAccountForm form) {
        Long documentId = form.getDocumentId();
        String documentType = form.getDocumentType();
        AssertUtils.asserts(documentId != null && StringUtil.isNotEmpty(documentType),
                FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        bpmProcessBiz.doChargeAgainst(documentId, documentType, form.getDefaultEffectiveDate());
        return Result.success();
    }


    /**
     * 提交流程，启动工作流
     *
     * @return
     */
    @GetMapping(value = "readMessage")
    @ApiOperation(value = "启动流程", notes = "启动流程")
    public Result readMessage(@RequestParam Long documentId) {

        bpmProcessBiz.readMessage(documentId);

        return Result.success();
    }

    /**
     * 重新提交
     *
     * @param form
     * @return
     */
    @PostMapping(value = "reSubmit")
    @ApiOperation(value = "重新提交", notes = "驳回后重新提交")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result reSubmit(@Valid @RequestBody ReSubmitProcessForm form) {

        bpmProcessBiz.reSubmit(form);

        return Result.success();
    }

    /**
     * 审批通过
     *
     * @param form
     * @return
     */
    @PostMapping(value = "passProcess")
    @ApiOperation(value = "审批通过/驳回后重新提交也调此接口", notes = "审批通过/驳回后重新提交也调此接口")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result passProcess(@Valid @RequestBody ProcessPassOrRejectForm form) {
        bpmProcessBiz.passProcess(form);
        return Result.success();
    }


    /**
     * 驳回到发起人
     *
     * @param form
     * @return
     */
    @PostMapping(value = "rejectProcessToFirst")
    @ApiOperation(value = "驳回", notes = "驳回")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result rejectProcessToFirst(@Valid @RequestBody ProcessPassOrRejectForm form) {
        bpmProcessBiz.rejectProcessToFirst(form);
        removeLock(form);
        return Result.success();
    }


    /**
     * 撤回流程
     *
     * @param
     * @return
     */
    @PostMapping(value = "rollbackProcess")
    @ApiOperation(value = "撤回流程", notes = "撤回流程")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result rollbackProcess(@Valid @RequestBody ProcessPassOrRejectForm form) {
        bpmProcessBiz.rollBackProcess(form);
        removeLock(form);
        return Result.success();
    }

    public void removeLock(ProcessPassOrRejectForm form) {
        //撤回驳回对已锁定的数据进行恢复
        Long doucmentId = form.getDocumentId();
        String documentType = form.getDocumentType();
        //去除借款单关联差旅申请
        if (documentType.equals(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue())) {
            BorrowMoneyInfo borrowMoneyInfo = borrowMoneyInfoService.getById(doucmentId);
            Long paymentId = borrowMoneyInfo.getPayMentId();
            if (paymentId != null) {
                borrowMoneyInfo.setPayMentId(null);
                borrowMoneyInfoService.updateById(borrowMoneyInfo);
            }
            Long applyForId = borrowMoneyInfo.getApplyForId();
            if (applyForId != null) {
                TasTravelApplyInfo tasTravelApplyInfo = tasTravleApplyInfoService.getById(applyForId);
                if (tasTravelApplyInfo != null) {
                    tasTravelApplyInfo.setIsBorrowConnect("N");
                    tasTravleApplyInfoService.updateById(tasTravelApplyInfo);
                }
            }

        }
        //去除差旅报账单关联差旅申请
        if (documentType.equals(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue())) {
            TasTravelReimburse tasTravelReimburse = tasTravelReimburseService.getById(doucmentId);
            Long applyForId = tasTravelReimburse.getApplyForId();
            if (applyForId != null) {
                TasTravelApplyInfo tasTravelApplyInfo = tasTravleApplyInfoService.getById(applyForId);
                if (tasTravelApplyInfo != null) {
                    tasTravelApplyInfo.setIsReimburConnect("N");
                    tasTravleApplyInfoService.updateById(tasTravelApplyInfo);
                }
            }
        }
        //去除付款单关联对公对私，公务卡单
        if (documentType.equals(FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue())) {
            PyPamentOrderInfo pyPamentOrderInfo = pyPamentOrderInfoService.getById(doucmentId);
            PyPamentOrderInfoVo pyPamentOrderInfoVo = new BeanUtils<PyPamentOrderInfoVo>().copyObj(pyPamentOrderInfo, PyPamentOrderInfoVo.class);
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
        removeSign(form);
    }

    public void removeSign(ProcessPassOrRejectForm form) {
        //撤回驳回对相关单据关连付款单的id置null
        Long doucmentId = form.getDocumentId();
        String documentType = form.getDocumentType();
        if (documentType.equals(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue())) {
            GeGeneralExpense generalExpense = geGeneralExpenseService.getById(doucmentId);
            Long paymentId = generalExpense.getPayMentId();
            if (paymentId != null) {
                generalExpense.setPayMentId(null);
                geGeneralExpenseService.updateById(generalExpense);
            }
        } else if (documentType.equals(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue())) {
            TasTravelReimburse tasTravelReimburse = tasTravelReimburseService.getById(doucmentId);
            Long paymentId = tasTravelReimburse.getPayMentId();
            if (paymentId != null) {
                tasTravelReimburse.setPayMentId(null);
                tasTravelReimburseService.updateById(tasTravelReimburse);
            }
        } else if (documentType.equals(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue())) {
            AdvancePaymentInfo advancePaymentInfo = bmAdvancePaymentInfoService.getById(doucmentId);
            Long paymentId = advancePaymentInfo.getPayMentId();
            if (paymentId != null) {
                advancePaymentInfo.setPayMentId(null);
                bmAdvancePaymentInfoService.updateById(advancePaymentInfo);
            }
        } else if (documentType.equals(FsscTableNameEums.LC_LABOR_COST.getValue())) {
            LcLaborCost lcLaborCost = lcLaborCostService.getById(doucmentId);
            Long paymentId = lcLaborCost.getPayMentId();
            if (paymentId != null) {
                lcLaborCost.setPayMentId(null);
                lcLaborCostService.updateById(lcLaborCost);
            }
        } else if (documentType.equals(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue())) {
            CrbContractReimburseBill crbContractReimburseBill = crbContractReimburseBillService.getById(doucmentId);
            Long paymentId = crbContractReimburseBill.getPayMentId();
            if (paymentId != null) {
                crbContractReimburseBill.setPayMentId(null);
                crbContractReimburseBillService.updateById(crbContractReimburseBill);
            }
        }
    }


    /**
     * 查询审批历史
     *
     * @param documentId
     * @param documentType
     * @return
     */
    @GetMapping(value = "findTaskHistory")
    @ApiOperation(value = "查询审批历史", notes = "查询审批历史")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<List<BpmProcessTaskVo>> findTaskHistory(@RequestParam("documentId") Long documentId, @RequestParam("documentType") String documentType) {
        List<BpmProcessTaskVo> history = bpmTaskBiz.findHistory(documentId, documentType);
        return Result.success(history);
    }


    /**
     * 查询我的待办
     *
     * @return
     */
    @GetMapping(value = "findMyTodo")
    @ApiOperation(value = "查询我的待办", notes = "查询我的待办")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<IPage<FsscBpmTaskVo>> findMyTodo(TaskQueryForm queryForm) {
        UserVo currentUser = fsscCommonServices.getCurrentUser();
        if (currentUser != null) {
            queryForm.setUserId(currentUser.getId());
        }
        GdcPage<BpmProcessTaskVo> myToDo = bpmTaskBiz.findMyToDo(queryForm);
        IPage<FsscBpmTaskVo> page = new BeanUtils<FsscBpmTaskVo>().copyPageObjs(myToDo, FsscBpmTaskVo.class);
        addAmountBpm(page.getRecords());
        return Result.success(page);
    }


    /**
     * 查询我的已办
     *
     * @return
     */
    @GetMapping(value = "findMyDone")
    @ApiOperation(value = "查询我的已办", notes = "查询我的已办")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<IPage<FsscBpmTaskVo>> findMyDone(TaskQueryForm queryForm) {
        UserVo currentUser = fsscCommonServices.getCurrentUser();
        if (currentUser != null) {
            queryForm.setUserId(currentUser.getId());
        }
        GdcPage<BpmProcessTaskVo> myDone = bpmTaskBiz.findMyDone(queryForm);
        IPage<FsscBpmTaskVo> page = new BeanUtils<FsscBpmTaskVo>().copyPageObjs(myDone, FsscBpmTaskVo.class);
        addAmountBpm(page.getRecords());
        return Result.success(page);
    }


    /**
     * 查询我的已办
     *
     * @return
     */
    @GetMapping(value = "findMyApply")
    @ApiOperation(value = "查询我的申请", notes = "查询我的申请")
    public Result<IPage<ProcessVo>> findMyApply(TaskQueryForm queryForm) {
        QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
        processQueryWrapper.eq(StringUtil.isNotEmpty(queryForm.getDocumentType()), Process.DOCUMENT_TYPE, queryForm.getDocumentType());
        processQueryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentNum()), Process.DOCUMENT_NUM, queryForm.getDocumentNum());
        processQueryWrapper.ge(StringUtil.isNotEmpty(queryForm.getStartTime()), Process.CREATE_TIME, queryForm.getStartTime());
        processQueryWrapper.le(StringUtil.isNotEmpty(queryForm.getEndTime()), Process.CREATE_TIME, queryForm.getEndTime());
        if (queryForm.getUserId() != null) {
            processQueryWrapper.eq(Process.CREATE_BY, StringUtil.castTolong(queryForm.getUserId()));
        } else {
            UserVo currentUser = fsscCommonServices.getCurrentUser();
            if (currentUser != null) {
                processQueryWrapper.eq(Process.CREATE_BY, StringUtil.castTolong(currentUser.getId()));
            } else {
                throw new FSSCException(FsscErrorType.GET_USER_NOT_EXIST);
            }
        }
        processQueryWrapper.orderByDesc(Process.CREATE_TIME);
        IPage<Process> page = processService.page(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), processQueryWrapper);
        IPage<ProcessVo> voIPage = new BeanUtils<ProcessVo>().copyPageObjs(page, ProcessVo.class);
        for (ProcessVo vo : voIPage.getRecords()) {
            vo.setProcessUrl(BpmProcessBizImpl.processUrls.get(vo.getDocumentType()));
            vo.setObjectUrl(BpmProcessBizImpl.processUrls.get(vo.getDocumentType()));
            vo.setDocumentName(FsscTableNameEums.getByValue(vo.getDocumentType()).getName());
            if (vo.getDocumentId() != null && StringUtil.isNotEmpty(vo.getDocumentType())) {
                String status = processService.findStatus(vo.getDocumentId(), vo.getDocumentType());
                if (StringUtil.isNotEmpty(status)) {
                    vo.setProcessStatusName(FsscEums.getByValue(status).getDescription());
                }
            }
        }
        return Result.success(voIPage);
    }


    @Autowired
    private IBaseBpmRejectReasonService rejectReasonService;

    /**
     * 查询单据驳回原因
     *
     * @return
     */
    @GetMapping(value = "findRejectReason")
    @ApiOperation(value = "查询驳回原因", notes = "查询驳回原因")
    public Result<BaseBpmRejectReason> findRejectReason(@RequestParam Long documentId){
        QueryWrapper<BaseBpmRejectReason> rejectReasonQueryWrapper=new QueryWrapper<>();
        rejectReasonQueryWrapper.eq(BaseBpmRejectReason.DOCUMENT_ID,documentId);
        BaseBpmRejectReason one = rejectReasonService.getOne(rejectReasonQueryWrapper);
        return Result.success(one);
    }


    private void addAmountBpm(List<FsscBpmTaskVo> vos) {
        if (CollectionUtils.isNotEmpty(vos)) {
            for (FsscBpmTaskVo vo : vos) {
                Long documentId = StringUtil.castTolong(vo.getObjectId());
                String documentType = vo.getObjectType();
                if (documentId == null) {
                    continue;
                }
                //付款单
                if ("PY_PAMENT_ORDER_INFO".equals(documentType)) {
                    PyPamentOrderInfo info = pyPamentOrderInfoService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getTotalAmount());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //预算细化调整申请
                if ("BUDGET_DETAILING_ADJUST_HEAD".equals(documentType)) {
                    BudgetDetailingAdjustHead info = budgetDetailingAdjustHeadService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getApplyTotal());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //通用报账
                if ("GE_GENERAL_EXPENSE".equals(documentType)) {
                    GeGeneralExpense info = geGeneralExpenseService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getTotalAmount());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //劳务费
                if ("LC_LABOR_COST".equals(documentType)) {
                    LcLaborCost info = lcLaborCostService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getShouldGiveTotalAmount());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //合同报账单
                if ("CRB_CONTRACT_REIMBURSE_BILL".equals(documentType)) {
                    CrbContractReimburseBill info = crbContractReimburseBillService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getTotalSum());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //差旅申请
                if ("TAS_TRAVLE_APPLY_INFO".equals(documentType)) {
                    TasTravelApplyInfo info = tasTravleApplyInfoService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getTotalSum());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //差旅报账
                if ("TAS_TRAVEL_REIMBURSE".equals(documentType)) {
                    TasTravelReimburse info = tasTravelReimburseService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getExpenseAmount());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //教育经费细化申请单
                if ("EDU_FUNDS_APPLY_HEAD".equals(documentType)) {
                    FundsApplyHead info = fundsApplyHeadService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getTotalAmount());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //收款单
                if ("REP_RECIEVE_PAYMENT".equals(documentType)) {
                    RecievePayment info = recievePaymentService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getTotalAmount());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //公用经费预算调整
                if ("BUDGET_PUBLIC_ADJUST".equals(documentType)) {
                    BudgetPublicAdjust info = budgetPublicAdjustService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getAnnualBudgetAmount());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //还款单
                if ("POI_PEPAYMENT_ORDER_INFO".equals(documentType)) {
                    PepaymentOrderInfo info = pepaymentOrderInfoService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getReAmountTatol());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //手工录入凭证
                if ("AV_MANUAL_VOUCHER_HEAD".equals(documentType)) {
                    AvManualVoucherHead info = manualVoucherHeadService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getTotalOriginalAmount());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //款项确认
                if ("PPC_PROJECT_CONFIRMATION".equals(documentType)) {
                    ProjectConfirmation info = projectConfirmationService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getTotalAmount());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //收入上缴
                if ("ITO_INCOME_TURNED_OVER".equals(documentType)) {
                    IncomeTurnedOver info = turnedOverService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getTotalSum());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //借款
                if ("BM_BORROW_MONEY_INFO".equals(documentType)) {
                    BorrowMoneyInfo info = borrowMoneyInfoService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getBorrowAmount());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }
                //对公预付款
                if ("ADP_ADVANCE_PAYMENT_INFO".equals(documentType)) {
                    AdvancePaymentInfo info = bmAdvancePaymentInfoService.getById(documentId);
                    if (info != null) {
                        vo.setTotalAmount(info.getTotalSum());
                        vo.setDocumentStatus(info.getDocumentStatus());
                    }
                }


            }
        }


    }

}



