package com.deloitte.services.fssc.bocb2e.bo.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("trn-b2e0009-rq")
public class TrnB2e0009Rq extends TrnB2eRq {


    @XStreamImplicit(itemFieldName = "b2e0009-rq")
    private List<B2e0009Rq> b2e0009Rqs;

}
