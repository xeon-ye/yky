package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaAcadConfTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaAcadConfTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaAcadConfTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaAcadConfTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaAcadConfTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaAcadConfTableServiceImpl extends ServiceImpl<MprEvaAcadConfTableMapper, MprEvaAcadConfTable> implements IMprEvaAcadConfTableService {


    @Autowired
    private MprEvaAcadConfTableMapper mprEvaAcadConfTableMapper;

    @Override
    public IPage<MprEvaAcadConfTable> selectPage(MprEvaAcadConfTableQueryForm queryForm ) {
        QueryWrapper<MprEvaAcadConfTable> queryWrapper = new QueryWrapper <MprEvaAcadConfTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaAcadConfTableMapper.selectPage(new Page<MprEvaAcadConfTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaAcadConfTable> selectList(MprEvaAcadConfTableQueryForm queryForm) {
        QueryWrapper<MprEvaAcadConfTable> queryWrapper = new QueryWrapper <MprEvaAcadConfTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaAcadConfTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaAcadConfTable> getQueryWrapper(QueryWrapper<MprEvaAcadConfTable> queryWrapper,MprEvaAcadConfTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaAcadConfTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getConferenceName())){
            queryWrapper.eq(MprEvaAcadConfTable.CONFERENCE_NAME,queryForm.getConferenceName());
        }
        if(StringUtils.isNotBlank(queryForm.getConferenceType())){
            queryWrapper.eq(MprEvaAcadConfTable.CONFERENCE_TYPE,queryForm.getConferenceType());
        }
        if(StringUtils.isNotBlank(queryForm.getForeReprNum())){
            queryWrapper.eq(MprEvaAcadConfTable.FORE_REPR_NUM,queryForm.getForeReprNum());
        }
        if(StringUtils.isNotBlank(queryForm.getDomeReprNum())){
            queryWrapper.eq(MprEvaAcadConfTable.DOME_REPR_NUM,queryForm.getDomeReprNum());
        }
        if(StringUtils.isNotBlank(queryForm.getConferencePlace())){
            queryWrapper.eq(MprEvaAcadConfTable.CONFERENCE_PLACE,queryForm.getConferencePlace());
        }
        if(StringUtils.isNotBlank(queryForm.getHoldingTime())){
            queryWrapper.eq(MprEvaAcadConfTable.HOLDING_TIME,queryForm.getHoldingTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaAcadConfTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaAcadConfTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaAcadConfTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaAcadConfTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

