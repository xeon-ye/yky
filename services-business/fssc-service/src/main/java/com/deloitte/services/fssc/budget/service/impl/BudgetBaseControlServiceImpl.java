package com.deloitte.services.fssc.budget.service.impl;

import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.service.IBaseBudgetAccountService;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeService;
import com.deloitte.services.fssc.base.service.IBaseExpenseMainTypeService;
import com.deloitte.services.fssc.budget.entity.*;
import com.deloitte.services.fssc.budget.service.*;
import com.deloitte.services.fssc.business.advance.service.IAdvancePaymentInfoService;
import com.deloitte.services.fssc.business.advance.service.IContactDetailService;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyInfoService;
import com.deloitte.services.fssc.business.borrow.service.IBorrowMoneyLineService;
import com.deloitte.services.fssc.business.contract.service.ICrbContractReimburseBillService;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.service.IGeExpenseBorrowPrepayService;
import com.deloitte.services.fssc.business.general.service.IGeGeneralExpenseService;
import com.deloitte.services.fssc.business.labor.service.ILcLaborCostService;
import com.deloitte.services.fssc.business.poi.service.IPepaymentOrderInfoService;
import com.deloitte.services.fssc.business.travle.service.ITasTravelApplyInfoService;
import com.deloitte.services.fssc.business.travle.service.ITasTravelReimburseService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;


public abstract class BudgetBaseControlServiceImpl {

    /**
     * 公共服务
     */
    @Autowired
    FsscCommonServices commonServices;

    /**
     * 单据类型定义 服务
     */
    @Autowired
    IBaseDocumentTypeService documentTypeService;

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
     * 预算事前申请 服务
     */
    @Autowired
    IBudgetAdvanceApplyForService advanceApplyForService;

    /**
     * 预算事前借款 服务
     */
    @Autowired
    IBudgetAdvanceBorrowService advanceBorrowService;

    /**
     * 预算事前借款行 服务
     */
    @Autowired
    IBudgetAdvanceBorrowLineService advanceBorrowLineService;

    /**
     * 预算事后报账 服务
     */
    @Autowired
    IBudgetAfterExpenseService afterExpenseService;

    /**
     * 预算事后报账行 服务
     */
    @Autowired
    IBudgetAfterExpenseLineService afterExpenseLineService;

    /**
     * 预算事后还款 服务
     */
    @Autowired
    IBudgetAfterRepaymentService afterRepaymentService;

    /**
     * 预算事后还款行 无法
     */
    @Autowired
    IBudgetAfterRepaymentLineService afterRepaymentLineService;

    /**
     * 差旅申请单头表 服务
     */
    @Autowired
    ITasTravelApplyInfoService travelApplyInfoService;

    /**
     * 差旅报账单头表 服务
     */
    @Autowired
    ITasTravelReimburseService tasTravelReimburseService;

    /**
     * 个人借款单头表 服务
     */
    @Autowired
    IBorrowMoneyInfoService borrowInfoService;

    /**
     * 个人借款单行表 服务
     */
    @Autowired
    IBorrowMoneyLineService borrowMoneyLineService;

    /**
     * 合同预付款头表 服务
     */
    @Autowired
    IAdvancePaymentInfoService advancePaymentInfoService;

    /**
     * 合同预付款头表 服务
     */
    @Autowired
    public IContactDetailService bmContactDetailService;


    /**
     * 合同报账单头表 服务
     */
    @Autowired
    ICrbContractReimburseBillService contractReimburseBillService;

    /**
     * 通用报账单 服务
     */
    @Autowired
    IGeGeneralExpenseService generalExpenseService;

    /**
     * 报账单关联预付款/借款 服务
     */
    @Autowired
    IGeExpenseBorrowPrepayService prepayService;

    /**
     * 劳务费报账单 服务
     */
    @Autowired
    ILcLaborCostService lcLaborCostService;

    /**
     * 还款单 服务
     */
    @Autowired
    IPepaymentOrderInfoService repayOrderInfo;

