package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeIncomeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeIncomeVo;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypeExpense;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypeIncome;
import com.deloitte.services.fssc.base.mapper.BaseDocumentTypeIncomeMapper;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeIncomeService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description :  BaseDocumentTypeIncome服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseDocumentTypeIncomeServiceImpl extends
        ServiceImpl<BaseDocumentTypeIncomeMapper, BaseDocumentTypeIncome> implements
        IBaseDocumentTypeIncomeService {

    @Autowired
    private BaseDocumentTypeIncomeMapper docTypeIncomeMapper;

    @Override
    public IPage<BaseDocumentTypeIncomeVo> selectVoPage(BaseDocumentTypeIncomeQueryForm queryForm) {
        IPage<BaseDocumentTypeIncomeVo> iPage = new Page<>(queryForm.getCurrentPage(),
                queryForm.getPageSize());
        if (StringUtils.isNotBlank(queryForm.getSort())) {
            queryForm.setSort("docType." + queryForm.getSort().toUpperCase());
        }
        List<BaseDocumentTypeIncomeVo> voList = docTypeIncomeMapper.selectVoPage(iPage, queryForm);
        iPage.setRecords(voList);
        return iPage;
    }

    @Override
    public List<BaseDocumentTypeIncomeVo> listVo(BaseDocumentTypeIncomeQueryForm queryForm) {
        if (StringUtils.isNotBlank(queryForm.getSort())) {
            queryForm.setSort("docType." + queryForm.getSort().toUpperCase());
        }
        return  docTypeIncomeMapper.listVo(queryForm);
    }

    @Override
    public List<BaseDocumentTypeIncome> selectList(BaseDocumentTypeIncomeQueryForm queryForm) {
        QueryWrapper<BaseDocumentTypeIncome> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return docTypeIncomeMapper.selectList(queryWrapper);
    }


    @Override
    public boolean modifyValidFlag(List<Long> ids, String validFlag) {
        QueryWrapper<BaseDocumentTypeIncome> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseDocumentTypeIncome.ID, ids);
        List<BaseDocumentTypeIncome> docTypeIncomeList = this.list(queryWrapper);
        for (BaseDocumentTypeIncome docTypeIncome : docTypeIncomeList) {
            docTypeIncome.setValidFlag(validFlag);
        }
        return this.updateBatchById(docTypeIncomeList);
    }

    @Override
    public boolean existsByIncomeMainTypeIds(List<Long> ids) {
        return this.existsByIncomeMainTypeIds(ids,null);
    }

    @Override
    public boolean existsByIncomeMainTypeIds(List<Long> ids, String validFlag) {
        QueryWrapper<BaseDocumentTypeIncome> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseDocumentTypeIncome.INCOME_MAIN_TYPE_ID, ids);
        if (StringUtils.isNotBlank(validFlag)){
            queryWrapper.eq(BaseDocumentTypeIncome.VALID_FLAG,validFlag);
        }
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean existsByDocumentTypeId(List<Long> ids) {
        QueryWrapper<BaseDocumentTypeIncome> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseDocumentTypeIncome.DOCUMENT_TYPE_ID, ids);
        return this.count(queryWrapper) > 0;
    }

    /**
     * 通用查询
     */
    public QueryWrapper<BaseDocumentTypeIncome> getQueryWrapper(
            QueryWrapper<BaseDocumentTypeIncome> queryWrapper,
            BaseDocumentTypeIncomeQueryForm queryForm) {
        //条件拼接
        if (queryForm.getIncomeMainTypeId() != null) {
            queryWrapper.eq(BaseDocumentTypeIncome.INCOME_MAIN_TYPE_ID,
                    queryForm.getIncomeMainTypeId());
        }
        if (queryForm.getDocumentTypeId() != null) {
            queryWrapper.eq(BaseDocumentTypeIncome.DOCUMENT_TYPE_ID,
                    queryForm.getDocumentTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseDocumentTypeIncome.VALID_FLAG, queryForm.getValidFlag());
        }
        return queryWrapper;
    }
}

