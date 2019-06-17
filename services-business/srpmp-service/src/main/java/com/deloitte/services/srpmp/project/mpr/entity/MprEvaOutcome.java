package com.deloitte.services.srpmp.project.mpr.entity;

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
 * 目标及考核指标完成情况任务详情表
 * </p>
 *
 * @author LIJUN
 * @since 2019-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_OUTCOME")
@ApiModel(value="MprEvaOutcome对象", description="目标及考核指标完成情况任务详情表")
public class MprEvaOutcome extends Model<MprEvaOutcome> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "代表性成果名称")
    @TableField("REP_OUTCOME_NAME")
    private String repOutcomeName;

    @ApiModelProperty(value = "成果类型")
    @TableField("OUTCOME_TYPE")
    private String outcomeType;

    @ApiModelProperty(value = "成果水平（国际领先/国际先进/国内领先/国内先进）")
    @TableField("OUTCOME_LEVEL")
    private String outcomeLevel;

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

    @ApiModelProperty(value = "对应的任务")
    @TableField("CORRESPOND_TASK")
    private String correspondTask;


    public static final String ID = "ID";

    public static final String PROJECT_NO = "PROJECT_NO";

    public static final String REP_OUTCOME_NAME = "REP_OUTCOME_NAME";

    public static final String OUTCOME_TYPE = "OUTCOME_TYPE";

    public static final String OUTCOME_LEVEL = "OUTCOME_LEVEL";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String CORRESPOND_TASK = "CORRESPOND_TASK";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
