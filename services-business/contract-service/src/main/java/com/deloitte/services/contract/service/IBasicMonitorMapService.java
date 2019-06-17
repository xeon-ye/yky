package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.BasicMonitorMapQueryForm;
import com.deloitte.services.contract.entity.BasicMonitorMap;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BasicMonitorMap服务类接口
 * @Modified :
 */
public interface IBasicMonitorMapService extends IService<BasicMonitorMap> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BasicMonitorMap>
     */
    IPage<BasicMonitorMap> selectPage(BasicMonitorMapQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BasicMonitorMap>
     */
    List<BasicMonitorMap> selectList(BasicMonitorMapQueryForm queryForm);
}
