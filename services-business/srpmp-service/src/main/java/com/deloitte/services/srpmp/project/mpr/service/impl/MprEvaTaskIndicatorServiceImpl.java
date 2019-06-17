package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTaskIndicatorQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTaskIndicator;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaTaskIndicatorMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaTaskIndicatorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-02
 * @Description :  MprEvaTaskIndicator服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaTaskIndicatorServiceImpl extends ServiceImpl<MprEvaTaskIndicatorMapper, MprEvaTaskIndicator> implements IMprEvaTaskIndicatorService {


    @Autowired
    private MprEvaTaskIndicatorMapper mprEvaTaskIndicatorMapper;

    @Override
    public IPage<MprEvaTaskIndicator> selectPage(MprEvaTaskIndicatorQueryForm queryForm ) {
        QueryWrapper<MprEvaTaskIndicator> queryWrapper = new QueryWrapper <MprEvaTaskIndicator>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTaskIndicatorMapper.selectPage(new Page<MprEvaTaskIndicator>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaTaskIndicator> selectList(MprEvaTaskIndicatorQueryForm queryForm) {
        QueryWrapper<MprEvaTaskIndicator> queryWrapper = new QueryWrapper <MprEvaTaskIndicator>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTaskIndicatorMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaTaskIndicator> getQueryWrapper(QueryWrapper<MprEvaTaskIndicator> queryWrapper,MprEvaTaskIndicatorQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaTaskIndicator.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskName())){
            queryWrapper.eq(MprEvaTaskIndicator.TASK_NAME,queryForm.getTaskName());
        }
        if(StringUtils.isNotBlank(queryForm.getIndicatorName())){
            queryWrapper.eq(MprEvaTaskIndicator.INDICATOR_NAME,queryForm.getIndicatorName());
        }
        if(StringUtils.isNotBlank(queryForm.getIndicatorCompleteStatus())){
            queryWrapper.eq(MprEvaTaskIndicator.INDICATOR_COMPLETE_STATUS,queryForm.getIndicatorCompleteStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaTaskIndicator.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaTaskIndicator.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaTaskIndicator.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaTaskIndicator.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

