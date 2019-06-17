package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaFundsBudgetQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaFundsBudget;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaFundsBudgetMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaFundsBudgetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-12
 * @Description :  MprEvaFundsBudget服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaFundsBudgetServiceImpl extends ServiceImpl<MprEvaFundsBudgetMapper, MprEvaFundsBudget> implements IMprEvaFundsBudgetService {


    @Autowired
    private MprEvaFundsBudgetMapper mprEvaFundsBudgetMapper;

    @Override
    public IPage<MprEvaFundsBudget> selectPage(MprEvaFundsBudgetQueryForm queryForm ) {
        QueryWrapper<MprEvaFundsBudget> queryWrapper = new QueryWrapper <MprEvaFundsBudget>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaFundsBudgetMapper.selectPage(new Page<MprEvaFundsBudget>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaFundsBudget> selectList(MprEvaFundsBudgetQueryForm queryForm) {
        QueryWrapper<MprEvaFundsBudget> queryWrapper = new QueryWrapper <MprEvaFundsBudget>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaFundsBudgetMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaFundsBudget> getQueryWrapper(QueryWrapper<MprEvaFundsBudget> queryWrapper,MprEvaFundsBudgetQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getYear())){
            queryWrapper.eq(MprEvaFundsBudget.YEAR,queryForm.getYear());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskName())){
            queryWrapper.eq(MprEvaFundsBudget.TASK_NAME,queryForm.getTaskName());
        }
        if(StringUtils.isNotBlank(queryForm.getBudget())){
            queryWrapper.eq(MprEvaFundsBudget.BUDGET,queryForm.getBudget());
        }
        if(StringUtils.isNotBlank(queryForm.getExpenses())){
            queryWrapper.eq(MprEvaFundsBudget.EXPENSES,queryForm.getExpenses());
        }
        if(StringUtils.isNotBlank(queryForm.getExacutiveRate())){
            queryWrapper.eq(MprEvaFundsBudget.EXACUTIVE_RATE,queryForm.getExacutiveRate());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaFundsBudget.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaFundsBudget.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaFundsBudget.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaFundsBudget.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

