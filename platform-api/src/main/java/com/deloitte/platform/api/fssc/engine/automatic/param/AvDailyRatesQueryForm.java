package com.deloitte.platform.api.fssc.engine.automatic.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :   AvDailyRates查询from对象
 * @Modified :
 */
@ApiModel("AvDailyRates查询表单")
@Data
public class AvDailyRatesQueryForm extends BaseQueryForm<AvDailyRatesQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "币种从")
    private String fromCurrency;

    @ApiModelProperty(value = "币种至")
    private String toCurrency;

    @ApiModelProperty(value = "折换日期")
    private LocalDateTime conversionDate;

    @ApiModelProperty(value = "折换类型")
    private String conversionType;

    @ApiModelProperty(value = "‘币种从’到‘币种至’的汇率")
    private Double conversionRate;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "预留字段1")
    private String etx1;

    @ApiModelProperty(value = "预留字段2")
    private String etx2;

    @ApiModelProperty(value = "预留字段3")
    private String etx3;

    @ApiModelProperty(value = "预留字段4")
    private String etx4;

    @ApiModelProperty(value = "预留字段5")
    private String etx5;
}
