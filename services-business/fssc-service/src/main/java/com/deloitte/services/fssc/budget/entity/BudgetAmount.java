package com.deloitte.services.fssc.budget.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 预算金额
 * @author jawjiang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="预算金额对象", description="")
public class BudgetAmount {

    @ApiModelProperty(value = "预算金额")
    private BigDecimal budgetAmount;

    @ApiModelProperty(value = "预算保留金额")
    private BigDecimal budgetRemainAmount;

    @ApiModelProperty(value = "预算占用金额")
    private BigDecimal budgetOccupyAmount;

    @ApiModelProperty(value = "预算可用金额")
    private BigDecimal budgetUsableAmount;
}
