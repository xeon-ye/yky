package com.deloitte.services.srpmp.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统字典表
 * </p>
 *
 * @author pengchao
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SYS_DICT")
@ApiModel(value="SysDict对象", description="系统字典表")
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "字典编码")
    @TableField("DICT_CODE")
    private String dictCode;

    @ApiModelProperty(value = "字典值")
    @TableField("DICT_VALUE")
    private String dictValue;

    @ApiModelProperty(value = "父级字典编码，顶级为NULL")
    @TableField("DICT_PARENT")
    private Long dictParent;

    @ApiModelProperty(value = "字典生效日期")
    @TableField("ACTIVE_DATE")
    private LocalDateTime activeDate;

    @ApiModelProperty(value = "字典失效日期")
    @TableField("EXPIRED_DATE")
    private LocalDateTime expiredDate;

    @ApiModelProperty(value = "是否过期，0正常 1过期")
    @TableField("IS_EXPIRED")
    private Integer isExpired;

    @ApiModelProperty(value = "是否修改，0可以修改 1不可修改")
    @TableField("EDIT_FLAG")
    private Integer editFlag;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    public static final String ID = "ID";

    public static final String DICT_CODE = "DICT_CODE";

    public static final String DICT_VALUE = "DICT_VALUE";

    public static final String DICT_PARENT = "DICT_PARENT";

    public static final String ACTIVE_DATE = "ACTIVE_DATE";

    public static final String EXPIRED_DATE = "EXPIRED_DATE";

    public static final String IS_EXPIRED = "IS_EXPIRED";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EDIT_FLAG = "EDIT_FLAG";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
