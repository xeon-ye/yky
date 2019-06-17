package com.deloitte.services.fssc.engine.dockingEbs.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="AvUnitLedgerRelationFromEbs对象", description="用于对接ebs单位和账薄的关系接口数据")
public class AvUnitLedgerRelationFromEbs {
    @ApiModelProperty(value = "平衡段值(公司编码)")
    private String balanceCode;

    @ApiModelProperty(value = "账簿ID")
    private Long ledgerId;

}
