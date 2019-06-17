package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseBudgetAccountQueryForm;
import com.deloitte.platform.api.fssc.budget.param.BudgetPublicAdjustQueryForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetPublicAdjustLineVo;
import com.deloitte.platform.api.fssc.budget.vo.BudgetPublicAdjustVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.base.service.IBaseBudgetAccountService;
import com.deloitte.services.fssc.budget.entity.BudgetBasicBudget;
import com.deloitte.services.fssc.budget.entity.BudgetPublicAdjust;
import com.deloitte.services.fssc.budget.entity.BudgetPublicAdjustLine;
import com.deloitte.services.fssc.budget.mapper.BudgetPublicAdjustMapper;
import com.deloitte.services.fssc.budget.service.IBudgetBasicBudgetService;
import com.deloitte.services.fssc.budget.service.IBudgetPublicAdjustLineService;
import com.deloitte.services.fssc.budget.service.IBudgetPublicAdjustService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.util.DateUtil;
import com.deloitte.services.fssc.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-30
 * @Description :  BudgetPublicAdjust服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetPublicAdjustServiceImpl extends ServiceImpl<BudgetPublicAdjustMapper, BudgetPublicAdjust>
        implements IBudgetPublicAdjustService {


    @Autowired
    private BudgetPublicAdjustMapper adjustMapper;

    @Autowired
    private IBudgetPublicAdjustLineService adjustLineService;

    @Autowired
    private IBudgetBasicBudgetService basicBudgetService;

    @Autowired
    private IBaseBudgetAccountService budgetAccountService;

    @Override
    public BudgetPublicAdjustVo findInfoById(Long adjustId, boolean readLast) {
        BudgetPublicAdjust adjust = this.getById(adjustId);
        if (adjust == null){
            return new BudgetPublicAdjustVo();
        }
        List<BudgetPublicAdjustLine> adjustLineList = adjustLineService.selectList(adjustId);
        if (readLast) {
            readLastBasicBudget(adjust, adjustLineList);
        }
        BaseBudgetAccountQueryForm queryForm = new BaseBudgetAccountQueryForm();
        queryForm.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
        Map<String,String> codeNameMap = budgetAccountService.getCodeNameMap(queryForm);
        for (BudgetPublicAdjustLine adjustLine : adjustLineList){
            adjustLine.setBudgetAccountName(codeNameMap.get(adjustLine.getBudgetAccountCode()));
        }
        BudgetPublicAdjustVo adjustVo = new BeanUtils<BudgetPublicAdjustVo>().copyObj(adjust, BudgetPublicAdjustVo.class);
        List<BudgetPublicAdjustLineVo> adjustLineVoList = new BeanUtils<BudgetPublicAdjustLineVo>()
                .copyObjs(adjustLineList,BudgetPublicAdjustLineVo.class);
        adjustVo.setLineVoList(adjustLineVoList);
        return adjustVo;
    }


    @Override
    public void readLastBasicBudget(BudgetPublicAdjust adjust,  List<BudgetPublicAdjustLine> adjustLineList) {
        if (adjust.getCreateTime() == null){
            adjust.setCreateTime(LocalDateTimeUtils.convertDateToLDT(Calendar.getInstance().getTime()));
        }
        String budgetAnnual = DateUtil.dateToString(LocalDateTimeUtils.convertLDTToDate(adjust.getCreateTime()),DateUtil.FM_YYYY);
        BudgetBasicBudget officeBudget = basicBudgetService.selectByUnitOffice(adjust.getUnitCode(), adjust.getDeptCode(), budgetAnnual);
        adjust.setAnnualBudgetAmount(officeBudget.getBudgetAmount());
        adjust.setPeriodBudgetAmount(officeBudget.getBudgetAmount());
        adjust.setPeriodUsableAmount(officeBudget.getBudgetUsableAmount());
        List<BudgetBasicBudget> accountBasicBudgetList = basicBudgetService.selectAccountByUnitOffice(adjust.getUnitCode(),
                adjust.getDeptCode(), budgetAnnual);
        Map<String,BudgetBasicBudget> accountBasicBudgetMap = new HashMap<>(accountBasicBudgetList.size());
        for(BudgetBasicBudget accountBasicBudget : accountBasicBudgetList){
            accountBasicBudgetMap.put(accountBasicBudget.getBudgetAccountCode(),accountBasicBudget);
        }
        if (CollectionUtils.isNotEmpty(adjustLineList)) {
            for (BudgetPublicAdjustLine line : adjustLineList) {
                BudgetBasicBudget accountBasicBudget = accountBasicBudgetMap.get(line.getBudgetAccountCode());
                line.setPeriodBudgetAmount(accountBasicBudget.getBudgetAmount());
                BigDecimal accountUsedBudgetAmount = accountBasicBudget.getBudgetRemainAmount().add(accountBasicBudget.getBudgetOccupyAmount());
                line.setPeriodUsedAmount(accountUsedBudgetAmount);
                line.setPeriodUsableAmount(accountBasicBudget.getBudgetUsableAmount());
                //重置调整后的金额
                BigDecimal adjustedBudgetAmount = accountBasicBudget.getBudgetAmount().add(line.getAdjustAmount());
                BigDecimal adjustedUsableAmount = accountBasicBudget.getBudgetUsableAmount().add(line.getAdjustAmount());
                line.setAdjustedPeriodBudgetAmount(adjustedBudgetAmount);
                line.setAdjustedPeriodUsableAmount(adjustedUsableAmount);
            }
        }
    }

    @Override
    public IPage<BudgetPublicAdjust> selectPage(BudgetPublicAdjustQueryForm queryForm) {
        QueryWrapper<BudgetPublicAdjust> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return adjustMapper.selectPage(new Page<>(queryForm.getCurrentPage(),
                queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public BudgetPublicAdjust getInitAdjust(DeptVo deptVo, OrganizationVo organizationVo, String annual) {
        BudgetPublicAdjust adjust = new BudgetPublicAdjust();
        adjust.setUnitCode(deptVo.getDeptCode());
        adjust.setUnitName(deptVo.getDeptName());
        adjust.setUnitId(Long.valueOf(deptVo.getDeptId()));
        adjust.setDeptCode(organizationVo.getCode());
        adjust.setDeptName(organizationVo.getName());
        adjust.setDeptId(StringUtil.castTolong(organizationVo.getId()));
        String budgetAnnual = DateUtil.dateToString(Calendar.getInstance().getTime(),DateUtil.FM_YYYY);
        BudgetBasicBudget officeBudget = basicBudgetService.selectByUnitOffice(adjust.getUnitCode(), adjust.getDeptCode(), budgetAnnual);
        if (officeBudget == null){
            throw new FSSCException(FsscErrorType.BASIC_BUDGET_NOT_FIND);
        }
        adjust.setAnnualBudgetAmount(officeBudget.getBudgetAmount());
        //BigDecimal usedBudgetAmount = officeBudget.getBudgetRemainAmount().add(officeBudget.getBudgetOccupyAmount());
        adjust.setPeriodBudgetAmount(officeBudget.getBudgetAmount());
        adjust.setPeriodUsableAmount(officeBudget.getBudgetUsableAmount());
        return adjust;
    }

    @Override
    public List<BudgetPublicAdjustLine> selectInitLine(String orgCode, String deptCode, String annual) {
        List<BudgetBasicBudget> basicBudgetList = basicBudgetService.selectAccountByUnitOffice(orgCode,
                deptCode, annual);
        if (CollectionUtils.isEmpty(basicBudgetList)){
            return Collections.EMPTY_LIST;
        }
        List<BudgetPublicAdjustLine> lineList = new ArrayList<>();
        BaseBudgetAccountQueryForm queryForm = new BaseBudgetAccountQueryForm();
        queryForm.setBudgetType(FsscEums.BUDGET_TYPE_BASIC.getValue());
        Map<String,String> codeNameMap = budgetAccountService.getCodeNameMap(queryForm);
        for (BudgetBasicBudget basicBudget : basicBudgetList){
            BudgetPublicAdjustLine line = new BudgetPublicAdjustLine();
            line.setBudgetAccountId(basicBudget.getBudgetAccountId());
            line.setBudgetAccountCode(basicBudget.getBudgetAccountCode());
            line.setBudgetAccountName(codeNameMap.get(basicBudget.getBudgetAccountCode()));
            line.setAdjustAmount(BigDecimal.ZERO);
            line.setPeriodBudgetAmount(basicBudget.getBudgetAmount());
            line.setAdjustedPeriodBudgetAmount(basicBudget.getBudgetAmount());
            line.setPeriodUsableAmount(basicBudget.getBudgetUsableAmount());
            line.setAdjustedPeriodUsableAmount(basicBudget.getBudgetUsableAmount());
            BigDecimal remainOccupyAmount = basicBudget.getBudgetRemainAmount().add(basicBudget.getBudgetOccupyAmount());
            line.setPeriodUsedAmount(remainOccupyAmount);
            lineList.add(line);
        }
        return lineList;
    }

    @Override
    public List<BudgetPublicAdjust> selectList(BudgetPublicAdjustQueryForm queryForm) {
        QueryWrapper<BudgetPublicAdjust> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return adjustMapper.selectList(queryWrapper);
    }


    /**
     * 通用查询
     * @param queryWrapper
     * @param queryForm
     * @return
     */
    private QueryWrapper<BudgetPublicAdjust> getQueryWrapper(QueryWrapper<BudgetPublicAdjust> queryWrapper,
                                                            BudgetPublicAdjustQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getDocumentNum())) {
            queryWrapper.like(BudgetPublicAdjust.DOCUMENT_NUM, queryForm.getDocumentNum());
        }
        if (StringUtils.isNotBlank(queryForm.getDocumentStatus())) {
            queryWrapper.eq(BudgetPublicAdjust.DOCUMENT_STATUS, queryForm.getDocumentStatus());
        }
        if (queryForm.getUnitId() != null) {
            queryWrapper.eq(BudgetPublicAdjust.UNIT_ID, queryForm.getUnitId());
        }
        if (StringUtils.isNotBlank(queryForm.getUnitCode())) {
            queryWrapper.eq(BudgetPublicAdjust.UNIT_CODE, queryForm.getUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getUnitName())) {
            queryWrapper.like(BudgetPublicAdjust.UNIT_NAME, queryForm.getUnitName());
        }
        if (queryForm.getDeptId() != null) {
            queryWrapper.eq(BudgetPublicAdjust.DEPT_ID, queryForm.getDeptId());
        }
        if (StringUtils.isNotBlank(queryForm.getDeptCode())) {
            queryWrapper.eq(BudgetPublicAdjust.DEPT_CODE, queryForm.getDeptCode());
        }
        if (StringUtils.isNotBlank(queryForm.getDeptName())) {
            queryWrapper.eq(BudgetPublicAdjust.DEPT_NAME, queryForm.getDeptName());
        }
        if (queryForm.getAnnualBudgetAmountDown() != null) {
            queryWrapper.ge(BudgetPublicAdjust.ANNUAL_BUDGET_AMOUNT, queryForm.getAnnualBudgetAmountDown());
        }
        if (queryForm.getAnnualBudgetAmountUp() != null) {
            queryWrapper.le(BudgetPublicAdjust.ANNUAL_BUDGET_AMOUNT, queryForm.getAnnualBudgetAmountUp());
        }
        if (queryForm.getMakeDocumentTimeStart() != null) {
            queryWrapper.ge(BudgetPublicAdjust.CREATE_TIME, queryForm.getMakeDocumentTimeStart());
        }
        if (queryForm.getMakeDocumentTimeEnd() != null) {
            queryWrapper.le(BudgetPublicAdjust.CREATE_TIME, queryForm.getMakeDocumentTimeEnd());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BudgetPublicAdjust.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.like(BudgetPublicAdjust.ORG_PATH, queryForm.getOrgPath());
        }
        if (StringUtils.isNotBlank(queryForm.getApplyForPerson())) {
            queryWrapper.eq(BudgetPublicAdjust.APPLY_FOR_PERSON, queryForm.getApplyForPerson());
        }
        if (StringUtils.isNotBlank(queryForm.getApplyForPersonName())) {
            queryWrapper.like(BudgetPublicAdjust.APPLY_FOR_PERSON_NAME, queryForm.getApplyForPersonName());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetPublicAdjust.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetPublicAdjust.UPDATE_BY, queryForm.getUpdateBy());
        }
        queryWrapper.orderByDesc(BudgetPublicAdjust.CREATE_TIME);
        return queryWrapper;
    }
}

