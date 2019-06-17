package com.deloitte.services.fssc.business.general.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryForm;
import com.deloitte.services.fssc.business.general.entity.GeExpenseBorrowPrepay;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description : GeExpenseBorrowPrepay服务类接口
 * @Modified :
 */
public interface IGeExpenseBorrowPrepayService extends IService<GeExpenseBorrowPrepay> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<GeExpenseBorrowPrepay>
     */
    IPage<GeExpenseBorrowPrepay> selectPage(GeExpenseBorrowPrepayQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<GeExpenseBorrowPrepay>
     */
    List<GeExpenseBorrowPrepay> selectList(GeExpenseBorrowPrepayQueryForm queryForm);




    /**
     * 驳回 撤回 冲销 报账单修改金额 涉及到所有报账单
     * @param isSubmit
     * @param documentId
     * @param documentType
     */
    void modifyAfterSubmit(boolean isSubmit, Long documentId, String documentType);

    void modifyContactAmount (boolean isSubmit, Long documentId, String documentType);

    void modifyAfterContractSubmit(boolean isSubmit, Long documentId, String documentType);


    void paddingHexiaomingxi(List<GeExpenseBorrowPrepay> borrowPrepays);

    String getPaymentOrderBudgetAccountCode(Long borrowOrPrepayId);

    void modifyPayStatus(Long documentId,String documentType);
}
