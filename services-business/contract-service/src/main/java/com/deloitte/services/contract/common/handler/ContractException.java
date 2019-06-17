package com.deloitte.services.contract.common.handler;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

@Getter
public class ContractException extends RuntimeException {



    private IErrorType errorType;

    public ContractException(IErrorType errorType){
        this.errorType = errorType;
    }

}
