package com.deloitte.services.fssc.bocb2e.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("status")
public class BocbStatus {

    /**
     *B001(表示成功) 返回代码
     */
    @XStreamAlias("rspcod")
    private String rspcod;

    /**
     *返回消息
     */
    @XStreamAlias("rspmsg")
    private String rspmsg;

}
