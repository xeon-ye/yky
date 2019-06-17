package com.deloitte.services.demomybatiesauto.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.demomybatiesauto.param.UserDemoQueryForm;
import com.deloitte.services.demomybatiesauto.entity.UserDemo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jack
 * @Date : Create in 2019-02-20
 * @Description : UserDemo服务类接口
 * @Modified :
 */
public interface IUserDemoService extends IService<UserDemo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<UserDemo>
     */
    IPage<UserDemo> selectPage(UserDemoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<UserDemo>
     */
    List<UserDemo> selectList(UserDemoQueryForm queryForm);
}
