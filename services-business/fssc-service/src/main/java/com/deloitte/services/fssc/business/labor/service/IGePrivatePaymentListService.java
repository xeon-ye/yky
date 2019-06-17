package com.deloitte.services.fssc.business.labor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.labor.param.GePrivatePaymentListQueryForm;
import com.deloitte.services.fssc.business.labor.entity.GePrivatePaymentList;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description : GePrivatePaymentList服务类接口
 * @Modified :
 */
public interface IGePrivatePaymentListService extends IService<GePrivatePaymentList> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<GePrivatePaymentList>
     */
    IPage<GePrivatePaymentList> selectPage(GePrivatePaymentListQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<GePrivatePaymentList>
     */
    List<GePrivatePaymentList> selectList(GePrivatePaymentListQueryForm queryForm);
}
