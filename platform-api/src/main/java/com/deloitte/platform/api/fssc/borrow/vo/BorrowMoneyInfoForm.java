package com.deloitte.platform.api.fssc.borrow.vo;

import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description : BorrowMoneyInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增BorrowMoneyInfo表单")
@Data
public class BorrowMoneyInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "修改时必填")
    private Long id;

    @ApiModelProperty(value = "项目预算会计科目")
    private String projectBudgetAccountCode;

    @ApiModelProperty(value = "版本(数字类型)修改时必填选填")
    private Long version;

    @ApiModelProperty(value = "单据状态新增时默认")
    private String documentStatus;

    @ApiModelProperty(value = "付款状态新增时默认")
    private String payStatus;

    @ApiModelProperty(value = "项目ID",required = true)
    private Long projectId;

    @ApiModelProperty(value = "项目编码",required = true)
    private String projectCode;

    @ApiModelProperty(value = "项目承担单位编码",required = true)
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位ID",required = true)
    private Long projectUnitId;

    @ApiModelProperty(value = "项目承担单位名称",required = true)
    private String projectUnitName;

    @ApiModelProperty(value = "关联事项申请ID",required = true)
    private Long applyForId;

    @ApiModelProperty(value = "支出大类ID",required = true)
    @NotNull(message = "支出大类不能为空")
    private Long mainTypeId;

    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;

    @ApiModelProperty(value = "大类编码",required = true)
    private String mainTypeCode;

    @ApiModelProperty(value = "付款方式",required = true)
    private String paymentType;

    @ApiModelProperty(value = "预计还款时间yyyy-MM-dd HH:mm:ss",notes = "必填yyyy-MM-dd HH:mm:ss",required = true)
    @NotNull
    private LocalDateTime repaymentTime;

    @ApiModelProperty(value = "借款金额合计原币",notes = "数字必填",required = true)
    @NotNull
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

    @ApiModelProperty(value = "币种",required = true)
    private String currencyCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "大类名称",required = true)
    @NotBlank
    private String mainTypeName;

    @ApiModelProperty(value = "项目名称",required = true)
    private String projectName;

    @ApiModelProperty(value = "关联事项名称",required = true)
    private String applyForName;

    @ApiModelProperty(value = "借款行信息",required = true)
    @Valid
    private List<BorrowMoneyLineForm> borrowMoneyLineList;

    @ApiModelProperty(value = "借款工资 公务卡信息")
    @Valid
    private List<BmBorrowBankForm> bmBorrowBankForms;

    @ApiModelProperty(value = "文件id集合")
    private List<Long> fileIds;

    @ApiModelProperty(value = "对公付款清单")
    private List<GeExpensePaymentListForm> geExpensePaymentListForms;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    @NotEmpty
    private String reSubmitType="FIRST_SUBMIT";

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;


    @ApiModelProperty(value = "未支付金额")
    private BigDecimal noPaidAmount;


    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;


    @ApiModelProperty(value = "支持性附件数量")
    private Long attachCount;

    @ApiModelProperty(value = "是否预算检查超过80%")
    private String budgetWarningCheck;

}
