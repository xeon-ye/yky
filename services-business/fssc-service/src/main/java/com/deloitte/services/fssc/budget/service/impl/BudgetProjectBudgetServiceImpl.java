package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.budget.param.BudgetProjectBudgetQueryForm;
import com.deloitte.platform.api.fssc.budget.vo.BudgetProjectBudgetVo;
import com.deloitte.services.fssc.budget.entity.BudgetAmount;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.entity.BudgetProjectBudget;
import com.deloitte.services.fssc.budget.mapper.BudgetProjectBudgetMapper;
import com.deloitte.services.fssc.budget.service.IBudgetProjectBudgetService;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.eums.FsscEums;
import java.math.BigDecimal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description :  BudgetProjectBudget服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class BudgetProjectBudgetServiceImpl extends
        ServiceImpl<BudgetProjectBudgetMapper, BudgetProjectBudget> implements
        IBudgetProjectBudgetService {


    @Autowired
    private BudgetProjectBudgetMapper budgetProjectBudgetMapper;

    @Autowired
    private IBudgetProjectService budgetProjectService;

    @Override
    public IPage<BudgetProjectBudget> selectPage(BudgetProjectBudgetQueryForm queryForm) {
        QueryWrapper<BudgetProjectBudget> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetProjectBudgetMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }

    @Override
    public List<BudgetProjectBudget> selectList(BudgetProjectBudgetQueryForm queryForm) {
        QueryWrapper<BudgetProjectBudget> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetProjectBudgetMapper.selectList(queryWrapper);
    }

    @Override
    public List<BudgetProjectBudgetVo> selectVoList(BudgetProjectBudgetQueryForm queryForm) {
        return budgetProjectBudgetMapper.listVo(queryForm.getOrgUnitCode(),queryForm.getBudgetProjectId());
    }

    @Override
    public BudgetProjectBudget selectByKeyWord(String unitCode, Long budgetProjectId,
            String budgetAccountCode, String budgetAnnual) {
        BudgetProject budgetProject = budgetProjectService.getById(budgetProjectId);
        QueryWrapper<BudgetProjectBudget> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetProjectBudget.ORG_UNIT_CODE,budgetProject.getOrgUnitCode());
        queryWrapper.eq(BudgetProjectBudget.BUDGET_PROJECT_ID,budgetProjectId);
        queryWrapper.eq(BudgetProjectBudget.BUDGET_ACCOUNT_CODE,budgetAccountCode);
        queryWrapper.eq(BudgetProjectBudget.BUDGET_ANNUAL,budgetAnnual);
        queryWrapper.eq(BudgetProjectBudget.TOTAL_FLAG, FsscEums.YES.getValue());
        return this.getOne(queryWrapper);
    }

    @Override
    public BudgetProjectBudget selectByKeyWord(String unitCode, String projectCode, String taskCode,
            String budgetAccountCode, String budgetAnnual) {
        QueryWrapper<BudgetProjectBudget> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetProjectBudget.ORG_UNIT_CODE,unitCode);
        queryWrapper.eq(BudgetProjectBudget.PROJECT_CODE,projectCode);
        queryWrapper.eq(BudgetProjectBudget.TASK_CODE, taskCode);
        queryWrapper.eq(BudgetProjectBudget.BUDGET_ACCOUNT_CODE,budgetAccountCode);
        queryWrapper.eq(BudgetProjectBudget.BUDGET_ANNUAL,budgetAnnual);
        queryWrapper.eq(BudgetProjectBudget.TOTAL_FLAG, FsscEums.YES.getValue());
        return this.getOne(queryWrapper);
    }

    @Override
    public BudgetProjectBudget selectByKeyWord(Long unitId, Long budgetProjectId,
            String budgetAccountCode, String budgetAnnual) {
        QueryWrapper<BudgetProjectBudget> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetProjectBudget.ORG_UNIT_ID,unitId);
        queryWrapper.eq(BudgetProjectBudget.BUDGET_PROJECT_ID,budgetProjectId);
        queryWrapper.eq(BudgetProjectBudget.BUDGET_ACCOUNT_CODE,budgetAccountCode);
        queryWrapper.eq(BudgetProjectBudget.BUDGET_ANNUAL,budgetAnnual);
        queryWrapper.eq(BudgetProjectBudget.TOTAL_FLAG, FsscEums.YES.getValue());
        return this.getOne(queryWrapper);
    }

    @Override
    public void updateProjectBudget(BigDecimal newRemainAmount, BigDecimal newOccupyAmount,
            BigDecimal newUsableAmount, BudgetProjectBudget projectBudget) {
        projectBudget.setBudgetRemainAmount(newRemainAmount);
        projectBudget.setBudgetOccupyAmount(newOccupyAmount);
        projectBudget.setBudgetUsableAmount(newUsableAmount);
        this.updateById(projectBudget);
        log.info("项目预算, 保留额: {},占用额: {}, 预算额: {},可用额: {}",
                newRemainAmount,newOccupyAmount,projectBudget.getBudgetAmount(),newUsableAmount);
    }

    @Override
    public void updateProjectBudget(BudgetAmount budgetAmount, BudgetProjectBudget projectBudget) {
        projectBudget.setBudgetRemainAmount(budgetAmount.getBudgetRemainAmount());
        projectBudget.setBudgetOccupyAmount(budgetAmount.getBudgetOccupyAmount());
        projectBudget.setBudgetUsableAmount(budgetAmount.getBudgetUsableAmount());
        this.updateById(projectBudget);
        log.info("项目预算, 保留额: {},占用额: {}, 预算额: {},可用额: {}",
                budgetAmount.getBudgetRemainAmount(),budgetAmount.getBudgetOccupyAmount(),
                projectBudget.getBudgetAmount(),budgetAmount.getBudgetUsableAmount());
    }


    @Override
    public BudgetProjectBudget selectMonthByKeyWord(String unitCode,String projectCode , String taskCode,
            String budgetAccountCode, String budgetAnnual, String budgetPeriod) {
        QueryWrapper<BudgetProjectBudget> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetProjectBudget.ORG_UNIT_CODE,unitCode);
        queryWrapper.eq(BudgetProjectBudget.PROJECT_CODE, projectCode);
        queryWrapper.eq(BudgetProjectBudget.TASK_CODE, taskCode);
        queryWrapper.eq(BudgetProjectBudget.BUDGET_ACCOUNT_CODE,budgetAccountCode);
        queryWrapper.eq(BudgetProjectBudget.BUDGET_ANNUAL,budgetAnnual);
        queryWrapper.eq(BudgetProjectBudget.TOTAL_FLAG, FsscEums.NO.getValue());
        queryWrapper.eq(BudgetProjectBudget.BUDGET_PERIOD,budgetPeriod);
        return this.getOne(queryWrapper);
    }

    @Override
    public BudgetProjectBudget getSummaryByTask(String projectCode, String taskCode, String budgetAnnual) {
        return budgetProjectBudgetMapper.getSummaryByTaskCode(projectCode,taskCode,budgetAnnual);
    }

    @Override
    public List<BudgetProjectBudgetVo> getSummaryAccountByTaskCode(String projectCode, String taskCode, String budgetAnnual) {
        return budgetProjectBudgetMapper.getSummaryAccountByTaskCode(projectCode,taskCode,budgetAnnual);
    }


    @Override
    public Integer countByBudgetAccountIds(List<Long> ids) {
        QueryWrapper<BudgetProjectBudget> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BudgetProjectBudget.BUDGET_ACCOUNT_ID,ids);
        return this.count(queryWrapper);
    }

    /**
     * 通用查询
     * @param queryWrapper
     * @param queryForm
     * @return
     */
    private QueryWrapper<BudgetProjectBudget> getQueryWrapper(
            QueryWrapper<BudgetProjectBudget> queryWrapper,
            BudgetProjectBudgetQueryForm queryForm) {
        //条件拼接
        if (queryForm.getId() != null) {
            queryWrapper.eq(BudgetProjectBudget.ID, queryForm.getId());
        }
        if (queryForm.getOrgUnitId() != null) {
            queryWrapper.eq(BudgetProjectBudget.ORG_UNIT_ID, queryForm.getOrgUnitId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgUnitCode())) {
            queryWrapper.eq(BudgetProjectBudget.ORG_UNIT_CODE, queryForm.getOrgUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.eq(BudgetProjectBudget.ORG_PATH, queryForm.getOrgPath());
        }
        if (queryForm.getBudgetProjectId() != null) {
            queryWrapper.eq(BudgetProjectBudget.BUDGET_PROJECT_ID, queryForm.getBudgetProjectId());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectCode())) {
            queryWrapper.eq(BudgetProjectBudget.PROJECT_CODE, queryForm.getProjectCode());
        }
        if (StringUtils.isNotBlank(queryForm.getSubjectCode())) {
            queryWrapper.eq(BudgetProjectBudget.SUBJECT_CODE, queryForm.getSubjectCode());
        }
        if (StringUtils.isNotBlank(queryForm.getTaskCode())) {
            queryWrapper.eq(BudgetProjectBudget.TASK_CODE, queryForm.getTaskCode());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectStatus())) {
            queryWrapper.eq(BudgetProjectBudget.PROJECT_STATUS, queryForm.getProjectStatus());
        }
        if (StringUtils.isNotBlank(queryForm.getBudgetAccountCode())) {
            queryWrapper
                    .eq(BudgetProjectBudget.BUDGET_ACCOUNT_CODE, queryForm.getBudgetAccountCode());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetProjectBudget.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetProjectBudget.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getTotalFlag())) {
            queryWrapper.eq(BudgetProjectBudget.TOTAL_FLAG, queryForm.getTotalFlag());
        }
        return queryWrapper;
    }
}

