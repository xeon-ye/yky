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
 * 项目执行预算情况
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_EXE_BUDGET")
@ApiModel(value="ExeBudget对象", description="项目执行预算情况")
public class ExeBudget extends Model<ExeBudget> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "项目执行id")
    @TableField("EXECUTION_ID")
    private String executionId;

    @ApiModelProperty(value = "预算种类code")
    @TableField("BUDGET_CODE")
    private String budgetCode;

    @ApiModelProperty(value = "预算种类name")
    @TableField("BUDGET_NAME")
    private String budgetName;

    @ApiModelProperty(value = "预算费用")
    @TableField("BUDGET_AMOUNT")
    private String budgetAmount;

    @ApiModelProperty(value = "执行费用")
    @TableField("EXE_AMOUNT")
    private String exeAmount;

    @ApiModelProperty(value = "年度")
    @TableField("BUDGET_YEAR")
    private String budgetYear;

    @ApiModelProperty(value = "创建时间 ")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    @TableField("EXT3")
    private String ext3;


    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String EXECUTION_ID = "EXECUTION_ID";

    public static final String BUDGET_CODE = "BUDGET_CODE";

    public static final String BUDGET_NAME = "BUDGET_NAME";

    public static final String BUDGET_AMOUNT = "BUDGET_AMOUNT";

    public static final String EXE_AMOUNT = "EXE_AMOUNT";

    public static final String BUDGET_YEAR = "BUDGET_YEAR";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
