package com.deloitte.services.contract.common.enums;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

@Getter
public enum ContractErrorType implements IErrorType {
    // 业务异常84070100
    SAVE_FAIL("84070101","保存失败!"),
    DATA_IS_NULL("84070102","数据为空!"),
    CONTRACT_IS_NULL("84070103","未查询到合同记录!"),
    CONTRACTNO_SETTING_NULL("84070104","合同流水号配置为空!"),
    CONTRACTNO_SAVE_FAIL("84070105","流水号保存失败!"),
    CONTRACTNO_GET_WATERMARK("84070106","没有配置对应水印信息!"),
    CONTRACTNO_GET_OLD_ATTAMENT("84070107","没有查询到对应文件信息!"),
    WORD_TO_PDF("84070108","word转换pdf失败!"),
    FROM_FINANCEINFO("84070109","没有传入合同财务信息!"),
    NO_FINANCE_TYPE("84070110","财务信息没有收付款类型!"),
    NO_CONTRACT_RELATION("84070111","没有找到合同关联性质!"),
    No_FROM("84070112","没有传入表单数据!"),
    STANDARD_IS_NULL("84070113","未查询到标准文本记录!"),
    EXPORT_EXCEPTION("84070114","文件导出异常!"),
    AMOUNT_IS_NULL("84070115","获取流程列表异常，未获取到合同金额!"),
    EXECUTER_TRANSFER_IS_NULL("84070116","未查询到移交数据!"),
    CANCEL_PROCESS_KEY_IS_NULL("84070117","未查询到作废参数!"),
    APPROVAL_TYPE_IS_NULL("84070118","办公室负责人审批意见不能为空!"),
    CONTRACT_IS_UNABLE("84070119","合同正在进行作废!"),
    CONTRACT_IS_OPERATOR("84070120","合同正在进行经办人移交!"),
    CONTRACT_IS_EXECUTER("84070121","合同正在进行履行人移交!"),
    CONTRACT_EXECUTER_NULL("84070122","未指定履行人!"),

    //数据库异常84070200
    SQL_EXCEPTION("840702001","数据库操作异常,请检查sql或查看表结构!"),
    DATA_IS_NOT_LATEST("840702002","保存失败,数据可能已经被修改,请重试!"),
    ONLY_DELETE_NEW("840702003","只能删除新建的数据!"),
    NOT_SAVE_NOT_DELETE("840702004","数据未保存不能删除!"),


    //文件类异常84070300
    FILE_NOT_EXIST("84070301","上传文件不存在"),
    FILE_UPLOAD_FIAL("84070302","上传文件失败"),
    FILE_DELETE_FIAL("84070303","文件删除失败!"),
    FILE_DOWNLOAD_FIAL("84070304","文件下载失败!"),


    //工作流异常84070400
    VOUCHER_IS_NULL("84070401","单据ID,单据类型,流程类型不能为空!"),
    PROCESS_DEF_IS_NULL("84070402","流程定义不存在!"),
    PROCESS_KEY_IS_NULL("84070403","流程定义KEY不能为空!"),
    PROCESS_START_FAIL("84070404","启动流程失败!"),
    NEXT_APPROVER_FIND_FIAL("84070405","寻找下一节点审批人失败!"),
    NEXT_APPROVER_IS_NULL("84070406","下一节点审批人为空,请先配置下一节点审批人!"),
    APPROVE_PASS_FIALD("84070407","调用BPM,审批失败!"),
    GET_TASK_FAILD("84070408","调用BPM获取任务失败!"),
    FIND_HISTORY_FAILD("84070409","查询审批历史失败!"),
    THIS_PROCESS_IS_NOT_FIND("84070410","流程不存在,该单据可能还没提交流程!"),
    VOUCHER_NOT_FIND("84070411","单据不存在!"),
    ONLY_DELETE_NEW_VOUCHER("84070413","只能删除新建状态的单据!"),
    ID_CANT_BE_NULL("84070414","ID不能为空!"),
    PROCESS_TASK_IS_NULL("84070415","流程节点不存在!"),
    ROLL_BACK_FIAL("84070416","流程撤回失败!"),
    CONTRACT_VOUCHER_IS_ALREADLY_SUBMIT("84070417","该合同已经提交了【合同审批】流程!"),
    SURE_VOUCHER_IS_ALREADLY_SUBMIT("84070418","该合同已经提交了【合同定稿】流程!"),
    SIGN_VOUCHER_IS_ALREADLY_SUBMIT("84070419","该合同已经提交了【合同打印签署】流程!"),
    EXECUTE_VOUCHER_IS_ALREADLY_SUBMIT("84070420","该合同已经提交了【合同履行】流程!"),
    FINISH_VOUCHER_IS_ALREADLY_SUBMIT("84070421","该合同已经提交了【合同办结】流程!"),
    VOUCHER_HAS_APPROVAL("84070421","合同已经被审批！"),
    STANDARD_VOUCHER_IS_ALREADLY_SUBMIT("84070422","该标准文本已经提交了【审批】流程!"),
    CONTRACT_VOUCHER_FINISH_IS_DOING("84070423","该合同已经提交了办结"),
    CONTRACT_VOUCHER_CANCEL_IS_DOING("84070424","该合同已经提交了作废"),
    CONTRACT_VOUCHER_EXECUTE_IS_DOING("84070425","该合同已经提交了履行人移交"),
    CONTRACT_VOUCHER_OPERATOR_IS_DOING("84070426","该合同已经提交了经办人移交"),

    CONTRACT_BOOK_PARAMETER_ERROR("84070427","合同审批流程参数有误!"),
    CONTRACT_SIGN_BOOK_PARAMETER_ERROR("84070428","合同签署流程参数有误!"),
    CONTRACT_FINISH_PARAMETER_ERROR("84070429","合同办结流程参数有误!"),
    CONTRACT_UNABLE_PARAMETER_ERROR("84070430","合同作废流程参数有误!"),
    CONTRACT_OPERATOR_TRANSFER_PARAMETER_ERROR("84070431","经办人移交流程参数有误!"),
    CONTRACT_EXECUTER_TRANSFER_PARAMETER_ERROR("84070432","履行人移交流程参数有误!"),
    CONTRACT_SURE_PARAMETER_ERROR("84070433","合同定稿流程参数有误!"),
    CONTRACT_EXECUTE_PARAMETER_ERROR("84070434","合同履行流程参数有误!"),
    CONTRACT_PRINT_PARAMETER_ERROR("84070435","合同打印流程参数有误!"),
    STANDARD_BOOK_PARAMETER_ERROR("84070436","标准文本审批流程参数有误!"),
    STANDARD_UNABLE_PARAMETER_ERROR("84070437","标准文本失效流程参数有误!"),
    APPROVAL_PARAMETER_ERROR("84070438","审批参数有误!"),
    NEXT_NODE_PARAMETER_ERROR("84070439","获取下一个节点参数有误!"),
    NEXT_NODE_FINANCE_PARAMETER_ERROR("84070440","获取财务领导失败!"),
    NEXT_NODE_OPERATOR_PARAMETER_ERROR("84070441","获取部门领导失败!"),
    BE_NO_READ_PARAMETER_ERROR("84070442","设置待阅参数有误!"),

    PROCESS_IS_NULL("84070438","获取流程信息失败!"),


    //系统用户异常84070500
    GET_USER_NOT_EXIST("84070501","获取用户数据失败"),
    GET_DEPT_NOT_EXIST("84070502","获取用户机构数据失败"),
    GET_ORG_NOT_EXIST("84070502","获取用户科室数据失败"),;



    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    ContractErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
