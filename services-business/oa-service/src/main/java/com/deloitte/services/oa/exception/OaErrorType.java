package com.deloitte.services.oa.exception;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

@Getter
public enum OaErrorType implements IErrorType{
    BPM_NEXT_APPROVER_ERROR("8408","获取流程下一处理人出错"),
    SAVE_ERROR("840811","保存失败"),
    USERINFO_ERROR("840812","获取用户信息失败"),
    BPM_TASKINFO_ERROR("840813", "获取流程信息失败"),
    DUTYINFO_ERROR("8408014", "查询处室负责人失败，请配置各部门处室负责人"),
    BPM_DELETE_ERROE("8408015", "该条新闻在审批中，无法删除")
    ;

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    OaErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
