package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.BaseUserQueryForm;
import com.deloitte.services.isump.entity.BaseUser;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : BaseUser服务类接口
 * @Modified :
 */
public interface IBaseUserService extends IService<BaseUser> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseUser>
     */
    IPage<BaseUser> selectPage(BaseUserQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseUser>
     */
    List<BaseUser> selectList(BaseUserQueryForm queryForm);
}
