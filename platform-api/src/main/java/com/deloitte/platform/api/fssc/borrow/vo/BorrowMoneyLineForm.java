package com.deloitte.platform.api.fssc.borrow.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description : BorrowMoneyLine新增修改form对象
 * @Modified :
 */
@ApiModel("新增BorrowMoneyLine表单")
@Data
public class BorrowMoneyLineForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "修改时必填")
    private Long id;
    @ApiModelProperty(value = "创建人姓名", required = true)
    private String createUserName;

    @ApiModelProperty(value = "行号", required = true)
    private Long lineNumber;

    @ApiModelProperty(value = "版本修改时必填")
    private Long version;


    @ApiModelProperty(value = "支出小类ID", required = true)
    @NotNull
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类code", required = true)
    private String subTypeCode;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "借款金额", required = true)
    @NotNull
    private BigDecimal borrowAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "借款主表ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "支出小类名称", required = true)
    @NotBlank
    private String subTypeName;

    @ApiModelProperty(value = "付款方式")
    @NotEmpty
    private String paymentType;
}
