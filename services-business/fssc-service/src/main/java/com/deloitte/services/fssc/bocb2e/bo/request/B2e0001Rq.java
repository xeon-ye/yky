package com.deloitte.services.fssc.bocb2e.bo.request;

import com.deloitte.services.fssc.util.DateUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Date;

@Data
@XStreamAlias("b2e0001-rq")
public class B2e0001Rq extends B2eRq{

    /**
     * 客户端日期时间 ，YYMMDDhhmmss
     */
    @XStreamAlias("custdt")
    private String custdt= DateUtil.dateToString(new Date(),"yyyyMMddHHmmss");

    /**
     * 登录密码 检查登录密码 ,当日输错5次 锁定；累计输 错15次锁定
     */
    @XStreamAlias("oprpwd")
    private String oprpwd;

}
