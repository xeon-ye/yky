package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RemindProcessForm extends BaseForm {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "taskId")
    private String taskId;

    @ApiModelProperty(value = "chargeOrg")
    private String chargeOrg;

    @ApiModelProperty(value = "processDefineKey")
    private String processDefineKey;
}
