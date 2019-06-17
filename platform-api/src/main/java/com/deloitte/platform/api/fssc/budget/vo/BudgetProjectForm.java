package com.deloitte.platform.api.fssc.budget.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description : BudgetProject新增修改form对象
 * @Modified :
 */
@ApiModel("新增BudgetProject表单")
@Data
public class BudgetProjectForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "财务code")
    private String fsscCode;

    @ApiModelProperty(value = "经费类型")
    private String moneyType;

    @ApiModelProperty(value = "计划号")
    private String planNum;

    @ApiModelProperty(value = "关联号")
    private String connectNum;

    @ApiModelProperty(value = "是否允许报账")
    private String isAllowExpense;

    @ApiModelProperty(value = "款项大类id")
    private Long inComeMainTypeId;

    @ApiModelProperty(value = "款项小类id")
    private Long inComeSubTypeId;

    @ApiModelProperty(value = "收入结转方式")
    private String paymentConfirmType;

    @ApiModelProperty(value = "财务-会计科目编码")
    private String accountCode;
}
