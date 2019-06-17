package com.deloitte.services.fssc.engine.dockingEbs.returnEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;

@Data
@ApiModel(value="AvRatesReturnEbs对象", description="用于对接ebs接口汇率数据")
public class AvRatesReturnEbs {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "导入标识，E导入失败；Y导入成功")
    private String importFlag ;

    @ApiModelProperty(value = "汇率日期")
    private LocalDateTime conversionDate;
    private String fromCurrency;
    @ApiModelProperty(value = "被转换币")
    private String toCurrency;

    @ApiModelProperty(value = "报账系统回写的详细错误信息")
    private String errorMessage ;
}
