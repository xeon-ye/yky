package com.deloitte.platform.api.hr.bpm.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProcessPassOrRejectForm extends BaseForm {

    @ApiModelProperty(value = "主键ID",required = true)
    private Long id;

    @ApiModelProperty(value = "审批意见/驳回意见")
    private String opinion;

    @ApiModelProperty(value = "审批流程key")
    private String processDefineKey;

    @ApiModelProperty(value = "查询下一审批人节点的taskId")
    private String taskId;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "负责组织")
    private String chargeOrg;

    @ApiModelProperty(value = "判断参数")
    private Map<String, String> map = new HashMap<>();

    @ApiModelProperty(value = "审批发起人ID")
    private String submitAccountId;

    @ApiModelProperty(value = "审批发起人姓名")
    private String submitAccountName;

    @ApiModelProperty(value = "审批发起人岗位")
    private String submitStation;

    @ApiModelProperty(value = "单据ID")
    private String documentId;

}
