package com.deloitte.platform.api.oaservice.param;

import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : fq
 * @Date : Create in 2019-04-08
 * @Description : 业务提交流程参数
 * @Modified :
 */
@ApiModel("业务提交流程参数")
@Data
public class OaWorkflowParamForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程驱动参数，不能为空")
    private OaWorkflowDriverForm oaWorkflowDriverForm;

    @ApiModelProperty(value = "流程驱动待办处理人")
    private ArrayList<NextNodeParamVo> nextNodeParamVo;

    @ApiModelProperty(value = "自定义参数；如filterIds")
    private Map<String,String> diyParam = new HashMap<String,String>();
}
