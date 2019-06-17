package com.deloitte.platform.common.web.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @Author : jackliu
 * @Date : Create in 20:02 20/03/2019
 * @Description :
 * @Modified :
 */
public class GdcFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
       // template.header("Content-Type", "application/json");
        System.out.println("这是自定义请求拦截器");
    }
}
