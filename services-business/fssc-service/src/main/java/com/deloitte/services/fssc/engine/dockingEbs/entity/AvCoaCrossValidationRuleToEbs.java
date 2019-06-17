package com.deloitte.services.fssc.engine.dockingEbs.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="AvCoaCrossValidationRuleToEbs对象", description="用于对接ebs接口推送交叉验证规则数据")
public class AvCoaCrossValidationRuleToEbs {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据类型(ACCOUNT/CURRENCY/VOUCHER)")
    private Long SOURCE_HEAD_ID;

    private Long SOURCE_LINE_ID;

    private Long LEDGER_ID;

    private String SEGMENT1;

    private String SEGMENT2;
    private String SEGMENT3;
    private String SEGMENT4;
    private String SEGMENT5;
    private String SEGMENT6;
    private String SEGMENT7;
    private String SEGMENT8;
    private String SEGMENT9;
    private String SEGMENT10;
    private String SEGMENT11;
    private String SEGMENT12;
    private String SEGMENT13;
    private String SEGMENT14;
    private String SEGMENT15;
    private String SEGMENT16;
    private String SEGMENT17;
    private String SEGMENT18;
    private String SEGMENT19;
    private String SEGMENT20;
}
