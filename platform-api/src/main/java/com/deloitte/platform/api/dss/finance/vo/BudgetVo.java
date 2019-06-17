package com.deloitte.platform.api.dss.finance.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 预算结构柱状图
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetVo extends BaseVo {

    @ApiModelProperty(value = "上年结转")
    private BigDecimal forward;

    @ApiModelProperty(value = "财政收入")
    private BigDecimal revenues;

    @ApiModelProperty(value = "事业收入")
    private BigDecimal career ;

    @ApiModelProperty(value = "经营收入")
    private BigDecimal operating;

    @ApiModelProperty(value = "其他收入")
    private BigDecimal other;

    @ApiModelProperty(value = "基本支出")
    private BigDecimal base;

    @ApiModelProperty(value = "项目支出")
    private BigDecimal project;

}
