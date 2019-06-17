package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaOutcomeQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaOutcome;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaOutcomeMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaOutcomeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-02
 * @Description :  MprEvaOutcome服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaOutcomeServiceImpl extends ServiceImpl<MprEvaOutcomeMapper, MprEvaOutcome> implements IMprEvaOutcomeService {


    @Autowired
    private MprEvaOutcomeMapper mprEvaOutcomeMapper;

    @Override
    public IPage<MprEvaOutcome> selectPage(MprEvaOutcomeQueryForm queryForm ) {
        QueryWrapper<MprEvaOutcome> queryWrapper = new QueryWrapper <MprEvaOutcome>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaOutcomeMapper.selectPage(new Page<MprEvaOutcome>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaOutcome> selectList(MprEvaOutcomeQueryForm queryForm) {
        QueryWrapper<MprEvaOutcome> queryWrapper = new QueryWrapper <MprEvaOutcome>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaOutcomeMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaOutcome> getQueryWrapper(QueryWrapper<MprEvaOutcome> queryWrapper,MprEvaOutcomeQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaOutcome.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getRepOutcomeName())){
            queryWrapper.eq(MprEvaOutcome.REP_OUTCOME_NAME,queryForm.getRepOutcomeName());
        }
        if(StringUtils.isNotBlank(queryForm.getOutcomeType())){
            queryWrapper.eq(MprEvaOutcome.OUTCOME_TYPE,queryForm.getOutcomeType());
        }
        if(StringUtils.isNotBlank(queryForm.getOutcomeLevel())){
            queryWrapper.eq(MprEvaOutcome.OUTCOME_LEVEL,queryForm.getOutcomeLevel());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaOutcome.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaOutcome.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaOutcome.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaOutcome.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCorrespond())){
            queryWrapper.eq(MprEvaOutcome.CORRESPOND,queryForm.getCorrespond());
        }
        return queryWrapper;
    }
     */
}

