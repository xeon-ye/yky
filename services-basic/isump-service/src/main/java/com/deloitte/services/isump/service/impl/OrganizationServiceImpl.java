package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.OrganizationQueryForm;
import com.deloitte.platform.api.isump.param.OrganizationQueryParam;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.isump.entity.DeputyAccount;
import com.deloitte.services.isump.entity.Organization;
import com.deloitte.services.isump.entity.User;
import com.deloitte.services.isump.mapper.OrganizationMapper;
import com.deloitte.services.isump.service.IOrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.isump.service.IUserService;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  Organization服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements IOrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private IUserService iUserService;

    @Override
    public OrganizationVo getByCode(String code){
        return organizationMapper.getByCode(code);
    }

    @Override
    public OrganizationVo getByName(String name){
        return organizationMapper.getByName(name);
    }

    /**
     * 根据id查询组织实体
     * @param id
     * @return
     */
    @Override
    public OrganizationVo getByID(String id){
        return organizationMapper.getByID(id);
    }

    /**
     * 查询组织信息
     * @param list 副账号信息
     * @return
     */
    @Override
    public List<Organization> getByListID(List<DeputyAccount> list) {
        return organizationMapper.getByListID(list);
    }

    /**
     * 组织编码查询下级组织
     * @param code 组织编号
     * @return
     */
    @Override
    public List<OrganizationVo> getOrgTreeByCode(String code) {
        return organizationMapper.getOrgTreeByCode(code);
    }

    /**
     * 组织编码查询下级虚拟组织
     * @param code 组织编码
     * @return
     */
    @Override
    public List<Organization> getOrgFinctionByCode(String code) {
        return organizationMapper.getOrgFinctionByCode(code);
    }

    /**
     * 根据组织code查询下级所有部门
     * @param code 组织code
     * @return
     */
    @Override
    public List<Organization> getOrgDeptByCode(String code) {
        return organizationMapper.getOrgDeptByCode(code);
    }

    @Override
    public IPage<Organization> selectPage(OrganizationQueryForm queryForm ) {
        QueryWrapper<Organization> queryWrapper = new QueryWrapper <Organization>();
        getQueryWrapper(queryWrapper,queryForm);
        return organizationMapper.selectPage(new Page<Organization>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Organization> selectList(OrganizationQueryForm queryForm) {
        QueryWrapper<Organization> queryWrapper = new QueryWrapper <Organization>();
        getQueryWrapper(queryWrapper,queryForm);
        return organizationMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<Organization> getQueryWrapper(QueryWrapper<Organization> queryWrapper, BaseQueryForm<OrganizationQueryParam> queryForm){
        OrganizationQueryParam organization = queryForm.toParam(OrganizationQueryParam.class);
        //条件拼接
        if(StringUtils.isNotBlank(organization.getName())){
            queryWrapper.like(Organization.NAME,organization.getName());
        }
        if(organization.getParentId() != null){
            queryWrapper.eq(Organization.PARENT_ID,organization.getParentId());
        }
        if(StringUtils.isNotBlank(organization.getType())){
            queryWrapper.eq(Organization.TYPE,organization.getType());
        }
        if(StringUtils.isNotBlank(organization.getState())){
            queryWrapper.eq(Organization.STATE,organization.getState());
        }
        if(StringUtils.isNotBlank(organization.getRemark())){
            queryWrapper.like(Organization.REMARK,organization.getRemark());
        }
        if(StringUtils.isNotBlank(organization.getReserve())){
            queryWrapper.eq(Organization.RESERVE,organization.getReserve());
        }
        if(StringUtils.isNotBlank(organization.getVersion())){
            queryWrapper.eq(Organization.VERSION,organization.getVersion());
        }
        if(organization.getCreateBy() != null){
            queryWrapper.eq(Organization.CREATE_BY,organization.getCreateBy());
        }
        if(organization.getUpdateBy() != null){
            queryWrapper.eq(Organization.UPDATE_BY,organization.getUpdateBy());
        }
        if(organization.getIds() != null && organization.getIds().size() > 0){
            queryWrapper.in(Organization.ID,organization.getIds());
        }
        if(organization.getCodes() != null && organization.getCodes().size() > 0){
            queryWrapper.in(Organization.CODE,organization.getCodes());
        }
        queryWrapper.orderByAsc(Organization.SORT);
        return queryWrapper;
    }
}

