package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeMainTypeQueryForm;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubType;
import com.deloitte.services.fssc.base.mapper.BaseIncomeMainTypeMapper;
import com.deloitte.services.fssc.base.service.IBaseIncomeMainTypeService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description :  BaseIncomeMainType服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseIncomeMainTypeServiceImpl extends
        ServiceImpl<BaseIncomeMainTypeMapper, BaseIncomeMainType> implements
        IBaseIncomeMainTypeService {

    @Autowired
    private BaseIncomeMainTypeMapper baseIncomeMainTypeMapper;

    @Override
    public  List<BaseIncomeMainType> getMainTypeByCode(String code,String unitCode) {
        QueryWrapper<BaseIncomeMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseIncomeMainType.CODE, code);
        queryWrapper.eq(BaseIncomeMainType.UNIT_CODE,unitCode);
        return this.list(queryWrapper);
    }

    @Override
    public  List<BaseIncomeMainType> getMainTypeByCode(String code, String unitCode, String validFlag) {
        QueryWrapper<BaseIncomeMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseIncomeMainType.CODE, code);
        queryWrapper.eq(BaseIncomeMainType.UNIT_CODE,unitCode);
        if (StringUtils.isNotBlank(validFlag)) {
            queryWrapper.eq(BaseIncomeMainType.VALID_FLAG,validFlag);
        }
        return this.list(queryWrapper);
    }

    @Override
    public List<BaseIncomeMainType> getChildMainType(String parentCode,String unitCode) {
        QueryWrapper<BaseIncomeMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseIncomeMainType.PARENT_CODE, parentCode);
        queryWrapper.eq(BaseIncomeMainType.UNIT_CODE, unitCode);
        return baseIncomeMainTypeMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existChildMainType(String parentCode,String validFlag,String unitCode) {
        QueryWrapper<BaseIncomeMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseIncomeMainType.PARENT_CODE, parentCode);
        queryWrapper.eq(BaseIncomeMainType.UNIT_CODE, unitCode);
        if (StringUtils.isNotBlank(validFlag)) {
            queryWrapper.eq(BaseIncomeSubType.VALID_FLAG, validFlag);
        }
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean existChildMainType(List<Long> ids, String validFlag) {
        QueryWrapper<BaseIncomeMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseIncomeMainType.ID, ids);
        List<BaseIncomeMainType> mainTypeList =  baseIncomeMainTypeMapper.selectList(queryWrapper);
        for (BaseIncomeMainType mainType : mainTypeList){
            if (FsscEums.YES.getValue().equals(mainType.getParentFlag())){
                if (this.existChildMainType(mainType.getCode(),validFlag,mainType.getUnitCode())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateMainType(BaseIncomeMainType mainType) {
        List<BaseIncomeMainType> childMainTypeList = this.getChildMainType(mainType.getCode(),
                mainType.getUnitCode());
        if (baseIncomeMainTypeMapper.updateById(mainType) < 1){
            throw new FSSCException(FsscErrorType.SAVE_FAIL);
        }
        if (CollectionUtils.isNotEmpty(childMainTypeList)) {
            for (BaseIncomeMainType child : childMainTypeList) {
                child.setParentName(mainType.getName());
            }
            if (!this.saveOrUpdateBatch(childMainTypeList)) {
                throw new FSSCException(FsscErrorType.SAVE_FAIL);
            }
        }
        return true;
    }

    @Override
    public IPage<BaseIncomeMainType> selectPage(BaseIncomeMainTypeQueryForm queryForm) {
        QueryWrapper<BaseIncomeMainType> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseIncomeMainTypeMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }

    @Override
    public List<BaseIncomeMainType> selectList(BaseIncomeMainTypeQueryForm queryForm) {
        QueryWrapper<BaseIncomeMainType> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseIncomeMainTypeMapper.selectList(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseIncomeMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseIncomeMainType.ID, idList);
        List<BaseIncomeMainType> mainTypeList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(mainTypeList)){
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        for (BaseIncomeMainType mainType : mainTypeList) {
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                if (validFlag.equals(mainType.getValidFlag())){
                    throw new FSSCException(FsscErrorType.CANNOT_REPEAT_VALID);
                }
                mainType.setValidDate(LocalDateTime.now());
                List<BaseIncomeMainType> sameCodeMainTypeList = this.getMainTypeByCode(mainType.getCode(),
                        mainType.getUnitCode(),FsscEums.VALID.getValue());
                if (CollectionUtils.isNotEmpty(sameCodeMainTypeList)){
                    throw new FSSCException(FsscErrorType.EXISTS_INCOME_SAME_CODE_FOR_VALID);
                }
            } else {
                if (validFlag.equals(mainType.getValidFlag())){
                    throw new FSSCException(FsscErrorType.CANNOT_INVALID);
                }
                mainType.setInvalidDate(LocalDateTime.now());
            }
            mainType.setValidFlag(validFlag);
        }
        return this.updateBatchById(mainTypeList);
    }

    /**
     * 通用查询
     */
    public QueryWrapper<BaseIncomeMainType> getQueryWrapper(
            QueryWrapper<BaseIncomeMainType> queryWrapper,
            BaseIncomeMainTypeQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getUnitCode())) {
            queryWrapper.like(BaseIncomeMainType.UNIT_CODE, queryForm.getUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            queryWrapper.like(BaseIncomeMainType.CODE, queryForm.getCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.like(BaseIncomeMainType.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getParentFlag())) {
            queryWrapper.eq(BaseIncomeMainType.PARENT_FLAG, queryForm.getParentFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseIncomeMainType.VALID_FLAG, queryForm.getValidFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getSort()) && StringUtils.isNotBlank(queryForm.getSortOrder())) {
            if (FsscEums.ASC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByAsc(queryForm.getSort());
            } else if (FsscEums.DESC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByDesc(queryForm.getSort());
            }
        }else{
            queryWrapper.orderByAsc(BaseIncomeMainType.CODE);
        }
        return queryWrapper;
    }
}

