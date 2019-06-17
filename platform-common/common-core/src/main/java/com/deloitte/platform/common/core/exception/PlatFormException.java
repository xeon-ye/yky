package com.deloitte.platform.common.core.exception;

import com.deloitte.platform.common.core.common.IErrorType;

/**
   * @Author : jackliu
   * @Date :  16:18 23/01/2019
   * @Description : 平台异常，
  * * */

public class PlatFormException extends BaseException {

    public PlatFormException(IErrorType errorType) {
        super(errorType);
    }
    public PlatFormException(IErrorType errorType, String message) {
        super(errorType,message);
    }
    public PlatFormException(IErrorType errorType, String message, Throwable cause) {
        super(errorType,message, cause);
    }

}
