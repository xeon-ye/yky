package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.BasicAttamentMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicAttamentMapForm;
import com.deloitte.services.contract.entity.BasicAttamentMap;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-29
 * @Description : BasicAttamentMap服务类接口
 * @Modified :
 */
public interface IBasicAttamentMapService extends IService<BasicAttamentMap> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BasicAttamentMap>
     */
    IPage<BasicAttamentMap> selectPage(BasicAttamentMapQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BasicAttamentMap>
     */
    List<BasicAttamentMap> selectList(BasicAttamentMapQueryForm queryForm);
    //保存授权委托书相关信息
    BasicAttamentMap addBasicAttamentMap(BasicAttamentMapForm basicAttamentMapForm);
}
