package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.DeputyAccountRoleQueryForm;
import com.deloitte.platform.api.isump.param.DeputyAccountRoleQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.isump.entity.DeputyAccountRole;
import com.deloitte.services.isump.mapper.DeputyAccountRoleMapper;
import com.deloitte.services.isump.service.IDeputyAccountRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  DeputyAccountRole服务实现类
 * @Modified :
 */
@Service
@Transactional
public class DeputyAccountRoleServiceImpl extends ServiceImpl<DeputyAccountRoleMapper, DeputyAccountRole> implements IDeputyAccountRoleService {


    @Autowired
    private DeputyAccountRoleMapper deputyAccountRoleMapper;

    @Override
    public IPage<DeputyAccountRole> selectPage(DeputyAccountRoleQueryForm queryForm ) {
        QueryWrapper<DeputyAccountRole> queryWrapper = new QueryWrapper <DeputyAccountRole>();
        getQueryWrapper(queryWrapper,queryForm);
        return deputyAccountRoleMapper.selectPage(new Page<DeputyAccountRole>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<DeputyAccountRole> selectList(DeputyAccountRoleQueryForm queryForm) {
        QueryWrapper<DeputyAccountRole> queryWrapper = new QueryWrapper <DeputyAccountRole>();
        getQueryWrapper(queryWrapper,queryForm);
        return deputyAccountRoleMapper.selectList(queryWrapper);
    }

    @Override
    public List<String> selectRolesByDeputyAccountId(long deputyAccountId) {
        return deputyAccountRoleMapper.selectRolesByDeputyAccountId(deputyAccountId);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<DeputyAccountRole> getQueryWrapper(QueryWrapper<DeputyAccountRole> queryWrapper, BaseQueryForm<DeputyAccountRoleQueryParam> queryForm){
        //条件拼接
        DeputyAccountRoleQueryParam deputyAccountRole = queryForm.toParam(DeputyAccountRoleQueryParam.class);
        if(deputyAccountRole.getDeputyAccountId() != null){
            queryWrapper.eq(DeputyAccountRole.DEPUTY_ACCOUNT_ID,deputyAccountRole.getDeputyAccountId());
        }
        if(deputyAccountRole.getRoleId() != null){
            queryWrapper.eq(DeputyAccountRole.ROLE_ID,deputyAccountRole.getRoleId());
        }
        return queryWrapper;
    }
}

