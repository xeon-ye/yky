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
 * 主体段值和账薄的关系
 * </p>
 *
 * @author chenx
 * @since 2019-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_LEDGER_UNIT_RELATION")
@ApiModel(value="AvLedgerUnitRelation对象", description="主体段值和账薄的关系")
public class AvLedgerUnitRelation extends Model<AvLedgerUnitRelation> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "平衡段值")
    @TableField("BALANCE_CODE")
    private String balanceCode;

    @ApiModelProperty(value = "账薄ID")
    @TableField("LEDGER_ID")
    private Long ledgerId;

    @ApiModelProperty(value = "预留字段1")
    @TableField("EXT_1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EXT_2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EXT_3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EXT_4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EXT_5")
    private String ext5;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;


    public static final String ID = "ID";

    public static final String BALANCE_CODE = "BALANCE_CODE";

    public static final String LEDGER_ID = "LEDGER_ID";

    public static final String EXT_1 = "EXT_1";

    public static final String EXT_2 = "EXT_2";

    public static final String EXT_3 = "EXT_3";

    public static final String EXT_4 = "EXT_4";

    public static final String EXT_5 = "EXT_5";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_DATE = "CREATE_DATE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
