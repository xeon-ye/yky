package com.deloitte.services.srpmp.common.enums;

/**
 * 预算类型枚举
 * Created by lixin on 20/02/2019.
 */
public enum SysDictEnums {

    // 普通值列表
    PRO_MAJOR("PRO_MAJOR", "专业"),
    PRO_ACTIVE_TYPE("PRO_ACTIVE_TYPE", "项目活动类型"),
    PRO_TYPE("PRO_TYPE", "项目类型"),
    PRO_COMPANY_TYPE("PRO_COMPANY_TYPE", "单位性质"),
    PRO_DOMAIN("PRO_DOMAIN", "所在领域"),
    SEX("SEX", "性别"),
    PRO_COUNTRY("PRO_COUNTRY", "授予国别"),
    PRO_STAT("PRO_STAT", "项目申请状态"),
    PRO_TITLE("PRO_TITLE", "职称"),
    PRO_CER_TYPE("PRO_CER_TYPE", "证件类型"),
    PRO_RELUST_TYPE("PRO_RELUST_TYPE", "预期成果类型"),
    OUTLINE_PRO_STAT("OUTLINE_PRO_STAT", "题录-项目状态"),
    PRO_DEGREE("PRO_DEGREE", "学位"),
    ROLE("ROLE", "角色"),
    SUBJECT_CHOICE("SUBJECT_CHOICE", "学科分类"),
    SUBJECT_OPTIONS("SUBJECT_OPTIONS", "学科选择"),
    // 树形结构
    REGION("REGION", "区域省市县"),
    PRO_SUBJ_CAT("PRO_SUBJ_CAT", "学科分类"),
    PRO_CAT("PRO_CATEGORY", "项目分类"),
    Person_Cat_APPLY("Person_Cat_APPLY", "研究人员、技术人员和博士后"),
    SURBORDINATE_TYPE("SURBORDINATE_TYPE", "隶属"),
    DEVICE_TYPE("DEVICE_TYPE", "设备型号"),
    BOOLEAN_TYPE("BOOLEAN_TYPE", "是否"),
    Person_Cat_BUDGET("Person_Cat_BUDGET", "预算书人员分类"),
    PRO_RELUST_TYPE_INN_PRE("PRO_RELUST_TYPE_INN_PRE", "预期成果类型-先导专项"),
    TALENT_PLAN_TYPE("talent_plan_type", "人才计划类型"),
    BE_CURRENT("BE_CURRENT", "创新工程通用指标类型"),
    REPORT_TYPE("REPORT_TYPE", "项目分类"),
    PRO_ACTIVE_TYPE_BCOO_2019("PRO_ACTIVE_TYPE_BCOO_2019", "项目活动类型重大协同2019"),;

    SysDictEnums(String code, String desc){
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
