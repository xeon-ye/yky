package com.deloitte.platform.basic.bpmservice.exception;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

/**
 * @Author : jackliu
 * @Date : Create in 14:55 17/02/2019
 * @Description :
 * @Modified :
 */

@Getter
public enum BpmErrorType implements IErrorType {


    BPM_NEXT_APPROVER_IS_EMPTY("82020001", "下一环节审批人为空"),

    BPM_APPROVE_FAILE("82020002", "审批失败"),

    BPM_PROCESS_TASK_NOT_EXIST("82020003", "审批记录不存在"),

    BPM_PROCESS_DEFINE_KEY_IS_EMPTY("82020004", "流程定义ID为空"),

    BPM_PROCESS_TASK_ID_IS_EMPTY("82020005", "流程TASK任务ID为空"),

    BPM_PROCESS_GET_FIRST_TASK_ERROR("82020006", "获取流程首节点错误"),

    BPM_PROCESS_OR_TASK_IS_EMPTY("82020007", "流程实例ID或TASK任务ID为空"),

    BPM_PROCESS_NO_UNIQUE("82020008", "流程历史记录不是唯一"),

    BPM_PROCESS_COMPLETE("82020009", "流程已审批完成"),

    BPM_TASK_COMPLETE("82020010", "当前任务已完成"),

    BPM_ROLL_BACK_ERROR("82020011", "撤回失败"),


    BPM_MANAGER_MODEL_IS_EMPTY("82020030", "模型数据为空，请先设计流程并成功保存，再进行发布。"),

    BPM_MANAGER_MODEL_ERROR("82020031", "模型数据为空，数据模型不符要求，请至少设计一条主线流程。"),

    BPM_MANAGER_EXPORT_ERROR("82020031", "流程模板导出失败"),


    ;
    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    BpmErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
