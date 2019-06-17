package com.deloitte.services.project.common.enums;

public enum VoucherTypeEnums {

    PROJECT_APPLY_PROCESS("1","申报流程"),
    PROJECT_CANCLE_PROCESS("2","取消流程"),
    PROJECT_CHANGE_PROCESS("3","变更流程"),
    PROJECT_OPERATE_PROCESS("4","维护流程"),
    PROJECT_ADJUST_PROCESS("5","调整流程"),
    PROJECT_TEST("101","通用系统待阅"),
    REVIEW_READ("8001","项目评审书"),
    REPLY_READ("8002","项目批复书");

    VoucherTypeEnums(String code, String value){
        this.code = code;
        this.value = value;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
