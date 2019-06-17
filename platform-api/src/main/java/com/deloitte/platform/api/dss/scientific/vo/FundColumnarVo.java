package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 科研经费基础总计数据vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundColumnarVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "年度")
    private String yaerValue;

    @ApiModelProperty(value = "总到位经费")
    private Double totalMoeny;

    @ApiModelProperty(value = "院校级到位经费")
    private Double academy;

    @ApiModelProperty(value = "国家级到位经费")
    private Double national;

    @ApiModelProperty(value = "省部级到位经费")
    private Double provincial;

    @ApiModelProperty(value = "横向位经费")
    private Double transverse;

    @ApiModelProperty(value = "其他到位经费")
    private Double other;

}
