package com.deloitte.services.srpmp.common.enums;

/**
 * Created by lixin on 13/06/2019.
 */
public enum VoucherAuditModeEnums {

    SELF_ONLY("selfOnly", "本单位审批"),
    TOP_ONLY("topOnly", "医科院审批"),
    ALL("all", "本单位及医科院共同审批");

    VoucherAuditModeEnums(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
