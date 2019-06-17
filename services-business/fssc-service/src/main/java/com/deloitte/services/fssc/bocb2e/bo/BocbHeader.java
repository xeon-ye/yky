package com.deloitte.services.fssc.bocb2e.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("head")
public class BocbHeader {

    /**
     *代表一台企业前置机 企业前置机id
     */
    @XStreamAlias("termid")
    private String termid;

    /**
     *客户端产生的报文编号
     */
    @XStreamAlias("trnid")
    private String trnid;

    /**
     *企业在中行网银系统的客户编码
     */
    @XStreamAlias("custid")
    private String custid;

    /**
     *企业操作员代码
     */
    @XStreamAlias("cusopr")
    private String cusopr;

    /**
     *交易代码
     */
    @XStreamAlias("trncod")
    private String trncod;

    /**
     *交易验证标识，签到时生成、签退时注销
     */
    @XStreamAlias("token")
    private String token;

    /**
     *交易流水号
     */
    @XStreamAlias("obssmsgid")
    private String obssmsgid;

    /**
     *业务类型
     */
    @XStreamAlias("trntype")
    private String trntype;

    /**
     *通知次数
     */
    @XStreamAlias("pushnum")
    private String pushnum;
}






























