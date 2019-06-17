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
public class FinExecutionVo extends BaseVo {

    @ApiModelProperty(value = "预算执行率")
    private BigDecimal execution;

    @ApiModelProperty(value = "同比")
    private BigDecimal rate;
}
