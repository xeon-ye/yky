package com.deloitte.platform.api.hr.check.vo;


import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("定时任务通用对象")
@Data
public class CommonUpdateForm extends BaseForm {

    @ApiModelProperty(value = "考核工作id")
    private Long checkWorkId;

    @ApiModelProperty(value = "状态")
    private String status;
}
