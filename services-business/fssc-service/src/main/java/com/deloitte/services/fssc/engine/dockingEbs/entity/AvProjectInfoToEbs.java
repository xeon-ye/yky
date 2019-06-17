package com.deloitte.services.fssc.engine.dockingEbs.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="AvProjectInfoToEbs对象", description="用于财务系统推送项目数据到ebs接口数据")
public class AvProjectInfoToEbs {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据类型")
    private String DATA_TYPE;
    @ApiModelProperty(value = "数据编码")
    private String DATA_CODE;
    @ApiModelProperty(value = "数据描述")
    private String DATA_DESC;
    @ApiModelProperty(value = "数据状态")
    private String DATA_STATUS;
}
