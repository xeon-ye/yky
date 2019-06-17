package com.deloitte.services.fssc.bocb2e.bo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

@Data
@XStreamAlias("bocb2e")
public class Bocb2eBo {

    @XStreamAsAttribute
    private String version = "100";
    @XStreamAsAttribute
    private String security = "true";
    @XStreamAsAttribute
    private String locale = "zh_CN";


    /**
     * 请求头
     */
    @XStreamAlias("head")
    private BocbHeader header;

    /**
     * 交易数据块
     */
    @XStreamAlias("trans")
    private BocbTrans trans;
}
