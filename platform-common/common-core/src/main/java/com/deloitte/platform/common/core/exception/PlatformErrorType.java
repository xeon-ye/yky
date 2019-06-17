package com.deloitte.platform.common.core.exception;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

@Getter
public enum PlatformErrorType implements IErrorType{


    //系统异常（主要是框架层面异常）
    SYSTEM_ERROR("-1", "系统繁忙,请稍候再试"),

    SYSTEM_BUSY("000001", "系统繁忙,请稍候再试"),
    NULL_POINT_ERROR("000002", "空指针异常"),

    SYSTEM_NO_PERMISSION("000003", "无权限访问"),

    GATEWAY_NOT_FOUND_SERVICE("010404", "服务未找到"),
    GATEWAY_SERVICE_UNABLE("010404", "进入断路器，服务不可用"),
    GATEWAY_ERROR("010500", "网关异常"),
    GATEWAY_CONNECT_TIME_OUT("010002", "网关超时"),

    //底层异常（主要是平台底层业务异常）
    ARGUMENT_NOT_VALID("020000", "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT("020001", "上传文件大小超过限制"),
    NO_DATA_FOUND("02002","没有查询到数据"),
    ADD_NOT_VALID("020003", "保存数据必填字段或者重复校验不通过"),
    ADD_ERROR("020003", "保存数据失败"),

    BUSINESS_ARGUMENT_NOT_VALID("020000", "{1}参数错误：{2}}"),


    TYPE_CAST_ERROE("02100","类型转换异常，可能是FORM转PO，或者是PO转VO时发生"),
    FORM_TO_PO_ERROR("02101","业务提交的FORM转换成PO对象失败"),
    PO_TO_VO_ERROR("02102","业务提交的PO转换成O对象失败"),
    OBJ_TO_MAP_ERROR("02103","bject对象转换成MAP失败"),

    WARPPER_BETWEEN_ERROR("02203","查询封装器between参数有误"),
    WARPPER_PARAM_ERROR("02203","查询封装器参数有误"),


    CODE_GENERATOR_PACKAGE_EMPTY("03001","工具生成代码时，输入的包名为空"),

    ORG_CODE_ERROR("-1","该组织已经存在！"),

    DPET_NAME_ERROR("-1","单位名称已存在，请重新输入!"),

    NO_LOGIN("401","请输入账号和密码!"),
    NO_USER("402","用户不存在!"),
    USER_EXCEPTION("403","账号状态异常，请联系管理员!"),
    USER_ERROR("404","用户名或密码错误!"),
    USER_PHONE_ERROR("-1","手机号已注册，请重新输入!"),
    USER_PHONE_NOT_RESGISTER_ERROR("-1","手机号码未注册!"),
    USER_EMAIL_ERROR("-1","邮箱已注册，请重新输入!"),
    USER_IDCARD_ERROR("-1","证件号已注册，请重新输入!"),
    ORG_SAVE_ERROR("-1","机构保存失败!"),
    USER_SAVE_ERROR("-1","用户保存失败!"),
    FILE_PREVIEW_COVERT_FAIL("100002","文件预览转换失败!"),
    IMPORT_TEMPLATE_ERROR("100001", "导入模板格式错误，请下载模板重新填写。请勿改变原有模板格式。"),;




    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    PlatformErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
