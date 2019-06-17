package com.deloitte.services.srpmp.common;

import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by lixin on 24/03/2019.
 */
public class DateTimeFormatUtil {

    public static void formatInnerDateTime(JSONObject voJson, String patter, String... keys){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patter);
        for (String key: keys){
            voJson.put(key, voJson.getObject(key, LocalDateTime.class)==null?"":voJson.getObject(key, LocalDateTime.class).format(formatter));
        }
    }

}
