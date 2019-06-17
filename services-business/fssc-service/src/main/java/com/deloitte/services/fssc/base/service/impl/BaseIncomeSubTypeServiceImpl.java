package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeSubTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseIncomeSubTypeVo;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubType;
import com.deloitte.services.fssc.base.mapper.BaseIncomeSubTypeMapper;
import com.deloitte.services.fssc.base.service.IBaseIncomeSubTypeService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description :  BaseIncomeSubType服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseIncomeSubTypeServiceImpl extends
        ServiceImpl<BaseIncomeSubTypeMapper, BaseIncomeSubType> implements
        IBaseIncomeSubTypeService {

    @Autowired
    BaseIncomeSubTypeMapper subTypeMapper;

    @Override
    public BaseIncomeSubTypeVo getSubTypeById(Long id) {
        return subTypeMapper.selectVo(id);
    }

    @Override
    public List<BaseIncomeSubType> getSubTypeByCode(String code,String unitCode,String validFlag) {
        QueryWrapper<BaseIncomeSubType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseIncomeSubType.CODE, code);
        queryWrapper.eq(BaseIncomeSubType.UNIT_CODE,unitCode);
        if (StringUtils.isNotBlank(validFlag)){
            queryWrapper.eq(BaseIncomeSubType.VALID_FLAG,validFlag);
        }
        return this.list(queryWrapper);
    }

    @Override
    public boolean updateSubType(BaseIncomeSubType subType) {
        return subTypeMapper.updateById(subType) > 0;
    }

    @Override
    public IPage<BaseIncomeSubType> selectPage(BaseIncomeSubTypeQueryForm queryForm) {
        QueryWrapper<BaseIncomeSubType> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return subTypeMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }

    @Override
    public IPage<BaseIncomeSubTypeVo> selectVoPage(BaseIncomeSubTypeQueryForm queryForm) {
        Page page = new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize());
        if (StringUtils.isNotBlank(queryForm.getSortOrder())){
            queryForm.setSortOrder("subType."+queryForm.getSortOrder().toUpperCase());
        }
        if (StringUtils.isNotBlank(queryForm.getSort())){
            queryForm.setSort("subType."+queryForm.getSort().toUpperCase());
        }
        List<BaseIncomeSubTypeVo> subTypeVoList = subTypeMapper.selectVoPage(page, queryForm);
        page.setRecords(subTypeVoList);
        return page;
    }

    @Override
    public List<BaseIncomeSubType> selectList(BaseIncomeSubTypeQueryForm queryForm) {
        QueryWrapper<BaseIncomeSubType> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return subTypeMapper.selectList(queryWrapper);
    }

    @Override
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseIncomeSubType> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseIncomeMainType.ID, idList);
        List<BaseIncomeSubType> subTypeList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(subTypeList)){
            throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
        }
        for (BaseIncomeSubType subType : subTypeList) {
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                if (validFlag.equals(subType.getValidFlag())) {
                    throw new FSSCException(FsscErrorType.CANNOT_REPEAT_VALID);
                }
                subType.setValidDate(LocalDateTime.now());
                List<BaseIncomeSubType> sameCodeSubTypeList = this.getSubTypeByCode(subType.getCode(),
                        subType.getUnitCode(),FsscEums.VALID.getValue());
                if (CollectionUtils.isNotEmpty(sameCodeSubTypeList)) {
                    throw new FSSCException(FsscErrorType.EXISTS_INCOME_SUB_SAME_CODE_FOR_VALID);
                }
            } else {
                if (validFlag.equals(subType.getValidFlag())) {
                    throw new FSSCException(FsscErrorType.CANNOT_INVALID);
                }
                subType.setInvalidDate(LocalDateTime.now());
            }
            subType.setValidFlag(validFlag);
        }
        return this.updateBatchById(subTypeList);
    }

    @Override
    public boolean existsByIncomeMainTypeIds(List<Long> incomeMainTypeIdList) {
        QueryWrapper<BaseIncomeSubType> queryWrapper = new QueryWrapper();
        queryWrapper.in(BaseIncomeSubType.INCOME_MAIN_TYPE_ID,incomeMainTypeIdList);
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean existsByIncomeMainTypeIds(List<Long> incomeMainTypeIdList, String validFlag) {
        QueryWrapper<BaseIncomeSubType> queryWrapper = new QueryWrapper();
        queryWrapper.in(BaseIncomeSubType.INCOME_MAIN_TYPE_ID,incomeMainTypeIdList);
        queryWrapper.eq(BaseIncomeSubType.VALID_FLAG,validFlag);
        return this.count(queryWrapper) > 0;
    }

    /**
     * 通用查询
     */
    public QueryWrapper<BaseIncomeSubType> getQueryWrapper(
            QueryWrapper<BaseIncomeSubType> queryWrapper,
            BaseIncomeSubTypeQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getIncomeMainTypeId())) {
            queryWrapper.like(BaseIncomeSubType.INCOME_MAIN_TYPE_ID, queryForm.getIncomeMainTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getUnitCode())) {
            queryWrapper.like(BaseIncomeSubType.UNIT_CODE, queryForm.getUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            queryWrapper.like(BaseIncomeSubType.CODE, queryForm.getCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.like(BaseIncomeSubType.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseIncomeSubType.VALID_FLAG, queryForm.getValidFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getSort()) && StringUtils.isNotBlank(queryForm.getSortOrder())) {
            if (FsscEums.ASC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByAsc(queryForm.getSort());
            } else if (FsscEums.DESC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByDesc(queryForm.getSort());
            }
        }else{
            queryWrapper.orderByDesc(BaseIncomeSubType.UPDATE_TIME);
        }
        return queryWrapper;
    }
}

