package com.deloitte.platform.api.dss.finance.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanceComIncomeVo extends BaseVo {

    @ApiModelProperty( value = "部门名称" )
    private String comDes;

    @ApiModelProperty( value= "收入金额" )
    private BigDecimal comIncome;

    @ApiModelProperty(value = "年份")
    private Integer year;

    @ApiModelProperty(value = "月份")
    private Integer month;

    @ApiModelProperty(value = "同比")
    private String rate;
}
