package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.RoleResourceQueryForm;
import com.deloitte.services.isump.entity.RoleResource;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : RoleResource服务类接口
 * @Modified :
 */
public interface IRoleResourceService extends IService<RoleResource> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<RoleResource>
     */
    IPage<RoleResource> selectPage(RoleResourceQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<RoleResource>
     */
    List<RoleResource> selectList(RoleResourceQueryForm queryForm);
}
