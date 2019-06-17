package com.deloitte.platform.api.bpmservice.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : jackliu
 * @Date : Create in 2019-03-16
 * @Description :   BpmApprovalMatrix查询from对象
 * @Modified :
 */
@ApiModel("查询审批人参数对象")
@Data
public class BpmApprovalMatrixQueryFormForApproval extends BaseQueryForm  {



    @ApiModelProperty(value = "流程定义")
    @NotEmpty
    private String processDefineKey;

    //首节点时无任务ID,请传Start
    @NotEmpty
    @ApiModelProperty(value = "任务节点ID")
    private String taskID;

    @NotEmpty
    @ApiModelProperty(value = "负责组织")
    private String chargeOrg;


    @ApiModelProperty(value = "负责业务")
    private String chargeBusiness;


    @ApiModelProperty(value = "流程变量，如果第二个节点是网关的话，必填：网关路由规则")
    Map<String,String> processVariables=new HashMap<>();


}
