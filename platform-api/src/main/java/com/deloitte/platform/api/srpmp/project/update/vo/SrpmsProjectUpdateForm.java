package com.deloitte.platform.api.srpmp.project.update.vo;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-01
 * @Description : SrpmsProjectUpdate新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectUpdate表单")
@Data
public class SrpmsProjectUpdateForm extends BaseForm {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "项目ID")
	private Long projectId;

	@ApiModelProperty(value = "项目名称")
	private String projectName;
	
	@ApiModelProperty(value = "变更类型")
	private String updateType;

	@ApiModelProperty(value = "变更原因")
	private String updateReason;

	@ApiModelProperty(value = "状态变更")
	private String status;

	@ApiModelProperty(value = "负责人变更")
	private JSONArray leadPerson;

	@ApiModelProperty(value = "预算变更列表")
	private JSONArray budgetList;

	@ApiModelProperty(value = "内容变更列表")
	private JSONArray contentList;

	@ApiModelProperty(value = "主要参与人员")
	private JSONArray mianMemberList;

	@ApiModelProperty(value = "院外人员")
	private JSONArray outMemberList;

	@ApiModelProperty(value = "附件")
	private List<SrpmsProjectAttachmentVo> attachmentFile;

	@ApiModelProperty(value = "预算书编号")
	private String budNum;

}
