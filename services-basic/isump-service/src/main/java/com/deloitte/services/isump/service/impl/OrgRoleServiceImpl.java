package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.OrgRoleQueryForm;
import com.deloitte.platform.api.isump.param.OrgRoleQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.isump.entity.OrgRole;
import com.deloitte.services.isump.mapper.OrgRoleMapper;
import com.deloitte.services.isump.service.IOrgRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  OrgRole服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OrgRoleServiceImpl extends ServiceImpl<OrgRoleMapper, OrgRole> implements IOrgRoleService {


    @Autowired
    private OrgRoleMapper orgRoleMapper;

    @Override
    public IPage<OrgRole> selectPage(OrgRoleQueryForm queryForm ) {
        QueryWrapper<OrgRole> queryWrapper = new QueryWrapper <OrgRole>();
        getQueryWrapper(queryWrapper,queryForm);
        return orgRoleMapper.selectPage(new Page<OrgRole>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OrgRole> selectList(OrgRoleQueryForm queryForm) {
        QueryWrapper<OrgRole> queryWrapper = new QueryWrapper <OrgRole>();
        getQueryWrapper(queryWrapper,queryForm);
        return orgRoleMapper.selectList(queryWrapper);
    }

    @Override
    public List<String> selectRolesByOrgId(Long orgId) {
        return orgRoleMapper.selectRolesByOrgId(orgId);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<OrgRole> getQueryWrapper(QueryWrapper<OrgRole> queryWrapper, BaseQueryForm<OrgRoleQueryParam> queryForm){
        //条件拼接
        OrgRoleQueryParam orgRole = queryForm.toParam(OrgRoleQueryParam.class);
        if(orgRole.getOrgId() != null){
            queryWrapper.eq(OrgRole.ORG_ID,orgRole.getOrgId());
        }
        if(orgRole.getRoleId() != null){
            queryWrapper.eq(OrgRole.ROLE_ID,orgRole.getRoleId());
        }
        return queryWrapper;
    }
}

