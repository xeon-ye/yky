package com.deloitte.services.contract.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-08
 * @Description :  JSON字符串转JSON对象
 * @Modified :
 */
public class JSONConvert {

    public static String[] removeArr = new String[]{"budFirstOpenFlg", "taskFirstOpenFlg", "taskDudFirstOpenFlg"};

    public static String[] jsonObjectArr = new String[]{"bothTopExpertPerson", "leadDept", "leadPerson"};

    public static String[] jsonArrArr = new String[]{"projectCategory", "achievementType", "performanceIndicatorDetail", "activeType", "budgetDetail", "budgetPreYearList","budgetPreYearList_budgetDetail", "performanceIndicatorDetail",  "taskDecompositionList_budgetDetail", "taskDecompositionList_joinPersonName"};

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
            if (jsonArrArr[i].contains("_")) {
                String[] strArr = jsonArrArr[i].split("_");
                JSONArray tempArr = json.getJSONArray(strArr[0]);
                JSONArray relustArr = new JSONArray();
                if (tempArr != null && tempArr.size() != 0) {
                    for (int j = 0; j < tempArr.size(); j++) {
                        JSONObject jsonItem = tempArr.getJSONObject(j);
                        jsonItem.put(strArr[1], (jsonItem.getJSONArray(strArr[1])));
                        relustArr.add(jsonItem);
                    }
                }
                json.put(strArr[0],relustArr);
            } else {
                if (json.containsKey(key) && JSONArray.isValidArray(json.getString(key))) {
                    json.put(key, json.getJSONArray(key));
                }
            }
        }
    }

    public static void convertdate(JSONObject json) {

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
                            convertdate(subJson);
                        }
                    }
                }
                if (JSONObject.isValidObject(json.getString(key))) {
                    JSONObject subJson = json.getJSONObject(key);
                    convertdate(subJson);
                }
            }
        }
    }

    public static void convertJson(JSONObject json) {
        json.put("id", json.getString("id"));
        convertJsonArr(json);
        convertdate(json);
        convertJsonObject(json);
        remove(json);
    }
}
