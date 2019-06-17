package com.deloitte.platform.common.core.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * @Author : jackliu
 * @Date : Create in 22:48 10/02/2019
 * @Description :
 * @Modified :
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final String operator="";

    @Override
    public void insertFill(MetaObject metaObject) {
        //新增时填充的字段
        if(getFieldValByName("createTime",  metaObject) == null) {
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
        if(getFieldValByName("updateTime",  metaObject) == null) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
//        setFieldValByName("createBy", operator, metaObject);
//        setFieldValByName("updateBy", operator, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新时 需要填充字段
        //if(getFieldValByName("updateTime",  metaObject) == null) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        //}
//        setFieldValByName("updateBy", operator, metaObject);
    }
}
