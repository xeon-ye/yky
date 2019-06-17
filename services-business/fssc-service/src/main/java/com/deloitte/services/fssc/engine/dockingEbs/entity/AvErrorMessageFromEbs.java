package com.deloitte.services.fssc.engine.dockingEbs.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AvErrorMessageFromEbs {
    @ApiModelProperty(value = "MacRP核心系统回写的详细错误信息\n")
    private String ERROR_MESSAGE;
    @ApiModelProperty(value = "报账系统凭证行号\n")
    private Long SOURCE_LINE_ID;
}
