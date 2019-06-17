package com.deloitte.services.fssc.bocb2e.bo.request;

import com.deloitte.services.fssc.bocb2e.bo.Fractn;
import com.deloitte.services.fssc.bocb2e.bo.Toactn;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
public class B2eRq {

    /**
     * 付款账户信息
     */
    @XStreamAlias("fractn")
    protected Fractn fractn;
    /**
     * 收款账户信息
     */
    @XStreamAlias("toactn")
    protected Toactn toactn;
    /**
     * 指令ID，一 条转账指令 在客户端的 唯一标识 ，建议企业 按时间顺序 生成且不超 过12
     */
    @XStreamAlias("insid")
    protected String insid;
    /**
     * 网银交易流 水号
     */
    @XStreamAlias("obssid")
    protected String obssid;
    /**
     * 转账金额
     */
    @XStreamAlias("trnamt")
    protected String trnamt;
    /**
     * 转账货币
     */
    @XStreamAlias("trncur")
    protected String trncur;
    /**
     * 银行处理优 先级(0-普通 ；1-加急)
     */
    @XStreamAlias("priolv")
    protected String priolv;
    /**
     * 用途
     */
    @XStreamAlias("furinfo")
    protected String furinfo;
    /**
     * 要求的转账 日期 YYYYMMDD
     */
    @XStreamAlias("trfdate")
    protected String trfdate;
    /**
     * 要求的转账时间
     */
    @XStreamAlias("trftime")
    protected String trftime;
    /**
     * 手续费账号
     */
    @XStreamAlias("comacn")
    protected String comacn;



}
