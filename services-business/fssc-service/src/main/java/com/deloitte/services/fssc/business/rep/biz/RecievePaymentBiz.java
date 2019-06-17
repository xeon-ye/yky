package com.deloitte.services.fssc.business.rep.biz;

import com.deloitte.platform.api.fssc.rep.vo.RecievePaymentForm;
import com.deloitte.platform.api.fssc.rep.vo.RecievePaymentVo;

public interface RecievePaymentBiz {

    RecievePaymentVo saveOrUpdate(RecievePaymentForm recievePaymentForm);

    void deleteById(Long documentId);

    RecievePaymentVo getById(Long id);

    void saveClaimArea(RecievePaymentForm recievePaymentForm);
}
