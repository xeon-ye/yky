package com.deloitte.platform.api.oaservice.applycenter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@ApiModel("新增SendProcessTask表单")
@Data
public class SendProcessTaskByBpmForm {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @ApiModelProperty(value = "流程定义key")
    private String processDefineKey;

    @NotEmpty
    @ApiModelProperty(value = "processInstanceId")
    private String processInstanceId;

    @NotEmpty
    @ApiModelProperty(value = "当前任务ID，流程启动时传start")
    private String bpmTaskId;

    @NotEmpty
    @ApiModelProperty(value = "审批单归属系统")
    private String sourceSystem;

    @NotEmpty
    @ApiModelProperty(value = "紧急程度{0,1,2}")
    private String emergency;

    @NotEmpty
    @ApiModelProperty(value = "阅办人信息")
    private List<SendProcessTaskApprovesFrom> sendProcessTaskApprovesFroms;

}
