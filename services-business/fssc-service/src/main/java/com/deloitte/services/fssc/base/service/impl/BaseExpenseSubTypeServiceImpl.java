package com.deloitte.services.fssc.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseSubTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseExpenseSubTypeVo;
import com.deloitte.services.fssc.base.entity.BaseExpenseSubType;
import com.deloitte.services.fssc.base.mapper.BaseExpenseSubTypeMapper;
import com.deloitte.services.fssc.base.service.IBaseExpenseSubTypeService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-02
 * @Description :  BaseExpenseSubType服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseExpenseSubTypeServiceImpl extends ServiceImpl<BaseExpenseSubTypeMapper, BaseExpenseSubType> implements IBaseExpenseSubTypeService {


    @Autowired
    private BaseExpenseSubTypeMapper baseExpenseSubTypeMapper;


    @Override
    public BaseExpenseSubType getSubTypeByCode(String code,String unitCode) {
        QueryWrapper<BaseExpenseSubType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BaseExpenseSubType.CODE, code);
        queryWrapper.eq(BaseExpenseSubType.UNIT_CODE, unitCode);
        queryWrapper.eq(BaseExpenseSubType.VALID_FLAG,"Y");
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean updateSubType(BaseExpenseSubType subType) {
        return baseExpenseSubTypeMapper.updateById(subType) > 0;
    }

    @Override
    public IPage<BaseExpenseSubType> selectPage(BaseExpenseSubTypeQueryForm queryForm) {
        QueryWrapper<BaseExpenseSubType> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseExpenseSubTypeMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);
    }


    @Override
    public List<BaseExpenseSubType> selectList(BaseExpenseSubTypeQueryForm queryForm) {
        QueryWrapper<BaseExpenseSubType> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return baseExpenseSubTypeMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<BaseExpenseSubTypeVo> selectVoPage(BaseExpenseSubTypeQueryForm queryForm) {
        if (StringUtils.isNotBlank(queryForm.getSortOrder())){
            queryForm.setSortOrder("subType."+queryForm.getSortOrder().toUpperCase());
        }
        if (StringUtils.isNotBlank(queryForm.getSort())){
            queryForm.setSort("subType."+queryForm.getSort().toUpperCase());
        }
        Page page = new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize());
        List<BaseExpenseSubTypeVo> subTypeVoList = baseExpenseSubTypeMapper.selectVoPage(page, queryForm);
        page.setRecords(subTypeVoList);
        return page;
    }

    @Override
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<BaseExpenseSubType> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(BaseExpenseSubType.ID, idList);
        List<BaseExpenseSubType> subTypeList = this.list(queryWrapper);
        for (BaseExpenseSubType subType : subTypeList) {
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                if (validFlag.equals(subType.getValidFlag())) {
                    throw new FSSCException(FsscErrorType.CANNOT_REPEAT_VALID);
                }
                subType.setValidDate(LocalDateTime.now());
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
    public BaseExpenseSubTypeVo getSubTypeById(Long id) {
        return baseExpenseSubTypeMapper.selectVo(id);
    }

    @Override
    public boolean existsByExpenseMainTypeIds(List<Long> expenseMainTypeIdList) {
        return baseExpenseSubTypeMapper.countByExpenseMainTypeIds(expenseMainTypeIdList) > 0 ? true : false;
    }
    /**
     * 通用查询
     */
    public QueryWrapper<BaseExpenseSubType> getQueryWrapper(
            QueryWrapper<BaseExpenseSubType> queryWrapper,
            BaseExpenseSubTypeQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getUnitCode())) {
            queryWrapper.like(BaseExpenseSubType.UNIT_CODE, queryForm.getUnitCode());
        }
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            queryWrapper.like(BaseExpenseSubType.CODE, queryForm.getCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.like(BaseExpenseSubType.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(BaseExpenseSubType.VALID_FLAG, queryForm.getValidFlag());
        }
        if (queryForm.getExpenseMainTypeId()!=null) {
            queryWrapper.eq(BaseExpenseSubType.EXPENSE_MAIN_TYPE_ID, queryForm.getExpenseMainTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getSort())) {
            if (FsscEums.ASC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByAsc(queryForm.getSort());
            } else if (FsscEums.DESC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByDesc(queryForm.getSort());
            }
        }else{
            queryWrapper.orderByAsc(BaseExpenseSubType.CODE);
        }
        return queryWrapper;
    }
}

