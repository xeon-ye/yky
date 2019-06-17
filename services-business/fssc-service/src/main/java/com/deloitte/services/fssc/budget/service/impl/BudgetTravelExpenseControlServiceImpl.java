package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.deloitte.services.fssc.budget.entity.*;
import com.deloitte.services.fssc.budget.service.IBudgetTravelExpenseControlService;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.travle.entity.TasTravelReimburse;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BudgetTravelExpenseControlServiceImpl extends BudgetBaseControlServiceImpl
        implements IBudgetTravelExpenseControlService {

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean travelExpenseBudgetControl(TasTravelReimburse travelReimburse, boolean warningFlag, boolean forbidFlag,
                                              boolean updateBudgetFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(travelReimburse != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = travelReimburse.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(travelReimburse.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (travelReimburse.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        } else {
            budgetAccount = budgetAccountService.getBudgetAccountByCode(travelReimburse.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        //预算-事后报账
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(travelReimburse.getId(),
                FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
        if (afterExpense != null) {
            throw new FSSCException(FsscErrorType.BUDGET_IS_EXISTS);
        }
        afterExpense = this
                .constructAfterExpense(travelReimburse.getId(), FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue(),
                        travelReimburse.getExpenseAmount(), expenseMainTypeId, budgetAccount);
        List<BudgetAfterExpenseLine> afterExpenseLineList = new ArrayList<>();
        BudgetAdvanceApplyFor advanceApplyFor = null;
        if (travelReimburse.getApplyForId() != null) {
            advanceApplyFor = advanceApplyForService.getByDocument(travelReimburse.getApplyForId(),
                    FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
        }
        //查询关联预付款或借款
        QueryWrapper<GeExpenseBorrowPrepay> prepayQueryWrapper = new QueryWrapper<>();
        prepayQueryWrapper.eq(GeExpenseBorrowPrepay.DOCUMENT_ID, travelReimburse.getId());
        List<GeExpenseBorrowPrepay> borrowPrepayList = prepayService.list(prepayQueryWrapper);
        Map<String, BudgetAdvanceBorrow> advanceBorrowMap = new HashMap<>(16);
        Map<BudgetAdvanceBorrow, List<BudgetAdvanceBorrowLine>> advanceBorrowListMap = new HashMap<>(
                16);
        if (CollectionUtils.isNotEmpty(borrowPrepayList)) {
            for (GeExpenseBorrowPrepay borrowPrepay : borrowPrepayList) {
                BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService
                        .getByLineNumber(borrowPrepay.getBorrowOrPrepayId(), borrowPrepay.getConnectDocumentType(),
                                borrowPrepay.getLineNumber());
                AssertUtils.asserts(borrowPrepay.getCurrentVerAmount() != null,FsscErrorType.VERIFICATION_AMOUNT_IS_EMPTY);
                BigDecimal newUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount()
                        .add(borrowPrepay.getCurrentVerAmount());
                BigDecimal newUsableRemainAmount = advanceBorrowLine.getBudgetRemainAmount()
                        .subtract(newUsedRemainAmount);
                if (newUsableRemainAmount.compareTo(BigDecimal.ZERO) < 0) {
                    throw new FSSCException(FsscErrorType.LINE_VER_AMOUNT_MORE_THAN_BUDGET);
                }
                //更新 预算-关联事前借款行的新保留额度信息
                advanceBorrowLine.setUsedRemainAmount(newUsedRemainAmount);
                advanceBorrowLine.setUsableRemainAmount(newUsableRemainAmount);
                //创建 事后报账行数据
                BudgetAfterExpenseLine afterExpenseLine = this
                        .constructAfterExpenseLine(borrowPrepay, advanceBorrowLine);
                afterExpenseLineList.add(afterExpenseLine);
                String advanceBorrowKey =
                        String.valueOf(borrowPrepay.getBorrowOrPrepayId()) + "$" + borrowPrepay.getConnectDocumentType();
                BigDecimal newBorrowUsedRemainAmount;
                BigDecimal newBorrowUsableRemainAmount;
                if (advanceBorrowMap.containsKey(advanceBorrowKey)) {
                    BudgetAdvanceBorrow advanceBorrow = advanceBorrowMap.get(advanceBorrowKey);
                    //更新 预算-关联的事前借款的新保留额度信息
                    newBorrowUsedRemainAmount = advanceBorrow.getUsedRemainAmount()
                            .add(borrowPrepay.getCurrentVerAmount());
                    newBorrowUsableRemainAmount = advanceBorrow.getUsableRemainAmount()
                            .subtract(borrowPrepay.getCurrentVerAmount());
                    advanceBorrow.setUsedRemainAmount(newBorrowUsedRemainAmount);
                    advanceBorrow.setUsableRemainAmount(newBorrowUsableRemainAmount);
                    List<BudgetAdvanceBorrowLine> advanceBorrowLineList = advanceBorrowListMap
                            .get(advanceBorrow);
                    advanceBorrowLineList.add(advanceBorrowLine);
                } else {
                    BudgetAdvanceBorrow advanceBorrow = advanceBorrowService
                            .getById(advanceBorrowLine.getAdvanceBorrowId());
                    newBorrowUsedRemainAmount = advanceBorrow.getUsedRemainAmount()
                            .add(borrowPrepay.getCurrentVerAmount());
                    newBorrowUsableRemainAmount = advanceBorrow.getUsableRemainAmount()
                            .subtract(borrowPrepay.getCurrentVerAmount());
                    advanceBorrow.setUsedRemainAmount(newBorrowUsedRemainAmount);
                    advanceBorrow.setUsableRemainAmount(newBorrowUsableRemainAmount);
                    advanceBorrowMap.put(advanceBorrowKey, advanceBorrow);
                    List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                    advanceBorrowLineList.add(advanceBorrowLine);
                    advanceBorrowListMap.put(advanceBorrow, advanceBorrowLineList);
                }
            }
        }
        //保留额
        BigDecimal newRemainAmount;
        //占用额
        BigDecimal newOccupyAmount;
        //保留+占用 额
        BigDecimal newRemainAndOccupyAmount;
        //预算80%
        BigDecimal budgetAmount80Percent;
        //可用额
        BigDecimal newUsableAmount;
        if (travelReimburse.getProjectId() != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(travelReimburse.getUnitCode(),
                            travelReimburse.getProjectId(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    travelReimburse.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},项目{},预算科目{},年度{}", travelReimburse.getUnitCode(),
                    travelReimburse.getProjectId(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            travelReimburse.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            afterExpense.setBudgetType(FsscEums.BUDGET_TYPE_PROJECT.getValue());
            afterExpense.setProjectId(travelReimburse.getProjectId());
            afterExpense.setProjectBudgetAccountId(budgetAccount.getId());
            //如果有关联借款单/预付款
            if (CollectionUtils.isNotEmpty(advanceBorrowMap.keySet())) {
                //借款单/预付款单核销总金额
                BigDecimal verificationSum = borrowPrepayList.stream()
                        .map(GeExpenseBorrowPrepay::
                                getCurrentVerAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal moreThanAmount = travelReimburse.getExpenseAmount().subtract(verificationSum);
                if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0) {
                    //如果报账单总金额大于核销金额
                    if (advanceApplyFor != null) {
                        //如果有关联差旅申请的报账单,无法核销的金额将使用差旅申请的预算额度
                        if (travelReimburse.getExpenseAmount().compareTo(advanceApplyFor.getApplyForAmount()) > 0) {
                            throw new FSSCException(FsscErrorType.BUDGET_EXPENSE_AMOUNT_MORE_THAN_APPLY_FOR);
                        }
                        BigDecimal newApplyUsedRemainAmount = advanceApplyFor.getUsedRemainAmount().add(moreThanAmount);
                        BigDecimal newApplyUsableRemainAmount = advanceApplyFor.getBudgetRemainAmount().subtract(newApplyUsedRemainAmount);
                        if (newApplyUsableRemainAmount.compareTo(BigDecimal.ZERO) < 0) {
                            throw new FSSCException(FsscErrorType.BUDGET_EXPENSE_AMOUNT_MORE_THAN_APPLY_FOR_USABLE);
                        }
                        advanceApplyFor.setUsedRemainAmount(newApplyUsedRemainAmount);
                        advanceApplyFor.setUsableRemainAmount(newApplyUsableRemainAmount);
                        afterExpense.setAdvanceApplyForId(travelReimburse.getApplyForId());
                        afterExpense.setUseApplyForAmount(moreThanAmount);
                        newRemainAmount = projectBudget.getBudgetRemainAmount();
                    } else {
                        //没有关联差旅申请,无法核销的金额将使用科目对应的预算额度
                        newRemainAmount = projectBudget.getBudgetRemainAmount().add(moreThanAmount);
                    }
                } else {
                    //如果报账单总金额等于核销金额,报账单不使用新的预算额度
                    newRemainAmount = projectBudget.getBudgetRemainAmount();
                }
            } else {
                //借款单和预付款单都不关联
                if (advanceApplyFor != null) {
                    //如果只关联差旅申请的报账单,将使用差旅申请的预算额度
                    if (travelReimburse.getExpenseAmount().compareTo(advanceApplyFor.getApplyForAmount()) > 0) {
                        throw new FSSCException(FsscErrorType.BUDGET_EXPENSE_AMOUNT_MORE_THAN_APPLY_FOR);
                    }
                    BigDecimal newApplyUsedRemainAmount = advanceApplyFor.getUsedRemainAmount().add(travelReimburse.getExpenseAmount());
                    BigDecimal newApplyUsableRemainAmount = advanceApplyFor.getBudgetRemainAmount().subtract(newApplyUsedRemainAmount);
                    if (newApplyUsableRemainAmount.compareTo(BigDecimal.ZERO) < 0) {
                        throw new FSSCException(FsscErrorType.BUDGET_EXPENSE_AMOUNT_MORE_THAN_APPLY_FOR_USABLE);
                    }
                    advanceApplyFor.setUsedRemainAmount(newApplyUsedRemainAmount);
                    advanceApplyFor.setUsableRemainAmount(newApplyUsableRemainAmount);
                    afterExpense.setAdvanceApplyForId(travelReimburse.getApplyForId());
                    afterExpense.setUseApplyForAmount(travelReimburse.getExpenseAmount());
                    newRemainAmount = projectBudget.getBudgetRemainAmount();
                } else {
                    // 直接使用对应预算科目的预算保留金额
                    newRemainAmount = projectBudget.getBudgetRemainAmount()
                            .add(travelReimburse.getExpenseAmount());
                }
            }
            newOccupyAmount = projectBudget.getBudgetOccupyAmount();
            newRemainAndOccupyAmount = newRemainAmount.add(newOccupyAmount);
            if (forbidFlag
                    && newRemainAndOccupyAmount.compareTo(projectBudget.getBudgetAmount())
                    > 0) {
                throw new FSSCException(FsscErrorType.BUDGET_MORE_THAN_100_PERCENT);
            }
            budgetAmount80Percent = projectBudget.getBudgetAmount()
                    .multiply(new BigDecimal(0.8d))
                    .setScale(2, RoundingMode.HALF_UP);
            if (warningFlag && newRemainAndOccupyAmount.compareTo(budgetAmount80Percent) > 0) {
                throw new FSSCException(FsscErrorType.BUDGET_MORE_THAN_80_PERCENT);
            }
            newUsableAmount = projectBudget.getBudgetAmount().subtract(newRemainAmount)
                    .subtract(newOccupyAmount);
            if (updateBudgetFlag) {
                projectBudgetService
                        .updateProjectBudget(newRemainAmount, newOccupyAmount, newUsableAmount,
                                projectBudget);
            }
        } else {
            // 基本预算
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(travelReimburse.getUnitCode(),
                            travelReimburse.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    travelReimburse.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},部门{},预算科目{},年度{}", travelReimburse.getUnitCode(),
                    travelReimburse.getDeptCode(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            travelReimburse.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            afterExpense.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
            afterExpense.setBasicBudgetAccountId(budgetAccount.getId());
            //如果有关联借款单/预付款
            if (CollectionUtils.isNotEmpty(advanceBorrowMap.keySet())) {
                //借款单/预付款单核销总金额
                BigDecimal verificationSum = borrowPrepayList.stream()
                        .map(GeExpenseBorrowPrepay::
                                getCurrentVerAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal moreThanAmount = travelReimburse.getExpenseAmount().subtract(verificationSum);
                if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0) {
                    //如果报账单总金额大于核销金额
                    if (advanceApplyFor != null) {
                        //如果关联差旅申请的报账单,无法核销的金额将使用差旅申请的预算额度
                        if (travelReimburse.getExpenseAmount().compareTo(advanceApplyFor.getApplyForAmount()) > 0) {
                            throw new FSSCException(FsscErrorType.BUDGET_EXPENSE_AMOUNT_MORE_THAN_APPLY_FOR);
                        }
                        BigDecimal newApplyUsedRemainAmount = advanceApplyFor.getUsedRemainAmount().add(moreThanAmount);
                        BigDecimal newApplyUsableRemainAmount = advanceApplyFor.getBudgetRemainAmount().subtract(newApplyUsedRemainAmount);
                        if (newApplyUsableRemainAmount.compareTo(BigDecimal.ZERO) < 0) {
                            throw new FSSCException(FsscErrorType.BUDGET_EXPENSE_AMOUNT_MORE_THAN_APPLY_FOR_USABLE);
                        }
                        advanceApplyFor.setUsedRemainAmount(newApplyUsedRemainAmount);
                        advanceApplyFor.setUsableRemainAmount(newApplyUsableRemainAmount);
                        afterExpense.setAdvanceApplyForId(travelReimburse.getApplyForId());
                        afterExpense.setUseApplyForAmount(moreThanAmount);
                        newRemainAmount = basicBudget.getBudgetRemainAmount();
                    } else {
                        //没有关联差旅申请,无法核销的金额将使用科目对应的预算额度
                        newRemainAmount = basicBudget.getBudgetRemainAmount().add(verificationSum);
                    }
                } else {
                    //如果报账单总金额等于核销金额,报账单不使用新的预算额度
                    newRemainAmount = basicBudget.getBudgetRemainAmount();
                }
            } else {
                //借款单和预付款单都不关联
                if (advanceApplyFor != null) {
                    //如果只关联差旅申请的报账单,将使用差旅申请的预算额度
                    if (travelReimburse.getExpenseAmount().compareTo(advanceApplyFor.getApplyForAmount()) > 0) {
                        throw new FSSCException(FsscErrorType.BUDGET_EXPENSE_AMOUNT_MORE_THAN_APPLY_FOR);
                    }
                    BigDecimal newApplyUsedRemainAmount = advanceApplyFor.getUsedRemainAmount().add(travelReimburse.getExpenseAmount());
                    BigDecimal newApplyUsableRemainAmount = advanceApplyFor.getBudgetRemainAmount().subtract(newApplyUsedRemainAmount);
                    if (newApplyUsableRemainAmount.compareTo(BigDecimal.ZERO) < 0) {
                        throw new FSSCException(FsscErrorType.BUDGET_EXPENSE_AMOUNT_MORE_THAN_APPLY_FOR_USABLE);
                    }
                    advanceApplyFor.setUsedRemainAmount(newApplyUsedRemainAmount);
                    advanceApplyFor.setUsableRemainAmount(newApplyUsableRemainAmount);
                    afterExpense.setAdvanceApplyForId(travelReimburse.getApplyForId());
                    afterExpense.setUseApplyForAmount(travelReimburse.getExpenseAmount());
                    newRemainAmount = basicBudget.getBudgetRemainAmount();
                } else {
                    // 直接使用对应预算科目的预算保留金额
                    newRemainAmount = basicBudget.getBudgetRemainAmount()
                            .add(travelReimburse.getExpenseAmount());
                }
            }
            newOccupyAmount = basicBudget.getBudgetOccupyAmount();
            newRemainAndOccupyAmount = newRemainAmount.add(newOccupyAmount);
            if (forbidFlag
                    && newRemainAndOccupyAmount.compareTo(basicBudget.getBudgetAmount())
                    > 0) {
                throw new FSSCException(FsscErrorType.BUDGET_MORE_THAN_100_PERCENT);
            }
            budgetAmount80Percent = basicBudget.getBudgetAmount()
                    .multiply(new BigDecimal(0.8d))
                    .setScale(2, RoundingMode.HALF_UP);
            if (warningFlag && newRemainAndOccupyAmount.compareTo(budgetAmount80Percent) > 0) {
                throw new FSSCException(FsscErrorType.BUDGET_MORE_THAN_80_PERCENT);
            }
            newUsableAmount = basicBudget.getBudgetAmount().subtract(newRemainAmount)
                    .subtract(newOccupyAmount);
            if (updateBudgetFlag) {
                basicBudgetService
                        .updateBasicBudget(newRemainAmount, newOccupyAmount, newUsableAmount,
                                basicBudget);
            }
        }
        afterExpenseService.save(afterExpense);
        if (advanceApplyFor != null) {
            advanceApplyForService.updateById(advanceApplyFor);
        }
        if (MapUtils.isNotEmpty(advanceBorrowListMap)) {
            for (BudgetAfterExpenseLine expenseLine : afterExpenseLineList) {
                expenseLine.setAfterExpenseId(afterExpense.getId());
            }
            afterExpenseLineService.saveBatch(afterExpenseLineList);
            advanceBorrowService.updateBatchById(advanceBorrowListMap.keySet());
            List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
            for (List<BudgetAdvanceBorrowLine> advanceBorrowLines : advanceBorrowListMap.values()) {
                advanceBorrowLineList.addAll(advanceBorrowLines);
            }
            advanceBorrowLineService.updateBatchById(advanceBorrowLineList);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean travelExpenseRemain2Occupy(TasTravelReimburse travelReimburse, boolean forbidFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(travelReimburse != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue().equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = travelReimburse.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(travelReimburse.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (travelReimburse.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        } else {
            budgetAccount = budgetAccountService.getBudgetAccountByCode(travelReimburse.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue().equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(travelReimburse.getId(),
                FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
        AssertUtils.asserts(afterExpense != null, FsscErrorType.BUDGET_AFTER_EXPENSE_NOT_FOUND);
        List<BudgetAfterExpenseLine> afterExpenseLineList = afterExpenseLineService.listByDocument(
                travelReimburse.getId(), FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
        if (travelReimburse.getProjectId() != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(travelReimburse.getUnitCode(),
                            travelReimburse.getProjectId(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    travelReimburse.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            //科目的保留变占用,不需要理会关联的预付款和借款的额度情况
            BudgetAmount newAccountBudgetAmount = accountRemain2Occupy(afterExpense.getBudgetRemainAmount(), projectBudget.getAmount());
            projectBudgetService.updateProjectBudget(newAccountBudgetAmount, projectBudget);
        } else {
            // 基本预算
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(travelReimburse.getUnitCode(),
                            travelReimburse.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    travelReimburse.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            //科目的保留变占用,不需要理会关联的预付款和借款的额度情况
            BudgetAmount newAccountBudgetAmount = accountRemain2Occupy(afterExpense.getBudgetRemainAmount(), basicBudget.getAmount());
            basicBudgetService.updateBasicBudget(newAccountBudgetAmount, basicBudget);
        }
        //报账单保留变占用
        afterExpense.setBudgetOccupyAmount(afterExpense.getBudgetRemainAmount());
        afterExpense.setBudgetRemainAmount(BigDecimal.ZERO);
        afterExpenseService.updateById(afterExpense);
        if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
            for (BudgetAfterExpenseLine afterExpenseLine : afterExpenseLineList) {
                afterExpenseLine.setBudgetOccupyAmount(afterExpenseLine.getBudgetRemainAmount());
                afterExpenseLine.setBudgetRemainAmount(BigDecimal.ZERO);
            }
            afterExpenseLineService.updateBatchById(afterExpenseLineList);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean travelExpenseBudgetFree(FsscEums budgetPhase, TasTravelReimburse travelReimburse) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService
                .getDocTypeByFunction(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue(),
                        deptVo.getDeptCode());
        AssertUtils.asserts(travelReimburse != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = travelReimburse.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(travelReimburse.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (travelReimburse.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        } else {
            budgetAccount = budgetAccountService.getBudgetAccountByCode(travelReimburse.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(travelReimburse.getId(),
                FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
        AssertUtils.asserts(afterExpense != null, FsscErrorType.BUDGET_AFTER_EXPENSE_NOT_FOUND);
        List<BudgetAfterExpenseLine> afterExpenseLineList = afterExpenseLineService.listByDocument(
                travelReimburse.getId(), FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue());
        BudgetAdvanceApplyFor advanceApplyFor = null;
        if (afterExpense.getAdvanceApplyForId() != null) {
            advanceApplyFor = advanceApplyForService.getByDocument(afterExpense.getAdvanceApplyForId(),
                    FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue());
        }
        //预算项目表的id,不是真正科研项目的id
        Long projectId = travelReimburse.getProjectId();
        BigDecimal moreThanAmount = travelReimburse.getExpenseAmount();
        if (projectId != null) {
            //项目预算
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(travelReimburse.getUnitCode(),
                            projectId, budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                    travelReimburse.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.getValue().equals(budgetPhase)) {
                //预算占用 释放
                afterExpense.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                if (CollectionUtils.isEmpty(afterExpenseLineList) && advanceApplyFor == null) {
                    //不关联 事前申请、借款单/预付款,直接释放科目上预算占用额度
                    BudgetAmount occupyFreeAmount = accountBudgetOccupyFree(travelReimburse.getExpenseAmount(), projectBudget.getAmount());
                    projectBudgetService.updateProjectBudget(occupyFreeAmount, projectBudget);
                } else {
                    //关联 事前申请,如果使用了事前申请的预算额度,还原保留额度
                    if (advanceApplyFor != null && afterExpense.getUseApplyForAmount() != null) {
                        advanceApplyForRestore(advanceApplyFor,afterExpense.getUseApplyForAmount());
                    }
                    //关联了借款单/预付款
                    if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                        BigDecimal verificationSum = afterExpenseLineList.stream()
                                .map(BudgetAfterExpenseLine::
                                        getBudgetOccupyAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                        moreThanAmount = travelReimburse.getExpenseAmount().subtract(verificationSum);
                        List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                        Map<Long, BigDecimal> restoreBorrowMap = new HashMap<>();
                        for (BudgetAfterExpenseLine afterExpenseLine : afterExpenseLineList) {
                            BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService
                                    .getById(afterExpenseLine.getAdvanceBorrowLineId());
                            //单据行的占用额度还原到借款单/预付款行的预算保留额度中
                            BigDecimal newUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount()
                                    .subtract(afterExpenseLine.getBudgetOccupyAmount());
                            BigDecimal newUsableRemainAmount = advanceBorrowLine.getBudgetRemainAmount()
                                    .subtract(newUsedRemainAmount);
                            advanceBorrowLine.setUsedRemainAmount(newUsedRemainAmount);
                            advanceBorrowLine.setUsableRemainAmount(newUsableRemainAmount);
                            advanceBorrowLineList.add(advanceBorrowLine);
                            BigDecimal restoreBorrowRemainAmount = restoreBorrowMap.get(advanceBorrowLine.getAdvanceBorrowId());
                            if (restoreBorrowRemainAmount != null) {
                                restoreBorrowRemainAmount = restoreBorrowRemainAmount.add(afterExpenseLine.getBudgetRemainAmount());
                                restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(), restoreBorrowRemainAmount);
                            } else {
                                restoreBorrowRemainAmount = afterExpenseLine.getBudgetRemainAmount();
                                restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(), restoreBorrowRemainAmount);
                            }
                        }
                        advanceBorrowRestore(restoreBorrowMap);
                        advanceBorrowLineService.updateBatchById(advanceBorrowLineList);
                        if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0 && advanceApplyFor == null) {
                            //报账金额大于核销金额,且没有关联事前申请
                            BudgetAmount occupyFreeAmount = accountBudgetOccupyFree(moreThanAmount, projectBudget.getAmount());
                            projectBudgetService.updateProjectBudget(occupyFreeAmount, projectBudget);
                        }
                    }
                }
                afterExpenseService.updateById(afterExpense);
            } else {
                //预算保留 释放
                if (CollectionUtils.isEmpty(afterExpenseLineList) && advanceApplyFor == null) {
                    //不关联 事前申请、借款单/预付款
                    BudgetAmount remainFreeAmount = accountBudgetRemainFree(moreThanAmount, projectBudget.getAmount());
                    projectBudgetService.updateProjectBudget(remainFreeAmount, projectBudget);
                } else {
                    //如果关联了借款单/预付款
                    if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                        BigDecimal verificationSum = afterExpenseLineList.stream()
                                .map(BudgetAfterExpenseLine::
                                        getBudgetRemainAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                        moreThanAmount = travelReimburse.getExpenseAmount().subtract(verificationSum);
                        List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                        Map<Long, BigDecimal> restoreBorrowMap = new HashMap<>();
                        for (BudgetAfterExpenseLine afterExpenseLine : afterExpenseLineList) {
                            BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService
                                    .getById(afterExpenseLine.getAdvanceBorrowLineId());
                            //单据行的保留额度还原到借款单/预付款行的预算保留额度中
                            BigDecimal newUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount()
                                    .subtract(afterExpenseLine.getBudgetRemainAmount());
                            BigDecimal newUsableRemainAmount = advanceBorrowLine.getBudgetRemainAmount()
                                    .subtract(newUsedRemainAmount);
                            advanceBorrowLine.setUsedRemainAmount(newUsedRemainAmount);
                            advanceBorrowLine.setUsableRemainAmount(newUsableRemainAmount);
                            advanceBorrowLineList.add(advanceBorrowLine);
                            BigDecimal restoreBorrowRemainAmount = restoreBorrowMap.get(advanceBorrowLine.getAdvanceBorrowId());
                            if (restoreBorrowRemainAmount != null) {
                                restoreBorrowRemainAmount = restoreBorrowRemainAmount.add(afterExpenseLine.getBudgetRemainAmount());
                                restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(), restoreBorrowRemainAmount);
                            } else {
                                restoreBorrowRemainAmount = afterExpenseLine.getBudgetRemainAmount();
                                restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(), restoreBorrowRemainAmount);
                            }
                        }
                        advanceBorrowRestore(restoreBorrowMap);
                        advanceBorrowLineService.updateBatchById(advanceBorrowLineList);
                    }
                    if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0){
                        if (advanceApplyFor != null) {
                            advanceApplyForRestore(advanceApplyFor,moreThanAmount);
                        }else{
                            //报账金额大于核销金额,且没有关联事前申请
                            BudgetAmount budgetAmount = accountBudgetRemainFree(moreThanAmount,projectBudget.getAmount());
                            projectBudgetService.updateProjectBudget(budgetAmount,projectBudget);
                        }
                    }
                }
                afterExpenseService.removeById(afterExpense.getId());
                if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                    List<Long> ids = afterExpenseLineList.stream().map(BudgetAfterExpenseLine::getId).collect(Collectors.toList());
                    afterExpenseLineService.removeByIds(ids);
                }
            }
        } else {
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(travelReimburse.getUnitCode(),
                            travelReimburse.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    travelReimburse.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.getValue().equals(budgetPhase)) {
                //预算占用 释放
                afterExpense.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                if (CollectionUtils.isEmpty(afterExpenseLineList) && advanceApplyFor == null) {
                    //不关联 事前申请、借款单/预付款,直接释放科目上预算占用额度
                    BudgetAmount occupyFreeAmount = accountBudgetOccupyFree(travelReimburse.getExpenseAmount(), basicBudget.getAmount());
                    basicBudgetService.updateBasicBudget(occupyFreeAmount, basicBudget);
                } else {
                    //关联 事前申请
                    if (advanceApplyFor != null && afterExpense.getUseApplyForAmount() != null) {
                        advanceApplyForRestore(advanceApplyFor,afterExpense.getUseApplyForAmount());
                    }
                    //关联了借款单/预付款
                    if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                        BigDecimal verificationSum = afterExpenseLineList.stream()
                                .map(BudgetAfterExpenseLine::
                                        getBudgetOccupyAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                        moreThanAmount = travelReimburse.getExpenseAmount().subtract(verificationSum);
                        List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                        Map<Long, BigDecimal> restoreBorrowMap = new HashMap<>();
                        for (BudgetAfterExpenseLine afterExpenseLine : afterExpenseLineList) {
                            BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService
                                    .getById(afterExpenseLine.getAdvanceBorrowLineId());
                            //单据行的保留额度还原到借款单/预付款行的预算保留额度中
                            BigDecimal newUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount()
                                    .subtract(afterExpenseLine.getBudgetOccupyAmount());
                            BigDecimal newUsableRemainAmount = advanceBorrowLine.getBudgetRemainAmount()
                                    .subtract(newUsedRemainAmount);
                            advanceBorrowLine.setUsedRemainAmount(newUsedRemainAmount);
                            advanceBorrowLine.setUsableRemainAmount(newUsableRemainAmount);
                            advanceBorrowLineList.add(advanceBorrowLine);
                            BigDecimal restoreBorrowRemainAmount = restoreBorrowMap.get(advanceBorrowLine.getAdvanceBorrowId());
                            if (restoreBorrowRemainAmount != null) {
                                restoreBorrowRemainAmount = restoreBorrowRemainAmount.add(afterExpenseLine.getBudgetRemainAmount());
                                restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(), restoreBorrowRemainAmount);
                            } else {
                                restoreBorrowRemainAmount = afterExpenseLine.getBudgetRemainAmount();
                                restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(), restoreBorrowRemainAmount);
                            }
                        }
                        advanceBorrowRestore(restoreBorrowMap);
                        advanceBorrowLineService.updateBatchById(advanceBorrowLineList);
                        if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0 && advanceApplyFor == null) {
                            //报账金额大于核销金额,且没有关联事前申请
                            BudgetAmount budgetAmount = accountBudgetRemainFree(moreThanAmount, basicBudget.getAmount());
                            basicBudgetService.updateBasicBudget(budgetAmount, basicBudget);
                        }
                    }
                }
                afterExpenseService.updateById(afterExpense);
            } else {
                //预算保留 释放
                if (CollectionUtils.isEmpty(afterExpenseLineList) && advanceApplyFor == null) {
                    //不关联 事前申请、借款单/预付款
                    BudgetAmount occupyFreeAmount = accountBudgetRemainFree(travelReimburse.getExpenseAmount(), basicBudget.getAmount());
                    basicBudgetService.updateBasicBudget(occupyFreeAmount, basicBudget);
                } else {
                    //如果关联了借款单/预付款
                    if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                        BigDecimal verificationSum = afterExpenseLineList.stream()
                                .map(BudgetAfterExpenseLine::
                                        getBudgetRemainAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                        moreThanAmount = travelReimburse.getExpenseAmount().subtract(verificationSum);
                        List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                        Map<Long, BigDecimal> restoreBorrowMap = new HashMap<>();
                        for (BudgetAfterExpenseLine afterExpenseLine : afterExpenseLineList) {
                            BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService
                                    .getById(afterExpenseLine.getAdvanceBorrowLineId());
                            //单据行的保留额度还原到借款单/预付款行的预算保留额度中
                            BigDecimal newUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount()
                                    .subtract(afterExpenseLine.getBudgetRemainAmount());
                            BigDecimal newUsableRemainAmount = advanceBorrowLine.getBudgetRemainAmount()
                                    .subtract(newUsedRemainAmount);
                            advanceBorrowLine.setUsedRemainAmount(newUsedRemainAmount);
                            advanceBorrowLine.setUsableRemainAmount(newUsableRemainAmount);
                            advanceBorrowLineList.add(advanceBorrowLine);
                            BigDecimal restoreBorrowRemainAmount = restoreBorrowMap.get(advanceBorrowLine.getAdvanceBorrowId());
                            if (restoreBorrowRemainAmount != null) {
                                restoreBorrowRemainAmount = restoreBorrowRemainAmount.add(afterExpenseLine.getBudgetRemainAmount());
                                restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(), restoreBorrowRemainAmount);
                            } else {
                                restoreBorrowRemainAmount = afterExpenseLine.getBudgetRemainAmount();
                                restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(), restoreBorrowRemainAmount);
                            }
                        }
                        advanceBorrowRestore(restoreBorrowMap);
                        advanceBorrowLineService.updateBatchById(advanceBorrowLineList);
                        if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0 && advanceApplyFor == null) {
                            //报账金额大于核销金额,且没有关联事前申请
                            BudgetAmount budgetAmount = accountBudgetRemainFree(moreThanAmount, basicBudget.getAmount());
                            basicBudgetService.updateBasicBudget(budgetAmount, basicBudget);
                        }
                    }
                    if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0){
                        if (advanceApplyFor != null) {
                            advanceApplyForRestore(advanceApplyFor,moreThanAmount);
                        }else{
                            //报账金额大于核销金额,且没有关联事前申请
                            BudgetAmount budgetAmount = accountBudgetRemainFree(moreThanAmount,basicBudget.getAmount());
                            basicBudgetService.updateBasicBudget(budgetAmount,basicBudget);
                        }
                    }
                }
                afterExpenseService.removeById(afterExpense.getId());
                if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                    List<Long> ids = afterExpenseLineList.stream().map(BudgetAfterExpenseLine::getId).collect(Collectors.toList());
                    afterExpenseLineService.removeByIds(ids);
                }
            }
        }
        return true;
    }

}
