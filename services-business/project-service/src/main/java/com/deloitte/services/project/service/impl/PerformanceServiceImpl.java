package com.deloitte.services.project.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.performance.feign.*;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexLibraryQueryForm;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexValueQueryForm;
import com.deloitte.platform.api.fssc.performance.param.PerformancePrjMainTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.param.PerformancePrjSubTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.*;
import com.deloitte.platform.api.project.param.PerformanceQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.project.entity.Performance;
import com.deloitte.services.project.mapper.PerformanceMapper;
import com.deloitte.services.project.service.IPerformanceService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Performance服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PerformanceServiceImpl extends ServiceImpl<PerformanceMapper, Performance> implements IPerformanceService {


    @Autowired
    private PerformanceMapper performanceMapper;

    /**
     * 项目大类
     */
    @Autowired
    private PerformancePrjMainTypeFeignService performancePrjMainTypeFeignService;
    /**
     * 项目小类
     */
    @Autowired
    private PerformancePrjSubTypeFeignService performancePrjSubTypeFeignService;
    /**
     * 指标库
     */
    @Autowired
    private PerformanceIndexLibraryFeignService performanceIndexLibraryFeignService;
    /**
     * 指标
     */
    @Autowired
    private PerformanceIndexFeignService performanceIndexFeignService;
    /**
     * 三级指标 值
     */
    @Autowired
    private PerformanceIndexValueFeignService valueFeignService;

    @Override
    public IPage<Performance> selectPage(PerformanceQueryForm queryForm ) {
        QueryWrapper<Performance> queryWrapper = new QueryWrapper <Performance>();
        //getQueryWrapper(queryWrapper,queryForm);
        return performanceMapper.selectPage(new Page<Performance>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Performance> selectList(PerformanceQueryForm queryForm) {
        QueryWrapper<Performance> queryWrapper = new QueryWrapper <Performance>();
        //getQueryWrapper(queryWrapper,queryForm);
        return performanceMapper.selectList(queryWrapper);
    }

    @Override
    public List<Performance> getPerformanceList(String applicationId) {
        QueryWrapper<Performance> queryWrapper = new QueryWrapper <Performance>();
        queryWrapper.eq(Performance.APPLICATION_ID,applicationId);
        return performanceMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteList(String applicationId) {
        QueryWrapper<Performance> queryWrapper = new QueryWrapper <Performance>();
        queryWrapper.eq(Performance.APPLICATION_ID,applicationId);
        performanceMapper.delete(queryWrapper);
    }

    @Override
    public void deleteRepList(String replyId) {
        QueryWrapper<Performance> queryWrapper = new QueryWrapper <Performance>();
        queryWrapper.eq(Performance.REPLAY_ID,replyId);
        performanceMapper.delete(queryWrapper);
    }

    @Override
    public List<Performance> getRepPerformanceList(String replyId) {
        QueryWrapper<Performance> queryWrapper = new QueryWrapper <Performance>();
        queryWrapper.eq(Performance.REPLAY_ID,replyId);
        return performanceMapper.selectList(queryWrapper);
    }

    @Override
    public List<Performance> getIndexTypeListWithDistinct(String applicationId) {
        QueryWrapper<Performance> performanceQueryWrapper = new QueryWrapper<>();
        performanceQueryWrapper.eq(Performance.APPLICATION_ID, applicationId);
        List<Performance> performanceList = performanceMapper.getIndexTypeListWithDistinct(applicationId);
        return performanceList;
    }

    @Override
    public List<Performance> getIndexTypeListWithDistinctByRep(String replyId) {
        return performanceMapper.getIndexTypeListWithDistinctByRep(replyId);
    }

    @Override
    public JSONArray getPerformanceFromFsscLibrary() {
        // 项目大类查询
        PerformancePrjMainTypeQueryForm prjMainTypeQueryForm = new PerformancePrjMainTypeQueryForm();
        Result<List<PerformancePrjMainTypeVo>> prjMainTypeResult =
                performancePrjMainTypeFeignService.list(prjMainTypeQueryForm);
        if (prjMainTypeResult.isSuccess()) {
            if (Objects.nonNull(prjMainTypeResult.getData())) {
                List<PerformancePrjMainTypeVo> performancePrjMainTypeVoList =
                        prjMainTypeResult.getData();
                for (PerformancePrjMainTypeVo prjMainTypeVo : performancePrjMainTypeVoList) {
                    if ("Y".equals(prjMainTypeVo.getValidFlag())) {
                        //String mainCode = prjMainTypeVo.getCode(); //项目大类Code
                        // 项目小类查询
                        PerformancePrjSubTypeQueryForm prjSubTypeQueryForm =
                                new PerformancePrjSubTypeQueryForm();
                        prjSubTypeQueryForm.setMainTypeId(prjMainTypeVo.getId());
                        Result<List<PerformancePrjSubTypeVo>> priSubTypeResult =
                                performancePrjSubTypeFeignService.list(prjSubTypeQueryForm);
                        if (priSubTypeResult.isSuccess()) {
                            if (Objects.nonNull(priSubTypeResult.getData())) {
                                List<PerformancePrjSubTypeVo> subTypeVoList = priSubTypeResult.getData();
                                for (PerformancePrjSubTypeVo subTypeVo : subTypeVoList) {
                                    if ("Y".equals(subTypeVo.getValidFlag())) {
                                        //String subCode = subTypeVo.getCode(); // 项目小类Code
                                        // 指标库查询
                                        PerformanceIndexLibraryQueryForm indexlibraryQueryForm = new PerformanceIndexLibraryQueryForm();
                                        indexlibraryQueryForm.setSubTypeId(subTypeVo.getId());
                                        Result<List<PerformanceIndexLibraryVo>> indexLibraryResult
                                                = performanceIndexLibraryFeignService.list(indexlibraryQueryForm);
                                        if (indexLibraryResult.isSuccess()) {
                                            if (Objects.nonNull(indexLibraryResult.getData())) {
                                                JSONArray rootArray = new JSONArray();
                                                // 【指标库】
                                                List<PerformanceIndexLibraryVo> performanceIndexLibraryVoList
                                                        = indexLibraryResult.getData();
                                                for (PerformanceIndexLibraryVo performanceIndexLibraryVo : performanceIndexLibraryVoList) {
                                                    if ("Y".equals(performanceIndexLibraryVo.getValidFlag())) {
                                                        JSONArray node1Array = new JSONArray();
                                                        JSONObject rootJson = new JSONObject();
                                                        // 指标值查询，根据指标库ID查询
                                                        Long libraryId = NumberUtils.toLong(performanceIndexLibraryVo.getId());
                                                        Result<List<PerformanceIndexVo>> performanceIndexResult
                                                                = performanceIndexFeignService.search(libraryId);
                                                        if (performanceIndexResult.isSuccess()) {
                                                            // 【一级指标】
                                                            List<PerformanceIndexVo> index1StVoList = performanceIndexResult.getData();
                                                            for (PerformanceIndexVo index1StVo : index1StVoList) {
                                                                if ("Y".equals(index1StVo.getValidFlag())) {
                                                                    if (index1StVo.getIndexLibraryId()
                                                                            .equals(performanceIndexLibraryVo.getId())) {
                                                                        JSONArray node2Array = new JSONArray();
                                                                        JSONObject index1StJson = new JSONObject();
                                                                        // 【二级指标】
                                                                        List<PerformanceIndexVo> index2StVoList
                                                                                = index1StVo.getChildIndexVoList();
                                                                        for (PerformanceIndexVo index2StVo : index2StVoList) {
                                                                            if (index2StVo.getLevel1Id().equals(index1StVo.getId())) {
                                                                                JSONArray node3Array = new JSONArray();
                                                                                JSONObject index2StJson = new JSONObject();
                                                                                // 【三级指标】
                                                                                List<PerformanceIndexVo> index3StVoList
                                                                                        = index2StVo.getChildIndexVoList();
                                                                                for (PerformanceIndexVo index3StVo
                                                                                        : index3StVoList) {
                                                                                    if (index3StVo.getLevel2Id()
                                                                                            .equals(index2StVo.getId())) {
                                                                                        JSONObject index3StJson = new JSONObject();
                                                                                        JSONObject valueJosn = null;
                                                                                        // 指标值
                                                                                        PerformanceIndexValueQueryForm queryForm =
                                                                                                new PerformanceIndexValueQueryForm();
                                                                                        queryForm.setId(index3StVo.getValueUnitId());
                                                                                        Result<List<PerformanceIndexValueVo>> valueVoResult
                                                                                                = valueFeignService.list(queryForm);
                                                                                        if (valueVoResult.isSuccess()) {
                                                                                            List<PerformanceIndexValueVo> valueVoList
                                                                                                    = valueVoResult.getData();
                                                                                            for (PerformanceIndexValueVo
                                                                                                    valueVo : valueVoList) {
                                                                                                if ("Y".equals(valueVo.getValidFlag())) {
                                                                                                    if (valueVo.getId().equals(index3StVo.getValueUnitId())) {
                                                                                                        String condition = null;
                                                                                                        if ("MoreThan".equals(index3StVo.getValueJudgeCondition())) {
                                                                                                            condition = ">=";
                                                                                                        } else if ("LessThan".equals(index3StVo.getValueJudgeCondition())) {
                                                                                                            condition = "<=";
                                                                                                        } else {
                                                                                                            condition = "";
                                                                                                        }
                                                                                                        valueJosn = new JSONObject();
                                                                                                        valueJosn.put("code", valueVo.getCode());
                                                                                                        if ("".equals(condition)) {
                                                                                                            valueJosn.put("name",  valueVo.getName());
                                                                                                        } else {
                                                                                                            valueJosn.put("name",  condition+ "_" + valueVo.getName());
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        index3StJson.put("per", valueJosn);
                                                                                        index3StJson.put("code", index3StVo.getCode());
                                                                                        index3StJson.put("name", index3StVo.getName());
                                                                                        node3Array.add(index3StJson);
                                                                                    }
                                                                                }
                                                                                index2StJson.put("children", node3Array);
                                                                                index2StJson.put("code", index2StVo.getCode());
                                                                                index2StJson.put("value", index2StVo.getName());
                                                                                node2Array.add(index2StJson);
                                                                            }
                                                                        }
                                                                        index1StJson.put("children", node2Array);
                                                                        index1StJson.put("code", index1StVo.getCode());
                                                                        index1StJson.put("value", index1StVo.getName());
                                                                        node1Array.add(index1StJson);
                                                                    }
                                                                }
                                                            }
                                                            rootJson.put("children", node1Array);
                                                            rootJson.put("code", performanceIndexLibraryVo.getCode());
                                                            rootJson.put("value", performanceIndexLibraryVo.getName());
                                                            rootArray.add(rootJson);
                                                        }
                                                    }
                                                }
                                                return rootArray;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Performance> getQueryWrapper(QueryWrapper<Performance> queryWrapper,PerformanceQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getPerformanceId())){
            queryWrapper.eq(Performance.PERFORMANCE_ID,queryForm.getPerformanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Performance.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectDate())){
            queryWrapper.eq(Performance.PROJECT_DATE,queryForm.getProjectDate());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualPerformanceTarget())){
            queryWrapper.eq(Performance.ANNUAL_PERFORMANCE_TARGET,queryForm.getAnnualPerformanceTarget());
        }
        if(StringUtils.isNotBlank(queryForm.getIndexType())){
            queryWrapper.eq(Performance.INDEX_TYPE,queryForm.getIndexType());
        }
        if(StringUtils.isNotBlank(queryForm.getIndex1st())){
            queryWrapper.eq(Performance.INDEX_1ST,queryForm.getIndex1st());
        }
        if(StringUtils.isNotBlank(queryForm.getIndex2nd())){
            queryWrapper.eq(Performance.INDEX_2ND,queryForm.getIndex2nd());
        }
        if(StringUtils.isNotBlank(queryForm.getIndex3st())){
            queryWrapper.eq(Performance.INDEX_3ST,queryForm.getIndex3st());
        }
        if(StringUtils.isNotBlank(queryForm.getIndex3stCode())){
            queryWrapper.eq(Performance.INDEX_3ST_CODE,queryForm.getIndex3stCode());
        }
        if(StringUtils.isNotBlank(queryForm.getIndexPer())){
            queryWrapper.eq(Performance.INDEX_PER,queryForm.getIndexPer());
        }
        if(StringUtils.isNotBlank(queryForm.getPerformanceTargetId())){
            queryWrapper.eq(Performance.PERFORMANCE_TARGET_ID,queryForm.getPerformanceTargetId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayId())){
            queryWrapper.eq(Performance.REPLAY_ID,queryForm.getReplayId());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Performance.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCareteBy())){
            queryWrapper.eq(Performance.CARETE_BY,queryForm.getCareteBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Performance.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Performance.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Performance.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Performance.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Performance.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Performance.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Performance.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Performance.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Performance.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

