package com.deloitte.platform.api.fssc.bpm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class ReSubmitProcessForm extends ProcessStartForm {

    @ApiModelProperty(value = "重新提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT",required = true)
    @NotEmpty
    private String reSubmitType;
    @ApiModelProperty(value = "审批意见")
    private String opinion;
}
