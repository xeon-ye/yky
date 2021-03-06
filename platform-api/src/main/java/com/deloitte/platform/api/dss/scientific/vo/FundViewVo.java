package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundViewVo extends BaseVo{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "时间")
    private String period;
    @ApiModelProperty(value = "金额")
    private String money;
    @ApiModelProperty(value = "类型")
    private String style;
}
