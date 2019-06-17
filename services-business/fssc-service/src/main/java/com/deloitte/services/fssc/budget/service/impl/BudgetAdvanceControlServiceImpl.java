package com.deloitte.services.fssc.budget.service.impl;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.deloitte.services.fssc.budget.entity.*;
import com.deloitte.services.fssc.budget.service.IBudgetAdvanceControlService;
import com.deloitte.services.fssc.business.advance.entity.AdvancePaymentInfo;
import com.deloitte.services.fssc.business.advance.entity.ContactDetail;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BudgetAdvanceControlServiceImpl  extends BudgetBaseControlServiceImpl
        implements IBudgetAdvanceControlService{


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean advanceBudgetControl(AdvancePaymentInfo advancePaymentInfo,
                                        boolean warningFlag, boolean forbidFlag,
                                        boolean updateBudgetFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue(),deptVo.getDeptCode());
        AssertUtils.asserts(advancePaymentInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue().equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = advancePaymentInfo.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(advancePaymentInfo.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (advancePaymentInfo.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(advancePaymentInfo.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        //预算-事前借款
        BudgetAdvanceBorrow budgetAdvanceBorrow = advanceBorrowService.getByDocument(advancePaymentInfo.getId(),
                FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
        if (budgetAdvanceBorrow != null){
            throw new FSSCException(FsscErrorType.BUDGET_IS_EXISTS);
        }
        budgetAdvanceBorrow = new BudgetAdvanceBorrow();
        budgetAdvanceBorrow.setDocumentId(advancePaymentInfo.getId());
        budgetAdvanceBorrow.setDocumentType(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
        budgetAdvanceBorrow.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_VALID.getValue());
        budgetAdvanceBorrow.setMainTypeId(expenseMainTypeId);
        budgetAdvanceBorrow.setBorrowAmount(advancePaymentInfo.getTotalSum());
        budgetAdvanceBorrow.setBudgetRemainAmount(advancePaymentInfo.getTotalSum());
        budgetAdvanceBorrow.setUsedRemainAmount(BigDecimal.ZERO);
        budgetAdvanceBorrow.setUsableRemainAmount(advancePaymentInfo.getTotalSum());
        Long budgetProjectId = advancePaymentInfo.getProjectId();
        if (budgetProjectId != null) {
            //项目预算
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(advancePaymentInfo.getUnitCode(),
                            budgetProjectId, budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                    advancePaymentInfo.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},项目{},预算科目{},年度{}",advancePaymentInfo.getUnitCode(),
                    advancePaymentInfo.getProjectId(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            advancePaymentInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            budgetAdvanceBorrow.setProjectBudgetAccountId(budgetAccount.getId());
            budgetAdvanceBorrow.setProjectId(advancePaymentInfo.getProjectId());
            budgetAdvanceBorrow.setBudgetType(FsscEums.BUDGET_TYPE_PROJECT.getValue());
            BudgetAmount updatedAmount = this.checkAndUpdateAccountBudget(projectBudget.getAmount(),advancePaymentInfo.getTotalSum(),
                    forbidFlag,warningFlag);
            if (updateBudgetFlag) {
                projectBudgetService.updateProjectBudget(updatedAmount,projectBudget);
            }
        } else {
            // 基本预算
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(advancePaymentInfo.getUnitCode(),
                            advancePaymentInfo.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    advancePaymentInfo.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},部门{},预算科目{},年度{}",advancePaymentInfo.getUnitCode(),
                    advancePaymentInfo.getDeptCode(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            advancePaymentInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            budgetAdvanceBorrow.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
            budgetAdvanceBorrow.setBasicBudgetAccountId(budgetAccount.getId());
            BudgetAmount updatedAmount = this.checkAndUpdateAccountBudget(basicBudget.getAmount(),advancePaymentInfo.getTotalSum(),
                    forbidFlag,warningFlag);
            if (updateBudgetFlag) {
                basicBudgetService.updateBasicBudget(updatedAmount,basicBudget);
            }
        }
        if (updateBudgetFlag) {
            List<ContactDetail> contactDetailList = bmContactDetailService
                    .selectList(advancePaymentInfo.getId());
            AssertUtils.asserts(CollectionUtils.isNotEmpty(contactDetailList),
                    FsscErrorType.ADVANCE_PAY_LINE_DATA_NOT_FOUND);
            advanceBorrowService.saveOrUpdate(budgetAdvanceBorrow);
            List<BudgetAdvanceBorrowLine> lineBudgetList = new ArrayList<>(contactDetailList.size());
            for (ContactDetail contactDetail : contactDetailList){
                AssertUtils.asserts(contactDetail.getActualPlayAmount() != null,FsscErrorType.ACTUAL_PLAY_AMOUNT_IS_EMPTY);
                AssertUtils.asserts(contactDetail.getLineNumber() != null,FsscErrorType.LINE_NUMBER_IS_EMPTY);
                BudgetAdvanceBorrowLine lineBudget = new BudgetAdvanceBorrowLine();
                lineBudget.setAdvanceBorrowId(budgetAdvanceBorrow.getId());
                lineBudget.setDocumentId(advancePaymentInfo.getId());
                lineBudget.setDocumentType(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
                lineBudget.setBorrowAmount(contactDetail.getActualPlayAmount());
                lineBudget.setBudgetRemainAmount(contactDetail.getActualPlayAmount());
                lineBudget.setSubTypeId(contactDetail.getSubTypeId());
                lineBudget.setUsableRemainAmount(contactDetail.getActualPlayAmount());
                lineBudget.setUsedRemainAmount(BigDecimal.ZERO);
                lineBudget.setLineNumber(contactDetail.getLineNumber());
                lineBudgetList.add(lineBudget);
            }
            advanceBorrowLineService.saveBatch(lineBudgetList);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean advanceRemain2Occupy(AdvancePaymentInfo advancePaymentInfo, boolean forbidFlag) {
        //对公预付款不会保留变占用
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean advanceBudgetFree(FsscEums budgetPhase,AdvancePaymentInfo advancePaymentInfo) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService
                .getDocTypeByFunction(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue(),deptVo.getDeptCode());
        AssertUtils.asserts(advancePaymentInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = advancePaymentInfo.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(advancePaymentInfo.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (advancePaymentInfo.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(advancePaymentInfo.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue().equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        Long budgetProjectId = advancePaymentInfo.getProjectId();
        BudgetAdvanceBorrow advanceBorrow = advanceBorrowService.getByDocument(advancePaymentInfo.getId(),
                FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
        if (advanceBorrow == null){
            throw new FSSCException(FsscErrorType.BUDGET_BORROW_NOT_FOUND);
        }
        BudgetAmount revertResult;
        if (budgetProjectId != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(advancePaymentInfo.getUnitCode(),
                            budgetProjectId, budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                    advancePaymentInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                //审批完成后的冲销

                //项目预算保留额度释放掉预付款单的可用预算保留额度
                revertResult = this.accountBudgetRemainFree(advanceBorrow.getUsableRemainAmount(),projectBudget.getAmount());
                advanceBorrow.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                advanceBorrowService.updateById(advanceBorrow);
            } else {
                //未审批完成的预算释放

                //项目预算保留额度释放掉预付款单的总金额
                revertResult = this.accountBudgetRemainFree(advanceBorrow.getUsableRemainAmount(),projectBudget.getAmount());
                advanceBorrowService.removeById(advanceBorrow.getId());
                advanceBorrowLineService.deleteByDocument(advanceBorrow.getId(),
                        FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            }
            projectBudgetService.updateProjectBudget(revertResult,projectBudget);
        } else {
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(advancePaymentInfo.getUnitCode(),
                            advancePaymentInfo.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    advancePaymentInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                //审批完成后的冲销
                revertResult = this.accountBudgetRemainFree(advanceBorrow.getUsableRemainAmount(),basicBudget.getAmount());
                advanceBorrow.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                advanceBorrowService.updateById(advanceBorrow);
            } else {
                //未审批完成的预算释放
                revertResult = this.accountBudgetRemainFree(advanceBorrow.getUsableRemainAmount(),basicBudget.getAmount());
                advanceBorrowService.removeById(advanceBorrow.getId());
                advanceBorrowLineService.deleteByDocument(advancePaymentInfo.getId(),
                        FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue());
            }
            basicBudgetService.updateBasicBudget(revertResult,basicBudget);
        }
        return true;
    }

}
