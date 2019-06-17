package com.deloitte.platform.api.fssc.ppc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : ProjectRecieveDetail新增修改form对象
 * @Modified :
 */
@ApiModel("新增ProjectRecieveDetail表单")
@Data
public class ProjectRecieveDetailForm extends BaseForm {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "无ID新增有id修改")
    private Long id;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据ID收款单ID",required = true)
    private Long connectDocumentId;

    @ApiModelProperty(value = "单据类型收款单表名",required = true)
    private String connectDocumentType;

    @ApiModelProperty(value = "收款单编号单据编号",required = true)
    private String connectDocumentNum;

    @ApiModelProperty(value = "款项收入小类ID",required = true)
    private Long inComeSubTypeId;

    @ApiModelProperty(value = "款项收入小类CODE",required = true)
    private String inComeSubTypeCode;

    @ApiModelProperty(value = "款项收入小类名称",required = true)
    private String inComeSubTypeName;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "收入确认金额",required = true)
    private BigDecimal recieveConfirmAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;

    @ApiModelProperty(value = "款项收入小类科目CODE",required = true)
    private String inComeSubSubjectCode;

    @ApiModelProperty(value = "关联收款行表id")
    private Long connectRecieveLineId;

    @ApiModelProperty(value = "不含税金额")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value = "税率")
    private BigDecimal tax;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "收款时间")
    private LocalDateTime recieveTime;

}
