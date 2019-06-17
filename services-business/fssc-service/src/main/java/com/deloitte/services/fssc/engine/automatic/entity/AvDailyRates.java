package com.deloitte.services.fssc.engine.automatic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * <p>
 * 记录EBS每天的实时汇率
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_DAILY_RATES")
@ApiModel(value="AvDailyRates对象", description="记录EBS每天的实时汇率")
public class AvDailyRates extends Model<AvDailyRates> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "币种从")
    @TableField("FROM_CURRENCY")
    private String fromCurrency;

    @ApiModelProperty(value = "币种至")
    @TableField("TO_CURRENCY")
    private String toCurrency;

    @ApiModelProperty(value = "折换日期")
    @TableField("CONVERSION_DATE")
    private LocalDateTime conversionDate;

    @ApiModelProperty(value = "折换类型")
    @TableField("CONVERSION_TYPE")
    private String conversionType;

    @ApiModelProperty(value = "‘币种从’到‘币种至’的汇率")
    @TableField("CONVERSION_RATE")
    private BigDecimal conversionRate;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "预留字段1")
    @TableField("ETX_1")
    private String etx1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("ETX_2")
    private String etx2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("ETX_3")
    private String etx3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("ETX_4")
    private String etx4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("ETX_5")
    private String etx5;


    public static final String ID = "ID";

    public static final String FROM_CURRENCY = "FROM_CURRENCY";

    public static final String TO_CURRENCY = "TO_CURRENCY";

    public static final String CONVERSION_DATE = "CONVERSION_DATE";

    public static final String CONVERSION_TYPE = "CONVERSION_TYPE";

    public static final String CONVERSION_RATE = "CONVERSION_RATE";

    public static final String CREATE_DATE = "CREATE_DATE";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String ETX_1 = "ETX_1";

    public static final String ETX_2 = "ETX_2";

    public static final String ETX_3 = "ETX_3";

    public static final String ETX_4 = "ETX_4";

    public static final String ETX_5 = "ETX_5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
