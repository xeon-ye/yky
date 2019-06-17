package com.deloitte.services.fastway.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.fastway.param.FastWayQueryForm;
import com.deloitte.services.fastway.entity.FastWay;

import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-17
 * @Description : FastWay服务类接口
 * @Modified :
 */
public interface IFastWayService extends IService<FastWay> {

    /**
     * 分页查询
     *
     * @param queryForm
     * @return IPage<FastWay>
     */
    IPage<FastWay> selectPage(FastWayQueryForm queryForm);

    /**
     * 条件查询
     *
     * @param queryForm
     * @return List<FastWay>
     */
    List<FastWay> selectList(FastWayQueryForm queryForm);
}
