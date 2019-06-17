package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("批量删除对象")
@Data
public class DeleteForm extends BaseForm {

    @ApiModelProperty(value = "批量删除的数组")
    private Long[] ids;
}
