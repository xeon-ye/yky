package com.deloitte.platform.api.fssc.general.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description : GeExpensePaymentList新增修改form对象
 * @Modified :
 */
@ApiModel("新增GeExpensePaymentList表单")
@Data
public class GeExpensePaymentListForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "报账单id修改时必填")
    private Long id;

    @ApiModelProperty(value = "创建人姓名",required = true)
    private String createUserName;

    @ApiModelProperty(value = "版本修改时必填")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型表名")
    private String documentType;

    @ApiModelProperty(value = "付款金额",required = true)
    private BigDecimal payAmount;

    @ApiModelProperty(value = "供应商ID",required = true)
    private Long vendorId;

    @ApiModelProperty(value = "供应商代码",required = true)
    private String vendorCode;

    @ApiModelProperty(value = "供应商名称",required = true)
    private String vendorName;

    @ApiModelProperty(value = "银行ID")
    private Long bankId;

    @ApiModelProperty(value = "银行code",required = true)
    private String bankCode;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "统一社会信息代码",required = true)
    private String commonCreditCode;

    @ApiModelProperty(value = "银行名称",required = true)
    private String bankName;

    @ApiModelProperty(value = "银行账号",required = true)
    private String bankAccount ;

    @ApiModelProperty(value = "账户名称")
    private String accountName;

    @ApiModelProperty(value = "联行号",required = true)
    private String interBranchNumber;

    @ApiModelProperty(value = "银行账户ID")
     private Long bankUnitId;

    @ApiModelProperty(value = "行号",required = true)
    private Long lineNumber;

    @ApiModelProperty(value = "供应商付款金额")
    private BigDecimal venderPaymentAmount;

}
