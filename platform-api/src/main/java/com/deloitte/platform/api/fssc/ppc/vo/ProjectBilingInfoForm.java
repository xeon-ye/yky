package com.deloitte.platform.api.fssc.ppc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : ProjectBilingInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增ProjectBilingInfo表单")
@Data
public class ProjectBilingInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "无ID新增有id修改")
    private Long id;

    @ApiModelProperty(value = "创建人姓名申请人",required = true)
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "票据类型",required = true)
    private String paperType;

    @ApiModelProperty(value = "发票号码",required = true)
    private String invoiceNum;

    @ApiModelProperty(value = "发票代码",required = true)
    private String invoiceCode;

    @ApiModelProperty(value = "不含税金额",required = true)
    private BigDecimal noCostAmount;

    @ApiModelProperty(value = "税率",required = true)
    private BigDecimal cost;

    @ApiModelProperty(value = "税额",required = true)
    private BigDecimal costAmount;

    @ApiModelProperty(value = "总额",required = true)
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;

}
