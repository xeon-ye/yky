package com.deloitte.platform.api.oaservice.news.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 驳回给指定人时，前端传入对象
 */
@Data
public class ProcessTaskVo {

    @ApiModelProperty(value = "审批人账号")
    private String approverAcount;

    @ApiModelProperty(value = "审批人姓名")
    private String approverName;

    @ApiModelProperty(value = "审批人组织")
    private String approverOrg;

    @ApiModelProperty(value = "审批对象ID")
    private String objectId;

    @ApiModelProperty(value = "任务定义key")
    private String taskKey;

    @ApiModelProperty(value = "跳转url")
    private String objectUrl;
}
