package com.deloitte.services.fssc.business.general.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.general.param.GeExpensePaymentListQueryForm;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description : GeExpensePaymentList服务类接口
 * @Modified :
 */
public interface IGeExpensePaymentListService extends IService<GeExpensePaymentList> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<GeExpensePaymentList>
     */
    IPage<GeExpensePaymentList> selectPage(GeExpensePaymentListQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<GeExpensePaymentList>
     */
    List<GeExpensePaymentList> selectList(GeExpensePaymentListQueryForm queryForm);
}
