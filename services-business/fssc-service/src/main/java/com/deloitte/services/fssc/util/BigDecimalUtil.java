package com.deloitte.services.fssc.util;

import java.math.BigDecimal;

public class BigDecimalUtil {


    public static BigDecimal convert(BigDecimal bigDecimal){
        if(bigDecimal==null){
            return new BigDecimal("0");
        }
        return bigDecimal;
    }


    public static BigDecimal toDecimal(Double d){
        if(d==null){
            return BigDecimal.ZERO;
        }
        String s = d.toString();
        return new BigDecimal(s);
    }

}
