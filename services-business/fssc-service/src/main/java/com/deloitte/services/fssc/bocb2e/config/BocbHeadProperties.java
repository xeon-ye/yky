package com.deloitte.services.fssc.bocb2e.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bocb2e.header")
public class BocbHeadProperties {

    /**
     * 代表一台企业前置机 企业前置机id Java代码里面默认 yml里面配置真实的
     */
    private String termid="EC700000001";
    /**
     * 企业操作员代码 Java代码里面默认 yml里面配置真实的
     */
    private String cusopr="yuanyuan";
    /**
     * 通知次数 Java代码里面默认 yml里面配置真实的
     */
    private String pushnum="1";
}
