package com.deloitte.services.fssc.performance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.performance.param.PerformancePrjSubTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjSubTypeForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjSubTypeVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.performance.entity.PerformancePrjSubType;
import com.deloitte.services.fssc.performance.mapper.PerformancePrjSubTypeMapper;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexLibraryService;
import com.deloitte.services.fssc.performance.service.IPerformancePrjSubTypeService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description :  PerformancePrjSubType服务实现类
 * @Modified :
 */
@Service
public class PerformancePrjSubTypeServiceImpl extends
        ServiceImpl<PerformancePrjSubTypeMapper, PerformancePrjSubType> implements
        IPerformancePrjSubTypeService {


    @Autowired
    private PerformancePrjSubTypeMapper subTypeMapper;

    @Autowired
    private IPerformanceIndexLibraryService indexLibraryService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateOrSaveBatch(List<PerformancePrjSubTypeForm> subTypeFormList) {
        List<PerformancePrjSubType> addSubTypeList = new ArrayList<>();
        List<PerformancePrjSubType> updateSubTypeList = new ArrayList<>();
        for(PerformancePrjSubTypeForm subTypeForm : subTypeFormList) {
            PerformancePrjSubType subType;
            if (subTypeForm.getId() != null) {
//                List<Long> ids = new ArrayList<>();
//                ids.add(Long.valueOf(subTypeForm.getId()));
//                TODO 如果关联指标库后，也需要考虑是否能被编辑问题
//                if (indexLibraryService.existsBySubTypeIds(ids)) {
//                    throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_EDIT);
//                }
                subType = this.getById(subTypeForm.getId());
                if (subType != null) {
                    subType.setExplain(subTypeForm.getExplain().trim());
                } else {
                    throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
                }
                updateSubTypeList.add(subType);
            } else {
                PerformancePrjSubType sameCodeMainType = this.getSubTypeByCode(subTypeForm.getCode());
                if (sameCodeMainType != null) {
                    throw new FSSCException(FsscErrorType.CODE_REPEAT);
                }
                subType = new BeanUtils<PerformancePrjSubType>()
                        .copyObj(subTypeForm, PerformancePrjSubType.class);
                subType.setName(subType.getName().trim());
                subType.setValidFlag(FsscEums.VALID.getValue());
                addSubTypeList.add(subType);
            }
        }
        if (CollectionUtils.isNotEmpty(updateSubTypeList)) {
            this.updateBatchById(updateSubTypeList);
        }
        if (CollectionUtils.isNotEmpty(addSubTypeList)) {
            this.saveBatch(addSubTypeList);
        }
    }

    @Override
    public PerformancePrjSubType getSubTypeByCode(String code) {
        QueryWrapper<PerformancePrjSubType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(PerformancePrjSubType.CODE, code);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<PerformancePrjSubType> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(PerformancePrjSubType.ID, idList);
        List<PerformancePrjSubType> mainTypeList = this.list(queryWrapper);
        for (PerformancePrjSubType subType : mainTypeList) {
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                if (validFlag.equals(subType.getValidFlag())){
                    throw new FSSCException(FsscErrorType.CANNOT_REPEAT_VALID);
                }
            } else if (validFlag.equals(subType.getValidFlag())){
                throw new FSSCException(FsscErrorType.CANNOT_INVALID);
            }
            subType.setValidFlag(validFlag);
        }
        return this.updateBatchById(mainTypeList);
    }

    @Override
    public boolean existsByMainTypeIds(List<Long> idList) {
       return this.existsByMainTypeIds(idList,null);
    }

    @Override
    public boolean existsByMainTypeIds(List<Long> idList, String validFlag) {
        QueryWrapper<PerformancePrjSubType> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(PerformancePrjSubType.MAIN_TYPE_ID, idList);
        if (StringUtils.isNotBlank(validFlag)) {
            queryWrapper.in(PerformancePrjSubType.VALID_FLAG, validFlag);
        }
        return this.count(queryWrapper) > 0;
    }

    @Override
    public IPage<PerformancePrjSubTypeVo> selectVoPage(PerformancePrjSubTypeQueryForm queryForm) {
        Page page = new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize());
        if (StringUtils.isNotBlank(queryForm.getSort())) {
            queryForm.setSort("subType." + queryForm.getSort().toUpperCase());
        }
        List<PerformancePrjSubTypeVo> subTypeVoList = subTypeMapper.selectVoPage(page, queryForm);
        page.setRecords(subTypeVoList);
        return page;
    }

    @Override
    public IPage<PerformancePrjSubType> selectPage(PerformancePrjSubTypeQueryForm queryForm) {
        QueryWrapper<PerformancePrjSubType> queryWrapper = new QueryWrapper<PerformancePrjSubType>();
        getQueryWrapper(queryWrapper, queryForm);
        return subTypeMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(),
                        queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<PerformancePrjSubType> selectList(PerformancePrjSubTypeQueryForm queryForm) {
        QueryWrapper<PerformancePrjSubType> queryWrapper = new QueryWrapper<PerformancePrjSubType>();
        getQueryWrapper(queryWrapper, queryForm);
        return subTypeMapper.selectList(queryWrapper);
    }

    /**
     * 通用查询
     */
    public QueryWrapper<PerformancePrjSubType> getQueryWrapper(
            QueryWrapper<PerformancePrjSubType> queryWrapper,
            PerformancePrjSubTypeQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getId())) {
            queryWrapper.eq(PerformancePrjSubType.ID, queryForm.getId());
        }
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            queryWrapper.like(PerformancePrjSubType.CODE, queryForm.getCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.like(PerformancePrjSubType.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getExplain())) {
            queryWrapper.eq(PerformancePrjSubType.EXPLAIN, queryForm.getExplain());
        }
        if (StringUtils.isNotBlank(queryForm.getMainTypeId())) {
            queryWrapper.eq(PerformancePrjSubType.MAIN_TYPE_ID, queryForm.getMainTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(PerformancePrjSubType.VALID_FLAG, queryForm.getValidFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(PerformancePrjSubType.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(PerformancePrjSubType.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgId())) {
            queryWrapper.eq(PerformancePrjSubType.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.like(PerformancePrjSubType.ORG_PATH, queryForm.getOrgPath());
        }
        if (StringUtils.isNotBlank(queryForm.getSort()) && StringUtils.isNotBlank(queryForm.getSortOrder())) {
            if (FsscEums.ASC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByAsc(queryForm.getSort());
            } else if (FsscEums.DESC.getValue().equals(queryForm.getSortOrder())) {
                queryWrapper.orderByDesc(queryForm.getSort());
            }
        }else{
            queryWrapper.orderByDesc(PerformancePrjSubType.UPDATE_TIME);
        }
        return queryWrapper;
    }
}

