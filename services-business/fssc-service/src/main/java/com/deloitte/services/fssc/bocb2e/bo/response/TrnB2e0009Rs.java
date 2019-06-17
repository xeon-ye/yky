package com.deloitte.services.fssc.bocb2e.bo.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("trn-b2e0009-rs")
public class TrnB2e0009Rs extends TrnB2eRs{

    @XStreamImplicit(itemFieldName = "b2e0009-rs")
    private List<B2e0009Rs> b2e0009Rs;
}
