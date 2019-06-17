package com.deloitte.services.srpmp.common.enums;

/**
 * 任务类型枚举
 * Created by lixin on 20/02/2019.
 */
public enum TaskCategoryEnums {

    APPLY_INNOVATE_TASK_YEAR("01", "创新工程-项目的年度任务、考核指标和时间节点"),
    APPLY_INNOVATE_TASK_DECOMPOSITION("02", "创新工程-重大创新-项目任务分解情况和各任务之间的逻辑关系图示"),
    APPLY_INNOVATE_TASK_JOINT_UNIT("03", "创新工程-重大创新-联合申报单位任务分工情况"),

    TASK_INNOVATE_TASK_YEAR("11", "任务书-创新工程-年度任务"),
    TASK_INNOVATE_TASK_DECOMPOSITION("12", "任务书-创新工程-任务分解"),

    TASK_INNOVATE_TRAN_LONG("20", "任务书-横纵项项目-任务分解"),

    APPLY_INNOVATE_BUDGET_DEPT("31", "创新工程-申请书-分单位预算"),
    APPLY_INNOVATE_BUDGET_ALL("32", "创新工程-申请书-总预算"),
    
    TASK_INNOVATE_BUDGET_DEPT("41", "创新工程-任务书-分单位预算"),
    TASK_INNOVATE_BUDGET_ALL("42", "创新工程-任务书-总预算"),

    ;

    TaskCategoryEnums(String code, String desc){
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
