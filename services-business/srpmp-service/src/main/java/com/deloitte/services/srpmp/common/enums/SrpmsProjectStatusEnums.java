package com.deloitte.services.srpmp.common.enums;

/**
 * @Description: 项目状态枚举
 * @Auther: Mr.Apeng
 * @Date: 2019.02.20
 */
public enum SrpmsProjectStatusEnums {

    PEROJECT_NOT_SUBMIT("0", "待申报"),
    PEROJECT_HAS_SUBMIT("10", "已申报"),
    PEROJECT_HAS_APPROVE_PASS("20", "待公示"),
//    PEROJECT_HAS_APPROVE_REFUSE("25", "已审批拒绝"),
    PEROJECT_HAS_PUBLICITY("30", "已公示"),
    PEROJECT_TASK_SUBMIT("40", "待立项"),
    PEROJECT_TASK_PASS("50", "已立项"),
    PEROJECT_SET_UP("60", "已批复"),
    PEROJECT_NOT_ACCEPT("70", "待验收"),
    PEROJECT_ACCEPT("80", "已验收"),
    PEROJECT_END("99", "已结束");

    SrpmsProjectStatusEnums(String code, String desc){
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
