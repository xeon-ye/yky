package com.deloitte.services.fssc.bocb2e.bo.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("trn-b2e0001-rq")
public class TrnB2e0001Rq {

    @XStreamAlias("b2e0001-rq")
    private B2e0001Rq b2e0001Rq;
}
