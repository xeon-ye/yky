package com.deloitte.platform.api.hr.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期格式化工具类 默认使用 "yyyy-MM-dd HH:mm:ss" 格式化日期.
 */
public class DateFarmatUtil {
    /**
     * 英文简写（默认）如：2017-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    /**
     * 英文全称 如：2017-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * 中文简写 如：2017年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    /**
     * 中文全称 如：2017年12月01日 23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    public static final String YYYY = "yyyy";
    public static final String MM = "MM";
    public static final String DD = "dd";

    /**
     * @param date  日期
     * @param pattern 日期格式化格式
     * @return
     * @描述 —— 时间对象转换成字符串
     */
    public static String dateToString(Date date, String pattern) {
        String strDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        strDate = sdf.format(date);
        return strDate;
    }

    /**
     * 使用用户格式提取字符串日期.
     * @param dateString 日期字符串
     * @param pattern 日期格式化格式
     * @return
     */
    public static Date stringToDate(String dateString, String pattern) {
        Date formateDate = null;
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        try {
            formateDate = format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
        return formateDate;
    }

    /**
     * @描述 —— 获得当前年份
     */
    public static String getNowYear() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY);
        return sdf.format(new Date());
    }

    /**
     * @描述 —— 获得当前月份
     */
    public static String getNowMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat(MM);
        return sdf.format(new Date());
    }

    /**
     * @描述 —— 获得当前日期中的日
     */
    public static String getNowDay(){
        SimpleDateFormat sdf = new SimpleDateFormat(DD);
        return sdf.format(new Date());
    }

    /**
     * 根据预设格式返回当前日期
     *
     * @return
     */
    public static String getNow() {
        return format(new Date());
    }

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return FORMAT_LONG;
    }

    /**
     * 根据用户格式返回当前日期
     *
     * @param format
     * @return
     */
    public static String getNow(String format) {
        return dateToString(new Date(), format);
    }

    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return dateToString(date, getDatePattern());
    }


    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate
     *            日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return stringToDate(strDate, getDatePattern());
    }


    /**
     * 字符串格式化LocalDateTime时间
     *
     * @param localDateTime  时间字符串（格式: 2019-01-25 12:22:22）
     * @return
     */
    public static LocalDateTime formatLocalDateTime(String localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_LONG);
       return LocalDateTime.parse(localDateTime, formatter);
    }

    /**
     * 字符串格式化LocalDateTime时间
     *
     * @param localDate  时间字符串（格式: 2019-01-25）
     * @return
     */
    public static LocalDate formatLocalDate(String localDate) {
        return LocalDate.parse(localDate);
    }

    /**
     *  获取当前时间后面几个月时间
     *
     * @param month  当前时间后几个月
     * @return
     */
    public static Date getDelayMonthTimeByCurrentTime(Integer month) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH,month);
       return c.getTime();
    }


    /**
     * LocalDate时间to DATE时间
     *
     * @param localDateTime
     * @return
     */
    public static Date LocalDateTimeToDate(LocalDate localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_SHORT);
        return stringToDate(localDateTime.format(formatter),FORMAT_SHORT);
    }

}
