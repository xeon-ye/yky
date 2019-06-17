package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTechStanTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTechStanTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaTechStanTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaTechStanTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaTechStanTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaTechStanTableServiceImpl extends ServiceImpl<MprEvaTechStanTableMapper, MprEvaTechStanTable> implements IMprEvaTechStanTableService {


    @Autowired
    private MprEvaTechStanTableMapper mprEvaTechStanTableMapper;

    @Override
    public IPage<MprEvaTechStanTable> selectPage(MprEvaTechStanTableQueryForm queryForm ) {
        QueryWrapper<MprEvaTechStanTable> queryWrapper = new QueryWrapper <MprEvaTechStanTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTechStanTableMapper.selectPage(new Page<MprEvaTechStanTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaTechStanTable> selectList(MprEvaTechStanTableQueryForm queryForm) {
        QueryWrapper<MprEvaTechStanTable> queryWrapper = new QueryWrapper <MprEvaTechStanTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTechStanTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaTechStanTable> getQueryWrapper(QueryWrapper<MprEvaTechStanTable> queryWrapper,MprEvaTechStanTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaTechStanTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getObtTechStanName())){
            queryWrapper.eq(MprEvaTechStanTable.OBT_TECH_STAN_NAME,queryForm.getObtTechStanName());
        }
        if(StringUtils.isNotBlank(queryForm.getStanType())){
            queryWrapper.eq(MprEvaTechStanTable.STAN_TYPE,queryForm.getStanType());
        }
        if(StringUtils.isNotBlank(queryForm.getStanNum())){
            queryWrapper.eq(MprEvaTechStanTable.STAN_NUM,queryForm.getStanNum());
        }
        if(StringUtils.isNotBlank(queryForm.getCompletePerson())){
            queryWrapper.eq(MprEvaTechStanTable.COMPLETE_PERSON,queryForm.getCompletePerson());
        }
        if(StringUtils.isNotBlank(queryForm.getPostDate())){
            queryWrapper.eq(MprEvaTechStanTable.POST_DATE,queryForm.getPostDate());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaTechStanTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaTechStanTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaTechStanTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaTechStanTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

