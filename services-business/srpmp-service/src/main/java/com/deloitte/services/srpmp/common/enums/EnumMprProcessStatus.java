package com.deloitte.services.srpmp.common.enums;

/**
 * Author:LIJUN
 * Date:02/04/2019
 * Description: 中期绩效考评流程状态
 */
public enum EnumMprProcessStatus {

    MPR_NOT_SUBMIT("0", "未提交"),
    MPR_AUDIT("10", "审核中"),
    MPR_REFUSE("20", "已拒绝"),
    MPR_REJECT("30", "已驳回"),
    MPR_APPROVED("40", "已同意"),
    MPR_CLOSE("50", "已关闭");

    EnumMprProcessStatus(String code, String desc){
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
