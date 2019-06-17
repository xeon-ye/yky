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
 * @Date : Create in 2019-04-17
 * @Description : 收支预算表返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinEtlIncomeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Long id;

    @ApiModelProperty(value = "期间")
    private String period;

    @ApiModelProperty(value = "机构")
    private Integer entity;

    @ApiModelProperty(value = "项目")
    private Integer item;

    @ApiModelProperty(value = "年初至今金额")
    private BigDecimal ytd;

    @ApiModelProperty(value = "年度预算")
    private BigDecimal budYtd;

    @ApiModelProperty(value = "${field.comment}")
    private String ex1;

    @ApiModelProperty(value = "${field.comment}")
    private String ex2;

    @ApiModelProperty(value = "${field.comment}")
    private String ex3;

    @ApiModelProperty(value = "${field.comment}")
    private String ex4;

    @ApiModelProperty(value = "${field.comment}")
    private String ex5;

    @ApiModelProperty(value = "${field.comment}")
    private String ex6;

    @ApiModelProperty(value = "${field.comment}")
    private String ex7;

    @ApiModelProperty(value = "${field.comment}")
    private String ex8;

    @ApiModelProperty(value = "${field.comment}")
    private String ex9;

    @ApiModelProperty(value = "${field.comment}")
    private String ex10;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

}
