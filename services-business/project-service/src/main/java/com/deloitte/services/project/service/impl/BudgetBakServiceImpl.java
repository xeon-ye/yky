package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.BudgetBakQueryForm;
import com.deloitte.services.project.entity.Budget;
import com.deloitte.services.project.entity.BudgetBak;
import com.deloitte.services.project.mapper.BudgetBakMapper;
import com.deloitte.services.project.service.IBudgetBakService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-09
 * @Description :  BudgetBak服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetBakServiceImpl extends ServiceImpl<BudgetBakMapper, BudgetBak> implements IBudgetBakService {


    @Autowired
    private BudgetBakMapper budgetBakMapper;

    @Override
    public IPage<BudgetBak> selectPage(BudgetBakQueryForm queryForm ) {
        QueryWrapper<BudgetBak> queryWrapper = new QueryWrapper <BudgetBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return budgetBakMapper.selectPage(new Page<BudgetBak>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BudgetBak> selectList(BudgetBakQueryForm queryForm) {
        QueryWrapper<BudgetBak> queryWrapper = new QueryWrapper <BudgetBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return budgetBakMapper.selectList(queryWrapper);
    }

    @Override
    public List<BudgetBak> selectByRepId(String replyId) {
        QueryWrapper<BudgetBak> queryWrapper = new QueryWrapper <BudgetBak>();
        queryWrapper.eq(BudgetBak.REPLAY_ID,replyId);
        return budgetBakMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteByRepId(String replyId) {
        QueryWrapper<BudgetBak> queryWrapper = new QueryWrapper <BudgetBak>();
        queryWrapper.eq(BudgetBak.REPLAY_ID,replyId);
        budgetBakMapper.delete(queryWrapper);
    }

    @Override
    public List getYearListRep(String replyId) {
        return budgetBakMapper.getYearListRep(replyId);
    }

    @Override
    public List<BudgetBak> getListRep(Map map) {
        return budgetBakMapper.getListRep(map);
    }

    @Override
    public void deleteByRepIdAndYear(Map map) {
        budgetBakMapper.deleteByRepIdAndYear(map);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BudgetBak> getQueryWrapper(QueryWrapper<BudgetBak> queryWrapper,BudgetBakQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getBudgetId())){
            queryWrapper.eq(BudgetBak.BUDGET_ID,queryForm.getBudgetId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(BudgetBak.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseProjectCode())){
            queryWrapper.eq(BudgetBak.EXPENSE_PROJECT_CODE,queryForm.getExpenseProjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenseProject())){
            queryWrapper.eq(BudgetBak.EXPENSE_PROJECT,queryForm.getExpenseProject());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetaryYear())){
            queryWrapper.eq(BudgetBak.BUDGETARY_YEAR,queryForm.getBudgetaryYear());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyTotal())){
            queryWrapper.eq(BudgetBak.APPLY_TOTAL,queryForm.getApplyTotal());
        }
        if(StringUtils.isNotBlank(queryForm.getCentralFin())){
            queryWrapper.eq(BudgetBak.CENTRAL_FIN,queryForm.getCentralFin());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartment())){
            queryWrapper.eq(BudgetBak.DEPARTMENT,queryForm.getDepartment());
        }
        if(StringUtils.isNotBlank(queryForm.getOther())){
            queryWrapper.eq(BudgetBak.OTHER,queryForm.getOther());
        }
        if(StringUtils.isNotBlank(queryForm.getBasisEstimatingAppFunds())){
            queryWrapper.eq(BudgetBak.BASIS_ESTIMATING_APP_FUNDS,queryForm.getBasisEstimatingAppFunds());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewId())){
            queryWrapper.eq(BudgetBak.REVIEW_ID,queryForm.getReviewId());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewCentralFinance())){
            queryWrapper.eq(BudgetBak.REVIEW_CENTRAL_FINANCE,queryForm.getReviewCentralFinance());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewDepartemntFund())){
            queryWrapper.eq(BudgetBak.REVIEW_DEPARTEMNT_FUND,queryForm.getReviewDepartemntFund());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewOthers())){
            queryWrapper.eq(BudgetBak.REVIEW_OTHERS,queryForm.getReviewOthers());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayId())){
            queryWrapper.eq(BudgetBak.REPLAY_ID,queryForm.getReplayId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayTotal())){
            queryWrapper.eq(BudgetBak.REPLAY_TOTAL,queryForm.getReplayTotal());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayCenter())){
            queryWrapper.eq(BudgetBak.REPLAY_CENTER,queryForm.getReplayCenter());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayDep())){
            queryWrapper.eq(BudgetBak.REPLAY_DEP,queryForm.getReplayDep());
        }
        if(StringUtils.isNotBlank(queryForm.getFoundingForward())){
            queryWrapper.eq(BudgetBak.FOUNDING_FORWARD,queryForm.getFoundingForward());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(BudgetBak.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(BudgetBak.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(BudgetBak.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(BudgetBak.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(BudgetBak.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(BudgetBak.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(BudgetBak.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(BudgetBak.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(BudgetBak.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(BudgetBak.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(BudgetBak.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(BudgetBak.PROJECT_ID,queryForm.getProjectId());
        }
        return queryWrapper;
    }
     */
}

