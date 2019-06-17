package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.budget.param.BudgetAfterExpenseLineQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAfterExpenseLine;
import com.deloitte.services.fssc.budget.mapper.BudgetAfterExpenseLineMapper;
import com.deloitte.services.fssc.budget.service.IBudgetAfterExpenseLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description :  BudgetAfterExpenseLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetAfterExpenseLineServiceImpl extends
        ServiceImpl<BudgetAfterExpenseLineMapper, BudgetAfterExpenseLine> implements
        IBudgetAfterExpenseLineService {


    @Autowired
    private BudgetAfterExpenseLineMapper budgetAfterExpenseLineMapper;

    @Override
    public List<BudgetAfterExpenseLine> listByDocument(Long documentId, String documentType) {
        QueryWrapper<BudgetAfterExpenseLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetAfterExpenseLine.DOCUMENT_ID,documentId);
        queryWrapper.eq(BudgetAfterExpenseLine.DOCUMENT_TYPE,documentType);
        return this.list(queryWrapper);
    }

    @Override
    public IPage<BudgetAfterExpenseLine> selectPage(BudgetAfterExpenseLineQueryForm queryForm) {
        QueryWrapper<BudgetAfterExpenseLine> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetAfterExpenseLineMapper
                .selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                        queryWrapper);

    }

    @Override
    public List<BudgetAfterExpenseLine> selectList(BudgetAfterExpenseLineQueryForm queryForm) {
        QueryWrapper<BudgetAfterExpenseLine> queryWrapper = new QueryWrapper<BudgetAfterExpenseLine>();
        getQueryWrapper(queryWrapper, queryForm);
        return budgetAfterExpenseLineMapper.selectList(queryWrapper);
    }


    public QueryWrapper<BudgetAfterExpenseLine> getQueryWrapper(
            QueryWrapper<BudgetAfterExpenseLine> queryWrapper,
            BudgetAfterExpenseLineQueryForm queryForm) {
        //条件拼接
        if (queryForm.getAdvanceBorrowLineId() != null) {
            queryWrapper.eq(BudgetAfterExpenseLine.ADVANCE_BORROW_LINE_ID,
                    queryForm.getAdvanceBorrowLineId());
        }
        if (queryForm.getAfterExpenseId() != null) {
            queryWrapper.eq(BudgetAfterExpenseLine.AFTER_EXPENSE_ID,
                    queryForm.getAfterExpenseId());
        }
        if (queryForm.getDocumentId() != null) {
            queryWrapper.eq(BudgetAfterExpenseLine.DOCUMENT_ID, queryForm.getDocumentId());
        }
        if (StringUtils.isNotBlank(queryForm.getDocumentType())) {
            queryWrapper.eq(BudgetAfterExpenseLine.DOCUMENT_TYPE, queryForm.getDocumentType());
        }
        if (queryForm.getSubTypeId() != null) {
            queryWrapper.eq(BudgetAfterExpenseLine.SUB_TYPE_ID, queryForm.getSubTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetAfterExpenseLine.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetAfterExpenseLine.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getExt1())) {
            queryWrapper.eq(BudgetAfterExpenseLine.EXT1, queryForm.getExt1());
        }
        if (StringUtils.isNotBlank(queryForm.getExt2())) {
            queryWrapper.eq(BudgetAfterExpenseLine.EXT2, queryForm.getExt2());
        }
        if (StringUtils.isNotBlank(queryForm.getExt3())) {
            queryWrapper.eq(BudgetAfterExpenseLine.EXT3, queryForm.getExt3());
        }
        if (StringUtils.isNotBlank(queryForm.getExt4())) {
            queryWrapper.eq(BudgetAfterExpenseLine.EXT4, queryForm.getExt4());
        }
        if (StringUtils.isNotBlank(queryForm.getExt5())) {
            queryWrapper.eq(BudgetAfterExpenseLine.EXT5, queryForm.getExt5());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BudgetAfterExpenseLine.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.eq(BudgetAfterExpenseLine.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }

}

