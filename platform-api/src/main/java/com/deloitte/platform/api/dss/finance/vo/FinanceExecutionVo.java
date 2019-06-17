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
 * @Date : Create in 2019-04-10
 * @Description : FinanceExecution返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanceExecutionVo extends BaseVo {
    private static final long serialVersionUID = 1L;

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
