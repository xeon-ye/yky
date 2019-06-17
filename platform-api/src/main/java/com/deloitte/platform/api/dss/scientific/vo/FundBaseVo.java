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
public class FundBaseVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "新获项目经费")
    private Double amount;

    @ApiModelProperty(value = "到位经费")
    private Double reciveAmount;

    @ApiModelProperty(value = "年度支出费用")
    private Double payAmount;

}
