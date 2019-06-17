package com.deloitte.platform.common.web.feign;

import feign.Retryer;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class FeignMultipartSupportConfig {

    @Bean
    @Scope("prototype")
    public Encoder multipartFormEncoder() {
        return new SpringMultipartEncoder();
    }

    @Bean
    Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }
}
