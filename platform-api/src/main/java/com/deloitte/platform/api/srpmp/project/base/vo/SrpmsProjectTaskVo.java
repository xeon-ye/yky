package com.deloitte.platform.api.srpmp.project.base.vo;

import java.time.LocalDateTime;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-26
 * @Description : SrpmsProjectTask返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SrpmsProjectTaskVo extends BaseVo {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@JsonSerialize(using = LongJsonSerializer.class)
	private Long id;

	@ApiModelProperty(value = "项目ID")
	private String projectId;

	@ApiModelProperty(value = "任务名称")
	private String taskName;

	@ApiModelProperty(value = "任务类型CODE")
	private String taskCategory;

	@ApiModelProperty(value = "任务年度")
	private String taskYear;

	@ApiModelProperty(value = "序号")
	private String serial;

	@ApiModelProperty(value = "年度考核目标")
	private String taskTargetYear;

	@ApiModelProperty(value = "重要任务的时间节点")
	private String importantPoint;

	@ApiModelProperty(value = "主要内容")
	private String taskContent;

	@ApiModelProperty(value = "3年考核指标")
	private String threeYearTarget;

	@ApiModelProperty(value = "分年度考核指标")
	private String targetPerYear;

	@ApiModelProperty(value = "负责人ID")
	private Long leadPersonId;

	@ApiModelProperty(value = "参加人员")
	private Long joinPersonId;

	@ApiModelProperty(value = "xx年经费分配比例")
	private String budgetAllotRatio;

	@ApiModelProperty(value = "单位名称")
	private String deptName;

	@ApiModelProperty(value = "参加课题组长及人员")
	private String groupLeaderMember;

	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "创建人")
	private String createBy;

	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;

	@ApiModelProperty(value = "更新人")
	private String updateBy;

	@ApiModelProperty(value = "第一年预期目标及考核指标(任务书)")
	private String firstYearTarget;

	@ApiModelProperty(value = "考核方式（任务书）")
	private String examMode;

	@ApiModelProperty(value = "负责人姓名")
	private String leadPersonName;

	@ApiModelProperty(value = "参加人姓名")
	private JSONArray joinPersonName;

	@ApiModelProperty(value = "参加人姓名字符串")
	private String joinPersonNameStr;

	@ApiModelProperty(value = "预算明细")
	private JSONArray budgetDetail;

	@ApiModelProperty(value = "预算合计")
	private String budgetAmount;

	@ApiModelProperty(value = "单位ID")
	private String deptId;

	@ApiModelProperty(value = "任务备注")
	private String taskRemark;

	@ApiModelProperty(value = "预算备注")
	private String budgetRemark;

	@ApiModelProperty(value = "人数")
	private String peopleCount;

	@ApiModelProperty(value = "分配预算")
	private String allocatedAmount;

	@ApiModelProperty(value = "组织机构代码")
	private String deptCode;

	@ApiModelProperty(value = "组织机构类型/承担单位类型")
	private String deptQuality;

	@ApiModelProperty(value = "中期考核目标")
	private String mediumTarget;

	@ApiModelProperty(value = "负责人")
	private String leadPerson;

	@ApiModelProperty(value = "参与人（数组）")
	private JSONArray joinPerson;

	@ApiModelProperty(value = "负责人详细信息")
	private JSONObject leadPersonInfo;

	@ApiModelProperty(value = "参与人详细信息(数组)")
	private JSONArray joinPersonInfo;

	@ApiModelProperty(value = "任务编码")
	private String projectTaskNum;

}
