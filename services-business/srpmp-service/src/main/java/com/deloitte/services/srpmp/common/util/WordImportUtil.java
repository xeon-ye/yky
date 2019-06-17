package com.deloitte.services.srpmp.common.util;

/**
 * Created by lixin on 22/03/2019.
 */
public class WordImportUtil {

    //截取序号
    public static String subStringSerial(String title){
        if (title == null){
            return title;
        }
        if (title.contains(".")){
            return title.substring(title.indexOf(".") + 1);
        }
        return title;
    }

}
