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

/**
 * <p>
 * 记录账薄信息
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_CHART_OF_ACCOUNT")
@ApiModel(value="AvChartOfAccount对象", description="记录账薄信息")
public class AvChartOfAccount extends Model<AvChartOfAccount> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "账套ID")
    @TableField("LEDGER_ID")
    private Long ledgerId;

    @ApiModelProperty(value = "账套名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "科目表ID（跟COA关联）")
    @TableField("CHART_OF_ACCOUNTS_ID")
    private Long chartOfAccountsId;

    @ApiModelProperty(value = "账套说明")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "币种")
    @TableField("CURRENCY_CODE")
    private String currencyCode;

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

    @ApiModelProperty(value = "简称")
    @TableField("SHORT_NAME")
    private String shortName;

    @ApiModelProperty(value = "是否有效")
    @TableField("STATUS")
    private String status;


    public static final String ID = "ID";

    public static final String LEDGER_ID = "LEDGER_ID";

    public static final String NAME = "NAME";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String CURRENCY_CODE = "CURRENCY_CODE";

    public static final String CREATE_DATE = "CREATE_DATE";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String ETX_1 = "ETX_1";

    public static final String ETX_2 = "ETX_2";

    public static final String ETX_3 = "ETX_3";

    public static final String ETX_4 = "ETX_4";

    public static final String ETX_5 = "ETX_5";

    public static final String SHORT_NAME = "SHORT_NAME";

    public static final String STATUS = "STATUS";

    public static final String CHART_OF_ACCOUNTS_ID = "CHART_OF_ACCOUNTS_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
