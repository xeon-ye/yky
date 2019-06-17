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
 * 
 * </p>
 *
 * @author zhengchun
 * @since 2019-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_CHANGE_BUDGET_BAK")
@ApiModel(value="ChangeBudgetBak对象", description="")
public class ChangeBudgetBak extends Model<ChangeBudgetBak> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "变更预算id")
    @TableField("MAINT_BUDGET_ID")
    private String maintBudgetId;

    @ApiModelProperty(value = "项目维护id")
    @TableField("MAINT_ID")
    private String maintId;

    @ApiModelProperty(value = "批复id")
    @TableField("ACT_CODE")
    private String actCode;

    @ApiModelProperty(value = "经济类型code")
    @TableField("ACT_NAME")
    private String actName;

    @ApiModelProperty(value = "经济类型name")
    @TableField("ACT_AMOUNT")
    private String actAmount;

    @ApiModelProperty(value = "创建时间")
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


    public static final String ID = "ID";

    public static final String MAINT_BUDGET_ID = "MAINT_BUDGET_ID";

    public static final String MAINT_ID = "MAINT_ID";

    public static final String ACT_CODE = "ACT_CODE";

    public static final String ACT_NAME = "ACT_NAME";

    public static final String ACT_AMOUNT = "ACT_AMOUNT";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
