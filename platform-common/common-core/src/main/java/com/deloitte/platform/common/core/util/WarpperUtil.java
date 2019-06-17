package com.deloitte.platform.common.core.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.common.core.exception.PlatFormException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author : jackliu
 * @Date : Create in 17:12 10/04/2019
 * @Description :
 * @Modified :
 */
public class WarpperUtil {

    public  static QueryWrapper constructWrapper(Map<String,String[]> wrapperMap){
        QueryWrapper queryWrapper = new QueryWrapper();

        for(Map.Entry<String,String[]> entry:wrapperMap.entrySet()){
             String filed=entry.getKey();
             String[] tempArray=entry.getValue();
             if(tempArray.length!=2){
                 throw new PlatFormException(PlatformErrorType.WARPPER_PARAM_ERROR);
             }
             String condition=tempArray[0];
             String value=tempArray[1];
             if(StringUtils.isEmpty(value)){
                 continue; }
                 if(value.contains(",")){
                   Object[] values=stringToObject(value.split(","));
                   switch (condition) {
                       case "in":queryWrapper.in(filed,values);break;
                       case "notIn":queryWrapper.notIn(filed,values);break;
                       case "between":
                           if(values.length!=2){
                               throw new PlatFormException(PlatformErrorType.WARPPER_BETWEEN_ERROR); }queryWrapper.between(filed,values[0],values[1]);
                           break;
                       default:break;}
                 }else{
                    switch (condition) {
                       case "eq": queryWrapper.eq(filed,value);break;
                       case "like": queryWrapper.like(filed,value);break;
                       case "le": queryWrapper.le(filed,value);break;
                       case "ge": queryWrapper.ge(filed,value);break;
                       case "lt": queryWrapper.lt(filed,value);break;
                       case "gt": queryWrapper.gt(filed,value);break;
                       default:break;
                    }
                 }
        }
        queryWrapper.orderByDesc("CREATE_TIME");
        return  queryWrapper;
    }

    //格式转换，现在只提供字符串转时间的方法
    private static Object[] stringToObject(String[] strs){
        ArrayList<Object> list =new ArrayList<>();
        String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";
        for(String str:strs){
            if(Pattern.compile(regex).matcher(str).matches()){
                list.add(LocalDateTimeUtils.convertStringToLDT(str));
            }else{
                list.add(str);
            }
        }
        return list.toArray();
    }


}
