package com.deloitte.platform.api.contract.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-04-02
 * @Description : BpmProcessTask返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractProcessTaskVo extends BaseVo {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "主键ID")
    private String id;
    
    @ApiModelProperty(value = "流程定义key")
    private String processDefineKey;
    
    @ApiModelProperty(value = "流程实例ID")
    private String processInstanceId;
    
    @ApiModelProperty(value = "任务ID")
    private String taskId;
    
    @ApiModelProperty(value = "任务定义key")
    private String taskKey;
    
    @ApiModelProperty(value = "任务定义名称")
    private String taskName;
    
    @ApiModelProperty(value = "任务标题")
    private String taskTitle;
    
    @ApiModelProperty(value = "任务描述")
    private String taskDescription;
    
    @ApiModelProperty(value = "流程任务审批状态 【1待审批 2已批准 3已驳回 4已提交 5待提交 6已转办 7 终止流程 8 撤回】")
    private String taskStauts;
    
    @ApiModelProperty(value = "审批人账号")
    private String approverAcount;
    
    @ApiModelProperty(value = "审批人姓名")
    private String approverName;
    
    @ApiModelProperty(value = "审批人岗位")
    private String approverStation;

    @ApiModelProperty(value = "审批人组织")
    private String approverOrg;
    
    @ApiModelProperty(value = "审批人描述")
    private String approverDescription;
    
    @ApiModelProperty(value = "审批对象ID")
    private String objectId;
    
    @ApiModelProperty(value = "审批对象业务单据编号")
    private String objectOrderNum;
    
    @ApiModelProperty(value = "审批对象审批状态 【1审批中 2已通过 3 已驳回 4 停止审批】")
    private String objectStauts;
    
    @ApiModelProperty(value = "审批对象类型")
    private String objectType;
    
    @ApiModelProperty(value = "审批对象URL")
    private String objectUrl;
    
    @ApiModelProperty(value = "审批对象描述")
    private String objectDescription;

    @ApiModelProperty(value = "审批单归属系统")
    private String sourceSystem;
    
    @ApiModelProperty(value = "审批意见")
    private String opinion;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
    
    @ApiModelProperty(value = "持续时间毫秒数")
    private Long durationTime;
    
    @ApiModelProperty(value = "提交人账号")
    private String submitterCode;
    
    @ApiModelProperty(value = "提交人姓名")
    private String submitterName;

    @ApiModelProperty(value = "提交人岗位")
    private String submitterStation;

    @ApiModelProperty(value = "提交人组织")
    private String submitterOrg;

    @ApiModelProperty(value = "手机端访问地址")
    private String objectAppUrl;

    @ApiModelProperty(value = "紧急程度")
    private String emergency;

    @ApiModelProperty(value = "签收时间")
    private LocalDateTime signTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建人")
    private String createByName;

    @ApiModelProperty(value = "创建人所在部门")
    private String createByOrgId;

    @ApiModelProperty(value = "上一任务id")
    private String previousTaskId;

    @ApiModelProperty(value = "流程创建时间")
    private LocalDateTime processCreateTime;

    @ApiModelProperty(value = "代理人id")
    private String agentId;

    @ApiModelProperty(value = "代理人名称")
    private String agentName;


    @ApiModelProperty(value = "审批意见")
    private List<ApprovalOpinionVo> approvalOpinionVoList;

}
