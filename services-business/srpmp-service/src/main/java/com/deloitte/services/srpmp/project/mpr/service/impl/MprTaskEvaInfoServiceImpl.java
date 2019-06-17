package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprTaskEvaInfoQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprTaskEvaInfo;
import com.deloitte.services.srpmp.project.mpr.mapper.MprTaskEvaInfoMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprTaskEvaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprTaskEvaInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprTaskEvaInfoServiceImpl extends ServiceImpl<MprTaskEvaInfoMapper, MprTaskEvaInfo> implements IMprTaskEvaInfoService {


    @Autowired
    private MprTaskEvaInfoMapper mprTaskEvaInfoMapper;


    @Override
    public IPage<MprTaskEvaInfo> selectPage(MprTaskEvaInfoQueryForm queryForm ) {
        QueryWrapper<MprTaskEvaInfo> queryWrapper = new QueryWrapper <MprTaskEvaInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprTaskEvaInfoMapper.selectPage(new Page<MprTaskEvaInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<MprTaskEvaInfo> selectList(MprTaskEvaInfoQueryForm queryForm) {
        QueryWrapper<MprTaskEvaInfo> queryWrapper = new QueryWrapper <MprTaskEvaInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprTaskEvaInfoMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprTaskEvaInfo> getQueryWrapper(QueryWrapper<MprTaskEvaInfo> queryWrapper,MprTaskEvaInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprTaskEvaInfo.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectType())){
            queryWrapper.eq(MprTaskEvaInfo.PROJECT_TYPE,queryForm.getProjectType());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCategory())){
            queryWrapper.eq(MprTaskEvaInfo.PROJECT_CATEGORY,queryForm.getProjectCategory());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(MprTaskEvaInfo.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectCycle())){
            queryWrapper.eq(MprTaskEvaInfo.PROJECT_CYCLE,queryForm.getProjectCycle());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskProgress())){
            queryWrapper.eq(MprTaskEvaInfo.TASK_PROGRESS,queryForm.getTaskProgress());
        }
        if(StringUtils.isNotBlank(queryForm.getAdjustState())){
            queryWrapper.eq(MprTaskEvaInfo.ADJUST_STATE,queryForm.getAdjustState());
        }
        if(StringUtils.isNotBlank(queryForm.getLandmarkAchieve())){
            queryWrapper.eq(MprTaskEvaInfo.LANDMARK_ACHIEVE,queryForm.getLandmarkAchieve());
        }
        if(StringUtils.isNotBlank(queryForm.getEconSocialBenefits())){
            queryWrapper.eq(MprTaskEvaInfo.ECON_SOCIAL_BENEFITS,queryForm.getEconSocialBenefits());
        }
        if(StringUtils.isNotBlank(queryForm.getCapaImprove())){
            queryWrapper.eq(MprTaskEvaInfo.CAPA_IMPROVE,queryForm.getCapaImprove());
        }
        if(StringUtils.isNotBlank(queryForm.getDeveProsAna())){
            queryWrapper.eq(MprTaskEvaInfo.DEVE_PROS_ANA,queryForm.getDeveProsAna());
        }
        if(StringUtils.isNotBlank(queryForm.getPersonFundUse())){
            queryWrapper.eq(MprTaskEvaInfo.PERSON_FUND_USE,queryForm.getPersonFundUse());
        }
        if(StringUtils.isNotBlank(queryForm.getFundAdjust())){
            queryWrapper.eq(MprTaskEvaInfo.FUND_ADJUST,queryForm.getFundAdjust());
        }
        if(StringUtils.isNotBlank(queryForm.getProblemSuggest())){
            queryWrapper.eq(MprTaskEvaInfo.PROBLEM_SUGGEST,queryForm.getProblemSuggest());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprTaskEvaInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprTaskEvaInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprTaskEvaInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprTaskEvaInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

