package com.deloitte.services.fssc.business.travle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.travle.param.TasTravelStandardsQueryForm;
import com.deloitte.services.fssc.business.travle.entity.TasTravelStandards;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-17
 * @Description : TasTravelStandards服务类接口
 * @Modified :
 */
public interface ITasTravelStandardsService extends IService<TasTravelStandards> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<TasTravelStandards>
     */
    IPage<TasTravelStandards> selectPage(TasTravelStandardsQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<TasTravelStandards>
     */
    List<TasTravelStandards> selectList(TasTravelStandardsQueryForm queryForm);


    TasTravelStandards selectTravelStandar(String peakMonth, String nationAdminCode, String country, String placeName);

    boolean selectCount(String travelDate,String locationEndTravel);
}
