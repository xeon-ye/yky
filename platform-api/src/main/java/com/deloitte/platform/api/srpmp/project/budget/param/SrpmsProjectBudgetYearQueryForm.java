package com.deloitte.platform.api.srpmp.project.budget.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description : SrpmsProjectBudgetApply查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectBudgetYearQueryForm extends BaseQueryForm<SrpmsProjectBudgetYearQueryParam> {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "查询类型flg")
	private int tableFlag;

	@ApiModelProperty(value = "项目名称")
	private String projectName;

	@ApiModelProperty(value = "项目编号")
	private String projectNum;

	@ApiModelProperty(value = "项目类型CODE")
	private String projectType;

	@ApiModelProperty(value = "状态")
	private String status;
	
    @ApiModelProperty(value = "单位ID")
    private String deptId;
    
    @ApiModelProperty(value = "创建人")
    private String createBy;

}
