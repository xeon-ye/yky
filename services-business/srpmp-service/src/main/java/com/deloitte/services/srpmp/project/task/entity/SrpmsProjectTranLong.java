package com.deloitte.services.srpmp.project.task.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 任务书-横纵项项目
 * </p>
 *
 * @author carlos
 * @since 2019-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_TRAN_LONG")
@ApiModel(value="SrpmsProjectTranLong对象", description="任务书-横纵项项目")
public class SrpmsProjectTranLong extends Model<SrpmsProjectTranLong> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编码")
    @TableField("PROJECT_CODE")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "参与程度")
    @TableField("PRO_ENTER_TYPE")
    private String proEnterType;

    @ApiModelProperty(value = "预期成果类型")
    @TableField("PROJECT_RESULT_TYPE")
    private String projectResultType;

    @ApiModelProperty(value = "所在领域")
    @TableField("BELONG_DOMAIN")
    private String belongDomain;

    @ApiModelProperty(value = "院外主要参与人员")
    @TableField("OUT_JOIN_PERSON")
    private String outJoinPerson;

    @ApiModelProperty(value = "项目执行开始时间")
    @TableField("PROJECT_ACTION_DATE_START")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行结束时间")
    @TableField("PROJECT_ACTION_DATE_END")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "任务分解相关说明")
    @TableField("TASK_DECOMPOSITION_INSTRUCTION")
    private String taskDecompositionInstruction;

    @ApiModelProperty(value = "参加人员相关说明")
    @TableField("JOIN_PERSON_INSTRUCTION")
    private String joinPersonInstruction;

    @ApiModelProperty(value = "项目经费来源")
    @TableField("PRO_FUNDS_SOURCE")
    private String proFundsSource;

    @ApiModelProperty(value = "预算相关说明")
    @TableField("BUDGET_INSTRUCTION")
    private String budgetInstruction;

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


    public static final String ID = "ID";

    public static final String PROJECT_CODE = "PROJECT_CODE";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PRO_ENTER_TYPE = "PRO_ENTER_TYPE";

    public static final String PROJECT_RESULT_TYPE = "PROJECT_RESULT_TYPE";

    public static final String BELONG_DOMAIN = "BELONG_DOMAIN";

    public static final String OUT_JOIN_PERSON = "OUT_JOIN_PERSON";

    public static final String PROJECT_ACTION_DATE_START = "PROJECT_ACTION_DATE_START";

    public static final String PROJECT_ACTION_DATE_END = "PROJECT_ACTION_DATE_END";

    public static final String TASK_DECOMPOSITION_INSTRUCTION = "TASK_DECOMPOSITION_INSTRUCTION";

    public static final String JOIN_PERSON_INSTRUCTION = "JOIN_PERSON_INSTRUCTION";

    public static final String PRO_FUNDS_SOURCE = "PRO_FUNDS_SOURCE";

    public static final String BUDGET_INSTRUCTION = "BUDGET_INSTRUCTION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
