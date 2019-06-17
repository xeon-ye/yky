package com.deloitte.platform.api.dss.finance.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description : IncomeBudget返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeBudgetVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "年累计收入")
    private BigDecimal ytd;

    @ApiModelProperty(value = "当月收入")
    private BigDecimal ptd;

    @ApiModelProperty(value = "年累计分子")
    private BigDecimal ytdN;

    @ApiModelProperty(value = "年累计分母")
    private BigDecimal ytdD;

    @ApiModelProperty(value = "期间")
    private String periodCode;

    @ApiModelProperty(value = "机构编码")
    private String comCode;

    @ApiModelProperty(value = "机构名称")
    private String comDes;

    @ApiModelProperty(value = "指标编码")
    private String indexCode;

    @ApiModelProperty(value = "指标名称")
    private String indexDes;

    @ApiModelProperty(value = "预算达成率")
    private BigDecimal execution;

    @ApiModelProperty(value = "同比数据")
    private BigDecimal rate;

    @ApiModelProperty(value = "同比数据2")
    private BigDecimal growthRate;

    @ApiModelProperty(value = "机构集合")
    private List<String> comCodes;
}
