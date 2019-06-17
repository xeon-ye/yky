package com.deloitte.services.fssc.bocb2e.bo.response;

import com.deloitte.services.fssc.bocb2e.bo.BocbStatus;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
public class B2eRs {

    /**
     * 状态
     */
    @XStreamAlias("status")
    protected BocbStatus status;

    /**
     *指令ID，请求 时给出的ID
     */
    @XStreamAlias("insid")
    protected String insid;

    /**
     *每条划账指令 的网银划账流 水号
     */
    @XStreamAlias("obssid")
    protected String obssid;

    /**
     * 服务日期
     */
    @XStreamAlias("serverdt")
    protected String serverdt;

    /**
     * 交易验证标识 ，签到时返回
     */
    @XStreamAlias("token")
    protected String token;

}
