package com.deloitte.services.isump.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.RoleQueryForm;
import com.deloitte.platform.api.isump.param.RoleQueryParam;
import com.deloitte.platform.api.isump.vo.RoleVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.isump.entity.DeputyAccount;
import com.deloitte.services.isump.entity.DeputyAccountRole;
import com.deloitte.services.isump.entity.OrgRole;
import com.deloitte.services.isump.entity.Role;
import com.deloitte.services.isump.mapper.DeputyAccountMapper;
import com.deloitte.services.isump.mapper.RoleMapper;
import com.deloitte.services.isump.service.IDeputyAccountRoleService;
import com.deloitte.services.isump.service.IDeputyAccountService;
import com.deloitte.services.isump.service.IOrgRoleService;
import com.deloitte.services.isump.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  Role服务实现类
 * @Modified :
 */
@Service
@Transactional
@Log4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {


    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private IDeputyAccountService deputyAccountService;
    @Autowired
    private IDeputyAccountRoleService deputyAccountRoleService;
    @Autowired
    private IOrgRoleService orgRoleService;

    @Override
    public IPage<Role> selectPage(RoleQueryForm queryForm ) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper <Role>();
        getQueryWrapper(queryWrapper,queryForm);
        return roleMapper.selectPage(new Page<Role>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<RoleVo> getByDeputyAccountId(long id,String type,String service){
        log.info("根据副账号ID查询角色列表，id:"+id+",type:"+type + ",service:"+service);
        DeputyAccount deputyAccount = deputyAccountService.getById(id);
        //查询副账号关联角色
        List<String> roleIds = deputyAccountRoleService.selectRolesByDeputyAccountId(id);
        //查询副账号的组织关联角色
        log.info("副账号JSON:"+ JSON.toJSONString(deputyAccount));
        if(deputyAccount!= null && deputyAccount.getOrgId() != null) {
            List<String> roleIds2 = orgRoleService.selectRolesByOrgId(deputyAccount.getOrgId());
            roleIds.addAll(roleIds2);
        }
        //查询角色列表
        QueryWrapper<Role> queryWrapper = new QueryWrapper <Role>();
        if(StringUtils.isNotEmpty(type)){
            queryWrapper.eq(Role.TYPE,type);
        }
        if(StringUtils.isNotEmpty(service)){
            queryWrapper.eq(Role.SERVICE,service);
        }
        queryWrapper.in(Role.ID,roleIds).orderByAsc(Role.TYPE,Role.SERVICE);
        List<Role> list = roleMapper.selectList(queryWrapper);
        log.info("返回角色列表JSON:"+JSON.toJSONString(list));
        return new BeanUtils<RoleVo>().copyObjs(list,RoleVo.class);
    }

    /**
     * 查询角色列表
     * @param service 系统ID
     * @param code 角色编码
     * @return
     */
    @Override
    public List<Role> getRoleList(String service, String code) {
        return roleMapper.getRoleList(service, code);
    }

    @Override
    public List<Role> selectList(RoleQueryForm queryForm) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper <Role>();
        getQueryWrapper(queryWrapper,queryForm);
        return roleMapper.selectList(queryWrapper);
    }

    /**
     * 查询用户有权限的系统
     * @param userId
     * @return
     */
    @Override
    public List<String> getSystemNameList(String userId) {
        return roleMapper.getSystemNameList(userId);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<Role> getQueryWrapper(QueryWrapper<Role> queryWrapper, BaseQueryForm<RoleQueryParam> queryForm){
        RoleQueryParam role = queryForm.toParam(RoleQueryParam.class);
        //条件拼接
        if(StringUtils.isNotBlank(role.getName())){
            queryWrapper.like(Role.NAME,role.getName());
        }
        if(StringUtils.isNotBlank(role.getType())){
            queryWrapper.like(Role.TYPE,role.getType());
        }
        if(role.getParentId() != null){
            queryWrapper.eq(Role.PARENT_ID,role.getParentId());
        }
        if(StringUtils.isNotBlank(role.getRemark())){
            queryWrapper.like(Role.REMARK,role.getRemark());
        }
        if(StringUtils.isNotBlank(role.getReserve())){
            queryWrapper.like(Role.RESERVE,role.getReserve());
        }
        if(StringUtils.isNotBlank(role.getVersion())){
            queryWrapper.like(Role.VERSION,role.getVersion());
        }
        if(StringUtils.isNotBlank(role.getService())){
            queryWrapper.eq(Role.SERVICE,role.getService());
        }
        if(StringUtils.isNotBlank(role.getCode())){
            queryWrapper.eq(Role.CODE,role.getCode());
        }
        if(role.getCreateBy() != null){
            queryWrapper.eq(Role.CREATE_BY,role.getCreateBy());
        }
        if(role.getUpdateBy() != null){
            queryWrapper.eq(Role.UPDATE_BY,role.getUpdateBy());
        }
        queryWrapper.orderByAsc(Role.SORT);
        return queryWrapper;
    }
}

