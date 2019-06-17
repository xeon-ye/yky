package com.deloitte.services.srpmp.project.apply.util;

import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.srpmp.outline.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期转换工具类
 */
public class LocalDateUtils extends LocalDateTimeUtils {

    public static final String PARRERN_YMD = "yyyy年MM月dd日";
    public static final String PARRERN_Y = "yyyy年";
    public static final String PARRERN_YM = "yyyy年MM月";
    public static final String PARRERN_Y_M_D = "yyyy-MM-dd";
    public static final String PARRERN_Y_M = "yyyy-MM";
    public static final String PARRERN_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";


    public static final String NULL_STR = "";
    /**
     * 获取指定时间的指定格式
     */
    public static String format(LocalDateTime time, String pattern) {
        if  (null == time ){
            return  NULL_STR;
        }
        if (StringUtils.isBlank(pattern)){
            pattern = PARRERN_YMD;
        }
        try {
            String date = time.format(DateTimeFormatter.ofPattern(pattern));
            return date;
        }catch (Exception e){
            e.printStackTrace();
        }
        return NULL_STR;
    }

    /**
     * 指定字符串 转换为指定格式
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String parse(String localDateTime, String pattern) {
        if (StringUtils.isBlank(localDateTime)){
            return NULL_STR;
        }
        try {
            localDateTime = localDateTime.replaceAll("T"," ");
            LocalDateTime dateTime = LocalDateTime.parse(localDateTime,DateTimeFormatter.ofPattern(PARRERN_Y_M_D_H_M_S));
            String  date = format(dateTime, pattern);
            return date;
        }catch (Exception e){
            e.printStackTrace();
        }
        return NULL_STR;
    }

    /**
     * 根据出生日期获取年龄
     * @param birthDate
     * @return
     */
    public static  Integer  getAge (String birthDate){
        if (StringUtils.isBlank(birthDate)){
            return null;
        }

        try {
            birthDate = birthDate.replaceAll("T"," ");
            LocalDateTime birthTime = LocalDateTime.parse(birthDate,DateTimeFormatter.ofPattern(PARRERN_Y_M_D_H_M_S));
            int birthYear = birthTime.getYear();
            LocalDateTime nowTime  = LocalDateTime.now();
            int nowYear = nowTime.getYear();
            return nowYear - birthYear;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
