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
 * 资源表（模块，菜单，按钮，数据）
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_RESOURCE")
@ApiModel(value="Resource对象", description="资源表（模块，菜单，按钮，数据）")
public class Resource extends Model<Resource> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "图标")
    @TableField("ICON")
    private String icon;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "编号")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "跳转链接")
    @TableField("URI")
    private String uri;

    @ApiModelProperty(value = "权限字符串")
    @TableField("PERMS")
    private String perms;

    @ApiModelProperty(value = "上级ID")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单层级")
    @TableField("LEVELS")
    private String levels;

    @ApiModelProperty(value = "是否是叶子")
    @TableField("LEAF")
    private Integer leaf;

    @ApiModelProperty(value = "是否打开")
    @TableField("OPEN")
    private Integer open;

    @ApiModelProperty(value = "类型")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Long sort;

    @ApiModelProperty(value = "状态")
    @TableField("STATE")
    private String state;

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

    @ApiModelProperty(value = "所属系统")
    @TableField("SYS_CODE")
    private String sysCode;


    public static final String ID = "ID";

    public static final String ICON = "ICON";

    public static final String NAME = "NAME";

    public static final String CODE = "CODE";

    public static final String URI = "URI";

    public static final String PERMS = "PERMS";

    public static final String PARENT_ID = "PARENT_ID";

    public static final String LEVELS = "LEVELS";

    public static final String LEAF = "LEAF";

    public static final String OPEN = "OPEN";

    public static final String TYPE = "TYPE";

    public static final String SORT = "SORT";

    public static final String STATE = "STATE";

    public static final String REMARK = "REMARK";

    public static final String RESERVE = "RESERVE";

    public static final String VERSION = "VERSION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String SYS_CODE = "SYS_CODE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
