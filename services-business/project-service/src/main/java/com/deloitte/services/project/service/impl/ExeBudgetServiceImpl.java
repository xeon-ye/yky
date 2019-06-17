package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ExeBudgetQueryForm;
import com.deloitte.services.project.entity.ExeBudget;
import com.deloitte.services.project.mapper.ExeBudgetMapper;
import com.deloitte.services.project.service.IExeBudgetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  ExeBudget服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ExeBudgetServiceImpl extends ServiceImpl<ExeBudgetMapper, ExeBudget> implements IExeBudgetService {


    @Autowired
    private ExeBudgetMapper exeBudgetMapper;

    @Override
    public IPage<ExeBudget> selectPage(ExeBudgetQueryForm queryForm ) {
        QueryWrapper<ExeBudget> queryWrapper = new QueryWrapper <ExeBudget>();
        //getQueryWrapper(queryWrapper,queryForm);
        return exeBudgetMapper.selectPage(new Page<ExeBudget>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ExeBudget> selectList(ExeBudgetQueryForm queryForm) {
        QueryWrapper<ExeBudget> queryWrapper = new QueryWrapper <ExeBudget>();
        //getQueryWrapper(queryWrapper,queryForm);
        return exeBudgetMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ExeBudget> getQueryWrapper(QueryWrapper<ExeBudget> queryWrapper,ExeBudgetQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(ExeBudget.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(ExeBudget.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getExecutionId())){
            queryWrapper.eq(ExeBudget.EXECUTION_ID,queryForm.getExecutionId());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetCode())){
            queryWrapper.eq(ExeBudget.BUDGET_CODE,queryForm.getBudgetCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetName())){
            queryWrapper.eq(ExeBudget.BUDGET_NAME,queryForm.getBudgetName());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetAmount())){
            queryWrapper.eq(ExeBudget.BUDGET_AMOUNT,queryForm.getBudgetAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getExeAmount())){
            queryWrapper.eq(ExeBudget.EXE_AMOUNT,queryForm.getExeAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetYear())){
            queryWrapper.eq(ExeBudget.BUDGET_YEAR,queryForm.getBudgetYear());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ExeBudget.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ExeBudget.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ExeBudget.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ExeBudget.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ExeBudget.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ExeBudget.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ExeBudget.EXT3,queryForm.getExt3());
        }
        return queryWrapper;
    }
     */
}

