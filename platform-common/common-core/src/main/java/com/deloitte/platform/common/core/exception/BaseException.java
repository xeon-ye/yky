package com.deloitte.platform.common.core.exception;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    /**
     * 异常对应的错误类型
     */
    private IErrorType errorType;

    /**
     * 默认是系统异常
     */
    public BaseException() {
        this.errorType = PlatformErrorType.SYSTEM_ERROR;
    }

    public BaseException(IErrorType errorType) {
        this.errorType = errorType;
    }

    public BaseException(IErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BaseException(IErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }
}
