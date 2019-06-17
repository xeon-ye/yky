package com.deloitte.services.fssc.budget.service.impl;

import com.deloitte.platform.api.fssc.budget.vo.BudgetPublicAdjustLineVo;
import com.deloitte.platform.api.fssc.budget.vo.BudgetPublicAdjustVo;
import com.deloitte.platform.api.isump.OrganizationClient;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.service.IBaseBudgetAccountService;
import com.deloitte.services.fssc.budget.entity.BudgetBasicBudget;
import com.deloitte.services.fssc.budget.entity.BudgetDetailingAdjustHead;
import com.deloitte.services.fssc.budget.entity.BudgetDetailingAdjustLine;
import com.deloitte.services.fssc.budget.service.*;
import com.deloitte.services.fssc.business.edu.entity.FundsApplyLine;
import com.deloitte.services.fssc.business.edu.service.IFundsApplyLineService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Service
public class BudgetAdjustServiceImpl implements IBudgetAdjustService {

    @Autowired
    IBudgetPublicAdjustService publicAdjustService;

    @Autowired
    IBudgetDetailingAdjustHeadService detailingAdjustHeadService;

    @Autowired
    IBudgetDetailingAdjustLineService detailingAdjustLineService;

    @Autowired
    IFundsApplyLineService applyLineService;

    @Autowired
    IBudgetBasicBudgetService basicBudgetService;

    @Autowired
    OrganizationClient organizationClient;

    @Autowired
    FsscCommonServices commonServices;

