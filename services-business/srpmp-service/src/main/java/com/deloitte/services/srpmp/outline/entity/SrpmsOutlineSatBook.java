package com.deloitte.services.srpmp.outline.entity;

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
 * 题录 09科技著作
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_OUTLINE_SAT_BOOK")
@ApiModel(value="SrpmsOutlineSatBook对象", description="题录 09科技著作")
public class SrpmsOutlineSatBook extends Model<SrpmsOutlineSatBook> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    @TableField("BASE_ID")
    private Long baseId;

    @ApiModelProperty(value = "项目编码")
    @TableField("PRO_CODE")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("PRO_NAME")
    private String proName;

    @ApiModelProperty(value = "著作名")
    @TableField("BOOK_NAME")
    private String bookName;

    @ApiModelProperty(value = "作者")
    @TableField("AUTHOR")
    private String author;

    @ApiModelProperty(value = "作者单位")
    @TableField("BOOK_ORG")
    private String bookOrg;

    @ApiModelProperty(value = "主编/参编")
    @TableField("BOOK_LEVEL")
    private String bookLevel;

    @ApiModelProperty(value = "出版社")
    @TableField("PRESS")
    private String press;

    @ApiModelProperty(value = "出版时间")
    @TableField("PRESS_TIME")
    private LocalDateTime pressTime;

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

    public static final String BASE_ID = "BASE_ID";

    public static final String PRO_CODE = "PRO_CODE";

    public static final String PRO_NAME = "PRO_NAME";

    public static final String BOOK_NAME = "BOOK_NAME";

    public static final String AUTHOR = "AUTHOR";

    public static final String BOOK_ORG = "BOOK_ORG";

    public static final String BOOK_LEVEL = "BOOK_LEVEL";

    public static final String PRESS = "PRESS";

    public static final String PRESS_TIME = "PRESS_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
