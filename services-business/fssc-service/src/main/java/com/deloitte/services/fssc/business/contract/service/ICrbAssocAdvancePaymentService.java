package com.deloitte.services.fssc.business.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.contract.param.CrbAssocAdvancePaymentQueryForm;
import com.deloitte.services.fssc.business.contract.entity.CrbAssocAdvancePayment;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-26
 * @Description : CrbAssocAdvancePayment服务类接口
 * @Modified :
 */
public interface ICrbAssocAdvancePaymentService extends IService<CrbAssocAdvancePayment> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<CrbAssocAdvancePayment>
     */
    IPage<CrbAssocAdvancePayment> selectPage(CrbAssocAdvancePaymentQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<CrbAssocAdvancePayment>
     */
    List<CrbAssocAdvancePayment> selectList(CrbAssocAdvancePaymentQueryForm queryForm);
}
