package com.deloitte.platform.api.fssc.pay.vo;

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
 * @Date : Create in 2019-04-22
 * @Description : PyPamentBusinessLine返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PyPamentBusinessLineVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "ID")
    private Long id;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "创建人ID申请人")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "付款单ID")
    private Long documentId;

    @ApiModelProperty(value = "付款单表名")
    private String documentType;

    @ApiModelProperty(value = "关联的单据编号")
    private String connectDocumentNum;

    @ApiModelProperty(value = "关联的单据类型")
    private String connectDocumentType;

    @ApiModelProperty(value = "交易金额")
    private BigDecimal transactionAmount;

    @ApiModelProperty(value = "交易日期")
    private LocalDateTime transactionDate;

    @ApiModelProperty(value = "付款金额/还款金额/收款金额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "公务卡号")
    private String businessCardNum;

    @ApiModelProperty(value = "收款人")
    private String recieveEmpName;

    @ApiModelProperty(value = "工号/证件号")
    private String empNo;

    @ApiModelProperty(value = "汇入银行账号")
    private String bankAccount;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty("汇入银行ID")
    private Long bankId;

    @ApiModelProperty(value = "汇入银行名称")
    private String bankName;

    @ApiModelProperty(value = "汇入分行")
    private String branchBankName;

    @ApiModelProperty(value = "联行号")
    private String interBranchNumber;

    @ApiModelProperty(value = "国际银行代码")
    private String bankInternationalCode;

    @ApiModelProperty(value = "支付状态")
    private String payStatus;

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

    @ApiModelProperty(value = "关联的单据行号")
    private Long connectNumber;

    @ApiModelProperty(value = "行类型提交类型 PUBLIC/PRIVATE/BUSINESS_CARD")
    private String  lineType;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "收款人Id")
    private Long recieveId;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "统一社会信用代码")
    private String commonCreditCode;

    @ApiModelProperty(value = "交易附言")
    private String transactionComments;

    @ApiModelProperty(value = "银行返回信息")
    private String bankReturnInfo;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "关联单据id")
    private Long connectDocumentId;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "关联的单据子表ID")
    private Long connectDocumentIdLine;
    @ApiModelProperty(value = "关联的单据名")
    private  String connectDocumentTypeName;

}
