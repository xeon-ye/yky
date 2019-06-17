package com.deloitte.services.srpmp.common.enums;

/**
 * Created by lixin on 16/03/2019.
 */
public enum VoucherTypeEnums {

    APPLY_BOOK("申请书审批","pro-apply-check"),
    TASK_BOOK("任务书审批", "pro-task-check"),
    UPDATE_BOOK("项目变更审批", ""),
    MPR_EVA_A("中期绩效报告审批A","examine-report-a"),
    MPR_EVA_B("中期绩效报告审批B", "examine-report-b"),
    UPDATE_JOIN_BOOK("项目参与人员变更审批","?"),
    UPDATE_PERSON_BOOK("项目负责人变更审批"),
    UPDATE_CONTENT_BOOK("项目内容变更审批", "change-content-check"),
    UPDATE_BUDGET_BOOK("项目预算变更审批", "change-budget-check"),
    UPDATE_STATE_BOOK("项目状态变更审批", "change-status-check"),
    BUDGET_APPLY_BOOK("预算申请审批", "pro-budget-check"),
    ACCEPT_BOOK("项目验收审批", "?"),
    TRAN_LONG_TASK_BOOK("横纵向项目审批", "create-project-check"),
    RESULT_PAPER("论文成果审批"),
    RESULT_PATENT("专利成果审批"),
    RESULT_BOOK("著作成果审批"),
    RESULT_ACA_EXC("学术交流成果审批"),
    RESULT_AWARD("奖励成果审批"),
    RESULT_MEDICAL("新药证书成果审批"),
    RESULT_APPLIANCE("医疗器械成果审批"),
    RESULT_SOFTWARE("软件成果审批"),
    RESULT_TRANS_BOOK("成果转化审批"),
    MPR_EVA_YEAR("项目年度报告审批"),
    MPR_EVA_OTHER("项目其他报告审批"),
    MPR_EVA_MID("项目中期报告审批")
	;
    VoucherTypeEnums(String code){
        this.code = code;
    }

    VoucherTypeEnums(String code, String auditRouteName){
        this.code = code;
        this.auditRouteName = auditRouteName;
    }

    public static VoucherTypeEnums getEnumByCode(String code){
        for (VoucherTypeEnums e: VoucherTypeEnums.values()){
            if (e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }

    private String code;
    private String auditRouteName;

    public String getAuditRouteName() {
        return auditRouteName;
    }

    public void setAuditRouteName(String auditRouteName) {
        this.auditRouteName = auditRouteName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
