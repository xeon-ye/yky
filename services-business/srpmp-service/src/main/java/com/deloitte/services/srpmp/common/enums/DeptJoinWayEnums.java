package com.deloitte.services.srpmp.common.enums;

/**
 * 合作单位合作方式枚举
 * Created by lixin on 20/02/2019.
 */
public  enum DeptJoinWayEnums {
    APPLY_INNOVATE_COPPDEPT("01", "申请书-创新工程-国际合作单位信息"),
    APPLY_INNOVATE_UNIT("02", "申请书-创新工程-联合申请单位信息"),
    TASK_INNOVATE_COPPDEPT("11", "任务书-创新工程-国际合作单位信息"),
    TASK_INNOVATE_UNIT("12", "任务书-创新工程-联合申请单位"),
    ;

    DeptJoinWayEnums(String code, String desc){
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