    /**
     * 检查和更新预算科目的额度
     * @param budgetAmount
     * @param borrowAmount
     * @param forbidFlag
     * @param warningFlag
     * @return
     */
    protected BudgetAmount checkAndUpdateAccountBudget(BudgetAmount budgetAmount, BigDecimal borrowAmount,
                                                       boolean forbidFlag, boolean warningFlag){
        BudgetAmount newAccountBudgetAmount = new BudgetAmount();
        BigDecimal newRemainAmount = budgetAmount.getBudgetRemainAmount().add(borrowAmount);
        BigDecimal newOccupyAmount = budgetAmount.getBudgetOccupyAmount();
        BigDecimal newRemainAndOccupyAmount = newRemainAmount.add(newOccupyAmount);
        if (forbidFlag && newRemainAndOccupyAmount.compareTo(budgetAmount.getBudgetAmount()) > 0) {
            throw new FSSCException(FsscErrorType.BUDGET_MORE_THAN_100_PERCENT);
        }
        BigDecimal budgetAmount80Percent = budgetAmount.getBudgetAmount().multiply(new BigDecimal(0.8d))
                .setScale(2, RoundingMode.HALF_UP);
        if (warningFlag && newRemainAndOccupyAmount.compareTo(budgetAmount80Percent) > 0) {
            throw new FSSCException(FsscErrorType.BUDGET_MORE_THAN_80_PERCENT);
        }
        BigDecimal newUsableAmount = budgetAmount.getBudgetAmount().subtract(newRemainAmount)
                .subtract(newOccupyAmount);
        newAccountBudgetAmount.setBudgetAmount(budgetAmount.getBudgetAmount());
        newAccountBudgetAmount.setBudgetRemainAmount(newRemainAmount);
        newAccountBudgetAmount.setBudgetOccupyAmount(newOccupyAmount);
        newAccountBudgetAmount.setBudgetUsableAmount(newUsableAmount);
        return newAccountBudgetAmount;
    }


    /**
     * 检查和更新 报账单的预算科目预算额度(关联借款单/预付款的单据)
     * @param accountBudgetAmount 科目预算额度
     * @param advanceBorrowMap  关联预算-事前借款信息
     * @param borrowPrepayList  关联借款单/预付款信息
     * @param expenseAmount 报账金额
     * @param forbidFlag
     * @param warningFlag
     * @return
     */
    protected BudgetAmount checkAndUpdateExpenseAccountBudget(BudgetAmount accountBudgetAmount,
                                                              Map<String, BudgetAdvanceBorrow> advanceBorrowMap,
                                                              List<GeExpenseBorrowPrepay> borrowPrepayList,
                                                              BigDecimal expenseAmount,boolean forbidFlag,boolean warningFlag){
        BudgetAmount newAccountBudgetAmount = new BudgetAmount();
        BigDecimal newRemainAmount;
        //如果有关联借款单/预付款
        if (CollectionUtils.isNotEmpty(advanceBorrowMap.keySet())) {
            //借款单/预付款单核销总金额
            BigDecimal verificationSum = borrowPrepayList.stream()
                    .map(GeExpenseBorrowPrepay::
                            getCurrentVerAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal moreThanAmount = expenseAmount.subtract(verificationSum);
            if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0) {
                //如果报账单总金额大于核销金额,报账单将使用新的预算保留额度
                newRemainAmount = accountBudgetAmount.getBudgetRemainAmount().add(moreThanAmount);
            } else {
                //如果报账单总金额等于核销金额,报账单不使用新的预算额度
                newRemainAmount = accountBudgetAmount.getBudgetRemainAmount();
            }
        } else {
            //借款单和预付款单都不关联，直接使用对应预算科目的预算保留金额
            newRemainAmount = accountBudgetAmount.getBudgetRemainAmount()
                    .add(expenseAmount);
        }
        BigDecimal newOccupyAmount = accountBudgetAmount.getBudgetOccupyAmount();
        BigDecimal newRemainAndOccupyAmount = newRemainAmount.add(newOccupyAmount);
        if (forbidFlag
                && newRemainAndOccupyAmount.compareTo(accountBudgetAmount.getBudgetAmount())
                > 0) {
            throw new FSSCException(FsscErrorType.BUDGET_MORE_THAN_100_PERCENT);
        }
        BigDecimal budgetAmount80Percent = accountBudgetAmount.getBudgetAmount()
                .multiply(new BigDecimal(0.8d))
                .setScale(2, RoundingMode.HALF_UP);
        if (warningFlag && newRemainAndOccupyAmount.compareTo(budgetAmount80Percent) > 0) {
            throw new FSSCException(FsscErrorType.BUDGET_MORE_THAN_80_PERCENT);
        }
        BigDecimal newUsableAmount = accountBudgetAmount.getBudgetAmount().subtract(newRemainAmount)
                .subtract(newOccupyAmount);
        newAccountBudgetAmount.setBudgetAmount(accountBudgetAmount.getBudgetAmount());
        newAccountBudgetAmount.setBudgetRemainAmount(newRemainAmount);
        newAccountBudgetAmount.setBudgetOccupyAmount(newOccupyAmount);
        newAccountBudgetAmount.setBudgetUsableAmount(newUsableAmount);
        return newAccountBudgetAmount;
    }


