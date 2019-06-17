package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.project.param.ProUserQueryForm;
import com.deloitte.services.project.entity.ProUser;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-24
 * @Description : ProUser服务类接口
 * @Modified :
 */
public interface IProUserService extends IService<ProUser> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProUser>
     */
    IPage<ProUser> selectPage(ProUserQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProUser>
     */
    List<ProUser> selectList(ProUserQueryForm queryForm);

    /**
     * 查询项目各负责人
     * @param userMark 各负责人标识
     * @return
     */
    List<ProUser> getProUserList(String userMark);
}
