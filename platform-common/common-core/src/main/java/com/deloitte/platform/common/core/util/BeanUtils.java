package com.deloitte.platform.common.core.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.PlatFormException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author : jackliu
 * @Date : Create in 11:20 14/02/2019
 * @Description :
 * @Modified :
 */
@Slf4j
public class BeanUtils<T> {

    private List<T> targetList;

    private IPage<T>  targetPage;

    /**
     * @description : 将source对象转换成Class对象
     * @param source
     * @param clz
     * @return
     */

    public T copyObj(Object source,Class<T> clz)  throws PlatFormException {
        try {
            return  (T)copyProperties(source,clz.newInstance());
        }catch (Exception e){
            throw new PlatFormException(PlatformErrorType.TYPE_CAST_ERROE,e.getMessage());
        }

    }


    /**
     * @description : 将source对象列表转换成Class对象列表
     * @param sourceList
     * @param clz
     * @return
     */
    public List<T> copyObjs(List sourceList,Class<T> clz) throws PlatFormException {
        try {
            targetList=new ArrayList<>();
            if(sourceList.size()==0){
                return sourceList;
            }
            for(Object source:sourceList ){

                targetList.add((T)BeanUtils.copyProperties( source, clz.newInstance()));
            }
            return targetList;
        }catch (Exception e){
            throw new PlatFormException(PlatformErrorType.TYPE_CAST_ERROE,e.getMessage());
        }
    }

    /**
     * @description : 将source对象分页转换成Class对象分页
     * @param sourcePage
     * @param clz
     * @return
     */
    public IPage<T> copyPageObjs(IPage sourcePage,Class<T> clz) throws PlatFormException {
        try {
            targetPage=new Page<>();
            targetList=new ArrayList<>();
            targetPage.setPages(sourcePage.getPages());
            targetPage.setCurrent(sourcePage.getCurrent());
            targetPage.setSize(sourcePage.getSize());
            targetPage.setTotal(sourcePage.getTotal());

            List sourceList=sourcePage.getRecords();
           ;
            if(sourceList.size()==0){
                return targetPage;
            }
            for(Object source:sourceList ){

                targetList.add((T)BeanUtils.copyProperties( source, clz.newInstance()));
            }
            targetPage.setRecords(targetList);
            return targetPage;
        }catch (Exception e){
            throw new PlatFormException(PlatformErrorType.TYPE_CAST_ERROE,e.getMessage());
        }
    }

    /**
     * @description : 将source对象分页转换成Class对象分页
     * @param sourcePage
     * @param clz
     * @return
     */
    public IPage<T> copyPageObjs(GdcPage sourcePage,Class<T> clz) throws PlatFormException {
        try {
            targetPage=new Page<>();
            targetList=new ArrayList<>();
            targetPage.setPages(sourcePage.getTotalPage());
            targetPage.setCurrent(sourcePage.getPageNo());
            targetPage.setSize(sourcePage.getPageSize());
            targetPage.setTotal(sourcePage.getTotal());

            List sourceList=sourcePage.getContent();
            ;
            if(sourceList.size()==0){
                return targetPage;
            }
            for(Object source:sourceList ){

                targetList.add((T)BeanUtils.copyProperties( source, clz.newInstance()));
            }
            targetPage.setRecords(targetList);
            return targetPage;
        }catch (Exception e){
            throw new PlatFormException(PlatformErrorType.TYPE_CAST_ERROE,e.getMessage());
        }
    }

    /**
     * @description : 将source对象转换成target对象
     * @param source
     * @param target
     * @return
     */
    public static Object copyProperties(Object source,Object target) throws Exception {
        if (target == null || source == null) {
            return target;
        }
        PropertyDescriptor[] targetDesc = PropertyUtils.getPropertyDescriptors(target);

       for (int i = 0; i < targetDesc.length; i++) {
           Class targetType = targetDesc[i].getPropertyType();
           Class sourceType = PropertyUtils.getPropertyType(source, targetDesc[i].getName());
           //Long转
           if(targetType != null&&sourceType != null&&targetType.equals(String.class)&&sourceType.equals(Long.class)){
               Object value = PropertyUtils.getProperty(source, targetDesc[i].getName());
               PropertyUtils.setProperty(target, targetDesc[i].getName(), String.valueOf(value));
           }
           if (targetType != null && targetType.equals(sourceType) && !targetType.equals(Class.class)) {
               if (!Collection.class.isAssignableFrom(sourceType)) {
                   try {
                       Object value = PropertyUtils.getProperty(source, targetDesc[i].getName());
                       PropertyUtils.setProperty(target, targetDesc[i].getName(), value);
                   } catch (Exception ex) {
                   }
               }
           }
       }
       return target;

    }

    /**
     * @description : 将对象转换成objMap对象
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj)  {
        try {
            Map<String, Object> map = new HashMap<>();
            Class<?> clazz = obj.getClass();
            System.out.println(clazz);
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                map.put(fieldName, value);
            }
            return map;
        }catch (Exception ex){
            log.error(ex.getMessage(),ex.getCause());
            throw new PlatFormException(PlatformErrorType.OBJ_TO_MAP_ERROR,ex.getMessage());
        }
    }


    public static Object beanTransaction( Object sourceObj, Class targetClass) throws Exception {
        Object targetObj = targetClass.newInstance();
        if(sourceObj==null){
            return targetObj;
        }
        Class sourceClass = sourceObj.getClass();
        // 取得form对象和父类的所有属性
        Field[] sourceFields = sourceClass.getDeclaredFields();
        Field[] superSourceFields = sourceClass.getSuperclass().getDeclaredFields();
        sourceFields = (Field[]) mergeArray(sourceFields, superSourceFields);

        // 遍历拼接target的set方法字段表示
        for (Field f : sourceFields) {
            String fieldName = f.getName();
            //取得当前get，set字符串表达形式
            String poSetMethodName = "set" + firstToBig(fieldName);
            String sourceGetMethodName = "get" + firstToBig(fieldName);

            Method tagetSetMethod = null;
            try {
                tagetSetMethod = targetClass.getMethod(poSetMethodName, f.getType());
            } catch (NoSuchMethodException e) {
                //如果不存在此方法跳过，
                continue;
            }//取得source对象的get方法
            Method sourceGetMethod = sourceClass.getMethod(sourceGetMethodName, null);
            // 将source对象的属性值set到target对象中去
            tagetSetMethod.invoke(targetObj, sourceGetMethod.invoke(sourceObj, null));
        }
        return targetObj;
    }


    /**
     * 合并数组
     * @param a
     * @param b
     * @return
     */
    public static Object[] mergeArray(Object[] a,Object[] b) {
        Object[] c = Arrays.copyOf(a, a.length+b.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     * 首字母大写
     *
     * @param fieldName
     * @return
     */
    public static String firstToBig(String fieldName) {
        if (fieldName != null && fieldName != "") {
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
        return fieldName;
    }
}
