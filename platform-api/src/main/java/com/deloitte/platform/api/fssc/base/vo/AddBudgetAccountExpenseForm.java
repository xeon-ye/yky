package com.deloitte.platform.api.fssc.base.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 预算科目-支出大类 Form
 * @author jawjiang
 */
@Data
public class AddBudgetAccountExpenseForm implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "budgetAccountId")
    @NotBlank(message = "预算科目ID不能为空")
    private String budgetAccountId;

    @ApiModelProperty(value = "expenseMainTypeIds")
    private String expenseMainTypeIds;

}
