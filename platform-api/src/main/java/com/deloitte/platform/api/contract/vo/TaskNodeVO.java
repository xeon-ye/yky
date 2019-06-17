package com.deloitte.platform.api.contract.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by lixin on 16/03/2019.
 * 流程任务节点VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskNodeVO {

    @ApiModelProperty(value = "审核节点ID")
    private String id;

    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "单据ID")
    private String objectId;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "单号")
    private String objectOrderNum;

    @ApiModelProperty(value = "单据类型")
    private String objectType;

    @ApiModelProperty(value = "单据状态")
    private String objectStauts;

    @ApiModelProperty(value = "任务状态")
    private String taskStauts;

    @ApiModelProperty(value = "申请日期")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "审批人")
    private String approverName;

    @ApiModelProperty(value = "申请人")
    private String applyPersonName;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    protected LocalDateTime updateTime;

    @ApiModelProperty(value = "审批意见")
    private String opinion;


}
