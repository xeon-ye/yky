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
 * @Description :  流程过程中的流程参数FORM对象
 * @Modified :
 */

@ApiModel("流程过程中的流程参数对象")
@Data
public class BpmProcessOperatorApprove {

      @ApiModelProperty(value = "下一环节审批参数")
      @NotNull
      ArrayList<NextNodeParamVo> nextNodeParamVo;

      @NotNull
      @ApiModelProperty(value = "流程参数对象")
      BpmProcessTaskFormApprove bpmProcessTaskForm;

      @ApiModelProperty(value = "流程变量对象")
      Map<String,String> processVariables=new HashMap<>();
}
