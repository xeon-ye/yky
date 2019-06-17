package com.deloitte.platform.api.oaservice.news.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 构建新闻审批流程
 */
@Data
public class NewsProcessForm extends BaseForm {

    /**
     * 该id为待办查出的主键id
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    @ApiModelProperty(value = "流程实例ID")
    private String processInstanceId;

    @ApiModelProperty(value = "审批意见/驳回意见")
    private String opinion;

    @ApiModelProperty(value = "审批类型")
    private String opinionType;

    @ApiModelProperty(value = "审批对象描述")
    private String objectDescription;

    @ApiModelProperty(value = "审批流程key")
    private String processDefineKey;

    /**
     * 该判断参数需前端设值传入
     */
    @ApiModelProperty(value = "判断参数")
    private Map<String, String> judgeParam;

//    @ApiModelProperty(value = "查询下一审批人节点的taskId")
//    private String taskId;

    @ApiModelProperty(value = "负责组织")
    private String chargeOrg;

    /**
     * 前端调用历史查询接口，并选择某一条数据进行返回，后端用该字段接收
     */
    @ApiModelProperty(value = "ProcessTaskVo返回的VO对象")
    ProcessTaskVo processTaskVo;

//    /**
//     * 待办中查询获取，在驳回给发起人时用到
//     */
//    @ApiModelProperty(value = "提交人code")
//    private String submitterCode;
//
//    /**
//     * 待办中查询获取，在驳回给发起人时用到
//     */
//    @ApiModelProperty(value = "提交人姓名")
//    private String submitterName;
//
//    /**
//     * 待办中查询获取，在驳回给发起人时用到
//     */
//    @ApiModelProperty(value = "提交人组织code")
//    private String submitterOrg;

    /**
     * 查询待办，获取该字段，根据该字段获取并修改新闻状态
     */
    @ApiModelProperty(value = "审批对象id")
    private String objectId;

    /**
     * 用于接收前端传入的多个审批人
     */
    @ApiModelProperty(value = "矩阵")
    List<ApprovalMatrixVo> nextMatriList;

}
