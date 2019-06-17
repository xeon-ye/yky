package com.deloitte.services.fssc.bocb2e.bo.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
public class TrnB2eRq {

    /**
     * 数字签名 ，该标签由 前置机自动 添加，企业 无须上送
     */
    @XStreamAlias("ceitinfo")
    protected String ceitinfo;

    /**
     * 交易类型
     */
    @XStreamAlias("transtype")
    protected String transtype;


}
