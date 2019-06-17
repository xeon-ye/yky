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
 * 项目合作人员信息表
 * </p>
 *
 * @author lixin
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_PERSON_JOIN")
@ApiModel(value = "SrpmsProjectPersonJoin对象", description = "项目合作人员信息表")
public class SrpmsProjectPersonJoin extends Model<SrpmsProjectPersonJoin> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@TableId(value = "ID", type = IdType.ID_WORKER)
	private Long id;

	@ApiModelProperty(value = "项目ID")
	@TableField("PROJECT_ID")
	private Long projectId;

	@ApiModelProperty(value = "项目编号")
	@TableField("PROJECT_NUM")
	private String projectNum;

	@ApiModelProperty(value = "合作类型CODE")
	@TableField("JOIN_WAY")
	private String joinWay;

	@ApiModelProperty(value = "性别")
	@TableField("GENDER")
	private String gender;

	@ApiModelProperty(value = "出生年月")
	@TableField("BIRTH_DATE")
	private LocalDateTime birthDate;

	@ApiModelProperty(value = "职称CODE")
	@TableField("POSITION_TITLE")
	private String positionTitle;

	@ApiModelProperty(value = "人员类别CODE")
	@TableField("PERSON_CATEGORY")
	private String personCategory;

	@ApiModelProperty(value = "学位CODE")
	@TableField("DEGREE")
	private String degree;

	@ApiModelProperty(value = "学历")
	@TableField("EDUCATION")
	private String education;

	@ApiModelProperty(value = "单位名称，科室名称")
	@TableField("DEPT_NAME")
	private String deptName;

	@ApiModelProperty(value = "电话")
	@TableField("PHONE")
	private String phone;

	@ApiModelProperty(value = "证件号码")
	@TableField("ID_CARD")
	private String idCard;

	@ApiModelProperty(value = "每年工作时间")
	@TableField("WORK_PER_YEAR")
	private Integer workPerYear;

	@ApiModelProperty(value = "所属研究任务，项目分工")
	@TableField("BELONG_TASK")
	private String belongTask;

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

	@ApiModelProperty(value = "源人员ID")
	@TableField("SOURCE_PERSON_ID")
	private Long sourcePersonId;

	@ApiModelProperty(value = "人员ID")
	@TableField("PERSON_ID")
	private Long personId;

	@ApiModelProperty(value = "部门代码")
	@TableField("DEPT_CODE")
	private Long deptCode;

	@ApiModelProperty(value = "承担其它相关国家科技计划-角色")
	@TableField("OTHER_PROJECT_ROLE")
	private String otherProjectRole;

	@ApiModelProperty(value = "承担其它相关国家科技计划-项目名称")
	@TableField("OTHER_PROJECT_NAME")
	private String otherProjectName;

	@ApiModelProperty(value = "承担其它相关国家科技计划-项目来源")
	@TableField("OTHER_PROJECT_SOURCE")
	private String otherProjectSource;

	@ApiModelProperty(value = "承担其它相关国家科技计划-开始时间")
	@TableField("OTHER_PROJECT_DATE_START")
	private LocalDateTime otherProjectDateStart;

	@ApiModelProperty(value = "承担其它相关国家科技计划-结束时间")
	@TableField("OTHER_PROJECT_DATE_END")
	private LocalDateTime otherProjectDateEnd;

	@ApiModelProperty(value = "承担其它相关国家科技计划-经费")
	@TableField("OTHER_PROJECT_AMOUNT")
	private String otherProjectAmount;

	@ApiModelProperty(value = "姓名")
	@TableField("PERSON_NAME")
	private String personName;

	@ApiModelProperty(value = "是否是任务负责人")
	@TableField("PERSON_ROLE")
	private String personRole;

	@ApiModelProperty(value = "专业")
	@TableField("MAJOR")
	private String major;

	@ApiModelProperty(value = "年龄")
	@TableField("AGE")
	private Double age;

	@ApiModelProperty(value = "是否变更标识")
	@TableField("CHANGE_FLAG")
	private String changeFlag;

	@ApiModelProperty(value = "变更原因")
	@TableField("CHANGE_REASON")
	private String changeReason;

	@ApiModelProperty(value = "是否有工资性收入")
	@TableField("HAS_SALARY_INPUT")
	private String hasSalaryInput;

	public static final String ID = "ID";

	public static final String PROJECT_ID = "PROJECT_ID";

	public static final String PROJECT_NUM = "PROJECT_NUM";

	public static final String JOIN_WAY = "JOIN_WAY";

	public static final String GENDER = "GENDER";

	public static final String BIRTH_DATE = "BIRTH_DATE";

	public static final String POSITION_TITLE = "POSITION_TITLE";

	public static final String PERSON_CATEGORY = "PERSON_CATEGORY";

	public static final String DEGREE = "DEGREE";

	public static final String DEPT_NAME = "DEPT_NAME";

	public static final String PHONE = "PHONE";

	public static final String ID_CARD = "ID_CARD";

	public static final String WORK_PER_YEAR = "WORK_PER_YEAR";

	public static final String BELONG_TASK = "BELONG_TASK";

	public static final String CREATE_TIME = "CREATE_TIME";

	public static final String CREATE_BY = "CREATE_BY";

	public static final String UPDATE_TIME = "UPDATE_TIME";

	public static final String UPDATE_BY = "UPDATE_BY";

	public static final String SOURCE_PERSON_ID = "SOURCE_PERSON_ID";

	public static final String OTHER_PROJECT_ROLE = "OTHER_PROJECT_ROLE";

	public static final String OTHER_PROJECT_NAME = "OTHER_PROJECT_NAME";

	public static final String OTHER_PROJECT_SOURCE = "OTHER_PROJECT_SOURCE";

	public static final String OTHER_PROJECT_DATE_START = "OTHER_PROJECT_DATE_START";

	public static final String OTHER_PROJECT_DATE_END = "OTHER_PROJECT_DATE_END";

	public static final String PERSON_NAME = "PERSON_NAME";

	public static final String MAJOR = "MAJOR";

	public static final String AGE = "AGE";

	public static final String HAS_SALARY_INPUT = "HAS_SALARY_INPUT";

	public static final String OTHER_PROJECT_AMOUNT = "OTHER_PROJECT_AMOUNT";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
