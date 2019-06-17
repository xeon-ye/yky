package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.project.param.BudgetQueryForm;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.entity.Budget;
import com.deloitte.services.project.mapper.BudgetMapper;
import com.deloitte.services.project.service.IBudgetService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-09
 * @Description :  Budget服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetServiceImpl extends ServiceImpl<BudgetMapper, Budget> implements IBudgetService {


    @Autowired
    private BudgetMapper budgetMapper;

    @Override
    public IPage<Budget> selectPage(BudgetQueryForm queryForm ) {
        QueryWrapper<Budget> queryWrapper = new QueryWrapper <Budget>();
        //getQueryWrapper(queryWrapper,queryForm);
        return budgetMapper.selectPage(new Page<Budget>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Budget> selectList(BudgetQueryForm queryForm) {
        QueryWrapper<Budget> queryWrapper = new QueryWrapper <Budget>();
        //getQueryWrapper(queryWrapper,queryForm);
        return budgetMapper.selectList(queryWrapper);
    }

    @Override
    public List<Budget> getBudgetYearList(String applicationId) {
        List<Budget> stringList = budgetMapper.getBudgetYearList(applicationId);
        return stringList;
    }

    @Override
    public void deleteByApp(String applicationId) {
        QueryWrapper<Budget> queryWrapper = new QueryWrapper <Budget>();
        queryWrapper.eq(Budget.APPLICATION_ID,applicationId);
        budgetMapper.delete(queryWrapper);
    }

    @Override
    public void deleteByRep(String replyId) {
        QueryWrapper<Budget> queryWrapper = new QueryWrapper <Budget>();
        queryWrapper.eq(Budget.REPLAY_ID,replyId);
        budgetMapper.delete(queryWrapper);
    }

    @Override
    public List getAppYearCount(String applicationId) {
        return budgetMapper.getAppYearCount(applicationId);
    }

    @Override
    public List<Budget> getAppBudgetList(String applicationId) {
        QueryWrapper<Budget> queryWrapper = new QueryWrapper <Budget>();
        queryWrapper.eq(Budget.APPLICATION_ID,applicationId);
        return budgetMapper.selectList(queryWrapper);
    }

    @Override
    public List<Budget> getAppBudgetMap(Map map) {
        return budgetMapper.getAppBudgetMap(map);
    }

    @Override
    public List getRepYearCount(String replyId) {
        return budgetMapper.getRepYearCount(replyId);
    }

    @Override
    public List<Budget> getRepBudgetList(String replyId) {

        QueryWrapper<Budget> queryWrapper = new QueryWrapper <Budget>();
        queryWrapper.eq(Budget.REPLAY_ID,replyId);
        return budgetMapper.selectList(queryWrapper);
    }

    @Override
    public List<Budget> getRepBudgetMap(Map map) {
        return budgetMapper.getRepBudgetMap(map);
    }

    @Override
    public void deleteByRepIdAndYear(Map map) {
        budgetMapper.deleteByRepIdAndYear(map);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Budget> getQueryWrapper(QueryWrapper<Budget> queryWrapper,BudgetQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getBudgetId())){
            queryWrapper.eq(Budget.BUDGET_ID,queryForm.getBudgetId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Budget.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseProjectCode())){
            queryWrapper.eq(Budget.EXPENSE_PROJECT_CODE,queryForm.getExpenseProjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseProject())){
            queryWrapper.eq(Budget.EXPENSE_PROJECT,queryForm.getExpenseProject());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetaryYear())){
            queryWrapper.eq(Budget.BUDGETARY_YEAR,queryForm.getBudgetaryYear());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyTotal())){
            queryWrapper.eq(Budget.APPLY_TOTAL,queryForm.getApplyTotal());
        }
        if(StringUtils.isNotBlank(queryForm.getCentralFin())){
            queryWrapper.eq(Budget.CENTRAL_FIN,queryForm.getCentralFin());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartment())){
            queryWrapper.eq(Budget.DEPARTMENT,queryForm.getDepartment());
        }
        if(StringUtils.isNotBlank(queryForm.getOther())){
            queryWrapper.eq(Budget.OTHER,queryForm.getOther());
        }
        if(StringUtils.isNotBlank(queryForm.getBasisEstimatingAppFunds())){
            queryWrapper.eq(Budget.BASIS_ESTIMATING_APP_FUNDS,queryForm.getBasisEstimatingAppFunds());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewId())){
            queryWrapper.eq(Budget.REVIEW_ID,queryForm.getReviewId());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewCentralFinance())){
            queryWrapper.eq(Budget.REVIEW_CENTRAL_FINANCE,queryForm.getReviewCentralFinance());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewDepartemntFund())){
            queryWrapper.eq(Budget.REVIEW_DEPARTEMNT_FUND,queryForm.getReviewDepartemntFund());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewOthers())){
            queryWrapper.eq(Budget.REVIEW_OTHERS,queryForm.getReviewOthers());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayId())){
            queryWrapper.eq(Budget.REPLAY_ID,queryForm.getReplayId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayTotal())){
            queryWrapper.eq(Budget.REPLAY_TOTAL,queryForm.getReplayTotal());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayCenter())){
            queryWrapper.eq(Budget.REPLAY_CENTER,queryForm.getReplayCenter());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayDep())){
            queryWrapper.eq(Budget.REPLAY_DEP,queryForm.getReplayDep());
        }
        if(StringUtils.isNotBlank(queryForm.getFoundingForward())){
            queryWrapper.eq(Budget.FOUNDING_FORWARD,queryForm.getFoundingForward());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Budget.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Budget.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Budget.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Budget.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Budget.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Budget.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Budget.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Budget.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Budget.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Budget.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Budget.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(Budget.PROJECT_ID,queryForm.getProjectId());
        }
        return queryWrapper;
    }
     */
}

