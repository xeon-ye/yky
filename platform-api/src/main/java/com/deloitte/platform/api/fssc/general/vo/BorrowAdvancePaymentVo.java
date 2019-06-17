package com.deloitte.platform.api.fssc.general.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BorrowAdvancePaymentVo {


    @ApiModelProperty(value = "单据ID借款或预付款")
    private Long borrowOrPrepayId;

    @ApiModelProperty(value = "单据编号借款或预付款")
    private String connectDocumentNum;

    @ApiModelProperty(value = "单据类型借款或预付款")
    private String connectDocumentType;


    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "支出大类CODE")
    private String mainTypeCode;

    @ApiModelProperty(value = "支出大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "支出小类ID")
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类CODE")
    private String subTypeCode;

    @ApiModelProperty(value = "支出小类名称")
    private String subTypeName;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;

    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "已核销金额")
    private BigDecimal verficatedAmount;

    @ApiModelProperty(value = "借款或预付金额")
    private BigDecimal bpAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

}
