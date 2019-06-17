package com.deloitte.services.demomybatiesauto.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.demomybatiesauto.param.UserDemoQueryForm;
import com.deloitte.services.demomybatiesauto.entity.UserDemo;
import com.deloitte.services.demomybatiesauto.mapper.UserDemoMapper;
import com.deloitte.services.demomybatiesauto.service.IUserDemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jack
 * @Date : Create in 2019-02-20
 * @Description :  UserDemo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class UserDemoServiceImpl extends ServiceImpl<UserDemoMapper, UserDemo> implements IUserDemoService {


    @Autowired
    private UserDemoMapper userDemoMapper;

    @Override
    public IPage<UserDemo> selectPage(UserDemoQueryForm queryForm ) {
        QueryWrapper<UserDemo> queryWrapper = new QueryWrapper <UserDemo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return userDemoMapper.selectPage(new Page<UserDemo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<UserDemo> selectList(UserDemoQueryForm queryForm) {
        QueryWrapper<UserDemo> queryWrapper = new QueryWrapper <UserDemo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return userDemoMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<UserDemo> getQueryWrapper(QueryWrapper<UserDemo> queryWrapper,BaseQueryForm<UserDemoQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(userDemo.getName())){
            queryWrapper.like(UserDemo.NAME,userDemo.getName());
        }
        if(StringUtils.isNotBlank(userDemo.getAge())){
            queryWrapper.like(UserDemo.AGE,userDemo.getAge());
        }
        if(StringUtils.isNotBlank(userDemo.getEmail())){
            queryWrapper.like(UserDemo.EMAIL,userDemo.getEmail());
        }
        if(StringUtils.isNotBlank(userDemo.getCreateTime())){
            queryWrapper.like(UserDemo.CREATE_TIME,userDemo.getCreateTime());
        }
        if(StringUtils.isNotBlank(userDemo.getCreateBy())){
            queryWrapper.like(UserDemo.CREATE_BY,userDemo.getCreateBy());
        }
        if(StringUtils.isNotBlank(userDemo.getUpdateTime())){
            queryWrapper.like(UserDemo.UPDATE_TIME,userDemo.getUpdateTime());
        }
        if(StringUtils.isNotBlank(userDemo.getUpdateBy())){
            queryWrapper.like(UserDemo.UPDATE_BY,userDemo.getUpdateBy());
        }
        if(StringUtils.isNotBlank(userDemo.getExt1())){
            queryWrapper.like(UserDemo.EXT1,userDemo.getExt1());
        }
        return queryWrapper;
    }
     */
}

