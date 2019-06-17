package com.deloitte.services.fssc.engine.dockingEbs.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@ApiModel(value="AvAccountInfoToEbs对象", description="用于对接ebs接口数据")
public class AvRatesFromEAbs {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "币种从")
    private String fromCurrency;

    @ApiModelProperty(value = "币种至")
    private String toCurrency;

    @ApiModelProperty(value = "折换日期")
    private LocalDateTime conversionDate;

    @ApiModelProperty(value = "折换类型，默认Corporate")
    private String conversionType;

    @ApiModelProperty(value = "币种从到币种至的汇率")
    private BigDecimal conversionRate;
}
