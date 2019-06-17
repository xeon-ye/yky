package com.deloitte.services.srpmp.common.enums;

/**
 * 预算类型枚举
 * Created by lixin on 20/02/2019.
 */
public enum BudgetCategoryEnums {

    APPLY_INNOVATE_BUDGET_YEAR("0101", "创新工程-申请书-预算明细"),
    APPLY_INNOVATE_BUDGET_DEVICE("0102", "创新工程-申请书-设备预算明细"),
    APPLY_INNOVATE_BUDGET_TEST("0103", "创新工程-申请书-测试预算明细"),
    TASK_INNOVATE_BUDGET_YEAR("0111", "创新工程-任务书-预算明细"),
    TASK_INNOVATE_BUDGET_DEVICE("0112", "创新工程-任务书-设备预算明细"),
    TASK_INNOVATE_BUDGET_TEST("0113", "创新工程-任务书-测试预算明细"),
    TASK_YEAR_PLAN_DETAIL("0114", "院校基科费-任务书-年度预算明细"),
    TASK_TRAN_LONG_DETAIL("0201", "横纵项项目-任务书-项目预算明细"),;

    BudgetCategoryEnums(String code, String desc){
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
