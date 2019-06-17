package com.deloitte.services.srpmp.outline.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 日期工具类
 * @功能描述: TODO
 * @创建人 ：hai.jiang
 * @创建时间：2018年8月16日 上午8:55:56
 */
public class DateUtil {
    // Grace style
    public static final String PATTERN_GRACE = "yyyy/MM/dd HH:mm:ss";
    public static final String PATTERN_GRACE_NORMAL = "yyyy/MM/dd HH:mm";
    public static final String PATTERN_GRACE_SIMPLE = "yyyy/MM/dd";

    // Classical style
    public static final String PATTERN_CLASSICAL = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_CLASSICAL_SIMPLE = "yyyy-MM-dd";
    public static final String PATTERN_CLASSICAL_MONTH = "yyyy-MM";
    public static final String PATTERN_CHINA_CLASSICAL_MONTH = "yyyy年MM月";
    public static final String PATTERN_FOR_CODE = "yyyyMMdd";

    private final static SimpleDateFormat df_yyyy_MM_dd=new SimpleDateFormat("yyyy-MM-dd");

    private final static SimpleDateFormat YYYY_MM_DD_HH_mm_ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final static SimpleDateFormat YYYY_MM_DD_HH_mm_ss_SSS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");


    private DateUtil() {
        // Cannot be instantiated
    }


    public static Date parse(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseStartDate(String text){
        text += " 00:00:00";
        return parse(text,PATTERN_CLASSICAL);
    }

    public static Date parseEndDate(String text){
        text += " 23:59:59";
        return parse(text,PATTERN_CLASSICAL);
    }

    /**
     * str 转 localDateTime
     *
     * @param str
     * @return
     */
    public static LocalDateTime chinaToLocalDateTime (String str,String pattern) {
        if (StringUtils.isBlank(str)){
            return null;
        }
        str = StringUtils.trim(str);
        Date date = parse(str,pattern);
        if (null == date){
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }


    /**
     * str 转 localDateTime
     *
     * @param str
     * @return
     */
    public static LocalDateTime strToLocalDateTime (String str) {
        if (StringUtils.isBlank(str)){
            return null;
        }
        Date date = parseStartDate(str);
        if (null == date){
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    /**
     * str 转 localDateTime
     *
     * @param str
     * @return
     */
    public static LocalDateTime strToLocalDateTimeEnd (String str) {
        if (StringUtils.isBlank(str)){
            return null;
        }
        Date date = parseEndDate(str);
        if (null == date){
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    public static String strDateToStr (String str){
        Date date = parse(str, PATTERN_CLASSICAL_SIMPLE);
        SimpleDateFormat format = new SimpleDateFormat(PATTERN_CLASSICAL_SIMPLE);
        return format.format(date);
    }

    public static LocalDateTime strTimeZoneToLocalDateTime (String str) throws ParseException {
        str = str.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date date = format.parse(str);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    public static LocalDateTime strTimeToLocalDateTime (String str) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = format.parse(str);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws ParseException {
       System.out.println(DateUtil.strToLocalDateTime("2019-01"));
      /*  String str = "2019-02-14T00:00:00.";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(str);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        System.out.println("LocalDateTime转成String类型的时间："+localDateTime);*/
    }
 
}