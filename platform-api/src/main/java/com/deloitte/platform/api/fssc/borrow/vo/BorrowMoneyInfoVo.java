package com.deloitte.platform.api.fssc.borrow.vo;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.api.fssc.file.vo.FileVo;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayVo;
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
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description : BorrowMoneyInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowMoneyInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目预算会计科目")
    private String projectBudgetAccountCode;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;
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

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "归属单位ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long unitId;

    @ApiModelProperty(value = "归属部门ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long deptId;

    @ApiModelProperty(value = "项目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long projectId;

    @ApiModelProperty(value = "关联事项申请ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long applyForId;

    @ApiModelProperty(value = "支出大类ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long mainTypeId;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    @ApiModelProperty(value = "预计还款时间")
    private LocalDateTime repaymentTime;

    @ApiModelProperty(value = "借款金额合计原币")
    private BigDecimal borrowAmount;

    @ApiModelProperty(value = "已核销金额")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "借款金额合计原币(外币)")
    private BigDecimal borrowAmountOtherCurrency;

    @ApiModelProperty(value = "已核销金额(外币)")
    private BigDecimal hasVerAmountOtherCurrency;

    @ApiModelProperty(value = "未核销金额(外币)")
    private BigDecimal noVerAmountOtherCurrency;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "是否同意承诺书")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否包含工资卡")
    private String hasSalaryCard;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;
    @ApiModelProperty(value = "归属部门名称")
    private String deptName;
    @ApiModelProperty(value = "大类名称")
    private String mainTypeName;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "关联事项名称")
    private String applyForName;

    @ApiModelProperty(value = "借款行信息")
    private List<BorrowMoneyLineVo> borrowMoneyLineList;

    @ApiModelProperty(value = "借款工资卡信息")
    private List<BmBorrowBankVo> salaryCards;

    @ApiModelProperty(value = "借款公务卡信息")
    private List<BmBorrowBankVo> businessCards;

    @ApiModelProperty(value = "审批历史")
    private List<BpmProcessTaskVo> bpmProcessTaskVos;

    @ApiModelProperty(value = "核销明细")
    private List<GeExpenseBorrowPrepayVo> expenseBorrowPrepayVos;

    private List<FileVo> fileVos;

    @ApiModelProperty(value = "对公付款清单")
    private List<GeExpensePaymentListVo> paymentListVos;

    @ApiModelProperty(value = "归属单位编码")
    private String unitCode;

    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目承担单位编码")
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位ID")
    private Long projectUnitId;

    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;
    @ApiModelProperty(value = "大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;


    @ApiModelProperty(value = "未支付金额")
    private BigDecimal noPaidAmount;


    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "支持性附件数量")
    private Long attachCount;

    @ApiModelProperty("是否生成过付款单,提交时修改此状态 Y N")
    private String isGeneratePayOrder;
}
