package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainType;
import com.deloitte.services.fssc.budget.entity.*;
import com.deloitte.services.fssc.budget.service.IBudgetContractControlService;
import com.deloitte.services.fssc.business.contract.entity.CrbContractReimburseBill;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BudgetContractControlServiceImpl extends BudgetBaseControlServiceImpl
        implements IBudgetContractControlService {


    @Override
    public boolean contractBudgetControl(CrbContractReimburseBill contractReimburseBill, boolean warningFlag,
                                         boolean forbidFlag, boolean updateBudgetFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(contractReimburseBill != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = contractReimburseBill.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(contractReimburseBill.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (contractReimburseBill.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(contractReimburseBill.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue().equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        //预算-事后报账
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(contractReimburseBill.getId(),
                FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        if (afterExpense != null){
            throw new FSSCException(FsscErrorType.BUDGET_IS_EXISTS);
        }
        afterExpense = this
                .constructAfterExpense(contractReimburseBill.getId(),FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue(),
                        contractReimburseBill.getTotalAmountReimburse(),
                        expenseMainTypeId, budgetAccount);
        List<BudgetAfterExpenseLine> afterExpenseLineList = new ArrayList<>();
        //查询关联预付款或借款
        QueryWrapper<GeExpenseBorrowPrepay> prepayQueryWrapper = new QueryWrapper<>();
        prepayQueryWrapper.eq(GeExpenseBorrowPrepay.DOCUMENT_ID, contractReimburseBill.getId());
        List<GeExpenseBorrowPrepay> borrowPrepayList = prepayService.list(prepayQueryWrapper);
        Map<String, BudgetAdvanceBorrow> advanceBorrowMap = new HashMap<>(16);
        Map<BudgetAdvanceBorrow, List<BudgetAdvanceBorrowLine>> advanceBorrowListMap = new HashMap<>(
                16);
        if (CollectionUtils.isNotEmpty(borrowPrepayList)) {
            for (GeExpenseBorrowPrepay borrowPrepay : borrowPrepayList) {
                BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService
                        .getByLineNumber(borrowPrepay.getBorrowOrPrepayId(), borrowPrepay.getConnectDocumentType(),
                                borrowPrepay.getLineNumber());
                BigDecimal newUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount()
                        .add(borrowPrepay.getCurrentVerAmount());
                BigDecimal newUsableRemainAmount = advanceBorrowLine.getUsableRemainAmount()
                        .subtract(borrowPrepay.getCurrentVerAmount());
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
                    AssertUtils.asserts(borrowPrepay.getCurrentVerAmount() != null,FsscErrorType.VERIFICATION_AMOUNT_IS_EMPTY);
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
        if (contractReimburseBill.getProjectId() != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(contractReimburseBill.getUnitCode(),
                            contractReimburseBill.getProjectId(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    contractReimburseBill.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},项目{},预算科目{},年度{}",contractReimburseBill.getUnitCode(),
                    contractReimburseBill.getProjectId(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            contractReimburseBill.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            afterExpense.setBudgetType(FsscEums.BUDGET_TYPE_PROJECT.getValue());
            afterExpense.setProjectId(contractReimburseBill.getProjectId());
            afterExpense.setProjectBudgetAccountId(budgetAccount.getId());
            BudgetAmount newAccountBudgetAmount = checkAndUpdateExpenseAccountBudget(projectBudget.getAmount(),advanceBorrowMap,
                    borrowPrepayList, contractReimburseBill.getTotalSum(),forbidFlag,warningFlag);
            if (updateBudgetFlag) {
                projectBudgetService.updateProjectBudget(newAccountBudgetAmount, projectBudget);
            }
        } else {
            // 基本预算
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(contractReimburseBill.getUnitCode(),
                            contractReimburseBill.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    contractReimburseBill.getCreateTime(), DateUtil.FM_YYYY));
            log.info("单位{},部门{},预算科目{},年度{}",contractReimburseBill.getUnitCode(),
                    contractReimburseBill.getDeptCode(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                            contractReimburseBill.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            afterExpense.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
            afterExpense.setBasicBudgetAccountId(budgetAccount.getId());
            BudgetAmount newAccountBudgetAmount = checkAndUpdateExpenseAccountBudget(basicBudget.getAmount(), advanceBorrowMap,
                    borrowPrepayList, contractReimburseBill.getTotalSum(), forbidFlag, warningFlag);
            if (updateBudgetFlag) {
                basicBudgetService.updateBasicBudget(newAccountBudgetAmount, basicBudget);
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
    public boolean contractRemain2Occupy(CrbContractReimburseBill contractReimburseBill, boolean forbidFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(contractReimburseBill != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = contractReimburseBill.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(contractReimburseBill.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (contractReimburseBill.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(contractReimburseBill.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue().equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(contractReimburseBill.getId(),
                FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        AssertUtils.asserts(afterExpense != null, FsscErrorType.BUDGET_AFTER_EXPENSE_NOT_FOUND);
        List<BudgetAfterExpenseLine> afterExpenseLineList = afterExpenseLineService.listByDocument(
                contractReimburseBill.getId(), FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        if (contractReimburseBill.getProjectId() != null) {
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(contractReimburseBill.getUnitCode(),
                            contractReimburseBill.getProjectId(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    contractReimburseBill.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            BudgetAmount newAccountBudgetAmount = accountRemain2Occupy(afterExpense.getBudgetRemainAmount(),projectBudget.getAmount());
            projectBudgetService.updateProjectBudget(newAccountBudgetAmount, projectBudget);
        } else {
            // 基本预算
            BudgetBasicBudget basicBudget = basicBudgetService
                    .selectByKeyWord(contractReimburseBill.getUnitCode(),
                            contractReimburseBill.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    contractReimburseBill.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
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
    public boolean contractBudgetFree(FsscEums budgetPhase, CrbContractReimburseBill contractReimburseBill) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService
                .getDocTypeByFunction(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue(),
                        deptVo.getDeptCode());
        AssertUtils.asserts(contractReimburseBill != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        Long expenseMainTypeId = contractReimburseBill.getMainTypeId();
        AssertUtils.asserts(expenseMainTypeId != null, FsscErrorType.EXPENSE_MAIN_TYPE_NOT_FIND);
        AssertUtils.asserts(contractReimburseBill.getCreateTime() != null,
                FsscErrorType.CREATE_TIME_NOT_EXIST);
        BaseExpenseMainType expenseMainType = expenseMainTypeService.getById(expenseMainTypeId);
        BaseBudgetAccount budgetAccount;
        if (contractReimburseBill.getProjectId() == null) {
            budgetAccount = budgetAccountService.getById(expenseMainType.getBudgetAccountId());
        }else{
            budgetAccount = budgetAccountService.getBudgetAccountByCode(contractReimburseBill.getProjectBudgetAccountCode(),
                    FsscEums.BUDGET_TYPE_PROJECT.getValue());
        }
        if (budgetAccount == null || !FsscEums.VALID.getValue()
                .equals(budgetAccount.getValidFlag())) {
            throw new FSSCException(FsscErrorType.BUDGET_ACCOUNT_NOT_FIND_OR_INVALID);
        }
        BudgetAfterExpense afterExpense = afterExpenseService.getByDocument(contractReimburseBill.getId(),
                FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        AssertUtils.asserts(afterExpense != null, FsscErrorType.BUDGET_AFTER_EXPENSE_NOT_FOUND);
        List<BudgetAfterExpenseLine> afterExpenseLineList = afterExpenseLineService.listByDocument(
                contractReimburseBill.getId(), FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue());
        Long projectId = contractReimburseBill.getProjectId();
        BigDecimal moreThanAmount = contractReimburseBill.getTotalSum();
        if (projectId != null) {
            //项目预算
            BudgetProjectBudget projectBudget = projectBudgetService
                    .selectByKeyWord(contractReimburseBill.getUnitCode(),
                            projectId, budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                    contractReimburseBill.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                //预算占用 释放
                afterExpense.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                //如果关联了借款单/预付款
                if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                    BigDecimal verificationSum = afterExpenseLineList.stream()
                            .map(BudgetAfterExpenseLine::
                                    getBudgetOccupyAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                    moreThanAmount = contractReimburseBill.getTotalSum().subtract(verificationSum);
                    List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                    Map<Long, BigDecimal> restoreBorrowMap = new HashMap<>(16);
                    for (BudgetAfterExpenseLine afterExpenseLine : afterExpenseLineList) {
                        BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService
                                .getById(afterExpenseLine.getAdvanceBorrowLineId());
                        //单据行的占用额度还原到借款单/预付款行的预算保留额度中
                        BigDecimal newUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount()
                                .subtract(afterExpenseLine.getBudgetOccupyAmount());
                        BigDecimal newUsableRemainAmount = advanceBorrowLine.getUsableRemainAmount()
                                .add(afterExpenseLine.getBudgetOccupyAmount());
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
                    moreThanAmount = contractReimburseBill.getTotalSum().subtract(verificationSum);
                    List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                    Map<Long,BigDecimal> restoreBorrowMap = new HashMap<>();
                    for (BudgetAfterExpenseLine afterExpenseLine : afterExpenseLineList) {
                        BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService
                                .getById(afterExpenseLine.getAdvanceBorrowLineId());
                        //单据行的保留额度还原到借款单/预付款行的预算保留额度中
                        BigDecimal newUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount()
                                .subtract(afterExpenseLine.getBudgetRemainAmount());
                        BigDecimal newUsableRemainAmount = advanceBorrowLine.getUsableRemainAmount()
                                .add(afterExpenseLine.getBudgetRemainAmount());
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
                    //报账金额超过借款单/预付款的部分保留额度 释放
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
                    .selectByKeyWord(contractReimburseBill.getUnitCode(),
                            contractReimburseBill.getDeptCode(), budgetAccount.getCode(),
                            LocalDateTimeUtils.formatTime(
                                    contractReimburseBill.getCreateTime(), DateUtil.FM_YYYY));
            AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
            if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
                //预算占用 释放
                afterExpense.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
                //如果关联了借款单/预付款
                if (CollectionUtils.isNotEmpty(afterExpenseLineList)) {
                    BigDecimal verificationSum = afterExpenseLineList.stream()
                            .map(BudgetAfterExpenseLine::
                                    getBudgetOccupyAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                    moreThanAmount = contractReimburseBill.getTotalSum().subtract(verificationSum);
                    List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                    Map<Long,BigDecimal> restoreBorrowMap = new HashMap<>();
                    for (BudgetAfterExpenseLine afterExpenseLine : afterExpenseLineList) {
                        BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService
                                .getById(afterExpenseLine.getAdvanceBorrowLineId());
                        //单据行的占用额度还原到借款单/预付款行的预算保留额度中
                        BigDecimal newUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount()
                                .subtract(afterExpenseLine.getBudgetOccupyAmount());
                        BigDecimal newUsableRemainAmount = advanceBorrowLine.getUsableRemainAmount()
                                .add(afterExpenseLine.getBudgetOccupyAmount());
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
                    BudgetAmount accountOccupy2Remain = accountOccupy2Remain(verificationSum,basicBudget.getAmount());
                    basicBudgetService.updateBasicBudget(accountOccupy2Remain,basicBudget);
                }
                if (moreThanAmount.compareTo(BigDecimal.ZERO) > 0) {
                    //报账金额超过 借款单/预付款的部分占用额度 释放
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
                    moreThanAmount = contractReimburseBill.getTotalSum().subtract(verificationSum);
                    List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>();
                    Map<Long,BigDecimal> restoreBorrowMap = new HashMap<>();
                    for (BudgetAfterExpenseLine afterExpenseLine : afterExpenseLineList) {
                        BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService
                                .getById(afterExpenseLine.getAdvanceBorrowLineId());
                        //单据行的保留额度还原到借款单/预付款行的预算保留额度中
                        BigDecimal newUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount()
                                .subtract(afterExpenseLine.getBudgetRemainAmount());
                        BigDecimal newUsableRemainAmount = advanceBorrowLine.getUsableRemainAmount()
                                .add(afterExpenseLine.getBudgetRemainAmount());
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
                    //报账金额超过借款单/预付款的部分保留额度 释放
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
