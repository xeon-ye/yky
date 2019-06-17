package com.deloitte.platform.api.fssc.advance.vo;

import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpensePaymentListForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description : BmAdvancePaymentInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增BmAdvancePaymentInfo表单")
@Data
public class AdvancePaymentInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人姓名",required = true)
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号",required = true)
    private String documentNum;

    @ApiModelProperty(value = "单据状态",required = true)
    private String documentStatus;

    @ApiModelProperty(value = "付款状态",required = true)
    private String payStatus;


    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目承担单位ID")
    private Long projectUnitId;

    @ApiModelProperty(value = "项目承担单位编码")
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;


    @ApiModelProperty(value = "支出大类ID" ,required = true)
    private Long mainTypeId;

    @ApiModelProperty(value = "大类编码",required = true)
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

    @ApiModelProperty(value = "归属单位名称",required = true)
    private String unitName;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;


    @ApiModelProperty(value = "支出大类名称",required = true)
    private String mainTypeName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;


    @ApiModelProperty(value = "付款方式",required = true)
    private String paymentType;


    @ApiModelProperty(value = "供应商",required = true)
    private String supplier;


    @ApiModelProperty(value = "供应商id",required = true)
    private Long supplierId;


    @ApiModelProperty(value = "供应商编码",required = true)
    private String supplierCode;

    @ApiModelProperty(value = "合同id")
    private Long contactNumberId;

    @ApiModelProperty(value = "合同编号")
    private String contactNumber;

    @ApiModelProperty(value = "合同名称")
    private String contactName;


    @ApiModelProperty(value = "支持附件数量",required = true)
    private Long supportFileNum;

    @ApiModelProperty(value = "合计金额本币位")
    private BigDecimal totalSumPosition;

    @ApiModelProperty(value = "已核销本币位")
    private BigDecimal hasVerAmountPosition;

    @ApiModelProperty(value = "未核销本币位")
    private BigDecimal noVerAmountPosition;

    @ApiModelProperty(value="是否附件驳回")
    private String isFileReject;

    @ApiModelProperty(value="驳回原因")
    private String rejectReason;

    @ApiModelProperty(value = "主键ID,待办里面的ID")
    private Long uuId;

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

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "组织PATH")
    private String orgPath;

    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;

    @ApiModelProperty(value = "项目预算编码")
    private String fsscCode;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    private String reSubmitType;

    @ApiModelProperty(value = "对公预付款行细信息")
    @Valid
    private List<AdvancePaymentLineForm> advancePaymentLineFormList;

    @ApiModelProperty(value="合同信息")
    @Valid
    private List<ContactDetailForm> contactDetailFormList;

    @ApiModelProperty(value="核销详情")
    @Valid
    private List<VerificationDetailForm> verificationDetailFormList;

    @ApiModelProperty(value="对公付款清单")
    @Valid
    private  List<GeExpensePaymentListForm> geExpensePaymentFormList;

    @ApiModelProperty(value = "借款工资 公务卡信息")
    @Valid
    private List<BmBorrowBankForm> bmBorrowBankForms;

    @ApiModelProperty(value = "文件id集合")
    private List<Long> fileIds;

    @ApiModelProperty(value = "是否预算检查超过80%")
    private String budgetWarningCheck;



}
