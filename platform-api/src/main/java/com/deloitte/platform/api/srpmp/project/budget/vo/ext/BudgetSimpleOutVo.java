package com.deloitte.platform.api.srpmp.project.budget.vo.ext;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectBudgetApply返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BudgetSimpleOutVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	// id
	@ApiModelProperty(value = "id")
	private String id;

	@ApiModelProperty(value = "预算执行年份")
	private String budgetYear;

	@ApiModelProperty(value = "预算执行期限开始")
	private String budgetActionDateStart;

	@ApiModelProperty(value = "预算执行期限结束")
	private String budgetActionDateEnd;

	@ApiModelProperty(value = "预算执行期限")
	private String budgetActionDate;

	@ApiModelProperty(value = "项目执行期限开始")
	private String projectActionDateStart;

	@ApiModelProperty(value = "项目执行期限结束")
	private String projectActionDateEnd;

	@ApiModelProperty(value = "项目执行期限")
	private String projectActionDate;

	@ApiModelProperty(value = "总预算")
	private String budgetAmount;

	@ApiModelProperty(value = "项目名称")
	private String projectName;

	@ApiModelProperty(value = "项目类型名称")
	private String projectTypeName;

	@ApiModelProperty(value = "项目编号")
	private String projectNum;

	@ApiModelProperty(value = "预算编号")
	private String budNum;

	@ApiModelProperty(value = "状态")
	private String statusName;

	@ApiModelProperty(value = "状态id")
	private String status;

	@ApiModelProperty(value = "负责人名称")
	private String leadPersonName;

	@ApiModelProperty(value = "单位名称")
	private String deptName;

}
