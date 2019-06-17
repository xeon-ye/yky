package com.deloitte.platform.api.bpmservice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashMap;
import java.util.Map;

@ApiModel("获取流程下一结点新")
@Data
public class BpmTaskNextNodeForm {
    @ApiModelProperty(value = "流程定义ID")
    private String processDefId;

    @ApiModelProperty(value = "流程实例ID")
    private String processInstanceId;

    @NotEmpty
    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "流程变量")
    Map<String,String> processVariables=new HashMap<>();
}
