package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.MaintenanceQueryForm;
import com.deloitte.services.project.entity.Maintenance;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description : Maintenance服务类接口
 * @Modified :
 */
public interface IMaintenanceService extends IService<Maintenance> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Maintenance>
     */
    IPage<Maintenance> selectPage(MaintenanceQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Maintenance>
     */
    List<Maintenance> selectList(MaintenanceQueryForm queryForm);

    /**
     * 获取
     * @param projectId
     * @return
     */
    List<Maintenance> getOneList(String projectId);
}
