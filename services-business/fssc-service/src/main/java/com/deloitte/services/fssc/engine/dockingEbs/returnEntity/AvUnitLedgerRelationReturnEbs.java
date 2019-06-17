package com.deloitte.services.fssc.engine.dockingEbs.returnEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@ApiModel(value="AvUnitLedgerRelationReturnEbs对象", description="用于对接ebs接口返回单位和账薄接口返回数据")
public class AvUnitLedgerRelationReturnEbs {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "导入标识，E导入失败；Y导入成功")
    private String importFlag ;

    @ApiModelProperty(value = "平衡值")
    private String balanceCode ;

    @ApiModelProperty(value = "报账系统回写的详细错误信息")
    private String errorMessage ;
}
