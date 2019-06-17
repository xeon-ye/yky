package com.deloitte.platform.api.srpmp.project.budget.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-19
 * @Description :   SrpmsProjectBudgetAccount查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectBudgetAccount查询表单")
@Data
public class SrpmsProjectBudgetAccountQueryForm extends BaseQueryForm<SrpmsProjectBudgetAccountQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "父ID")
    private Long parentId;

    @ApiModelProperty(value = "项目类型")
    private String projectCategory;

    @ApiModelProperty(value = "预算年度")
    private String budgetAccountYear;

    @ApiModelProperty(value = "预算CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预算名字")
    private String budgetAccountName;

    @ApiModelProperty(value = "预算状态（0-禁用；1-启用）")
    private Integer budgetAccountStatus;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
