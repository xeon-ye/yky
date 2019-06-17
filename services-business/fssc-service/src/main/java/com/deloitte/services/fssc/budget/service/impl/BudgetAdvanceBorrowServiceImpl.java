package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.budget.param.BudgetAdvanceBorrowQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAdvanceBorrow;
import com.deloitte.services.fssc.budget.mapper.BudgetAdvanceBorrowMapper;
import com.deloitte.services.fssc.budget.service.IBudgetAdvanceBorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description :  BudgetAdvanceBorrow服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetAdvanceBorrowServiceImpl extends
        ServiceImpl<BudgetAdvanceBorrowMapper, BudgetAdvanceBorrow> implements
        IBudgetAdvanceBorrowService {


    @Autowired
    private BudgetAdvanceBorrowMapper advanceBorrowMapper;

    @Override
    public BudgetAdvanceBorrow getByDocument(Long documentId, String documentType) {
        QueryWrapper<BudgetAdvanceBorrow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetAdvanceBorrow.DOCUMENT_ID,documentId);
        queryWrapper.eq(BudgetAdvanceBorrow.DOCUMENT_TYPE,documentType);
        return advanceBorrowMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<BudgetAdvanceBorrow> selectPage(BudgetAdvanceBorrowQueryForm queryForm) {
        QueryWrapper<BudgetAdvanceBorrow> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return advanceBorrowMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);

    }

    @Override
    public List<BudgetAdvanceBorrow> selectList(BudgetAdvanceBorrowQueryForm queryForm) {
        QueryWrapper<BudgetAdvanceBorrow> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return advanceBorrowMapper.selectList(queryWrapper);
    }


    public QueryWrapper<BudgetAdvanceBorrow> getQueryWrapper(
            QueryWrapper<BudgetAdvanceBorrow> queryWrapper,
            BudgetAdvanceBorrowQueryForm queryForm) {
        //条件拼接
        if (queryForm.getDocumentId() != null) {
            queryWrapper.eq(BudgetAdvanceBorrow.DOCUMENT_ID, queryForm.getDocumentId());
        }
        if (StringUtils.isNotBlank(queryForm.getDocumentType())) {
            queryWrapper.eq(BudgetAdvanceBorrow.DOCUMENT_TYPE, queryForm.getDocumentType());
        }
        if (queryForm.getAdvanceApplyForId() != null) {
            queryWrapper
                    .eq(BudgetAdvanceBorrow.ADVANCE_APPLY_FOR_ID, queryForm.getAdvanceApplyForId());
        }
        if (StringUtils.isNotBlank(queryForm.getBudgetType())) {
            queryWrapper.eq(BudgetAdvanceBorrow.BUDGET_TYPE, queryForm.getBudgetType());
        }
        if (queryForm.getMainTypeId() != null) {
            queryWrapper.eq(BudgetAdvanceBorrow.MAIN_TYPE_ID, queryForm.getMainTypeId());
        }
        if (queryForm.getBasicBudgetAccountId() != null) {
            queryWrapper.eq(BudgetAdvanceBorrow.BASIC_BUDGET_ACCOUNT_ID,
                    queryForm.getBasicBudgetAccountId());
        }
        if (queryForm.getProjectId() != null) {
            queryWrapper.eq(BudgetAdvanceBorrow.PROJECT_ID, queryForm.getProjectId());
        }
        if (queryForm.getProjectBudgetAccountId() != null) {
            queryWrapper.eq(BudgetAdvanceBorrow.PROJECT_BUDGET_ACCOUNT_ID,
                    queryForm.getProjectBudgetAccountId());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetAdvanceBorrow.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetAdvanceBorrow.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getExt1())) {
            queryWrapper.eq(BudgetAdvanceBorrow.EXT1, queryForm.getExt1());
        }
        if (StringUtils.isNotBlank(queryForm.getExt2())) {
            queryWrapper.eq(BudgetAdvanceBorrow.EXT2, queryForm.getExt2());
        }
        if (StringUtils.isNotBlank(queryForm.getExt3())) {
            queryWrapper.eq(BudgetAdvanceBorrow.EXT3, queryForm.getExt3());
        }
        if (StringUtils.isNotBlank(queryForm.getExt4())) {
            queryWrapper.eq(BudgetAdvanceBorrow.EXT4, queryForm.getExt4());
        }
        if (StringUtils.isNotBlank(queryForm.getExt5())) {
            queryWrapper.eq(BudgetAdvanceBorrow.EXT5, queryForm.getExt5());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BudgetAdvanceBorrow.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.eq(BudgetAdvanceBorrow.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }

}

