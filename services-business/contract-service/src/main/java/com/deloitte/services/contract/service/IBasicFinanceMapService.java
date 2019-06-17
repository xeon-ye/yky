package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.BasicFinanceMapQueryForm;
import com.deloitte.services.contract.entity.BasicFinanceMap;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BasicFinanceMap服务类接口
 * @Modified :
 */
public interface IBasicFinanceMapService extends IService<BasicFinanceMap> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BasicFinanceMap>
     */
    IPage<BasicFinanceMap> selectPage(BasicFinanceMapQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BasicFinanceMap>
     */
    List<BasicFinanceMap> selectList(BasicFinanceMapQueryForm queryForm);
}
