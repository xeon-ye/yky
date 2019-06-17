package com.deloitte.services.push.exception;

import com.deloitte.platform.common.core.common.IErrorType;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import lombok.Getter;

@Getter
public enum PushErrorType implements IErrorType{
    BUFFER_FULL("1", "下发缓冲池已满"),
    CONNECTION_FAIL("2", "没有建立连接"),
    FLOW_OVER("3", "流量超过设定值"),
    SYSTEM_ERROR("-1", "系统异常")


    ;
    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    PushErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
