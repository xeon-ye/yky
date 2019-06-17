package com.deloitte.platform.api.fssc.bpm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class ProcessPassOrRejectForm extends ProcessStartForm {

    @ApiModelProperty(value = "主键ID,待办里面的ID,撤回时可不传",required = true)
    private Long id;

    @ApiModelProperty(value = "审批意见/驳回意见")
    private String opinion;

}
