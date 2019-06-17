package com.deloitte.services.fssc.engine.dockingEbs.returnEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="AvAccountInfoToEbs对象", description="用于对接ebs接口数据")

public class CrossValidationRuleReturnEbs {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "导入标识，E校验失败；Y校验成功)")
    private String importFlag;

    @ApiModelProperty(value = "报账系统凭证头号")
    private String sourceHeadId;

    @ApiModelProperty(value = "报账系统凭证行号")
    private Long sourceLineId;

    @ApiModelProperty(value = "MacRP核心系统回写的详细错误信息")
    private String errorMessage;
}
