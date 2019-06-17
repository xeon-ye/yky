package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 科研经费展示Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SRFundVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目个数")
    private String projectNum;

    @ApiModelProperty(value = "所获经费")
    private Long totalFund;

    @ApiModelProperty(value = "年度到位经费")
    private Long receiveFund;

    @ApiModelProperty(value = "年度支出经费")
    private Long expendFund;
}
