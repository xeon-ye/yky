package com.deloitte.services.isump.entity;

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
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_ROLE")
@ApiModel(value="Role对象", description="角色表")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "类型(分组，功能，数据)")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Long sort;

    @ApiModelProperty(value = "上级ID")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    @TableField("RESERVE")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    @TableField("VERSION")
    private String version;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "服务类型")
    @TableField("SERVICE")
    private String service;

    @ApiModelProperty(value = "角色code")
    @TableField("CODE")
    private String code;


    public static final String ID = "ID";

    public static final String SERVICE = "SERVICE";

    public static final String CODE = "CODE";

    public static final String NAME = "NAME";

    public static final String TYPE = "TYPE";

    public static final String SORT = "SORT";

    public static final String PARENT_ID = "PARENT_ID";

    public static final String REMARK = "REMARK";

    public static final String RESERVE = "RESERVE";

    public static final String VERSION = "VERSION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
