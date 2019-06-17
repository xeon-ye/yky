package com.deloitte.services.demomybatiesauto.constant;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

/**
 * @Author : jackliu
 * @Date : Create in 14:55 17/02/2019
 * @Description :
 * @Modified :
 */

@Getter
public enum DemoErrorType implements IErrorType {



    DEMO_RUN_ERROR("89030001","(这是一个错误类示例)示例演示失败")



    ;
    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    DemoErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
