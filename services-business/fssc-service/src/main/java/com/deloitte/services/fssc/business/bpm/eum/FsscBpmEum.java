package com.deloitte.services.fssc.business.bpm.eum;

import lombok.Getter;

@Getter
public enum  FsscBpmEum {

    START("申请人提交","start"),
    PROJECT_LEADER("项目负责人审批","projectleader"),
    RE_DEPT_LEADER("科技管理处负责人审批","re_deptleader"),
    RE_MANAGER_LEADER("分管科研院校领导审批","re_managerleader"),
    DEPT_LEADER("处室负责人审批","deptleader"),
    BACK_DEPT_LEADER("后勤服务中心审批","back_deptleader"),
    ADMIN_DEPT_LEADER("院校办公室审批","admin_deptleader"),
    OFFICE_DEPT_LEADER("行政基建处审批","office_deptleader"),
    MANAGER_LEADER("部门分管院校领导审批","managerleader"),
    FI_MANAGER_LEADER("分管财务院校领导审批","fi_managerleader"),
    ASSET("资产管理部门审核","asset"),
    CASHIER("出纳审核","cashier"),
    ACCOUNT("会计审核","account"),
    GL_MANAGER("核算主管审核","gl_manager"),
    FI_DEPTLEADER("财务处负责人审核","fi_deptleader"),
    AUDIT("全过程审计","audit"),
    END("结束","结束")
    ;


    /**
     * 节点名称
     */
    private String name;
    /**
     * 节点key
     */
    private String value;

    FsscBpmEum(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
