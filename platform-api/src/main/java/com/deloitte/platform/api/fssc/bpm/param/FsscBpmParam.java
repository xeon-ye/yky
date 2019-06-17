package com.deloitte.platform.api.fssc.bpm.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class FsscBpmParam {

    private String documentNum;

    private Long documentId;

    private String documentType;

    private String processInstanceId;

    private String processDefKey;

    private Long localProcessDefId;

    private String taskId;

    @ApiModelProperty(value = "流程变量")
    Map<String,String> processVariables=new HashMap<>();


}
