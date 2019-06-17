package com.deloitte.platform.api.dss.finance.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 计划增长率
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrowthVo extends BaseVo {

    @ApiModelProperty(value = "分子")
    private BigDecimal ytdN;

    @ApiModelProperty(value = "分母")
    private BigDecimal ytdD;

    @ApiModelProperty(value = "执行率")
    private BigDecimal execution;

    @ApiModelProperty(value = "期间")
    private String periodCode;

    @ApiModelProperty(value = "机构编码")
    private String comCode;

    @ApiModelProperty(value = "机构名称")
    private String comDes;

    @ApiModelProperty(value = "指标名称")
    private String indexCode;

    @ApiModelProperty(value = "指标编码")
    private String indexDes;

    @ApiModelProperty(value = "同比")
    private BigDecimal rate;

}
