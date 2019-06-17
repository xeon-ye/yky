package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.budget.param.BudgetAfterExpenseQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAfterExpense;
import com.deloitte.services.fssc.budget.mapper.BudgetAfterExpenseMapper;
import com.deloitte.services.fssc.budget.service.IBudgetAfterExpenseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description :  BudgetAfterExpense服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetAfterExpenseServiceImpl extends
        ServiceImpl<BudgetAfterExpenseMapper, BudgetAfterExpense> implements
        IBudgetAfterExpenseService {


    @Autowired
    private BudgetAfterExpenseMapper budgetAfterExpenseMapper;

    @Override
    public BudgetAfterExpense getByDocument(Long documentId, String documentType) {
        QueryWrapper<BudgetAfterExpense> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetAfterExpense.DOCUMENT_ID, documentId);
        queryWrapper.eq(BudgetAfterExpense.DOCUMENT_TYPE, documentType);
        return this.getOne(queryWrapper);
    }

    @Override
    public IPage<BudgetAfterExpense> selectPage(BudgetAfterExpenseQueryForm queryForm) {
        QueryWrapper<BudgetAfterExpense> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetAfterExpenseMapper
                .selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                        queryWrapper);

    }

    @Override
    public List<BudgetAfterExpense> selectList(BudgetAfterExpenseQueryForm queryForm) {
        QueryWrapper<BudgetAfterExpense> queryWrapper = new QueryWrapper<BudgetAfterExpense>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetAfterExpenseMapper.selectList(queryWrapper);
    }

    /**
     * 通用查询
     */
    private QueryWrapper<BudgetAfterExpense> getQueryWrapper(
            QueryWrapper<BudgetAfterExpense> queryWrapper, BudgetAfterExpenseQueryForm queryForm) {
        //条件拼接
        if (queryForm.getDocumentId() != null) {
            queryWrapper.eq(BudgetAfterExpense.DOCUMENT_ID, queryForm.getDocumentId());
        }
        if (StringUtils.isNotBlank(queryForm.getDocumentType())) {
            queryWrapper.eq(BudgetAfterExpense.DOCUMENT_TYPE, queryForm.getDocumentType());
        }
        if (queryForm.getAdvanceApplyForId() != null) {
            queryWrapper
                    .eq(BudgetAfterExpense.ADVANCE_APPLY_FOR_ID, queryForm.getAdvanceApplyForId());
        }
        if (StringUtils.isNotBlank(queryForm.getBudgetType())) {
            queryWrapper.eq(BudgetAfterExpense.BUDGET_TYPE, queryForm.getBudgetType());
        }
        if (queryForm.getMainTypeId() != null) {
            queryWrapper.eq(BudgetAfterExpense.MAIN_TYPE_ID, queryForm.getMainTypeId());
        }
        if (queryForm.getBasicBudgetAccountId() != null) {
            queryWrapper.eq(BudgetAfterExpense.BASIC_BUDGET_ACCOUNT_ID,
                    queryForm.getBasicBudgetAccountId());
        }
        if (queryForm.getProjectId() != null) {
            queryWrapper.eq(BudgetAfterExpense.PROJECT_ID, queryForm.getProjectId());
        }
        if (queryForm.getProjectBudgetAccountId() != null) {
            queryWrapper.eq(BudgetAfterExpense.PROJECT_BUDGET_ACCOUNT_ID,
                    queryForm.getProjectBudgetAccountId());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetAfterExpense.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetAfterExpense.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getExt1())) {
            queryWrapper.eq(BudgetAfterExpense.EXT1, queryForm.getExt1());
        }
        if (StringUtils.isNotBlank(queryForm.getExt2())) {
            queryWrapper.eq(BudgetAfterExpense.EXT2, queryForm.getExt2());
        }
        if (StringUtils.isNotBlank(queryForm.getExt3())) {
            queryWrapper.eq(BudgetAfterExpense.EXT3, queryForm.getExt3());
        }
        if (StringUtils.isNotBlank(queryForm.getExt4())) {
            queryWrapper.eq(BudgetAfterExpense.EXT4, queryForm.getExt4());
        }
        if (StringUtils.isNotBlank(queryForm.getExt5())) {
            queryWrapper.eq(BudgetAfterExpense.EXT5, queryForm.getExt5());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BudgetAfterExpense.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.eq(BudgetAfterExpense.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }

}

