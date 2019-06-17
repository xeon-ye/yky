package com.deloitte.services.fssc.budget.service.impl;

import com.deloitte.services.fssc.base.service.IBaseBudgetAccountService;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeService;
import com.deloitte.services.fssc.base.service.IBaseExpenseMainTypeService;
import com.deloitte.services.fssc.budget.service.*;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentInfo;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentInfoService;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyInfoService;
import com.deloitte.services.fssc.business.contract.entity.CrbContractReimburseBill;
import com.deloitte.services.fssc.business.contract.service.ICrbContractReimburseBillService;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseService;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostService;
import com.deloitte.services.fssc.business.poi.entity.PepaymentOrderInfo;
import com.deloitte.services.fssc.business.poi.service.IPepaymentOrderInfoService;
import com.deloitte.services.fssc.business.travle.entity.TasTravelApplyInfo;
import com.deloitte.services.fssc.business.travle.entity.TasTravelReimburse;
import com.deloitte.services.fssc.business.travle.service.ITasTravelApplyInfoService;
import com.deloitte.services.fssc.business.travle.service.ITasTravelReimburseService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.AssertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class BudgetControlServiceImpl implements IBudgetControlService {


    /**
     * 预算科目 服务
     */
    @Autowired
    IBaseBudgetAccountService budgetAccountService;

    /**
     * 支出大类 服务
     */
    @Autowired
    IBaseExpenseMainTypeService expenseMainTypeService;

    /**
     * 单据类型定义 服务
     */
    @Autowired
    IBaseDocumentTypeService documentTypeService;

    /**
     * 项目 服务
     */
    @Autowired
    IBudgetProjectService projectService;

    /**
     * 项目预算 服务
     */
    @Autowired
    IBudgetProjectBudgetService projectBudgetService;

    /**
     * 基础预算 服务
     */
    @Autowired
    IBudgetBasicBudgetService basicBudgetService;


    /**
     * 差旅申请单头表 服务
     */
    @Autowired
    ITasTravelApplyInfoService travelApplyInfoService;

    /**
     * 个人借款单头表 服务
     */
    @Autowired
    IBorrowMoneyInfoService borrowInfoService;

    /**
     * 对公预付款
     */
    @Autowired
    IAdvancePaymentInfoService advancePaymentInfoService;

    /**
     * 通用报账单 服务
     */
    @Autowired
    IGeGeneralExpenseService generalExpenseService;

    /**
     * 差旅报账单 服务
     */
    @Autowired
    ITasTravelReimburseService travelReimburseService;

    /**
     * 合同报账单 服务
     */
    @Autowired
    ICrbContractReimburseBillService contractReimburseBillService;

    /**
     * 劳务费报账单 服务
     */
    @Autowired
    ILcLaborCostService lcLaborCostService;

    /**
     * 还款单
     */
    @Autowired
    IPepaymentOrderInfoService repayOrderInfoService;

    @Autowired
    IBudgetTravelApplyControlService travelApplyControlService;

    @Autowired
    IBudgetBorrowControlService budgetBorrowControlService;

    @Autowired
    IBudgetAdvanceControlService advanceControlService;

    @Autowired
    IBudgetGeneralControlService generalControlService;

    @Autowired
    IBudgetTravelExpenseControlService travelExpenseControlService;

    @Autowired
    IBudgetContractControlService contractControlService;

    @Autowired
    IBudgetLaborCostControlService laborCostControlService;

    @Autowired
    private IBudgetRepayControlService repayControlService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean checkBudgetRemain(Long documentId, String documentTable, boolean warningFlag,
            boolean forbidFlag, boolean updateBudgetFlag) {
        AssertUtils.asserts(documentId != null, FsscErrorType.DOCUMENT_ID_CANNOT_EMPTY);
        AssertUtils.asserts(StringUtils.isNotBlank(documentTable),
                FsscErrorType.DOCUMENT_TABLE_CANNOT_EMPTY);
        if (FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue().equals(documentTable)) {
            //差旅申请
            TasTravelApplyInfo travelApplyInfo = travelApplyInfoService.getById(documentId);
            AssertUtils.asserts(travelApplyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.NEW.getValue().equals(travelApplyInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(travelApplyInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(travelApplyInfo.getDocumentStatus())) {
                travelApplyControlService.travelApplyBudgetControl(travelApplyInfo,warningFlag,
                        forbidFlag,updateBudgetFlag);
            }
        } else if (FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue().equals(documentTable)) {
            // 借款单
            BorrowMoneyInfo borrowMoneyInfo = borrowInfoService.getById(documentId);
            AssertUtils.asserts(borrowMoneyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            //新建、已驳回、已撤回 状态,才做预算保留 额度控制
            if (FsscEums.NEW.getValue().equals(borrowMoneyInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(borrowMoneyInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(borrowMoneyInfo.getDocumentStatus())) {
                budgetBorrowControlService.borrowBudgetControl(borrowMoneyInfo, warningFlag,
                        forbidFlag, updateBudgetFlag);
            }
        }else if (FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(documentTable)){
            // 对公预付款
            AdvancePaymentInfo advancePaymentInfo = advancePaymentInfoService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(advancePaymentInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(advancePaymentInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(advancePaymentInfo.getDocumentStatus())) {
                advanceControlService.advanceBudgetControl(advancePaymentInfo, warningFlag,
                        forbidFlag, updateBudgetFlag);
            }
        } else if (FsscTableNameEums.GE_GENERAL_EXPENSE.getValue().equals(documentTable)) {
            // 通用报账单
            GeGeneralExpense generalExpense = generalExpenseService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(generalExpense.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(generalExpense.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(generalExpense.getDocumentStatus())) {
                generalControlService.generalBudgetControl(generalExpense, warningFlag,
                        forbidFlag, updateBudgetFlag);
            }
        } else if (FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue().equals(documentTable)) {
            // 差旅报账单
            TasTravelReimburse tasTravelReimburse = travelReimburseService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(tasTravelReimburse.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(tasTravelReimburse.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(tasTravelReimburse.getDocumentStatus())) {
                travelExpenseControlService.travelExpenseBudgetControl(tasTravelReimburse, warningFlag,
                        forbidFlag, updateBudgetFlag);
            }
        }else if (FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue().equals(documentTable)) {
            // 合同报账单
            CrbContractReimburseBill contractReimburseBill = contractReimburseBillService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(contractReimburseBill.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(contractReimburseBill.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(contractReimburseBill.getDocumentStatus())) {
                contractControlService.contractBudgetControl(contractReimburseBill, warningFlag,
                        forbidFlag, updateBudgetFlag);
            }
        }else if (FsscTableNameEums.LC_LABOR_COST.getValue().equals(documentTable)) {
            // 劳务费
            LcLaborCost laborCost = lcLaborCostService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(laborCost.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(laborCost.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(laborCost.getDocumentStatus())) {
                laborCostControlService.laborCostBudgetControl(laborCost, warningFlag,
                        forbidFlag, updateBudgetFlag);
            }
        }else if (FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue().equals(documentTable)) {
            // 还款单
            PepaymentOrderInfo pepaymentOrderInfo = repayOrderInfoService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(pepaymentOrderInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(pepaymentOrderInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(pepaymentOrderInfo.getDocumentStatus())) {
                repayControlService.repayBudgetControl(pepaymentOrderInfo, warningFlag,
                        forbidFlag, updateBudgetFlag);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean checkBudgetRemain(Long documentId, String documentTable) {
        AssertUtils.asserts(documentId != null, FsscErrorType.DOCUMENT_ID_CANNOT_EMPTY);
        AssertUtils.asserts(StringUtils.isNotBlank(documentTable), FsscErrorType.DOCUMENT_TABLE_CANNOT_EMPTY);
        if (FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue().equals(documentTable)) {
            //差旅申请
            TasTravelApplyInfo travelApplyInfo = travelApplyInfoService.getById(documentId);
            AssertUtils.asserts(travelApplyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.NEW.getValue().equals(travelApplyInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(travelApplyInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(travelApplyInfo.getDocumentStatus())) {
                travelApplyControlService.travelApplyBudgetControl(travelApplyInfo, true,
                        true, false);
            }
        } else if (FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue().equals(documentTable)) {
            //借款单
            BorrowMoneyInfo borrowMoneyInfo = borrowInfoService.getById(documentId);
            AssertUtils.asserts(borrowMoneyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            //新建、已驳回、已撤回 状态,才做预算保留 额度控制
            if (FsscEums.NEW.getValue().equals(borrowMoneyInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(borrowMoneyInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(borrowMoneyInfo.getDocumentStatus())) {
                budgetBorrowControlService
                        .borrowBudgetControl(borrowMoneyInfo,true, true,
                        false);
            }
        }else if (FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(documentTable)){
            // 对公预付款
            AdvancePaymentInfo advancePaymentInfo = advancePaymentInfoService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(advancePaymentInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(advancePaymentInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(advancePaymentInfo.getDocumentStatus())) {
                advanceControlService.advanceBudgetControl(advancePaymentInfo,true,
                        true, false);
            }
        } else if (FsscTableNameEums.GE_GENERAL_EXPENSE.getValue().equals(documentTable)) {
            // 通用报账单
            GeGeneralExpense generalExpense = generalExpenseService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(generalExpense.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(generalExpense.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(generalExpense.getDocumentStatus())) {
                generalControlService.generalBudgetControl(generalExpense, true,
                        true, false);
            }
        }else if (FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue().equals(documentTable)) {
            // 差旅报账单
            TasTravelReimburse tasTravelReimburse = travelReimburseService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(tasTravelReimburse.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(tasTravelReimburse.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(tasTravelReimburse.getDocumentStatus())) {
                travelExpenseControlService.travelExpenseBudgetControl(tasTravelReimburse, true,
                        true, false);
            }
        }else if (FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue().equals(documentTable)) {
            // 合同报账单
            CrbContractReimburseBill contractReimburseBill = contractReimburseBillService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(contractReimburseBill.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(contractReimburseBill.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(contractReimburseBill.getDocumentStatus())) {
                contractControlService.contractBudgetControl(contractReimburseBill, true,
                        true, false);
            }
        }else if (FsscTableNameEums.LC_LABOR_COST.getValue().equals(documentTable)) {
            // 劳务费报账单
            LcLaborCost laborCost = lcLaborCostService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(laborCost.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(laborCost.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(laborCost.getDocumentStatus())) {
                laborCostControlService.laborCostBudgetControl(laborCost, true,
                        true, false);
            }
        }else if (FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue().equals(documentTable)) {
            // 还款单
            PepaymentOrderInfo pepaymentOrderInfo = repayOrderInfoService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(pepaymentOrderInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(pepaymentOrderInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(pepaymentOrderInfo.getDocumentStatus())) {
                repayControlService.repayBudgetControl(pepaymentOrderInfo, true,
                        true, false);
            }
        }
        return true;
    }

    @Override
    public boolean executeBudgetRemain(Long documentId, String documentTable,boolean warningFlag, boolean forbidFlag) {
        AssertUtils.asserts(documentId != null, FsscErrorType.DOCUMENT_ID_CANNOT_EMPTY);
        AssertUtils.asserts(StringUtils.isNotBlank(documentTable),
                FsscErrorType.DOCUMENT_TABLE_CANNOT_EMPTY);
        if (FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue().equals(documentTable)) {
            //差旅申请
            TasTravelApplyInfo travelApplyInfo = travelApplyInfoService.getById(documentId);
            AssertUtils.asserts(travelApplyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.NEW.getValue().equals(travelApplyInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(travelApplyInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(travelApplyInfo.getDocumentStatus())) {
                travelApplyControlService.travelApplyBudgetControl(travelApplyInfo, warningFlag,
                        true, true);
            }
        } else if (FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue().equals(documentTable)) {
            //借款单
            BorrowMoneyInfo borrowMoneyInfo = borrowInfoService.getById(documentId);
            AssertUtils.asserts(borrowMoneyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            AssertUtils.asserts(borrowMoneyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            //新建、已驳回、已撤回 状态,才做预算保留 额度控制
            if (FsscEums.NEW.getValue().equals(borrowMoneyInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(borrowMoneyInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(borrowMoneyInfo.getDocumentStatus())) {
                budgetBorrowControlService
                        .borrowBudgetControl(borrowMoneyInfo, warningFlag, true,
                        true);
            }
        }else if (FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(documentTable)){
            // 对公预付款
            AdvancePaymentInfo advancePaymentInfo = advancePaymentInfoService.getById(documentId);
            AssertUtils.asserts(advancePaymentInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.NEW.getValue().equals(advancePaymentInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(advancePaymentInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(advancePaymentInfo.getDocumentStatus())) {
                advanceControlService.advanceBudgetControl(advancePaymentInfo,warningFlag,
                        true, true);
            }
        } else if (FsscTableNameEums.GE_GENERAL_EXPENSE.getValue().equals(documentTable)) {
            // 通用报账单
            GeGeneralExpense generalExpense = generalExpenseService.getById(documentId);
            AssertUtils.asserts(generalExpense != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.NEW.getValue().equals(generalExpense.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(generalExpense.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(generalExpense.getDocumentStatus())) {
                generalControlService.generalBudgetControl(generalExpense, warningFlag,
                        true, true);
            }
        }else if (FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue().equals(documentTable)) {
            // 差旅报账单
            TasTravelReimburse tasTravelReimburse = travelReimburseService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(tasTravelReimburse.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(tasTravelReimburse.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(tasTravelReimburse.getDocumentStatus())) {
                travelExpenseControlService.travelExpenseBudgetControl(tasTravelReimburse, warningFlag,
                        true, true);
            }
        }else if (FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue().equals(documentTable)) {
            // 合同报账单
            CrbContractReimburseBill contractReimburseBill = contractReimburseBillService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(contractReimburseBill.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(contractReimburseBill.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(contractReimburseBill.getDocumentStatus())) {
                contractControlService.contractBudgetControl(contractReimburseBill, warningFlag,
                        true, true);
            }
        }else if (FsscTableNameEums.LC_LABOR_COST.getValue().equals(documentTable)) {
            // 劳务费报账单
            LcLaborCost laborCost = lcLaborCostService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(laborCost.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(laborCost.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(laborCost.getDocumentStatus())) {
                laborCostControlService.laborCostBudgetControl(laborCost, warningFlag,
                        true, true);
            }
        }else if (FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue().equals(documentTable)) {
            // 还款单
            PepaymentOrderInfo pepaymentOrderInfo = repayOrderInfoService.getById(documentId);
            if (FsscEums.NEW.getValue().equals(pepaymentOrderInfo.getDocumentStatus())
                    || FsscEums.REJECTED.getValue().equals(pepaymentOrderInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(pepaymentOrderInfo.getDocumentStatus())) {
                repayControlService.repayBudgetControl(pepaymentOrderInfo, warningFlag,
                        true, true);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean executeBudgetOccupy(Long documentId, String documentTable) {
        AssertUtils.asserts(documentId != null, FsscErrorType.DOCUMENT_ID_CANNOT_EMPTY);
        AssertUtils.asserts(StringUtils.isNotBlank(documentTable),
                FsscErrorType.DOCUMENT_TABLE_CANNOT_EMPTY);
        if (FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue().equals(documentTable)) {
            //差旅申请
            TasTravelApplyInfo travelApplyInfo = travelApplyInfoService.getById(documentId);
            AssertUtils.asserts(travelApplyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.APPROVED.getValue().equals(travelApplyInfo.getDocumentStatus())) {
                travelApplyControlService.travelApplyRemain2Occupy(travelApplyInfo,true);
            }
        }else if (FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue().equals(documentTable)) {
            //借款单
            BorrowMoneyInfo borrowMoneyInfo = borrowInfoService.getById(documentId);
            AssertUtils.asserts(borrowMoneyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.APPROVED.getValue().equals(borrowMoneyInfo.getDocumentStatus())) {
                budgetBorrowControlService.borrowRemain2Occupy(borrowMoneyInfo,true);
            }
        }else if (FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(documentTable)){
            // 对公预付款
            AdvancePaymentInfo advancePaymentInfo = advancePaymentInfoService.getById(documentId);
            AssertUtils.asserts(advancePaymentInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.APPROVED.getValue().equals(advancePaymentInfo.getDocumentStatus())) {
                advanceControlService.advanceRemain2Occupy(advancePaymentInfo,true);
            }
        } else if (FsscTableNameEums.GE_GENERAL_EXPENSE.getValue().equals(documentTable)) {
            // 通用报账单
            GeGeneralExpense generalExpense = generalExpenseService.getById(documentId);
            AssertUtils.asserts(generalExpense != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            //已审批 状态,才执行预算保留 变 占用
            if (FsscEums.APPROVED.getValue().equals(generalExpense.getDocumentStatus())) {
                generalControlService.generalRemain2Occupy(generalExpense,true);
            }
        }else if (FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue().equals(documentTable)) {
            // 差旅报账单
            TasTravelReimburse travelReimburse = travelReimburseService.getById(documentId);
            AssertUtils.asserts(travelReimburse != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            //已审批 状态,才执行预算保留 变 占用
            if (FsscEums.APPROVED.getValue().equals(travelReimburse.getDocumentStatus())) {
                travelExpenseControlService.travelExpenseRemain2Occupy(travelReimburse,true);
            }
        }else if (FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue().equals(documentTable)) {
            // 合同报账单
            CrbContractReimburseBill contractReimburseBill = contractReimburseBillService.getById(documentId);
            if (FsscEums.APPROVED.getValue().equals(contractReimburseBill.getDocumentStatus())) {
                contractControlService.contractRemain2Occupy(contractReimburseBill,true);
            }
        }else if (FsscTableNameEums.LC_LABOR_COST.getValue().equals(documentTable)) {
            // 劳务费报账单
            LcLaborCost laborCost = lcLaborCostService.getById(documentId);
            if (FsscEums.APPROVED.getValue().equals(laborCost.getDocumentStatus())) {
                laborCostControlService.laborCostRemain2Occupy(laborCost,true);
            }
        }
        else if (FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue().equals(documentTable)) {
            // 还款单
            PepaymentOrderInfo pepaymentOrderInfo = repayOrderInfoService.getById(documentId);
            if (FsscEums.APPROVED.getValue().equals(pepaymentOrderInfo.getDocumentStatus())) {
                repayControlService.repayRemain2Occupy(pepaymentOrderInfo,true);
            }
        }
        return true;
    }

    @Override
    public boolean executeBudgetRemainFree(Long documentId, String documentTable) {
        AssertUtils.asserts(documentId != null, FsscErrorType.DOCUMENT_ID_CANNOT_EMPTY);
        AssertUtils.asserts(StringUtils.isNotBlank(documentTable),
                FsscErrorType.DOCUMENT_TABLE_CANNOT_EMPTY);
        if (FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue().equals(documentTable)) {
            //差旅申请
            TasTravelApplyInfo travelApplyInfo = travelApplyInfoService.getById(documentId);
            AssertUtils.asserts(travelApplyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            //已驳回(并且是驳回到发起人)、已撤回 状态,才执行预算保留释放
            if (FsscEums.REJECTED.getValue().equals(travelApplyInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(travelApplyInfo.getDocumentStatus())) {
                return travelApplyControlService.travelApplyBudgetFree(FsscEums.BUDGET_PHASE_PROCESSING,travelApplyInfo);
            }
        }else if (FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue().equals(documentTable)) {
            //借款单
            BorrowMoneyInfo borrowMoneyInfo = borrowInfoService.getById(documentId);
            AssertUtils.asserts(borrowMoneyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            //已驳回(并且是驳回到发起人)、已撤回 状态,才执行预算保留释放
            if (FsscEums.REJECTED.getValue().equals(borrowMoneyInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(borrowMoneyInfo.getDocumentStatus())) {
                return budgetBorrowControlService.borrowBudgetFree(FsscEums.BUDGET_PHASE_PROCESSING,borrowMoneyInfo);
            }
        }
        else if (FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(documentTable)) {
            //对公预付款
            AdvancePaymentInfo advancePaymentInfo = advancePaymentInfoService.getById(documentId);
            AssertUtils.asserts(advancePaymentInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.REJECTED.getValue().equals(advancePaymentInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(advancePaymentInfo.getDocumentStatus())) {
                return advanceControlService.advanceBudgetFree(FsscEums.BUDGET_PHASE_PROCESSING,advancePaymentInfo);
            }
        }
        else if (FsscTableNameEums.GE_GENERAL_EXPENSE.getValue().equals(documentTable)) {
            // 通用报账单
            GeGeneralExpense generalExpense = generalExpenseService.getById(documentId);
            AssertUtils.asserts(generalExpense != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.REJECTED.getValue().equals(generalExpense.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(generalExpense.getDocumentStatus())) {
                return generalControlService.generalBudgetFree(FsscEums.BUDGET_PHASE_PROCESSING,generalExpense);
            }
        }
        else if (FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue().equals(documentTable)) {
            // 差旅报账单
            TasTravelReimburse travelReimburse = travelReimburseService.getById(documentId);
            AssertUtils.asserts(travelReimburse != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.REJECTED.getValue().equals(travelReimburse.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(travelReimburse.getDocumentStatus())) {
                return travelExpenseControlService.travelExpenseBudgetFree(FsscEums.BUDGET_PHASE_PROCESSING,travelReimburse);
            }
        }else if (FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue().equals(documentTable)) {
            // 合同报账单
            CrbContractReimburseBill contractReimburseBill = contractReimburseBillService.getById(documentId);
            if (FsscEums.REJECTED.getValue().equals(contractReimburseBill.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(contractReimburseBill.getDocumentStatus())) {
                return contractControlService.contractBudgetFree(FsscEums.BUDGET_PHASE_PROCESSING,contractReimburseBill);
            }
        }else if (FsscTableNameEums.LC_LABOR_COST.getValue().equals(documentTable)) {
            // 劳务费报账单
            LcLaborCost laborCost = lcLaborCostService.getById(documentId);
            if (FsscEums.REJECTED.getValue().equals(laborCost.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(laborCost.getDocumentStatus())) {
                return laborCostControlService.laborCostBudgetFree(FsscEums.BUDGET_PHASE_PROCESSING,laborCost);
            }
        }else if (FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue().equals(documentTable)) {
            // 还款单
            PepaymentOrderInfo pepaymentOrderInfo = repayOrderInfoService.getById(documentId);
            if (FsscEums.REJECTED.getValue().equals(pepaymentOrderInfo.getDocumentStatus())
                    || FsscEums.RECALLED.getValue().equals(pepaymentOrderInfo.getDocumentStatus())) {
                return repayControlService.repayBudgetFree(FsscEums.BUDGET_PHASE_PROCESSING,pepaymentOrderInfo);
            }
        }
        return true;
    }

    @Override
    public boolean executeBudgetFree(Long documentId, String documentTable) {
        AssertUtils.asserts(documentId != null, FsscErrorType.DOCUMENT_ID_CANNOT_EMPTY);
        AssertUtils.asserts(StringUtils.isNotBlank(documentTable), FsscErrorType.DOCUMENT_TABLE_CANNOT_EMPTY);
        if (FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue().equals(documentTable)) {
            //差旅申请
            TasTravelApplyInfo travelApplyInfo = travelApplyInfoService.getById(documentId);
            AssertUtils.asserts(travelApplyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            //已撤销、关闭状态 执行预算释放
            if (FsscEums.REVOKE.getValue().equals(travelApplyInfo.getDocumentStatus())
                    || FsscEums.CLOSED.getValue().equals(travelApplyInfo.getDocumentStatus())) {
                return travelApplyControlService.travelApplyBudgetFree(FsscEums.BUDGET_PHASE_PROCESSED,travelApplyInfo);
            }
        }else if (FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue().equals(documentTable)) {
            //借款单
            BorrowMoneyInfo borrowMoneyInfo = borrowInfoService.getById(documentId);
            AssertUtils.asserts(borrowMoneyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            //已冲销 状态,才执行预算释放
            if (FsscEums.HAS_CHARGE_AGAINST.getValue().equals(borrowMoneyInfo.getDocumentStatus())) {
                return budgetBorrowControlService.borrowBudgetFree(FsscEums.BUDGET_PHASE_PROCESSED,borrowMoneyInfo);
            }
        }else if (FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue().equals(documentTable)) {
            //对公预付款
            AdvancePaymentInfo advancePaymentInfo = advancePaymentInfoService.getById(documentId);
            AssertUtils.asserts(advancePaymentInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.HAS_CHARGE_AGAINST.getValue().equals(advancePaymentInfo.getDocumentStatus())) {
                return advanceControlService.advanceBudgetFree(FsscEums.BUDGET_PHASE_PROCESSED,advancePaymentInfo);
            }
        } else if (FsscTableNameEums.GE_GENERAL_EXPENSE.getValue().equals(documentTable)) {
            // 通用报账单
            GeGeneralExpense generalExpense = generalExpenseService.getById(documentId);
            AssertUtils.asserts(generalExpense != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.HAS_CHARGE_AGAINST.getValue().equals(generalExpense.getDocumentStatus())) {
                return generalControlService.generalBudgetFree(FsscEums.BUDGET_PHASE_PROCESSED,generalExpense);
            }
        } else if (FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue().equals(documentTable)) {
            // 差旅报账单
            TasTravelReimburse travelReimburse = travelReimburseService.getById(documentId);
            AssertUtils.asserts(travelReimburse != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            if (FsscEums.HAS_CHARGE_AGAINST.getValue().equals(travelReimburse.getDocumentStatus())) {
                return travelExpenseControlService.travelExpenseBudgetFree(FsscEums.BUDGET_PHASE_PROCESSED,travelReimburse);
            }
        }else if (FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue().equals(documentTable)) {
            // 合同报账单
            CrbContractReimburseBill contractReimburseBill = contractReimburseBillService.getById(documentId);
            if (FsscEums.HAS_CHARGE_AGAINST.getValue().equals(contractReimburseBill.getDocumentStatus())) {
                return contractControlService.contractBudgetFree(FsscEums.BUDGET_PHASE_PROCESSED,contractReimburseBill);
            }
        }else if (FsscTableNameEums.LC_LABOR_COST.getValue().equals(documentTable)) {
            // 劳务费报账单
            LcLaborCost laborCost = lcLaborCostService.getById(documentId);
            if (FsscEums.HAS_CHARGE_AGAINST.getValue().equals(laborCost.getDocumentStatus())) {
                return laborCostControlService.laborCostBudgetFree(FsscEums.BUDGET_PHASE_PROCESSED,laborCost);
            }
        }else if (FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue().equals(documentTable)) {
            // 还款单
            PepaymentOrderInfo pepaymentOrderInfo = repayOrderInfoService.getById(documentId);
            if (FsscEums.HAS_CHARGE_AGAINST.getValue().equals(pepaymentOrderInfo.getDocumentStatus())) {
                return repayControlService.repayBudgetFree(FsscEums.BUDGET_PHASE_PROCESSED,pepaymentOrderInfo);
            }
        }
        return true;
    }

}
