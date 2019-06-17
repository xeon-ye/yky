package com.deloitte.platform.api.contract.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-03
 * @Description : BasicInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfoExecuteVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    // 0：正常履行；1：非正常履行；2：未提交履行计划
    @ApiModelProperty(value = "履行类型")
    private String exeType;

    @ApiModelProperty(value = "履行类型对应数量")
    private String exeNumber;

    @ApiModelProperty(value = "逾期未付款")
    private String payDate;

    @ApiModelProperty(value = "逾期未收款")
    private String incomeDate;

    @ApiModelProperty(value = "付款金额与约定不符")
    private String payMoney;

    @ApiModelProperty(value = "收款金额与约定不符")
    private String incomeMoney;

    @ApiModelProperty(value = "月份")
    private String mmTime;

    @ApiModelProperty(value = "付款金额")
    private String payMoneyAll;

    @ApiModelProperty(value = "收款金额")
    private String incomeMoneyAll;
}
