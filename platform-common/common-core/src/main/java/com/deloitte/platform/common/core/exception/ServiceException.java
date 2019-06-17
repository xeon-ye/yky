package com.deloitte.platform.common.core.exception;

import com.deloitte.platform.common.core.common.IErrorType;

/**
 /**
 * @Author : jackliu
 * @Date :  16:18 23/01/2019
 * @Description : 业务服务异常， 针对服务的业务异常 ，通用的businessException
  * */

public class ServiceException extends BaseException {

    public ServiceException(IErrorType errorType) {
        super(errorType);
    }
    public ServiceException(IErrorType errorType, String message) {
        super(errorType,message);
    }
    public ServiceException(IErrorType errorType, String message, Throwable cause) {
        super(errorType,message, cause);
    }

}
