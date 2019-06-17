package com.deloitte.services.isump.exception;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

@Getter
public enum IsumpErrorType implements IErrorType {


    NO_USER("82090001","没有找到用户！"),
    PASSWORD_ERROR("82090002","密码错误！"),
    // 此部分代码由手工填写 开始

    // ------------------------创新工程输入检查错误定义   结束----------------------------------------
    ;


    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    IsumpErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
