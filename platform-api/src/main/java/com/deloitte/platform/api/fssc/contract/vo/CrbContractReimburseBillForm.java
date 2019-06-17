package com.deloitte.platform.api.fssc.contract.vo;

import com.deloitte.platform.api.fssc.advance.vo.ContactDetailForm;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-25
 * @Description : CrbContractReimburseBill新增修改form对象
 * @Modified :
 */
@ApiModel("新增CrbContractReimburseBill表单")
@Data
public class CrbContractReimburseBillForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目预算会计科目")
    private String projectBudgetAccountCode;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人姓名",required = true)
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态",required = true)
    private String documentStatus;

    @ApiModelProperty(value = "付款状态",required = true)
    private String payStatus;

    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;@ApiModelProperty(value = "项目承担单位ID")

    private Long projectUnitId;

    @ApiModelProperty(value = "项目承担单位编码")
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;

    @ApiModelProperty(value = "支出大类ID",required = true)
    private Long mainTypeId;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "是否同意承诺书")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "大类名称",required = true)
    private String mainTypeName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "付款方式",required = true)
    private String paymentType;

    @ApiModelProperty(value = "供应商",required = true)
    private String supplier;

    @ApiModelProperty(value = "供应商ID",required = true)
    private Long supplierId;

    @ApiModelProperty(value = "合同编号ID",required = true)
    private Long contactNumberId;

    @ApiModelProperty(value = "合同编号",required = true)
    private String contactNumber;

    @ApiModelProperty(value = "支持附件数量",required = true)
    private Long supportFileNum;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "合计金额本币位")
    private BigDecimal totalSumPosition;

    @ApiModelProperty(value = "预留字段1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    private String ext5;

    @ApiModelProperty(value = "预留字段6")
    private String ext6;

    @ApiModelProperty(value = "预留字段7")
    private String ext7;

    @ApiModelProperty(value = "预留字段8")
    private String ext8;

    @ApiModelProperty(value = "预留字段9")
    private String ext9;

    @ApiModelProperty(value = "预留字段10")
    private String ext10;

    @ApiModelProperty(value = "预留字段11")
    private String ext11;

    @ApiModelProperty(value = "预留字段12")
    private String ext12;

    @ApiModelProperty(value = "预留字段13")
    private String ext13;

    @ApiModelProperty(value = "预留字段14")
    private String ext14;

    @ApiModelProperty(value = "预留字段15")
    private String ext15;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "供应商编码",required = true)
    private String supplierCode;

    @ApiModelProperty(value = "支出大类编码",required = true)
    private String mainTypeCode;

    @ApiModelProperty(value = "合同名称",required = true)
    private String contactName;

    @ApiModelProperty(value = "报销总金额")
    private BigDecimal totalAmountReimburse;

    @ApiModelProperty(value = "核销金额")
    private BigDecimal verAmount;

    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal actualPaymentAmount;


    @ApiModelProperty(value="是否附件驳回")
    private String isFileReject;

    @ApiModelProperty(value="驳回原因")
    private String rejectReason;

    @ApiModelProperty(value = "主键ID,待办里面的ID")
    private Long uuId;

    @ApiModelProperty(value = "项目预算编码")
    private String fsscCode;

    @ApiModelProperty(value="是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    @NotEmpty
    private String reSubmitType;

    @ApiModelProperty(value="已支付金额")
    private BigDecimal amountPaid;

    @ApiModelProperty(value="未支付金额")
    private BigDecimal unpaidAmount;

    @ApiModelProperty(value="入账时间")
    private LocalDateTime postingTime;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "组织PATH")
    private String orgPath;

    @ApiModelProperty(value = "核销金额公务卡")
    private BigDecimal verAmountBusiness;
    @ApiModelProperty(value = "核销金额对公付款")
    private BigDecimal verAmountExpense;
    @ApiModelProperty(value = "公务卡还款金额")
    private BigDecimal businessRepayment;
    @ApiModelProperty(value = "对公付款金额")
    private BigDecimal expensePayment;


    @ApiModelProperty(value = "合同报账单合同信息")
    private List<ContactDetailForm> bmContactDetaiForm;

  /*  @ApiModelProperty(value = "合同报账单关联预付款信息")
    private List<CrbAssocAdvancePaymentForm> crbAssocAdvancePaymentForm;*/

    @ApiModelProperty(value = "关联借款单对公预付款单")
    @Valid
    private List<GeExpenseBorrowPrepayForm> borrowPrepayFormList;

    @ApiModelProperty(value="合同报账单对公付款清单")
    private List<GeExpensePaymentListForm> geExpensePaymentFormList;

    @ApiModelProperty(value = "借款工资 公务卡信息")
    @Valid
    private List<BmBorrowBankForm> bmBorrowBankForms;

    @ApiModelProperty(value = "文件id集合")
    private List<Long> fileIds;

    @ApiModelProperty(value = "是否预算检查超过80%")
    private String budgetWarningCheck;


    @ApiModelProperty(value = "是否关联验证预付款  Y N")
    private String hasBorrowLines;

    @ApiModelProperty(value = "不含税金额")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;
}
