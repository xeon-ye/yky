package com.deloitte.services.contract.common.config;
//import org.jasig.cas.client.authentication.AuthenticationFilter;
//import org.jasig.cas.client.util.AssertionThreadLocalFilter;
//import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
//import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 跨域过滤器
 */
@Configuration
@Component
public class CORSConfigure {
    @Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
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
}