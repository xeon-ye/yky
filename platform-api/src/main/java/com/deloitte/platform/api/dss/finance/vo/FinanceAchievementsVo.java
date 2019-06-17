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
 * @Date : Create in 2019-04-11
 * @Description : FinanceAchievements返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanceAchievementsVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private Integer id;

    @ApiModelProperty(value = "项目ID")
    private Integer itemsId;

    @ApiModelProperty(value = "绩效分数")
    private BigDecimal score;

    @ApiModelProperty(value = "平均绩效分数")
    private BigDecimal avgScore;

    @ApiModelProperty(value = "同比")
    private BigDecimal rate;

    @ApiModelProperty(value = "年份")
    private Integer year;

    @ApiModelProperty(value = "${field.comment}")
    private String ex1;

    @ApiModelProperty(value = "${field.comment}")
    private String ex2;

    @ApiModelProperty(value = "${field.comment}")
    private String ex3;

    @ApiModelProperty(value = "${field.comment}")
    private String ex4;

}
