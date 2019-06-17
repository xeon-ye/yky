package com.deloitte.services.oaservice.entity;

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
 * 
 * </p>
 *
 * @author fuqiao
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_MENU_FAVORITES")
@ApiModel(value="OaMenuFavorites对象", description="")
public class OaMenuFavorites extends Model<OaMenuFavorites> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "菜单编码")
    @TableField("MENU_CODE")
    private String menuCode;

    @ApiModelProperty(value = "菜单名称")
    @TableField("MENU_NAME")
    private String menuName;

    @ApiModelProperty(value = "菜单地址")
    @TableField("MENU_URL")
    private String menuUrl;

    @ApiModelProperty(value = "父菜单id")
    @TableField("PARENT_ID")
    private String parentId;

    @ApiModelProperty(value = "所属模块编码")
    @TableField("MODULE_CODE")
    private String moduleCode;

    @ApiModelProperty(value = "所属模块名称")
    @TableField("MODULE_NAME")
    private String moduleName;

    @ApiModelProperty(value = "菜单图标")
    @TableField("ICON")
    private String icon;

    @ApiModelProperty(value = "所属系统")
    @TableField("SYSTEM_SOURCE")
    private String systemSource;

    @ApiModelProperty(value = "排序号")
    @TableField("ORDER_SORT")
    private Double orderSort;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT5")
    private String ext5;


    public static final String ID = "ID";

    public static final String MENU_CODE = "MENU_CODE";

    public static final String MENU_NAME = "MENU_NAME";

    public static final String MENU_URL = "MENU_URL";

    public static final String PARENT_ID = "PARENT_ID";

    public static final String MODULE_CODE = "MODULE_CODE";

    public static final String MODULE_NAME = "MODULE_NAME";

    public static final String ICON = "ICON";

    public static final String SYSTEM_SOURCE = "SYSTEM_SOURCE";

    public static final String ORDER_SORT = "ORDER_SORT";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
