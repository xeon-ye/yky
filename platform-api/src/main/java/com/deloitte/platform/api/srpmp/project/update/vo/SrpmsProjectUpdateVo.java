package com.deloitte.platform.api.srpmp.project.update.vo;

import java.time.LocalDateTime;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.common.core.entity.vo.BaseVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-01
 * @Description : SrpmsProjectUpdate返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectUpdateVo extends BaseVo {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "项目变更ID")
	private String id;

	@ApiModelProperty(value = "变更单号")
	private String updateNumber;

	@ApiModelProperty(value = "单位ID")
	private String deptId;

	@ApiModelProperty(value = "项目ID")
	private String projectId;

	@ApiModelProperty(value = "项目编号")
	private String projectCode;

	@ApiModelProperty(value = "项目名称")
	private String projectName;

	@ApiModelProperty(value = "项目类型")
	private String projectType;

	@ApiModelProperty(value = "项目类型名称")
	private String projectTypeName;

	@ApiModelProperty(value = "项目负责人")
	private String leadPersonName;

	@ApiModelProperty(value = "批示文件ID")
	private String fileId;

	@ApiModelProperty(value = "批示文件名")
	private String fileName;

	@ApiModelProperty(value = "批示文件URL")
	private String fileUrl;

	@ApiModelProperty(value = "变更类型")
	private String updateType;

	@ApiModelProperty(value = "变更类型名称")
	private String updateTypeName;

	@ApiModelProperty(value = "变更原因")
	private String updateReason;

	@ApiModelProperty(value = "变更状态")
	private String updateState;

	@ApiModelProperty(value = "变更状态名称")
	private String updateStateName;

	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "创建人")
	private String createBy;

	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;

	@ApiModelProperty(value = "更新人")
	private String updateBy;

	@ApiModelProperty(value = "项目标识")
	private String projectFlag;

	@ApiModelProperty(value = "预算书编号")
	private String budNum;

	@ApiModelProperty(value = "状态变更")
	private String status;

	@ApiModelProperty(value = "负责人变更")
	private JSONObject leadPerson;

	@ApiModelProperty(value = "预算变更列表")
	private JSONArray budgetList;

	@ApiModelProperty(value = "内容变更列表")
	private JSONArray contentList;

	@ApiModelProperty(value = "主要参与人员")
	private JSONArray mianMemberList;

	@ApiModelProperty(value = "院外人员")
	private JSONArray outMemberList;

	@ApiModelProperty(value = "状态变更")
	private String oldStatus;

	@ApiModelProperty(value = "负责人变更")
	private JSONObject oldLeadPerson;

	@ApiModelProperty(value = "预算变更列表")
	private JSONArray oldBudgetList;

	@ApiModelProperty(value = "内容变更列表")
	private JSONArray oldContentList;

	@ApiModelProperty(value = "主要参与人员")
	private JSONArray oldMianMemberList;

	@ApiModelProperty(value = "院外人员")
	private JSONArray oldOutMemberList;

	@ApiModelProperty(value = "任务分解")
	private JSONArray taskDecompositionList;
}