    protected BudgetAfterExpense constructAfterExpense(Long documentId,String documentType,BigDecimal expenseAmount,
                                                       Long expenseMainTypeId,
                                                       BaseBudgetAccount budgetAccount) {
        BudgetAfterExpense afterExpense = new BudgetAfterExpense();
        afterExpense.setDocumentId(documentId);
        afterExpense.setDocumentType(documentType);
        afterExpense.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_VALID.getValue());
        afterExpense.setBasicBudgetAccountId(budgetAccount.getId());
        afterExpense.setMainTypeId(expenseMainTypeId);
        afterExpense.setExpenseAmount(expenseAmount);
        afterExpense.setBudgetRemainAmount(expenseAmount);
        afterExpense.setBudgetOccupyAmount(BigDecimal.ZERO);
        return afterExpense;
    }

    protected BudgetAfterExpenseLine constructAfterExpenseLine(GeExpenseBorrowPrepay borrowPrepay,
                                                               BudgetAdvanceBorrowLine advanceBorrowLine) {
        BudgetAfterExpenseLine afterExpenseLine = new BudgetAfterExpenseLine();
        afterExpenseLine.setAdvanceBorrowLineId(advanceBorrowLine.getId());
        afterExpenseLine.setDocumentId(borrowPrepay.getDocumentId());
        afterExpenseLine.setDocumentType(borrowPrepay.getDocumentType());
        afterExpenseLine.setVerificationAmount(borrowPrepay.getVerficatedAmount());
        afterExpenseLine.setBudgetRemainAmount(borrowPrepay.getCurrentVerAmount());
        afterExpenseLine.setBudgetOccupyAmount(BigDecimal.ZERO);
        afterExpenseLine.setSubTypeId(borrowPrepay.getSubTypeId());
        afterExpenseLine.setLineNumber(borrowPrepay.getLineNumber());
        return afterExpenseLine;
    }


    /**
     * 还原事前借款的预算保留额度
     * @param advanceApplyFor
     * @param restoreAmount
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void advanceApplyForRestore(BudgetAdvanceApplyFor advanceApplyFor,BigDecimal restoreAmount) {
        BigDecimal newUsedRemainAmount = advanceApplyFor.getUsedRemainAmount().subtract(restoreAmount);
        BigDecimal newUsableRemainAmount = advanceApplyFor.getBudgetRemainAmount().subtract(newUsedRemainAmount);
        advanceApplyFor.setUsedRemainAmount(newUsedRemainAmount);
        advanceApplyFor.setUsableRemainAmount(newUsableRemainAmount);
        advanceApplyForService.updateById(advanceApplyFor);
    }


    /**
     * 还原事前借款的预算保留额度
     * @param restoreBorrowMap
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void advanceBorrowRestore(Map<Long, BigDecimal> restoreBorrowMap) {
        for (Map.Entry<Long,BigDecimal> entry : restoreBorrowMap.entrySet()){
            BudgetAdvanceBorrow advanceBorrow = advanceBorrowService
                    .getById(entry.getKey());
            //单据行的保留额度合计还原到借款单/预付款的头预算保留额度
            BigDecimal borrowUsedRemainAmount = advanceBorrow.getUsedRemainAmount()
                    .subtract(entry.getValue());
            BigDecimal borrowUsableRemainAmount = advanceBorrow.getUsableRemainAmount()
                    .add(entry.getValue());
            advanceBorrow.setUsedRemainAmount(borrowUsedRemainAmount);
            advanceBorrow.setUsableRemainAmount(borrowUsableRemainAmount);
            advanceBorrowService.updateById(advanceBorrow);
        }
    }

    /**
     * 预算科目的保留额度释放
     * @param freeAmount
     * @param accountBudgetAmount
     * @return
     */
    protected BudgetAmount accountBudgetRemainFree(BigDecimal freeAmount,BudgetAmount accountBudgetAmount){
        //报账金额超过借款单/预付款的部分保留额度 释放
        BigDecimal newRemainAmount = accountBudgetAmount.getBudgetRemainAmount().subtract(freeAmount);
        BigDecimal newOccupyAmount = accountBudgetAmount.getBudgetOccupyAmount();
        BigDecimal newRemainAndOccupyAmount = newRemainAmount.add(newOccupyAmount);
        BigDecimal newUsableAmount = accountBudgetAmount.getBudgetAmount().subtract(newRemainAndOccupyAmount);
        BudgetAmount budgetAmount = new BudgetAmount();
        budgetAmount.setBudgetRemainAmount(newRemainAmount);
        budgetAmount.setBudgetOccupyAmount(newOccupyAmount);
        budgetAmount.setBudgetUsableAmount(newUsableAmount);
        budgetAmount.setBudgetAmount(accountBudgetAmount.getBudgetAmount());
        return budgetAmount;
    }

    /**
     *  预算科目的占用额度释放
     * @param freeAmount
     * @param accountBudgetAmount
     * @return
     */
    protected BudgetAmount accountBudgetOccupyFree(BigDecimal freeAmount,BudgetAmount accountBudgetAmount){
        //报账金额超过借款单/预付款的部分保留额度 释放
        BigDecimal newRemainAmount = accountBudgetAmount.getBudgetRemainAmount();
        BigDecimal newOccupyAmount = accountBudgetAmount.getBudgetOccupyAmount().subtract(freeAmount);
        BigDecimal newRemainAndOccupyAmount = newRemainAmount.add(newOccupyAmount);
        BigDecimal newUsableAmount = accountBudgetAmount.getBudgetAmount().subtract(newRemainAndOccupyAmount);
        BudgetAmount budgetAmount = new BudgetAmount();
        budgetAmount.setBudgetRemainAmount(newRemainAmount);
        budgetAmount.setBudgetOccupyAmount(newOccupyAmount);
        budgetAmount.setBudgetUsableAmount(newUsableAmount);
        budgetAmount.setBudgetAmount(accountBudgetAmount.getBudgetAmount());
        return budgetAmount;
    }

    /**
     * 预算科目的保留变占用
     * @param amount 转移的额度
     * @param accountBudgetAmount 科目的预算金额
     * @return
     */
    protected BudgetAmount accountRemain2Occupy(BigDecimal amount,BudgetAmount accountBudgetAmount){
        BigDecimal newRemainAmount = accountBudgetAmount.getBudgetRemainAmount().subtract(amount);
        BigDecimal newOccupyAmount = accountBudgetAmount.getBudgetOccupyAmount().add(amount);
        BigDecimal newUsableAmount = accountBudgetAmount.getBudgetUsableAmount();
        BudgetAmount budgetAmount = new BudgetAmount();
        budgetAmount.setBudgetRemainAmount(newRemainAmount);
        budgetAmount.setBudgetOccupyAmount(newOccupyAmount);
        budgetAmount.setBudgetUsableAmount(newUsableAmount);
        budgetAmount.setBudgetAmount(accountBudgetAmount.getBudgetAmount());
        return budgetAmount;
    }

    /**
     * 预算科目的占用变保留
     * @param amount 转移的额度
     * @param accountBudgetAmount 科目的预算金额
     * @return
     */
    protected BudgetAmount accountOccupy2Remain(BigDecimal amount,BudgetAmount accountBudgetAmount){
        BigDecimal newRemainAmount = accountBudgetAmount.getBudgetRemainAmount().add(amount);
        BigDecimal newOccupyAmount = accountBudgetAmount.getBudgetOccupyAmount().subtract(amount);
        BigDecimal newUsableAmount = accountBudgetAmount.getBudgetUsableAmount();
        BudgetAmount budgetAmount = new BudgetAmount();
        budgetAmount.setBudgetRemainAmount(newRemainAmount);
        budgetAmount.setBudgetOccupyAmount(newOccupyAmount);
        budgetAmount.setBudgetUsableAmount(newUsableAmount);
        budgetAmount.setBudgetAmount(accountBudgetAmount.getBudgetAmount());
        return budgetAmount;
    }
}
