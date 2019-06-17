package com.deloitte.services.fssc.performance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.performance.entity.PerformanceIndex;
import com.deloitte.services.fssc.performance.mapper.PerformanceIndexMapper;
import com.deloitte.services.fssc.performance.service.IPerformanceIndexService;

import java.util.*;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-08
 * @Description :  PerformanceIndex服务实现类
 * @Modified :
 */
@Service
public class PerformanceIndexServiceImpl extends
        ServiceImpl<PerformanceIndexMapper, PerformanceIndex> implements IPerformanceIndexService {


    @Autowired
    private PerformanceIndexMapper indexMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateOrSaveBatch(List<PerformanceIndexForm> indexFormList) {
        List<PerformanceIndex> addIndexList = new ArrayList<>();
        List<PerformanceIndex> updateIndexList = new ArrayList<>();
        for (PerformanceIndexForm indexLibraryForm : indexFormList) {
            PerformanceIndex performanceIndex;
            if (indexLibraryForm.getId() != null) {
                List<Long> ids = new ArrayList<>();
                ids.add(Long.valueOf(indexLibraryForm.getId()));
                performanceIndex = this.getById(indexLibraryForm.getId());
                if (performanceIndex != null) {
                    performanceIndex.setIndexLibraryId(indexLibraryForm.getIndexLibraryId());
                    performanceIndex.setValueJudgeCondition(indexLibraryForm.getValueJudgeCondition());
                    performanceIndex.setValueUnitId(indexLibraryForm.getValueUnitId());
                    PerformanceIndex oldPerformanceIndex = this.getIndexByKeyWord(performanceIndex.getName(),performanceIndex.getIndexLibraryId(),
                            performanceIndex.getValueJudgeCondition(),performanceIndex.getValueUnitId(),performanceIndex.getValueLevel());
                    if (oldPerformanceIndex != null && !oldPerformanceIndex.getId().equals(performanceIndex.getId())){
                        throw new FSSCException(FsscErrorType.PERFORMANCE_DATA_REPEAT);
                    }
                } else {
                    throw new FSSCException(FsscErrorType.NOT_FIND_DATA);
                }
                updateIndexList.add(performanceIndex);
            } else {
                PerformanceIndex sameCodeIndex = this.getIndexByCode(indexLibraryForm.getCode());
                if (sameCodeIndex != null) {
                    throw new FSSCException(FsscErrorType.CODE_REPEAT);
                }
                performanceIndex = new BeanUtils<PerformanceIndex>()
                        .copyObj(indexLibraryForm, PerformanceIndex.class);
                PerformanceIndex oldPerformanceIndex = this.getIndexByKeyWord(performanceIndex.getName(),performanceIndex.getIndexLibraryId(),
                        performanceIndex.getValueJudgeCondition(),performanceIndex.getValueUnitId(),performanceIndex.getValueLevel());
                if (oldPerformanceIndex != null){
                    throw new FSSCException(FsscErrorType.PERFORMANCE_DATA_REPEAT);
                }
                performanceIndex.setName(performanceIndex.getName().trim());
                addIndexList.add(performanceIndex);
            }
        }
        if (CollectionUtils.isNotEmpty(updateIndexList)) {
            this.updateBatchById(updateIndexList);
        }
        if (CollectionUtils.isNotEmpty(addIndexList)) {
            this.saveBatch(addIndexList);
        }
    }

    @Override
    public PerformanceIndex getIndexByCode(String code) {
        QueryWrapper<PerformanceIndex> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(PerformanceIndex.CODE, code);
        return this.getOne(queryWrapper);
    }

    @Override
    public Map<String, PerformanceIndex> selectIndexByCodes(List<String> codeList) {
        QueryWrapper<PerformanceIndex> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(PerformanceIndex.CODE, codeList);
        List<PerformanceIndex> indexList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(indexList)){
            return Collections.emptyMap();
        }
        Map<String, PerformanceIndex> codeBeanMap = new HashMap<>();
        for (PerformanceIndex index : indexList){
            codeBeanMap.put(index.getCode(),index);
        }
        return codeBeanMap;
    }

    @Override
    public PerformanceIndex getIndexByKeyWord(String name, Long indexLibraryId, String valueJudgeCondition,
                                              Long valueUnitId, String level) {
        QueryWrapper<PerformanceIndex> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(PerformanceIndex.NAME, name);
        queryWrapper.eq(PerformanceIndex.INDEX_LIBRARY_ID, indexLibraryId);
        if (FsscEums.PERFORMANCE_INDEX_LEVEL_3.getValue().equals(level)){
            queryWrapper.eq(PerformanceIndex.VALUE_JUDGE_CONDITION, valueJudgeCondition);
            queryWrapper.eq(PerformanceIndex.VALUE_UNIT_ID, valueUnitId);
        }
        queryWrapper.eq(PerformanceIndex.VALUE_LEVEL, level);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean modifyValidFlag(List<Long> idList, String validFlag) {
        QueryWrapper<PerformanceIndex> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(PerformanceIndex.ID, idList);
        List<PerformanceIndex> indexList = this.list(queryWrapper);
        for (PerformanceIndex index : indexList) {
            if (FsscEums.VALID.getValue().equals(validFlag)) {
                if (validFlag.equals(index.getValidFlag())) {
                    throw new FSSCException(FsscErrorType.CANNOT_REPEAT_VALID);
                }
            } else if (validFlag.equals(index.getValidFlag())) {
                throw new FSSCException(FsscErrorType.CANNOT_INVALID);
            }
            index.setValidFlag(validFlag);
        }
        return this.updateBatchById(indexList);
    }

    @Override
    public IPage<PerformanceIndexVo> selectVoPage(PerformanceIndexQueryForm queryForm) {
        Page page = new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize());
        if (StringUtils.isNotBlank(queryForm.getSort())) {
            queryForm.setSort("A." + queryForm.getSort().toUpperCase());
        }
        List<PerformanceIndexVo> voList= indexMapper.selectVo(page,queryForm);
        page.setRecords(voList);
        return page;
    }

    @Override
    public List<PerformanceIndex> selectList(PerformanceIndexQueryForm queryForm) {
        QueryWrapper<PerformanceIndex> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        if (!FsscEums.PERFORMANCE_INDEX_LEVEL_1.getValue().equals(queryForm.getValueLevel())) {
            queryWrapper.in(PerformanceIndex.VALUE_LEVEL, FsscEums.PERFORMANCE_INDEX_LEVEL_2.getValue(),
                    FsscEums.PERFORMANCE_INDEX_LEVEL_3.getValue());
        }
        return indexMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existsByLibraryIds(List<Long> idList) {
        return this.existsByLibraryIds(idList,null);
    }

    @Override
    public boolean existsByLibraryIds(List<Long> idList, String validFlag) {
        QueryWrapper<PerformanceIndex> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(PerformanceIndex.INDEX_LIBRARY_ID, idList);
        if (StringUtils.isNotBlank(validFlag)) {
            queryWrapper.in(PerformanceIndex.VALID_FLAG, validFlag);
        }
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean existsByValueIds(List<Long> idList) {
        return this.existsByValueIds(idList,null);
    }

    @Override
    public boolean existsByValueIds(List<Long> idList, String validFlag) {
        QueryWrapper<PerformanceIndex> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(PerformanceIndex.VALUE_UNIT_ID, idList);
        if (StringUtils.isNotBlank(validFlag)) {
            queryWrapper.in(PerformanceIndex.VALID_FLAG, validFlag);
        }
        return this.count(queryWrapper) > 0;
    }

    @Override
    public boolean existsByChild(List<Long> idList) {
        return this.existsByChild(idList,null);
    }

    @Override
    public boolean existsByChild(List<Long> idList, String validFlag) {
        QueryWrapper<PerformanceIndex> queryWrapper = new QueryWrapper<>();
        QueryWrapper<PerformanceIndex> queryWrapper2 = new QueryWrapper<>();
        queryWrapper.in(PerformanceIndex.LEVEL_1_ID, idList);
        queryWrapper2.in(PerformanceIndex.LEVEL_2_ID, idList);
        if (StringUtils.isNotBlank(validFlag)) {
            queryWrapper.in(PerformanceIndex.VALID_FLAG, validFlag);
            queryWrapper2.in(PerformanceIndex.VALID_FLAG, validFlag);
        }
        return this.count(queryWrapper) > 0 || this.count(queryWrapper2) > 0;
    }

    /**
     * 通用查询
     */
    private QueryWrapper<PerformanceIndex> getQueryWrapper(
            QueryWrapper<PerformanceIndex> queryWrapper, PerformanceIndexQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getId())) {
            queryWrapper.eq(PerformanceIndex.ID, queryForm.getId());
        }
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            queryWrapper.eq(PerformanceIndex.CODE, queryForm.getCode());
        }
        if (StringUtils.isNotBlank(queryForm.getName())) {
            queryWrapper.eq(PerformanceIndex.NAME, queryForm.getName());
        }
        if (StringUtils.isNotBlank(queryForm.getValidFlag())) {
            queryWrapper.eq(PerformanceIndex.VALID_FLAG, queryForm.getValidFlag());
        }
        if (queryForm.getIndexLibraryId() != null) {
            queryWrapper.eq(PerformanceIndex.INDEX_LIBRARY_ID, queryForm.getIndexLibraryId());
        }
        if (StringUtils.isNotBlank(queryForm.getValueJudgeCondition())) {
            queryWrapper
                    .eq(PerformanceIndex.VALUE_JUDGE_CONDITION, queryForm.getValueJudgeCondition());
        }
        if (StringUtils.isNotBlank(queryForm.getValueUnitId())) {
            queryWrapper.eq(PerformanceIndex.VALUE_UNIT_ID, queryForm.getValueUnitId());
        }
        if (StringUtils.isNotBlank(queryForm.getValueLevel())) {
            queryWrapper.eq(PerformanceIndex.VALUE_LEVEL, queryForm.getValueLevel());
        }
        if (queryForm.getLevel1Id() != null) {
            queryWrapper.eq(PerformanceIndex.LEVEL_1_ID, queryForm.getLevel1Id());
        }
        if (queryForm.getLevel2Id() != null) {
            queryWrapper.eq(PerformanceIndex.LEVEL_2_ID, queryForm.getLevel2Id());
        }
        if (StringUtils.isNotBlank(queryForm.getCreateBy())) {
            queryWrapper.eq(PerformanceIndex.CREATE_BY, queryForm.getCreateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getUpdateBy())) {
            queryWrapper.eq(PerformanceIndex.UPDATE_BY, queryForm.getUpdateBy());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgId())) {
            queryWrapper.eq(PerformanceIndex.ORG_ID, queryForm.getOrgId());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.eq(PerformanceIndex.ORG_PATH, queryForm.getOrgPath());
        }
        return queryWrapper;
    }
}

