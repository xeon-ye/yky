package com.deloitte.platform.common.core.entity.vo;

import com.deloitte.platform.common.core.common.IErrorType;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;

@ApiModel(description = "通用REST返回对象")
@Getter
public class Result<T> {

    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MESG = "处理成功";

    @ApiModelProperty(value = "处理结果code", required = true)
    private String code;
    @ApiModelProperty(value = "处理结果描述信息")
    private String mesg;

    @ApiModelProperty(value = "请求结果生成时间戳")
    private Instant timestamp;

    @ApiModelProperty(value = "处理结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @ApiModelProperty(value = "错误信息唯一UUID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uuid;

    public Result() {
        this.timestamp = ZonedDateTime.now().toInstant();
    }

    /**
     * @param errorType
     */
    public Result(IErrorType errorType) {
        this.code = errorType.getCode();
        this.mesg = errorType.getMsg();
        this.timestamp = ZonedDateTime.now().toInstant();
    }

    /**
     * @param errorType
     * @param data
     */
    public Result(IErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    /**
     * @param errorType
     * @param data
     */
    public Result(IErrorType errorType, String uuid, T data) {
        this(errorType);
        this.data = data;
        this.uuid=uuid;
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param mesg
     * @param data
     */
    public Result(String code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
        this.timestamp = ZonedDateTime.now().toInstant();
    }


    public Result<T> sucess(Object data){
        return new Result<T>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, (T)data);
    }
    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result success(Object data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static Result fail() {
        return new Result(PlatformErrorType.SYSTEM_ERROR);
    }

    /**
     * 保存校验返回信息
     *
     * @return
     */
    public static Result repetition(Object data) {
        return new Result(PlatformErrorType.ADD_NOT_VALID, data);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param baseException
     * @return Result
     */
    public static Result fail(BaseException baseException) {
        return fail(baseException, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(BaseException baseException, Object data) {
        return new Result<>(baseException.getErrorType(), data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(BaseException baseException,String uuid,Object data) {
        return new Result<>(baseException.getErrorType(),uuid, data);
    }


    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    public static Result fail(PlatformErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @return Result
     */
    public static Result fail(PlatformErrorType errorType) {
        return Result.fail(errorType, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(Object data) {
        return new Result<>(PlatformErrorType.SYSTEM_ERROR, data);
    }


    /**
     * 成功code=000000
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
}
