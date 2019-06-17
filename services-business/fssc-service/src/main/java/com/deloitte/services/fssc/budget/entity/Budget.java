package com.deloitte.services.fssc.budget.entity;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 预算
 *
 * @author jawjiang
 */
@Data
public class Budget {

    /**
     * 单位id
     */
    private String unitId;

    /**
     * 单位
     */
    private String unitCode;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 预算类型
     */
    private String budgetType;

    /**
     * 预算类型名称
     */
    private String budgetTypeName;

    /**
     * 项目
     */
    private String projectCode;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 课题
     */
    private String subjectCode;

    /**
     * 课题名称
     */
    private String subjectName;

    /**
     * 任务
     */
    private String taskCode;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 一级处室
     */
    private String Level1OfficeCode;

    /**
     * 一级处室名称
     */
    private String Level1OfficeName;

    /**
     * 二级处室
     */
    private String Level2OfficeCode;

    /**
     * 二级处室名称
     */
    private String Level2OfficeName;

    /**
     * 预算科目编码
     */
    private String budgetAccountCode;

    /**
     * 预算科目名称
     */
    private String budgetAccountName;

    /**
     * 预算科目ID
     */
    private String budgetAccountId;

    /**
     * 预算期间
     */
    private String budgetPeriod;

    /**
     * 预算金额
     */
    private BigDecimal budgetAmount;

    /**
     * 预算年度
     */
    private String budgetAnnual;

    /**
     * 申请人
     */
    private String applyForPerson;

    /**
     * 申请人名称
     */
    private String applyForPersonName;

    private Integer index;

    public static final String INDEX_EXCEL = "序号";
    public static final String UNIT_CODE_EXCEL = "单位";
    public static final String BUDGET_TYPE_EXCEL = "预算类型";
    public static final String PROJECT_CODE_EXCEL = "项目";
    public static final String SUBJECT_CODE_EXCEL = "课题";
    public static final String TASK_CODE_EXCEL = "任务";
    public static final String LEVEL1_OFFICE_CODE_EXCEL = "一级处室";
    public static final String LEVEL2_OFFICE_CODE_EXCEL = "二级处室";
    public static final String BUDGET_ACCOUNT_CODE_EXCEL = "预算科目";
    public static final String BUDGET_PERIOD_EXCEL = "预算期间";
    public static final String BUDGET_AMOUNT_EXCEL = "预算金额";
    public static final String BUDGET_ANNUAL_EXCEL = "预算年度";
    public static final String APPLY_FOR_PERSON_EXCEL = "申请人";


}
