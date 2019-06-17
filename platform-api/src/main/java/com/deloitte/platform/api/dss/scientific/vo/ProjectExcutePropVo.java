package com.deloitte.platform.api.dss.scientific.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 预算明细个类别占比
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectExcutePropVo {

    @ApiModelProperty(value = "预算项目类别")
    private Integer phyletic;

    @ApiModelProperty(value = "预算项目名称")
    private String phyleticName;

    @ApiModelProperty(value = "预算金额")
    private Double budgetFunds;

    @ApiModelProperty(value = "支出金额")
    private Double payFunds;

    @ApiModelProperty(value = "占比")
    private Double propprtion;



}
