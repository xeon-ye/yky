package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ExecutionQueryForm;
import com.deloitte.services.project.entity.Execution;
import com.deloitte.services.project.mapper.ExecutionMapper;
import com.deloitte.services.project.service.IExecutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :  Execution服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ExecutionServiceImpl extends ServiceImpl<ExecutionMapper, Execution> implements IExecutionService {


    @Autowired
    private ExecutionMapper executionMapper;

    @Override
    public IPage<Execution> selectPage(ExecutionQueryForm queryForm ) {
        QueryWrapper<Execution> queryWrapper = new QueryWrapper <Execution>();
        //getQueryWrapper(queryWrapper,queryForm);
        return executionMapper.selectPage(new Page<Execution>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Execution> selectList(ExecutionQueryForm queryForm) {
        QueryWrapper<Execution> queryWrapper = new QueryWrapper <Execution>();
        //getQueryWrapper(queryWrapper,queryForm);
        return executionMapper.selectList(queryWrapper);
    }

    @Override
    public List<Execution> getExeList(String projectId) {
        QueryWrapper<Execution> queryWrapper = new QueryWrapper <Execution>();
        queryWrapper.eq(Execution.PROJECT_ID, projectId);
        return executionMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Execution> getQueryWrapper(QueryWrapper<Execution> queryWrapper,ExecutionQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getExeId())){
            queryWrapper.eq(Execution.EXE_ID,queryForm.getExeId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(Execution.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Execution.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getChangeAdv())){
            queryWrapper.eq(Execution.CHANGE_ADV,queryForm.getChangeAdv());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Execution.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Execution.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Execution.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Execution.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Execution.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Execution.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Execution.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Execution.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Execution.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Execution.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

