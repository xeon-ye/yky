package com.deloitte.services.project.common.enums;

import lombok.Getter;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/4/28 13:41
 * @Description :
 * @Modified:
 */
@Getter
public enum  ValueEnums {

    // 基本状态
    BOY("1001", "男"),
    GIRL("1002", "女"),

    // 申报书状态
    APPLICATION_UNDECLARED("2001", "未申报"),
    APPLICATION_DECLAARED("2002", "已申报"),
    APPLICATION_PENDING_REVIEW("2003", "待评审"),
    APPLICATION_ADJUESTED("2004", "待调整"),
    APPLICATION_REJECTED("2005", "已拒绝"),
    APPLICATION_HAVE_PASSED("2006", "已通过"),
    //APPLICATION_REPLIED("2007", "待批复"),
    APPLICATION_APPROVED("2007", "已批复"),

    // 项目状态
    PROJECT_UNDECLARED("7001", "未申报"),
    PROJECT_DECLAARED("7002", "已申报"),
    PROJECT_REPLIED("7003", "待批复"),
    PROJECT_ESTABLISHED("7004","已立项"),
    PROJECT_COMPLETED("7005", "已结项"),
    PROJECT_CANCELLED("7006", "已取消"),
    PROJECT_TERMINATED("7007", "已终止"),

    // 评审状态
    REVIEW_PENDING("6001", "待评审"),
    REVIEW_ADJUESTED("6002", "待调整"),
    REVIEW_HAVE_PASSED("6003","已通过"),
    REVIEW_REJECTED("6004","已拒绝"),

    // 项目类型
    PROJECT_TYPE_CENTRAL_INFRA("5001", "中央基建投资"),
    PROJECT_TYPE_DEPART_BUDGET("5002", "部门预算项目"),
    PROJECT_TYPE_REPAIR_PURCHASE("5003", "中央级科学事业单位修缮购置项目"),
    PROJECT_TYPE_SPECIAL_FUNDING("5004", "中央高校改善基本办学条件专项资金项目"),
    PROJECT_TYPE_INFRA("5005", "基础建设类"),
    PROJECT_TYPE_EDUCATION("5006", "教育类"),
    PROJECT_TYPE_OTHER("5007", "其它"),

    // 项目类别
    PROJECT_CATEGORY_HOUSE_RENOVATION("3001",	"房屋修缮"),
    PROJECT_CATEGORY_PURCHASE_EQUIPMENT("3002","设备资料购置"),
    PROJECT_CATEGORY_INFRA_IMPROVEMENT("3003","基础设施改造"),
    PROJECT_CATEGORY_CONSTRUCT_COMPLEMENTARY("3004",	"建设项目配套工程"),
    PROJECT_CATEGORY_OTHER("3005","其它"),

    // 一级项目
    FLP_CAPACITY_PUBLIC_INTITUT("4001","公共卫生机构服务能力建设"),
    FLP_PUBLIC_TASK_FUNDS("4002","公共卫生专项任务经费"),
    FLP_BUDGET_HOSPITAL_CAPACITY("4003","预算管理医院服务能力建设"),
    FLP_OTHER_PUBLIC_HOSPITAL("4004","其它项目_公立医院"),
    FLP_NATIONAL_CONTINUATION_ALLOCATION("4005","国家科技计划（专项）延续拨款"),
    FLP_PURCHASE_REPAIR_SCIENTIFIC("4006","科研机构修缮购置专项"),
    FLP_BUSINESS_EXPENSE_SCIENTIFIC("4007","科研机构专项业务费"),
    FLP_CENTRAL_EXPENDITURE("4008","归口管理科学支出其它项目"),
    FLP_CITATION_STUDY("4009","引用研究"),
    FLP_BASE("4010","基地专项"),
    FLP_FUNDS_CONDITIONS("4011","中央高校改善基本办学条件经费"),
    FLP_PERFORENCE_ALLOCATION_REFROM("4012","中央高校管理改革等绩效拨款"),
    FLP_SUPPORT_FUND_TEACH_REFORM("4013","中央高校教育教学改革支持经费"),
    FLP_IMPROVE_CHARACTERISTIC_DEVELOPMENT("4014","中央高校质量提升与特色发展引导经费"),

    //待阅
    REVIEW_READ("8001","项目评审书"),
    REPLY_READ("8002","项目批复书"),
    SUB_CODE_APP("9001","申请中"),
    SUB_CODE_PAS("9002","已通过")
    ;

    private String code;
    private String value;

    ValueEnums(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
