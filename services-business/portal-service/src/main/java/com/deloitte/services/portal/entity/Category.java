package com.deloitte.services.portal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 栏目表
 * </p>
 *
 * @author pengchao
 * @since 2019-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CATEGORY")
@ApiModel(value="Category对象", description="栏目表")
public class Category extends Model<Category> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "栏目ID")
    @TableField("CATEGORY_ID")
    private Long categoryId;

    @ApiModelProperty(value = "栏目标题")
    @TableField("CATEGORY_TITLE")
    private String categoryTitle;

    @ApiModelProperty(value = "栏目关键字")
    @TableField("CATEGORY_KEY")
    private String categoryKey;

    @ApiModelProperty(value = "栏目排序")
    @TableField("CATEGORY_SORT")
    private Long categorySort;

    @ApiModelProperty(value = "栏目发布时间")
    @TableField("CATEGORY_DATETIME")
    private LocalDateTime categoryDatetime;

    @ApiModelProperty(value = "栏目描述")
    @TableField("CATEGORY_DESCRIPTION")
    private String categoryDescription;

    @ApiModelProperty(value = "删除状态")
    @TableField("DEL_FLG")
    private String delFlg;


    public static final String CATEGORY_ID = "CATEGORY_ID";

    public static final String CATEGORY_TITLE = "CATEGORY_TITLE";

    public static final String CATEGORY_KEY = "CATEGORY_KEY";

    public static final String CATEGORY_SORT = "CATEGORY_SORT";

    public static final String CATEGORY_DATETIME = "CATEGORY_DATETIME";

    public static final String CATEGORY_DESCRIPTION = "CATEGORY_DESCRIPTION";

    public static final String DEL_FLG = "DEL_FLG";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
