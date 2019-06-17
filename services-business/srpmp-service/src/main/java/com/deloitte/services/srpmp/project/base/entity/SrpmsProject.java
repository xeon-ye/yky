package com.deloitte.services.srpmp.project.base.entity;

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
 * 项目表
 * </p>
 *
 * @author pengchao
 * @since 2019-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT")
@ApiModel(value="SrpmsProject对象", description="项目表")
public class SrpmsProject extends Model<SrpmsProject> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    @JSONField(serializeUsing = LongToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NUM")
    private String projectNum;

    @ApiModelProperty(value = "申请书编号")
    @TableField("APL_NUM")
    private String aplNum;

    @ApiModelProperty(value = "预算书编号")
    @TableField("BUD_NUM")
    private String budNum;

    @ApiModelProperty(value = "任务书编号")
    @TableField("TAS_NUM")
    private String tasNum;

    @ApiModelProperty(value = "公示件编号")
    @TableField("PUD_NUM")
    private String pudNum;

    @ApiModelProperty(value = "批复件编号")
    @TableField("APD_NUM")
    private String apdNum;

    @ApiModelProperty(value = "申报年度")
    @TableField("APPLY_YEAR")
    private String applyYear;

    @ApiModelProperty(value = "项目类型CODE")
    @TableField("PROJECT_CATEGORY")
    private String projectCategory;

    @ApiModelProperty(value = "项目类型（最小分类）")
    @TableField("PROJECT_TYPE")
    private String projectType;

    @ApiModelProperty(value = "项目执行期限开始")
    @TableField("PROJECT_ACTION_DATE_START")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    @TableField("PROJECT_ACTION_DATE_END")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目负责人ID")
    @TableField("LEAD_PERSON_ID")
    private Long leadPersonId;

    @ApiModelProperty(value = "共同首席专家ID")
    @TableField("BOTH_TOP_EXPERT_PERSON_ID")
    private Long bothTopExpertPersonId;

    @ApiModelProperty(value = "负责人信息JSON")
    @TableField("LEAD_PERSON")
    private String leadPerson;

    @ApiModelProperty(value = "共同负责人信息JSON")
    @TableField("BOTH_TOP_EXPERT_PERSON")
    private String bothTopExpertPerson;

    @ApiModelProperty(value = "承担单位ID")
    @TableField("LEAD_DEPT_ID")
    private Long leadDeptId;

    @ApiModelProperty(value = "承担单位信息JSON")
    @TableField("LEAD_DEPT")
    private String leadDept;

    @ApiModelProperty(value = "项目角色CODE")
    @TableField("PROJECT_ROLE")
    private String projectRole;

    @ApiModelProperty(value = "学科分类CODE")
    @TableField("SUBJECT_CATEGORY")
    private String subjectCategory;

    @ApiModelProperty(value = "项目状态 0：未提交 10：已提交(待审核) 15：二级已审核  20：已审核通过  30：已审核拒绝 40：已公示  50：已批复  ")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "公示时间")
    @TableField(value = "PUBLIC_TIME", fill = FieldFill.INSERT)
    private LocalDateTime publicTime;

    @ApiModelProperty(value = "批复时间")
    @TableField(value = "APPROVE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime approveTime;

    @ApiModelProperty(value = "删除标识位")
    @TableField("DEL_FLG")
    private String delFlg;

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

    @ApiModelProperty(value = "申请书文件ID")
    @TableField("APPLY_BOOK_FILE_ID")
    @JSONField(serializeUsing = LongToStringSerializer.class)
    private Long applyBookFileId;

    @ApiModelProperty(value = "申请书文件URL")
    @TableField("APPLY_BOOK_FILE_URL")
    private String applyBookFileUrl;

    @ApiModelProperty(value = "申请书文件文件名")
    @TableField("APPLY_BOOK_FILE_NAME")
    private String applyBookFileName;

    @ApiModelProperty(value = "预算书申请书文件ID")
    @TableField("BUDGET_BOOK_FILE_ID")
    @JSONField(serializeUsing = LongToStringSerializer.class)
    private Long budgetBookFileId;

    @ApiModelProperty(value = "预算申请书文件文件URL")
    @TableField("BUDGET_BOOK_FILE_URL")
    private String budgetBookFileUrl;

    @ApiModelProperty(value = "预算申请书文件文件名")
    @TableField("BUDGET_BOOK_FILE_NAME")
    private String budgetBookFileName;

    @ApiModelProperty(value = "申请书预算书首次打开标志")
    @TableField("BUD_FIRST_OPEN_FLG")
    private String budFirstOpenFlg;

    @ApiModelProperty(value = "任务书首次打开标志")
    @TableField("TASK_FIRST_OPEN_FLG")
    private String taskFirstOpenFlg;

    @ApiModelProperty(value = "任务书预算书首次打开标志")
    @TableField("TASK_BUD_FIRST_OPEN_FLG")
    private String taskBudFirstOpenFlg;

    @ApiModelProperty(value = "任务书文件ID")
    @TableField("TASK_BOOK_FILE_ID")
    @JSONField(serializeUsing = LongToStringSerializer.class)
    private Long taskBookFileId;

    @ApiModelProperty(value = "任务书文件名")
    @TableField("TASK_BOOK_FILE_NAME")
    private String taskBookFileName;

    @ApiModelProperty(value = "任务书文件URL")
    @TableField("TASK_BOOK_FILE_URL")
    private String taskBookFileUrl;

    @ApiModelProperty(value = "公示文件文件ID")
    @TableField("PUBLISH_BOOK_FILE_ID")
    @JSONField(serializeUsing = LongToStringSerializer.class)
    private Long publishBookFileId;

    @ApiModelProperty(value = "公示文件文件名")
    @TableField("PUBLISH_BOOK_FILE_NAME")
    private String publishBookFileName;

    @ApiModelProperty(value = "公示文件文件URL")
    @TableField("PUBLISH_BOOK_FILE_URL")
    private String publishBookFileUrl;

    @ApiModelProperty(value = "批复文件文件ID")
    @TableField("APPROVE_BOOK_FILE_ID")
    @JSONField(serializeUsing = LongToStringSerializer.class)
    private Long approveBookFileId;

    @ApiModelProperty(value = "批复文件文件名")
    @TableField("APPROVE_BOOK_FILE_NAME")
    private String approveBookFileName;

    @ApiModelProperty(value = "批复文件文件URL")
    @TableField("APPROVE_BOOK_FILE_URL")
    private String approveBookFileUrl;

    @ApiModelProperty(value = "预算任务书文件ID")
    @TableField("BUDGET_TASK_BOOK_FILE_ID")
    @JSONField(serializeUsing = LongToStringSerializer.class)
    private Long budgetTaskBookFileId;

    @ApiModelProperty(value = "预算任务书文件文件URL")
    @TableField("BUDGET_TASK_BOOK_FILE_URL")
    private String budgetTaskBookFileUrl;

    @ApiModelProperty(value = "预算任务书文件文件名")
    @TableField("BUDGET_TASK_BOOK_FILE_NAME")
    private String budgetTaskBookFileName;

    @ApiModelProperty(value = "项目标识位")
    @TableField("PROJECT_FLAG")
    private String projectFlag;

    @ApiModelProperty(value = "验收编号")
    @TableField("ACCEPT_NUM")
    private String acceptNum;


    public static final String ID = "ID";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PROJECT_NUM = "PROJECT_NUM";

    public static final String APL_NUM = "APL_NUM";

    public static final String BUD_NUM = "BUD_NUM";

    public static final String TAS_NUM = "TAS_NUM";

    public static final String PUD_NUM = "PUD_NUM";

    public static final String APD_NUM = "APD_NUM";

    public static final String APPLY_YEAR = "APPLY_YEAR";

    public static final String PROJECT_CATEGORY = "PROJECT_CATEGORY";

    public static final String PROJECT_TYPE = "PROJECT_TYPE";

    public static final String PROJECT_ACTION_DATE_START = "PROJECT_ACTION_DATE_START";

    public static final String PROJECT_ACTION_DATE_END = "PROJECT_ACTION_DATE_END";

    public static final String LEAD_PERSON_ID = "LEAD_PERSON_ID";

    public static final String BOTH_TOP_EXPERT_PERSON_ID = "BOTH_TOP_EXPERT_PERSON_ID";

    public static final String LEAD_PERSON = "LEAD_PERSON";

    public static final String BOTH_TOP_EXPERT_PERSON = "BOTH_TOP_EXPERT_PERSON";

    public static final String LEAD_DEPT_ID = "LEAD_DEPT_ID";

    public static final String LEAD_DEPT = "LEAD_DEPT";

    public static final String PROJECT_ROLE = "PROJECT_ROLE";

    public static final String SUBJECT_CATEGORY = "SUBJECT_CATEGORY";

    public static final String STATUS = "STATUS";

    public static final String PUBLIC_TIME = "PUBLIC_TIME";

    public static final String APPROVE_TIME = "APPROVE_TIME";

    public static final String DEL_FLG = "DEL_FLG";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String APPLY_BOOK_FILE_ID = "APPLY_BOOK_FILE_ID";

    public static final String APPLY_BOOK_FILE_URL = "APPLY_BOOK_FILE_URL";

    public static final String APPLY_BOOK_FILE_NAME = "APPLY_BOOK_FILE_NAME";

    public static final String BUDGET_BOOK_FILE_ID = "BUDGET_BOOK_FILE_ID";

    public static final String BUDGET_BOOK_FILE_URL = "BUDGET_BOOK_FILE_URL";

    public static final String BUDGET_BOOK_FILE_NAME = "BUDGET_BOOK_FILE_NAME";

    public static final String BUD_FIRST_OPEN_FLG = "BUD_FIRST_OPEN_FLG";

    public static final String TASK_FIRST_OPEN_FLG = "TASK_FIRST_OPEN_FLG";

    public static final String TASK_BUD_FIRST_OPEN_FLG = "TASK_BUD_FIRST_OPEN_FLG";

    public static final String TASK_BOOK_FILE_ID = "TASK_BOOK_FILE_ID";

    public static final String TASK_BOOK_FILE_NAME = "TASK_BOOK_FILE_NAME";

    public static final String TASK_BOOK_FILE_URL = "TASK_BOOK_FILE_URL";

    public static final String PUBLISH_BOOK_FILE_ID = "PUBLISH_BOOK_FILE_ID";

    public static final String PUBLISH_BOOK_FILE_NAME = "PUBLISH_BOOK_FILE_NAME";

    public static final String PUBLISH_BOOK_FILE_URL = "PUBLISH_BOOK_FILE_URL";

    public static final String APPROVE_BOOK_FILE_ID = "APPROVE_BOOK_FILE_ID";

    public static final String APPROVE_BOOK_FILE_NAME = "APPROVE_BOOK_FILE_NAME";

    public static final String APPROVE_BOOK_FILE_URL = "APPROVE_BOOK_FILE_URL";

    public static final String BUDGET_TASK_BOOK_FILE_ID = "BUDGET_TASK_BOOK_FILE_ID";

    public static final String BUDGET_TASK_BOOK_FILE_URL = "BUDGET_TASK_BOOK_FILE_URL";

    public static final String BUDGET_TASK_BOOK_FILE_NAME = "BUDGET_TASK_BOOK_FILE_NAME";

    public static final String PROJECT_FLAG = "PROJECT_FLAG";

    public static final String ACCEPT_NUM = "ACCEPT_NUM";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
