package com.deloitte.services.fssc.engine.automatic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvDailyRatesQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvDailyRates;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description : AvDailyRates服务类接口
 * @Modified :
 */
public interface IAvDailyRatesService extends IService<AvDailyRates> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AvDailyRates>
     */
    IPage<AvDailyRates> selectPage(AvDailyRatesQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AvDailyRates>
     */
    List<AvDailyRates> selectList(AvDailyRatesQueryForm queryForm);
}
