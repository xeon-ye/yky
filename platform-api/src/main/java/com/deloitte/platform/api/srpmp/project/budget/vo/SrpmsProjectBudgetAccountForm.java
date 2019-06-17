package com.deloitte.platform.api.srpmp.project.budget.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-19
 * @Description : SrpmsProjectBudgetAccount新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectBudgetAccount表单")
@Data
public class SrpmsProjectBudgetAccountForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "父ID")
    private Long parentId;

    @ApiModelProperty(value = "项目类型")
    private String projectCategory;

    @ApiModelProperty(value = "预算年度")
    private String budgetAccountYear;

    @ApiModelProperty(value = "预算名字")
    private String budgetAccountName;

    @ApiModelProperty(value = "预算状态（0-禁用；1-启用）")
    private Integer budgetAccountStatus;

}
