package com.deloitte.services.fssc.budget.service.impl;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.deloitte.services.fssc.budget.entity.BudgetAfterExpense;
import com.deloitte.services.fssc.budget.entity.BudgetAmount;
import com.deloitte.services.fssc.budget.entity.BudgetBasicBudget;
import com.deloitte.services.fssc.budget.entity.BudgetProjectBudget;
import com.deloitte.services.fssc.budget.service.IBudgetLaborCostControlService;
import com.deloitte.services.fssc.business.labor.entity.LcLaborCost;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class BudgetLaborCostControlServiceImpl extends BudgetBaseControlServiceImpl
        implements IBudgetLaborCostControlService {


    @Override
    public boolean laborCostBudgetControl(LcLaborCost laborCost, boolean warningFlag, boolean forbidFlag, boolean updateBudgetFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.LC_LABOR_COST.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(laborCost != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = laborCost.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(laborCost.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (laborCost.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(laborCost.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        //预算-事后报账
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(laborCost.getId(),
                FsscTableNameEums.LC_LABOR_COST.getValue());
        if (afterExpense != null){
            throw new FSSCException(FsscErrorType.BUDGET_IS_EXISTS);
        }
        afterExpense = this.constructAfterExpense(laborCost.getId(),FsscTableNameEums.LC_LABOR_COST.getValue(),
                        laborCost.getShouldGiveTotalAmount(), expenseMainTypeId, budgetAccount);
         BudgetAmount budgetAmount;
        if (laborCost.getProjectId() != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(laborCost.getUnitCode(),
                            laborCost.getProjectId(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    laborCost.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},项目{},预算科目{},年度{}",laborCost.getUnitCode(),
                    laborCost.getProjectId(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            laborCost.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            afterExpense.setBudgetType(FsscEums.BUDGET_TYPE_PROJECT.getValue());
            afterExpense.setProjectId(laborCost.getProjectId());
            afterExpense.setProjectBudgetAccountId(budgetAccount.getId());
            budgetAmount = checkAndUpdateAccountBudget(projectBudget.getAmount(),laborCost.getShouldGiveTotalAmount(),forbidFlag,warningFlag);
            if (updateBudgetFlag) {
                projectBudgetService.updateProjectBudget(budgetAmount, projectBudget);
            }
        } else {
            // 基本预算
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(laborCost.getUnitCode(),
                            laborCost.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    laborCost.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},部门{},预算科目{},年度{}",laborCost.getUnitCode(),
                    laborCost.getDeptCode(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            laborCost.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            afterExpense.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
            afterExpense.setBasicBudgetAccountId(budgetAccount.getId());
            budgetAmount = checkAndUpdateAccountBudget(basicBudget.getAmount(),laborCost.getShouldGiveTotalAmount(),forbidFlag,warningFlag);
            if (updateBudgetFlag) {
                basicBudgetService.updateBasicBudget(budgetAmount, basicBudget);
            }
        }
        afterExpenseService.save(afterExpense);
        return true;
    }

    @Override
    public boolean laborCostRemain2Occupy(LcLaborCost laborCost, boolean forbidFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.LC_LABOR_COST.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(laborCost != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = laborCost.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(laborCost.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (laborCost.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(laborCost.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(laborCost.getId(),
                FsscTableNameEums.LC_LABOR_COST.getValue());
        AssertUtils.asserts(afterExpense != null, FsscErrorType.BUDGET_AFTER_EXPENSE_NOT_FOUND);
        if (laborCost.getProjectId() != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(laborCost.getUnitCode(),
                            laborCost.getProjectId(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    laborCost.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            BudgetAmount budgetAmount = this.accountRemain2Occupy(laborCost.getShouldGiveTotalAmount(),projectBudget.getAmount());
            projectBudgetService.updateProjectBudget(budgetAmount, projectBudget);
        } else {
            // 基本预算
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(laborCost.getUnitCode(),
                            laborCost.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    laborCost.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            BudgetAmount budgetAmount = this.accountRemain2Occupy(laborCost.getShouldGiveTotalAmount(),basicBudget.getAmount());
            basicBudgetService.updateBasicBudget(budgetAmount, basicBudget);
        }
        //保留变占用
        afterExpense.setBudgetOccupyAmount(afterExpense.getBudgetRemainAmount());
        afterExpense.setBudgetRemainAmount(BigDecimal.ZERO);
        afterExpenseService.updateById(afterExpense);
        return true;
    }

    @Override
    public boolean laborCostBudgetFree(FsscEums budgetPhase, LcLaborCost laborCost) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService
                .getDocTypeByFunction(FsscTableNameEums.LC_LABOR_COST.getValue(),
                        deptVo.getDeptCode());
        AssertUtils.asserts(laborCost != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = laborCost.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(laborCost.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (laborCost.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(laborCost.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(laborCost.getId(),
                FsscTableNameEums.LC_LABOR_COST.getValue());
        AssertUtils.asserts(afterExpense != null, FsscErrorType.BUDGET_AFTER_EXPENSE_NOT_FOUND);
        Long budgetProjectId = laborCost.getProjectId();
        if (budgetProjectId != null) {
            //项目预算
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(laborCost.getUnitCode(),
                            budgetProjectId, budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                    laborCost.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                //预算占用 释放
                afterExpense.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                BudgetAmount convertAmount = this.accountBudgetOccupyFree(laborCost.getShouldGiveTotalAmount(),projectBudget.getAmount());
                projectBudgetService.updateProjectBudget(convertAmount,projectBudget);
                afterExpenseService.updateById(afterExpense);
            } else {
                //预算保留 释放
                BudgetAmount convertAmount = this.accountBudgetRemainFree(laborCost.getShouldGiveTotalAmount(),projectBudget.getAmount());
                projectBudgetService.updateProjectBudget(convertAmount,projectBudget);
                afterExpenseService.removeById(afterExpense.getId());
            }
        } else {
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(laborCost.getUnitCode(),
                            laborCost.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    laborCost.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                afterExpense.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                BudgetAmount convertAmount = this.accountBudgetOccupyFree(laborCost.getShouldGiveTotalAmount(),basicBudget.getAmount());
                basicBudgetService.updateBasicBudget(convertAmount,basicBudget);
                afterExpenseService.updateById(afterExpense);
            } else {
                BudgetAmount convertAmount = this.accountBudgetRemainFree(laborCost.getShouldGiveTotalAmount(),basicBudget.getAmount());;
                basicBudgetService.updateBasicBudget(convertAmount, basicBudget);
                afterExpenseService.removeById(afterExpense.getId());
            }
        }
        return true;
    }


}
