package com.deloitte.services.fssc.bocb2e.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 付款人账户信息
 */
@Data
@XStreamAlias("fractn")
public class Fractn {

    /**
     * 联行号
     */
    @XStreamAlias("fribkn")
    private String fribkn;

    /**
     * 付款账号
     */
    @XStreamAlias("actacn")
    private String actacn;

    /**
     * 付款人名称
     */
    @XStreamAlias("actnam")
    private String actnam;

}
