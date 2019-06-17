package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.project.param.ExePerformanceQueryForm;
import com.deloitte.services.project.entity.ExePerformance;
import com.deloitte.services.project.mapper.ExePerformanceMapper;
import com.deloitte.services.project.service.IExePerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  ExePerformance服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ExePerformanceServiceImpl extends ServiceImpl<ExePerformanceMapper, ExePerformance> implements IExePerformanceService {


    @Autowired
    private ExePerformanceMapper exePerformanceMapper;

    @Override
    public IPage<ExePerformance> selectPage(ExePerformanceQueryForm queryForm ) {
        QueryWrapper<ExePerformance> queryWrapper = new QueryWrapper <ExePerformance>();
        //getQueryWrapper(queryWrapper,queryForm);
        return exePerformanceMapper.selectPage(new Page<ExePerformance>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ExePerformance> selectList(ExePerformanceQueryForm queryForm) {
        QueryWrapper<ExePerformance> queryWrapper = new QueryWrapper <ExePerformance>();
        //getQueryWrapper(queryWrapper,queryForm);
        return exePerformanceMapper.selectList(queryWrapper);
    }

    @Override
    public List<ExePerformance> getAllList(String replyId) {
        QueryWrapper<ExePerformance> queryWrapper = new QueryWrapper <ExePerformance>();
        queryWrapper.eq(ExePerformance.REPLY_ID, replyId);
        return exePerformanceMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteAllByRep(String replyId) {
        QueryWrapper<ExePerformance> queryWrapper = new QueryWrapper <ExePerformance>();
        queryWrapper.eq(ExePerformance.REPLY_ID, replyId);
        exePerformanceMapper.delete(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ExePerformance> getQueryWrapper(QueryWrapper<ExePerformance> queryWrapper,ExePerformanceQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(ExePerformance.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(ExePerformance.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getExecutionId())){
            queryWrapper.eq(ExePerformance.EXECUTION_ID,queryForm.getExecutionId());
        }
        if(StringUtils.isNotBlank(queryForm.getIndicators1())){
            queryWrapper.eq(ExePerformance.INDICATORS1,queryForm.getIndicators1());
        }
        if(StringUtils.isNotBlank(queryForm.getIndicators2())){
            queryWrapper.eq(ExePerformance.INDICATORS2,queryForm.getIndicators2());
        }
        if(StringUtils.isNotBlank(queryForm.getIndicators3())){
            queryWrapper.eq(ExePerformance.INDICATORS3,queryForm.getIndicators3());
        }
        if(StringUtils.isNotBlank(queryForm.getIndicatorsYear())){
            queryWrapper.eq(ExePerformance.INDICATORS_YEAR,queryForm.getIndicatorsYear());
        }
        if(StringUtils.isNotBlank(queryForm.getExeCondition1and7())){
            queryWrapper.eq(ExePerformance.EXE_CONDITION_1AND7,queryForm.getExeCondition1and7());
        }
        if(StringUtils.isNotBlank(queryForm.getExeConditionYear())){
            queryWrapper.eq(ExePerformance.EXE_CONDITION_YEAR,queryForm.getExeConditionYear());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingSec())){
            queryWrapper.eq(ExePerformance.FUNDING_SEC,queryForm.getFundingSec());
        }
        if(StringUtils.isNotBlank(queryForm.getSystemSec())){
            queryWrapper.eq(ExePerformance.SYSTEM_SEC,queryForm.getSystemSec());
        }
        if(StringUtils.isNotBlank(queryForm.getPersonSec())){
            queryWrapper.eq(ExePerformance.PERSON_SEC,queryForm.getPersonSec());
        }
        if(StringUtils.isNotBlank(queryForm.getHardwareSec())){
            queryWrapper.eq(ExePerformance.HARDWARE_SEC,queryForm.getHardwareSec());
        }
        if(StringUtils.isNotBlank(queryForm.getOtherSec())){
            queryWrapper.eq(ExePerformance.OTHER_SEC,queryForm.getOtherSec());
        }
        if(StringUtils.isNotBlank(queryForm.getReasonInstruction())){
            queryWrapper.eq(ExePerformance.REASON_INSTRUCTION,queryForm.getReasonInstruction());
        }
        if(StringUtils.isNotBlank(queryForm.getTargetPlanCode())){
            queryWrapper.eq(ExePerformance.TARGET_PLAN_CODE,queryForm.getTargetPlanCode());
        }
        if(StringUtils.isNotBlank(queryForm.getTargetPlanName())){
            queryWrapper.eq(ExePerformance.TARGET_PLAN_NAME,queryForm.getTargetPlanName());
        }
        if(StringUtils.isNotBlank(queryForm.getNote())){
            queryWrapper.eq(ExePerformance.NOTE,queryForm.getNote());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ExePerformance.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ExePerformance.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ExePerformance.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ExePerformance.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ExePerformance.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ExePerformance.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ExePerformance.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getEt4())){
            queryWrapper.eq(ExePerformance.ET4,queryForm.getEt4());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(ExePerformance.ORG_ID,queryForm.getOrgId());
        }
        return queryWrapper;
    }
     */
}

