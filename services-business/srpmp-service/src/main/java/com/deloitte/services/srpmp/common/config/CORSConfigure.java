package com.deloitte.services.srpmp.common.config;

//import org.jasig.cas.client.authentication.AuthenticationFilter;
//import org.jasig.cas.client.util.AssertionThreadLocalFilter;
//import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
//import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import com.deloitte.services.srpmp.common.fiter.RequestIdFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 跨域过滤器
 */
@Configuration
@Component
public class CORSConfigure {
    @Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        System.out.println("1-----------------corsFilter--------------------------");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 可以自行筛选
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new CorsFilter(source));
        authenticationFilter.setOrder(0);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }

    /**
     * 日志添加唯一请求ID
     * @return
     */
    @Bean
    public FilterRegistrationBean requestFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new RequestIdFilter());
        registration.setName("requestFilter");
        registration.setOrder(Integer.MAX_VALUE - 2);
        return registration;
    }

//    @Bean
//    public FilterRegistrationBean authenticationFilterRegistrationBean() {
//        System.out.println("3-----------------AuthenticationFilter--------------------------");
//        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
//        authenticationFilter.setFilter(new AuthenticationFilter());
//        Map<String, String> initParameters = new HashMap<>();
//        initParameters.put("casServerLoginUrl", "http://124.17.100.180:9080/isump/cas/login");
//        initParameters.put("serverName", "http://124.17.96.101:8401");
//        authenticationFilter.setInitParameters(initParameters);
//        authenticationFilter.setOrder(3);
//        List<String> urlPatterns = new ArrayList<>();
//        urlPatterns.add("/*");// 设置匹配的url
//        authenticationFilter.setUrlPatterns(urlPatterns);
//        return authenticationFilter;
//    }
//
//    @Bean
//    public FilterRegistrationBean ValidationFilterRegistrationBean(){
//        System.out.println("2-----------------Cas20ProxyReceivingTicketValidationFilter--------------------------");
//        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
//        authenticationFilter.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
//        Map<String, String> initParameters = new HashMap<String, String>();
//        initParameters.put("casServerUrlPrefix", "http://124.17.100.180:9080/isump");
//        initParameters.put("serverName", "http://124.17.96.101:8401");
//        authenticationFilter.setInitParameters(initParameters);
//        authenticationFilter.setOrder(2);
//        List<String> urlPatterns = new ArrayList<String>();
//        urlPatterns.add("/*");// 设置匹配的url
//        authenticationFilter.setUrlPatterns(urlPatterns);
//        return authenticationFilter;
//    }
//
//    @Bean
//    public FilterRegistrationBean casHttpServletRequestWrapperFilter(){
//        System.out.println("4-----------------HttpServletRequestWrapperFilter--------------------------");
//        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
//        authenticationFilter.setFilter(new HttpServletRequestWrapperFilter());
//        authenticationFilter.setOrder(4);
//        List<String> urlPatterns = new ArrayList<String>();
//        urlPatterns.add("/*");// 设置匹配的url
//        authenticationFilter.setUrlPatterns(urlPatterns);
//        return authenticationFilter;
//    }
//
//    @Bean
//    public FilterRegistrationBean casAssertionThreadLocalFilter(){
//        System.out.println("5-----------------AssertionThreadLocalFilter--------------------------");
//        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
//        authenticationFilter.setFilter(new AssertionThreadLocalFilter());
//        authenticationFilter.setOrder(5);
//        List<String> urlPatterns = new ArrayList<String>();
//        urlPatterns.add("/*");// 设置匹配的url
//        authenticationFilter.setUrlPatterns(urlPatterns);
//        return authenticationFilter;
//    }
}