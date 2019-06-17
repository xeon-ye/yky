package com.deloitte.platform.api.fssc.budget.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel("启动流程Process表单")
@Data
@NoArgsConstructor
public class BudgetCheckForm extends BaseForm{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单据ID")
    @NonNull
    private Long documentId;

    @ApiModelProperty(value = "单据类型取表名,如借款就是借款的头表名称")
    @NotEmpty
    private String documentType;

}
