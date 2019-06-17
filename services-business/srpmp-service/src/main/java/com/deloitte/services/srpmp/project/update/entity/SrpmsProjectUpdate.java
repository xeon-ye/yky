package com.deloitte.services.srpmp.project.update.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.deloitte.platform.api.srpmp.common.config.LongToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目变更记录表
 * </p>
 *
 * @author Apeng
 * @since 2019-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_UPDATE")
@ApiModel(value = "SrpmsProjectUpdate对象", description = "项目变更记录表")
public class SrpmsProjectUpdate extends Model<SrpmsProjectUpdate> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "项目变更ID")
	@TableId(value = "ID", type = IdType.ID_WORKER)
	private Long id;

	@ApiModelProperty(value = "变更单号")
	@TableField("UPDATE_NUMBER")
	private String updateNumber;

	@ApiModelProperty(value = "单位ID")
	@TableField("DEPT_ID")
	private Long deptId;

	@ApiModelProperty(value = "项目ID")
	@TableField("PROJECT_ID")
	private Long projectId;

	@ApiModelProperty(value = "项目编号")
	@TableField("PROJECT_CODE")
	private String projectCode;

	@ApiModelProperty(value = "项目名称")
	@TableField("PROJECT_NAME")
	private String projectName;

	@ApiModelProperty(value = "项目类型")
	@TableField("PROJECT_TYPE")
	private String projectType;

	@ApiModelProperty(value = "项目负责人")
	@TableField("LEAD_PERSON_NAME")
	private String leadPersonName;

	@ApiModelProperty(value = "批示文件ID")
	@TableField("FILE_ID")
	@JSONField(serializeUsing = LongToStringSerializer.class)
	private Long fileId;

	@ApiModelProperty(value = "批示文件名")
	@TableField("FILE_NAME")
	private String fileName;

	@ApiModelProperty(value = "批示文件URL")
	@TableField("FILE_URL")
	private String fileUrl;

	@ApiModelProperty(value = "变更类型")
	@TableField("UPDATE_TYPE")
	private String updateType;

	@ApiModelProperty(value = "变更原因")
	@TableField("UPDATE_REASON")
	private String updateReason;

	@ApiModelProperty(value = "变更状态")
	@TableField("UPDATE_STATE")
	private String updateState;

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

	@ApiModelProperty(value = "预算书编号")
	@TableField(value = "BUD_NUM")
	private String budNum;

	@ApiModelProperty(value = "项目标识位")
	@TableField("PROJECT_FLAG")
	private String projectFlag;

	@ApiModelProperty(value = "变更前值")
	@TableField("OLD_VALUE")
	private String oldValue;

	@ApiModelProperty(value = "变更后值")
	@TableField("NEW_VALUE")
	private String newValue;

	@ApiModelProperty(value = "删除标识")
	@TableField("DEL_FLAG")
	private String delFlag;

	public static final String DEL_FLAG = "DEL_FLAG";

	public static final String BUD_NUM = "BUD_NUM";

	public static final String ID = "ID";

	public static final String UPDATE_NUMBER = "UPDATE_NUMBER";

	public static final String DEPT_ID = "DEPT_ID";

	public static final String PROJECT_ID = "PROJECT_ID";

	public static final String PROJECT_CODE = "PROJECT_CODE";

	public static final String PROJECT_NAME = "PROJECT_NAME";

	public static final String PROJECT_TYPE = "PROJECT_TYPE";

	public static final String FILE_ID = "FILE_ID";

	public static final String FILE_NAME = "FILE_NAME";

	public static final String FILE_URL = "FILE_URL";

	public static final String UPDATE_TYPE = "UPDATE_TYPE";

	public static final String UPDATE_REASON = "UPDATE_REASON";

	public static final String UPDATE_STATE = "UPDATE_STATE";

	public static final String CREATE_TIME = "CREATE_TIME";

	public static final String CREATE_BY = "CREATE_BY";

	public static final String UPDATE_TIME = "UPDATE_TIME";

	public static final String UPDATE_BY = "UPDATE_BY";

	public static final String PROJECT_FLAG = "PROJECT_FLAG";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
