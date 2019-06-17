package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.budget.param.BudgetBasicBudgetQueryForm;
import com.deloitte.services.fssc.budget.entity.Budget;
import com.deloitte.services.fssc.budget.entity.BudgetAmount;
import com.deloitte.services.fssc.budget.entity.BudgetBasicBudget;
import com.deloitte.services.fssc.budget.mapper.BudgetBasicBudgetMapper;
import com.deloitte.services.fssc.budget.service.IBudgetBasicBudgetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.eums.FsscEums;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description :  BudgetBasicBudget服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class BudgetBasicBudgetServiceImpl extends
        ServiceImpl<BudgetBasicBudgetMapper, BudgetBasicBudget> implements
        IBudgetBasicBudgetService {


    @Autowired
    private BudgetBasicBudgetMapper budgetBasicBudgetMapper;

    @Override
    public IPage<BudgetBasicBudget> selectPage(BudgetBasicBudgetQueryForm queryForm) {
        QueryWrapper<BudgetBasicBudget> queryWrapper = new QueryWrapper<BudgetBasicBudget>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetBasicBudgetMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }

    @Override
    public List<BudgetBasicBudget> selectList(BudgetBasicBudgetQueryForm queryForm) {
        QueryWrapper<BudgetBasicBudget> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetBasicBudgetMapper.selectList(queryWrapper);
    }

    @Override
    public BudgetBasicBudget selectByKeyWord(String unitCode, String deptCode,
            String budgetAccountCode, String budgetAnnual) {
        log.info("selectByKeyWord,unitCode :{},deptCode: {},selectByKeyWord :{} ,budgetAnnual: {}",
                unitCode,deptCode,budgetAccountCode,budgetAnnual);
        QueryWrapper<BudgetBasicBudget> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetBasicBudget.ORG_UNIT_CODE,unitCode);
        queryWrapper.eq(BudgetBasicBudget.LEVEL2_OFFICE_CODE,deptCode);
        queryWrapper.eq(BudgetBasicBudget.BUDGET_ACCOUNT_CODE,budgetAccountCode);
        queryWrapper.eq(BudgetBasicBudget.BUDGET_ANNUAL,budgetAnnual);
        queryWrapper.eq(BudgetBasicBudget.TOTAL_FLAG, FsscEums.YES.getValue());
        return budgetBasicBudgetMapper.selectOne(queryWrapper);
    }

    @Override
    public BudgetBasicBudget selectByKeyWord(Long unitId, String deptCode, String budgetAccountCode,
            String budgetAnnual) {
        log.info("selectByKeyWord,unitId :{},deptCode: {},budgetAccountCode :{} ,budgetAnnual: {}",
                unitId,deptCode,budgetAccountCode,budgetAnnual);
        QueryWrapper<BudgetBasicBudget> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetBasicBudget.ORG_UNIT_ID,unitId);
        queryWrapper.eq(BudgetBasicBudget.LEVEL2_OFFICE_CODE,deptCode);
        queryWrapper.eq(BudgetBasicBudget.BUDGET_ACCOUNT_CODE,budgetAccountCode);
        queryWrapper.eq(BudgetBasicBudget.BUDGET_ANNUAL,budgetAnnual);
        queryWrapper.eq(BudgetBasicBudget.TOTAL_FLAG, FsscEums.YES.getValue());
        return budgetBasicBudgetMapper.selectOne(queryWrapper);
    }

    @Override
    public BudgetBasicBudget selectMonthByKeyWord(String unitCode, String deptCode,
            String budgetAccountCode, String budgetAnnual, String budgetPeriod) {
        QueryWrapper<BudgetBasicBudget> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetBasicBudget.ORG_UNIT_CODE,unitCode);
        queryWrapper.eq(BudgetBasicBudget.LEVEL2_OFFICE_CODE,deptCode);
        queryWrapper.eq(BudgetBasicBudget.BUDGET_ACCOUNT_CODE,budgetAccountCode);
        queryWrapper.eq(BudgetBasicBudget.BUDGET_ANNUAL,budgetAnnual);
        queryWrapper.eq(BudgetBasicBudget.BUDGET_PERIOD,budgetPeriod);
        queryWrapper.eq(BudgetBasicBudget.TOTAL_FLAG, FsscEums.NO.getValue());
        return budgetBasicBudgetMapper.selectOne(queryWrapper);
    }

    @Override
    public BudgetBasicBudget selectByUnitOffice(String unitCode, String officeCode, String budgetAnnual) {
        return budgetBasicBudgetMapper.selectByUnitOffice(unitCode,officeCode,budgetAnnual);
    }

    @Override
    public List<BudgetBasicBudget> selectAccountByUnitOffice(String unitCode, String officeCode, String budgetAnnual) {
        QueryWrapper<BudgetBasicBudget> queryWrapper = new QueryWrapper<>();
        BudgetBasicBudgetQueryForm queryForm = new BudgetBasicBudgetQueryForm();
        queryForm.setBudgetAnnual(budgetAnnual);
        queryForm.setOrgUnitCode(unitCode);
        queryForm.setLevel2OfficeCode(officeCode);
        queryForm.setTotalFlag(FsscEums.YES.getValue());
        queryWrapper = this.getQueryWrapper(queryWrapper,queryForm);
        return this.list(queryWrapper);
    }

    @Override
    public void updateBasicBudget(BigDecimal newRemainAmount, BigDecimal newOccupyAmount,
            BigDecimal newUsableAmount, BudgetBasicBudget basicBudget) {
        basicBudget.setBudgetRemainAmount(newRemainAmount);
        basicBudget.setBudgetOccupyAmount(newOccupyAmount);
        basicBudget.setBudgetUsableAmount(newUsableAmount);
        this.updateById(basicBudget);
        log.info("基本预算, 保留额: {},占用额: {}, 预算额: {},可用额: {}",
                newRemainAmount,newOccupyAmount,basicBudget.getBudgetAmount(),newUsableAmount);
    }

    @Override
    public void updateBasicBudget(BudgetAmount budgetAmount, BudgetBasicBudget basicBudget) {
        basicBudget.setBudgetRemainAmount(budgetAmount.getBudgetRemainAmount());
        basicBudget.setBudgetOccupyAmount(budgetAmount.getBudgetOccupyAmount());
        basicBudget.setBudgetUsableAmount(budgetAmount.getBudgetUsableAmount());
        this.updateById(basicBudget);
        log.info("基本预算, 保留额: {},占用额: {}, 预算额: {},可用额: {}",
                budgetAmount.getBudgetRemainAmount(),budgetAmount.getBudgetOccupyAmount(),
                basicBudget.getBudgetAmount(),budgetAmount.getBudgetUsableAmount());
    }

    @Override
    public Integer countByBudgetAccountIds(List<Long> ids) {
        QueryWrapper<BudgetBasicBudget> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BudgetBasicBudget.BUDGET_ACCOUNT_ID,ids);
        return this.count(queryWrapper);
    }

    /**
     * 通用查询
     */
    private QueryWrapper<BudgetBasicBudget> getQueryWrapper(
            QueryWrapper<BudgetBasicBudget> queryWrapper, BudgetBasicBudgetQueryForm queryForm) {
        //条件拼接
        if (queryForm.getOrgUnitId() != null) {
            queryWrapper.eq(BudgetBasicBudget.ORG_UNIT_ID, queryForm.getOrgUnitId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgUnitCode())) {
            queryWrapper.eq(BudgetBasicBudget.ORG_UNIT_CODE, queryForm.getOrgUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.eq(BudgetBasicBudget.ORG_PATH, queryForm.getOrgPath());
        }
        if (StringUtils.isNotBlank(queryForm.getLevel1OfficeCode())) {
            queryWrapper.eq(BudgetBasicBudget.LEVEL1_OFFICE_CODE, queryForm.getLevel1OfficeCode());
        }
        if (StringUtils.isNotBlank(queryForm.getLevel2OfficeCode())) {
            queryWrapper.eq(BudgetBasicBudget.LEVEL2_OFFICE_CODE, queryForm.getLevel2OfficeCode());
        }
        if (StringUtils.isNotBlank(queryForm.getBudgetAccountCode())) {
            queryWrapper
                    .eq(BudgetBasicBudget.BUDGET_ACCOUNT_CODE, queryForm.getBudgetAccountCode());
        }
        if (StringUtils.isNotBlank(queryForm.getBudgetPeriod())) {
            queryWrapper.eq(BudgetBasicBudget.BUDGET_PERIOD, queryForm.getBudgetPeriod());
        }
        if (StringUtils.isNotBlank(queryForm.getBudgetAnnual())) {
            queryWrapper.eq(BudgetBasicBudget.BUDGET_ANNUAL, queryForm.getBudgetAnnual());
        }
        if (StringUtils.isNotBlank(queryForm.getApplyForPerson())) {
            queryWrapper.eq(BudgetBasicBudget.APPLY_FOR_PERSON, queryForm.getApplyForPerson());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetBasicBudget.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetBasicBudget.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getTotalFlag())) {
            queryWrapper.eq(BudgetBasicBudget.TOTAL_FLAG, queryForm.getTotalFlag());
        }
        return queryWrapper;
    }
}

