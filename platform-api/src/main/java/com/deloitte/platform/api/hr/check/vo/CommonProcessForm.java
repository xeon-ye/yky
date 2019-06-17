package com.deloitte.platform.api.hr.check.vo;


import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("通用流程对象")
@Data
public class CommonProcessForm extends BaseForm {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "taskId")
    private String taskId;

    @ApiModelProperty(value = "chargeOrg")
    private String chargeOrg;

    @ApiModelProperty(value = "processDefineKey")
    private String processDefineKey;

    @ApiModelProperty(value = "操作类型，是否同意，1同意，2不同意")
    private String type;

    @ApiModelProperty(value = "考核关系Id集合")
    private List<String> checkRelationIdList;

    @ApiModelProperty(value = "个人业绩考核Id")
    private Long checkAchUserId;

    @ApiModelProperty(value = "审批意见")
    private String opinion;
}
