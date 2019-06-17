package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : yangyq
 * @Date : Create in 2019-04-16
 * @Description : TicketInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增TicketInfo表单")
@Data
public class TicketInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "销售方/购买方编号")
    private Long taxpayerCode;

    @ApiModelProperty(value = "销售方/购买方名称")
    private String taxpayer;

    @ApiModelProperty(value = "纳税人识别号")
    private String taxNum;

    @ApiModelProperty(value = "发票代码")
    private String invoiceCode;

    @ApiModelProperty(value = "发票号码")
    private String invoiceNum;

    @ApiModelProperty(value = "不含税金额")
    private BigDecimal amountExcludTax;

    @ApiModelProperty(value = "税率")
    private Double taxRate;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "价税合计")
    private BigDecimal amount;

    @ApiModelProperty(value = "开票日期")
    private LocalDateTime ticketTime;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private Long spareField5;

    @ApiModelProperty(value = "合同类型（收入类，支出类等）")
    private String contractType;

    @ApiModelProperty(value = "判断开票收票标记")
    private String type;

    @ApiModelProperty(value = "合同id")
    private Long contractId;
}
