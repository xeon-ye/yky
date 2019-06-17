package com.deloitte.services.srpmp.common.constant;

public interface ProcessConstant {

    //申请书-医科院本级审批流程key
    public final String APPLY_AUDIT_PROCESS_TOP = "srpmp_process_apply_audit_top";

    //申请书-医科院二级审批流程key
    public final String APPLY_AUDIT_PROCESS_SEC = "srpmp_process_apply_audit_sec";

    //新申请书审批
    String APPLY_AUDIT_PROCESS_SEC_NEW = "srpms_process_global";

    //任务书-医科院本级审批流程key
    public final String TASK_AUDIT_PROCESS_TOP = "srpmp_process_task_audit_top";

    //中期绩效报告审批流程A
    public final String MPR_EVA_PROCESS_A = "mpr_eva_process_a";

    //中期绩效报告审批流程B
    public final String MPR_EVA_PROCESS_B = "mpr_eva_process_b";

    public final String BPM_CLIENT_NAME = "srpms";

    //项目变更-医科院本级审批流程key
    public final String srpmp_process_modify_this = "srpmp_process_modify_this";

    //项目变更-医科院上级审批流程key
    public final String srpmp_process_modify_parent = "srpmp_process_modify_parent";

    // 科研共用本级审批流程
    public String SRPMP_SHARING_GENERAL_PROCESS = "srpmp_sharing_general_process";

    // 科研共用两级审批流程
    public final String SRPMP_SHARING_SECOND_PROCESS = "srpmp_sharing_second_process";
}