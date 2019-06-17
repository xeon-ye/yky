package com.deloitte.services.srpmp.common.enums;

/**
 * @Description: 项目状态枚举
 * @Auther: Mr.Apeng
 * @Date: 2019.02.20
 */
public enum SrpmsProjectUpdateStatusEnums {

    PEROJECT_NOT_SUBMIT("0", "未提交"),
    PEROJECT_HAS_SUBMIT("10", "已提交"),
    PEROJECT_HAS_APPROVE_PASS("20", "审批通过"),
    PEROJECT_APPROVE_NO("30", "审批拒绝"),
	PEROJECT_APPROVE_REDO("40", "审批驳回"),
	PEROJECT_APPROVE_CLOSE("50", "已关闭");


    SrpmsProjectUpdateStatusEnums(String code, String desc){
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
