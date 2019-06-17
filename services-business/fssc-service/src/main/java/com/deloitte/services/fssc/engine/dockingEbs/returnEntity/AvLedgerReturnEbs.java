package com.deloitte.services.fssc.engine.dockingEbs.returnEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="AvAccountInfoToEbs对象", description="用于对接ebs接口数据")

public class AvLedgerReturnEbs {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "导入标识，E导入失败；Y导入成功")
    private String importFlag ;

    @ApiModelProperty(value = "账簿ID")
    private Long  ledgerId;

    @ApiModelProperty(value = "报账系统回写的详细错误信息")
    private String errorMessage ;
}
