package com.deloitte.services.fssc.business.poi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.poi.param.PepaymentOrderInfoQueryForm;
import com.deloitte.services.fssc.business.poi.entity.PepaymentOrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-05-13
 * @Description : PepaymentOrderInfo服务类接口
 * @Modified :
 */
public interface IPepaymentOrderInfoService extends IService<PepaymentOrderInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PepaymentOrderInfo>
     */
    IPage<PepaymentOrderInfo> selectPage(PepaymentOrderInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PepaymentOrderInfo>
     */
    List<PepaymentOrderInfo> selectList(PepaymentOrderInfoQueryForm queryForm);
}
