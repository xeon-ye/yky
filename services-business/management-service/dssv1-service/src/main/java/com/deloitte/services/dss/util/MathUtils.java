package com.deloitte.services.dss.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

public class MathUtils {

    /**
     * 计算除法保留两位
     * @param divisor
     * @param dividend
     * @return
     */
    public static  Double div (Object  divisor,Object dividend){
        divisor  = null == divisor? NumberUtils.DOUBLE_ZERO:divisor;
        dividend  = null == dividend? NumberUtils.DOUBLE_ZERO:dividend;
        if (NumberUtils.DOUBLE_ZERO == NumberUtils.toDouble(dividend.toString())){
            return NumberUtils.DOUBLE_ZERO;
        }
        BigDecimal divisorBig = new BigDecimal(divisor.toString());
        BigDecimal dividendBig = new BigDecimal(dividend.toString());
        return divisorBig.divide(dividendBig,2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

//保留4位小数
    public static  Double div (Object  divisor,Object dividend,int scale){
        divisor  = null == divisor? NumberUtils.DOUBLE_ZERO:divisor;
        dividend  = null == dividend? NumberUtils.DOUBLE_ZERO:dividend;
        if (NumberUtils.DOUBLE_ZERO == NumberUtils.toDouble(dividend.toString())){
            return NumberUtils.DOUBLE_ZERO;
        }
        BigDecimal divisorBig = new BigDecimal(divisor.toString());
        BigDecimal dividendBig = new BigDecimal(dividend.toString());
        return divisorBig.divide(dividendBig,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static void main(String[] args){
       System.out.println(div(10.38994366,30.0,4));
    }
}
