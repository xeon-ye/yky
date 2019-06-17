package com.deloitte.services.fssc.budget.service.impl;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.deloitte.services.fssc.budget.entity.*;
import com.deloitte.services.fssc.budget.service.IBudgetBorrowControlService;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyLine;
import com.deloitte.services.fssc.business.travle.entity.TasTravelApplyInfo;
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
public class BudgetBorrowControlServiceImpl extends BudgetBaseControlServiceImpl
        implements IBudgetBorrowControlService {


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean borrowBudgetControl(BorrowMoneyInfo borrowMoneyInfo, boolean warningFlag,
            boolean forbidFlag, boolean updateBudgetFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(borrowMoneyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = borrowMoneyInfo.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        //差旅申请
        TasTravelApplyInfo travelApplyInfo = null;
        //预算-事前申请
        BudgetAdvanceApplyFor advanceApplyFor = null;
        if (borrowMoneyInfo.getApplyForId() != null) {
            travelApplyInfo = travelApplyInfoService.getById(borrowMoneyInfo.getApplyForId());
            AssertUtils.asserts(travelApplyInfo != null, FsscErrorType.DOCUMENT_APPLY_FOR_IS_EMPTY);
            advanceApplyFor = advanceApplyForService.getByDocument(borrowMoneyInfo.getApplyForId(),
                    FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
            AssertUtils.asserts(advanceApplyFor != null, FsscErrorType.BUDGET_APPLY_FOR_IS_EMPTY);
            AssertUtils.asserts(FsscEums.DOCUMENT_BUDGET_STATUS_VALID.getValue().equals(advanceApplyFor.getDocumentStatus()),
                    FsscErrorType.BUDGET_APPLY_FOR_IS_INVALID);
            //检查支出大类是否一致
            AssertUtils.asserts(expenseMainTypeId.equals(travelApplyInfo.getMainTypeId()),
                    FsscErrorType.BORROW_MAIN_TYPE_NOT_MATCHING_APPLY);
            AssertUtils.asserts(borrowMoneyInfo.getBorrowAmount().compareTo(
                    advanceApplyFor.getApplyForAmount()) <= 0,
                    FsscErrorType.BUDGET_BORROW_MORE_THAN_APPLY);
        }
        AssertUtils.asserts(borrowMoneyInfo.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (borrowMoneyInfo.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(borrowMoneyInfo.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        //预算-事前借款
        BudgetAdvanceBorrow budgetAdvanceBorrow = advanceBorrowService.getByDocument(borrowMoneyInfo.getId(),
                FsscEums.TABLE_BORROW_DOCUMENT.getValue());
        if (budgetAdvanceBorrow != null){
            throw new FSSCException(FsscErrorType.BUDGET_IS_EXISTS);
        }
        budgetAdvanceBorrow = new BudgetAdvanceBorrow();
        budgetAdvanceBorrow.setDocumentId(borrowMoneyInfo.getId());
        budgetAdvanceBorrow.setDocumentType(FsscEums.TABLE_BORROW_DOCUMENT.getValue());
        budgetAdvanceBorrow.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_VALID.getValue());
        budgetAdvanceBorrow.setMainTypeId(expenseMainTypeId);
        budgetAdvanceBorrow.setBorrowAmount(borrowMoneyInfo.getBorrowAmount());
        budgetAdvanceBorrow.setBudgetRemainAmount(borrowMoneyInfo.getBorrowAmount());
        budgetAdvanceBorrow.setUsedRemainAmount(BigDecimal.ZERO);
        budgetAdvanceBorrow.setUsableRemainAmount(borrowMoneyInfo.getBorrowAmount());
        if (borrowMoneyInfo.getApplyForId() != null) {
            budgetAdvanceBorrow.setAdvanceApplyForId(borrowMoneyInfo.getApplyForId());
            budgetAdvanceBorrow.setUseApplyForAmount(borrowMoneyInfo.getBorrowAmount());
        }
        if (borrowMoneyInfo.getProjectId() != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(borrowMoneyInfo.getUnitCode(),
                            borrowMoneyInfo.getProjectId(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},项目{},预算科目{},年度{}",borrowMoneyInfo.getUnitCode(),
                    borrowMoneyInfo.getProjectId(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            budgetAdvanceBorrow.setBudgetType(FsscEums.BUDGET_TYPE_PROJECT.getValue());
            budgetAdvanceBorrow.setProjectBudgetAccountId(budgetAccount.getId());
            budgetAdvanceBorrow.setProjectId(borrowMoneyInfo.getProjectId());
            budgetAdvanceBorrow.setBudgetType(FsscEums.BUDGET_TYPE_PROJECT.getValue());
            //预算保留
            if (advanceApplyFor != null) {
                //事前申请的已用保留额度增加借款单的总金额
                BigDecimal newUsedRemainAmount = advanceApplyFor.getUsedRemainAmount().add(
                        borrowMoneyInfo.getBorrowAmount());
                BigDecimal newUsableRemainAmount = advanceApplyFor.getApplyForAmount().subtract(newUsedRemainAmount);
                if (newUsableRemainAmount.compareTo(BigDecimal.ZERO) < 0){
                    throw new FSSCException(FsscErrorType.BUDGET_BORROW_MORE_THAN_APPLY2);
                }
                advanceApplyFor.setUsedRemainAmount(newUsedRemainAmount);
                advanceApplyFor.setUsableRemainAmount(newUsableRemainAmount);
                if (updateBudgetFlag) {
                    advanceApplyForService.updateById(advanceApplyFor);
                }
            } else {
                //未关联事前申请的借款单，使用科目新的预算保留额度和更新预算数据
                BudgetAmount updatedAmount = this.checkAndUpdateAccountBudget(projectBudget.getAmount(),
                        borrowMoneyInfo.getBorrowAmount(), forbidFlag,warningFlag);
                if (updateBudgetFlag) {
                    projectBudgetService.updateProjectBudget(updatedAmount,projectBudget);
                }
             }
        } else {
            // 基本预算
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(borrowMoneyInfo.getUnitCode(),
                            borrowMoneyInfo.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},部门{},预算科目{},年度{}",borrowMoneyInfo.getUnitCode(),
                    borrowMoneyInfo.getDeptCode(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            budgetAdvanceBorrow.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
            budgetAdvanceBorrow.setBasicBudgetAccountId(budgetAccount.getId());
            budgetAdvanceBorrow.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
            if (advanceApplyFor != null) {
                //把借款单的总金额添加到事前申请的已用保留额度
                BigDecimal newUsedRemainAmount = advanceApplyFor.getUsedRemainAmount().add(
                        borrowMoneyInfo.getBorrowAmount());
                //更新事前申请的可用保留额度
                BigDecimal newUsableRemainAmount = advanceApplyFor.getApplyForAmount().subtract(newUsedRemainAmount);
                if (newUsableRemainAmount.compareTo(BigDecimal.ZERO) < 0){
                    throw new FSSCException(FsscErrorType.BUDGET_BORROW_MORE_THAN_APPLY2);
                }
                advanceApplyFor.setUsedRemainAmount(newUsedRemainAmount);
                advanceApplyFor.setUsableRemainAmount(newUsableRemainAmount);
                if (updateBudgetFlag) {
                    advanceApplyForService.updateById(advanceApplyFor);
                }
            } else {
                //未关联事前申请的借款单，才会使用新的预算保留额度和更新预算数据
                BudgetAmount updatedAmount = this.checkAndUpdateAccountBudget(basicBudget.getAmount(),
                        borrowMoneyInfo.getBorrowAmount(), forbidFlag,warningFlag);
                if (updateBudgetFlag) {
                    basicBudgetService.updateBasicBudget(updatedAmount, basicBudget);
                }
            }
        }
        if (updateBudgetFlag) {
            List<BorrowMoneyLine> borrowMoneyLineList = borrowMoneyLineService
                    .selectList(borrowMoneyInfo.getId());
            AssertUtils.asserts(CollectionUtils.isNotEmpty(borrowMoneyLineList),
                    FsscErrorType.BORROW_LINE_DATA_NOT_FOUND);
            advanceBorrowService.saveOrUpdate(budgetAdvanceBorrow);
            List<BudgetAdvanceBorrowLine> lineBudgetList = new ArrayList<>(borrowMoneyLineList.size());
            for (BorrowMoneyLine borrowMoneyLine : borrowMoneyLineList){
                AssertUtils.asserts(borrowMoneyLine.getBorrowAmount() != null,FsscErrorType.BORROW_LINE_AMOUNT_IS_EMPTY);
                AssertUtils.asserts(borrowMoneyLine.getLineNumber() != null,FsscErrorType.LINE_NUMBER_IS_EMPTY);
                BudgetAdvanceBorrowLine lineBudget = new BudgetAdvanceBorrowLine();
                lineBudget.setAdvanceBorrowId(budgetAdvanceBorrow.getId());
                lineBudget.setDocumentId(borrowMoneyInfo.getId());
                lineBudget.setDocumentType(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
                lineBudget.setBorrowAmount(borrowMoneyLine.getBorrowAmount());
                lineBudget.setBudgetRemainAmount(borrowMoneyLine.getBorrowAmount());
                lineBudget.setSubTypeId(borrowMoneyLine.getSubTypeId());
                lineBudget.setUsableRemainAmount(borrowMoneyLine.getBorrowAmount());
                lineBudget.setUsedRemainAmount(BigDecimal.ZERO);
                lineBudget.setLineNumber(borrowMoneyLine.getLineNumber());
                lineBudgetList.add(lineBudget);
            }
            advanceBorrowLineService.saveBatch(lineBudgetList);
        }
        return true;
    }

    @Override
    public boolean borrowRemain2Occupy(BorrowMoneyInfo borrowMoneyInfo, boolean forbidFlag) {
        //借款单不会保留变占用
        return true;
    }

    @Override
    public boolean borrowBudgetFree(FsscEums budgetPhase, BorrowMoneyInfo borrowMoneyInfo) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService
                .getDocTypeByFunction(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue(),
                        deptVo.getDeptCode());
        AssertUtils.asserts(borrowMoneyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        //差旅申请
        TasTravelApplyInfo travelApplyInfo;
        //预算-事前申请
        BudgetAdvanceApplyFor advanceApplyFor = null;
        if (borrowMoneyInfo.getApplyForId() != null) {
            travelApplyInfo = travelApplyInfoService.getById(borrowMoneyInfo.getApplyForId());
            AssertUtils.asserts(travelApplyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
            advanceApplyFor = advanceApplyForService.getByDocument(borrowMoneyInfo.getApplyForId(),
                    FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
        }
        Long expenseMainTypeId = borrowMoneyInfo.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(borrowMoneyInfo.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (borrowMoneyInfo.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(borrowMoneyInfo.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        Long budgetProjectId = borrowMoneyInfo.getProjectId();
        BudgetAdvanceBorrow advanceBorrow = advanceBorrowService.getByDocument(borrowMoneyInfo.getId(),
                FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
        if (advanceBorrow == null){
            throw new FSSCException(FsscErrorType.BUDGET_BORROW_NOT_FOUND);
        }
        BudgetAmount revertResult;
        if (budgetProjectId != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(borrowMoneyInfo.getUnitCode(),
                            budgetProjectId, budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                    borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            if (advanceApplyFor != null){
                //已关联事前申请单
                if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)){
                    //单据审批完成后的冲销

                    //把借款单的未使用的预算额度还原事前申请单的预算额度上
                    BigDecimal newUsedRemainAmount = advanceApplyFor.getUsedRemainAmount().subtract(
                            advanceBorrow.getUsableRemainAmount());
                    //更新事前申请单的可用预算额度
                    BigDecimal newUsableRemainAmount = advanceApplyFor.getBudgetRemainAmount().subtract(newUsedRemainAmount);
                    advanceApplyFor.setUsedRemainAmount(newUsedRemainAmount);
                    advanceApplyFor.setUsableRemainAmount(newUsableRemainAmount);
                    advanceApplyForService.updateById(advanceApplyFor);
                    advanceBorrow.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                    advanceBorrowService.updateById(advanceBorrow);
                }else{
                    //单据未审批完成的预算释放

                    //把借款单的预算额度还原事前申请单的预算额度上,删除事前借款及行的预算数据
                    BigDecimal newUsedRemainAmount = advanceApplyFor.getUsedRemainAmount().subtract(
                            advanceBorrow.getUseApplyForAmount());
                    BigDecimal newUsableRemainAmount = advanceApplyFor.getBudgetRemainAmount().subtract(newUsedRemainAmount);
                    advanceApplyFor.setUsedRemainAmount(newUsedRemainAmount);
                    advanceApplyFor.setUsableRemainAmount(newUsableRemainAmount);
                    advanceApplyForService.updateById(advanceApplyFor);
                    advanceBorrowService.removeById(advanceBorrow.getId());
                    advanceBorrowLineService.deleteByDocument(borrowMoneyInfo.getId(),
                            FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
                }
            }else {
                if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                    //单据审批完成后的冲销

                    //项目预算保留额度释放掉预付款单的可用预算保留额度
                    revertResult = this.accountBudgetRemainFree(advanceBorrow.getUsableRemainAmount(),projectBudget.getAmount());
                    advanceBorrow.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                    advanceBorrowService.updateById(advanceBorrow);
                } else {
                    //单据未审批完成的预算释放

                    //项目预算保留额度释放掉借款单的总金额
                    revertResult = this.accountBudgetRemainFree(advanceBorrow.getBudgetRemainAmount(),projectBudget.getAmount());
                    advanceBorrowService.removeById(advanceBorrow.getId());
                    advanceBorrowLineService.deleteByDocument(borrowMoneyInfo.getId(),FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
                }
                projectBudgetService.updateProjectBudget(revertResult, projectBudget);
            }
        } else {
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(borrowMoneyInfo.getUnitCode(),
                            borrowMoneyInfo.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            if (advanceApplyFor != null){
                //已关联事前申请单
                if (FsscEums.BUDGET_PHASE_PROCESSED.getValue().equals(budgetPhase)){
                    //单据审批完成后的冲销

                    //把借款单的未使用的预算额度还原事前申请单的预算额度上
                    BigDecimal newUsedRemainAmount = advanceApplyFor.getUsedRemainAmount().subtract(
                            advanceBorrow.getUsableRemainAmount());
                    //更新事前申请单的可用预算额度
                    BigDecimal newUsableRemainAmount = advanceApplyFor.getBudgetRemainAmount().subtract(newUsedRemainAmount);
                    advanceApplyFor.setUsedRemainAmount(newUsedRemainAmount);
                    advanceApplyFor.setUsableRemainAmount(newUsableRemainAmount);
                    advanceApplyForService.updateById(advanceApplyFor);
                    advanceBorrow.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                    advanceBorrowService.updateById(advanceBorrow);
                }else{
                    //单据未审批完成的预算释放

                    //把借款单的预算额度还原事前申请单的预算额度上,删除事前借款及行的预算数据
                    BigDecimal newUsedRemainAmount = advanceApplyFor.getUsedRemainAmount().subtract(
                            advanceBorrow.getUseApplyForAmount());
                    BigDecimal newUsableRemainAmount = advanceApplyFor.getBudgetRemainAmount().subtract(newUsedRemainAmount);
                    advanceApplyFor.setUsedRemainAmount(newUsedRemainAmount);
                    advanceApplyFor.setUsableRemainAmount(newUsableRemainAmount);
                    advanceApplyForService.updateById(advanceApplyFor);
                    advanceBorrowService.removeById(advanceBorrow.getId());
                    advanceBorrowLineService.deleteByDocument(borrowMoneyInfo.getId(),
                            FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
                }
            }else {
                if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                    //单据审批完成后的冲销
                    revertResult = this.accountBudgetRemainFree(advanceBorrow.getUsableRemainAmount(),basicBudget.getAmount());
                    advanceBorrow.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                    advanceBorrowService.updateById(advanceBorrow);
                } else {
                    //单据未审批完成的预算释放
                    revertResult = this.accountBudgetRemainFree(advanceBorrow.getBudgetRemainAmount(),basicBudget.getAmount());
                    advanceBorrowService.removeById(advanceBorrow.getId());
                    advanceBorrowLineService.deleteByDocument(borrowMoneyInfo.getId(),FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue());
                }
                basicBudgetService.updateBasicBudget(revertResult, basicBudget);
            }

        }
        return true;
    }

}
