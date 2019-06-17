package com.deloitte.services.srpmp.common.enums;

/**
 * 附件类型枚举
 * Created by lixin on 20/02/2019.
 */
public enum AttachmentCategoryEnums {

    ATTACHMENT_APPLY_NORMAL("00", "通用项目申请书相关附件"),
    ATTACHMENT_APPLY_01("01", "创新工程-牵头单位学术委员会推荐意见/院基科费-通用项目申请书相关附件/校基科费-本项目相关生物医学研究伦理问题意见"),
    ATTACHMENT_APPLY_02("02", "创新工程-牵头单位审核意见及承诺/院基科费/校基科费"),
    ATTACHMENT_APPLY_03("03", "创新工程-重大协同创新申请书声明/院基科费/校基科费"),
    ATTACHMENT_APPLY_04("04", "校基科费-学生-项目负责人所在单位审核意见及承诺"),
    ATTACHMENT_APPLY_05("05", "校基科费-学生-学校审批意见"),

    ATTACHMENT_TASK_NORMAL("10","通用项目申请书相关附件（任务书）"),
    ATTACHMENT_TASK_11("11","任务书签订各方意见及签章/依托单位学术委员会意见"),
    ATTACHMENT_TASK_12("12","任务书单位伦理委员会意见/依托单位的意见"),
    ATTACHMENT_TASK_13("13","依托单位诚信申报承诺"),
    ATTACHMENT_TASK_14("14","中国医学科学院意见"),

    ATTACHMENT_UPDATE("20","项目变更相关附件"),
    ATTACHMENT_TRAN_LONG("30","横纵项相关附件"),
    ATTACHMENT_ACCEPT("40","项目验收相关附件"),
    ATTACHMENT_ACCEPT_ACD_01("41","项目验收-院基科费-项目有关技术资料"),
    ATTACHMENT_ACCEPT_ACD_02("42","项目验收-院基科费-项目有关证明材料"),
    ATTACHMENT_ACCEPT_ACD_03("43","项目验收-院基科费-项目经费决算表"),
    ATTACHMENT_ACCEPT_ACD_04("44","项目验收-院基科费-经费购置的固定资产验收清单"),
    ATTACHMENT_ACCEPT_ACD_05("45","项目验收-院基科费-承担单位审核意见"),
    ATTACHMENT_ACCEPT_SCH_01("46","项目验收-校基科费-经费决算"),
    ATTACHMENT_ACCEPT_SCH_02("47","项目验收-校基科费-项目负责人承诺"),
    ATTACHMENT_ACCEPT_SCH_03("48","项目验收-校基科费-项目依托单位审查意见"),
    ATTACHMENT_ACCEPT_SCH_04("49","项目验收-校基科费-学校审核意见"),

    ATTACHMENT_PROJECT_REPORT_YEAR("60","项目年度报告相关附件"),
    ATTACHMENT_PROJECT_REPORT_MID("61","项目中期报告相关附件"), // 中期附件不存在此处，暂为使用, 后续需要修改则在相应sevice方法中添加
    ATTACHMENT_PROJECT_REPORT_OTHER("62","项目其他告相关附件"),
    ;
    AttachmentCategoryEnums(String code, String desc){
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
