package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RetireApplyProcessForm extends BaseForm {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "taskId")
    private String taskId;

    @ApiModelProperty(value = "chargeOrg")
    private String chargeOrg;

    @ApiModelProperty(value = "processDefineKey")
    private String processDefineKey;

    @ApiModelProperty(value = "审核意见")
    private String opnion;

    @ApiModelProperty(value = "审核类型,1.通过,2驳回")
    private String type;

}
