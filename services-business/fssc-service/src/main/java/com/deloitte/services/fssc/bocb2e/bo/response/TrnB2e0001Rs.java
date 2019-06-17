package com.deloitte.services.fssc.bocb2e.bo.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("trn-b2e0001-rs")
public class TrnB2e0001Rs extends TrnB2eRs{

    @XStreamAlias("b2e0001-rs")
    private B2e0001Rs b2e0001Rs;
}
