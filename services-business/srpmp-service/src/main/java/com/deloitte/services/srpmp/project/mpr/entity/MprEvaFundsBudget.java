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
 * 中期绩效报告经费预算表
 * </p>
 *
 * @author LIJUN
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_FUNDS_BUDGET")
@ApiModel(value="MprEvaFundsBudget对象", description="中期绩效报告经费预算表")
public class MprEvaFundsBudget extends Model<MprEvaFundsBudget> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "年度")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "任务")
    @TableField("TASK_NAME")
    private String taskName;

    @ApiModelProperty(value = "预算")
    @TableField("BUDGET")
    private String budget;

    @ApiModelProperty(value = "支出")
    @TableField("EXPENSES")
    private String expenses;

    @ApiModelProperty(value = "执行率")
    @TableField("EXACUTIVE_RATE")
    private String exacutiveRate;

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

    public static final String YEAR = "YEAR";

    public static final String TASK_NAME = "TASK_NAME";

    public static final String BUDGET = "BUDGET";

    public static final String EXPENSES = "EXPENSES";

    public static final String EXACUTIVE_RATE = "EXACUTIVE_RATE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
