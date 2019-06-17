package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.deloitte.services.fssc.budget.entity.*;
import com.deloitte.services.fssc.budget.service.IBudgetGeneralControlService;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.general.entity.GeGeneralExpense;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BudgetGeneralControlServiceImpl extends BudgetBaseControlServiceImpl
        implements IBudgetGeneralControlService {


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean generalBudgetControl(GeGeneralExpense generalExpense, boolean warningFlag,
            boolean forbidFlag, boolean updateBudgetFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.GE_GENERAL_EXPENSE.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(generalExpense != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = generalExpense.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(generalExpense.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (generalExpense.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(generalExpense.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        //预算-事后报账
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(generalExpense.getId(),
                FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        if (afterExpense != null){
            throw new FSSCException(FsscErrorType.BUDGET_IS_EXISTS);
        }
        afterExpense = this.constructAfterExpense(generalExpense.getId(),
                FsscTableNameEums.GE_GENERAL_EXPENSE.getValue(), generalExpense.getExpenseAmount(),
                expenseMainTypeId, budgetAccount);
        List<BudgetAfterExpenseLine> afterExpenseLineList = new ArrayList<>();
        //查询关联预付款或借款
        QueryWrapper<GeExpenseBorrowPrepay> prepayQueryWrapper = new QueryWrapper<>();
        prepayQueryWrapper.eq(GeExpenseBorrowPrepay.DOCUMENT_ID, generalExpense.getId());
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
                        String.valueOf(borrowPrepay.getBorrowOrPrepayId()) + "$" +  borrowPrepay.getConnectDocumentType();
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
        if (generalExpense.getProjectId() != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(generalExpense.getUnitCode(),
                            generalExpense.getProjectId(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    generalExpense.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},项目{},预算科目{},年度{}",generalExpense.getUnitCode(),
                    generalExpense.getProjectId(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            generalExpense.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            afterExpense.setBudgetType(FsscEums.BUDGET_TYPE_PROJECT.getValue());
            afterExpense.setProjectId(generalExpense.getProjectId());
            afterExpense.setProjectBudgetAccountId(budgetAccount.getId());
            BudgetAmount newAccountBudgetAmount = checkAndUpdateExpenseAccountBudget(projectBudget.getAmount(),advanceBorrowMap,
                    borrowPrepayList, generalExpense.getExpenseAmount(),forbidFlag,warningFlag);
            if (updateBudgetFlag) {
                projectBudgetService.updateProjectBudget(newAccountBudgetAmount, projectBudget);
            }
        } else {
            // 基本预算
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(generalExpense.getUnitCode(),
                            generalExpense.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    generalExpense.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},部门{},预算科目{},年度{}",generalExpense.getUnitCode(),
                    generalExpense.getDeptCode(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            generalExpense.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            afterExpense.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
            afterExpense.setBasicBudgetAccountId(budgetAccount.getId());
            BudgetAmount newAccountBudgetAmount = checkAndUpdateExpenseAccountBudget(basicBudget.getAmount(),advanceBorrowMap,
                    borrowPrepayList, generalExpense.getExpenseAmount(),forbidFlag,warningFlag);
            if (updateBudgetFlag) {
                basicBudgetService.updateBasicBudget(newAccountBudgetAmount,basicBudget);
            }
        }
        afterExpenseService.save(afterExpense);
        if (MapUtils.isNotEmpty(advanceBorrowListMap)) {
            for (BudgetAfterExpenseLine expenseLine : afterExpenseLineList){
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
    public boolean generalRemain2Occupy(GeGeneralExpense generalExpense, boolean forbidFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.GE_GENERAL_EXPENSE.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(generalExpense != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = generalExpense.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(generalExpense.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (generalExpense.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(generalExpense.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(generalExpense.getId(),
                FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        AssertUtils.asserts(afterExpense != null, FsscErrorType.BUDGET_AFTER_EXPENSE_NOT_FOUND);
        List<BudgetAfterExpenseLine> afterExpenseLineList = afterExpenseLineService.listByDocument(
                generalExpense.getId(), FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        if (generalExpense.getProjectId() != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(generalExpense.getUnitCode(),
                            generalExpense.getProjectId(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    generalExpense.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            //保留变占用,不需要理会关联的预付款和借款的额度情况
            BudgetAmount newAccountBudgetAmount = accountRemain2Occupy(afterExpense.getBudgetRemainAmount(),projectBudget.getAmount());
            projectBudgetService.updateProjectBudget(newAccountBudgetAmount, projectBudget);
        } else {
            // 基本预算
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(generalExpense.getUnitCode(),
                            generalExpense.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    generalExpense.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            //保留变占用,不需要理会关联的预付款和借款的额度情况
            BudgetAmount newAccountBudgetAmount = accountRemain2Occupy(afterExpense.getBudgetRemainAmount(),basicBudget.getAmount());
            basicBudgetService.updateBasicBudget(newAccountBudgetAmount, basicBudget);
        }
        //保留变占用
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
    public boolean generalBudgetFree(FsscEums budgetPhase, GeGeneralExpense generalExpense) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService
                .getDocTypeByFunction(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue(),
                        deptVo.getDeptCode());
        AssertUtils.asserts(generalExpense != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = generalExpense.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(generalExpense.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (generalExpense.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(generalExpense.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(generalExpense.getId(),
                FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        AssertUtils.asserts(afterExpense != null, FsscErrorType.BUDGET_AFTER_EXPENSE_NOT_FOUND);
        List<BudgetAfterExpenseLine> afterExpenseLineList = afterExpenseLineService.listByDocument(
                generalExpense.getId(), FsscTableNameEums.GE_GENERAL_EXPENSE.getValue());
        //预算项目表的id,不是真正科研项目的id
        Long projectId = generalExpense.getProjectId();
        BigDecimal moreThanAmount = generalExpense.getTotalAmount();
        if (projectId != null) {
            //项目预算
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(generalExpense.getUnitCode(),
                            projectId, budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                    generalExpense.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                //预算占用 释放
                afterExpense.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                //如果关联了借款单/预付款
                if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                    BigDecimal verificationSum = afterExpenseLineList.stream()
                            .map(BudgetAfterExpenseLine::
                                    getBudgetOccupyAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                    moreThanAmount = generalExpense.getExpenseAmount().subtract(verificationSum);
                    List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                    Map<Long, BigDecimal> restoreBorrowMap = new HashMap<>(16);
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
                        if (restoreBorrowMap.containsKey(advanceBorrowLine.getAdvanceBorrowId())){
                            BigDecimal restoreBorrowRemainAmount = restoreBorrowMap.get(advanceBorrowLine.getAdvanceBorrowId())
                                    .add(afterExpenseLine.getBudgetOccupyAmount());
                            restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(),restoreBorrowRemainAmount);
                        }else{
                            BigDecimal restoreBorrowRemainAmount = afterExpenseLine.getBudgetOccupyAmount();
                            restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(),restoreBorrowRemainAmount);
                        }
                    }
                    advanceBorrowRestore(restoreBorrowMap);
                    advanceBorrowLineService.updateBatchById(advanceBorrowLineList);
                    //把核销金额从占用额度转移到保留额度
                    BudgetAmount remain2OccupyAmount = accountOccupy2Remain(verificationSum,projectBudget.getAmount());
                    projectBudgetService.updateProjectBudget(remain2OccupyAmount,projectBudget);
                }
                if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0) {
                    //非挖取的借款单/预付款预算额度的占用额度 释放
                    projectBudget = projectBudgetService.getById(projectBudget.getId());
                    BudgetAmount budgetAmount = accountBudgetOccupyFree(moreThanAmount,projectBudget.getAmount());
                    projectBudgetService.updateProjectBudget(budgetAmount, projectBudget);
                }
                afterExpenseService.updateById(afterExpense);
            } else {
                //预算保留 释放

                //如果关联了借款单/预付款
                if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                    BigDecimal verificationSum = afterExpenseLineList.stream()
                            .map(BudgetAfterExpenseLine::
                                    getBudgetRemainAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                    moreThanAmount = generalExpense.getExpenseAmount().subtract(verificationSum);
                    List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                    Map<Long,BigDecimal> restoreBorrowMap = new HashMap<>();
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
                        if (restoreBorrowRemainAmount != null){
                            restoreBorrowRemainAmount = restoreBorrowRemainAmount.add(afterExpenseLine.getBudgetRemainAmount());
                            restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(),restoreBorrowRemainAmount);
                        }else{
                            restoreBorrowRemainAmount = afterExpenseLine.getBudgetRemainAmount();
                            restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(),restoreBorrowRemainAmount);
                        }
                    }
                    advanceBorrowRestore(restoreBorrowMap);
                    advanceBorrowLineService.updateBatchById(advanceBorrowLineList);
                }
                if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0) {
                    BudgetAmount budgetAmount = accountBudgetRemainFree(moreThanAmount,projectBudget.getAmount());
                    projectBudgetService.updateProjectBudget(budgetAmount, projectBudget);
                }
                afterExpenseService.removeById(afterExpense.getId());
                if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                    List<Long> ids  = afterExpenseLineList.stream().map(BudgetAfterExpenseLine :: getId).collect(Collectors.toList());
                    afterExpenseLineService.removeByIds(ids);
                }
            }
        } else {
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(generalExpense.getUnitCode(),
                            generalExpense.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    generalExpense.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                //预算占用 释放
                afterExpense.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                //如果关联了借款单/预付款
                if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                    BigDecimal verificationSum = afterExpenseLineList.stream()
                            .map(BudgetAfterExpenseLine::
                                    getBudgetOccupyAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                    moreThanAmount = generalExpense.getExpenseAmount().subtract(verificationSum);
                    List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                    Map<Long,BigDecimal> restoreBorrowMap = new HashMap<>();
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
                        if (restoreBorrowRemainAmount != null){
                            restoreBorrowRemainAmount = restoreBorrowRemainAmount.add(afterExpenseLine.getBudgetOccupyAmount());
                            restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(),restoreBorrowRemainAmount);
                        }else{
                            restoreBorrowRemainAmount = afterExpenseLine.getBudgetOccupyAmount();
                            restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(),restoreBorrowRemainAmount);
                        }
                    }
                    advanceBorrowRestore(restoreBorrowMap);
                    advanceBorrowLineService.updateBatchById(advanceBorrowLineList);
                    //把核销金额从占用额度转移到保留额度
                    BudgetAmount remain2OccupyAmount = accountOccupy2Remain(verificationSum,basicBudget.getAmount());
                    basicBudgetService.updateBasicBudget(remain2OccupyAmount,basicBudget);
                }
                if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0) {
                    basicBudget = basicBudgetService.getById(basicBudget.getId());
                    BudgetAmount occupyFreeAmount = accountBudgetOccupyFree(moreThanAmount,basicBudget.getAmount());
                    basicBudgetService.updateBasicBudget(occupyFreeAmount,basicBudget);
                }
                afterExpenseService.updateById(afterExpense);
            } else {
                //预算保留 释放

                //如果关联了借款单/预付款
                if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                    BigDecimal verificationSum = afterExpenseLineList.stream()
                            .map(BudgetAfterExpenseLine::
                                    getBudgetRemainAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                    moreThanAmount = generalExpense.getExpenseAmount().subtract(verificationSum);
                    List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                    Map<Long,BigDecimal> restoreBorrowMap = new HashMap<>();
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
                        if (restoreBorrowRemainAmount != null){
                            restoreBorrowRemainAmount = restoreBorrowRemainAmount.add(afterExpenseLine.getBudgetRemainAmount());
                            restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(),restoreBorrowRemainAmount);
                        }else{
                            restoreBorrowRemainAmount = afterExpenseLine.getBudgetRemainAmount();
                            restoreBorrowMap.put(advanceBorrowLine.getAdvanceBorrowId(),restoreBorrowRemainAmount);
                        }
                    }
                    advanceBorrowRestore(restoreBorrowMap);
                    advanceBorrowLineService.updateBatchById(advanceBorrowLineList);
                }
                if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0) {
                    BudgetAmount budgetAmount = accountBudgetRemainFree(moreThanAmount,basicBudget.getAmount());
                    basicBudgetService.updateBasicBudget(budgetAmount, basicBudget);
                }
                afterExpenseService.removeById(afterExpense.getId());
                if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                    List<Long> ids  = afterExpenseLineList.stream().map(BudgetAfterExpenseLine :: getId).collect(Collectors.toList());
                    afterExpenseLineService.removeByIds(ids);
                }
            }
        }
        return true;
    }


}
