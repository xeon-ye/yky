package com.deloitte.services.fssc.base.entity;

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
 * 收入小类-组织关系
 * </p>
 *
 * @author jaws
 * @since 2019-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_INCOME_SUB_TYPE_ORG_UNIT")
@ApiModel(value="BaseIncomeSubTypeOrgUnit对象", description="收入小类-组织关系")
public class BaseIncomeSubTypeOrgUnit extends Model<BaseIncomeSubTypeOrgUnit> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "收入小类ID")
    @TableField("INCOME_SUB_TYPE_ID")
    private Long incomeSubTypeId;

    @ApiModelProperty(value = "组织单位ID")
    @TableField("ORG_UNIT_ID")
    private Long orgUnitId;

    @ApiModelProperty(value = "组织单位编码")
    @TableField("ORG_UNIT_CODE")
    private String orgUnitCode;

    @ApiModelProperty(value = "组织单位名称")
    @TableField("ORG_UNIT_NAME")
    private String orgUnitName;

    @ApiModelProperty(value = "生失效标志")
    @TableField("VALID_FLAG")
    private String validFlag;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("EXT5")
    private String ext5;

    public static final String ID = "ID";

    public static final String ORG_UNIT_ID = "ORG_UNIT_ID";

    public static final String ORG_UNIT_CODE = "ORG_UNIT_CODE";

    public static final String ORG_UNIT_NAME = "ORG_UNIT_NAME";

    public static final String INCOME_SUB_TYPE_ID = "INCOME_SUB_TYPE_ID";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
