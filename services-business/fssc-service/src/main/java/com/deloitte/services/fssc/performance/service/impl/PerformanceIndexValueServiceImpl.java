package com.deloitte.services.fssc.performance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexValueQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexValueForm;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.performance.entity.PerformanceIndexValue;
import com.deloitte.services.fssc.performance.entity.PerformancePrjMainType;
import com.deloitte.services.fssc.performance.mapper.PerformanceIndexValueMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexService;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexValueService;
import java.util.ArrayList;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description :  PerformanceIndex服务实现类
 * @Modified :
 */
@Service
public class PerformanceIndexValueServiceImpl extends
        ServiceImpl<PerformanceIndexValueMapper, PerformanceIndexValue> implements
        IPerformanceIndexValueService {


    @Autowired
    private PerformanceIndexValueMapper performanceIndexMapper;

    @Autowired
    private IPerformanceIndexService indexService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateOrSaveBatch(List<PerformanceIndexValueForm> indexValueFormList) {
        List<PerformanceIndexValue> addIndexValueList = new ArrayList<>();
        List<PerformanceIndexValue> updateIndexValueList = new ArrayList<>();
        for(PerformanceIndexValueForm indexValueForm : indexValueFormList) {
            PerformanceIndexValue indexValue;
            if (indexValueForm.getId() != null) {
                List<Long> ids = new ArrayList<>();
                ids.add(Long.valueOf(indexValueForm.getId()));
                //TODO 如果指标定义关联,不能编辑
                if (indexService.existsByValueIds(ids)) {
                    throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_EDIT);
                }
                indexValue = this.getById(indexValueForm.getId());
                if (indexValue != null) {
                    indexValue.setExplain(indexValueForm.getExplain().trim());
                } else {
                    throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
                }
                updateIndexValueList.add(indexValue);
            } else {
                PerformanceIndexValue sameCodeIndexValue = this.getIndexValueByCode(indexValueForm.getCode());
                if (sameCodeIndexValue != null) {
                    throw new FSSCException(FsscErrorType.CODE_REPEAT);
                }
                indexValue = new BeanUtils<PerformanceIndexValue>()
                        .copyObj(indexValueForm, PerformanceIndexValue.class);
                indexValue.setName(indexValue.getName().trim());
                indexValue.setValidFlag(FsscEums.VALID.getValue());
                addIndexValueList.add(indexValue);
            }
        }
        if (CollectionUtils.isNotEmpty(updateIndexValueList)) {
            this.updateBatchById(updateIndexValueList);
        }
        if (CollectionUtils.isNotEmpty(addIndexValueList)) {
            this.saveBatch(addIndexValueList);
        }
    }

    @Override
    public PerformanceIndexValue getIndexValueByCode(String code) {
        QueryWrapper<PerformanceIndexValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(PerformanceIndexValue.CODE, code);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<PerformanceIndexValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(PerformanceIndexValue.ID, idList);
        List<PerformanceIndexValue> indexValueList = this.list(queryWrapper);
        for (PerformanceIndexValue indexValue : indexValueList) {
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                if (validFlag.equals(indexValue.getValidFlag())){
                    throw new FSSCException(FsscErrorType.CANNOT_REPEAT_VALID);
                }
            } else if (validFlag.equals(indexValue.getValidFlag())){
                throw new FSSCException(FsscErrorType.CANNOT_INVALID);
            }
            indexValue.setValidFlag(validFlag);
        }
        return this.updateBatchById(indexValueList);
    }

    @Override
    public IPage<PerformanceIndexValue> selectPage(PerformanceIndexValueQueryForm queryForm) {
        QueryWrapper<PerformanceIndexValue> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper,queryForm);
        return performanceIndexMapper.selectPage(
                new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                queryWrapper);

    }

    @Override
    public List<PerformanceIndexValue> selectList(PerformanceIndexValueQueryForm queryForm) {
        QueryWrapper<PerformanceIndexValue> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper,queryForm);
        return performanceIndexMapper.selectList(queryWrapper);
    }

    /**
     * 通用查询
     */
    public QueryWrapper<PerformanceIndexValue> getQueryWrapper(
            QueryWrapper<PerformanceIndexValue> queryWrapper, PerformanceIndexValueQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getId())) {
            queryWrapper.eq(PerformanceIndexValue.ID, queryForm.getId());
        }
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            queryWrapper.like(PerformanceIndexValue.CODE, queryForm.getCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.like(PerformanceIndexValue.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(PerformanceIndexValue.VALID_FLAG, queryForm.getValidFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(PerformanceIndexValue.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(PerformanceIndexValue.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgId())) {
            queryWrapper.eq(PerformanceIndexValue.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.like(PerformanceIndexValue.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }
}

