package com.deloitte.services.srpmp.common.enums;

/**
 * 合作人员合作方式枚举
 * Created by lixin on 20/02/2019.
 */
public enum PersonJoinWayEnums {

    APPLY_MAIN_MEMBER("01", "项目主要参与人员信息"),
    APPLY_OTHER_PROJECT("02", "承担其它相关国家科技计划"),
    APPLY_UNIT_PERSON("03", "固定科技人员名单"),
    APPLY_UNIT_PROJECT("04", "近5年承担的重要科研项目清单"),
    TASK_UNIT_PERSON("05", "固定科技人员名单"),
    TASK_UNIT_PROJECT("06", "近5年承担的重要科研项目清单"),

    TASK_MAIN_MEMBER("11","任务书-主要参与人员");
    PersonJoinWayEnums(String code, String desc){
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
    }}
