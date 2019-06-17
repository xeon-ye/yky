package com.deloitte.platform.api.fssc.general.vo;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankVo;
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
 * @Description : GeGeneralExpense返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeGeneralExpenseVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目预算会计科目")
    private String projectBudgetAccountCode;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;

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

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;

    private String unitCode;

    @ApiModelProperty(value = "归属部门ID")
    private Long deptId;

    private String deptCode;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long projectId;

    private String projectCode;
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long projectUnitId;

    private String projectUnitCode;

    private String projectUnitName;

    @ApiModelProperty(value = "关联事项申请ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long applyForId;

    @ApiModelProperty(value = "关联事项名称")
    private String applyForName;

    @ApiModelProperty(value = "大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "支出大类ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long mainTypeId;

    private String mainTypeCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "支持性附件数量")
    private Long attachCount;

    @ApiModelProperty(value = "报账合计金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "报账合计金额(外币)")
    private BigDecimal totalAmountOtherCurrency;

    @ApiModelProperty(value = "报销总金额")
    private BigDecimal expenseAmount;

    @ApiModelProperty(value = "核销金额已废弃")
    private BigDecimal verificationAmount;

    @ApiModelProperty(value = "核销金额-公务卡")
    private BigDecimal verAmountBusiness;

    @ApiModelProperty(value = "核销金额-工资卡")
    private BigDecimal verAmountSarlary;

    @ApiModelProperty(value = "核销金额-对公付款")
    private BigDecimal verAmountPublic;


    @ApiModelProperty(value = "支付至工资卡金额")
    private BigDecimal paySalaryAmount;

    @ApiModelProperty(value = "公务卡还款金额")
    private BigDecimal businussAmount;

    @ApiModelProperty(value = "对公付款金额")
    private BigDecimal payCompanyAmount;

    @ApiModelProperty(value = "借款工资卡信息")
    private List<BmBorrowBankVo> salaryCards;

    @ApiModelProperty(value = "借款公务卡信息")
    private List<BmBorrowBankVo> businessCards;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;

    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

    @ApiModelProperty(value = "未支付金额")
    private BigDecimal noPaidAmount;

    @ApiModelProperty("是否生成过付款单,提交时修改此状态 Y N")
    private String isGeneratePayOrder;

    @ApiModelProperty(value = "工资卡公务卡集合")
    private List<BmBorrowBankVo> bankVosList;

    @ApiModelProperty(value = "关联借款单对公预付款单")
    private List<GeExpenseBorrowPrepayVo> borrowPrepayVoList;

    @ApiModelProperty(value = "对公付款清单")
    private List<GeExpensePaymentListVo> geExpensePaymentList;

    @ApiModelProperty(value = "行明细")
    private List<GeGeneralExpenseLineVo> generalExpenseLines;

    @ApiModelProperty(value = "审批历史")
    private List<BpmProcessTaskVo> bpmProcessTaskVos;

    @ApiModelProperty(value = "不含税金额")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "税率")
    private BigDecimal tax;
}
