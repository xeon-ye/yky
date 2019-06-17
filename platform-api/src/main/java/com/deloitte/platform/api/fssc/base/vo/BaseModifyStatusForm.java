package com.deloitte.platform.api.fssc.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改状态Form
 * @author jawjiang
 */
@Data
@ApiModel("启用/失效状态的表单")
public class BaseModifyStatusForm {

    @ApiModelProperty(value = "主键id的集合",dataType = "array",required = true)
    private List<Long> ids;

    @ApiModelProperty(value = "生失效状态",notes = "Y:启用,N:失效",dataType = "string",required = true)
    @NotBlank(message = "生失效状态不能为空!")
    private String status;

}
