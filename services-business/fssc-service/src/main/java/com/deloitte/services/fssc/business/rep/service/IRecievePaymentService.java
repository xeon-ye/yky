package com.deloitte.services.fssc.business.rep.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.rep.param.RecieveLineMsgQueryParam;
import com.deloitte.platform.api.fssc.rep.param.RecievePaymentQueryForm;
import com.deloitte.platform.api.fssc.rep.vo.RecieveLineMsgVo;
import com.deloitte.platform.api.fssc.trade.vo.BusinessRelateDocument;
import com.deloitte.services.fssc.business.rep.entity.RecievePayment;

import java.util.List;
import java.util.Map;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description : RecievePayment服务类接口
 * @Modified :
 */
public interface IRecievePaymentService extends IService<RecievePayment> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<RecievePayment>
     */
    IPage<RecievePayment> selectPage(RecievePaymentQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<RecievePayment>
     */
    List<RecievePayment> selectList(RecievePaymentQueryForm queryForm);

    List<RecieveLineMsgVo> findRecieveLines(RecieveLineMsgQueryParam recieveLineMsgQueryParam);

    List<RecieveLineMsgVo> findRecieveLineShouRuShangjiao( RecieveLineMsgQueryParam param);
    /**
     * 收入大类是否被关联
     * @param incomeMainTypeIds
     * @return
     */
    boolean existsByReceivePayment(List<Long> incomeMainTypeIds);

    void modifyCliamStatus(String documentType, Long documentId,boolean isSubmit);

    Map<String,List<BusinessRelateDocument>> getSerialNumDocumentMap(List<String> serialNumList);
}
