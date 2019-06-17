package com.deloitte.platform.api.fssc.general.vo;

import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoVo;
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
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description : GeExpensePaymentList返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeExpensePaymentListVo extends BaseVo {
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

    @ApiModelProperty(value = "单据类型表名")
    private String documentType;

    @ApiModelProperty(value = "付款金额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "供应商ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long vendorId;

    private String vendorCode;

    @ApiModelProperty(value = "供应商名称")
    private String vendorName;

    @ApiModelProperty(value = "银行ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long bankId;

    private String bankCode;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "账户名称")
    private String accountName;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;
    @ApiModelProperty(value = "统一社会信息代码")
    private String commonCreditCode;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "账户名称")
    private String bankAccount ;

    @ApiModelProperty(value = "联行号")
    private String interBranchNumber;

    @ApiModelProperty("银行信息")
    private List<BankUnitInfoVo> bankUnitInfoVos;

    @ApiModelProperty(value = "银行账户ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long bankUnitId;
}