    @Autowired
    IBaseBudgetAccountService budgetAccountService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean preAdjust(Long documentId, String documentTable) {
        if (FsscTableNameEums.BUDGET_PUBLIC_ADJUST.getValue().equals(documentTable)) {
            publicPreAdjust(documentId);
        }else if (FsscTableNameEums.BUDGET_DETAILING_ADJUST_HEAD.getValue().equals(documentTable)){
            BudgetDetailingAdjustHead detailingAdjustHead = detailingAdjustHeadService.getById(documentId);
            FundsApplyLine applyLine = applyLineService.getByKeyWord(detailingAdjustHead.getRelatedDocumentId(),detailingAdjustHead.getLineNum());
            applyLine.setIsConnected(FsscEums.YES.getValue());
            applyLineService.saveOrUpdate(applyLine);
        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean toAdjust(Long documentId, String documentTable) {
        DeptVo deptVo = commonServices.getCurrentDept();
        OrganizationVo organizationVo = commonServices.getCurrentOrg();
        UserVo userVo = commonServices.getCurrentUser();
        if (FsscTableNameEums.BUDGET_PUBLIC_ADJUST.getValue().equals(documentTable)){
            publicDoAdjust(documentId);
        }else if (FsscTableNameEums.BUDGET_DETAILING_ADJUST_HEAD.getValue().equals(documentTable)){
            BudgetDetailingAdjustHead detailingAdjustHead = detailingAdjustHeadService.getById(documentId);
            List<BudgetDetailingAdjustLine> detailingAdjustLineList = detailingAdjustLineService.selectList(documentId);
            String budgetAnnual = DateUtil.dateToString(LocalDateTimeUtils.convertLDTToDate(detailingAdjustHead.getCreateTime()),DateUtil.FM_YYYY);
            for (BudgetDetailingAdjustLine adjustLine : detailingAdjustLineList){
                BudgetBasicBudget basicBudget = basicBudgetService.selectByKeyWord(detailingAdjustHead.getUnitCode(),
                        detailingAdjustHead.getDeptCode(),adjustLine.getBudgetAccountCode(),budgetAnnual);
                if (basicBudget != null) {
                    BigDecimal newBudgetAmount = basicBudget.getBudgetAmount().add(adjustLine.getAllocationAmount());
                    BigDecimal newUsableAmount = basicBudget.getBudgetAmount().subtract(adjustLine.getAllocationAmount());
                    basicBudget.setBudgetAmount(newBudgetAmount);
                    basicBudget.setBudgetUsableAmount(newUsableAmount);
                    basicBudgetService.saveOrUpdate(basicBudget);
                }else {
                    //创建基础预算,保护期间(月)预算,及相应年预算
                    BaseBudgetAccount budgetAccount = budgetAccountService.getBudgetAccountByCode(adjustLine.getBudgetAccountCode(),
                            FsscEums.BUDGET_TYPE_BASIC.getValue());
                    BudgetBasicBudget basicBudgetPeriod = constructBasicBudget(deptVo, organizationVo, userVo, adjustLine, budgetAccount);
                    basicBudgetPeriod.setBudgetPeriod(DateUtil.dateToString(Calendar.getInstance().getTime(), DateUtil.FM_YYYY_MM));
                    basicBudgetPeriod.setBudgetAnnual(budgetAnnual);
                    basicBudgetService.saveOrUpdate(basicBudgetPeriod);
                    BudgetBasicBudget basicBudgetAnnual = constructBasicBudget(deptVo, organizationVo, userVo, adjustLine, budgetAccount);
                    basicBudgetAnnual.setBudgetAnnual(budgetAnnual);
                    basicBudgetAnnual.setTotalFlag(FsscEums.YES.getValue());
                    basicBudgetService.saveOrUpdate(basicBudgetAnnual);
                }
            }
        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean unAdjust(Long documentId, String documentTable) {
        if (FsscTableNameEums.BUDGET_PUBLIC_ADJUST.getValue().equals(documentTable)){
            publicUnAdjust(documentId);
        }else if (FsscTableNameEums.BUDGET_DETAILING_ADJUST_HEAD.getValue().equals(documentTable)){
            BudgetDetailingAdjustHead detailingAdjustHead = detailingAdjustHeadService.getById(documentId);
            FundsApplyLine applyLine = applyLineService.getByKeyWord(detailingAdjustHead.getRelatedDocumentId(),detailingAdjustHead.getLineNum());
            applyLine.setIsConnected(FsscEums.NO.getValue());
            applyLineService.saveOrUpdate(applyLine);
        }
        return true;
    }

    /**
     * 将调整金额加到预算科目金额上
     * @param adjustVo
     * @param budgetAnnual
     * @param lineVo
     */
    private void accountBudgetAmountAdd(BudgetPublicAdjustVo adjustVo, String budgetAnnual, BudgetPublicAdjustLineVo lineVo) {
        BudgetBasicBudget budgetBasicBudget = basicBudgetService.selectByKeyWord(adjustVo.getUnitCode(),adjustVo.getDeptCode(),
                lineVo.getBudgetAccountCode(),budgetAnnual);
        BigDecimal newBudgetAmount = budgetBasicBudget.getBudgetAmount().add(lineVo.getAdjustAmount());
        BigDecimal newUsableAmount = budgetBasicBudget.getBudgetUsableAmount().add(lineVo.getAdjustAmount());
        AssertUtils.asserts(newUsableAmount.compareTo(BigDecimal.ZERO) >= 0,FsscErrorType.ADJUSTED_USABLE_AMOUNT_LESS_THAN_ZERO);
        budgetBasicBudget.setBudgetAmount(newBudgetAmount);
        budgetBasicBudget.setBudgetUsableAmount(newUsableAmount);
        basicBudgetService.saveOrUpdate(budgetBasicBudget);
    }

    /**
     * 公用经费预算调整 取消调整
     * @param documentId
     */
    private void publicUnAdjust(Long documentId) {
        BudgetPublicAdjustVo adjustVo = publicAdjustService.findInfoById(documentId,false);
        String budgetAnnual = DateUtil.dateToString(LocalDateTimeUtils.convertLDTToDate(adjustVo.getCreateTime()),DateUtil.FM_YYYY);
        //调整总金额
        BigDecimal adjustSumAmount = BigDecimal.ZERO;
        for (BudgetPublicAdjustLineVo lineVo : adjustVo.getLineVoList()){
            if (lineVo.getAdjustAmount().compareTo(BigDecimal.ZERO) < 0){
                //预算科目的预算金额如果已调减,将还原
                BudgetBasicBudget budgetBasicBudget = basicBudgetService.selectByKeyWord(adjustVo.getUnitCode(),adjustVo.getDeptCode(),
                        lineVo.getBudgetAccountCode(),budgetAnnual);
                BigDecimal newBudgetAmount = budgetBasicBudget.getBudgetAmount().subtract(lineVo.getAdjustAmount());
                BigDecimal newUsableAmount = budgetBasicBudget.getBudgetUsableAmount().subtract(lineVo.getAdjustAmount());
                budgetBasicBudget.setBudgetAmount(newBudgetAmount);
                budgetBasicBudget.setBudgetUsableAmount(newUsableAmount);
                basicBudgetService.saveOrUpdate(budgetBasicBudget);
            }
            adjustSumAmount = adjustSumAmount.add(lineVo.getAdjustAmount());
        }
        Result<List<OrganizationVo>> fictionOrgResult = organizationClient.getOrgFinctionByCode(adjustVo.getUnitCode());
        if (fictionOrgResult.isFail() || CollectionUtils.isEmpty(fictionOrgResult.getData())){
            throw new FSSCException(FsscErrorType.GET_FICTION_ORG_FAIL);
        }
        BudgetBasicBudget cashPool = basicBudgetService.selectByKeyWord(adjustVo.getUnitCode(),
                fictionOrgResult.getData().get(0).getCode(), FsscEums.BUDGET_CASH_POOLING_ACCOUNT_CODE.getValue(),budgetAnnual);
        if (cashPool == null){
            throw new FSSCException(FsscErrorType.CASH_POOLING_NOT_FIND);
        }
        if (adjustSumAmount.compareTo(BigDecimal.ZERO) > 0){
            //调整金额大于0,提交审批时,已使用了资金池的保留额度,所以需要还原额度
            BigDecimal newRemainAmount = cashPool.getBudgetRemainAmount().subtract(adjustSumAmount);
            BigDecimal newRemainOccupyAmount = newRemainAmount.add(cashPool.getBudgetOccupyAmount());
            BigDecimal newUsableAmount = cashPool.getBudgetAmount().subtract(newRemainOccupyAmount);
            cashPool.setBudgetRemainAmount(newRemainAmount);
            cashPool.setBudgetUsableAmount(newUsableAmount);
            basicBudgetService.saveOrUpdate(cashPool);
        }
    }

    /**
     * 公用经费预算调整 调整前处理
     * @param documentId
     */
    private void publicPreAdjust(Long documentId) {
        BudgetPublicAdjustVo adjustVo = publicAdjustService.findInfoById(documentId,false);
        String budgetAnnual = DateUtil.dateToString(LocalDateTimeUtils.convertLDTToDate(adjustVo.getCreateTime()),DateUtil.FM_YYYY);
        //调整总金额
        BigDecimal adjustSumAmount = BigDecimal.ZERO;
        for (BudgetPublicAdjustLineVo lineVo : adjustVo.getLineVoList()){
            if (lineVo.getAdjustAmount().compareTo(BigDecimal.ZERO) < 0){
                accountBudgetAmountAdd(adjustVo, budgetAnnual, lineVo);
            }
            adjustSumAmount = adjustSumAmount.add(lineVo.getAdjustAmount());
        }
        Result<List<OrganizationVo>> fictionOrgResult = organizationClient.getOrgFinctionByCode(adjustVo.getUnitCode());
        if (fictionOrgResult.isFail() || CollectionUtils.isEmpty(fictionOrgResult.getData())){
            throw new FSSCException(FsscErrorType.GET_FICTION_ORG_FAIL);
        }
        BudgetBasicBudget cashPool = basicBudgetService.selectByKeyWord(adjustVo.getUnitCode(),
                fictionOrgResult.getData().get(0).getCode(), FsscEums.BUDGET_CASH_POOLING_ACCOUNT_CODE.getValue(),budgetAnnual);
        if (cashPool == null){
            throw new FSSCException(FsscErrorType.CASH_POOLING_NOT_FIND);
        }
        if (adjustSumAmount.compareTo(BigDecimal.ZERO) > 0){
            //调整金额大于0,相当于在提交时减少资金池的可用金额
            BigDecimal newRemainAmount = cashPool.getBudgetRemainAmount().add(adjustSumAmount);
            BigDecimal newRemainOccupyAmount = newRemainAmount.add(cashPool.getBudgetOccupyAmount());
            BigDecimal newUsableAmount = cashPool.getBudgetAmount().subtract(newRemainOccupyAmount);
            cashPool.setBudgetRemainAmount(newRemainAmount);
            cashPool.setBudgetUsableAmount(newUsableAmount);
            AssertUtils.asserts(newUsableAmount.compareTo(BigDecimal.ZERO) >= 0,FsscErrorType.CASH_POOLING_AMOUNT_NO_ENOUGH);
            basicBudgetService.saveOrUpdate(cashPool);
        }
    }

    private void publicDoAdjust(Long documentId) {
        BudgetPublicAdjustVo adjustVo = publicAdjustService.findInfoById(documentId,false);
        String budgetAnnual = DateUtil.dateToString(LocalDateTimeUtils.convertLDTToDate(adjustVo.getCreateTime()),DateUtil.FM_YYYY);
        //调整总金额
        BigDecimal adjustSumAmount = BigDecimal.ZERO;
        for (BudgetPublicAdjustLineVo lineVo : adjustVo.getLineVoList()){
            if (lineVo.getAdjustAmount().compareTo(BigDecimal.ZERO) > 0){
                accountBudgetAmountAdd(adjustVo, budgetAnnual, lineVo);
            }
            adjustSumAmount = adjustSumAmount.add(lineVo.getAdjustAmount());
        }
        Result<List<OrganizationVo>> fictionOrgResult = organizationClient.getOrgFinctionByCode(adjustVo.getUnitCode());
        if (fictionOrgResult.isFail() || CollectionUtils.isEmpty(fictionOrgResult.getData())){
            throw new FSSCException(FsscErrorType.GET_FICTION_ORG_FAIL);
        }
        BudgetBasicBudget cashPool = basicBudgetService.selectByKeyWord(adjustVo.getUnitCode(),
                fictionOrgResult.getData().get(0).getCode(), FsscEums.BUDGET_CASH_POOLING_ACCOUNT_CODE.getValue(),budgetAnnual);
        AssertUtils.asserts(cashPool != null,FsscErrorType.CASH_POOLING_NOT_FIND);
        if (adjustSumAmount.compareTo(BigDecimal.ZERO) < 0){
            //调整金额小于0,直接在审批通过后增加资金池的可用金额
            BigDecimal newBudgetAmount = cashPool.getBudgetAmount().subtract(adjustSumAmount);
            BigDecimal newUsableAmount = cashPool.getBudgetUsableAmount().subtract(adjustSumAmount);
            cashPool.setBudgetAmount(newBudgetAmount);
            cashPool.setBudgetUsableAmount(newUsableAmount);
            basicBudgetService.saveOrUpdate(cashPool);
        }
        if (adjustSumAmount.compareTo(BigDecimal.ZERO) > 0){
            //调整金额大于0,资金池的可用额在提交时已减扣除,需要将已使用保留额度变占用
            BigDecimal newRemainAmount = cashPool.getBudgetRemainAmount().subtract(adjustSumAmount);
            BigDecimal newOccupyAmount = cashPool.getBudgetOccupyAmount().add(adjustSumAmount);
            cashPool.setBudgetRemainAmount(newRemainAmount);
            cashPool.setBudgetOccupyAmount(newOccupyAmount);
            basicBudgetService.saveOrUpdate(cashPool);
        }
    }

    /**
     * 根据预算细化调整及预算科目,创建基础预算
     * @param deptVo
     * @param organizationVo
     * @param userVo
     * @param adjustLine
     * @param budgetAccount
     * @return
     */
    private BudgetBasicBudget constructBasicBudget(DeptVo deptVo, OrganizationVo organizationVo, UserVo userVo,
                                                   BudgetDetailingAdjustLine adjustLine, BaseBudgetAccount budgetAccount) {
        BudgetBasicBudget basicBudget = new BudgetBasicBudget();
        basicBudget.setBudgetAmount(adjustLine.getAllocationAmount());
        basicBudget.setBudgetRemainAmount(BigDecimal.ZERO);
        basicBudget.setBudgetOccupyAmount(BigDecimal.ZERO);
        basicBudget.setBudgetUsableAmount(adjustLine.getAllocationAmount());
        basicBudget.setCreateBy(userVo.getId());
        basicBudget.setApplyForPerson(userVo.getEmpNo());
        basicBudget.setOrgUnitId(Long.valueOf(deptVo.getDeptId()));
        basicBudget.setOrgUnitCode(deptVo.getDeptCode());
        basicBudget.setLevel1OfficeCode(organizationVo.getCode());
        basicBudget.setLevel2OfficeCode(organizationVo.getCode());
        basicBudget.setBudgetAccountId(budgetAccount.getId());
        basicBudget.setBudgetAccountCode(budgetAccount.getCode());
        return basicBudget;
    }

}
