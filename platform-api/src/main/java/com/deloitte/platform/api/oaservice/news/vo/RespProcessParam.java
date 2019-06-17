package com.deloitte.platform.api.oaservice.news.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RespProcessParam  implements Serializable {

    @ApiModelProperty(value = "操作人姓名")
    private String operatorName;

    @ApiModelProperty(value = "操作状态")
    private String operatorStatus;

    @ApiModelProperty(value = "接收时间")
    private LocalDateTime receiveTime;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime operatorTime;

    @ApiModelProperty(value = "操作耗时")
    private Long durationTime;

    @ApiModelProperty(value = "节点标识")
    private String tastKey;

    @ApiModelProperty(value = "签收时间，当用户未查看时，该字段为空")
    private LocalDateTime signTime;


}
