package com.deloitte.platform.api.fssc.travle.vo;

import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-08
 * @Description : TasTravelReimburse新增修改form对象
 * @Modified :
 */
@ApiModel("新增TasTravelReimburse表单")
@Data
public class TasTravelReimburseForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目预算会计科目")
    private String projectBudgetAccountCode;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目承担单位ID")
    private Long projectUnitId;

    @ApiModelProperty(value = "项目承担单位编码")
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;

    @ApiModelProperty(value = "支出大类ID",required = true)
    private Long mainTypeId;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "是否同意承诺书")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "大类名称",required = true)
    private String mainTypeName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

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

    @ApiModelProperty(value = "支出大类编码",required = true)
    private String mainTypeCode;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    @ApiModelProperty(value = "出差人数")
    private Long travelPeopleNum;

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

    @ApiModelProperty(value = "未支付金额")
    private BigDecimal noPaidAmount;

    @ApiModelProperty(value = "关联差旅申请CODE")
    private String applyForCode;

    @ApiModelProperty(value = "关联差旅申请id")
    private Long applyForId;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "支持附件数量",required = true)
    private Long supportFileNum;

    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "报销总金额")
    private BigDecimal expenseAmount;

    @ApiModelProperty(value = "公务卡还款金额")
    private BigDecimal businussAmount;

    @ApiModelProperty(value = "核销金额")
    private BigDecimal verificationAmount;

    @ApiModelProperty(value = "对公付款金额")
    private BigDecimal payCompanyAmount;

    @ApiModelProperty(value = "支付至工资卡金额")
    private BigDecimal paySalaryAmount;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "合计金额本币位")
    private BigDecimal totalSumPosition;

    @ApiModelProperty(value = "组织PATH")
    private String orgPath;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    @NotEmpty
    private String reSubmitType;

    @ApiModelProperty(value = "核销金额公务卡")
    private BigDecimal verAmountBusiness;
    @ApiModelProperty(value = "核销金额对公付款")
    private BigDecimal verAmountExpense;
    @ApiModelProperty(value = "核销金额工资卡")
    private BigDecimal verAmountSalary;

    @ApiModelProperty(value = "不含税金额")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "工资卡公务卡集合")
    private List<BmBorrowBankForm> bankFormsList;

    @ApiModelProperty(value = "关联借款单对公预付款单")
    private List<GeExpenseBorrowPrepayForm> borrowPrepayFormList;

    @ApiModelProperty(value = "对公付款清单")
    private List<GeExpensePaymentListForm> geExpensePaymentList;

    @ApiModelProperty(value="行明细")
    private List<TasTravelLineForm> tasTravelLineForm;

    @ApiModelProperty(value = "文件id集合")
    private List<Long> fileIds;

    @ApiModelProperty(value = "是否预算检查超过80%")
    private String budgetWarningCheck;
    @ApiModelProperty(value = "项目预算编码")
    private String fsscCode;
    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;


}
