package com.deloitte.services.srpmp.common.enums;

/**
 * Created by lixin on 16/03/2019.
 */
public enum VoucherStatusEnums {

    AUDITING("审核中"),
    APPROVED("已通过"),
    REJECT("已驳回"),
    RECALL("已撤回"),
    CLOSE("已关闭"),
    REFUSED("已拒绝");

    VoucherStatusEnums(String code){
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
