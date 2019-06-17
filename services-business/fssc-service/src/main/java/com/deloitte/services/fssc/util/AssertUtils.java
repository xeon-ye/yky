package com.deloitte.services.fssc.util;

import com.deloitte.platform.common.core.common.IErrorType;
import com.deloitte.services.fssc.handler.FSSCException;

public class AssertUtils {

    public static void asserts(boolean expression, IErrorType errorType){
        if (!expression){
            throw new FSSCException(errorType);
        }
    }

}
