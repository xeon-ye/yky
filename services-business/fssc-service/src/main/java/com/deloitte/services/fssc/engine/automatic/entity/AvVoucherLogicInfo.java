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
 * 凭证逻辑详细页面记录颗粒度到达每个账薄下面的对应的单据类型
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_VOUCHER_LOGIC_INFO")
@ApiModel(value="AvVoucherLogicInfo对象", description="凭证逻辑详细页面记录颗粒度到达每个账薄下面的对应的单据类型")
public class AvVoucherLogicInfo extends Model<AvVoucherLogicInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "账薄ID")
    @TableField("LEDGER_ID")
    private Long ledgerId;

    @ApiModelProperty(value = "单据类型")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_DATE")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

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

    public static final String LEDGER_ID = "LEDGER_ID";

    public static final String TYPE = "TYPE";

    public static final String CREATE_DATE = "CREATE_DATE";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_DATE = "UPDATE_DATE";

    public static final String UPDATE_BY = "UPDATE_BY";

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
