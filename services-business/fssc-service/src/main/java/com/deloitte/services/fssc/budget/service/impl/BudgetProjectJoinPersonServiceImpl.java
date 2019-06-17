package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.bpm.param.BudgetProjectJoinPersonQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetProjectJoinPerson;
import com.deloitte.services.fssc.budget.mapper.BudgetProjectJoinPersonMapper;
import com.deloitte.services.fssc.budget.service.IBudgetProjectJoinPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-18
 * @Description :  BudgetProjectJoinPerson服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetProjectJoinPersonServiceImpl extends ServiceImpl<BudgetProjectJoinPersonMapper, BudgetProjectJoinPerson>
        implements IBudgetProjectJoinPersonService {


    @Autowired
    private BudgetProjectJoinPersonMapper budgetProjectJoinPersonMapper;

    @Override
    public IPage<BudgetProjectJoinPerson> selectPage(BudgetProjectJoinPersonQueryForm queryForm) {
        QueryWrapper<BudgetProjectJoinPerson> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetProjectJoinPersonMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<BudgetProjectJoinPerson> selectList(BudgetProjectJoinPersonQueryForm queryForm) {
        QueryWrapper<BudgetProjectJoinPerson> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetProjectJoinPersonMapper.selectList(queryWrapper);
    }

    public QueryWrapper<BudgetProjectJoinPerson> getQueryWrapper(QueryWrapper<BudgetProjectJoinPerson> queryWrapper, BudgetProjectJoinPersonQueryForm queryForm) {
        //条件拼接
        if (queryForm.getProjectId() != null) {
            queryWrapper.eq(BudgetProjectJoinPerson.PROJECT_ID, queryForm.getProjectId());
        }
        if (StringUtils.isNotBlank(queryForm.getProjectCode())) {
            queryWrapper.eq(BudgetProjectJoinPerson.PROJECT_CODE, queryForm.getProjectCode());
        }
        if (queryForm.getPersonId() != null) {
            queryWrapper.eq(BudgetProjectJoinPerson.PERSON_ID, queryForm.getPersonId());
        }
        if (StringUtils.isNotBlank(queryForm.getPersonName())) {
            queryWrapper.like(BudgetProjectJoinPerson.PERSON_NAME, queryForm.getPersonName());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.like(BudgetProjectJoinPerson.VALID_FLAG, queryForm.getValidFlag());
        }
        return queryWrapper;
    }
}

