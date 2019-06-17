package com.deloitte.platform.api.project.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class TaskNodeActionVO {

    @ApiModelProperty(value = "审核节点ID")
    private String id;

    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "任务key")
    private String taskKey;

    @ApiModelProperty(value = "单据ID")
    private String objectId;

    @ApiModelProperty(value = "审批意见")
    private String opinion;

    @ApiModelProperty(value = "业务编号")
    private String businessId;

    @ApiModelProperty(value = "流程定义key")
    private String processDefineKey;

    @ApiModelProperty(value = "流程实例ID")
    private String processInstanceId;

    @ApiModelProperty(value = "提交人机构，即单据和流程的归属机构")
    private String submitterOrg;

}
