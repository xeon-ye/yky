package com.deloitte.services.fssc.engine.dockingEbs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@ApiModel(value="AvAccountInfoToEbs对象", description="用于对接ebs接口数据")
public class AvAccountInfoToEbs {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "报账系统凭证批次号")
    private Long SOURCE_BATCH_ID ;

    @ApiModelProperty(value = "报账系统凭证头号")
    private Long  SOURCE_HEAD_ID;

    @ApiModelProperty(value = "报账系统凭证行号")
    private Long  SOURCE_LINE_ID;

    @ApiModelProperty(value = "账簿ID")
    private Long  LEDGER_ID;
    @ApiModelProperty(value = "会计日期,YYYY-MM-DD")
    private Date ACCOUNTING_DATE;
    @ApiModelProperty(value = "币种，必须与EBS系统币种代码一致，如：人民币CNY")
    private String CURRENCY_CODE;
    @ApiModelProperty(value = "数据录入日期,YYYY-MM-DD HH:MM:SS")
    private Date DATE_CREATED;
    @ApiModelProperty(value = "余额类型,A-实际;B-预算;E-保留款,插入固定值A")
    private String  ACTUAL_FLAG;
    @ApiModelProperty(value = "凭证类别")
    private String   USER_JE_CATEGORY_NAME;
    @ApiModelProperty(value = "凭证来源")
    private String  USER_JE_SOURCE_NAME;
    @ApiModelProperty(value = "汇率日期，YYYY-MM-DD")
    private Date  CURRENCY_CONVERSION_DATE;
    @ApiModelProperty(value = "汇率类型，插入固定值CORPORATE")
    private String  USER_CURRENCY_CONVERSION_TYPE;
    @ApiModelProperty(value = "汇率")
    private BigDecimal CURRENCY_CONVERSION_RATE;
    @ApiModelProperty(value = "SEGMENT1-主体段")
    private String  SEGMENT1;
    @ApiModelProperty(value = "SEGMENT2-部门段")
    private String  SEGMENT2;
    @ApiModelProperty(value = "SEGMENT3-科目段")
    private String  SEGMENT3;
    @ApiModelProperty(value = "SEGMENT4-项目段")
    private String  SEGMENT4;
    @ApiModelProperty(value = "SEGMENT5-往来段")
    private String  SEGMENT5;
    @ApiModelProperty(value = "SEGMENT6-备用1段")
    private String  SEGMENT6;
    @ApiModelProperty(value = "SEGMENT7-备用2段")
    private String  SEGMENT7;
    @ApiModelProperty(value = "SEGMENT8-备用3段")
    private String  SEGMENT8;
    @ApiModelProperty(value = "SEGMENT9-备用4段")
    private String  SEGMENT9;
    @ApiModelProperty(value = "SEGMENT10-备用5段")
    private String  SEGMENT10;
    @ApiModelProperty(value = "备用")
    private String  SEGMENT11;
    @ApiModelProperty(value = "备用")
    private String  SEGMENT12;
    @ApiModelProperty(value = "备用")
    private String  SEGMENT13;
    @ApiModelProperty(value = "备用")
    private String  SEGMENT14;
    @ApiModelProperty(value = "备用")
    private String  SEGMENT15;
    @ApiModelProperty(value = "备用")
    private String  SEGMENT16;
    @ApiModelProperty(value = "备用")
    private String  SEGMENT17;
    @ApiModelProperty(value = "备用")
    private String  SEGMENT18;
    @ApiModelProperty(value = "备用")
    private String  SEGMENT19;
    @ApiModelProperty(value = "备用")
    private String  SEGMENT20;
    @ApiModelProperty(value = "原币借方金额，借方和贷方只能一方有数")
    private BigDecimal  ENTERED_DR;
    @ApiModelProperty(value = "原币贷方金额，借方和贷方只能一方有数")
    private BigDecimal  ENTERED_CR;
    @ApiModelProperty(value = "本位币借方金额，借方和贷方只能一方有数")
    private BigDecimal  ACCOUNTED_DR;
    @ApiModelProperty(value = "本位币贷方金额，借方和贷方只能一方有数")
    private BigDecimal  ACCOUNTED_CR;
    @ApiModelProperty(value = "批名，传空即可")
    private String  REFERENCE1;
    @ApiModelProperty(value = "批说明，传空即可")
    private String  REFERENCE2;
    @ApiModelProperty(value = "必须为空")
    private String  REFERENCE3;
    @ApiModelProperty(value = "凭证名")
    private String  REFERENCE4;
    @ApiModelProperty(value = "凭证头说明")
    private String  REFERENCE5;
    @ApiModelProperty(value = "日记账分录参考，必须为空")
    private String  REFERENCE6;
    @ApiModelProperty(value = "日记账分录冲销标志，必须为空")
    private String  REFERENCE7;
    @ApiModelProperty(value = "分录类型，区分财预（C/Y）")
    private String  REFERENCE8;
    @ApiModelProperty(value = "日记账冲销方法，为空即可，MacRP核心系统指定默认的冲销方法")
    private String  REFERENCE9;
    @ApiModelProperty(value = "凭证行说明")
    private String  REFERENCE10;
    @ApiModelProperty(value = "期间，会计记账日期所在月，YYYY-MM")
    private String  PERIOD_NAME;


}
