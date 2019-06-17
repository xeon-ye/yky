package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.BasicOrderMapQueryForm;
import com.deloitte.services.contract.entity.BasicOrderMap;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BasicOrderMap服务类接口
 * @Modified :
 */
public interface IBasicOrderMapService extends IService<BasicOrderMap> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BasicOrderMap>
     */
    IPage<BasicOrderMap> selectPage(BasicOrderMapQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BasicOrderMap>
     */
    List<BasicOrderMap> selectList(BasicOrderMapQueryForm queryForm);
}
