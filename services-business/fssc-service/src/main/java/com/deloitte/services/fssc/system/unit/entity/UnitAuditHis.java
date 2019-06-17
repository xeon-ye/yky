package com.deloitte.services.fssc.system.unit.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 单位审核历史表
 * </p>
 *
 * @author qiliao
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_UNIT_AUDIT_HIS")
@ApiModel(value="UnitAuditHis对象", description="单位审核历史表")
public class UnitAuditHis extends Model<UnitAuditHis> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "历史纪录ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位ID")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "审核意见")
    @TableField("AUDIT_OPIN")
    private String auditOpin;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

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

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @ApiModelProperty(value = "版本")
    @TableField(value = "VERSION")
    @Version
    private Long version;

    public static final String ID = "ID";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String AUDIT_OPIN = "AUDIT_OPIN";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT_1 = "EXT_1";

    public static final String EXT_2 = "EXT_2";

    public static final String EXT_3 = "EXT_3";

    public static final String EXT_4 = "EXT_4";

    public static final String EXT_5 = "EXT_5";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
