package com.deloitte.platform.api.oaservice.param;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : fq
 * @Date : Create in 2019-04-08
 * @Description : 流程驱动参数
 * @Modified :
 */
@ApiModel("流程驱动参数")
@Data
public class OaWorkflowDriverForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "流程定义key")
    private String processDefineKey;

    @ApiModelProperty(value = "流程历史中的主键ID")
    private String workflowId;

    @ApiModelProperty(value = "流程实例ID")
    private String processInstanceId;

    @ApiModelProperty(value = "任务实例ID")
    private String taskId;


    @ApiModelProperty(value = "任务定义key")
    private String taskKey;

    @ApiModelProperty(value = "任务定义名称")
    private String taskName;

    @ApiModelProperty(value = "题任务标")
    private String taskTitle;

    @ApiModelProperty(value = "任务描述")
    private String taskDescription;

    @ApiModelProperty(value = "审批对象ID")
    private String objectId;

    @ApiModelProperty(value = "审批对象业务单据编号")
    private String objectOrderNum;

    @ApiModelProperty(value = "审批对象类型中文描述")
    private String objectType;

    @ApiModelProperty(value = "审批对象URL")
    private String objectUrl;

    @ApiModelProperty(value = "审批对象描述")
    private String objectDescription;

    @ApiModelProperty(value = "审批意见")
    private String opinion;

    @ApiModelProperty(value = "审批单归属系统")
    private String sourceSystem = "oaservice";

    @ApiModelProperty(value = "操作类型")
    private String optionType;

    @ApiModelProperty(value = "紧急程度")
    private String emergency;

    @ApiModelProperty(value = "手机端访问地址")
    private String objectAppUrl;

    @ApiModelProperty(value = "流程变量对象")
    Map<String,String> processVariables=new HashMap<>();

}
