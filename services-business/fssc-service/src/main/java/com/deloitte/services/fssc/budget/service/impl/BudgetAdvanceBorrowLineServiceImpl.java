package com.deloitte.services.fssc.budget.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.budget.param.BudgetAdvanceBorrowLineQueryForm;
import com.deloitte.services.fssc.budget.entity.BudgetAdvanceBorrowLine;
import com.deloitte.services.fssc.budget.mapper.BudgetAdvanceBorrowLineMapper;
import com.deloitte.services.fssc.budget.service.IBudgetAdvanceBorrowLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description :  BudgetAdvanceBorrowLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BudgetAdvanceBorrowLineServiceImpl extends
        ServiceImpl<BudgetAdvanceBorrowLineMapper, BudgetAdvanceBorrowLine> implements
        IBudgetAdvanceBorrowLineService {


    @Autowired
    private BudgetAdvanceBorrowLineMapper advanceBorrowLineMapper;

    @Override
    public BudgetAdvanceBorrowLine getByExpenseSubTypeId(Long documentId, String documentType,
            Long expenseSubTypeId) {
        QueryWrapper<BudgetAdvanceBorrowLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetAdvanceBorrowLine.DOCUMENT_ID,documentId);
        queryWrapper.eq(BudgetAdvanceBorrowLine.DOCUMENT_TYPE,documentType);
        queryWrapper.eq(BudgetAdvanceBorrowLine.SUB_TYPE_ID,expenseSubTypeId);
        return this.getOne(queryWrapper);
    }

    @Override
    public BudgetAdvanceBorrowLine getByLineNumber(Long documentId, String documentType, Long lineNumber) {
        QueryWrapper<BudgetAdvanceBorrowLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetAdvanceBorrowLine.DOCUMENT_ID,documentId);
        queryWrapper.eq(BudgetAdvanceBorrowLine.DOCUMENT_TYPE,documentType);
        queryWrapper.eq(BudgetAdvanceBorrowLine.LINE_NUMBER,lineNumber);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<BudgetAdvanceBorrowLine> selectList(Long documentId, String documentType) {
        QueryWrapper<BudgetAdvanceBorrowLine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BudgetAdvanceBorrowLine.DOCUMENT_ID,documentId);
        queryWrapper.eq(BudgetAdvanceBorrowLine.DOCUMENT_TYPE,documentType);
        return this.list(queryWrapper);
    }

    @Override
    public void deleteByDocument(Long documentId, String documentType) {
        QueryWrapper<BudgetAdvanceBorrowLine> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq(BudgetAdvanceBorrowLine.DOCUMENT_ID,documentId);
        deleteWrapper.eq(BudgetAdvanceBorrowLine.DOCUMENT_TYPE,documentType);
        this.remove(deleteWrapper);
    }

    @Override
    public IPage<BudgetAdvanceBorrowLine> selectPage(BudgetAdvanceBorrowLineQueryForm queryForm) {
        QueryWrapper<BudgetAdvanceBorrowLine> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return advanceBorrowLineMapper
                .selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                        queryWrapper);

    }

    @Override
    public List<BudgetAdvanceBorrowLine> selectList(BudgetAdvanceBorrowLineQueryForm queryForm) {
        QueryWrapper<BudgetAdvanceBorrowLine> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return advanceBorrowLineMapper.selectList(queryWrapper);
    }

    private QueryWrapper<BudgetAdvanceBorrowLine> getQueryWrapper(
            QueryWrapper<BudgetAdvanceBorrowLine> queryWrapper,
            BudgetAdvanceBorrowLineQueryForm queryForm) {
        //条件拼接
        if (queryForm.getDocumentId() != null) {
            queryWrapper.eq(BudgetAdvanceBorrowLine.DOCUMENT_ID, queryForm.getDocumentId());
        }
        if (StringUtils.isNotBlank(queryForm.getDocumentType())) {
            queryWrapper.eq(BudgetAdvanceBorrowLine.DOCUMENT_TYPE, queryForm.getDocumentType());
        }
        if (queryForm.getSubTypeId() != null) {
            queryWrapper.eq(BudgetAdvanceBorrowLine.SUB_TYPE_ID, queryForm.getSubTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BudgetAdvanceBorrowLine.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BudgetAdvanceBorrowLine.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BudgetAdvanceBorrowLine.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.eq(BudgetAdvanceBorrowLine.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }
}

