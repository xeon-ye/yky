package com.deloitte.platform.api.project.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-27
 * @Description : ProcessTaskApproval返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessTaskApprovalVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程当前节点id")
    private String id;

    @ApiModelProperty(value = "工作流程名称")
    private String processDefineName;
    
    @ApiModelProperty(value = "流程定义key")
    private String processDefineKey;
    
    @ApiModelProperty(value = "流程实例ID")
    private String processInstanceId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "任务定义key")
    private String taskKey;

    @ApiModelProperty(value = "任务定义名称")
    private String taskName;

    @ApiModelProperty(value = "当前任务定义key")
    private String currentTaskKey;

    @ApiModelProperty(value = "当前任务定义名称")
    private String currentTaskName;

    @ApiModelProperty(value = "流程任务审批状态 【1待审批 2已批准 3已驳回 4已提交 5待提交 6已转办 7 终止流程 8 撤回】")
    private String taskStauts;

    @ApiModelProperty(value = "审批人id")
    private String approvalNum;

    @ApiModelProperty(value = "审批人")
    private String approvalName;

    @ApiModelProperty(value = "最近审批人id")
    private String lastApprovalNum;

    @ApiModelProperty(value = "最近审批人")
    private String lastApprovalName;

    @ApiModelProperty(value = "最近审批日期")
    private LocalDateTime lastApprovalTime;

    @ApiModelProperty(value = "项目id ")
    private String projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "提交人账号")
    private String submitterCode;

    @ApiModelProperty(value = "提交人姓名")
    private String submitterName;

}
