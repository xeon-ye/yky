package com.deloitte.services.fssc.business.travle.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hjy
 * @since 2019-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("TAS_TRAVEL_LINE")
@ApiModel(value="TasTravelLine对象", description="")
public class TasTravelLine extends Model<TasTravelLine> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("CREATE_USER_NAME")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "预留字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "预留字段6")
    @TableField("EXT6")
    private String ext6;

    @ApiModelProperty(value = "预留字段7")
    @TableField("EXT7")
    private String ext7;

    @ApiModelProperty(value = "预留字段8")
    @TableField("EXT8")
    private String ext8;

    @ApiModelProperty(value = "预留字段9")
    @TableField("EXT9")
    private String ext9;

    @ApiModelProperty(value = "预留字段10")
    @TableField("EXT10")
    private String ext10;

    @ApiModelProperty(value = "预留字段11")
    @TableField("EXT11")
    private String ext11;

    @ApiModelProperty(value = "预留字段12")
    @TableField("EXT12")
    private String ext12;

    @ApiModelProperty(value = "预留字段13")
    @TableField("EXT13")
    private String ext13;

    @ApiModelProperty(value = "预留字段14")
    @TableField("EXT14")
    private String ext14;

    @ApiModelProperty(value = "预留字段15")
    @TableField("EXT15")
    private String ext15;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

    @ApiModelProperty(value = "姓名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "工号")
    @TableField("JOB_NUMBER")
    private String jobNumber;

    @ApiModelProperty(value = "级别")
    @TableField("LEVEL_NAME")
    private String levelName;

    @ApiModelProperty(value = "差旅时间")
    @TableField("TRAVEL_TIME")
    private String travelTime;

    @ApiModelProperty(value = "差旅开始时间")
    @TableField(value = "TRAVEL_BEGIN_TIME")
    private LocalDateTime travelBeginTime;

    @ApiModelProperty(value = "差旅结束时间")
    @TableField(value = "TRAVEL_END_TIME")
    private LocalDateTime travelEndTime;

    @ApiModelProperty(value = "差旅开始地点")
    @TableField("LOCATION_BEG_TRAVEL")
    private String locationBegTravel;

    @ApiModelProperty(value = "差旅结束地点")
    @TableField("LOCATION_END_TRAVEL")
    private String locationEndTravel;

    @ApiModelProperty(value = "总额")
    @TableField("TOTAL_FOREHEAD")
    private BigDecimal totalForehead;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "天数")
    @TableField("DAY_NUM")
    private Long dayNum;

    @ApiModelProperty(value = "单价")
    @TableField("UNIT_PRICE")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "合计金额")
    @TableField("TOTAL_SUM")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "行号")
    @TableField("LINE_NUMBER")
    private Long lineNumber;

    @ApiModelProperty(value = "单价id")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单价类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "支出小类ID")
    @TableField("SUB_TYPE_ID")
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类CODE")
    @TableField("SUB_TYPE_CODE")
    private String subTypeCode;

    @ApiModelProperty(value = "支出小类NAME")
    @TableField("SUB_TYPE_NAME")
    private String subTypeName;

    @TableField("ACCOUNT_CODE")
    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "发票号")
    @TableField("INVOICE_NUMBER")
    private String invoiceNumber;

    @ApiModelProperty(value = "税额")
    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "税率")
    @TableField("TAX_RATE")
    private BigDecimal taxRate;

    @ApiModelProperty(value = "已核销金额")
    @TableField("HAS_VER_AMOUNT")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "付款方式")
    @TableField("PAYMENT_TYPE")
    private String paymentType;

    @ApiModelProperty(value = "未核销金额")
    @TableField("NO_VER_AMOUNT")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "不含税金额")
    @TableField("NO_TAX_AMOUNT")
    private BigDecimal noTaxAmount;


    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String EXT6 = "EXT6";

    public static final String EXT7 = "EXT7";

    public static final String EXT8 = "EXT8";

    public static final String EXT9 = "EXT9";

    public static final String EXT10 = "EXT10";

    public static final String EXT11 = "EXT11";

    public static final String EXT12 = "EXT12";

    public static final String EXT13 = "EXT13";

    public static final String EXT14 = "EXT14";

    public static final String EXT15 = "EXT15";

    public static final String VERSION = "VERSION";

    public static final String NAME = "NAME";

    public static final String JOB_NUMBER = "JOB_NUMBER";

    public static final String LEVEL_NAME = "LEVEL_NAME";

    public static final String TRAVEL_BEGIN_TIME = "TRAVEL_BEGIN_TIME";

    public static final String TRAVEL_END_TIME = "TRAVEL_END_TIME";

    public static final String LOCATION_BEG_TRAVEL = "LOCATION_BEG_TRAVEL";

    public static final String LOCATION_END_TRAVEL = "LOCATION_END_TRAVEL";

    public static final String TOTAL_FOREHEAD = "TOTAL_FOREHEAD";

    public static final String REMARK = "REMARK";

    public static final String DAY_NUM = "DAY_NUM";

    public static final String UNIT_PRICE = "UNIT_PRICE";

    public static final String TOTAL_SUM = "TOTAL_SUM";

    public static final String LINE_NUMBER = "LINE_NUMBER";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String SUB_TYPE_ID = "SUB_TYPE_ID";

    public static final String SUB_TYPE_CODE = "SUB_TYPE_CODE";

    public static final String SUB_TYPE_NAME = "SUB_TYPE_NAME";

    public static final String INVOICE_NUMBER = "INVOICE_NUMBER";

    public static final String TAX_AMOUNT = "TAX_AMOUNT";

    public static final String TAX_RATE = "TAX_RATE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
