package com.deloitte.platform.api.contract.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


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

    @ApiModelProperty(value = "合同编号")
    private String contractId;

    @ApiModelProperty(value = "流程定义key")
    private String processDefineKey;

    @ApiModelProperty(value = "流程实例ID")
    private String processInstanceId;

    @ApiModelProperty(value = "审批意见")
    private List<ApprovalOpinionVo> approvalOpinionList;

    /*@ApiModelProperty(value = "流程参数-是否需要副领导审批  =1需要副领导审批  =0不需要负领导审批")
    private String assistant = "1";
    @ApiModelProperty(value = "流程参数-复审参数  =2需要复审  =1不需要复审")
    private String approvalWay = "1";
    @ApiModelProperty(value = "流程参数-主办部门分管领导审批参数 =1流程结束  =2财务部门分管领导  =3主要领导")
    private String financeWay = "2";
    @ApiModelProperty(value = "流程参数-财务部门分管领导审批参数 =1流程结束  =2主要领导")
    private String dutyWay = "2" ;*/

    @ApiModelProperty("选中的节点信息和人员信息")
    private NextNodeVO nextNodeVO;

    @ApiModelProperty(value = "提交人机构，即单据和流程的归属机构")
    private String submitterOrg;

    @ApiModelProperty("是否已复审 1为已复审， 其他为未复审")
    private String review;

    @ApiModelProperty("办公室负责人审批意见 1：勾选【重要事项，请分管领导、主要领导审批】  2：勾选【常规事项，请分管领导审批】  3：勾选【重要事项，请分管领导、主要领导审批】和【签名章，请院校主要负责人本人审批】")
    private String approvalType;


    @ApiModelProperty(value = "履行人code")
    private String executerCode;

    @ApiModelProperty(value = "履行人")
    private String executer;

    @ApiModelProperty(value = "履行人机构")
    private String executerOrg;

    @ApiModelProperty(value = "履行人结构id")
    private String executerOrgId;

}
