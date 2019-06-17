package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.budget.param.BudgetAdvanceApplyForQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAdvanceApplyFor;
import com.deloitte.services.fssc.budget.mapper.BudgetAdvanceApplyForMapper;
import com.deloitte.services.fssc.budget.service.IBudgetAdvanceApplyForService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description :  BudgetAdvanceApplyFor服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetAdvanceApplyForServiceImpl extends
        ServiceImpl<BudgetAdvanceApplyForMapper, BudgetAdvanceApplyFor> implements
        IBudgetAdvanceApplyForService {


    @Autowired
    private BudgetAdvanceApplyForMapper applyForMapper;

    @Override
    public BudgetAdvanceApplyFor getByDocument(Long documentId, String documentType) {
        QueryWrapper<BudgetAdvanceApplyFor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetAdvanceApplyFor.DOCUMENT_ID,documentId);
        queryWrapper.eq(BudgetAdvanceApplyFor.DOCUMENT_TYPE,documentType);
        return applyForMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<BudgetAdvanceApplyFor> selectPage(BudgetAdvanceApplyForQueryForm queryForm) {
        QueryWrapper<BudgetAdvanceApplyFor> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return applyForMapper
                .selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                        queryWrapper);

    }

    @Override
    public List<BudgetAdvanceApplyFor> selectList(BudgetAdvanceApplyForQueryForm queryForm) {
        QueryWrapper<BudgetAdvanceApplyFor> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return applyForMapper.selectList(queryWrapper);
    }

    /**
     * 通用查询
     */
    private QueryWrapper<BudgetAdvanceApplyFor> getQueryWrapper(
            QueryWrapper<BudgetAdvanceApplyFor> queryWrapper,
            BudgetAdvanceApplyForQueryForm queryForm) {
        //条件拼接
        if (queryForm.getDocumentId() != null) {
            queryWrapper.eq(BudgetAdvanceApplyFor.DOCUMENT_ID, queryForm.getDocumentId());
        }
        if (StringUtils.isNotBlank(queryForm.getDocumentType())) {
            queryWrapper.eq(BudgetAdvanceApplyFor.DOCUMENT_TYPE, queryForm.getDocumentType());
        }
        if (StringUtils.isNotBlank(queryForm.getBudgetType())) {
            queryWrapper.eq(BudgetAdvanceApplyFor.BUDGET_TYPE, queryForm.getBudgetType());
        }
        if (queryForm.getMainTypeId() != null) {
            queryWrapper.eq(BudgetAdvanceApplyFor.MAIN_TYPE_ID, queryForm.getMainTypeId());
        }
        if (queryForm.getBasicBudgetAccountId() != null) {
            queryWrapper.eq(BudgetAdvanceApplyFor.BASIC_BUDGET_ACCOUNT_ID,
                    queryForm.getBasicBudgetAccountId());
        }
        if (queryForm.getProjectId() != null) {
            queryWrapper.eq(BudgetAdvanceApplyFor.PROJECT_ID, queryForm.getProjectId());
        }
        if (queryForm.getProjectBudgetAccountId() != null) {
            queryWrapper.eq(BudgetAdvanceApplyFor.PROJECT_BUDGET_ACCOUNT_ID,
                    queryForm.getProjectBudgetAccountId());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetAdvanceApplyFor.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetAdvanceApplyFor.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BudgetAdvanceApplyFor.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.eq(BudgetAdvanceApplyFor.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }
}

