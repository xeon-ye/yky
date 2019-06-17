package com.deloitte.services.project.entity;

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
 * 立项细化经济分类
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_ECONOMIC")
@ApiModel(value="Economic对象", description="立项细化经济分类")
public class Economic extends Model<Economic> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "经济分类id")
    @TableField("ECONOMIC_ID")
    private String economicId;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "经济类型code")
    @TableField("ECO_CODE")
    private String ecoCode;

    @ApiModelProperty(value = "经济类型name")
    @TableField("ECO_NAME")
    private String ecoName;

    @ApiModelProperty(value = "年度")
    @TableField("ECO_YEAR")
    private String ecoYear;

    @ApiModelProperty(value = "申报财政拨款")
    @TableField("APP_FUNDING")
    private String appFunding;

    @ApiModelProperty(value = "申报部门预算")
    @TableField("APP_BUDGET")
    private String appBudget;

    @ApiModelProperty(value = "申报其他金额")
    @TableField("APP_OTHER")
    private String appOther;

    @ApiModelProperty(value = "评审财政拨款")
    @TableField("REVIEW_FUNDING")
    private String reviewFunding;

    @ApiModelProperty(value = "评审预算金额")
    @TableField("REVIEW_BUDGET")
    private String reviewBudget;

    @ApiModelProperty(value = "评审其他金额")
    @TableField("REVIEW_OTHER")
    private String reviewOther;

    @ApiModelProperty(value = "批复财政拨款")
    @TableField("REPLY_FUNDING")
    private String replyFunding;

    @ApiModelProperty(value = "批复部门预算")
    @TableField("REPLY_BUDGET")
    private String replyBudget;

    @ApiModelProperty(value = "批复其他金额")
    @TableField("REPLY_OTHER")
    private String replyOther;

    @ApiModelProperty(value = "结转金额")
    @TableField("CARRY_AMOUNT")
    private String carryAmount;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "维度id")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "维度")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "批复id")
    @TableField("REPLY_ID")
    private String replyId;


    public static final String ID = "ID";

    public static final String ECONOMIC_ID = "ECONOMIC_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String ECO_CODE = "ECO_CODE";

    public static final String ECO_NAME = "ECO_NAME";

    public static final String ECO_YEAR = "ECO_YEAR";

    public static final String APP_FUNDING = "APP_FUNDING";

    public static final String APP_BUDGET = "APP_BUDGET";

    public static final String APP_OTHER = "APP_OTHER";

    public static final String REVIEW_FUNDING = "REVIEW_FUNDING";

    public static final String REVIEW_BUDGET = "REVIEW_BUDGET";

    public static final String REVIEW_OTHER = "REVIEW_OTHER";

    public static final String REPLY_FUNDING = "REPLY_FUNDING";

    public static final String REPLY_BUDGET = "REPLY_BUDGET";

    public static final String REPLY_OTHER = "REPLY_OTHER";

    public static final String CARRY_AMOUNT = "CARRY_AMOUNT";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String REPLY_ID = "REPLY_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
