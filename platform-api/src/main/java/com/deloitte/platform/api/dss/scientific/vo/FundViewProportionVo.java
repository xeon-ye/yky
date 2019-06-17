package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundViewProportionVo extends BaseVo{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "类别")
    private String proType;
    @ApiModelProperty(value = "高层次人占比")
    private Double highTalentProp;
    @ApiModelProperty(value = "高级人才占比")
    private Double highProp;
    @ApiModelProperty(value = "博士后占比")
    private Double categroyProp;
    @ApiModelProperty(value = "执行率占比")
    private Double exceuteProp;
    @ApiModelProperty(value = "成果占比")
    private Double resultProp;
}
