package com.deloitte.services.srpmp.common.fiter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lixin on 2019/4/16.
 */
@Slf4j
public class RequestIdFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            MDC.clear();
            String hashedReqId = generateReqId(request);
            MDC.put("REQID", hashedReqId);
            response.addHeader("REQID", hashedReqId);
        } catch (Exception e) {
            log.error("", e);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    protected String generateReqId(HttpServletRequest request) {
//        String ips = request.getHeader("x-forwarded-for");
//        String clientIp;
//        if (StringUtils.isNotEmpty(ips)) {
//            clientIp = ips.split(",")[0];
//        } else {
//            clientIp = request.getRemoteHost();
//        }
//        MDC.put("clientIp", clientIp);
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
