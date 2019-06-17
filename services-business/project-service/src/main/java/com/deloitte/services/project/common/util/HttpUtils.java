package com.deloitte.services.project.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtils {

    /**
     * 获取当前线程的request
     * @return
     */
    public static HttpServletRequest currentRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attr.getRequest();
    }

    /**
     * 获取当前线程的response
     * @return
     */
    public static HttpServletResponse currentResponse(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attr.getResponse();
    }

}
