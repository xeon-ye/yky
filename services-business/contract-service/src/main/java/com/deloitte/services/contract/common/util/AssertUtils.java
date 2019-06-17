package com.deloitte.services.contract.common.util;

import com.deloitte.platform.common.core.common.IErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.handler.ContractException;

public class AssertUtils {

    public static void asserts(boolean expression, ContractErrorType errorType){
        if (expression){
            throw new ServiceException(errorType);
        }
    }

}
