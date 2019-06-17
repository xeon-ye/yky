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
 * 核算要素分配账薄信息表
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_ACCOUNT_ELEMENT_RELE")
@ApiModel(value="AvAccountElementRele对象", description="核算要素分配账薄信息表")
public class AvAccountElementRele extends Model<AvAccountElementRele> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "核算要素列ID")
    @TableField("ELEMENT_ID")
    private Long elementId;

    @ApiModelProperty(value = "账薄ID")
    @TableField("LEDGER_ID")
    private Long ledgerId;

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

    @ApiModelProperty(value = "预留属性1")
    @TableField("ETX_1")
    private String etx1;

    @ApiModelProperty(value = "预留属性2")
    @TableField("ETX_2")
    private String etx2;

    @ApiModelProperty(value = "预留属性3")
    @TableField("ETX_3")
    private String etx3;

    @ApiModelProperty(value = "预留属性4")
    @TableField("ETX_4")
    private String etx4;

    @ApiModelProperty(value = "预留属性5")
    @TableField("ETX_5")
    private String etx5;


    public static final String ID = "ID";

    public static final String ELEMENT_ID = "ELEMENT_ID";

    public static final String LEDGER_ID = "LEDGER_ID";

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
