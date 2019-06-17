package com.deloitte.services.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 申报书
 * </p>
 *
 * @author zhengchun
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_APPLICATION")
@ApiModel(value="Application对象", description="申报书")
public class Application extends Model<Application> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "申报书ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "申报年度")
    @TableField("THE_APPLICATION_YEAR")
    private String theApplicationYear;

    @ApiModelProperty(value = "预算立项依据")
    @TableField("BUD_BASIS")
    private String budBasis;

    @ApiModelProperty(value = "预算主要内容")
    @TableField("BUD_CONTENT")
    private String budContent;

    @ApiModelProperty(value = "预算项目总体目标及分阶段实施方案")
    @TableField("BUD_TARGET_MEASURE")
    private String budTargetMeasure;

    @ApiModelProperty(value = "预算项目实施条件")
    @TableField("BUD_CONDITION")
    private String budCondition;

    @ApiModelProperty(value = "科研修购项目立项依据")
    @TableField("SCI_BASIS")
    private String sciBasis;

    @ApiModelProperty(value = "科研修购项目情况概述")
    @TableField("SCI_CONTENT")
    private String sciContent;

    @ApiModelProperty(value = "教育修购项目实施必要性分析")
    @TableField("EDU_NEED")
    private String eduNeed;

    @ApiModelProperty(value = "教育修购项目实施可行性分析")
    @TableField("EDU_MAYBE")
    private String eduMaybe;

    @ApiModelProperty(value = "教育修购实施条件")
    @TableField("EDU_CONDI")
    private String eduCondi;

    @ApiModelProperty(value = "教育修购项目实施主要内容和相关预算")
    @TableField("EDU_BUDGE")
    private String eduBudge;

    @ApiModelProperty(value = "教育修购项目进度与计划安排")
    @TableField("EDU_PLAN")
    private String eduPlan;

    @ApiModelProperty(value = "教育修购子活动风险与不确定性分析")
    @TableField("EDU_ANALYSIS")
    private String eduAnalysis;

    @ApiModelProperty(value = "教育修购预期经济社会效益")
    @TableField("EDU_BENEFIT")
    private String eduBenefit;

    @ApiModelProperty(value = "教育修购项目立项依据")
    @TableField("EDU_CONTENT")
    private String eduContent;

    @ApiModelProperty(value = "预算科目类")
    @TableField("BUDGET_ACC_TYPE")
    private String budgetAccType;

    @ApiModelProperty(value = "预算科目款")
    @TableField("BUDGET_ACC_VALUE")
    private String budgetAccValue;

    @ApiModelProperty(value = "优先级排序")
    @TableField("PRIORITY")
    private String priority;

    @ApiModelProperty(value = "单位预算编码")
    @TableField("OU_BUDGET_CODE")
    private String ouBudgetCode;

    @ApiModelProperty(value = "主管部门")
    @TableField("DEPARTMENT")
    private String department;

    @ApiModelProperty(value = "项目学校")
    @TableField("SCHOOL")
    private String school;

    @ApiModelProperty(value = "申报状态")
    @TableField("APP_STATE_CODE")
    private String appStateCode;

    @ApiModelProperty(value = "申报状态名称")
    @TableField("APP_STATE_NAME")
    private String appStateName;

    @ApiModelProperty(value = "主管部门代码")
    @TableField("DEPARTMENT_CODE")
    private String departmentCode;

    @ApiModelProperty(value = "项目属性")
    @TableField("PROJECT_ATTRIBUTE")
    private String projectAttribute;

    @ApiModelProperty(value = "实施单位")
    @TableField("OPERATION_OU")
    private String operationOu;

    @ApiModelProperty(value = "项目执行中项目变更原因")
    @TableField("PRO_CHANGE")
    private String proChange;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "数据权限维度字段ORG_ID")
    @TableField("ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "数据权限维度字段ORG_PATH")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "申报书的ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "项目类别CODE")
    @TableField("SUBPROJECT_TYPE_CODE")
    private String subprojectTypeCode;

    @ApiModelProperty(value = "项目类别NAME")
    @TableField("SUBPROJECT_TYPE_NAME")
    private String subprojectTypeName;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "业务单号流水号")
    @TableField("SERVICE_NUM")
    private String serviceNum;

    @ApiModelProperty(value = "单位地址")
    @TableField("PRO_ADRESS")
    private String proAdress;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("CREATE_USER_NAME")
    private String createUserName;

    @ApiModelProperty(value = "创建人id")
    @TableField("CREATE_USER_ID")
    private String createUserId;


    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String THE_APPLICATION_YEAR = "THE_APPLICATION_YEAR";

    public static final String BUD_BASIS = "BUD_BASIS";

    public static final String BUD_CONTENT = "BUD_CONTENT";

    public static final String BUD_TARGET_MEASURE = "BUD_TARGET_MEASURE";

    public static final String BUD_CONDITION = "BUD_CONDITION";

    public static final String SCI_BASIS = "SCI_BASIS";

    public static final String SCI_CONTENT = "SCI_CONTENT";

    public static final String EDU_NEED = "EDU_NEED";

    public static final String EDU_MAYBE = "EDU_MAYBE";

    public static final String EDU_CONDI = "EDU_CONDI";

    public static final String EDU_BUDGE = "EDU_BUDGE";

    public static final String EDU_PLAN = "EDU_PLAN";

    public static final String EDU_ANALYSIS = "EDU_ANALYSIS";

    public static final String EDU_BENEFIT = "EDU_BENEFIT";

    public static final String EDU_CONTENT = "EDU_CONTENT";

    public static final String BUDGET_ACC_TYPE = "BUDGET_ACC_TYPE";

    public static final String BUDGET_ACC_VALUE = "BUDGET_ACC_VALUE";

    public static final String PRIORITY = "PRIORITY";

    public static final String OU_BUDGET_CODE = "OU_BUDGET_CODE";

    public static final String DEPARTMENT = "DEPARTMENT";

    public static final String SCHOOL = "SCHOOL";

    public static final String APP_STATE_CODE = "APP_STATE_CODE";

    public static final String APP_STATE_NAME = "APP_STATE_NAME";

    public static final String DEPARTMENT_CODE = "DEPARTMENT_CODE";

    public static final String PROJECT_ATTRIBUTE = "PROJECT_ATTRIBUTE";

    public static final String OPERATION_OU = "OPERATION_OU";

    public static final String PRO_CHANGE = "PRO_CHANGE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String SUBPROJECT_TYPE_CODE = "SUBPROJECT_TYPE_CODE";

    public static final String SUBPROJECT_TYPE_NAME = "SUBPROJECT_TYPE_NAME";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String SERVICE_NUM = "SERVICE_NUM";

    public static final String PRO_ADRESS = "PRO_ADRESS";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String CREATE_USER_ID = "CREATE_USER_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
