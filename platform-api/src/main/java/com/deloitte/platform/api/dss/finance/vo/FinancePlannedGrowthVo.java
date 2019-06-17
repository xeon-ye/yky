package com.deloitte.platform.api.dss.finance.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-10
 * @Description : FinancePlannedGrowth返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancePlannedGrowthVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Integer id;

    @ApiModelProperty(value = "增长类型 （0：支出 1：收入）")
    private Integer growthType;

    @ApiModelProperty(value = "计划增长率")
    private Double plannedGrowth;

    @ApiModelProperty(value = "年份")
    private Integer year;

}
