package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaCoopResultTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaCoopResultTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaCoopResultTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaCoopResultTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaCoopResultTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaCoopResultTableServiceImpl extends ServiceImpl<MprEvaCoopResultTableMapper, MprEvaCoopResultTable> implements IMprEvaCoopResultTableService {


    @Autowired
    private MprEvaCoopResultTableMapper mprEvaCoopResultTableMapper;

    @Override
    public IPage<MprEvaCoopResultTable> selectPage(MprEvaCoopResultTableQueryForm queryForm ) {
        QueryWrapper<MprEvaCoopResultTable> queryWrapper = new QueryWrapper <MprEvaCoopResultTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaCoopResultTableMapper.selectPage(new Page<MprEvaCoopResultTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaCoopResultTable> selectList(MprEvaCoopResultTableQueryForm queryForm) {
        QueryWrapper<MprEvaCoopResultTable> queryWrapper = new QueryWrapper <MprEvaCoopResultTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaCoopResultTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaCoopResultTable> getQueryWrapper(QueryWrapper<MprEvaCoopResultTable> queryWrapper,MprEvaCoopResultTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaCoopResultTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getResultName())){
            queryWrapper.eq(MprEvaCoopResultTable.RESULT_NAME,queryForm.getResultName());
        }
        if(StringUtils.isNotBlank(queryForm.getResultType())){
            queryWrapper.eq(MprEvaCoopResultTable.RESULT_TYPE,queryForm.getResultType());
        }
        if(StringUtils.isNotBlank(queryForm.getCoopUnit())){
            queryWrapper.eq(MprEvaCoopResultTable.COOP_UNIT,queryForm.getCoopUnit());
        }
        if(StringUtils.isNotBlank(queryForm.getCoopPerson())){
            queryWrapper.eq(MprEvaCoopResultTable.COOP_PERSON,queryForm.getCoopPerson());
        }
        if(StringUtils.isNotBlank(queryForm.getCompletePersonTask())){
            queryWrapper.eq(MprEvaCoopResultTable.COMPLETE_PERSON_TASK,queryForm.getCompletePersonTask());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaCoopResultTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaCoopResultTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaCoopResultTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaCoopResultTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

