package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.BasicTicketMapQueryForm;
import com.deloitte.services.contract.entity.BasicTicketMap;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BasicTicketMap服务类接口
 * @Modified :
 */
public interface IBasicTicketMapService extends IService<BasicTicketMap> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BasicTicketMap>
     */
    IPage<BasicTicketMap> selectPage(BasicTicketMapQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BasicTicketMap>
     */
    List<BasicTicketMap> selectList(BasicTicketMapQueryForm queryForm);
}
