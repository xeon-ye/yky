package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.BasicProjectMapQueryForm;
import com.deloitte.services.contract.entity.BasicProjectMap;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BasicProjectMap服务类接口
 * @Modified :
 */
public interface IBasicProjectMapService extends IService<BasicProjectMap> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BasicProjectMap>
     */
    IPage<BasicProjectMap> selectPage(BasicProjectMapQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BasicProjectMap>
     */
    List<BasicProjectMap> selectList(BasicProjectMapQueryForm queryForm);
}
