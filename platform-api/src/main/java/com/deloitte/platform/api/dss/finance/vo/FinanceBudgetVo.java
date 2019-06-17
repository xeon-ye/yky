package com.deloitte.platform.api.dss.finance.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description : FinanceBudget返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanceBudgetVo extends BaseVo {

    @ApiModelProperty(value = "唯一标识")
    private Integer id;

    @ApiModelProperty(value = "部门ID")
    private Integer comCode;

    @ApiModelProperty(value = "部门名称")
    private String comDes;

    @ApiModelProperty(value = "预算类型(0:支出 1:收入)")
    private Integer budgetType;

    @ApiModelProperty(value = "预算明细ID")
    private Integer budgetItems;

    @ApiModelProperty(value = "预算明细名称")
    private String budgetName;

    @ApiModelProperty(value = "年份")
    private Integer year;

    @ApiModelProperty(value = "月份")
    private Integer month;

    @ApiModelProperty(value = "预算金额")
    private BigDecimal amountMoney;

    @ApiModelProperty(value = "${field.comment}")
    private String ex2;

    @ApiModelProperty(value = "${field.comment}")
    private String ex3;

    @ApiModelProperty(value = "${field.comment}")
    private String ex4;

    @ApiModelProperty(value = "${field.comment}")
    private String ex5;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;

}
