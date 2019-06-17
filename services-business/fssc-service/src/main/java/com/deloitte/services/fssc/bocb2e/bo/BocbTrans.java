package com.deloitte.services.fssc.bocb2e.bo;

import com.deloitte.services.fssc.bocb2e.bo.request.TrnB2e0001Rq;
import com.deloitte.services.fssc.bocb2e.bo.request.TrnB2e0009Rq;
import com.deloitte.services.fssc.bocb2e.bo.response.TrnB2e0001Rs;
import com.deloitte.services.fssc.bocb2e.bo.response.TrnB2e0009Rs;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("trans")
public class BocbTrans {

    /**
     * 对公支付请求
     */
    @XStreamAlias("trn-b2e0009-rq")
    private TrnB2e0009Rq trnB2e0009Rq;

    /**
     *对公支付响应
     */
    @XStreamAlias("trn-b2e0009-rs")
    private TrnB2e0009Rs trnB2e0009Rs;

    /**
     * 签到请求
     */
    @XStreamAlias("trn-b2e0001-rq")
    private TrnB2e0001Rq trnB2e0001Rq;

    /**
     *签到响应
     */
    @XStreamAlias("trn-b2e0001-rs")
    private TrnB2e0001Rs trnB2e0001Rs;

}
