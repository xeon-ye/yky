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
 * 活动总投资
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_ALL_ACT")
@ApiModel(value="AllAct对象", description="活动总投资")
public class AllAct extends Model<AllAct> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "活动汇总ID")
    @TableField("ALL_ACT_ID")
    private String allActId;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "资金来源code")
    @TableField("FIN_SOURCE_CODE")
    private String finSourceCode;

    @ApiModelProperty(value = "资金来源")
    @TableField("FIN_SOURCE")
    private String finSource;

    @ApiModelProperty(value = "总投资")
    @TableField("ALL_INVESTMENT")
    private String allInvestment;

    @ApiModelProperty(value = "已批复预算数")
    @TableField("REPLY_QUANTITY")
    private String replyQuantity;

    @ApiModelProperty(value = "截至上年底结转数")
    @TableField("CARRY_OVER_END_OF_LAST_YEAR")
    private String carryOverEndOfLastYear;

    @ApiModelProperty(value = "申请预算")
    @TableField("BUDGET_APPLICATION")
    private String budgetApplication;

    @ApiModelProperty(value = "备注")
    @TableField("REMARKS")
    private String remarks;

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
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_PATH")
    private String orgPath;


    public static final String ID = "ID";

    public static final String ALL_ACT_ID = "ALL_ACT_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String FIN_SOURCE_CODE = "FIN_SOURCE_CODE";

    public static final String FIN_SOURCE = "FIN_SOURCE";

    public static final String ALL_INVESTMENT = "ALL_INVESTMENT";

    public static final String REPLY_QUANTITY = "REPLY_QUANTITY";

    public static final String CARRY_OVER_END_OF_LAST_YEAR = "CARRY_OVER_END_OF_LAST_YEAR";

    public static final String BUDGET_APPLICATION = "BUDGET_APPLICATION";

    public static final String REMARKS = "REMARKS";

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
