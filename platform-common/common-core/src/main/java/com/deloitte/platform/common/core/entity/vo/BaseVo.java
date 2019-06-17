package com.deloitte.platform.common.core.entity.vo;

import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.PlatFormException;
import com.deloitte.platform.common.core.util.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseVo {

    /**
     * Po转化为Vo，返回结果
     */
    public  static Object PoToVo( Object po, Class voClass ) throws PlatFormException {
        try {
            return BeanUtils.beanTransaction( po, voClass);
        }catch(Exception e){
            throw new PlatFormException(PlatformErrorType.PO_TO_VO_ERROR,e.getMessage());
        }
    }

    /**
     * Po转化为Vo，返回结果
     */
    public  static Object PoCopyToVo( Object po, Class voClass ) throws PlatFormException {
        try {
            Object targetObj = voClass.newInstance();
            return BeanUtils.copyProperties( po, targetObj);
        }catch(Exception e){
            throw new PlatFormException(PlatformErrorType.PO_TO_VO_ERROR,e.getMessage());
        }
    }

    public static List PosToVos(List poList,Class voClass)throws PlatFormException {
        try {
            List retrunList = new ArrayList();
            if(poList.size()==0){
                return poList;
            }
            for(Object po:poList ){
                Object targetObj = voClass.newInstance();
                retrunList.add(BeanUtils.copyProperties( po, targetObj));
            }
            return retrunList;
        }catch(Exception e){
            throw new PlatFormException(PlatformErrorType.PO_TO_VO_ERROR,e.getMessage());
        }
    }
}
