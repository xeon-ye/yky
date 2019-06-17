package com.deloitte.services.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 合同开票信息
 * </p>
 *
 * @author yangyq
 * @since 2019-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_TICKET_INFO")
@ApiModel(value="TicketInfo对象", description="合同开票信息")
public class TicketInfo extends Model<TicketInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "销售方/购买方编号")
    @TableField("TAXPAYER_CODE")
    private Long taxpayerCode;

    @ApiModelProperty(value = "销售方/购买方名称")
    @TableField("TAXPAYER")
    private String taxpayer;

    @ApiModelProperty(value = "纳税人识别号")
    @TableField("TAX_NUM")
    private String taxNum;

    @ApiModelProperty(value = "发票代码")
    @TableField("INVOICE_CODE")
    private String invoiceCode;

    @ApiModelProperty(value = "发票号码")
    @TableField("INVOICE_NUM")
    private String invoiceNum;

    @ApiModelProperty(value = "不含税金额")
    @TableField("AMOUNT_EXCLUD_TAX")
    private BigDecimal amountExcludTax;

    @ApiModelProperty(value = "税率")
    @TableField("TAX_RATE")
    private Double taxRate;

    @ApiModelProperty(value = "税额")
    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "价税合计")
    @TableField("AMOUNT")
    private BigDecimal amount;

    @ApiModelProperty(value = "开票日期")
    @TableField("TICKET_TIME")
    private LocalDateTime ticketTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    @TableField("IS_USED")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_1")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_2")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_3")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_4")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_5")
    private Long spareField5;

    @ApiModelProperty(value = "合同类型（收入类，支出类等）")
    @TableField("CONTRACT_TYPE")
    private String contractType;

    @ApiModelProperty(value = "判断开票收票标记")
    @TableField("TYPE")
    private String type;


    public static final String ID = "ID";

    public static final String TAXPAYER_CODE = "TAXPAYER_CODE";

    public static final String TAXPAYER = "TAXPAYER";

    public static final String TAX_NUM = "TAX_NUM";

    public static final String INVOICE_CODE = "INVOICE_CODE";

    public static final String INVOICE_NUM = "INVOICE_NUM";

    public static final String AMOUNT_EXCLUD_TAX = "AMOUNT_EXCLUD_TAX";

    public static final String TAX_RATE = "TAX_RATE";

    public static final String TAX_AMOUNT = "TAX_AMOUNT";

    public static final String AMOUNT = "AMOUNT";

    public static final String TICKET_TIME = "TICKET_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String IS_USED = "IS_USED";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    public static final String CONTRACT_TYPE = "CONTRACT_TYPE";

    public static final String TYPE = "TYPE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
