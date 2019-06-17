package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.budget.param.BudgetAfterRepaymentLineQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAfterRepaymentLine;
import com.deloitte.services.fssc.budget.mapper.BudgetAfterRepaymentLineMapper;
import com.deloitte.services.fssc.budget.service.IBudgetAfterRepaymentLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-28
 * @Description :  BudgetAfterRepaymentLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetAfterRepaymentLineServiceImpl extends ServiceImpl<BudgetAfterRepaymentLineMapper, BudgetAfterRepaymentLine>
        implements IBudgetAfterRepaymentLineService {


    @Autowired
    private BudgetAfterRepaymentLineMapper repaymentLineMapper;

    @Override
    public IPage<BudgetAfterRepaymentLine> selectPage(BudgetAfterRepaymentLineQueryForm queryForm) {
        QueryWrapper<BudgetAfterRepaymentLine> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return repaymentLineMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<BudgetAfterRepaymentLine> selectList(BudgetAfterRepaymentLineQueryForm queryForm) {
        QueryWrapper<BudgetAfterRepaymentLine> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return repaymentLineMapper.selectList(queryWrapper);
    }

    @Override
    public List<BudgetAfterRepaymentLine> listByDocument(Long documentId, String documentType) {
        QueryWrapper<BudgetAfterRepaymentLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetAfterRepaymentLine.DOCUMENT_ID,documentId);
        queryWrapper.eq(BudgetAfterRepaymentLine.DOCUMENT_TYPE,documentType);
        return repaymentLineMapper.selectList(queryWrapper);
    }

    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     **/
    public QueryWrapper<BudgetAfterRepaymentLine> getQueryWrapper(QueryWrapper<BudgetAfterRepaymentLine> queryWrapper,
                                                                  BudgetAfterRepaymentLineQueryForm queryForm) {
        //条件拼接
        if (queryForm.getDocumentId() != null) {
            queryWrapper.eq(BudgetAfterRepaymentLine.DOCUMENT_ID, queryForm.getDocumentId());
        }
        if (StringUtils.isNotBlank(queryForm.getDocumentType())) {
            queryWrapper.eq(BudgetAfterRepaymentLine.DOCUMENT_TYPE, queryForm.getDocumentType());
        }
        if (queryForm.getAdvanceBorrowLineId() != null) {
            queryWrapper.eq(BudgetAfterRepaymentLine.ADVANCE_BORROW_LINE_ID, queryForm.getAdvanceBorrowLineId());
        }
        if (queryForm.getBorrowLineId() != null) {
            queryWrapper.eq(BudgetAfterRepaymentLine.BORROW_LINE_ID, queryForm.getBorrowLineId());
        }
        if (queryForm.getSubTypeId() != null) {
            queryWrapper.eq(BudgetAfterRepaymentLine.SUB_TYPE_ID, queryForm.getSubTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetAfterRepaymentLine.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetAfterRepaymentLine.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BudgetAfterRepaymentLine.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.like(BudgetAfterRepaymentLine.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }
}

