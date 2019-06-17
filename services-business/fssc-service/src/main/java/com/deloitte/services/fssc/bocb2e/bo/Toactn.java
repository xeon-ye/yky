package com.deloitte.services.fssc.bocb2e.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 收款人账户信息
 */
@Data
@XStreamAlias("toactn")
public class Toactn {

    /**
     * 收款行 联行号
     */
    @XStreamAlias("toibkn")
    private String toibkn;

    /**
     * 收款账号
     */
    @XStreamAlias("actacn")
    private String actacn;

    /**
     * 收款人名称
     */
    @XStreamAlias("toname")
    private String toname;

    /**
     * 收款人地址
     */
    @XStreamAlias("toaddr")
    private String toaddr;

    /**
     * 收款人开户 行名称
     */
    @XStreamAlias("tobknm")
    private String tobknm;

    /**
     * 收款账户类型
     */
    @XStreamAlias("acttyp")
    private String acttyp;
}
