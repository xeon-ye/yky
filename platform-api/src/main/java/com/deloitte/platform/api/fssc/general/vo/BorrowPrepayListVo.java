package com.deloitte.platform.api.fssc.general.vo;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BorrowPrepayListVo {

    @ApiModelProperty(value = "借款或预付款ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long borrowOrPrepayId;
    @ApiModelProperty(value = "借款或预付款单据编号")
    private String connectDocumentNum;
    @ApiModelProperty(value = "借款或预付款单据类型名称")
    private String  connectDocumentTypeName;
    @ApiModelProperty(value = "单据类型")
    private String connectDocumentType;
    @ApiModelProperty(value = "大类名称")
    private String mainTypeName;
    @ApiModelProperty(value = "大类code")
    private String mainTypeCode;
    @ApiModelProperty(value = "大类id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long mainTypeId;
    @ApiModelProperty(value = "行号")
    private Long lineNumber;
    @ApiModelProperty(value = "小类code")
    private String subTypeCode;
    @ApiModelProperty(value = "小类id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long subTypeId;
    @ApiModelProperty(value = "小类名称")
    private String subTypeName;
    @ApiModelProperty(value = "会计科目")
    private String accountCode;
    @ApiModelProperty(value = "预算会计科目")
    private String budgetAccountCode;
    @ApiModelProperty(value = "借款或预付款金额")
    private BigDecimal bpAmount;
    @ApiModelProperty(value = "已核销金额")
    private BigDecimal verficatedAmount;
    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "对方签约主体")
    private String subjectSuperficil;
    @ApiModelProperty(value = "约定付款批次")
    private String agreedPaymentLot;
    @ApiModelProperty(value = "核销日期")
    private LocalDateTime verficationDate;
    @ApiModelProperty(value = "付款方式")
    private String paymentType;
    @ApiModelProperty(value = "付款方式名称")
    private String paymentTypeText;
    @ApiModelProperty(value = "借款单行id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long lineId;

    @ApiModelProperty(value = "付款银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "付款银行账户")
    private String bankType;
}
