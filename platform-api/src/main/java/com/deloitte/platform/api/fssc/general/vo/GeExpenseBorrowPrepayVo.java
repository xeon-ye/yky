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
 * @Description : GeExpenseBorrowPrepay返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeExpenseBorrowPrepayVo extends BaseVo {
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
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long documentId;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "单据类型名称")
    private String documentTypeName;

    @ApiModelProperty(value = "支出大类ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long mainTypeId;

    private String mainTypeCode;

    @ApiModelProperty(value = "支出大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "核销日期")
    private LocalDateTime verficationDate;

    @ApiModelProperty(value = "借款或预付金额")
    private BigDecimal bpAmount;

    @ApiModelProperty(value = "已核销金额")
    private BigDecimal verficatedAmount;

    @ApiModelProperty(value = "本次核销金额")
    private BigDecimal currentVerAmount;

    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "核销说明")
    private String verficationRemark;

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

    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "关联的单据ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long borrowOrPrepayId;

    @ApiModelProperty(value = "关联的单据类型 借款or对公付款")
    private String connectDocumentType;


    @ApiModelProperty(value = "关联的单据号 借款or对公付款")
    private String connectDocumentNum;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;

    @ApiModelProperty(value = "对方签约主体")
    private String subjectSuperficil;

    @ApiModelProperty(value = "约定付款批次")
    private String agreedPaymentLot;

    @ApiModelProperty(value = "履行计划ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long travelPlanId;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    @ApiModelProperty(value = "付款单银行账户财务会计科目")
    private String paymentAccountCode;

    @ApiModelProperty(value = "付款银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "付款银行账户")
    private String bankType;
}
