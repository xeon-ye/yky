package com.deloitte.platform.api.dss.finance.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description : FinacenIncome返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinacenIncomeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Integer id;

    @ApiModelProperty(value = "部门编号")
    private Integer comCode;

    @ApiModelProperty(value = "部门名称")
    private String comDes;

    @ApiModelProperty(value = "收入明细ID")
    private Integer incomeId;

    @ApiModelProperty(value = "收入金额")
    private BigDecimal income;

    @ApiModelProperty(value = "年份")
    private Integer year;

    @ApiModelProperty(value = "月份")
    private Integer month;

    @ApiModelProperty(value = "同比")
    private BigDecimal rate;

    @ApiModelProperty(value = "${field.comment}")
    private String ex2;

    @ApiModelProperty(value = "${field.comment}")
    private String ex3;

    @ApiModelProperty(value = "${field.comment}")
    private String ex4;

    @ApiModelProperty(value = "${field.comment}")
    private String ex5;

    @ApiModelProperty(value = "创建创建人")
    private String createBy;

    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;

}
