package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 科研项目及到位经费分布 项目规模
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionalProScaleVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "延续项目个数")
    private Long continueNum;

    @ApiModelProperty(value = "新获项目个数")
    private Long newNum;


    @ApiModelProperty(value = "新获项目经费")
    private Double budgetAmout;


    @ApiModelProperty(value = "本年到位经费")
    private Double receiveOutlay;

}
