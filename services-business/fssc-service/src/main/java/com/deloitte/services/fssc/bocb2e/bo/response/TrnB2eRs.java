package com.deloitte.services.fssc.bocb2e.bo.response;

import com.deloitte.services.fssc.bocb2e.bo.BocbStatus;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
public class TrnB2eRs {

    /**
     * 状态
     */
    @XStreamAlias("status")
    protected BocbStatus status;

}
