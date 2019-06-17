package com.deloitte.platform.api.dss.finance.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 收入预算结
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeItemsVo {

   /* @ApiModelProperty(value = "部门名称")
    private String comDes;*/

    @ApiModelProperty(value = "预算名称")
    private String itemName;

    @ApiModelProperty(value = "预算金额")
    private BigDecimal budgetMoney;

    @ApiModelProperty(value = "年份")
    private Integer year;
}
