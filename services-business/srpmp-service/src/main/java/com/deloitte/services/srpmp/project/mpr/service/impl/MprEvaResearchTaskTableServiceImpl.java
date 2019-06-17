package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaResearchTaskTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaResearchTaskTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaResearchTaskTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaResearchTaskTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaResearchTaskTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaResearchTaskTableServiceImpl extends ServiceImpl<MprEvaResearchTaskTableMapper, MprEvaResearchTaskTable> implements IMprEvaResearchTaskTableService {


    @Autowired
    private MprEvaResearchTaskTableMapper mprEvaResearchTaskTableMapper;

    @Override
    public IPage<MprEvaResearchTaskTable> selectPage(MprEvaResearchTaskTableQueryForm queryForm ) {
        QueryWrapper<MprEvaResearchTaskTable> queryWrapper = new QueryWrapper <MprEvaResearchTaskTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaResearchTaskTableMapper.selectPage(new Page<MprEvaResearchTaskTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaResearchTaskTable> selectList(MprEvaResearchTaskTableQueryForm queryForm) {
        QueryWrapper<MprEvaResearchTaskTable> queryWrapper = new QueryWrapper <MprEvaResearchTaskTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaResearchTaskTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaResearchTaskTable> getQueryWrapper(QueryWrapper<MprEvaResearchTaskTable> queryWrapper,MprEvaResearchTaskTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaResearchTaskTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.eq(MprEvaResearchTaskTable.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectNoResearch())){
            queryWrapper.eq(MprEvaResearchTaskTable.PROJECT_NO_RESEARCH,queryForm.getProjectNoResearch());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectManager())){
            queryWrapper.eq(MprEvaResearchTaskTable.PROJECT_MANAGER,queryForm.getProjectManager());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectSource())){
            queryWrapper.eq(MprEvaResearchTaskTable.PROJECT_SOURCE,queryForm.getProjectSource());
        }
        if(StringUtils.isNotBlank(queryForm.getActualFund())){
            queryWrapper.eq(MprEvaResearchTaskTable.ACTUAL_FUND,queryForm.getActualFund());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaResearchTaskTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaResearchTaskTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaResearchTaskTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaResearchTaskTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

