package com.deloitte.services.project.common.enums;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

@Getter
public enum ProjectErrorType implements IErrorType {
    // 业务异常84070100
    SAVE_FAIL("84110101","保存失败!"),
    DATA_IS_NULL("84110102","数据为空!"),
    CONTRACTNO_SAVE_FAIL("84110105","数据保存失败!"),
    CONTRACTNO_GET_OLD_ATTAMENT("84110107","没有查询到对应文件信息!"),
    WORD_TO_PDF("84110108","word转换pdf失败!"),
    No_FROM("84110112","没有传入表单数据!"),
    NOT_SUBMIT_REPEAT_FROM("84110201", "已申报单据不可重复提交"),
    PRO_LIB_EXCEL_NOT_WRITTED("84110601", "项目库Excel数据写入异常"),

    //数据库异常84110200
    SQL_EXCEPTION("841102001","数据库操作异常,请检查sql或查看表结构!"),
    DATA_IS_NOT_LATEST("841102002","保存失败,数据可能已经被修改,请重试!"),
    ONLY_DELETE_NEW("841102003","只能删除新建的数据!"),
    NOT_SAVE_NOT_DELETE("841102004","数据未保存不能删除!"),


    //文件类异常84110300
    FILE_NOT_EXIST("84110301","上传文件不存在"),
    FILE_UPLOAD_FIAL("84110302","上传文件失败"),
    FILE_DELETE_FIAL("84110303","文件删除失败!"),
    FILE_DOWNLOAD_FIAL("84110304","文件下载失败!"),


    //工作流异常84110400
    VOUCHER_IS_NULL("84110401","单据ID,单据类型,流程类型不能为空!"),
    PROCESS_DEF_IS_NULL("84110402","流程定义不存在!"),
    PROCESS_KEY_IS_NULL("84110403","流程定义KEY不能为空!"),
    PROCESS_START_FAIL("84110404","启动流程失败!"),
    NEXT_APPROVER_FIND_FIAL("84110405","寻找下一节点审批人失败!"),
    NEXT_APPROVER_IS_NULL("84110406","下一节点审批人为空,请先配置下一节点审批人!"),
    APPROVE_PASS_FIALD("84110407","调用BPM,审批失败!"),
    GET_TASK_FAILD("84110408","调用BPM获取任务失败!"),
    FIND_HISTORY_FAILD("84110409","查询审批历史失败!"),
    THIS_PROCESS_IS_NOT_FIND("84110410","流程不存在,该单据可能还没提交流程!"),
    VOUCHER_NOT_FIND("84110411","单据不存在!"),
    ONLY_DELETE_NEW_VOUCHER("84110413","只能删除新建状态的单据!"),
    ID_CANT_BE_NULL("84110414","ID不能为空!"),
    PROCESS_TASK_IS_NULL("84110415","流程节点不存在!"),
    ROLL_BACK_FIAL("84110416","流程撤回失败!"),

    BE_NO_READ_PARAMETER_ERROR("84110442","设置待阅参数有误!"),

    PROCESS_IS_NULL("84110443","获取流程信息失败!"),

    //系统用户异常84110500
    GET_USER_NOT_EXIST("84110501","获取用户数据失败"),
    GET_DEPT_NOT_EXIST("84110502","获取用户机构数据失败"),
    GET_ORG_NOT_EXIST("84110502","获取用户科室数据失败");



    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    ProjectErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
