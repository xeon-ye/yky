package com.deloitte.services.fssc.business.bpm.biz.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.bpmservice.feign.BpmApprovalMatrixFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.*;
import com.deloitte.platform.api.fssc.bpm.param.FsscBpmParam;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessPassOrRejectForm;
import com.deloitte.platform.api.fssc.bpm.vo.ProcessStartForm;
import com.deloitte.platform.api.fssc.bpm.vo.ReSubmitProcessForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentBusinessLineVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.ExceptionUtil;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubType;
import com.deloitte.services.fssc.base.service.IBaseBudgetAccountService;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeService;
import com.deloitte.services.fssc.base.service.IBaseExpenseMainTypeService;
import com.deloitte.services.fssc.base.service.IBaseIncomeSubTypeService;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.mq.MqConfig;
import com.deloitte.services.fssc.budget.service.IBudgetAdjustService;
import com.deloitte.services.fssc.budget.service.IBudgetControlService;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentInfo;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentInfoService;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractInfoService;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;
import com.deloitte.services.fssc.business.borrow.service.IBmBorrowBankService;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyInfoService;
import com.deloitte.services.fssc.business.bpm.biz.BpmProcessBiz;
import com.deloitte.services.fssc.business.bpm.biz.ProcessPersonBiz;
import com.deloitte.services.fssc.business.bpm.entity.BaseBpmRejectReason;
import com.deloitte.services.fssc.business.bpm.entity.Process;
import com.deloitte.services.fssc.business.bpm.entity.ProcessDef;
import com.deloitte.services.fssc.business.bpm.mapper.ProcessMapper;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmRejectReasonService;
import com.deloitte.services.fssc.business.bpm.service.IProcessDefService;
import com.deloitte.services.fssc.business.bpm.service.IProcessService;
import com.deloitte.services.fssc.business.bpm.vo.SendToOaVo;
import com.deloitte.services.fssc.business.carryforward.entity.DssScientificPay;
import com.deloitte.services.fssc.business.carryforward.entity.IncomeOfCarryForward;
import com.deloitte.services.fssc.business.carryforward.service.IDssScientificPayService;
import com.deloitte.services.fssc.business.carryforward.service.IIncomeOfCarryForwardService;
import com.deloitte.services.fssc.business.contract.entity.CrbContractReimburseBill;
import com.deloitte.services.fssc.business.contract.service.ICrbContractReimburseBillService;
import com.deloitte.services.fssc.business.edu.service.IFundsApplyHeadService;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;
import com.deloitte.services.fssc.business.general.service.IGeExpenseBorrowPrepayService;
import com.deloitte.services.fssc.business.general.service.IGeExpensePaymentListService;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseService;
import com.deloitte.services.fssc.business.ito.service.IDetailsOfFundsService;
import com.deloitte.services.fssc.business.labor.entity.GePrivatePaymentList;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;
import com.deloitte.services.fssc.business.labor.service.IGePrivatePaymentListService;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostService;
import com.deloitte.services.fssc.business.pay.entity.PyPamentBusinessLine;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import com.deloitte.services.fssc.business.pay.service.IPyPamentBusinessLineService;
import com.deloitte.services.fssc.business.pay.service.IPyPamentOrderInfoService;
import com.deloitte.services.fssc.business.poi.entity.PepaymentOrderInfo;
import com.deloitte.services.fssc.business.poi.service.IPepaymentOrderInfoService;
import com.deloitte.services.fssc.business.rep.service.IRecievePaymentService;
import com.deloitte.services.fssc.business.travle.entity.TasTravelReimburse;
import com.deloitte.services.fssc.business.travle.service.ITasTravelReimburseService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.dockingEbs.service.IAccountVoucherToEbsService;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherHead;
import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherHeadService;
import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherLineService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.BigDecimalUtil;
import com.deloitte.services.fssc.util.StringUtil;
import feign.QueryMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BpmProcessBizImpl implements BpmProcessBiz {

    @Autowired
    private IProcessDefService processDefService;
    @Autowired
    public IRecievePaymentService recievePaymentService;
    @Autowired
    private IProcessService processService;

    @Autowired
    private ProcessPersonBiz processPersonBiz;
    @Autowired
    private BpmOperatorFeignService bpmOperatorFeignService;

    @Autowired
    private BpmProcessTaskFeignService taskFeignService;

    @Autowired
    private BpmApprovalMatrixFeignService matrixFeignService;

    @Autowired
    private IBudgetControlService budgetControlService;

    @Autowired
    private IBudgetAdjustService budgetAdjustService;

    @Autowired
    private IBaseDocumentTypeService documentTypeService;

    @Autowired
    private IBaseContractInfoService contractInfoService;
    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private IGeExpenseBorrowPrepayService prepayService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private IBaseBpmRejectReasonService rejectReasonService;
    @Autowired
    private IAccountVoucherToEbsService accountVoucherToEbsService;

    @Autowired
    private IFundsApplyHeadService fundsApplyHeadService;

    @Autowired
    public IBankUnitInfoService bankUnitInfoService;


    @Autowired
    public IPyPamentOrderInfoService pyPamentOrderInfoService;
    @Autowired
    public IPyPamentBusinessLineService pyPamentBusinessLineService;

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
    @Autowired
    private IDetailsOfFundsService fundsService;

    @Autowired
    private  IBaseIncomeSubTypeService baseIncomeSubTypeService;

    @Autowired
    IGeExpensePaymentListService geExpensePaymentListService;//对公付款
    @Autowired
    private IBmBorrowBankService bmBorrowBankService;//对私，公务卡
    @Autowired
    IGePrivatePaymentListService gePrivatePaymentListService;//劳务报账对私

    @Autowired
    private IAvManualVoucherLineService avManualVoucherLineService;//凭证行
    @Autowired
    private IIncomeOfCarryForwardService incomeOfCarryForwardService;//收入结转
    @Autowired
    private IAvManualVoucherHeadService  avManualVoucherHeadService;//凭证头
    @Autowired
    private IBudgetProjectService budgetProjectService;
    @Autowired
    private IBaseBudgetAccountService baseBudgetAccountService;
    @Autowired
    private IBaseExpenseMainTypeService baseExpenseMainTypeService;
    @Autowired
    private IDssScientificPayService dssScientificPayService;
    @Autowired
    public IPepaymentOrderInfoService pepaymentOrderInfoService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    //重新提交方式
    private static Map<String, String> reSubmitTypes = new HashMap<>();

    //冲销时需要修改金额的表
    private static List<String> hasModifyAmountTables = new ArrayList<>();

    //审批地址集合
    public static Map<String, String> processUrls = new HashMap<>();

    public static String localUrl = "http://124.17.100.183:30080/fssc/index.html#/";

    static {
        reSubmitTypes.put("RESUBMIT_ROLLBACK", "待重新提交");
        reSubmitTypes.put("RESUBMIT_REJECT", "待重新提交");

        //通用报账
        hasModifyAmountTables.add(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        //差旅报账
        hasModifyAmountTables.add(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
        //合同报账
        hasModifyAmountTables.add(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        //还款单
        hasModifyAmountTables.add(FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());

        //借款
        processUrls.put(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue(), localUrl + "borrowApproval");
        //对公预付款
        processUrls.put(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue(), localUrl + "advanceApprove");
        //通用报账
        processUrls.put(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue(), localUrl + "commonApproval");
        //劳务费
        processUrls.put(FsscTableNameEums.LC_LABOR_COST.getValue(), localUrl + "scAddQueryAudit");
        //收款单
        processUrls.put(FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue(), localUrl + "collectionApprove");
        //差旅申请
        processUrls.put(FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue(), localUrl + "travelApply");
        //差旅报账
        processUrls.put(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue(), localUrl + "travelReimbur");
        //款项确认
        processUrls.put(FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue(), localUrl + "paymentConfirmApprove");
        //付款单
        processUrls.put(FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue(), localUrl + "paymentOrderDetail");
        //合同报账
        processUrls.put(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue(), localUrl + "contractApprove");
        //教育华申请经费
        processUrls.put(FsscTableNameEums.EDU_FUNDS_APPLY_HEAD.getValue(), localUrl + "educationDetail");
    }

    private static final String APPROVE_END = "审批结束";


    public void readMessage(Long documentId) {
        log.info("阅读结果SUCCESS");
    }




    private FsscBpmParam findStartFsscBpmParam(Long documentId, String documentType,
                                               String processType, Map<String,String> processVariables,
                                               String documentNum){
        FsscBpmParam p=new FsscBpmParam();
        p.setDocumentNum(documentNum);
        p.setDocumentId(documentId);
        p.setDocumentType(documentType);
        p.setProcessVariables(processVariables);
        ProcessDef processDef = findProcessDef(documentType, processType);
        p.setProcessDefKey(processDef.getProcessKey());
        p.setLocalProcessDefId(processDef.getId());
        Process process = getLocalProcess(documentId);
        if(process!=null){
            p.setProcessInstanceId(process.getProcessId());
        }
        return p;
    }

    private FsscBpmParam findOtherFsscBpmParam(Long documentId, String documentType,
                                               Map<String,String> processVariables,
                                               String documentNum){
        FsscBpmParam p=new FsscBpmParam();
        Process process = getLocalProcess(documentId);
        AssertUtils.asserts(process!=null,FsscErrorType.THIS_PROCESS_IS_NOT_FIND);
        p.setProcessInstanceId(process.getProcessId());
        p.setDocumentNum(documentNum);
        p.setDocumentId(documentId);
        p.setDocumentType(documentType);
        p.setProcessVariables(processVariables);
        ProcessDef def = processDefService.getById(process.getProcessDefId());
        p.setProcessDefKey(def.getProcessKey());
        p.setLocalProcessDefId(def.getId());
        return p;
    }




    /**
     * 启动流程(未提交过流程)
     *
     * @param form
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void startProcess(ProcessStartForm form) {
        log.info("流程开始FORM:{}", JSON.toJSONString(form));
        Long documentId = form.getDocumentId();
        String documentType = form.getDocumentType();
        BaseDocumentType baseDocumentType = this.getBaseDocumentType(documentType);
        //提交审批成功后,执行预算额度检查和更新预算
        boolean notWarningFlag = FsscEums.NO.getValue().equals(form.getBudgetWarningCheck());
        budgetControlService.executeBudgetRemain(documentId, documentType, !notWarningFlag, true);
        budgetAdjustService.preAdjust(documentId, documentType);
        if (!FsscEums.YES.getValue().equals(baseDocumentType.getAuditFlag())) {
            processMapper.updateDocumentStatus(documentId, documentType, FsscEums.APPROVED.getValue());
            //不需要审批时,直接执行保留变占用逻辑,更新单据状态为已审批
            budgetControlService.executeBudgetOccupy(documentId, documentType);
            budgetAdjustService.toAdjust(documentId, documentType);
            return;
        }
        String processType = form.getProcessType();
        checkBasicParam(form);
        //获取流程定义
        FsscBpmParam fsscBpmParam =
                findStartFsscBpmParam(documentId, documentType, processType,form.getProcessVariables(),form.getDocumentNum());
        AssertUtils.asserts(StringUtil.isEmpty(fsscBpmParam.getProcessInstanceId()),
                FsscErrorType.DOCUMENT_IS_ALREADLY_SUBMIT);
        //构造参数
        BpmProcessOperatorFormStart formStart = buildStartBpmForm(fsscBpmParam);
        formStart.setProcessVariables(form.getProcessVariables());
        log.info("流程启动参数:{}", JSON.toJSONString(formStart));
        Result result = bpmOperatorFeignService.startProcess(formStart);
        AssertUtils.asserts(result.isSuccess(), FsscErrorType.PROCESS_START_FAIL);
        Object data = result.getData();
        String processInstanceId = StringUtil.objectToString(data);
        if (FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue().equals(documentType)) {
            List<Map<String, String>> copyEmpNos = form.getCopyEmpNos();
            if (CollectionUtils.isNotEmpty(copyEmpNos)) {
                SendToOaVo oaVo = new SendToOaVo();
                oaVo.setCopyEmpNos(copyEmpNos);
                oaVo.setDocumentId(documentId);
                oaVo.setDocumentNum(form.getDocumentNum());
                oaVo.setProcessDefKey(fsscBpmParam.getProcessDefKey());
                oaVo.setProcessInstanceId(processInstanceId);
                oaVo.setDocumentType(documentType);
                MessageBuilder messageBuilder = MessageBuilder.withBody(JSON.toJSONBytes(oaVo));
                amqpTemplate.convertAndSend(MqConfig.fsscTopicEx, MqConfig.sendToOa, messageBuilder.build());
            }

        }
        Process process = new Process();
        process.setDocumentId(documentId);
        process.setDocumentType(documentType);
        process.setDocumentNum(form.getDocumentNum());
        process.setProcessId(processInstanceId);
        process.setProcessDefId(fsscBpmParam.getLocalProcessDefId());
        process.setProcessStatus(FsscEums.SUBMIT.getValue());
        processService.saveOrUpdate(process);
        processMapper.updateDocumentStatus(documentId, documentType, FsscEums.SUBMIT.getValue());
    }


    private Process getLocalProcess(Long documentId) {
        QueryWrapper<Process> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Process.DOCUMENT_ID, documentId);
        return processService.getOne(queryWrapper);
    }


    /**
     * 审批通过
     *
     * @param form
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void passProcess(ProcessPassOrRejectForm form) {
        checkBasicParam(form);
        FsscBpmParam fsscBpmParam = findOtherFsscBpmParam(form.getDocumentId(),
                form.getDocumentType(),form.getProcessVariables(),form.getDocumentNum());
        BpmProcessOperatorApprove bpmProcessParamForm = buildBpmProcessOperatorApproveParam(form);
        fsscBpmParam.setTaskId(StringUtil.objectToString(form.getId()));
        ArrayList<NextNodeParamVo> nextAuditPerson = processPersonBiz.findNextAuditPerson(fsscBpmParam);
        addProcessUrl(nextAuditPerson, form.getDocumentId(), form.getDocumentType());
        bpmProcessParamForm.setNextNodeParamVo(nextAuditPerson);
        bpmProcessParamForm.setProcessVariables(form.getProcessVariables());
        log.info("审批通过流程参数:{}", JSON.toJSONString(bpmProcessParamForm));
        doProcess(form.getDocumentId(),fsscBpmParam.getLocalProcessDefId(),
                bpmProcessParamForm, form.getDocumentType(), null);
    }

    /**
     * 重新提交
     *
     * @param form
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void reSubmit(ReSubmitProcessForm form) {
        checkBasicParam(form);

        //重新提交审批 执行预算额度检查和更新预算
        boolean notWarningFlag = FsscEums.NO.getValue().equals(form.getBudgetWarningCheck());
        budgetControlService.executeBudgetRemain(form.getDocumentId(), form.getDocumentType(), !notWarningFlag, true);
        budgetAdjustService.preAdjust(form.getDocumentId(), form.getDocumentType());
        FsscBpmParam fsscBpmParam = findStartFsscBpmParam(form.getDocumentId(), form.getDocumentType(),
                form.getProcessType(),form.getProcessVariables(),form.getDocumentNum());

        AssertUtils.asserts(StringUtil.isNotEmpty(fsscBpmParam.getProcessInstanceId()),
                FsscErrorType.THIS_PROCESS_IS_NOT_FIND);


        if (FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue().equals(form.getDocumentType())) {
            List<Map<String, String>> copyEmpNos = form.getCopyEmpNos();
            if (CollectionUtils.isNotEmpty(copyEmpNos)) {
                SendToOaVo oaVo = new SendToOaVo();
                oaVo.setCopyEmpNos(copyEmpNos);
                oaVo.setDocumentId(form.getDocumentId());
                oaVo.setDocumentNum(form.getDocumentNum());
                oaVo.setProcessDefKey(fsscBpmParam.getProcessDefKey());
                oaVo.setProcessInstanceId(fsscBpmParam.getProcessInstanceId());
                oaVo.setDocumentType(form.getDocumentType());
                MessageBuilder messageBuilder = MessageBuilder.withBody(JSON.toJSONBytes(oaVo));
                amqpTemplate.convertAndSend(MqConfig.fsscTopicEx, MqConfig.sendToOa, messageBuilder.build());
            }

        }
        BpmProcessTaskVo taskVo = findTaskVo(fsscBpmParam.getProcessInstanceId(), reSubmitTypes.get(form.getReSubmitType()));
        //判断提交类型 如果是驳回提交 并且 驳回原因是附件错误的
        if("RESUBMIT_REJECT".equals(form.getReSubmitType())){
            QueryWrapper<BaseBpmRejectReason> rejectReasonQueryWrapper=new QueryWrapper<>();
            rejectReasonQueryWrapper.eq(BaseBpmRejectReason.DOCUMENT_ID,form.getDocumentId());
            BaseBpmRejectReason one = rejectReasonService.getOne(rejectReasonQueryWrapper);
            if(one!=null&&"ATTACHMENT_ERROR".equals(one.getRejectReason())){
                //执行转办流程
                BpmProcessOperatorApprove turn=new BpmProcessOperatorApprove();
                ArrayList<NextNodeParamVo> nextNodeParamVo=new ArrayList<>();
                NextNodeParamVo vo=new NextNodeParamVo();
                vo.setAcountId(one.getRejectUserId());
                vo.setAcountName(one.getRejectUserName());
                vo.setStationId(one.getRejectStationId());
                nextNodeParamVo.add(vo);
                addProcessUrl(nextNodeParamVo, form.getDocumentId(), form.getDocumentType());
                turn.setNextNodeParamVo(nextNodeParamVo);
                turn.setProcessVariables(form.getProcessVariables());
                BpmProcessTaskFormApprove bpmProcessTaskForm=new BpmProcessTaskFormApprove();
                bpmProcessTaskForm.setObjectDescription(taskVo.getObjectDescription());
                bpmProcessTaskForm.setId(StringUtil.castTolong(taskVo.getId()));
                bpmProcessTaskForm.setOpinion(form.getOpinion());
                bpmProcessTaskForm.setDestTaskKey(one.getRejectTaskKey());
                bpmProcessTaskForm.setEmergency("0");
                turn.setBpmProcessTaskForm(bpmProcessTaskForm);
                log.info("流程转办参数:{}",JSON.toJSONString(turn));
                Result result = bpmOperatorFeignService.approveProcessToNode(turn);
                AssertUtils.asserts(result.isSuccess(), FsscErrorType.APPROVE_PASS_FIALD);
                afterPass(form.getDocumentId(),form.getDocumentType(),StringUtil.objectToString(result.getData()),form.getReSubmitType()
                        ,fsscBpmParam.getLocalProcessDefId());
                return;
            }
        }

        BpmProcessOperatorApprove bpmProcessParamForm = new BpmProcessOperatorApprove();
        //ArrayList<NextNodeParamVo> paramVoArrayList = getNextNode(fsscBpmParam.getProcessDefKey(), "start");
        fsscBpmParam.setTaskId("start");
        ArrayList<NextNodeParamVo> nextAuditPerson = processPersonBiz.findNextAuditPerson(fsscBpmParam);
        addProcessUrl(nextAuditPerson, form.getDocumentId(), form.getDocumentType());
        bpmProcessParamForm.setNextNodeParamVo(nextAuditPerson);

        BpmProcessTaskFormApprove formApprove = new BpmProcessTaskFormApprove();

        formApprove.setId(StringUtil.castTolong(taskVo.getId()));
        formApprove.setOpinion(form.getOpinion());
        formApprove.setObjectDescription(taskVo.getObjectDescription());


        bpmProcessParamForm.setBpmProcessTaskForm(formApprove);
        bpmProcessParamForm.setProcessVariables(form.getProcessVariables());
        log.info("重新提交流程参数:{}", JSON.toJSONString(bpmProcessParamForm));
        doProcess(form.getDocumentId() ,fsscBpmParam.getLocalProcessDefId(),
                bpmProcessParamForm, form.getDocumentType(), form.getReSubmitType());

    }


    /**
     * 调用审批
     *
     * @param documentId
     * @param
     * @param bpmProcessParamForm
     * @param documentType
     */
    private void doProcess(Long documentId,Long prodefId,
                           BpmProcessOperatorApprove bpmProcessParamForm,
                           String documentType, String submitType) {
        log.info("审批参数:{}", JSON.toJSONString(bpmProcessParamForm));
        Result result = bpmOperatorFeignService.approveProcess(bpmProcessParamForm);
        AssertUtils.asserts(result.isSuccess(), FsscErrorType.APPROVE_PASS_FIALD);

        afterPass(documentId,documentType,StringUtil.objectToString(result.getData()),submitType,prodefId);

    }

    private void afterPass(Long documentId,String documentType,String msg,
                           String submitType,Long prodefId){

        QueryWrapper<Process> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Process.DOCUMENT_ID, documentId);
        Process one = processService.getOne(queryWrapper);
        if (APPROVE_END.equals(msg)) {//msg
            one.setProcessStatus(FsscEums.APPROVED.getValue());
            processMapper.updateDocumentStatus(documentId, documentType, FsscEums.APPROVED.getValue());
            try {
                //审批通过,正常情况是执行保留变占用,如果单据不需要预算占用,预算将不发生变化
                budgetControlService.executeBudgetOccupy(documentId, documentType);
                budgetAdjustService.toAdjust(documentId, documentType);
                //审批通过后付款单主表子表一系列变化
                if (FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue().equals(documentType)) {
                    paymentOrOverDocumentChange(documentType, documentId);
                }
                contractInfoService.sendPlanToContract(documentId, documentType);
                //通用报账 合同报账 审核完成如果 借款单 预付款单 未核销金额为0的时候修改支付状态为已支付
                if (FsscTableNameEums.GE_GENERAL_EXPENSE.getValue().equals(documentType)
                        || FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue().equals(documentType)
                        || FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue().equals(documentType)) {
                    prepayService.modifyPayStatus(documentId, documentType);
                }


            } catch (Exception e) {
                log.error("流程审批通过,但是后续执行方法报错!", ExceptionUtil.getStackTraceAsString(e));
            }

        } else {
            if (StringUtil.isNotEmpty(submitType)) {
                one.setProcessStatus(FsscEums.SUBMIT.getValue());
                processMapper.updateDocumentStatus(documentId, documentType, FsscEums.SUBMIT.getValue());
            } else {
                one.setProcessStatus(FsscEums.APPROVALING.getValue());
                processMapper.updateDocumentStatus(documentId, documentType, FsscEums.APPROVALING.getValue());
            }

        }
        one.setProcessDefId(prodefId);
        processService.updateById(one);


    }

    /**
     * 冲销后对付款单关联的单据付款状态进行回写（NO_PAY）
     * @param documentType
     * @param documentId
     */
    public void DocumentPaymentStatusChange(String documentType, Long documentId){
        PyPamentOrderInfo pyPamentOrderInfo = pyPamentOrderInfoService.getById(documentId);
        if (pyPamentOrderInfo != null) {
            Long id = pyPamentOrderInfo.getId();
            String payDocumentNum = pyPamentOrderInfo.getPayDocumentNum();//待支付编号
            if (id != null) {
                QueryWrapper<PyPamentBusinessLine> pyWrapper = new QueryWrapper<>();
                pyWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
                List<PyPamentBusinessLine> line = pyPamentBusinessLineService.list(pyWrapper);
                pyPamentOrderInfo.setPayStatus("NO_PAY");
                pyPamentOrderInfoService.updateById(pyPamentOrderInfo);
                if (CollectionUtils.isNotEmpty(line)) {
                    for (PyPamentBusinessLine py : line) {
                        py.setPayStatus("NO_PAY");
                    }
                    pyPamentBusinessLineService.updateBatchById(line);
                }
                List<PyPamentBusinessLineVo> lineVos =
                        new BeanUtils<PyPamentBusinessLineVo>().copyObjs(line, PyPamentBusinessLineVo.class);
                if (CollectionUtils.isNotEmpty(lineVos)) {
                    for (PyPamentBusinessLineVo vo : lineVos) {
                        String connectDocumentNum = vo.getConnectDocumentNum();
                        if (StringUtil.isNotEmpty(payDocumentNum)) {
                            AssertUtils.asserts(payDocumentNum.equalsIgnoreCase(connectDocumentNum), FsscErrorType.DOCUENT_NUM_NOT_EQ);
                        }
                        if (StringUtil.isNotEmpty(connectDocumentNum)) {
                            String simpleDoc = connectDocumentNum.substring(3, 5);
                            if ("TY".equals(simpleDoc)) {
                                QueryWrapper<GeGeneralExpense> geQueryWrapper = new QueryWrapper<>();
                                geQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                                GeGeneralExpense generalExpense = geGeneralExpenseService.getOne(geQueryWrapper);
                                if (generalExpense != null) {
                                    generalExpense.setPayStatus(vo.getPayStatus());
                                    generalExpense.setPayMentId(null);
                                    generalExpense.setIsGeneratePayOrder("N");
                                    BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(generalExpense.getNoPaidAmount());
                                    BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(generalExpense.getPaidAmount());
                                    BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                                    BigDecimal yizhifu = generalExpensePaidAmount.subtract(payAmount);
                                    generalExpense.setPaidAmount(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.add(yizhifu);
                                    generalExpense.setNoPaidAmount(weizhifu);
                                    geGeneralExpenseService.saveOrUpdate(generalExpense);
                                }
                            }
                            if ("CL".equals(simpleDoc)) {//差旅888CL20190400141
                                QueryWrapper<TasTravelReimburse> traveQueryWrapper = new QueryWrapper<>();
                                traveQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                                TasTravelReimburse tasTravelReimburse = tasTravelReimburseService.getOne(traveQueryWrapper);
                                if (tasTravelReimburse != null) {
                                    tasTravelReimburse.setPayStatus(vo.getPayStatus());
                                    tasTravelReimburse.setPayMentId(null);
                                    tasTravelReimburse.setIsGeneratePayOrder("N");
                                    BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(tasTravelReimburse.getNoPaidAmount());
                                    BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(tasTravelReimburse.getPaidAmount());
                                    BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                                    BigDecimal yizhifu = generalExpensePaidAmount.subtract(payAmount);
                                    tasTravelReimburse.setPaidAmount(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.add(yizhifu);
                                    tasTravelReimburse.setNoPaidAmount(weizhifu);
                                    tasTravelReimburseService.updateById(tasTravelReimburse);
                                }
                            }

                            if ("YF".equals(simpleDoc)) {//对公预付款888YF20190400261
                                QueryWrapper<AdvancePaymentInfo> adQueryWrapper = new QueryWrapper<>();
                                adQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                                AdvancePaymentInfo advancePaymentInfo = bmAdvancePaymentInfoService.getOne(adQueryWrapper);
                                if (advancePaymentInfo != null) {
                                    advancePaymentInfo.setPayStatus(vo.getPayStatus());
                                    advancePaymentInfo.setPayMentId(null);
                                    advancePaymentInfo.setIsGeneratePayOrder("N");
                                    BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(advancePaymentInfo.getUnpaidAmount());
                                    BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(advancePaymentInfo.getAmountPaid());
                                    BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                                    BigDecimal yizhifu = generalExpensePaidAmount.subtract(payAmount);
                                    advancePaymentInfo.setAmountPaid(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.add(yizhifu);
                                    advancePaymentInfo.setUnpaidAmount(weizhifu);
                                    bmAdvancePaymentInfoService.updateById(advancePaymentInfo);
                                }
                            }

                            if ("JK".equals(simpleDoc)) {//个人借款888JK20190400145
                                QueryWrapper<BorrowMoneyInfo> brQueryWrapper = new QueryWrapper<>();
                                brQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                                BorrowMoneyInfo borrowMoneyInfo = borrowMoneyInfoService.getOne(brQueryWrapper);
                                if (borrowMoneyInfo != null) {
                                    borrowMoneyInfo.setIsGeneratePayOrder("N");
                                    borrowMoneyInfo.setPayMentId(null);
                                    borrowMoneyInfo.setPayStatus(vo.getPayStatus());
                                    BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(borrowMoneyInfo.getNoPaidAmount());
                                    BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(borrowMoneyInfo.getPaidAmount());
                                    BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                                    BigDecimal yizhifu = generalExpensePaidAmount.subtract(payAmount);
                                    borrowMoneyInfo.setPaidAmount(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.add(yizhifu);
                                    borrowMoneyInfo.setNoPaidAmount(weizhifu);
                                    borrowMoneyInfoService.updateById(borrowMoneyInfo);
                                }
                            }

                            if ("HT".equals(simpleDoc)) { //合同报账888HT20190400081
                                QueryWrapper<CrbContractReimburseBill> crbQueryWrapper = new QueryWrapper<>();
                                crbQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                                CrbContractReimburseBill crbContractReimburseBill = crbContractReimburseBillService.getOne(crbQueryWrapper);
                                if (crbContractReimburseBill != null) {
                                    crbContractReimburseBill.setIsGeneratePayOrder("N");
                                    crbContractReimburseBill.setPayMentId(null);
                                    crbContractReimburseBill.setPayStatus(vo.getPayStatus());
                                    BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(crbContractReimburseBill.getUnpaidAmount());
                                    BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(crbContractReimburseBill.getAmountPaid());
                                    BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                                    BigDecimal yizhifu = generalExpensePaidAmount.subtract(payAmount);
                                    crbContractReimburseBill.setAmountPaid(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.add(yizhifu);
                                    crbContractReimburseBill.setUnpaidAmount(weizhifu);
                                    crbContractReimburseBillService.updateById(crbContractReimburseBill);
                                }
                            }
                            if ("LW".equals(simpleDoc)) {  //LW
                                QueryWrapper<LcLaborCost> geQueryWrapper = new QueryWrapper<>();
                                geQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                                LcLaborCost LcLaborCost = lcLaborCostService.getOne(geQueryWrapper);
                                if (LcLaborCost != null) {
                                    LcLaborCost.setPayStatus(vo.getPayStatus());
                                    LcLaborCost.setPayMentId(null);
                                    LcLaborCost.setIsGeneratePayOrder("N");
                                    BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(LcLaborCost.getNoPaidAmount());
                                    BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(LcLaborCost.getPaidAmount());
                                    BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                                    BigDecimal yizhifu = generalExpensePaidAmount.subtract(payAmount);
                                    LcLaborCost.setPaidAmount(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.add(yizhifu);
                                    LcLaborCost.setNoPaidAmount(weizhifu);
                                    lcLaborCostService.saveOrUpdate(LcLaborCost);
                                }
                            }
                        }
                    }

                }

                if (CollectionUtils.isNotEmpty(lineVos)) {
                    for (PyPamentBusinessLineVo pyPamentBusinessLineVo : lineVos) {
                        String lineType = pyPamentBusinessLineVo.getLineType();
                        String connectDocumentType = pyPamentBusinessLineVo.getConnectDocumentType();//关联的单据类型
                        if ("BUSINESS_CARD".equals(lineType)) {
                            Long connectDoucmentIdLine = pyPamentBusinessLineVo.getConnectDocumentIdLine();//获取关联的行ID；
                            // bmBorrowBankService
                            BmBorrowBank bmBorrowBank = bmBorrowBankService.getById(connectDoucmentIdLine);
                            bmBorrowBank.setEx1("N");//此目的在于判断该行表信息是否被关联过；
                            bmBorrowBank.setPayStatus(pyPamentBusinessLineVo.getPayStatus());
                            bmBorrowBankService.updateById(bmBorrowBank);
                        } else if ("PRIVATE".equals(lineType)) {
                            Long connectDoucmentIdLine = pyPamentBusinessLineVo.getConnectDocumentIdLine();//获取关联的行ID；
                            if (connectDocumentType.equals(FsscTableNameEums.LC_LABOR_COST.getValue())) {
                                //gePrivatePaymentListService
                                GePrivatePaymentList gePrivatePaymentList = gePrivatePaymentListService.getById(connectDoucmentIdLine);
                                gePrivatePaymentList.setEx1("N");//此目的在于判断该行表信息是否被关联过；
                                gePrivatePaymentList.setPayStatus(pyPamentBusinessLineVo.getPayStatus());
                                gePrivatePaymentListService.updateById(gePrivatePaymentList);
                            } else {
                                // bmBorrowBankService
                                BmBorrowBank bmBorrowBank = bmBorrowBankService.getById(connectDoucmentIdLine);
                                bmBorrowBank.setEx1("N");//此目的在于判断该行表信息是否被关联过；
                                bmBorrowBank.setPayStatus(pyPamentBusinessLineVo.getPayStatus());
                                bmBorrowBankService.updateById(bmBorrowBank);
                            }
                        } else if ("PUBLIC".equals(lineType)) {
                            Long connectDoucmentIdLine = pyPamentBusinessLineVo.getConnectDocumentIdLine();//获取关联的行ID；
                            // geExpensePaymentListService
                            GeExpensePaymentList geExpensePaymentList = geExpensePaymentListService.getById(connectDoucmentIdLine);
                            geExpensePaymentList.setEx1("N");//此目的在于判断该行表信息是否被关联过；
                            geExpensePaymentList.setPayStatus(pyPamentBusinessLineVo.getPayStatus());
                            geExpensePaymentListService.updateById(geExpensePaymentList);
                        }
                    }
                }
            }
        }
    }

    public void paymentOrOverDocumentChange(String documentType, Long documentId) {
        PyPamentOrderInfo pyPamentOrderInfo = pyPamentOrderInfoService.getById(documentId);
        if (pyPamentOrderInfo != null) {
            Long id = pyPamentOrderInfo.getId();
            String isAfterPatch = pyPamentOrderInfo.getIsAfterPatch();//是否事后补单
            String bankAccount = pyPamentOrderInfo.getBankAccount();
            String payDocumentNum = pyPamentOrderInfo.getPayDocumentNum();//待支付编号
            //PyPamentOrderInfoVo pyPamentOrderInfoVo = new BeanUtils<PyPamentOrderInfoVo>().copyObj(pyPamentOrderInfo, PyPamentOrderInfoVo.class);
            String isBankDrectLink = "N";
            if (StringUtil.isNotEmpty(bankAccount)) {
                QueryWrapper<BankUnitInfo> bankWrapper = new QueryWrapper<>();
                bankWrapper.eq("BANK_ACCOUNT", bankAccount);
                BankUnitInfo bankUnitInfo = bankUnitInfoService.getOne(bankWrapper);
                if (bankUnitInfo != null) {
                    isBankDrectLink = bankUnitInfo.getIsBankDrectLink();//是否银企直联
                }
            }
            if (id != null) {
                QueryWrapper<PyPamentBusinessLine> pyWrapper = new QueryWrapper<>();
                pyWrapper.eq("DOCUMENT_ID", id).eq("DOCUMENT_TYPE", FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue());
                List<PyPamentBusinessLine> line = pyPamentBusinessLineService.list(pyWrapper);
                if ("Y".equals(isAfterPatch) || ("N".equals(isAfterPatch) && "N".equals(isBankDrectLink))) {
                    pyPamentOrderInfo.setPayStatus("PAYED");
                    pyPamentOrderInfo.setPaidAmount(pyPamentOrderInfo.getNoPaidAmount());
                    pyPamentOrderInfo.setNoPaidAmount(BigDecimal.ZERO);
                    pyPamentOrderInfoService.updateById(pyPamentOrderInfo);
                    if (CollectionUtils.isNotEmpty(line)) {
                        Map<String,String> map = new HashMap<>();
                        for (PyPamentBusinessLine py : line) {
                            py.setPayStatus("PAYED");

                            //主要针对的是收入结转6个单据，单据的付款状态是已付款，关联的项目是收入结转方式是PROGRESS_PERCENT
                             if(!map.containsKey(py.getConnectDocumentNum())){
                                 map.put(py.getConnectDocumentNum(),py.getConnectDocumentNum());
                                 //收入结转
                                 carryForward(py);
                                 //项目支出接口
                                 ExpenseCost(py);
                             }

                        }
                        pyPamentBusinessLineService.updateBatchById(line);
                    }

                }
                List<PyPamentBusinessLineVo> lineVos =
                        new BeanUtils<PyPamentBusinessLineVo>().copyObjs(line, PyPamentBusinessLineVo.class);
                if (CollectionUtils.isNotEmpty(lineVos)) {
                    for (PyPamentBusinessLineVo vo : lineVos) {
                        String connectDocumentNum = vo.getConnectDocumentNum();
                        if (StringUtil.isNotEmpty(payDocumentNum)) {
                            AssertUtils.asserts(payDocumentNum.equalsIgnoreCase(connectDocumentNum), FsscErrorType.DOCUENT_NUM_NOT_EQ);
                        }
                        if (StringUtil.isNotEmpty(connectDocumentNum)) {
                            String simpleDoc = connectDocumentNum.substring(3, 5);
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
                                    generalExpense.setPaidAmount(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.subtract(yizhifu);
                                    if (weizhifu.compareTo(BigDecimal.ZERO) <= 0) {
                                        weizhifu = BigDecimal.ZERO;
                                    }
                                    generalExpense.setNoPaidAmount(weizhifu);
                                    geGeneralExpenseService.saveOrUpdate(generalExpense);
                                    updateGePreOrAdv(vo.getConnectDocumentId(),pyPamentOrderInfo.getAccountCode(),"GE_GENERAL_EXPENSE");
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
                                    tasTravelReimburse.setPaidAmount(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.subtract(yizhifu);
                                    if (weizhifu.compareTo(BigDecimal.ZERO) <= 0) {
                                        weizhifu = BigDecimal.ZERO;
                                    }
                                    tasTravelReimburse.setNoPaidAmount(weizhifu);
                                    tasTravelReimburseService.updateById(tasTravelReimburse);
                                    updateGePreOrAdv(vo.getConnectDocumentId(),pyPamentOrderInfo.getAccountCode(),"TAS_TRAVEL_REIMBURSE");
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
                                    advancePaymentInfo.setAmountPaid(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.subtract(yizhifu);
                                    if (weizhifu.compareTo(BigDecimal.ZERO) <= 0) {
                                        weizhifu = BigDecimal.ZERO;
                                    }
                                    advancePaymentInfo.setUnpaidAmount(weizhifu);
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
                                    borrowMoneyInfo.setPaidAmount(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.subtract(yizhifu);
                                    if (weizhifu.compareTo(BigDecimal.ZERO) <= 0) {
                                        weizhifu = BigDecimal.ZERO;
                                    }
                                    borrowMoneyInfo.setNoPaidAmount(weizhifu);
                                    borrowMoneyInfoService.updateById(borrowMoneyInfo);
                                }
                            }

                            if ("HT".equals(simpleDoc)) { //合同报账888HT20190400081
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
                                    crbContractReimburseBill.setAmountPaid(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.subtract(yizhifu);
                                    if (weizhifu.compareTo(BigDecimal.ZERO) <= 0) {
                                        weizhifu = BigDecimal.ZERO;
                                    }
                                    crbContractReimburseBill.setUnpaidAmount(weizhifu);
                                    crbContractReimburseBillService.updateById(crbContractReimburseBill);
                                    updateGePreOrAdv(vo.getConnectDocumentId(),pyPamentOrderInfo.getAccountCode(),"CRB_CONTRACT_REIMBURSE_BILL");
                                }
                            }
                            if ("LW".equals(simpleDoc)) {  //LW
                                QueryWrapper<LcLaborCost> geQueryWrapper = new QueryWrapper<>();
                                geQueryWrapper.eq("DOCUMENT_NUM", connectDocumentNum);
                                LcLaborCost LcLaborCost = lcLaborCostService.getOne(geQueryWrapper);
                                if (LcLaborCost != null) {
                                    LcLaborCost.setIsGeneratePayOrder("Y");
                                    LcLaborCost.setPayMentId(id);
                                    LcLaborCost.setPayStatus(vo.getPayStatus());
                                    BigDecimal generalExpenseNoPaidAmount = BigDecimalUtil.convert(LcLaborCost.getNoPaidAmount());
                                    BigDecimal generalExpensePaidAmount = BigDecimalUtil.convert(LcLaborCost.getPaidAmount());
                                    BigDecimal payAmount = BigDecimalUtil.convert(vo.getPayAmount());
                                    BigDecimal yizhifu = generalExpensePaidAmount.add(payAmount);
                                    LcLaborCost.setPaidAmount(yizhifu);
                                    BigDecimal weizhifu = generalExpenseNoPaidAmount.subtract(yizhifu);
                                    if (weizhifu.compareTo(BigDecimal.ZERO) <= 0) {
                                        weizhifu = BigDecimal.ZERO;
                                    }
                                    LcLaborCost.setNoPaidAmount(weizhifu);
                                    lcLaborCostService.saveOrUpdate(LcLaborCost);
                                }
                            }
                        }
                    }

                }

               /*  QueryWrapper<PyPamentBusinessLine> businessWrapper = new QueryWrapper<>();
                 businessWrapper.eq("DOCUMENT_ID", id).
                         eq("DOCUMENT_TYPE", FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue()).eq("LINE_TYPE", FsscTableNameEums.BUSINESS_CARD.getValue());
                 List<PyPamentBusinessLine> pyPamentBusinessLines = pyPamentBusinessLineService.list(businessWrapper);
                */
                //公务卡,对私,对公集合
                //List<PyPamentBusinessLineVo> pyPamentBusinessLineVos = new BeanUtils<PyPamentBusinessLineVo>().copyObjs(line, PyPamentBusinessLineVo.class);
                if (CollectionUtils.isNotEmpty(lineVos)) {
                    for (PyPamentBusinessLineVo pyPamentBusinessLineVo : lineVos) {
                        String lineType = pyPamentBusinessLineVo.getLineType();
                        String connectDocumentType = pyPamentBusinessLineVo.getConnectDocumentType();//关联的单据类型
                        if ("BUSINESS_CARD".equals(lineType)) {
                            Long connectDoucmentIdLine = pyPamentBusinessLineVo.getConnectDocumentIdLine();//获取关联的行ID；
                            // bmBorrowBankService
                            BmBorrowBank bmBorrowBank = bmBorrowBankService.getById(connectDoucmentIdLine);
                            bmBorrowBank.setEx1("Y");//此目的在于判断该行表信息是否被关联过；
                            bmBorrowBank.setPayStatus(pyPamentBusinessLineVo.getPayStatus());
                            bmBorrowBankService.updateById(bmBorrowBank);
                        } else if ("PRIVATE".equals(lineType)) {
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
                        } else if ("PUBLIC".equals(lineType)) {
                            Long connectDoucmentIdLine = pyPamentBusinessLineVo.getConnectDocumentIdLine();//获取关联的行ID；
                            // geExpensePaymentListService
                            GeExpensePaymentList geExpensePaymentList = geExpensePaymentListService.getById(connectDoucmentIdLine);
                            geExpensePaymentList.setEx1("Y");//此目的在于判断该行表信息是否被关联过；
                            geExpensePaymentList.setPayStatus(pyPamentBusinessLineVo.getPayStatus());
                            geExpensePaymentListService.updateById(geExpensePaymentList);
                        }
                    }
                }
            }
        }
    }

    public void updateGePreOrAdv(Long id ,String accountCode,String documentType){
        QueryWrapper<GeExpenseBorrowPrepay> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("DOCUMENT_ID",id).eq("DOCUMENT_TYPE",documentType);
        List<GeExpenseBorrowPrepay> geList= prepayService.list(queryWrapper);
        if(CollectionUtils.isNotEmpty(geList)){
            for(GeExpenseBorrowPrepay ge:geList){
                ge.setPaymentAccountCode(accountCode);
            }
            prepayService.updateBatchById(geList);
        }
    }

    /**
     * 收入结转
     * @param py
     */
    @Override
    public void carryForward(PyPamentBusinessLine py){
        if(py.getConnectDocumentType().equals(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue())||py.getConnectDocumentType().equals(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue())||
                py.getConnectDocumentType().equals(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue())||py.getConnectDocumentType().equals(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue())||
                py.getConnectDocumentType().equals(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue())||py.getConnectDocumentType().equals(FsscTableNameEums.LC_LABOR_COST.getValue())){
            IncomeOfCarryForward forwardEntity  = new IncomeOfCarryForward();
            Map<String,Object> documentEntity = avManualVoucherLineService.selectMapLimitOne(py.getConnectDocumentType(),py.getConnectDocumentId());
            if(documentEntity.size()>0){
                if(documentEntity.containsKey("PROJECT_ID")){
                    QueryWrapper<BudgetProject> bpWrapper = new QueryWrapper<BudgetProject>();
                    bpWrapper.eq(BudgetProject.ID,documentEntity.get("PROJECT_ID"));
                    List<BudgetProject> bpList =   budgetProjectService.list(bpWrapper);
                    if(bpList.size()>0){
                        if(StringUtils.isNotEmpty(bpList.get(0).getPaymentConfirmType())){
                            if(py.getConnectDocumentType().equals(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue())||py.getConnectDocumentType().equals(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue())||
                                    py.getConnectDocumentType().equals(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue())||py.getConnectDocumentType().equals(FsscTableNameEums.LC_LABOR_COST.getValue())) {
                                if (bpList.get(0).getPaymentConfirmType().equals("PROGRESS_PERCENT")) {
                                    QueryWrapper<AvManualVoucherHead> ahWrapper = new QueryWrapper<AvManualVoucherHead>();
                                    ahWrapper.eq(AvManualVoucherHead.ACCOUNT_RESOURCE_TYPE_ID, py.getConnectDocumentId());
                                    ahWrapper.eq(AvManualVoucherHead.DOCUMENT_TYPE, py.getConnectDocumentType());
                                    AvManualVoucherHead head = avManualVoucherHeadService.list(ahWrapper).get(0);
                                    if (bpList.get(0).getInComeSubTypeId() != null) {
                                        BaseIncomeSubType baseIncomeEntity = baseIncomeSubTypeService.getById(bpList.get(0).getInComeSubTypeId());
                                        forwardEntity.setCrAccountCode(baseIncomeEntity.getCAccountCode());//款项小类财务会计编码
                                    }
                                    forwardEntity.setFsscCode(bpList.get(0).getFsscCode());//项目上的财务编码
                                    forwardEntity.setDrAccountCode(bpList.get(0).getAccountCode());//项目上的会计编码
                                    forwardEntity.setEtx1(bpList.get(0).getResponsibleUnitCode());//依托单位编码
                                    forwardEntity.setDeptName(documentEntity.get("DEPT_NAME").toString());
                                    forwardEntity.setDocumentId(py.getConnectDocumentId());
                                    forwardEntity.setDocumentNum(documentEntity.get("DOCUMENT_NUM").toString());
                                    forwardEntity.setDocumentType(py.getConnectDocumentType());
                                    forwardEntity.setEnterDate(head.getDefaultEffectiveDate());
                                    forwardEntity.setUnitName(documentEntity.get("UNIT_NAME").toString());
                                    forwardEntity.setUnitId(Long.parseLong(documentEntity.get("UNIT_ID").toString()));
                                    forwardEntity.setStatus("N");
                                    if (documentEntity.containsKey("REMARK")) {
                                        forwardEntity.setRemark(documentEntity.get("REMARK").toString());
                                    }

                                    forwardEntity.setMainTypeId(Long.parseLong(documentEntity.get("MAIN_TYPE_ID").toString()));
                                    forwardEntity.setMainTypeName(documentEntity.get("MAIN_TYPE_NAME").toString());
                                    forwardEntity.setMainTypeCode(documentEntity.get("MAIN_TYPE_CODE").toString());
                                    if (py.getConnectDocumentType().equals(FsscTableNameEums.LC_LABOR_COST.getValue())) {
                                        forwardEntity.setMoney(documentEntity.containsKey("SHOULD_GIVE_TOTAL_AMOUNT") ? new BigDecimal(documentEntity.get("SHOULD_GIVE_TOTAL_AMOUNT").toString()) : new BigDecimal("0"));
                                    } else if (py.getConnectDocumentType().equals(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue())||py.getConnectDocumentType().equals(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue())) {
                                        forwardEntity.setMoney(documentEntity.containsKey("TOTAL_SUM") ? new BigDecimal(documentEntity.get("TOTAL_SUM").toString()) : new BigDecimal("0"));
                                    } else {
                                        forwardEntity.setMoney(documentEntity.containsKey("TOTAL_AMOUNT") ? new BigDecimal(documentEntity.get("TOTAL_AMOUNT").toString()) : new BigDecimal("0"));
                                    }
                                    forwardEntity.setDeptId(Long.parseLong(documentEntity.containsKey("DEPT_ID") ? documentEntity.get("DEPT_ID").toString() : null));
                                    forwardEntity.setProjectCode(bpList.get(0).getProjectCode());
                                    forwardEntity.setProjectName(bpList.get(0).getProjectName());
                                    forwardEntity.setProjectId(Long.parseLong(documentEntity.containsKey("PROJECT_ID") ? documentEntity.get("PROJECT_ID").toString() : null));
                                    forwardEntity.setDeptCode(documentEntity.containsKey("DEPT_CODE") ? documentEntity.get("DEPT_CODE").toString() : null);
                                    forwardEntity.setUnitCode(documentEntity.containsKey("UNIT_CODE") ? documentEntity.get("UNIT_CODE").toString() : null);
                                    incomeOfCarryForwardService.saveOrUpdate(forwardEntity);

                                }
                            }
                        }

                    }

                }

            }
        }
    }

    /**
     * 项目支出接口
     * a.	项目支出接口表中插入数据节点为报账单或借款单、合同预付款单头中的付款状态为已支付时，及对应的付款单审批完成后，将其对应的支出数据插入中间表中
     */
    private  void  ExpenseCost(PyPamentBusinessLine py){
        if(py.getConnectDocumentType().equals(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue())||py.getConnectDocumentType().equals(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue())||
                py.getConnectDocumentType().equals(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue())||py.getConnectDocumentType().equals(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue())||
                py.getConnectDocumentType().equals(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue())||py.getConnectDocumentType().equals(FsscTableNameEums.LC_LABOR_COST.getValue())) {
            IncomeOfCarryForward forwardEntity = new IncomeOfCarryForward();
            Map<String, Object> documentEntity = avManualVoucherLineService.selectMapLimitOne(py.getConnectDocumentType(), py.getConnectDocumentId());
            if (documentEntity.size() > 0) {
                String documentType = py.getConnectDocumentType();
                if (documentEntity.containsKey("PROJECT_ID")) {
                    QueryWrapper<BudgetProject> bpWrapper = new QueryWrapper<BudgetProject>();
                    bpWrapper.eq(BudgetProject.ID, documentEntity.get("PROJECT_ID"));
                    List<BudgetProject> bpList = budgetProjectService.list(bpWrapper);
                    if (bpList.size() > 0) {
                        BudgetProject project = bpList.get(0);
                        BaseBudgetAccount account = new BaseBudgetAccount();
                        BaseExpenseMainType mainType = baseExpenseMainTypeService.getById(Long.parseLong(documentEntity.get("MAIN_TYPE_ID").toString()));
                        if (mainType != null) {
                            if (mainType.getBudgetAccountId() != null) {
                                account = baseBudgetAccountService.getById(mainType.getBudgetAccountId());
                            }
                        }
                        DssScientificPay pay = new DssScientificPay();
                        pay.setBudgetYear(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy")));
                        pay.setCreateTime(LocalDateTime.now());
                        pay.setDocumentNum(documentEntity.get("DOCUMENT_NUM").toString());
                        pay.setEconomicClassificationId(account != null ? account.getId() : null);
                        pay.setEconomicClassificationCode(account != null ? account.getCode() : null);
                        pay.setFromSystem(project.getExt1());
                        if (documentType.equals(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue()) || documentType.equals(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue()) || documentType.equals(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue())) {

                            if (documentType.equals(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue())) {
                                pay.setFunds(documentEntity.containsKey("TOTAL_SUM") ? new BigDecimal(documentEntity.get("TOTAL_SUM").toString()) : null);
                            } else if (documentType.equals(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue())) {
                                BigDecimal TOTAL_SUM = new BigDecimal(documentEntity.get("TOTAL_SUM").toString());
                                BigDecimal VER_AMOUNT = new BigDecimal(documentEntity.get("VER_AMOUNT").toString());
                                BigDecimal sum = TOTAL_SUM.subtract(VER_AMOUNT);
                                pay.setFunds(sum);
                            } else {
                                BigDecimal TOTAL_SUM = new BigDecimal(documentEntity.get("TOTAL_SUM").toString());
                                BigDecimal VERIFICATION_AMOUNT = new BigDecimal(documentEntity.get("VERIFICATION_AMOUNT").toString());
                                BigDecimal sum = TOTAL_SUM.subtract(VERIFICATION_AMOUNT);
                                pay.setFunds(sum);
                            }
                        } else if (documentType.equals(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue())) {
                            pay.setFunds(documentEntity.containsKey("BORROW_AMOUNT") ? new BigDecimal(documentEntity.get("BORROW_AMOUNT").toString()) : null);
                        } else if (documentType.equals(FsscTableNameEums.LC_LABOR_COST.getValue())) {
                            pay.setFunds(documentEntity.containsKey("SHOULD_GIVE_TOTAL_AMOUNT") ? new BigDecimal(documentEntity.get("SHOULD_GIVE_TOTAL_AMOUNT").toString()) : null);
                        } else if (documentType.equals(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue())) {
                            BigDecimal TOTAL_AMOUNT = new BigDecimal(documentEntity.get("TOTAL_AMOUNT").toString());
                            BigDecimal VERIFICATION_AMOUNT = new BigDecimal(documentEntity.get("VERIFICATION_AMOUNT").toString());
                            BigDecimal sum = TOTAL_AMOUNT.subtract(VERIFICATION_AMOUNT);
                            pay.setFunds(sum);
                        }
                        pay.setPayDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM")));
                        pay.setPhyletic(documentEntity.containsKey("PROJECT_BUDGET_ACCOUNT_CODE") ? documentEntity.get("PROJECT_BUDGET_ACCOUNT_CODE").toString() : null);
                        if (documentEntity.containsKey("PROJECT_BUDGET_ACCOUNT_CODE")) {
                            QueryWrapper<BaseBudgetAccount> accountQueryWrapper = new QueryWrapper<>();
                            accountQueryWrapper.eq(BaseBudgetAccount.CODE, documentEntity.get("PROJECT_BUDGET_ACCOUNT_CODE").toString());
                            pay.setPhyleticName(baseBudgetAccountService.getOne(accountQueryWrapper).getName());
                        }
                        pay.setProCode(project.getProjectCode());
                        pay.setStatus("Y");
                        pay.setRemark(documentEntity.containsKey("REMARK") ? documentEntity.get("REMARK").toString() : null);
                        pay.setTaskCode(project.getProjectCode());
                        pay.setCreateUserName(documentEntity.containsKey("CREATE_USER_NAME") ? documentEntity.get("CREATE_USER_NAME").toString() : null);
                        dssScientificPayService.saveOrUpdate(pay);
                    }
                }
            }
        }
    }
    /**
     * 驳回给发起人
     *
     * @param form
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void rejectProcessToFirst(ProcessPassOrRejectForm form) {
        checkBasicParam(form);
        processMapper.updateDocumentStatus(form.getDocumentId(), form.getDocumentType(), FsscEums.REJECTED.getValue());
        recievePaymentService.modifyCliamStatus(form.getDocumentType(), form.getDocumentId(), false);
        //执行预算保留 释放
        budgetControlService.executeBudgetRemainFree(form.getDocumentId(), form.getDocumentType());
        budgetAdjustService.unAdjust(form.getDocumentId(), form.getDocumentType());
        fundsApplyHeadService.modifyAmount(false, form.getDocumentId(), form.getDocumentType());

        BpmProcessOperatorApprove bpmProcessParamForm = buildBpmRejectToFirstProcessParam(form);
        //bpmProcessParamForm.setProcessVariables(form.getProcessVariables());
        log.info("驳回流程参数:{}", JSON.toJSONString(bpmProcessParamForm));
        //收入上缴
        fundsService.setRecieveLineY(form.getDocumentId()
                , form.getDocumentType(), "N");

        QueryWrapper<Process> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Process.DOCUMENT_ID, form.getDocumentId());

        Process one = processService.getOne(queryWrapper);
        one.setProcessStatus(FsscEums.REJECTED.getValue());
        processService.updateById(one);

        //修改金额
        if (hasModifyAmountTables.contains(form.getDocumentType())) {
            prepayService.modifyAfterSubmit(false, form.getDocumentId(), form.getDocumentType());
        }
        if(form.getDocumentType().equals(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue())
        ||form.getDocumentType().equals(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue())){
            prepayService.modifyContactAmount(false, form.getDocumentId(), form.getDocumentType());
        }


        Result result = bpmOperatorFeignService.rejectToFirstProcess(bpmProcessParamForm);
        AssertUtils.asserts(result.isSuccess(), FsscErrorType.APPROVE_PASS_FIALD);
    }


    /**
     * 撤回流程
     *
     * @param form
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void rollBackProcess(ProcessPassOrRejectForm form) {
        checkBasicParam(form);
        processMapper.updateDocumentStatus(form.getDocumentId(), form.getDocumentType(), FsscEums.RECALLED.getValue());
        recievePaymentService.modifyCliamStatus(form.getDocumentType(), form.getDocumentId(), false);
        //执行预算保留 释放
        budgetControlService.executeBudgetRemainFree(form.getDocumentId(), form.getDocumentType());
        budgetAdjustService.unAdjust(form.getDocumentId(), form.getDocumentType());
        fundsApplyHeadService.modifyAmount(false, form.getDocumentId(), form.getDocumentType());

        Process processList = getLocalProcess(form.getDocumentId());
        AssertUtils.asserts(processList != null, FsscErrorType.THIS_PROCESS_IS_NOT_FIND);

        fundsService.setRecieveLineY(form.getDocumentId()
                , form.getDocumentType(), "N");

        BpmProcessOperatorApprove bpmProcessParamForm =
                buildRollBackBpmProcessOperatorApproveParam(processList.getProcessId(), form.getOpinion());
        bpmProcessParamForm.setProcessVariables(form.getProcessVariables());
        log.info("撤回流程参数:{}", JSON.toJSONString(bpmProcessParamForm));
        addProcessUrl(bpmProcessParamForm.getNextNodeParamVo(), form.getDocumentId(), form.getDocumentType());
        //修改金额
        if (hasModifyAmountTables.contains(form.getDocumentType())) {
            prepayService.modifyAfterSubmit(false, form.getDocumentId(), form.getDocumentType());
        }
        if(form.getDocumentType().equals(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue())
                ||form.getDocumentType().equals(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue())){
            prepayService.modifyContactAmount(false, form.getDocumentId(), form.getDocumentType());
        }
        processList.setProcessStatus(FsscEums.RECALLED.getValue());
        processMapper.updateById(processList);


        Result result = bpmOperatorFeignService.rollBackProcess(bpmProcessParamForm);
        AssertUtils.asserts(result.isSuccess(), FsscErrorType.APPROVE_PASS_FIALD);
    }

    public void orderChargeAgainstDocument(Long documentId, String documentType){
        if (FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue().equals(documentType)) {
            // PyPamentOrderInfo pyPamentOrderInfo = pyPamentOrderInfoService.getById(documentId);
            QueryWrapper<PyPamentBusinessLine>  lineWrapper=new QueryWrapper<>();
            lineWrapper.eq("DOCUMENT_ID",documentId);
            List<PyPamentBusinessLine> pyPamentBusinessLines=pyPamentBusinessLineService.list(lineWrapper);
            if(CollectionUtils.isNotEmpty(pyPamentBusinessLines)){
                String ConnectDocumentNum=pyPamentBusinessLines.get(0).getConnectDocumentNum();//关联的单据号
                QueryWrapper<GeExpenseBorrowPrepay> pyWrapper = new QueryWrapper<>();
                pyWrapper.eq("CONNECT_DOCUMENT_NUM", ConnectDocumentNum).eq("CONNECT_DOCUMENT_TYPE", FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
                List<GeExpenseBorrowPrepay> lines = prepayService.list(pyWrapper);
                if(CollectionUtils.isNotEmpty(lines)){
                    Long id=lines.get(0).getDocumentId();
                    PepaymentOrderInfo pepaymentOrderInfo=pepaymentOrderInfoService.getById(id);
                    if(pepaymentOrderInfo!=null){
                        if(!FsscEums.HAS_CHARGE_AGAINST.getValue().equals(pepaymentOrderInfo.getDocumentStatus())){
                            throw new FSSCException(FsscErrorType.PLEASE_CHARGE_AGAINST_PAYORREP);
                        }
                    }
                }
            }
        }
        if (FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue().equals(documentType)) {
            QueryWrapper<PyPamentBusinessLine>  lineWrapper=new QueryWrapper<>();
            lineWrapper.eq("CONNECT_DOCUMENT_ID",documentId);
            List<PyPamentBusinessLine> pyPamentBusinessLines=pyPamentBusinessLineService.list(lineWrapper);
            if(CollectionUtils.isNotEmpty(pyPamentBusinessLines)){
                Long payDocumentId=pyPamentBusinessLines.get(0).getDocumentId();//关联的付款单单据id
                PyPamentOrderInfo pyPamentOrderInfo=pyPamentOrderInfoService.getById(payDocumentId);
                if(pyPamentOrderInfo!=null){
                    if(!FsscEums.HAS_CHARGE_AGAINST.getValue().equals(pyPamentOrderInfo.getDocumentStatus())){
                        throw new FSSCException(FsscErrorType.PLEASE_CHARGE_AGAINST_PAYORREP);
                    }
                }
            }
        }
    }

    @Override
    @Transactional
    public void doChargeAgainst(Long documentId, String documentType, LocalDateTime defaultEffectiveDate) {

        Long document = processMapper.findDocument(documentId, documentType);
        AssertUtils.asserts(document != null, FsscErrorType.DOCUMENT_NOT_FIND);
        //判断多长单据关联是否顺序冲销单据例：先冲还款单，再冲付款单，最后冲借款单
        orderChargeAgainstDocument(documentId,documentType);
        //冲销时释放认领状态
        recievePaymentService.modifyCliamStatus(documentType, documentId, false);
        String documentStatus = processMapper.findStatus(documentId, documentType);
        AssertUtils.asserts(FsscEums.HAS_ACCOUTED.getValue().equals(documentStatus), FsscErrorType.ONLY_CHARGE_IN_ACCOUNT);
        //修改金额
        if (hasModifyAmountTables.contains(documentType)) {
            prepayService.modifyAfterSubmit(false, documentId, documentType);
        }
        if(documentType.equals(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue())
                ||documentType.equals(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue())){
            prepayService.modifyContactAmount(false, documentId, documentType);
        }
        //借款单额外判断 对公预付款额外判断
        if (FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue().equals(documentType)
                || FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(documentType)) {
            QueryWrapper<GeExpenseBorrowPrepay> prepayQueryWrapper = new QueryWrapper<>();
            prepayQueryWrapper.eq("CONNECT_DOCUMENT_TYPE", FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue())
                    .eq("BORROW_OR_PREPAY_ID", documentId);
            List<GeExpenseBorrowPrepay> prepayList = prepayService.list(prepayQueryWrapper);
            if (CollectionUtils.isNotEmpty(prepayList)) {
                for (GeExpenseBorrowPrepay prepay : prepayList) {
                    String documentType2 = prepay.getDocumentType();
                    Long documentId2 = prepay.getDocumentId();
                    String dStatus = processMapper.findStatus(documentId2, documentType2);
                    AssertUtils.asserts(FsscEums.HAS_CHARGE_AGAINST.getValue().equals(dStatus),
                            FsscErrorType.MUST_ORDER_CHARGE);
                }
            }
        }
        if (FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue().equals(documentType)) {
            DocumentPaymentStatusChange(documentType, documentId);
        }
        //会计凭证冲销
        accountVoucherToEbsService.reverseAccountForBusiness(documentType, documentId, defaultEffectiveDate);
        processMapper.updateDocumentStatus(documentId, documentType, FsscEums.HAS_CHARGE_AGAINST.getValue());
        budgetControlService.executeBudgetFree(documentId, documentType);

    }

    /**
     * 停止流程，删除单据时调用
     * @param documentId
     */
    @Override
    @Transactional
    public void stopProcess(Long documentId) {
        try {
            Process localProcess = getLocalProcess(documentId);
            if(localProcess!=null){
                BpmProcessTaskQueryForm form=new BpmProcessTaskQueryForm();
                form.setProcessInstanceId(localProcess.getProcessId());
                Result<List<BpmProcessTaskVo>> listResult = taskFeignService.list(form);
                if(listResult.isSuccess()&&CollectionUtils.isNotEmpty(listResult.getData())){
                    BpmProcessTaskVo bpmProcessTaskVo = listResult.getData().get(0);
                    BpmProcessTaskFormApprove bpmProcessTask=new BpmProcessTaskFormApprove();
                    bpmProcessTask.setId(StringUtil.castTolong(bpmProcessTaskVo.getId()));
                    Result result = bpmOperatorFeignService.stopProcess(bpmProcessTask);
                    log.info("停止流程:{}",result.getMesg());
                }

                //删除本地流程
                processService.removeById(localProcess.getId());

            }
        }catch (Exception e){
            log.error("停止流程失败:{}",ExceptionUtil.getStackTraceAsString(e));
        }


    }

    /**
     * 撤回构建审批人
     *
     * @param processInstanceId
     * @param opinion
     * @return
     */
    private BpmProcessOperatorApprove buildRollBackBpmProcessOperatorApproveParam(String processInstanceId, String opinion) {
        BpmProcessOperatorApprove approve = new BpmProcessOperatorApprove();
        BpmProcessTaskVo taskVo = findTaskVoByKey(processInstanceId, "start");
        //撤回之后审批人是自己
        ArrayList<NextNodeParamVo> paramVoArrayList = new ArrayList<>();
        NextNodeParamVo nextNodeParamVo = new NextNodeParamVo();
        UserVo currentUser = getCurrentUser();
        nextNodeParamVo.setAcountId(currentUser.getId());
        nextNodeParamVo.setAcountName(currentUser.getName());
        nextNodeParamVo.setApproverDescription(currentUser.getPositionTitle());
        nextNodeParamVo.setStationId(currentUser.getHonor());
        paramVoArrayList.add(nextNodeParamVo);
        //构造主要参数
        BpmProcessTaskFormApprove formApprove = getTaskFormApprove(taskVo, opinion);

        approve.setBpmProcessTaskForm(formApprove);
        approve.setNextNodeParamVo(paramVoArrayList);
        return approve;

    }


    /**
     * 查询流程定义
     *
     * @param documentType
     * @param processType
     * @return
     */
    private ProcessDef findProcessDef(String documentType, String processType) {
        QueryWrapper<ProcessDef> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ProcessDef.DOCUMENT_TYPE, documentType).eq(ProcessDef.PROCESS_TYPE, processType);
        ProcessDef processDef = processDefService.getOne(queryWrapper);
        AssertUtils.asserts(processDef != null, FsscErrorType.PROCESS_DEF_IS_NULL);
        AssertUtils.asserts(StringUtil.isNotEmpty(processDef.getProcessKey()), FsscErrorType.PROCESS_DEF_IS_NULL);
        return processDef;
    }

    /**
     * 检查基本参数
     *
     * @param form
     */
    private void checkBasicParam(ProcessStartForm form) {
        Long documentId = form.getDocumentId();
        String documentType = form.getDocumentType();
        String processType = form.getProcessType();
        AssertUtils.asserts(documentId != null &&
                        StringUtil.isNotEmpty(documentType) &&
                        StringUtil.isNotEmpty(processType)
                , FsscErrorType.DOCUMENT_INFO_IS_NULL);

        Long document = processMapper.findDocument(documentId, documentType);
        AssertUtils.asserts(document != null, FsscErrorType.DOCUMENT_NOT_FIND);

    }

    /**
     * 构造流程启动需要的参数
     *
     * @param form
     * @param
     * @return
     */
    private BpmProcessOperatorFormStart buildStartBpmForm(FsscBpmParam form) {
        //构造一个用户
        UserVo userVo = getCurrentUser();
        BpmProcessOperatorFormStart formStart = new BpmProcessOperatorFormStart();
        //流程启动参数对象
        BpmProcessTaskFormStart bpmProcessTaskFormStart = new BpmProcessTaskFormStart();
        bpmProcessTaskFormStart.setSubmitterCode(StringUtil.objectToString(userVo.getId()));
        bpmProcessTaskFormStart.setSubmitterName(userVo.getName());
        bpmProcessTaskFormStart.setSubmitterStation(userVo.getHonor());
        bpmProcessTaskFormStart.setSubmitterOrg(userVo.getPositionTitle());
        bpmProcessTaskFormStart.setObjectId(StringUtil.objectToString(form.getDocumentId()));
        bpmProcessTaskFormStart.setObjectOrderNum(form.getDocumentNum());
        bpmProcessTaskFormStart.setObjectType(form.getDocumentType());
        FsscTableNameEums eums = FsscTableNameEums.getByValue(form.getDocumentType());
        bpmProcessTaskFormStart.setSourceSystem(FsscConstants.SOURCE_SYSTEM_NAME);
        if (eums != null) {
            bpmProcessTaskFormStart.setObjectDescription(eums.getName());
        }
        bpmProcessTaskFormStart.setProcessDefineKey(form.getProcessDefKey());
        bpmProcessTaskFormStart.setObjectUrl(localUrl + "myBacklog");
        formStart.setBpmProcessTaskForm(bpmProcessTaskFormStart);
        //下一个审批人
        form.setTaskId("start");
        ArrayList<NextNodeParamVo> paramVos = processPersonBiz.findNextAuditPerson(form);

        addProcessUrl(paramVos, form.getDocumentId(), form.getDocumentType());
        formStart.setNextNodeParamVo(paramVos);
        return formStart;
    }


    /**
     * 构造审批通过参数
     *
     * @param form
     * @return
     */
    private BpmProcessOperatorApprove buildBpmProcessOperatorApproveParam(ProcessPassOrRejectForm form) {
        BpmProcessOperatorApprove approve = new BpmProcessOperatorApprove();
        BpmProcessTaskVo taskVo = getTaskVoById(form.getId());
        //构造审批人参数

        //构造主要参数
        BpmProcessTaskFormApprove formApprove = getTaskFormApprove(taskVo, form.getOpinion());

        approve.setBpmProcessTaskForm(formApprove);

        return approve;
    }



    /**
     * 构造驳回到发起人的参数
     *
     * @param form
     * @return
     */
    private BpmProcessOperatorApprove buildBpmRejectToFirstProcessParam(ProcessPassOrRejectForm form) {
        BpmProcessOperatorApprove reject = new BpmProcessOperatorApprove();
        BpmProcessTaskVo taskVo = getTaskVoById(form.getId());

        //保存驳回参数和驳回节点驳回人 先删除之前的
        QueryWrapper<BaseBpmRejectReason> rejectReasonQueryWrapper=new QueryWrapper<>();
        rejectReasonQueryWrapper.eq(BaseBpmRejectReason.DOCUMENT_ID,form.getDocumentId())
                .eq(BaseBpmRejectReason.DOCUMENT_TYPE,form.getDocumentType());
        rejectReasonService.remove(rejectReasonQueryWrapper);
        BaseBpmRejectReason rejectReason=new BaseBpmRejectReason();
        rejectReason.setDocumentId(form.getDocumentId());
        rejectReason.setDocumentType(form.getDocumentType());
        rejectReason.setRejectTaskId(taskVo.getId());
        rejectReason.setRejectTaskKey(taskVo.getTaskKey());
        rejectReason.setRejectUserId(taskVo.getApproverAcount());
        rejectReason.setRejectUserName(taskVo.getApproverName());
        if(StringUtil.isNotEmpty(form.getOpinion())){
            rejectReason.setRejectDesc(form.getOpinion());
            String substring = form.getOpinion().substring(form.getOpinion().indexOf("@@@"));
            rejectReason.setRejectReason(substring.replaceAll("@@@",""));
        }
        rejectReasonService.save(rejectReason);



        BpmProcessTaskFormApprove formApprove = getTaskFormApprove(taskVo, form.getOpinion());
        reject.setBpmProcessTaskForm(formApprove);

        ArrayList<NextNodeParamVo> list = new ArrayList<>();
        NextNodeParamVo paramVo = new NextNodeParamVo();
        paramVo.setAcountId(taskVo.getSubmitterCode());
        paramVo.setAcountName(taskVo.getSubmitterName());
        paramVo.setStationId(taskVo.getSubmitterStation());
        list.add(paramVo);
        addProcessUrl(list, form.getDocumentId(), form.getDocumentType());
        reject.setNextNodeParamVo(list);



        return reject;
    }

    /**
     * 构造参数
     *
     * @param taskVo
     * @param opinion
     * @return
     */
    private BpmProcessTaskFormApprove getTaskFormApprove(BpmProcessTaskVo taskVo, String opinion) {
        BpmProcessTaskFormApprove formApprove = new BpmProcessTaskFormApprove();
        formApprove.setId(StringUtil.castTolong(taskVo.getId()));
        if (StringUtil.isNotEmpty(opinion)) {
            opinion = opinion.replaceAll("reason", ":");
            String[] opinionSp = opinion.split("@@@");
            if(opinionSp.length>1){
                formApprove.setOpinion(opinionSp[0]);
            }
        }
        formApprove.setObjectDescription(taskVo.getObjectDescription());

        return formApprove;
    }


    /**
     * 获取任务vo
     *
     * @param id
     * @return
     */
    private BpmProcessTaskVo getTaskVoById(Long id) {
        AssertUtils.asserts(id != null, FsscErrorType.TAKS_ID_CANT_BE_NULL);
        Result<BpmProcessTaskVo> taskVoResult = taskFeignService.get(id);
        AssertUtils.asserts(taskVoResult.isSuccess(), FsscErrorType.GET_TASK_FAILD);
        return taskVoResult.getData();
    }

    /**
     * 查询审批任务
     *
     * @param processInstanceId
     * @param status
     * @return
     */
    private BpmProcessTaskVo findTaskVo(String processInstanceId, String status) {
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm =
                new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setTaskStauts(status);
        bpmProcessTaskQueryForm.setProcessInstanceId(processInstanceId);
        Result<List<BpmProcessTaskVo>> taskResult = taskFeignService.list(bpmProcessTaskQueryForm);
        AssertUtils.asserts(taskResult.isSuccess()
                && CollectionUtils.isNotEmpty(taskResult.getData()), FsscErrorType.GET_TASK_FAILD);
        return taskResult.getData().get(0);
    }


    private BpmProcessTaskVo findTaskVoByKey(String processInstanceId, String taskKey) {
        BpmProcessTaskQueryForm bpmProcessTaskQueryForm =
                new BpmProcessTaskQueryForm();
        bpmProcessTaskQueryForm.setTaskKey(taskKey);
        bpmProcessTaskQueryForm.setProcessInstanceId(processInstanceId);
        Result<List<BpmProcessTaskVo>> taskResult = taskFeignService.list(bpmProcessTaskQueryForm);
        List<BpmProcessTaskVo> data = taskResult.getData();
        AssertUtils.asserts(taskResult.isSuccess()
                && CollectionUtils.isNotEmpty(data), FsscErrorType.GET_TASK_FAILD);
        return data.get(0);
    }


    /**
     * 获取下节点审批人
     *
     * @param processKey
     * @param taskId
     * @return
     */
    private ArrayList<NextNodeParamVo> getNextNode(String processKey, String taskId) {
        BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixQueryForm =
                new BpmApprovalMatrixQueryFormForApproval();
        bpmApprovalMatrixQueryForm.setTaskID(taskId);
        bpmApprovalMatrixQueryForm.setProcessDefineKey(processKey);
        bpmApprovalMatrixQueryForm.setChargeOrg("1001");
        log.info("审批矩阵流程参数:{}", JSON.toJSONString(bpmApprovalMatrixQueryForm));
        Result<List<BpmApprovalMatrixVo>> matrixVos = matrixFeignService.findNextApprover(bpmApprovalMatrixQueryForm);
        AssertUtils.asserts(matrixVos.isSuccess(), FsscErrorType.NEXT_APPROVER_FIND_FIAL);
        List<BpmApprovalMatrixVo> matrixVosData = matrixVos.getData();
        return buildNextNodeApprovers(matrixVosData != null ? matrixVosData : new ArrayList<>());
    }

    /**
     * 构造下一审批人,如果从流程引擎查询不到下一节点审批人,构造空审批人
     *
     * @param matrixVos
     * @return
     */
    private ArrayList<NextNodeParamVo> buildNextNodeApprovers(List<BpmApprovalMatrixVo> matrixVos) {
        ArrayList<NextNodeParamVo> nodeParamVos = new ArrayList<>();
        for (BpmApprovalMatrixVo vo : matrixVos) {
            NextNodeParamVo nextNodeParamVo = new NextNodeParamVo();
            nextNodeParamVo.setAcountId(vo.getAccountNum());
            nextNodeParamVo.setAcountName(vo.getAccountName());
            nextNodeParamVo.setStationId(vo.getPosition());
            nodeParamVos.add(nextNodeParamVo);
        }
        if (CollectionUtils.isEmpty(nodeParamVos)) {
            nodeParamVos.add(new NextNodeParamVo());
        }
        return nodeParamVos;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    private UserVo getCurrentUser() {
        return commonServices.getCurrentUser();
    }


    /**
     * 添加审批地址
     *
     * @param next
     * @param documentId
     * @param documentType
     */
    private void addProcessUrl(ArrayList<NextNodeParamVo> next, Long documentId, String documentType) {
        if (CollectionUtils.isNotEmpty(next)) {
            for (NextNodeParamVo vo : next) {
//                vo.setObjectUrl(processUrls.get(documentType)+"?documentId="+documentId);
                vo.setObjectUrl(localUrl + "myBacklog");
            }
        }
    }


    /**
     * 获取单据类型定义,并检查非空和启用状态
     *
     * @param documentTable
     * @return
     */
    private BaseDocumentType getBaseDocumentType(String documentTable) {
        AssertUtils.asserts(StringUtils.isNotBlank(documentTable), FsscErrorType.DOCUMENT_INFO_IS_NULL);
        DeptVo currentDept = commonServices.getCurrentDept();
        BaseDocumentType baseDocumentType = documentTypeService.getDocTypeByFunction(
                documentTable, currentDept.getDeptCode());
        if (baseDocumentType == null || !FsscEums.VALID.getValue().equals(baseDocumentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        return baseDocumentType;
    }
}
