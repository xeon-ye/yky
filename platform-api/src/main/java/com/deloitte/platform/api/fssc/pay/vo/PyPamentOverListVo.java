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
public class PyPamentOverListVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "报账单ID")
    private Long id;

    @ApiModelProperty(value = "是公务卡，对私付款还是对公付款")
    private  String connectDocumentLine;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

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

    @ApiModelProperty(value = "关联的主表单据名")
    private  String connectDocumentTypeName;

    @ApiModelProperty(value = "公务卡号")
    private String businessCardNum;

    @ApiModelProperty(value = "收款人")
    private String recieveEmpName;

    @ApiModelProperty(value = "工号/证件号")
    private String empNo;

    @ApiModelProperty(value = "汇入银行账号")
    private String bankAccount;

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

    @ApiModelProperty(value = "备注")
    private String remark;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "银行ID")
    private Long bankId;
    @ApiModelProperty(value = "银行code")
    private String bankCode;

    @ApiModelProperty(value = "开户行")
    private String bankBame;

    @ApiModelProperty(value = "统一社会信息代码")
    private String commonCreditCode;
    @ApiModelProperty(value = "账户名称")
    private String accountName;
   @ApiModelProperty(value="关联的单据行号")
    private  String connectNumber;
    @ApiModelProperty(value="交易附言")
    private  String transactionComments;
    @ApiModelProperty(value="单位id")
    private  Long unitId;
    @ApiModelProperty(value="单位名称")
    private  String unitName;
    @ApiModelProperty(value="单位编码")
    private  String unitCode;
    @ApiModelProperty(value = "收款人Id")
    private Long recieveId;
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "关联单据id")
    private Long connectDocumentId;
    @ApiModelProperty(value = "证件类型")
    private String certType;
    @ApiModelProperty(value = "证件号码")
    private String certNum;
    @ApiModelProperty(value = "支出大类code")
    private String mainTypeCode;

    @ApiModelProperty(value = "大类名称")
    private String mainTypeName;
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "关联的单据子表ID")
    private Long connectDocumentIdLine;

}
