package com.deloitte.services.fssc.bocb2e.biz.ipml;

import com.deloitte.services.fssc.bocb2e.biz.IBocb2eBiz;
import com.deloitte.services.fssc.bocb2e.bo.Bocb2eBo;
import com.deloitte.services.fssc.business.pay.service.IPyPamentBusinessLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bocb2e0009BizImpl extends Bocb2eAbstract implements IBocb2eBiz {

    @Autowired
    private IPyPamentBusinessLineService pyPamentBusinessLineService;


    /**
     * @param paymentId 付款单id
     */
    @Override
    public void sendToBank(Long paymentId) {
        Bocb2eBo bocb2eBo = buildBoCommon();

    }


}
