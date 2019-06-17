/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deloitte.services.srpmp.outline.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :  工具类
 * @Modified :
 */
public class CommonUtil {

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9_\\.-]+@[a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+$";

    /**
     *
     * @param value
     * @param length
     * @return
     */
    public static boolean maxLengthCheck(String value, int length) {
        return value.length() > length;
    }

    public static int getIntegerValue(Object data) {
        if (data == null || data == "") {
            return 0;
        } else {
            return Integer.valueOf(data.toString());
        }
    }

    public static double getDoubleValue(Object data) {
        if (data == null || data == "") {
            return 0;
        } else {
            return Double.valueOf(data.toString());
        }
    }

    public static long getLongValue(Object data) {
        if (data == null || data == "") {
            return 0;
        } else {
            return Long.valueOf(data.toString());
        }
    }

    /**
     * 对象转化为string类型
     *
     * @param obj
     * @return
     */
    public static String objectToString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }


    /**
     * 有效日期验证
     *
     * @param date
     * @return
     */
    public static boolean isValidDate(String date) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(date);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 数字
     *
     * @param data
     * @return
     */
    public static boolean isValidDouble(String data) {
       try {
           Double.valueOf(data);
           return false;
       } catch (Exception e) {
           return true;
       }
    }
    /**
     * 手机号码有效
     *
     * @param mailAddress
     * @return
     */
    public static boolean isValidMobile(String mailAddress) {
        Pattern p = Pattern.compile(REGEX_MOBILE);
        Matcher m = p.matcher(mailAddress);
        return m.matches();
    }

    /**
     * 邮箱地址有效
     *
     * @param mailAddress
     * @return
     */
    public static boolean isValidMailAddress(String mailAddress) {
        Pattern p = Pattern.compile(REGEX_EMAIL);
        Matcher m = p.matcher(mailAddress);
        return m.matches();
    }

    /**
     * 正则去掉首尾空格
     *
     * @param str
     * @return
     */
    public static String deleteStringSpace(String str){
        Pattern pt=Pattern.compile("^\\s*|\\s*$");
        Matcher mt=pt.matcher(str);
        str=mt.replaceAll("");
        return str;
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * copy文件
     *
     * @param srcFileName
     * @param destFileName
     * @param overlay
     * @return
     */
    public static boolean fileChannelCopy(String srcFileName, String destFileName, boolean overlay) {
        File srcFile = new File(srcFileName);
        if (!srcFile.exists()) {
            return false;
        } else if (!srcFile.isFile()) {
            return false;
        }

        File destFile = new File(destFileName);
        if (destFile.exists()) {
            if (overlay) {
                new File(destFileName).delete();
            }
        } else {
            if (!destFile.getParentFile().exists()) {
                if (!destFile.getParentFile().mkdirs()) {
                    return false;
                }
            }
        }

        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(srcFile);
            outStream = new FileOutputStream(destFile);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (in != null) {
                    in.close();
                }
                if (outStream != null) {
                    outStream.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                return false;
            }
        }
        return true;
    }

    public static void setBorder(Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
    }

    public static JSONObject getEmptyRelust() {
        JSONObject json = new JSONObject();
        json.put("current", 1);
        json.put("pages", 1);
        json.put("records", new JSONArray());
        json.put("total", 0);
        json.put("tableFlag", 2);
        return json;
    }

}
