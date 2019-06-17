package com.deloitte.platform.api.bpmservice.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("流程节点信息")
@Data
public class BpmTaskNextNodeVo {
    private String taskKey;
    private String taskNodeName;
    private String conditionText;
    private boolean multi;
    private boolean isEndNode;
    private boolean isFirstNode;
    private boolean isReject = true;
}
