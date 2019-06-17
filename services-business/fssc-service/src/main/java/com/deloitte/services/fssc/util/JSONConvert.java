package com.deloitte.services.fssc.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-08
 * @Description :  JSON字符串转JSON对象
 * @Modified :
 */
public class JSONConvert {

    public static String[] removeArr = new String[]{"budFirstOpenFlg", "taskFirstOpenFlg", "taskDudFirstOpenFlg"};

    public static String[] jsonObjectArr = new String[]{"bothTopExpertPerson", "leadDept", "leadPerson"};

    public static String[] jsonArrArr = new String[]{"projectCategory", "achievementType", "performanceIndicatorDetail", "activeType", "performanceIndicatorDetail", "budgetDetail", "taskDecompositionList.budgetDetail"};

    public static void remove(JSONObject json) {

        for (int i = 0; i < removeArr.length; i++) {
            String key = removeArr[i];

            if (json.containsKey(key)) {
                json.remove(key);
            }
        }
    }

    public static void convertJsonObject(JSONObject json) {

        for (int i = 0; i < jsonObjectArr.length; i++) {
            String key = jsonObjectArr[i];

            if (json.containsKey(key) && JSONObject.isValid(json.getString(key))) {

                json.put(key, JSONObject.parseObject(json.getString(key)));

            }
        }
    }

    public static void convertJsonArr(JSONObject json) {

        for (int i = 0; i < jsonArrArr.length; i++) {
            String key = jsonArrArr[i];
            if (jsonArrArr[i].contains(".")) {
                String[] strArr = jsonArrArr[i].split("/.");
                if (JSONArray.isValid(json.getString(strArr[0]))) {
                    JSONArray tempArr = json.getJSONArray(strArr[0]);
                    if (tempArr.size() != 0) {
                        for (int j = 0; j < tempArr.size(); j++) {
                            tempArr.getJSONObject(j).put(strArr[1], JSONObject.parseObject(tempArr.getJSONObject(j).getString(strArr[1])));
                        }
                    }
                }
            } else {
                if (json.containsKey(key) && JSONArray.isValid(json.getString(key))) {
                    json.put(key, JSONArray.parseArray(json.getString(key)));
                }
            }
        }
    }

    public static void convertdata(JSONObject json) {

        Iterator<String> itr = json.keySet().iterator();
        while (itr.hasNext()) {
            String key = itr.next();
            String pattern = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";

            if(StringUtils.isNotBlank(json.getString(key))) {
                boolean isMatch = Pattern.matches(pattern, json.getString(key));
                if (isMatch) {
                    json.put(key, json.getString(key).replaceAll("T", " "));
                }
                if (JSONArray.isValidArray(json.getString(key))) {
                    JSONArray subArr = json.getJSONArray(key);
                    for (int i = 0; i < subArr.size(); i ++) {
                        if (JSONObject.isValidObject(subArr.getString(i))) {
                            JSONObject subJson = subArr.getJSONObject(i);
                            convertdata(subJson);
                        }
                    }
                }
                if (JSONObject.isValidObject(json.getString(key))) {
                    JSONObject subJson = json.getJSONObject(key);
                    convertdata(subJson);
                }
            }
        }
    }

    public static void convertJson(JSONObject json) {
        json.put("id", json.getString("id"));
        convertJsonArr(json);
        convertdata(json);
        convertJsonObject(json);
        remove(json);
    }
}
