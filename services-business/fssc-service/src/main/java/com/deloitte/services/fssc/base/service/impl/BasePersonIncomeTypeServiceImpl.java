package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BasePersonIncomeTypeQueryForm;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BasePersonIncomeType;
import com.deloitte.services.fssc.base.mapper.BasePersonIncomeTypeMapper;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeService;
import com.deloitte.services.fssc.base.service.IBasePersonIncomeTypeService;
import com.deloitte.services.fssc.eums.FsscEums;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-22
 * @Description :  BasePersonIncomeType服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BasePersonIncomeTypeServiceImpl extends ServiceImpl<BasePersonIncomeTypeMapper, BasePersonIncomeType>
        implements IBasePersonIncomeTypeService {


    @Autowired
    private BasePersonIncomeTypeMapper basePersonIncomeTypeMapper;

    @Autowired
    private IBaseDocumentTypeService documentTypeService;

    @Override
    public IPage<BasePersonIncomeType> selectPage(BasePersonIncomeTypeQueryForm queryForm) {
        QueryWrapper<BasePersonIncomeType> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return basePersonIncomeTypeMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<BasePersonIncomeType> selectList(BasePersonIncomeTypeQueryForm queryForm) {
        QueryWrapper<BasePersonIncomeType> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return basePersonIncomeTypeMapper.selectList(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> ids, String validFlag) {
        QueryWrapper<BasePersonIncomeType> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BasePersonIncomeType.ID, ids);
        List<BasePersonIncomeType> personTypeList = this.list(queryWrapper);
        for (BasePersonIncomeType personType : personTypeList) {
            personType.setValidFlag(validFlag);
        }
        return this.updateBatchById(personTypeList);
    }

    @Override
    public boolean existsByDocumentTypeId(List<Long> ids) {
        QueryWrapper<BasePersonIncomeType> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BasePersonIncomeType.DOCUMENT_TYPE_ID, ids);
        return this.count(queryWrapper) > 0;
    }

    @Override
    public List<BasePersonIncomeType> selectByDocumentTypeId(Long documentId) {
        QueryWrapper<BasePersonIncomeType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BasePersonIncomeType.VALID_FLAG, FsscEums.VALID.getValue());
        queryWrapper.eq(BasePersonIncomeType.DOCUMENT_TYPE_ID,documentId);
        return this.list(queryWrapper);
    }

    @Override
    public List<BasePersonIncomeType> selectByFunctionModule(String functionModule, String unitCode) {
        BaseDocumentType documentType = documentTypeService.getDocTypeByFunction(functionModule,unitCode);
        if (documentType == null){
            return Collections.emptyList();
        }
        return this.selectByDocumentTypeId(documentType.getId());
    }

    public QueryWrapper<BasePersonIncomeType> getQueryWrapper(QueryWrapper<BasePersonIncomeType> queryWrapper, BasePersonIncomeTypeQueryForm queryForm) {
        //条件拼接
        if (queryForm.getDocumentTypeId() != null) {
            queryWrapper.eq(BasePersonIncomeType.DOCUMENT_TYPE_ID, queryForm.getDocumentTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getTypeCode())) {
            queryWrapper.like(BasePersonIncomeType.TYPE_CODE, queryForm.getTypeCode());
        }
        if (StringUtils.isNotBlank(queryForm.getTypeName())) {
            queryWrapper.like(BasePersonIncomeType.TYPE_NAME, queryForm.getTypeName());
        }
        if (StringUtils.isNotBlank(queryForm.getTypeExplain())) {
            queryWrapper.like(BasePersonIncomeType.TYPE_EXPLAIN, queryForm.getTypeExplain());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BasePersonIncomeType.VALID_FLAG, queryForm.getValidFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(BasePersonIncomeType.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(BasePersonIncomeType.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BasePersonIncomeType.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.like(BasePersonIncomeType.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }

}

