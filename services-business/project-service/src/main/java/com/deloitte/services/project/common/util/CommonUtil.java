package com.deloitte.services.project.common.util;

import java.lang.reflect.Field;
import java.util.*;

public class CommonUtil {

    /**
     * 对象转map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj){
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Collection 转List 集合
     * @param collection
     * @return
     */
    public static List collToList(Collection collection){
        List list;
        if(collection instanceof List){
            list = (List)collection;
        }else {
            list = new ArrayList(collection);
        }
        return list;
    }
}
