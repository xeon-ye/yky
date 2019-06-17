package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.budget.param.BudgetAfterRepaymentQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAfterRepayment;
import com.deloitte.services.fssc.budget.mapper.BudgetAfterRepaymentMapper;
import com.deloitte.services.fssc.budget.service.IBudgetAfterRepaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-28
 * @Description :  BudgetAfterRepayment服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetAfterRepaymentServiceImpl extends ServiceImpl<BudgetAfterRepaymentMapper, BudgetAfterRepayment>
        implements IBudgetAfterRepaymentService {


    @Autowired
    private BudgetAfterRepaymentMapper repaymentMapper;

    @Override
    public IPage<BudgetAfterRepayment> selectPage(BudgetAfterRepaymentQueryForm queryForm) {
        QueryWrapper<BudgetAfterRepayment> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return repaymentMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<BudgetAfterRepayment> selectList(BudgetAfterRepaymentQueryForm queryForm) {
        QueryWrapper<BudgetAfterRepayment> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return repaymentMapper.selectList(queryWrapper);
    }

    @Override
    public BudgetAfterRepayment getByDocument(Long documentId, String documentType) {
        QueryWrapper<BudgetAfterRepayment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetAfterRepayment.DOCUMENT_ID,documentId);
        queryWrapper.eq(BudgetAfterRepayment.DOCUMENT_TYPE,documentType);
        return repaymentMapper.selectOne(queryWrapper);
    }

    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<BudgetAfterRepayment> getQueryWrapper(QueryWrapper<BudgetAfterRepayment> queryWrapper,
                                                              BudgetAfterRepaymentQueryForm queryForm) {
        //条件拼接
        if (queryForm.getDocumentId() != null) {
            queryWrapper.eq(BudgetAfterRepayment.DOCUMENT_ID, queryForm.getDocumentId());
        }
        if (StringUtils.isNotBlank(queryForm.getDocumentType())) {
            queryWrapper.eq(BudgetAfterRepayment.DOCUMENT_TYPE, queryForm.getDocumentType());
        }
        if (StringUtils.isNotBlank(queryForm.getBudgetType())) {
            queryWrapper.eq(BudgetAfterRepayment.BUDGET_TYPE, queryForm.getBudgetType());
        }
        if (queryForm.getMainTypeId() != null) {
            queryWrapper.eq(BudgetAfterRepayment.MAIN_TYPE_ID, queryForm.getMainTypeId());
        }
        if (queryForm.getProjectId() != null) {
            queryWrapper.eq(BudgetAfterRepayment.PROJECT_ID, queryForm.getProjectId());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetAfterRepayment.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetAfterRepayment.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BudgetAfterRepayment.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.eq(BudgetAfterRepayment.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }
}

