package com.deloitte.services.fssc.performance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexLibraryQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexLibraryForm;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.performance.entity.PerformanceIndexLibrary;
import com.deloitte.services.fssc.performance.mapper.PerformanceIndexLibraryMapper;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexLibraryService;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexService;
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
 * @Description :  PerformanceIndexLibrary服务实现类
 * @Modified :
 */
@Service
public class PerformanceIndexLibraryServiceImpl extends
        ServiceImpl<PerformanceIndexLibraryMapper, PerformanceIndexLibrary> implements
        IPerformanceIndexLibraryService {


    @Autowired
    private PerformanceIndexLibraryMapper indexLibraryMapper;

    @Autowired
    private IPerformanceIndexService indexService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateOrSaveBatch(List<PerformanceIndexLibraryForm> indexLibraryFormList) {
        List<PerformanceIndexLibrary> addIndexLibraryList = new ArrayList<>();
        List<PerformanceIndexLibrary> updateIndexLibraryList = new ArrayList<>();
        for (PerformanceIndexLibraryForm indexLibraryForm : indexLibraryFormList) {
            PerformanceIndexLibrary indexLibrary;
            if (indexLibraryForm.getId() != null) {
                List<Long> ids = new ArrayList<>();
                ids.add(Long.valueOf(indexLibraryForm.getId()));
                //TODO 如果指标定义关联,不能编辑
//                if (indexService.existsByLibraryIds(ids)) {
//                    throw new FSSCException(FsscErrorType.REFERENCES_BY_OTHER_FOR_EDIT);
//                }
                indexLibrary = this.getById(indexLibraryForm.getId());
                if (indexLibrary != null) {
                    indexLibrary.setExplain(indexLibraryForm.getExplain().trim());
                } else {
                    throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
                }
                updateIndexLibraryList.add(indexLibrary);
            } else {
                PerformanceIndexLibrary sameCodeIndexLibrary = this
                        .getIndexLibraryByCode(indexLibraryForm.getCode());
                if (sameCodeIndexLibrary != null) {
                    throw new FSSCException(FsscErrorType.CODE_REPEAT);
                }
                indexLibrary = new BeanUtils<PerformanceIndexLibrary>()
                        .copyObj(indexLibraryForm, PerformanceIndexLibrary.class);
                indexLibrary.setName(indexLibrary.getName().trim());
                indexLibrary.setValidFlag(FsscEums.VALID.getValue());
                addIndexLibraryList.add(indexLibrary);
            }
        }
        if (CollectionUtils.isNotEmpty(updateIndexLibraryList)) {
            this.updateBatchById(updateIndexLibraryList);
        }
        if (CollectionUtils.isNotEmpty(addIndexLibraryList)) {
            this.saveBatch(addIndexLibraryList);
        }
    }

    @Override
    public PerformanceIndexLibrary getIndexLibraryByCode(String code) {
        QueryWrapper<PerformanceIndexLibrary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(PerformanceIndexLibrary.CODE, code);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<PerformanceIndexLibrary> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(PerformanceIndexLibrary.ID, idList);
        List<PerformanceIndexLibrary> indexLibraryList = this.list(queryWrapper);
        for (PerformanceIndexLibrary indexLibrary : indexLibraryList) {
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                if (validFlag.equals(indexLibrary.getValidFlag())) {
                    throw new FSSCException(FsscErrorType.CANNOT_REPEAT_VALID);
                }
            } else if (validFlag.equals(indexLibrary.getValidFlag())) {
                throw new FSSCException(FsscErrorType.CANNOT_INVALID);
            }
            indexLibrary.setValidFlag(validFlag);
        }
        return this.updateBatchById(indexLibraryList);
    }

    @Override
    public boolean existsBySubTypeIds(List<Long> idList) {
        return this.existsBySubTypeIds(idList, null);
    }

    @Override
    public boolean existsBySubTypeIds(List<Long> idList, String validFlag) {
        QueryWrapper<PerformanceIndexLibrary> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(PerformanceIndexLibrary.SUB_TYPE_ID, idList);
        if (StringUtils.isNotBlank(validFlag)) {
            queryWrapper.in(PerformanceIndexLibrary.VALID_FLAG, validFlag);
        }
        return this.count(queryWrapper) > 0;
    }

    @Override
    public IPage<PerformanceIndexLibrary> selectPage(PerformanceIndexLibraryQueryForm queryForm) {
        QueryWrapper<PerformanceIndexLibrary> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return indexLibraryMapper
                .selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                        queryWrapper);
    }

    @Override
    public List<PerformanceIndexLibrary> selectList(PerformanceIndexLibraryQueryForm queryForm) {
        QueryWrapper<PerformanceIndexLibrary> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        return indexLibraryMapper.selectList(queryWrapper);
    }

    /**
     * 通用查询
     */
    public QueryWrapper<PerformanceIndexLibrary> getQueryWrapper(
            QueryWrapper<PerformanceIndexLibrary> queryWrapper,
            PerformanceIndexLibraryQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getId())) {
            queryWrapper.eq(PerformanceIndexLibrary.ID, queryForm.getId());
        }
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            queryWrapper.like(PerformanceIndexLibrary.CODE, queryForm.getCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.like(PerformanceIndexLibrary.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getExplain())) {
            queryWrapper.like(PerformanceIndexLibrary.EXPLAIN, queryForm.getExplain());
        }
        if (StringUtils.isNotBlank(queryForm.getSubTypeId())) {
            queryWrapper.eq(PerformanceIndexLibrary.SUB_TYPE_ID, queryForm.getSubTypeId());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(PerformanceIndexLibrary.VALID_FLAG, queryForm.getValidFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(PerformanceIndexLibrary.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(PerformanceIndexLibrary.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgId())) {
            queryWrapper.eq(PerformanceIndexLibrary.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.like(PerformanceIndexLibrary.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }
}

