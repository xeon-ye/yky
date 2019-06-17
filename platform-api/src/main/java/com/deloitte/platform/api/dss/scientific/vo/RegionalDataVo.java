package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 科研项目及到位经费分布
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionalDataVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "城市或单位所标识通用接收字段")
    private String  baseType;

    @ApiModelProperty(value = "任务数量")
    private Long taskNum;

    @ApiModelProperty(value = "项目数量")
    private Long proNum;

    @ApiModelProperty(value = "到位经费")
    private Double fundMoney;

    @ApiModelProperty(value = "ID标识")
    private Long id;
}
