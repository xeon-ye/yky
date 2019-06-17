package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.DeputyAccountRoleQueryForm;
import com.deloitte.services.isump.entity.DeputyAccountRole;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : DeputyAccountRole服务类接口
 * @Modified :
 */
public interface IDeputyAccountRoleService extends IService<DeputyAccountRole> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<DeputyAccountRole>
     */
    IPage<DeputyAccountRole> selectPage(DeputyAccountRoleQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<DeputyAccountRole>
     */
    List<DeputyAccountRole> selectList(DeputyAccountRoleQueryForm queryForm);

    List<String> selectRolesByDeputyAccountId(long deputyAccountId);

}
