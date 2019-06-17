package com.deloitte.services.oa.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.common.core.exception.PlatFormException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.GdcPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 11:20 14/02/2019
 * @Description :
 * @Modified :
 */
@Slf4j
public class OaBeanUtils<T>{

    private List<T> targetList;
    private IPage<T> targetPage;
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
    public List<T> copyObjs(List sourceList, Class<T> clz) throws PlatFormException {
        try {
            targetList=new ArrayList<>();
            if(sourceList.size()==0){
                return sourceList;
            }
            for(Object source:sourceList ){

                targetList.add((T) copyProperties( source, clz.newInstance()));
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

                targetList.add((T) copyProperties( source, clz.newInstance()));
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
    public IPage<T> copyPageObjs(GdcPage sourcePage, Class<T> clz) throws PlatFormException {
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

                targetList.add((T)copyProperties( source, clz.newInstance()));
            }
            targetPage.setRecords(targetList);
            return targetPage;
        }catch (Exception e){
            throw new PlatFormException(PlatformErrorType.TYPE_CAST_ERROE,e.getMessage());
        }
    }

    public Object copyProperties(Object source,Object target) throws Exception {
        if (target == null || source == null) {
            return target;
        }
        PropertyDescriptor[] targetDesc = PropertyUtils.getPropertyDescriptors(target);

        for (int i = 0; i < targetDesc.length; i++) {
            Class targetType = targetDesc[i].getPropertyType();
            Class sourceType = PropertyUtils.getPropertyType(source, targetDesc[i].getName());
            //Long转String
            if(targetType != null&&sourceType != null&&targetType.equals(String.class)&&sourceType.equals(Long.class)){
                Object value = PropertyUtils.getProperty(source, targetDesc[i].getName());
                PropertyUtils.setProperty(target, targetDesc[i].getName(), String.valueOf(value));
            }
            //String转Long
            if(targetType != null&&sourceType != null&&targetType.equals(Long.class)&&sourceType.equals(String.class)){
                String value = (String)PropertyUtils.getProperty(source, targetDesc[i].getName());
                if(value!=null&&!"".equals(value)&&value.matches("^[-\\+]?[\\d]*$")){
                    PropertyUtils.setProperty(target, targetDesc[i].getName(), Long.valueOf(value));
                }
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
}
