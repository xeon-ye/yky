package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("批量操作对象")
@Data
public class BatchObj extends BaseForm {
    @ApiModelProperty(value = "批量操作对象数组")
    private List<Long> ids;
}
