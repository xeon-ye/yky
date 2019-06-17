package com.deloitte.services.srpmp.project.budget.entity;

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
 * 项目预算科目配置表
 * </p>
 *
 * @author LIJUN
 * @since 2019-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_BUDGET_ACCOUNT")
@ApiModel(value="SrpmsProjectBudgetAccount对象", description="项目预算科目配置表")
public class SrpmsProjectBudgetAccount extends Model<SrpmsProjectBudgetAccount> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "父ID")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty(value = "项目类型")
    @TableField("PROJECT_CATEGORY")
    private String projectCategory;

    @ApiModelProperty(value = "预算年度")
    @TableField("BUDGET_ACCOUNT_YEAR")
    private String budgetAccountYear;

    @ApiModelProperty(value = "预算CODE")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预算名字")
    @TableField("BUDGET_ACCOUNT_NAME")
    private String budgetAccountName;

    @ApiModelProperty(value = "预算状态（0-禁用；1-启用）")
    @TableField("BUDGET_ACCOUNT_STATUS")
    private Integer budgetAccountStatus;

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

    public static final String PARENT_ID = "PARENT_ID";

    public static final String PROJECT_CATEGORY = "PROJECT_CATEGORY";

    public static final String BUDGET_ACCOUNT_YEAR = "BUDGET_ACCOUNT_YEAR";

    public static final String BUDGET_ACCOUNT_CODE = "BUDGET_ACCOUNT_CODE";

    public static final String BUDGET_ACCOUNT_NAME = "BUDGET_ACCOUNT_NAME";

    public static final String BUDGET_ACCOUNT_STATUS = "BUDGET_ACCOUNT_STATUS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
