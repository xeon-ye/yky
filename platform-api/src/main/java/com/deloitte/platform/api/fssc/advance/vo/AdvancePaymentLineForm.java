package com.deloitte.platform.api.fssc.advance.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description : BmAdvancePaymentLine新增修改form对象
 * @Modified :
 */
@ApiModel("新增BmAdvancePaymentLine表单")
@Data
public class AdvancePaymentLineForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    private String createUserName;

    @ApiModelProperty(value = "${field.comment}")
    private String ex1;

    @ApiModelProperty(value = "${field.comment}")
    private String ex2;

    @ApiModelProperty(value = "${field.comment}")
    private String ex3;

    @ApiModelProperty(value = "${field.comment}")
    private String ex4;

    @ApiModelProperty(value = "${field.comment}")
    private String ex5;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "支出小类编码",required = true)
    private String subTypeCode;

    @ApiModelProperty(value = "支出小类ID",required = true)
    private Long subTypeId;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预付金额")
    private BigDecimal prepaidAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "对公预付款Id")
    private Long documentId;

    @ApiModelProperty(value = "支出大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "支出小类名称",required = true)
    private String subTypeName;

    @ApiModelProperty(value="行号")
    private Long lineNumber;

    @ApiModelProperty(value="是否被报账单关联Y，N")
    private String isAssociated;

    @ApiModelProperty(value = "已核销金额")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "关联单据类型")
    private String documentType;

}
