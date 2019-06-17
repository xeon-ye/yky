package com.deloitte.platform.basic.fileservice.exception;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

/**
 * @Author : jackliu
 * @Date : Create in 14:55 17/02/2019
 * @Description :
 * @Modified :
 */

@Getter
public enum FileErrorType  implements IErrorType {


    FILE_EXSIT("82010001", "文件已存在"),
    FILE_NOT_EXSIT("82010002","文件不存在"),
    FILE_UPLOAD_ERROR("82010003","文件上传失败"),
    FILE_DOWNLOAD_ERROR("82010004", "数据被暂停或中断"),

    FILE_READ_ERROR("82010005", "文件读取异常"),
    FILE_CLOSE_ERROR("82010006", "文件关闭异常"),

    FILE_MERGE_ERROR("82010007","文件合并失败"),
    FILE_CHECK_ERROR("82010008","文件检验失败"),

    FILE_TRANSACTION_MD5_ERROR("82010009","获取文件MD5值失败")



    ;
    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    FileErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
