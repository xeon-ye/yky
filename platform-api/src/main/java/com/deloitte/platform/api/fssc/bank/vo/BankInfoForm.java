package com.deloitte.platform.api.fssc.bank.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-26
 * @Description : BankInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增BankInfo表单")
@Data
public class BankInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "银行代码")
    private String bankCode;

    @ApiModelProperty(value = "银行名称")
    @NotBlank
    private String bankName;

    @ApiModelProperty(value = "银行简称")
    private String bankSimpleName;

    @ApiModelProperty(value = "分行名称")
    private String branchBankName;

    @ApiModelProperty(value = "联行号")
    private String interBranchNumber;

    @ApiModelProperty(value = "银行国际代码")
    @NotBlank
    private String bankInternationalCode;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

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

    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "状态")
    private String isValid;
    @ApiModelProperty(value = "提交审核状态")
    private String auditStatus;
}
