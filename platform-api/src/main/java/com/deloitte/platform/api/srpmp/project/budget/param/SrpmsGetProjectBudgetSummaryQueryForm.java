package com.deloitte.platform.api.srpmp.project.budget.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author:LIJUN
 * Date:05/06/2019
 * Description:
 */
@ApiModel("SrpmsGetProjectBudgetSummaryQueryForm查询表单")
@Data
public class SrpmsGetProjectBudgetSummaryQueryForm extends BaseQueryForm<SrpmsGetProjectBudgetSummaryQueryParam> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "查询年度yyyy，为空则查询汇总数据")
    private String year;

}
