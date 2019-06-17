package com.deloitte.services.fssc.performance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.performance.param.PerformancePrjMainTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjMainTypeForm;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.performance.entity.PerformancePrjMainType;
import com.deloitte.services.fssc.performance.mapper.PerformancePrjMainTypeMapper;
import com.deloitte.services.fssc.performance.service.IPerformancePrjMainTypeService;
import com.deloitte.services.fssc.performance.service.IPerformancePrjSubTypeService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description :  PerformancePrjMainType服务实现类
 * @Modified :
 */
@Service
public class PerformancePrjMainTypeServiceImpl extends
        ServiceImpl<PerformancePrjMainTypeMapper, PerformancePrjMainType> implements
        IPerformancePrjMainTypeService {

    @Autowired
    private PerformancePrjMainTypeMapper mainTypeMapper;

    @Autowired
    private IPerformancePrjSubTypeService subTypeService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateOrSaveBatch(List<PerformancePrjMainTypeForm> mainTypeFormList) {
        List<PerformancePrjMainType> addMainTypeList = new ArrayList<>();
        List<PerformancePrjMainType> updateMainTypeList = new ArrayList<>();
        for(PerformancePrjMainTypeForm mainTypeForm : mainTypeFormList) {
            PerformancePrjMainType projectMainType;
            if (mainTypeForm.getId() != null) {
                projectMainType = this.getById(mainTypeForm.getId());
                if (projectMainType != null) {
                    projectMainType.setExplain(mainTypeForm.getExplain().trim());
                } else {
                    throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
                }
                updateMainTypeList.add(projectMainType);
            } else {
                PerformancePrjMainType sameCodeMainType = this.getMainTypeByCode(mainTypeForm.getCode());
                if (sameCodeMainType != null) {
                    throw new FSSCException(FsscErrorType.CODE_REPEAT);
                }
                projectMainType = new BeanUtils<PerformancePrjMainType>()
                        .copyObj(mainTypeForm, PerformancePrjMainType.class);
                projectMainType.setName(projectMainType.getName().trim());
                projectMainType.setValidFlag(FsscEums.VALID.getValue());
                addMainTypeList.add(projectMainType);
            }
        }
        if (CollectionUtils.isNotEmpty(updateMainTypeList)) {
            this.updateBatchById(updateMainTypeList);
        }
        if (CollectionUtils.isNotEmpty(addMainTypeList)) {
            this.saveBatch(addMainTypeList);
        }
    }

    @Override
    public PerformancePrjMainType getMainTypeByCode(String code) {
        QueryWrapper<PerformancePrjMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(PerformancePrjMainType.CODE, code);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<PerformancePrjMainType> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(PerformancePrjMainType.ID, idList);
        List<PerformancePrjMainType> mainTypeList = this.list(queryWrapper);
        for (PerformancePrjMainType mainType : mainTypeList) {
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                if (validFlag.equals(mainType.getValidFlag())){
                    throw new FSSCException(FsscErrorType.CANNOT_REPEAT_VALID);
                }
            } else if (validFlag.equals(mainType.getValidFlag())){
                throw new FSSCException(FsscErrorType.CANNOT_INVALID);
            }
            mainType.setValidFlag(validFlag);
        }
        return this.updateBatchById(mainTypeList);
    }

    @Override
    public IPage<PerformancePrjMainType> selectPage(PerformancePrjMainTypeQueryForm queryForm) {
        QueryWrapper<PerformancePrjMainType> queryWrapper = new QueryWrapper<PerformancePrjMainType>();
        getQueryWrapper(queryWrapper, queryForm);
        return mainTypeMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(),
                        queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<PerformancePrjMainType> selectList(PerformancePrjMainTypeQueryForm queryForm) {
        QueryWrapper<PerformancePrjMainType> queryWrapper = new QueryWrapper<PerformancePrjMainType>();
        getQueryWrapper(queryWrapper, queryForm);
        return mainTypeMapper.selectList(queryWrapper);
    }

    /**
     * 通用查询
     */
    public QueryWrapper<PerformancePrjMainType> getQueryWrapper(
            QueryWrapper<PerformancePrjMainType> queryWrapper,
            PerformancePrjMainTypeQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getId())) {
            queryWrapper.eq(PerformancePrjMainType.ID, queryForm.getId());
        }
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            queryWrapper.like(PerformancePrjMainType.CODE, queryForm.getCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.like(PerformancePrjMainType.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getExplain())) {
            queryWrapper.like(PerformancePrjMainType.EXPLAIN, queryForm.getExplain());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(PerformancePrjMainType.VALID_FLAG, queryForm.getValidFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(PerformancePrjMainType.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(PerformancePrjMainType.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgId())) {
            queryWrapper.eq(PerformancePrjMainType.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.like(PerformancePrjMainType.ORG_PATH, queryForm.getOrgPath());
        }
        if (StringUtils.isNotBlank(queryForm.getSort()) && StringUtils.isNotBlank(queryForm.getSortOrder())) {
            if (FsscEums.ASC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByAsc(queryForm.getSort());
            } else if (FsscEums.DESC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByDesc(queryForm.getSort());
            }
        }else{
            queryWrapper.orderByDesc(PerformancePrjMainType.UPDATE_TIME);
        }
        return queryWrapper;
    }
}

