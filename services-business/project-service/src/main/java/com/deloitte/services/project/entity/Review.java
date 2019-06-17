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
 * 项目评审表
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_REVIEW")
@ApiModel(value="Review对象", description="项目评审表")
public class Review extends Model<Review> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "评审ID")
    @TableField("REVIEW_ID")
    private String reviewId;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "预算ID")
    @TableField("BUDGET_ID")
    private String budgetId;

    @ApiModelProperty(value = "支出ID")
    @TableField("EXPENSE_ID")
    private String expenseId;

    @ApiModelProperty(value = "绩效ID")
    @TableField("PERFORMANCE_ID")
    private String performanceId;

    @ApiModelProperty(value = "院级排序")
    @TableField("SCHOOL_PRIORITY")
    private String schoolPriority;

    @ApiModelProperty(value = "一级项目")
    @TableField("FIRST_LEVEL_PROJECT")
    private String firstLevelProject;

    @ApiModelProperty(value = "评审意见")
    @TableField("REVIEW_ADVICE")
    private String reviewAdvice;

    @ApiModelProperty(value = "评审状态name")
    @TableField("REVIEW_STATUS_NAME")
    private String reviewStatusName;

    @ApiModelProperty(value = "评审状态code")
    @TableField("REVIEW_STATUS_CODE")
    private String reviewStatusCode;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
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

    @ApiModelProperty(value = "数据权限维度字段ORG_ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "数据权限维度字段ORG_PATH")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "审批人")
    @TableField("APPROVER")
    private String approver;

    @ApiModelProperty(value = "审批时间")
    @TableField("APPROVER_TIME")
    private LocalDateTime approverTime;

    @ApiModelProperty(value = "意见")
    @TableField("OPINION")
    private String opinion;

    @ApiModelProperty(value = "一级项目code")
    @TableField("FIRST_LEVEL_PROJECT_CODE")
    private String firstLevelProjectCode;

    @ApiModelProperty(value = "业务单号流水号")
    @TableField("SERVICE_NUM")
    private String serviceNum;


    public static final String ID = "ID";

    public static final String REVIEW_ID = "REVIEW_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String BUDGET_ID = "BUDGET_ID";

    public static final String EXPENSE_ID = "EXPENSE_ID";

    public static final String PERFORMANCE_ID = "PERFORMANCE_ID";

    public static final String SCHOOL_PRIORITY = "SCHOOL_PRIORITY";

    public static final String FIRST_LEVEL_PROJECT = "FIRST_LEVEL_PROJECT";

    public static final String REVIEW_ADVICE = "REVIEW_ADVICE";

    public static final String REVIEW_STATUS_NAME = "REVIEW_STATUS_NAME";

    public static final String REVIEW_STATUS_CODE = "REVIEW_STATUS_CODE";

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

    public static final String REMARK = "REMARK";

    public static final String APPROVER = "APPROVER";

    public static final String APPROVER_TIME = "APPROVER_TIME";

    public static final String OPINION = "OPINION";

    public static final String FIRST_LEVEL_PROJECT_CODE = "FIRST_LEVEL_PROJECT_CODE";

    public static final String SERVICE_NUM = "SERVICE_NUM";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
