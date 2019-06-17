package com.deloitte.services.contract.common.enums;

/**
 * Created by lixin on 16/03/2019.
 */
public enum VoucherStatusEnums {

    AUDITING("1","审核中"),
    BACK("2","退回"),
    REFUSED("3","已拒绝"),
    FINISH("4","审批通过"),
    STOP("5","作废"),
    ROLLBACK("6","撤回"),
    READ("7","待阅已阅");

    VoucherStatusEnums(String code,String value){
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
