package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.RoleResourceQueryForm;
import com.deloitte.platform.api.isump.param.RoleResourceQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.isump.entity.RoleResource;
import com.deloitte.services.isump.mapper.RoleResourceMapper;
import com.deloitte.services.isump.service.IRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  RoleResource服务实现类
 * @Modified :
 */
@Service
@Transactional
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {


    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public IPage<RoleResource> selectPage(RoleResourceQueryForm queryForm ) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper <RoleResource>();
        getQueryWrapper(queryWrapper,queryForm);
        return roleResourceMapper.selectPage(new Page<RoleResource>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<RoleResource> selectList(RoleResourceQueryForm queryForm) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper <RoleResource>();
        getQueryWrapper(queryWrapper,queryForm);
        return roleResourceMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<RoleResource> getQueryWrapper(QueryWrapper<RoleResource> queryWrapper, BaseQueryForm<RoleResourceQueryParam> queryForm){
        RoleResourceQueryParam roleResource = queryForm.toParam(RoleResourceQueryParam.class);
        //条件拼接
        if(roleResource.getRoleId() != null){
            queryWrapper.eq(RoleResource.ROLE_ID,roleResource.getRoleId());
        }
        if(roleResource.getResourceId() != null){
            queryWrapper.eq(RoleResource.RESOURCE_ID,roleResource.getResourceId());
        }
        return queryWrapper;
    }
}

