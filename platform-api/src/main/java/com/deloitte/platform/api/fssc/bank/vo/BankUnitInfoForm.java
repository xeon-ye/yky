package com.deloitte.platform.api.fssc.bank.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-26
 * @Description : BankUnitInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增BankUnitInfo表单")
@Data
public class BankUnitInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "单位code")
    private String unitCode;

    @ApiModelProperty(value = "单位类型")
    private String unitType;

    @ApiModelProperty(value = "统一信用代码")
    private String commonCreditCode;

    @ApiModelProperty(value = "银行账号")
    @NotBlank
    private String bankAccount;

    @ApiModelProperty(value = "银行账号名称")
    private String bankAccountName;

    @ApiModelProperty(value = "会计科目ID")
    private Long subjectId;

    @ApiModelProperty(value = "会计科目名称")
    private String subjectName;

    @ApiModelProperty(value = "会计科目代码")
    private String subjectCode;

    @ApiModelProperty(value = "预算会计科目ID")
    private Long budgetSubjectId;

    @ApiModelProperty(value = "预算会计科目名称")
    private String budgetSubjectName;

    @ApiModelProperty(value = "预算会计科目代码")
    private String budgetSubjectCode;

    @ApiModelProperty(value = "提交的审核状态")
    private String auditStatus;

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

    @ApiModelProperty(value = "账户性质")
    @NotBlank
    private String bankType;

    @ApiModelProperty(value = "是否银企直联")
    private String isBankDrectLink;

    @ApiModelProperty(value = "状态")
    private String isValid;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "银行ID")
    @NotNull
    private Long bankId;


    private String currencyCode;
}
