package com.deloitte.platform.api.fssc.advance.vo;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankVo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.api.fssc.file.vo.FileVo;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListVo;
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
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description : BmAdvancePaymentInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvancePaymentInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "ID")
    private Long id;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "更改人")
    private Long updateBy;


    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本号")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "归属部门id")
    private Long deptId;

    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "项目承担单位ID")
    private Long projectUnitId;

    @ApiModelProperty(value = "项目承担单位编码")
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "已核销金额")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "是否同意承诺书")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "支出大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    @ApiModelProperty(value = "供应商")
    private String supplier;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "合同id")
    private Long contactNumberId;

    @ApiModelProperty(value = "合同编号")
    private String contactNumber;

    @ApiModelProperty(value = "合同名称")
    private String contactName;

    @ApiModelProperty(value = "支持附件数量")
    private Long supportFileNum;

    @ApiModelProperty(value = "合计金额本币位")
    private BigDecimal totalSumPosition;

    @ApiModelProperty(value = "已核销本币位")
    private BigDecimal hasVerAmountPosition;

    @ApiModelProperty(value = "未核销本币位")
    private BigDecimal noVerAmountPosition;

    @ApiModelProperty(value = "项目预算会计科目")
    private String projectBudgetAccountCode;

    @ApiModelProperty(value = "${field.comment}")
    private String ext1;

    @ApiModelProperty(value = "${field.comment}")
    private String ext2;

    @ApiModelProperty(value = "${field.comment}")
    private String ext3;

    @ApiModelProperty(value = "${field.comment}")
    private String ext4;

    @ApiModelProperty(value = "${field.comment}")
    private String ext5;

    @ApiModelProperty(value = "${field.comment}")
    private String ext6;

    @ApiModelProperty(value = "${field.comment}")
    private String ext7;

    @ApiModelProperty(value = "${field.comment}")
    private String ext8;

    @ApiModelProperty(value = "${field.comment}")
    private String ext9;

    @ApiModelProperty(value = "${field.comment}")
    private String ext10;

    @ApiModelProperty(value = "${field.comment}")
    private String ext11;

    @ApiModelProperty(value = "${field.comment}")
    private String ext12;

    @ApiModelProperty(value = "${field.comment}")
    private String ext13;

    @ApiModelProperty(value = "${field.comment}")
    private String ext14;

    @ApiModelProperty(value = "${field.comment}")
    private String ext15;

    @ApiModelProperty(value="是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value="已支付金额")
    private BigDecimal amountPaid;

    @ApiModelProperty(value="未支付金额")
    private BigDecimal unpaidAmount;

    @ApiModelProperty("是否生成过付款单,提交时修改此状态 Y N")
    private String isGeneratePayOrder;
    @ApiModelProperty(value = "项目预算编码")
    private String fsscCode;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "组织PATH")
    private String orgPath;

    @ApiModelProperty(value = "对公预付款行信息")
    private List<AdvancePaymentLineVo> advanceMoneyLineList;

    @ApiModelProperty(value = "对公预付款合同信息")
    private List<ContactDetailVo> bmContactDetaiVos;

    @ApiModelProperty(value = "对公预付款核销")
    private List<VerificationDetailVo> verificationDetailVos;

    private List<FileVo> fileVos;

    @ApiModelProperty(value="对公付款清单")
    private  List<GeExpensePaymentListVo> geExpensePaymentFormList;

    @ApiModelProperty(value="对公审批历史")
    private List<BpmProcessTaskVo>  bpmProcessTaskVos;

    @ApiModelProperty(value = "借款公务卡信息")
    private List<BmBorrowBankVo> businessCards;
}
