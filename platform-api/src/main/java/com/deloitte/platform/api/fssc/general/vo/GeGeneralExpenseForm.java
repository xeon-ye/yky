package com.deloitte.platform.api.fssc.general.vo;

import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description : GeGeneralExpense新增修改form对象
 * @Modified :
 */
@ApiModel("新增GeGeneralExpense表单")
@Data
public class GeGeneralExpenseForm extends BaseForm {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "报账单id修改时必填")
    private Long id;

    @ApiModelProperty(value = "项目预算会计科目")
    private String projectBudgetAccountCode;


    @ApiModelProperty(value = "版本修改时必填")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "归属部门ID",required = true)
    private Long deptId;

    @ApiModelProperty(value = "部门code",required = true)
    private String deptCode;

    @ApiModelProperty(value = "归属单位名称",required = true)
    @NotBlank
    private String unitName;

    @ApiModelProperty(value = "归属部门名称",required = true)
    private String deptName;

    @ApiModelProperty(value = "付款方式",required = true)
    private String paymentType;

    @ApiModelProperty(value = "项目名称",required = true)
    private String projectName;

    @ApiModelProperty(value = "项目ID",required = true)
    private Long projectId;

    @ApiModelProperty(value = "项目code",required = true)
    private String projectCode;

    @ApiModelProperty(value = "项目承担单位code",required = true)
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位名称",required = true)
    private String projectUnitName;

    @ApiModelProperty(value = "项目承担单位ID",required = true)
    private Long projectUnitId;

    @ApiModelProperty(value = "关联事项申请ID",required = true)
    private Long applyForId;

    @ApiModelProperty(value = "关联事项名称",required = true)
    private String applyForName;

    @ApiModelProperty(value = "大类名称",required = true)
    @NotBlank
    private String mainTypeName;

    @ApiModelProperty(value = "支出大类ID",required = true)
    @NotNull
    private Long mainTypeId;

    @ApiModelProperty(value = "支出大类code",required = true)
    private String mainTypeCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "币种",required = true)
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "支持性附件数量",required = true)
    @NotNull
    private Long attachCount;

    @ApiModelProperty(value = "报账合计金额",required = true)
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "报账合计金额(外币)")
    private BigDecimal totalAmountOtherCurrency;

    @ApiModelProperty(value = "报销总金额")
    private BigDecimal expenseAmount;

    @ApiModelProperty(value = "核销金额")
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

    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    private String reSubmitType;

    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

    @ApiModelProperty(value = "未支付金额")
    private BigDecimal noPaidAmount;

    @ApiModelProperty(value = "工资卡公务卡集合")
    @Valid
    private List<BmBorrowBankForm> bankFormList;

    @ApiModelProperty(value = "关联借款单对公预付款单")
    @Valid
    private List<GeExpenseBorrowPrepayForm> borrowPrepayFormList;

    @ApiModelProperty(value = "对公付款清单")
    @Valid
    private List<GeExpensePaymentListForm> geExpensePaymentList;

    @ApiModelProperty(value = "行明细",required = true)
    @Valid
    private List<GeGeneralExpenseLineForm> generalExpenseLines;

    @ApiModelProperty(value = "文件id集合")
    private List<Long> fileIds;

    @ApiModelProperty(value = "是否预算检查超过80%")
    private String budgetWarningCheck;

    @ApiModelProperty(value = "是否关联验证借款  Y N")
    private String hasBorrowLines;

    @ApiModelProperty(value = "不含税金额")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "税率")
    private BigDecimal tax;
}
