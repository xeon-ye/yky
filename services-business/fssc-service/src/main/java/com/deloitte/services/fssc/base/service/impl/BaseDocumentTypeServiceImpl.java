package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeQueryForm;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.base.mapper.BaseDocumentTypeMapper;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description :  BaseDocumentType服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseDocumentTypeServiceImpl extends
        ServiceImpl<BaseDocumentTypeMapper, BaseDocumentType> implements IBaseDocumentTypeService {

    @Autowired
    private BaseDocumentTypeMapper baseDocumentTypeMapper;

    @Override
    public IPage<BaseDocumentType> selectPage(BaseDocumentTypeQueryForm queryForm) {
        QueryWrapper<BaseDocumentType> queryWrapper = new QueryWrapper<BaseDocumentType>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseDocumentTypeMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);

    }

    @Override
    public List<BaseDocumentType> selectList(BaseDocumentTypeQueryForm queryForm) {
        QueryWrapper<BaseDocumentType> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseDocumentTypeMapper.selectList(queryWrapper);
    }

    @Override
    public List<BaseDocumentType> getDocTypeByCode(String code,String unitCode,String validFlag) {
        QueryWrapper<BaseDocumentType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseDocumentType.UNIT_CODE, unitCode);
        queryWrapper.eq(BaseDocumentType.TYPE_CODE, code);
        if (StringUtils.isNotBlank(validFlag)){
            queryWrapper.eq(BaseDocumentType.VALID_FLAG, validFlag);
        }
        return this.list(queryWrapper);
    }

    @Override
    public BaseDocumentType getDocTypeByFunction(String functionModule,String unitCode) {
        QueryWrapper<BaseDocumentType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseDocumentType.UNIT_CODE, unitCode);
        queryWrapper.eq(BaseDocumentType.FUNCTION_MODULE, functionModule);
        queryWrapper.eq(BaseDocumentType.VALID_FLAG,FsscEums.VALID.getValue());
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseDocumentType> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseDocumentType.ID, idList);
        List<BaseDocumentType> documentTypeList = this.list(queryWrapper);
        for (BaseDocumentType docType : documentTypeList) {
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                if (validFlag.equals(docType.getValidFlag())) {
                    throw new FSSCException(FsscErrorType.CANNOT_REPEAT_VALID);
                }
                List<BaseDocumentType> sameCodeList = this.getDocTypeByCode(docType.getTypeCode(),
                        docType.getUnitCode(), FsscEums.VALID.getValue());
                if (CollectionUtils.isNotEmpty(sameCodeList)) {
                    throw new FSSCException(FsscErrorType.EXISTS_SAME_CODE_AND_VALID);
                }
                BaseDocumentType validDocType = this.getDocTypeByFunction(docType.getFunctionModule(),docType.getUnitCode());
                if (validDocType != null){
                    throw new FSSCException(FsscErrorType.EXISTS_SAME_FUNCTION_AND_VALID);
                }
            } else {
                if (validFlag.equals(docType.getValidFlag())) {
                    throw new FSSCException(FsscErrorType.CANNOT_INVALID);
                }
            }
            docType.setValidFlag(validFlag);
        }
        return this.updateBatchById(documentTypeList);
    }

    /**
     * 通用查询
     */
    private QueryWrapper<BaseDocumentType> getQueryWrapper(
            QueryWrapper<BaseDocumentType> queryWrapper,
            BaseDocumentTypeQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getTypeCode())) {
            queryWrapper.like(BaseDocumentType.TYPE_CODE, queryForm.getTypeCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.like(BaseDocumentType.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getFunctionModule())) {
            queryWrapper.eq(BaseDocumentType.FUNCTION_MODULE, queryForm.getFunctionModule());
        }
        if (StringUtils.isNotBlank(queryForm.getUnitCode())) {
            queryWrapper.eq(BaseDocumentType.UNIT_CODE, queryForm.getUnitCode());
        }
        if (queryForm.getOrgId() != null) {
            queryWrapper.eq(BaseDocumentType.ORG_ID, queryForm.getOrgId());
        }
        if (queryForm.getOrgPath() != null) {
            queryWrapper.like(BaseDocumentType.ORG_PATH, queryForm.getOrgPath());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseDocumentType.VALID_FLAG, queryForm.getValidFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getSort()) && StringUtils.isNotBlank(queryForm.getSortOrder())) {
            if (FsscEums.ASC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByAsc(queryForm.getSort());
            } else if (FsscEums.DESC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByDesc(queryForm.getSort());
            }
        }else{
            queryWrapper.orderByDesc(BaseDocumentType.UPDATE_TIME);
        }
        return queryWrapper;
    }
}

