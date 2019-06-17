package com.deloitte.platform.api.fssc.engine.manual.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description : 主要运用到ebs交叉验证错误信息返回前台
 * @Modified :
 */
@Data
public class EbsReturnStatusVo {
    @ApiModelProperty(value = "status,状态为true证明是通过的，如果为false是证明交叉验证失败")
    private Boolean status ;
    @ApiModelProperty(value = "errMsg,返回的错误消息")
    private String errMsg;
}
