package com.deloitte.services.fssc.handler;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

@Getter
public class FSSCException extends RuntimeException {



    private IErrorType errorType;

    public FSSCException(IErrorType errorType){
        this.errorType = errorType;
    }

}
