package com.deloitte.services.srpmp.common.util;

import org.apache.tomcat.jni.Local;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import static org.apache.commons.lang3.time.DateUtils.parseDate;
import static org.apache.poi.hssf.usermodel.HSSFDataFormat.getBuiltinFormat;

/**
 * Created by lixin on 05/03/2019.
 */
public class DateParseUtil {

//    private static Date ff;

    private static final String [] partterns2 = new String[6];

    static {
        partterns2[0]="HH:mm:ss";
        partterns2[1]="HH:mm";
        partterns2[2]="HH时mm分ss秒";
        partterns2[3]="mm分ss秒";
        partterns2[4]="HH时mm分";
        partterns2[5]="HH时mm分ss";

    }
    private static final String[] partterns1_C =new  String[18];
    static{
        partterns1_C[0] = "yyyy/MM/dd";
        partterns1_C[1] = "yyyy-MM-dd";
        partterns1_C[2] = "MM/dd/yyyy";
        partterns1_C[3] = "M/dd";
        partterns1_C[4] = "yyyyMMdd";
        partterns1_C[5] = "yyyy年MM月dd日";
        partterns1_C[6] = "yyyy年M月dd日";
        partterns1_C[7] = "yyyy年M月d日";
        partterns1_C[8] = "yyyy年MM月d日";
        partterns1_C[9] = "MM月dd日";
        partterns1_C[10] = "M月dd日";
        partterns1_C[11] = "MM月d日";
        partterns1_C[12] = "M月d日";
        partterns1_C[13] = "yyyy年MM月";
        partterns1_C[14] = "yyyy年M月";
        partterns1_C[15] = "yyyyMM";
        partterns1_C[16] = "yyyy.MM.dd";
        partterns1_C[17] = "yyyy.MM";
    }
    private  static final String[] partterns1_E=new String[11];
    static{
        partterns1_E[0] = "dd-MMM-yy";
        partterns1_E[1] = "dd-MMM";
        partterns1_E[2] = "dd-MMM-yyyy";
        partterns1_E[3] = "d-MMM";
        partterns1_E[4] = "d-MMM-yyyy";
        partterns1_E[5] = "d-MMM-yy";
        partterns1_E[6] = "MMM d";
        partterns1_E[7] = "MMM dd";
        partterns1_E[8] = "MMM-dd";
        partterns1_E[9] = "MMM-d";
        partterns1_E[10] = "MMM d,yyyy";
    }


    private static String TimeConvert(String str2) throws ParseException {

        //--------------------中文日期---------

        Date ff = parseDate(str2, partterns2);

        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        return sdf2.format(ff);
    }

    private static String DateConvert(String str1) throws ParseException {

        Date ff;
        if (str1.matches(".*\\p{Alpha}.*")) {
            ff = parseDate(str1, Locale.ENGLISH, partterns1_E);
        } else {
            ff =  parseDate(str1, partterns1_C);
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        return sdf2.format(ff);
    }


    private static String getDate(String date) throws ParseException {
        String FinalString =null;
        if (date.length() <= 10) {
            if (date.contains(":") || (date.contains("时") || date.contains("分") || date.contains("秒"))) {
                FinalString = TimeConvert(date);
            }
            if (date.contains("年") || date.contains("月") || date.contains("日")||date.matches(".*\\p{Alpha}.*")||date.contains("/")||date.contains(".")) {
                FinalString = DateConvert(date);
            }
            else{
                FinalString = DateConvert(date);
            }
        } else {
            String str1 = date.split(" ")[0];
            String str2 = date.substring(date.indexOf(" ") + 1);
            String result1 = DateConvert(str1);
            String result2 = TimeConvert(str2);
            FinalString = result1 + " " + result2;
        }
        return FinalString;
    }


    public static void main(String args[]) throws ParseException {
//        String date[]={"2008/07/30",
//                "20080730 05:07:30",
//                "2008年7月30日 05:07:30",
//                "2008年7月 05:06:30",
//                "7月30日 05:06:30",
//                "200807 05:06:30",
//                "2008.07.30 05:06:30",
//                "2008.07 05:06:30",
//                "30-Jun-08 05:06:30",
//                "30-Jun-2008 05:06:30",
//                "30-Jun 05:06:30",
//                "199708"
//        };
//        for (String n:date){
//            System.out.println(getDate(n));
//        }
        System.out.println(parseLocalDateTime("19980801"));
        System.out.println(parseLocalDateTime("1998-08-01"));
    }

    public static LocalDateTime parseLocalDateTime(String str){
        if (str == null) return null;
        LocalDateTime ldt = null;
        try {
            String dateStr = getDate(str);
            if (!dateStr.contains(":")){
                dateStr += " 00:00:00";
            }
            ldt = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return ldt;
    }

}
