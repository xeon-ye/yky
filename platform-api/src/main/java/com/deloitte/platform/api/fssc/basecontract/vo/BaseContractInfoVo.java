package com.deloitte.platform.api.fssc.basecontract.vo;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description : BaseContractInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseContractInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "合同ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long contractId;

    @ApiModelProperty(value = "合同编号")
    private String contractNo;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "对方签约主体名称")
    private String sideSubjectName;

    @ApiModelProperty(value = "我方签约主体名称")
    private String ourSubjectName;

    @ApiModelProperty(value = "生效日期")
    private LocalDateTime executeStatueTime;

    @ApiModelProperty(value = "履行期限开始时间")
    private LocalDateTime executeStartTime;

    @ApiModelProperty(value = "履行期限结束时间")
    private LocalDateTime executeEndTime;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "合同状态/审批状态")
    private String statue;

    @ApiModelProperty(value = "主办部门")
    private String org;

    @ApiModelProperty(value = "部门code")
    private String orgCode;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "是否缴纳印花税")
    private String isPayStampDuty;

    @ApiModelProperty(value = "是否涉外合同")
    private String isForeignContract;

    @ApiModelProperty(value = "合同类型(印花税)")
    private String contractTypeStampDuty;

    @ApiModelProperty(value = "应税凭证名称")
    private String nameOfTaxable;

    @ApiModelProperty(value = "印花税税额")
    private BigDecimal stampDuty;

    @ApiModelProperty(value = "通用印花税税率")
    private BigDecimal generalStampDutyRate;

    @ApiModelProperty(value = "计税金额或件数")
    private BigDecimal taxAmountOrPieces;

    @ApiModelProperty("合同含税金额/合同总金额")
    private BigDecimal amountIncludTax;

    @ApiModelProperty(value = "预留字段1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2")
    private String ex2;

    @ApiModelProperty(value = "预留字段3")
    private String ex3;

    @ApiModelProperty(value = "预留字段4")
    private String ex4;

    @ApiModelProperty(value = "预留字段5")
    private String ex5;

    @ApiModelProperty(value = "预留字段6")
    private String ex6;

    @ApiModelProperty(value = "预留字段7")
    private String ex7;

    @ApiModelProperty(value = "预留字段8")
    private String ex8;

    @ApiModelProperty(value = "预留字段9")
    private String ex9;

    @ApiModelProperty(value = "预留字段10")
    private String ex10;

    @ApiModelProperty(value = "预留字段11")
    private String ex11;

    @ApiModelProperty(value = "预留字段12")
    private String ex12;

    @ApiModelProperty(value = "预留字段13")
    private String ex13;

    @ApiModelProperty(value = "预留字段14")
    private String ex14;

    @ApiModelProperty(value = "预留字段15")
    private String ex15;

    @ApiModelProperty(value = "对方签约主体ID")
    private Long sideSubjectId;

    @ApiModelProperty(value = "我方签约主体ID")
    private Long ourSubjectId;

    @ApiModelProperty(value = "合同状态/审批状态中文")
    private String statueName;
}
