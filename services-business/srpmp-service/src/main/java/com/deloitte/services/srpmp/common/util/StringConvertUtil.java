package com.deloitte.services.srpmp.common.util;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.CollectionUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串转换工具
 */
public class StringConvertUtil {

    public static char[] numArr1 = new char[]{'0','1','2','3','4','5','6','7','8','9'};

    public static char[] numArr2 = new char[]{'〇','一','二','三','四','五','六','七','八','九'};

    public static Integer  convert (String content){
        if (StringUtils.isBlank(content)){
            return NumberUtils.INTEGER_ZERO;
        }
        String regEX = "[^0-9]";
        Pattern pattern  = Pattern.compile(regEX);
        Matcher matcher = pattern.matcher(content);
        String trim = matcher.replaceAll("").trim();
        return NumberUtils.toInt(trim);
    }

    public static String  convertNumToWord (String content){
        StringBuffer sb = new StringBuffer();
        char[] charArr = content.toCharArray();
        for (int i = 0; i < charArr.length; i ++) {

            for (int j = 0; j < numArr1.length; j ++) {
                if (charArr[i] == numArr1[j]) {
                    sb.append(numArr2[j]);
                }
            }
        }
        return sb.toString();
    }


    /**
     * 为空返回“”
     * @param content
     * @return
     */
    public static String  convertNull (String content){
        if (StringUtils.isBlank(content)){
            return null;
        }
        return content;
    }



    public static void main(String[] args) {
        System.out.println(convert("2019 jsj 888 skks"));
    }
}
