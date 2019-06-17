package com.deloitte.services.fssc.business.travle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.travle.param.TasTravelTimeQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasTravelTime;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-02
 * @Description : TasTravelTime服务类接口
 * @Modified :
 */
public interface ITasTravelTimeService extends IService<TasTravelTime> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<TasTravelTime>
     */
    IPage<TasTravelTime> selectPage(TasTravelTimeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<TasTravelTime>
     */
    List<TasTravelTime> selectList(TasTravelTimeQueryForm queryForm);
}
