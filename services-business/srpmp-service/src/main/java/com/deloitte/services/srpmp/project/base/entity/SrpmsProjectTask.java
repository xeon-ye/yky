package com.deloitte.services.srpmp.project.base.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 项目任务表
 * </p>
 *
 * @author lixin
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_TASK")
@ApiModel(value = "SrpmsProjectTask对象", description = "项目任务表")
public class SrpmsProjectTask extends Model<SrpmsProjectTask> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@TableId(value = "ID", type = IdType.ID_WORKER)
	private Long id;

	@ApiModelProperty(value = "项目ID")
	@TableField("PROJECT_ID")
	private Long projectId;

	@ApiModelProperty(value = "任务名称")
	@TableField("TASK_NAME")
	private String taskName;

	@ApiModelProperty(value = "任务类型CODE")
	@TableField("TASK_CATEGORY")
	private String taskCategory;

	@ApiModelProperty(value = "任务年度")
	@TableField("TASK_YEAR")
	private String taskYear;

	@ApiModelProperty(value = "序号")
	@TableField("SERIAL")
	private String serial;

	@ApiModelProperty(value = "年度考核目标")
	@TableField("TASK_TARGET_YEAR")
	private String taskTargetYear;

	@ApiModelProperty(value = "重要任务的时间节点")
	@TableField("IMPORTANT_POINT")
	private String importantPoint;

	@ApiModelProperty(value = "主要内容")
	@TableField("TASK_CONTENT")
	private String taskContent;

	@ApiModelProperty(value = "3年考核指标")
	@TableField("THREE_YEAR_TARGET")
	private String threeYearTarget;

	@ApiModelProperty(value = "分年度考核指标")
	@TableField("TARGET_PER_YEAR")
	private String targetPerYear;

	@ApiModelProperty(value = "负责人ID")
	@TableField("LEAD_PERSON_ID")
	private Long leadPersonId;

	@ApiModelProperty(value = "参加人员")
	@TableField("JOIN_PERSON_ID")
	private Long joinPersonId;

	@ApiModelProperty(value = "xx年经费分配比例")
	@TableField("BUDGET_ALLOT_RATIO")
	private String budgetAllotRatio;

	@ApiModelProperty(value = "单位名称")
	@TableField("DEPT_NAME")
	private String deptName;

	@ApiModelProperty(value = "参加课题组长及人员")
	@TableField("GROUP_LEADER_MEMBER")
	private String groupLeaderMember;

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

	@ApiModelProperty(value = "第一年预期目标及考核指标(任务书)")
	@TableField("FIRST_YEAR_TARGET")
	private String firstYearTarget;

	@ApiModelProperty(value = "考核方式（任务书）")
	@TableField("EXAM_MODE")
	private String examMode;

	@ApiModelProperty(value = "负责人姓名")
	@TableField("LEAD_PERSON_NAME")
	private String leadPersonName;

	@ApiModelProperty(value = "负责人姓名")
	@TableField("JOIN_PERSON_NAME")
	private String joinPersonName;

	@ApiModelProperty(value = "预算明细")
	@TableField("BUDGET_DETAIL")
	private String budgetDetail;

	@ApiModelProperty(value = "预算合计")
	@TableField("BUDGET_AMOUNT")
	private Double budgetAmount;

	@ApiModelProperty(value = "任务备注")
	@TableField("TASK_REMARK")
	private String taskRemark;

	@ApiModelProperty(value = "预算备注")
	@TableField("BUDGET_REMARK")
	private String budgetRemark;

	@ApiModelProperty(value = "单位ID")
	@TableField("DEPT_ID")
	private String deptId;

	@ApiModelProperty(value = "人数")
	@TableField("PEOPLE_COUNT")
	private String peopleCount;

	@ApiModelProperty(value = "分配预算")
	@TableField("ALLOCATED_AMOUNT")
	private String allocatedAmount;

	@ApiModelProperty(value = "组织机构代码")
	@TableField("DEPT_CODE")
	private String deptCode;

	@ApiModelProperty(value = "组织机构类型")
	@TableField("DEPT_QUALITY")
	private String deptQuality;

	@ApiModelProperty(value = "负责人")
	@TableField("LEAD_PERSON")
	private String leadPerson;

	@ApiModelProperty(value = "参与人（数组）")
	@TableField("JOIN_PERSON")
	private String joinPerson;

	@ApiModelProperty(value = "负责人详细信息")
	@TableField("LEAD_PERSON_INFO")
	private String leadPersonInfo;

	@ApiModelProperty(value = "参与人详细信息(数组)")
	@TableField("JOIN_PERSON_INFO")
	private String joinPersonInfo;

	@ApiModelProperty(value = "中期考核目标")
	@TableField("MEDIUM_TARGET")
	private String mediumTarget;

	@ApiModelProperty(value = "任务编码")
	@TableField("PROJECT_TASK_NUM")
	private String projectTaskNum;

	public static final String BUDGET_DETAIL = "BUDGET_DETAIL";

	public static final String BUDGET_AMOUNT = "BUDGET_AMOUNT";

	public static final String ID = "ID";

	public static final String PROJECT_ID = "PROJECT_ID";

	public static final String TASK_NAME = "TASK_NAME";

	public static final String TASK_CATEGORY = "TASK_CATEGORY";

	public static final String TASK_YEAR = "TASK_YEAR";

	public static final String SERIAL = "SERIAL";

	public static final String TASK_TARGET_YEAR = "TASK_TARGET_YEAR";

	public static final String IMPORTANT_POINT = "IMPORTANT_POINT";

	public static final String TASK_CONTENT = "TASK_CONTENT";

	public static final String THREE_YEAR_TARGET = "THREE_YEAR_TARGET";

	public static final String TARGET_PER_YEAR = "TARGET_PER_YEAR";

	public static final String LEAD_PERSON_ID = "LEAD_PERSON_ID";

	public static final String JOIN_PERSON_ID = "JOIN_PERSON_ID";

	public static final String BUDGET_ALLOT_RATIO = "BUDGET_ALLOT_RATIO";

	public static final String DEPT_NAME = "DEPT_NAME";

	public static final String GROUP_LEADER_MEMBER = "GROUP_LEADER_MEMBER";

	public static final String CREATE_TIME = "CREATE_TIME";

	public static final String CREATE_BY = "CREATE_BY";

	public static final String UPDATE_TIME = "UPDATE_TIME";

	public static final String UPDATE_BY = "UPDATE_BY";

	public static final String FIRST_YEAR_TARGET = "FIRST_YEAR_TARGET";

	public static final String EXAM_MODE = "EXAM_MODE";

	public static final String LEAD_PERSON_NAME = "LEAD_PERSON_NAME";

	public static final String JOIN_PERSON_NAME = "JOIN_PERSON_NAME";

	public static final String MEDIUM_TARGET = "MEDIUM_TARGET";

	public static final String PROJECT_TASK_NUM = "PROJECT_TASK_NUM";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
