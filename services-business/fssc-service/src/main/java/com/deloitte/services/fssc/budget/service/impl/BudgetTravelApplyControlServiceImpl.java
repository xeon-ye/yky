package com.deloitte.services.fssc.budget.service.impl;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.deloitte.services.fssc.budget.entity.BudgetAdvanceApplyFor;
import com.deloitte.services.fssc.budget.entity.BudgetAmount;
import com.deloitte.services.fssc.budget.entity.BudgetBasicBudget;
import com.deloitte.services.fssc.budget.entity.BudgetProjectBudget;
import com.deloitte.services.fssc.budget.service.IBudgetTravelApplyControlService;
import com.deloitte.services.fssc.business.travle.entity.TasTravelApplyInfo;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
public class BudgetTravelApplyControlServiceImpl extends BudgetBaseControlServiceImpl
        implements IBudgetTravelApplyControlService{

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean travelApplyBudgetControl(TasTravelApplyInfo applyInfo, boolean warningFlag,
            boolean forbidFlag, boolean updateBudgetFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue(),deptVo.getDeptCode());
        AssertUtils.asserts(applyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue().equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long budgetProjectId = applyInfo.getProjectId();
        Long expenseMainTypeId = applyInfo.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(applyInfo.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (budgetProjectId == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(applyInfo.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue().equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        //预算 事前申请
        BudgetAdvanceApplyFor advanceApplyFor = advanceApplyForService.getByDocument(applyInfo.getId(),
                FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
        if (advanceApplyFor != null){
            throw new FSSCException(FsscErrorType.BUDGET_IS_EXISTS);
        }
        advanceApplyFor = new BudgetAdvanceApplyFor();
        advanceApplyFor.setDocumentId(applyInfo.getId());
        advanceApplyFor.setDocumentType(FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
        advanceApplyFor.setMainTypeId(expenseMainTypeId);
        if (budgetProjectId != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(applyInfo.getUnitCode(),
                            budgetProjectId, budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                    applyInfo.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},项目{},预算科目{},年度{}",applyInfo.getUnitCode(),
                    budgetProjectId, budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            applyInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            advanceApplyFor.setBudgetType(FsscEums.BUDGET_TYPE_PROJECT.getValue());
            advanceApplyFor.setProjectId(budgetProjectId);
            advanceApplyFor.setProjectBudgetAccountId(budgetAccount.getId());
            //预算保留
            BudgetAmount accountBudgetAmount = checkAndUpdateAccountBudget(projectBudget.getAmount(), applyInfo.getTotalSum(),
                    forbidFlag, warningFlag);
            if (updateBudgetFlag) {
                projectBudgetService.updateProjectBudget(accountBudgetAmount, projectBudget);
            }
        } else {
            // 基本预算
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(applyInfo.getUnitCode(),
                            applyInfo.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    applyInfo.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},部门{},预算科目{},年度{}",applyInfo.getUnitCode(),
                    applyInfo.getDeptCode(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            applyInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            advanceApplyFor.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
            advanceApplyFor.setBasicBudgetAccountId(budgetAccount.getId());
            //预算保留
            BudgetAmount accountBudgetAmount = checkAndUpdateAccountBudget(basicBudget.getAmount(), applyInfo.getTotalSum(),
                    forbidFlag, warningFlag);
            if (updateBudgetFlag) {
                basicBudgetService.updateBasicBudget(accountBudgetAmount, basicBudget);
            }
        }
        if (updateBudgetFlag) {
            advanceApplyFor.setApplyForAmount(applyInfo.getTotalSum());
            advanceApplyFor.setBudgetRemainAmount(applyInfo.getTotalSum());
            advanceApplyFor.setUsableRemainAmount(applyInfo.getTotalSum());
            advanceApplyFor.setUsedRemainAmount(BigDecimal.ZERO);
            advanceApplyFor.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_VALID.getValue());
            advanceApplyForService.save(advanceApplyFor);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean travelApplyRemain2Occupy(TasTravelApplyInfo applyInfo, boolean forbidFlag) {
        //差旅申请单,不会预算占用
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean travelApplyBudgetFree(FsscEums budgetPhase, TasTravelApplyInfo applyInfo) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService
                .getDocTypeByFunction(FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue(),deptVo.getDeptCode());
        AssertUtils.asserts(applyInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long budgetProjectId = applyInfo.getProjectId();
        Long expenseMainTypeId  = applyInfo.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(applyInfo.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (budgetProjectId == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(applyInfo.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue().equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        BudgetAdvanceApplyFor advanceApplyFor = advanceApplyForService.getByDocument(applyInfo.getId(),
                FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
        AssertUtils.asserts(advanceApplyFor != null, FsscErrorType.BUDGET_APPLY_FOR_IS_EMPTY);
        BudgetAmount accountBudgetAmount;
        if (budgetProjectId != null) {
            //项目预算
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(applyInfo.getUnitCode(),
                            budgetProjectId, budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                    applyInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                accountBudgetAmount =  accountBudgetRemainFree(advanceApplyFor.getUsableRemainAmount(),projectBudget.getAmount());
                advanceApplyFor.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                advanceApplyForService.updateById(advanceApplyFor);
            }else{
                accountBudgetAmount =  accountBudgetRemainFree(advanceApplyFor.getUsableRemainAmount(),projectBudget.getAmount());
                advanceApplyForService.removeById(advanceApplyFor.getId());
            }
            projectBudgetService.updateProjectBudget(accountBudgetAmount,projectBudget);
        } else {
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(applyInfo.getUnitCode(),
                            applyInfo.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    applyInfo.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                accountBudgetAmount =  accountBudgetRemainFree(advanceApplyFor.getUsableRemainAmount(),basicBudget.getAmount());
                advanceApplyFor.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                advanceApplyForService.updateById(advanceApplyFor);
            }else{
                accountBudgetAmount =  accountBudgetRemainFree(advanceApplyFor.getUsableRemainAmount(),basicBudget.getAmount());
                advanceApplyForService.removeById(advanceApplyFor.getId());
            }
            basicBudgetService.updateBasicBudget(accountBudgetAmount,basicBudget);
        }
        return true;
    }

}
