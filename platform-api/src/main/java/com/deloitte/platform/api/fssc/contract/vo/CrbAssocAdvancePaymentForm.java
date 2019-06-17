package com.deloitte.platform.api.fssc.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-25
 * @Description : CrbAssocAdvancePayment新增修改form对象
 * @Modified :
 */
@ApiModel("新增CrbAssocAdvancePayment表单")
@Data
public class CrbAssocAdvancePaymentForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号",required = true)
    private String documentNum;

    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "预留字段1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    private String ext5;

    @ApiModelProperty(value = "预留字段6")
    private String ext6;

    @ApiModelProperty(value = "预留字段7")
    private String ext7;

    @ApiModelProperty(value = "预留字段8")
    private String ext8;

    @ApiModelProperty(value = "预留字段9")
    private String ext9;

    @ApiModelProperty(value = "预留字段10")
    private String ext10;

    @ApiModelProperty(value = "预留字段11")
    private String ext11;

    @ApiModelProperty(value = "预留字段12")
    private String ext12;

    @ApiModelProperty(value = "预留字段13")
    private String ext13;

    @ApiModelProperty(value = "预留字段14")
    private String ext14;

    @ApiModelProperty(value = "预留字段15")
    private String ext15;

    @ApiModelProperty(value = "支出大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "关联表名")
    private String documentType;

    @ApiModelProperty(value = "关联ID")
    private Long documentId;

    @ApiModelProperty(value = "已核销金额")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "本次核销金额")
    private BigDecimal thisVerAmount;

    @ApiModelProperty(value = "核销说明")
    private String verInstructions;

    @ApiModelProperty(value = "预付金额")
    private BigDecimal prepaidAmount;

    @ApiModelProperty(value = "核销日期")
    private LocalDateTime cancellationTime;

    @ApiModelProperty(value = "支出小类ID")
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类CODE")
    private String subTypeCode;

    @ApiModelProperty(value = "支出小类NAME")
    private String subTypeName;

    @ApiModelProperty(value = "行号",required = true)
    private Long lineNumber;

}
