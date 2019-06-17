package com.deloitte.platform.api.fssc.general.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description : GeGeneralExpenseLine新增修改form对象
 * @Modified :
 */
@ApiModel("新增GeGeneralExpenseLine表单")
@Data
public class GeGeneralExpenseLineForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "报账单id修改时必填")
    private Long id;

    @ApiModelProperty(value = "创建人姓名",required = true)
    private String createUserName;



    @ApiModelProperty(value = "版本修改时必填")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
     private String documentType;

    @ApiModelProperty(value = "支出小类ID",required = true)
    @NotNull
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类code",required = true)
    private String subTypeCode;

    @ApiModelProperty(value = "支出小类名称",required = true)
    @NotBlank
    private String subTypeName;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "发票代码",required = true)
    private String invoiceCode;

    @ApiModelProperty(value = "发票号",required = true)
    private String invoiceNumber;

    @ApiModelProperty(value = "发票金额",required = true)
    @NotNull
    private BigDecimal invoiceAmount;

    @ApiModelProperty(value = "税率",required = true)
    private BigDecimal tax;

    @ApiModelProperty(value = "不含税金额")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value = "税额",required = true)
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "付款方式")
    @NotBlank
    private String paymentType;

}
