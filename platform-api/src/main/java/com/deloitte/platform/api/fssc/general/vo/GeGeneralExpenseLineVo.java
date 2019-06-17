package com.deloitte.platform.api.fssc.general.vo;

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
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description : GeGeneralExpenseLine返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeGeneralExpenseLineVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "支出小类ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long subTypeId;

    private String subTypeCode;

    @ApiModelProperty(value = "支出小类名称")
    private String subTypeName;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "发票代码")
    private String invoiceCode;

    @ApiModelProperty(value = "发票号")
    private String invoiceNumber;

    @ApiModelProperty(value = "发票金额")
    private BigDecimal invoiceAmount;

    @ApiModelProperty(value = "税率")
    private BigDecimal tax;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "不含税金额")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

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
    @ApiModelProperty(value = "行号")
    private Long lineNumber;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;
}
