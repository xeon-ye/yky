package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.budget.entity.*;
import com.deloitte.services.fssc.budget.service.IBudgetRepayControlService;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyInfo;
import com.deloitte.services.fssc.business.borrow.entity.BorrowMoneyLine;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;
import com.deloitte.services.fssc.business.poi.entity.PepaymentOrderInfo;
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
public class IBudgetRepayControlServiceImpl extends BudgetBaseControlServiceImpl implements IBudgetRepayControlService {


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean repayBudgetControl(PepaymentOrderInfo repayOrderInfo, boolean warningFlag,
                                      boolean forbidFlag, boolean updateBudgetFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(repayOrderInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        BudgetAfterRepayment afterRepayment = new BudgetAfterRepayment();
        afterRepayment.setDocumentId(repayOrderInfo.getId());
        afterRepayment.setDocumentType(FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
        afterRepayment.setProjectId(repayOrderInfo.getProjectId());
        afterRepayment.setBudgetType(repayOrderInfo.getProjectId() != null ? FsscEums.BUDGET_TYPE_PROJECT.getValue()
                : FsscEums.BUDGET_TYPE_BASIC.getValue() );
        afterRepayment.setRepayAmount(repayOrderInfo.getReAmountTatol());
        afterRepayment.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_VALID.getValue());
        List<BudgetAfterRepaymentLine> afterRepaymentLineList = new ArrayList<>();
        QueryWrapper<GeExpenseBorrowPrepay> borrowPrepayQueryWrapper = new QueryWrapper<>();
        borrowPrepayQueryWrapper.eq(GeExpenseBorrowPrepay.DOCUMENT_ID,repayOrderInfo.getId());
        List<GeExpenseBorrowPrepay> repayLineList = prepayService.list(borrowPrepayQueryWrapper);
        AssertUtils.asserts(CollectionUtils.isNotEmpty(repayLineList),FsscErrorType.REPAY_LINE_NOT_FIND);
        List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>(repayLineList.size());
        Map<String,BudgetAdvanceBorrow> advanceBorrowMap = new HashMap<>();
        for (GeExpenseBorrowPrepay repayLine : repayLineList){
            //预算-事前借款行
            BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService.getByLineNumber(repayLine.getBorrowOrPrepayId(),
                    repayLine.getConnectDocumentType(),repayLine.getLineNumber());
            //单据-借款行
            BorrowMoneyLine borrowMoneyLine = borrowMoneyLineService.getByDocumentAndLineNum(repayLine.getBorrowOrPrepayId(),repayLine.getLineNumber());
            //预算-事后还款行
            BudgetAfterRepaymentLine afterRepaymentLine = new BudgetAfterRepaymentLine();
            afterRepaymentLine.setAdvanceBorrowLineId(advanceBorrowLine.getId());
            afterRepaymentLine.setBorrowLineId(borrowMoneyLine.getId());
            afterRepaymentLine.setRepayAmount(repayLine.getCurrentVerAmount());
            afterRepaymentLine.setSubTypeId(repayLine.getSubTypeId());
            afterRepaymentLine.setDocumentId(repayOrderInfo.getId());
            afterRepaymentLine.setDocumentType(FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
            afterRepaymentLineList.add(afterRepaymentLine);
            AssertUtils.asserts(repayLine.getCurrentVerAmount() != null,FsscErrorType.REPAY_AMOUNT_IS_EMPTY);
            BigDecimal newLineUsableRemainAmount = advanceBorrowLine.getUsableRemainAmount().subtract(repayLine.getCurrentVerAmount());
            advanceBorrowLine.setUsableRemainAmount(newLineUsableRemainAmount);
            BigDecimal newLineUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount().add(repayLine.getCurrentVerAmount());
            advanceBorrowLine.setUsedRemainAmount(newLineUsedRemainAmount);
            advanceBorrowLineList.add(advanceBorrowLine);
            String advanceBorrowKey =
                    String.valueOf(repayLine.getBorrowOrPrepayId()) + "$" +  repayLine.getConnectDocumentType();
            if (advanceBorrowMap.get(advanceBorrowKey) == null){
                //预算-事前借款
                BudgetAdvanceBorrow advanceBorrow = advanceBorrowService.getByDocument(repayLine.getBorrowOrPrepayId(),
                        repayLine.getConnectDocumentType());
                BigDecimal newUsableRemainAmount = advanceBorrow.getUsableRemainAmount().subtract(repayLine.getCurrentVerAmount());
                advanceBorrow.setUsableRemainAmount(newUsableRemainAmount);
                BigDecimal newUsedRemainAmount = advanceBorrow.getUsedRemainAmount().add(repayLine.getCurrentVerAmount());
                advanceBorrow.setUsedRemainAmount(newUsedRemainAmount);
                advanceBorrowMap.put(advanceBorrowKey,advanceBorrow);
            }else{
                BudgetAdvanceBorrow advanceBorrow = advanceBorrowMap.get(advanceBorrowKey);
                BigDecimal newUsableRemainAmount = advanceBorrow.getUsableRemainAmount().subtract(repayLine.getCurrentVerAmount());
                advanceBorrow.setUsableRemainAmount(newUsableRemainAmount);
                BigDecimal newUsedRemainAmount = advanceBorrow.getUsedRemainAmount().add(repayLine.getCurrentVerAmount());
                advanceBorrow.setUsedRemainAmount(newUsedRemainAmount);
            }
        }
        afterRepaymentService.saveOrUpdate(afterRepayment);
        if (CollectionUtils.isNotEmpty(afterRepaymentLineList)){
            for (BudgetAfterRepaymentLine afterRepaymentLine : afterRepaymentLineList){
                afterRepaymentLine.setAfterRepayId(afterRepayment.getId());
            }
            afterRepaymentLineService.saveOrUpdateBatch(afterRepaymentLineList);
        }
        if (CollectionUtils.isNotEmpty(advanceBorrowLineList)){
            advanceBorrowLineService.saveOrUpdateBatch(advanceBorrowLineList);
        }
        if (MapUtils.isNotEmpty(advanceBorrowMap)){
            List<BudgetAdvanceBorrow> advanceBorrowList = new ArrayList<>(advanceBorrowMap.values());
            advanceBorrowService.saveOrUpdateBatch(advanceBorrowList);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean repayRemain2Occupy(PepaymentOrderInfo repayOrderInfo, boolean forbidFlag) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(repayOrderInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        List<BudgetAfterRepaymentLine> afterRepaymentLineList = afterRepaymentLineService.listByDocument(repayOrderInfo.getId(),
                FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
        Map<String,BudgetAdvanceBorrow> advanceBorrowMap = new HashMap<>();
        Map<String,BigDecimal> advanceBorrowRepayAmountMap = new HashMap<>();
        for (BudgetAfterRepaymentLine afterRepaymentLine : afterRepaymentLineList){
            //预算-事前借款行
            BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService.getById(afterRepaymentLine.getAdvanceBorrowLineId());
            //预算-事前借款行
            BudgetAdvanceBorrow borrowMoney = advanceBorrowService.getById(advanceBorrowLine.getAdvanceBorrowId());
            String advanceBorrowKey =
                    String.valueOf(borrowMoney.getDocumentId()) + "$" +  borrowMoney.getDocumentType();
            if (advanceBorrowMap.get(advanceBorrowKey) == null){
                advanceBorrowMap.put(advanceBorrowKey,borrowMoney);
                advanceBorrowRepayAmountMap.put(advanceBorrowKey,afterRepaymentLine.getRepayAmount());
            }else{
                BigDecimal newAdvanceBorrowRepayAmount = advanceBorrowRepayAmountMap.get(advanceBorrowKey).add(afterRepaymentLine.getRepayAmount());
                advanceBorrowRepayAmountMap.put(advanceBorrowKey,newAdvanceBorrowRepayAmount);
            }
        }
        for (Map.Entry<String,BudgetAdvanceBorrow> entry : advanceBorrowMap.entrySet()){
            BudgetAdvanceBorrow advanceBorrow = entry.getValue();
            BorrowMoneyInfo borrowMoneyInfo = borrowInfoService.getById(advanceBorrow.getDocumentId());
            BigDecimal repayAmount = advanceBorrowRepayAmountMap.get(entry.getKey());
            if (borrowMoneyInfo.getProjectId() != null) {
                BaseBudgetAccount budgetAccount = budgetAccountService.getById(advanceBorrow.getProjectBudgetAccountId());
                BudgetProjectBudget projectBudget = projectBudgetService
                        .selectByKeyWord(borrowMoneyInfo.getUnitCode(),
                                borrowMoneyInfo.getProjectId(), budgetAccount.getCode(),
                                LocalDateTimeUtils.formatTime(
                                        borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
                log.info("单位{},项目{},预算科目{},年度{}", borrowMoneyInfo.getUnitCode(),
                        borrowMoneyInfo.getProjectId(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
                AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
                BudgetAmount newAccountBudgetAmount = accountBudgetRemainFree(repayAmount,projectBudget.getAmount());
                projectBudgetService.updateProjectBudget(newAccountBudgetAmount,projectBudget);
            }else{
                BaseBudgetAccount budgetAccount = budgetAccountService.getById(advanceBorrow.getBasicBudgetAccountId());
                BudgetBasicBudget basicBudget = basicBudgetService
                        .selectByKeyWord(borrowMoneyInfo.getUnitCode(),
                                borrowMoneyInfo.getDeptCode(), budgetAccount.getCode(),
                                LocalDateTimeUtils.formatTime(
                                        borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
                log.info("单位{},部门{},预算科目{},年度{}",borrowMoneyInfo.getUnitCode(),
                        borrowMoneyInfo.getDeptCode(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
                AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
                BudgetAmount newAccountBudgetAmount = accountBudgetRemainFree(repayAmount,basicBudget.getAmount());
                basicBudgetService.updateBasicBudget(newAccountBudgetAmount,basicBudget);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean repayBudgetFree(FsscEums budgetPhase, PepaymentOrderInfo repayOrderInfo) {
        DeptVo deptVo = commonServices.getCurrentDept();
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(
                FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue(), deptVo.getDeptCode());
        AssertUtils.asserts(repayOrderInfo != null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        if (documentType == null || !FsscEums.VALID.getValue()
                .equals(documentType.getValidFlag())) {
            throw new FSSCException(FsscErrorType.DOCUMENT_NOT_FIND_OR_INVALID);
        }
        if (!FsscEums.YES.getValue().equals(documentType.getBudgetControlFlag())) {
            return true;
        }
        BudgetAfterRepayment afterRepayment = afterRepaymentService.getByDocument(repayOrderInfo.getId(),
                FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
        List<BudgetAfterRepaymentLine> afterRepaymentLineList = afterRepaymentLineService.listByDocument(repayOrderInfo.getId(),
                FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue());
        List<BudgetAdvanceBorrowLine> advanceBorrowLineList = new ArrayList<>(afterRepaymentLineList.size());
        Map<String,BudgetAdvanceBorrow> advanceBorrowMap = new HashMap<>();
        Map<String,BigDecimal> advanceBorrowRepayAmountMap = new HashMap<>();
        for (BudgetAfterRepaymentLine afterRepaymentLine : afterRepaymentLineList){
            //预算-事前借款行,还原行的上可用预算保留金额
            BudgetAdvanceBorrowLine advanceBorrowLine = advanceBorrowLineService.getById(afterRepaymentLine.getAdvanceBorrowLineId());
            BigDecimal newLineUsableRemainAmount = advanceBorrowLine.getUsableRemainAmount().add(afterRepaymentLine.getRepayAmount());
            advanceBorrowLine.setUsableRemainAmount(newLineUsableRemainAmount);
            BigDecimal newLineUsedRemainAmount = advanceBorrowLine.getUsedRemainAmount().subtract(afterRepaymentLine.getRepayAmount());
            advanceBorrowLine.setUsedRemainAmount(newLineUsedRemainAmount);
            advanceBorrowLineList.add(advanceBorrowLine);
            String advanceBorrowKey =
                    String.valueOf(advanceBorrowLine.getDocumentId()) + "$" +  advanceBorrowLine.getDocumentType();
            BudgetAdvanceBorrow advanceBorrow = advanceBorrowMap.get(advanceBorrowKey);
            if (advanceBorrow == null){
                //预算-事前借款头,还原头的上可用预算保留金额
                advanceBorrow = advanceBorrowService.getById(advanceBorrowLine.getAdvanceBorrowId());
                BigDecimal newUsableRemainAmount = advanceBorrow.getUsableRemainAmount().add(afterRepaymentLine.getRepayAmount());
                advanceBorrow.setUsableRemainAmount(newUsableRemainAmount);
                BigDecimal newUsedRemainAmount = advanceBorrow.getUsedRemainAmount().subtract(afterRepaymentLine.getRepayAmount());
                advanceBorrow.setUsedRemainAmount(newUsedRemainAmount);
                advanceBorrowMap.put(advanceBorrowKey,advanceBorrow);
                BigDecimal releaseAmount = afterRepaymentLine.getRepayAmount();
                advanceBorrowRepayAmountMap.put(advanceBorrowKey,releaseAmount);
            }else{
                BigDecimal newUsableRemainAmount = advanceBorrow.getUsableRemainAmount().add(afterRepaymentLine.getRepayAmount());
                advanceBorrow.setUsableRemainAmount(newUsableRemainAmount);
                BigDecimal newUsedRemainAmount = advanceBorrow.getUsedRemainAmount().subtract(afterRepaymentLine.getRepayAmount());
                advanceBorrow.setUsedRemainAmount(newUsedRemainAmount);
                BigDecimal releaseAmount = advanceBorrowRepayAmountMap.get(advanceBorrowKey).add(afterRepaymentLine.getRepayAmount());
                advanceBorrowRepayAmountMap.put(advanceBorrowKey,releaseAmount);
            }
        }
        //不管是已审批还是流程中的释放,都需要环境借款单头、行上的可用预算保留金额
        if (CollectionUtils.isNotEmpty(advanceBorrowLineList)){
            advanceBorrowLineService.saveOrUpdateBatch(advanceBorrowLineList);
        }
        if (MapUtils.isNotEmpty(advanceBorrowMap)){
            List<BudgetAdvanceBorrow> advanceBorrowList = new ArrayList<>(advanceBorrowMap.values());
            advanceBorrowService.saveOrUpdateBatch(advanceBorrowList);
        }
        if (FsscEums.BUDGET_PHASE_PROCESSED.equals(budgetPhase)) {
            //已审批后 释放
            afterRepayment.setDocumentStatus(FsscEums.DOCUMENT_BUDGET_STATUS_INVALID.getValue());
            afterRepaymentService.updateById(afterRepayment);
            for (Map.Entry<String,BudgetAdvanceBorrow> entry : advanceBorrowMap.entrySet()){
                BudgetAdvanceBorrow advanceBorrow = entry.getValue();
                BorrowMoneyInfo borrowMoneyInfo = borrowInfoService.getById(advanceBorrow.getDocumentId());
                BigDecimal repayAmount = advanceBorrowRepayAmountMap.get(entry.getKey());
                if (borrowMoneyInfo.getProjectId() != null) {
                    BaseBudgetAccount budgetAccount = budgetAccountService.getById(advanceBorrow.getProjectBudgetAccountId());
                    BudgetProjectBudget projectBudget = projectBudgetService
                            .selectByKeyWord(borrowMoneyInfo.getUnitCode(),
                                    borrowMoneyInfo.getProjectId(), budgetAccount.getCode(),
                                    LocalDateTimeUtils.formatTime(
                                            borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
                    log.info("单位{},项目{},预算科目{},年度{}", borrowMoneyInfo.getUnitCode(),
                            borrowMoneyInfo.getProjectId(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                    borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
                    AssertUtils.asserts(projectBudget != null, FsscErrorType.PROJECT_BUDGET_NOT_FIND);
                    BudgetAmount newAccountBudgetAmount = checkAndUpdateAccountBudget(projectBudget.getAmount(),repayAmount,true,false);
                    projectBudgetService.updateProjectBudget(newAccountBudgetAmount,projectBudget);
                }else{
                    BaseBudgetAccount budgetAccount = budgetAccountService.getById(advanceBorrow.getBasicBudgetAccountId());
                    BudgetBasicBudget basicBudget = basicBudgetService
                            .selectByKeyWord(borrowMoneyInfo.getUnitCode(),
                                    borrowMoneyInfo.getDeptCode(), budgetAccount.getCode(),
                                    LocalDateTimeUtils.formatTime(
                                            borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
                    log.info("单位{},部门{},预算科目{},年度{}",borrowMoneyInfo.getUnitCode(),
                            borrowMoneyInfo.getDeptCode(), budgetAccount.getCode(), LocalDateTimeUtils.formatTime(
                                    borrowMoneyInfo.getCreateTime(), DateUtil.FM_YYYY));
                    AssertUtils.asserts(basicBudget != null, FsscErrorType.BASIC_BUDGET_NOT_FIND);
                    BudgetAmount newAccountBudgetAmount = checkAndUpdateAccountBudget(basicBudget.getAmount(),repayAmount,true,false);
                    basicBudgetService.updateBasicBudget(newAccountBudgetAmount,basicBudget);
                }
            }
        }else{
            //流程中 释放
            List<Long> ids = afterRepaymentLineList.stream().map(BudgetAfterRepaymentLine :: getId).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(ids)) {
                afterRepaymentLineService.removeByIds(ids);
            }
            afterRepaymentService.removeById(afterRepayment.getId());
        }
        return true;
    }
}
