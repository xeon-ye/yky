package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.PerformanceBakQueryForm;
import com.deloitte.services.project.entity.PerformanceBak;
import com.deloitte.services.project.mapper.PerformanceBakMapper;
import com.deloitte.services.project.service.IPerformanceBakService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  PerformanceBak服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PerformanceBakServiceImpl extends ServiceImpl<PerformanceBakMapper, PerformanceBak> implements IPerformanceBakService {


    @Autowired
    private PerformanceBakMapper performanceBakMapper;

    @Override
    public IPage<PerformanceBak> selectPage(PerformanceBakQueryForm queryForm ) {
        QueryWrapper<PerformanceBak> queryWrapper = new QueryWrapper <PerformanceBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return performanceBakMapper.selectPage(new Page<PerformanceBak>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<PerformanceBak> selectList(PerformanceBakQueryForm queryForm) {
        QueryWrapper<PerformanceBak> queryWrapper = new QueryWrapper <PerformanceBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return performanceBakMapper.selectList(queryWrapper);
    }

    @Override
    public List<PerformanceBak> getAllList(String applicationId) {
        QueryWrapper<PerformanceBak> queryWrapper = new QueryWrapper <PerformanceBak>();
        queryWrapper.eq(PerformanceBak.APPLICATION_ID,applicationId);
        return performanceBakMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteAllList(String applicationId) {
        QueryWrapper<PerformanceBak> queryWrapper = new QueryWrapper <PerformanceBak>();
        queryWrapper.eq(PerformanceBak.APPLICATION_ID,applicationId);
        performanceBakMapper.delete(queryWrapper);
    }

    @Override
    public List<PerformanceBak> getAllByRepId(String replyId) {
        QueryWrapper<PerformanceBak> queryWrapper = new QueryWrapper <PerformanceBak>();
        queryWrapper.eq(PerformanceBak.REPLAY_ID,replyId);
        return performanceBakMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteAllByRepId(String replyId) {
        QueryWrapper<PerformanceBak> queryWrapper = new QueryWrapper <PerformanceBak>();
        queryWrapper.eq(PerformanceBak.REPLAY_ID,replyId);
        performanceBakMapper.delete(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<PerformanceBak> getQueryWrapper(QueryWrapper<PerformanceBak> queryWrapper,PerformanceBakQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getPerformanceId())){
            queryWrapper.eq(PerformanceBak.PERFORMANCE_ID,queryForm.getPerformanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(PerformanceBak.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectDate())){
            queryWrapper.eq(PerformanceBak.PROJECT_DATE,queryForm.getProjectDate());
        }
        if(StringUtils.isNotBlank(queryForm.getAnnualPerformanceTarget())){
            queryWrapper.eq(PerformanceBak.ANNUAL_PERFORMANCE_TARGET,queryForm.getAnnualPerformanceTarget());
        }
        if(StringUtils.isNotBlank(queryForm.getIndexType())){
            queryWrapper.eq(PerformanceBak.INDEX_TYPE,queryForm.getIndexType());
        }
        if(StringUtils.isNotBlank(queryForm.getIndex1st())){
            queryWrapper.eq(PerformanceBak.INDEX_1ST,queryForm.getIndex1st());
        }
        if(StringUtils.isNotBlank(queryForm.getIndex2nd())){
            queryWrapper.eq(PerformanceBak.INDEX_2ND,queryForm.getIndex2nd());
        }
        if(StringUtils.isNotBlank(queryForm.getIndex3st())){
            queryWrapper.eq(PerformanceBak.INDEX_3ST,queryForm.getIndex3st());
        }
        if(StringUtils.isNotBlank(queryForm.getIndexPer())){
            queryWrapper.eq(PerformanceBak.INDEX_PER,queryForm.getIndexPer());
        }
        if(StringUtils.isNotBlank(queryForm.getPerformanceTargetId())){
            queryWrapper.eq(PerformanceBak.PERFORMANCE_TARGET_ID,queryForm.getPerformanceTargetId());
        }
        if(StringUtils.isNotBlank(queryForm.getReplayId())){
            queryWrapper.eq(PerformanceBak.REPLAY_ID,queryForm.getReplayId());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(PerformanceBak.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCareteBy())){
            queryWrapper.eq(PerformanceBak.CARETE_BY,queryForm.getCareteBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(PerformanceBak.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(PerformanceBak.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(PerformanceBak.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(PerformanceBak.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(PerformanceBak.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(PerformanceBak.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(PerformanceBak.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(PerformanceBak.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(PerformanceBak.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

