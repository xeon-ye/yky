package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ExpenseQueryForm;
import com.deloitte.services.project.entity.Expense;
import com.deloitte.services.project.mapper.ExpenseMapper;
import com.deloitte.services.project.service.IExpenseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Expense服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ExpenseServiceImpl extends ServiceImpl<ExpenseMapper, Expense> implements IExpenseService {


    @Autowired
    private ExpenseMapper expenseMapper;

    @Override
    public IPage<Expense> selectPage(ExpenseQueryForm queryForm ) {
        QueryWrapper<Expense> queryWrapper = new QueryWrapper <Expense>();
        //getQueryWrapper(queryWrapper,queryForm);
        return expenseMapper.selectPage(new Page<Expense>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Expense> selectList(ExpenseQueryForm queryForm) {
        QueryWrapper<Expense> queryWrapper = new QueryWrapper <Expense>();
        //getQueryWrapper(queryWrapper,queryForm);
        return expenseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Expense> getList(String projectApprovalId) {
        QueryWrapper<Expense> queryWrapper = new QueryWrapper <Expense>();
        queryWrapper.eq(Expense.PROJECT_APPROVAL_ID, projectApprovalId);
        return expenseMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteList(String applicationId) {
        QueryWrapper<Expense> queryWrapper = new QueryWrapper <Expense>();
        queryWrapper.eq(Expense.APPLICATION_ID, applicationId);
        expenseMapper.delete(queryWrapper);
    }

    @Override
    public void delByRep(String replyId) {
        QueryWrapper<Expense> queryWrapper = new QueryWrapper <Expense>();
        queryWrapper.eq(Expense.PROJECT_APPROVAL_ID, replyId);
        expenseMapper.delete(queryWrapper);
    }

    @Override
    public List<Expense> getAllList(String replyId) {
        QueryWrapper<Expense> queryWrapper = new QueryWrapper <Expense>();
        queryWrapper.eq(Expense.PROJECT_APPROVAL_ID,replyId);
        return expenseMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Expense> getQueryWrapper(QueryWrapper<Expense> queryWrapper,ExpenseQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getExpenseId())){
            queryWrapper.eq(Expense.EXPENSE_ID,queryForm.getExpenseId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectApprovalId())){
            queryWrapper.eq(Expense.PROJECT_APPROVAL_ID,queryForm.getProjectApprovalId());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetYear())){
            queryWrapper.eq(Expense.BUDGET_YEAR,queryForm.getBudgetYear());
        }
        if(StringUtils.isNotBlank(queryForm.getExpensePlan())){
            queryWrapper.eq(Expense.EXPENSE_PLAN,queryForm.getExpensePlan());
        }
        if(StringUtils.isNotBlank(queryForm.getFinAllocationEarly())){
            queryWrapper.eq(Expense.FIN_ALLOCATION_EARLY,queryForm.getFinAllocationEarly());
        }
        if(StringUtils.isNotBlank(queryForm.getFinCarryOverEarly())){
            queryWrapper.eq(Expense.FIN_CARRY_OVER_EARLY,queryForm.getFinCarryOverEarly());
        }
        if(StringUtils.isNotBlank(queryForm.getAdjFinancialAllocation())){
            queryWrapper.eq(Expense.ADJ_FINANCIAL_ALLOCATION,queryForm.getAdjFinancialAllocation());
        }
        if(StringUtils.isNotBlank(queryForm.getAdjFinancialCarryOver())){
            queryWrapper.eq(Expense.ADJ_FINANCIAL_CARRY_OVER,queryForm.getAdjFinancialCarryOver());
        }
        if(StringUtils.isNotBlank(queryForm.getFinAllocationAllYear())){
            queryWrapper.eq(Expense.FIN_ALLOCATION_ALL_YEAR,queryForm.getFinAllocationAllYear());
        }
        if(StringUtils.isNotBlank(queryForm.getFinCarryOverAllYear())){
            queryWrapper.eq(Expense.FIN_CARRY_OVER_ALL_YEAR,queryForm.getFinCarryOverAllYear());
        }
        if(StringUtils.isNotBlank(queryForm.getFinAllocationBudget())){
            queryWrapper.eq(Expense.FIN_ALLOCATION_BUDGET,queryForm.getFinAllocationBudget());
        }
        if(StringUtils.isNotBlank(queryForm.getFinCarryOverBudget())){
            queryWrapper.eq(Expense.FIN_CARRY_OVER_BUDGET,queryForm.getFinCarryOverBudget());
        }
        if(StringUtils.isNotBlank(queryForm.getFinAllocationSurplus())){
            queryWrapper.eq(Expense.FIN_ALLOCATION_SURPLUS,queryForm.getFinAllocationSurplus());
        }
        if(StringUtils.isNotBlank(queryForm.getFinCarryOverSurplus())){
            queryWrapper.eq(Expense.FIN_CARRY_OVER_SURPLUS,queryForm.getFinCarryOverSurplus());
        }
        if(StringUtils.isNotBlank(queryForm.getFinAllocationFactSurplus())){
            queryWrapper.eq(Expense.FIN_ALLOCATION_FACT_SURPLUS,queryForm.getFinAllocationFactSurplus());
        }
        if(StringUtils.isNotBlank(queryForm.getFinCarryOverFactSurplus())){
            queryWrapper.eq(Expense.FIN_CARRY_OVER_FACT_SURPLUS,queryForm.getFinCarryOverFactSurplus());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Expense.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Expense.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Expense.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Expense.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Expense.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Expense.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Expense.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Expense.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Expense.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Expense.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Expense.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }
     */
}

