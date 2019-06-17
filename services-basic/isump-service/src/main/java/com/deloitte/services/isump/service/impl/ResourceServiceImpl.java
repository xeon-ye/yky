package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.ResourceQueryForm;
import com.deloitte.platform.api.isump.param.ResourceQueryParam;
import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.services.isump.entity.DeputyAccount;
import com.deloitte.services.isump.entity.Resource;
import com.deloitte.services.isump.entity.User;
import com.deloitte.services.isump.mapper.ResourceMapper;
import com.deloitte.services.isump.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  Resource服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {


    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private IDeputyAccountRoleService deputyAccountRoleService;

    @Autowired
    private IOrgRoleService orgRoleService;

    @Autowired
    private IDeputyAccountService deputyAccountService;
    @Autowired
    private IUserService userService;

    @Override
    public IPage<Resource> selectPage(ResourceQueryForm queryForm ) {
        QueryWrapper<Resource> queryWrapper = new QueryWrapper <Resource>();
        getQueryWrapper(queryWrapper,queryForm);
        return resourceMapper.selectPage(new Page<Resource>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Resource> selectList(ResourceQueryForm queryForm) {
        QueryWrapper<Resource> queryWrapper = new QueryWrapper <Resource>();
        getQueryWrapper(queryWrapper,queryForm);
        return resourceMapper.selectList(queryWrapper);
    }

    @Override
    public ResourceVo findTree(long deputyAccountId, String sysCode) {
        //查询当前副账号的角色（包含组织公共角色和副账号私有的角色）
        //1.查询副账号角色
        List<String> roles = deputyAccountRoleService.selectRolesByDeputyAccountId(deputyAccountId);
        //2.查询组织公共角色
        DeputyAccount deputyAccount = deputyAccountService.getById(deputyAccountId);
        if(deputyAccount == null){
            throw new ServiceException(PlatformErrorType.NO_USER);
        }
        User user = userService.getById(deputyAccount.getUserId());
        if(user == null){
            throw new ServiceException(PlatformErrorType.NO_USER);
        }
        if(deputyAccount != null && deputyAccount.getOrgId() != null){
            List<String> orgRoles = orgRoleService.selectRolesByOrgId(deputyAccount.getOrgId());
            //根据角色和系统code查询资源对象
            roles.addAll(orgRoles);
        }
        if(roles == null || roles.size() == 0){
            return new ResourceVo();
        }
        List<ResourceVo> resource = resourceMapper.findTree(roles,sysCode);
        if(resource == null || resource.size() == 0){
            return new ResourceVo();
        }
        //设定根目录的levels
        ResourceVo root = new ResourceVo();
        root.setChildren(new ArrayList<>());
        String levels = resource.get(0).getLevels();
        for (ResourceVo vo: resource) {
            if(levels.equals(vo.getLevels())){
                //加根目录
                 root.getChildren().add(vo);
            }else{
                //加到上级的children中
                for (ResourceVo v:resource) {
                    if("1001".equals(user.getDept()) && vo.getId() == 43L){
                        //如果是1级，跳过科研的自评报告
                        //TODO 针对科研上线特殊模块处理，后面要改为通用的
                    }else if(v.getId().equals(vo.getParentId())){
                        List<ResourceVo> ch = v.getChildren();
                        if(ch == null){
                            ch = new ArrayList<>();
                            v.setChildren(ch);
                        }
                        v.getChildren().add(vo);
                        break;
                    }
                }
            }
        }
        return root;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<Resource> getQueryWrapper(QueryWrapper<Resource> queryWrapper, BaseQueryForm<ResourceQueryParam> queryForm){
        ResourceQueryParam resource = queryForm.toParam(ResourceQueryParam.class);
        //条件拼接
        if(StringUtils.isNotBlank(resource.getIcon())){
            queryWrapper.like(Resource.ICON,resource.getIcon());
        }
        if(StringUtils.isNotBlank(resource.getName())){
            queryWrapper.like(Resource.NAME,resource.getName());
        }
        if(StringUtils.isNotBlank(resource.getCode())){
            queryWrapper.eq(Resource.CODE,resource.getCode());
        }
        if(StringUtils.isNotBlank(resource.getUri())){
            queryWrapper.like(Resource.URI,resource.getUri());
        }
        if(StringUtils.isNotBlank(resource.getPerms())){
            queryWrapper.like(Resource.PERMS,resource.getPerms());
        }
        if(resource.getParentId() != null){
            queryWrapper.eq(Resource.PARENT_ID,resource.getParentId());
        }
        if(StringUtils.isNotBlank(resource.getLevels())){
            queryWrapper.eq(Resource.LEVELS,resource.getLevels());
        }
        if(resource.getLeaf() != null){
            queryWrapper.eq(Resource.LEAF,resource.getLeaf());
        }
        if(resource.getOpen() != null){
            queryWrapper.eq(Resource.OPEN,resource.getOpen());
        }
        if(StringUtils.isNotBlank(resource.getType())){
            queryWrapper.eq(Resource.TYPE,resource.getType());
        }
        if(StringUtils.isNotBlank(resource.getState())){
            queryWrapper.eq(Resource.STATE,resource.getState());
        }
        if(StringUtils.isNotBlank(resource.getRemark())){
            queryWrapper.like(Resource.REMARK,resource.getRemark());
        }
        if(StringUtils.isNotBlank(resource.getReserve())){
            queryWrapper.like(Resource.RESERVE,resource.getReserve());
        }
        if(StringUtils.isNotBlank(resource.getVersion())){
            queryWrapper.like(Resource.VERSION,resource.getVersion());
        }
        if(resource.getCreateBy() != null){
            queryWrapper.eq(Resource.CREATE_BY,resource.getCreateBy());
        }
        if(resource.getUpdateBy() != null){
            queryWrapper.eq(Resource.UPDATE_BY,resource.getUpdateBy());
        }
        if(StringUtils.isNotBlank(resource.getSysCode())){
            queryWrapper.like(Resource.SYS_CODE,resource.getSysCode());
        }
        queryWrapper.orderByAsc(Resource.SORT);
        return queryWrapper;
    }
}

