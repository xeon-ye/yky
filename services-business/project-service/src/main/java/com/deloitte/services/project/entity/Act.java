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
 * 项目支出计划
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_ACT")
@ApiModel(value="Act对象", description="项目支出计划")
public class Act extends Model<Act> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "活动ID")
    @TableField("ACT_ID")
    private String actId;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "活动序号")
    @TableField("ACT_NO")
    private String actNo;

    @ApiModelProperty(value = "项目活动")
    @TableField("ACT_NAME")
    private String actName;

    @ApiModelProperty(value = "项目活动描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "数量/频率")
    @TableField("QUANTITY_FRENQUENCE")
    private String quantityFrenquence;

    @ApiModelProperty(value = "分项支出")
    @TableField("SUB_EXPENSE")
    private String subExpense;

    @ApiModelProperty(value = "价格/标准")
    @TableField("PRICE_STANDARD")
    private String priceStandard;

    @ApiModelProperty(value = "支出计划")
    @TableField("ACT_PLAN")
    private String actPlan;

    @ApiModelProperty(value = "备注")
    @TableField("REMARKS")
    private String remarks;

    @ApiModelProperty(value = "评审金额")
    @TableField("REVIEW_AMOUNT")
    private String reviewAmount;

    @ApiModelProperty(value = "评审备注")
    @TableField("REVIEW_REMARK")
    private String reviewRemark;

    @ApiModelProperty(value = "批复ID")
    @TableField("REPLY_ID")
    private String replyId;

    @ApiModelProperty(value = "批复金额")
    @TableField("REPLAY_AMOUNT")
    private String replayAmount;

    @ApiModelProperty(value = "批复备注")
    @TableField("REPLAY_REMARK")
    private String replayRemark;

    @ApiModelProperty(value = "创建")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "父子活动对应关系关联字段")
    @TableField("IS_RELATED")
    private String isRelated;


    public static final String ID = "ID";

    public static final String ACT_ID = "ACT_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String ACT_NO = "ACT_NO";

    public static final String ACT_NAME = "ACT_NAME";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String QUANTITY_FRENQUENCE = "QUANTITY_FRENQUENCE";

    public static final String SUB_EXPENSE = "SUB_EXPENSE";

    public static final String PRICE_STANDARD = "PRICE_STANDARD";

    public static final String ACT_PLAN = "ACT_PLAN";

    public static final String REMARKS = "REMARKS";

    public static final String REVIEW_AMOUNT = "REVIEW_AMOUNT";

    public static final String REVIEW_REMARK = "REVIEW_REMARK";

    public static final String REPLY_ID = "REPLY_ID";

    public static final String REPLAY_AMOUNT = "REPLAY_AMOUNT";

    public static final String REPLAY_REMARK = "REPLAY_REMARK";

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

    public static final String IS_RELATED = "IS_RELATED";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
