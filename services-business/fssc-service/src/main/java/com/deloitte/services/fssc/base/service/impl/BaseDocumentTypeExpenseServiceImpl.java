package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeExpenseQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeExpenseVo;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypeExpense;
import com.deloitte.services.fssc.base.mapper.BaseDocumentTypeExpenseMapper;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeExpenseService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description :  BaseDocumentTypeExpense服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseDocumentTypeExpenseServiceImpl extends
        ServiceImpl<BaseDocumentTypeExpenseMapper, BaseDocumentTypeExpense> implements
        IBaseDocumentTypeExpenseService {

    @Autowired
    private BaseDocumentTypeExpenseMapper baseDocumentTypeExpenseMapper;

    @Override
    public IPage<BaseDocumentTypeExpenseVo> selectVoPage(BaseDocumentTypeExpenseQueryForm queryForm) {
        IPage<BaseDocumentTypeExpenseVo> iPage = new Page<>(queryForm.getCurrentPage(),
                queryForm.getPageSize());
        if (StringUtils.isNotBlank(queryForm.getSort())) {
            queryForm.setSort("docType." + queryForm.getSort().toUpperCase());
        }
        List<BaseDocumentTypeExpenseVo> voList = baseDocumentTypeExpenseMapper
                .selectVoPage(iPage, queryForm);
        iPage.setRecords(voList);
        return iPage;
    }

    @Override
    public List<BaseDocumentTypeExpenseVo> listVo(BaseDocumentTypeExpenseQueryForm queryForm) {
        if (StringUtils.isNotBlank(queryForm.getSort())) {
            queryForm.setSort("docType." + queryForm.getSort().toUpperCase());
        }
        return  baseDocumentTypeExpenseMapper.listVo(queryForm);
    }

    @Override
    public List<BaseDocumentTypeExpense> selectList(BaseDocumentTypeExpenseQueryForm queryForm) {
        QueryWrapper<BaseDocumentTypeExpense> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseDocumentTypeExpenseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> ids, String validFlag) {
        QueryWrapper<BaseDocumentTypeExpense> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseDocumentTypeExpense.ID, ids);
        List<BaseDocumentTypeExpense> documentTypeExpenseList = this.list(queryWrapper);
        for (BaseDocumentTypeExpense documentTypeExpense : documentTypeExpenseList) {
            documentTypeExpense.setValidFlag(validFlag);
        }
        return this.updateBatchById(documentTypeExpenseList);
    }

    @Override
    public boolean existsByExpenseMainTypeIds(List<Long> ids) {
        return this.existsByExpenseMainTypeIds(ids,null);
    }

    @Override
    public boolean existsByExpenseMainTypeIds(List<Long> ids, String validFlag) {
        QueryWrapper<BaseDocumentTypeExpense> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseDocumentTypeExpense.EXPENSE_MAIN_TYPE_ID, ids);
        if (StringUtils.isNotBlank(validFlag)){
            queryWrapper.eq(BaseDocumentTypeExpense.VALID_FLAG,validFlag);
        }
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean existsByDocumentTypeId(List<Long> ids) {
        QueryWrapper<BaseDocumentTypeExpense> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseDocumentTypeExpense.DOCUMENT_TYPE_ID, ids);
        return this.count(queryWrapper) > 0;
    }

    /**
     * 通用查询
     */
    public QueryWrapper<BaseDocumentTypeExpense> getQueryWrapper(
            QueryWrapper<BaseDocumentTypeExpense> queryWrapper,
            BaseDocumentTypeExpenseQueryForm queryForm) {
        //条件拼接
        if (queryForm.getExpenseMainTypeId() != null) {
            queryWrapper.eq(BaseDocumentTypeExpense.EXPENSE_MAIN_TYPE_ID,
                    queryForm.getExpenseMainTypeId());
        }
        if (queryForm.getDocumentTypeId() != null) {
            queryWrapper
                    .eq(BaseDocumentTypeExpense.DOCUMENT_TYPE_ID, queryForm.getDocumentTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseDocumentTypeExpense.VALID_FLAG, queryForm.getValidFlag());
        }
        return queryWrapper;
    }

}

