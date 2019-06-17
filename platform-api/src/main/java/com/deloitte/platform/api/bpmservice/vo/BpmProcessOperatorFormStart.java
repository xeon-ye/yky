package com.deloitte.platform.api.bpmservice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : jackliu
 * @Date : Create in 0:21 25/02/2019
 * @Description : 流程启动时的流程参数FORM对象
 * @Modified :
 */

@ApiModel("流程启动时的流程参数对象")
@Data
public class BpmProcessOperatorFormStart {

      @ApiModelProperty(value = "下一环节审批参数")
      @NotNull
      ArrayList<NextNodeParamVo> nextNodeParamVo;

      @NotNull
      @ApiModelProperty(value = "流程参数对象")
      BpmProcessTaskFormStart bpmProcessTaskForm;

      @ApiModelProperty(value = "流程变量")
      Map<String,String> processVariables=new HashMap<>();
}
