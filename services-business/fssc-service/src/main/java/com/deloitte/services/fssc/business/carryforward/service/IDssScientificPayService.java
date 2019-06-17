package com.deloitte.services.fssc.business.carryforward.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.carryforward.param.DssScientificPayQueryForm;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.business.carryforward.entity.DssScientificPay;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-06-11
 * @Description : DssScientificPay服务类接口
 * @Modified :
 */
public interface IDssScientificPayService extends IService<DssScientificPay> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<DssScientificPay>
     */
    IPage<DssScientificPay> selectPage(DssScientificPayQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<DssScientificPay>
     */
    List<DssScientificPay> selectList(DssScientificPayQueryForm queryForm);
}
