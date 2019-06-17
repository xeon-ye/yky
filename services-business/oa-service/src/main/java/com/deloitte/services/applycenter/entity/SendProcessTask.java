package com.deloitte.services.applycenter.entity;

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
 * 抄送待阅表
 * </p>
 *
 * @author yidaojun
 * @since 2019-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_SEND_PROCESS_TASK")
@ApiModel(value="SendProcessTask对象", description="抄送待阅表")
public class SendProcessTask extends Model<SendProcessTask> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "流程定义key")
    @TableField("PROCESS_DEFINE_KEY")
    private String processDefineKey;

    @TableField("PROCESS_INSTANCE_ID")
    private String processInstanceId;

    @ApiModelProperty(value = "任务ID")
    @TableField("TASK_ID")
    private String taskId;

    @ApiModelProperty(value = "任务定义key")
    @TableField("TASK_KEY")
    private String taskKey;

    @ApiModelProperty(value = "任务定义名称")
    @TableField("TASK_NAME")
    private String taskName;

    @ApiModelProperty(value = "任务标题")
    @TableField("TASK_TITLE")
    private String taskTitle;

    @ApiModelProperty(value = "任务描述")
    @TableField("TASK_DESCRIPTION")
    private String taskDescription;

    @ApiModelProperty(value = "流程任务审批状态 【待阅、已阅】")
    @TableField("TASK_STAUTS")
    private String taskStauts;

    @ApiModelProperty(value = "审批人账号")
    @TableField("APPROVER_ACOUNT")
    private String approverAcount;

    @ApiModelProperty(value = "审批人姓名")
    @TableField("APPROVER_NAME")
    private String approverName;

    @ApiModelProperty(value = "审批人岗位")
    @TableField("APPROVER_STATION")
    private String approverStation;

    @ApiModelProperty(value = "审批人描述")
    @TableField("APPROVER_DESCRIPTION")
    private String approverDescription;

    @ApiModelProperty(value = "审批对象ID")
    @TableField("OBJECT_ID")
    private String objectId;

    @ApiModelProperty(value = "审批对象业务单据编号")
    @TableField("OBJECT_ORDER_NUM")
    private String objectOrderNum;

    @ApiModelProperty(value = "审批对象审批状态 【1审批中 2已通过 3 已驳回 4 停止审批】")
    @TableField("OBJECT_STAUTS")
    private String objectStauts;

    @ApiModelProperty(value = "审批对象类型")
    @TableField("OBJECT_TYPE")
    private String objectType;

    @ApiModelProperty(value = "审批对象URL")
    @TableField("OBJECT_URL")
    private String objectUrl;

    @ApiModelProperty(value = "审批对象描述")
    @TableField("OBJECT_DESCRIPTION")
    private String objectDescription;

    @ApiModelProperty(value = "审批意见")
    @TableField("OPINION")
    private String opinion;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "持续时间毫秒数")
    @TableField("DURATION_TIME")
    private Long durationTime;

    @ApiModelProperty(value = "提交人账号")
    @TableField("SUBMITTER_CODE")
    private String submitterCode;

    @ApiModelProperty(value = "提交人姓名")
    @TableField("SUBMITTER_NAME")
    private String submitterName;

    @ApiModelProperty(value = "提交人组织")
    @TableField("SUBMITTER_ORG")
    private String submitterOrg;

    @ApiModelProperty(value = "审批人组织")
    @TableField("APPROVER_ORG")
    private String approverOrg;

    @ApiModelProperty(value = "审批单归属系统")
    @TableField("SOURCE_SYSTEM")
    private String sourceSystem;

    @ApiModelProperty(value = "任务创建人，即该条记录的创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "任务创建人所在部门")
    @TableField("CREATE_BY_ORG_ID")
    private String createByOrgId;

    @ApiModelProperty(value = "流程发起时间")
    @TableField("PROCESS_CREATE_TIME")
    private LocalDateTime processCreateTime;

    @ApiModelProperty(value = "app地址")
    @TableField("OBJECT_APP_URL")
    private String objectAppUrl;

    @ApiModelProperty(value = "紧急程度")
    @TableField("EMERGENCY")
    private String emergency;

    @ApiModelProperty(value = "提交人岗位")
    @TableField("SUBMITTER_STATION")
    private String submitterStation;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("CREATE_BY_NAME")
    private String createByName;

    @ApiModelProperty(value = "上一任务id")
    @TableField("PREVIOUS_TASK_ID")
    private String previousTaskId;

    @ApiModelProperty(value = "金额")
    @TableField("MONEY")
    private String money;


    public static final String ID = "ID";

    public static final String PROCESS_DEFINE_KEY = "PROCESS_DEFINE_KEY";

    public static final String PROCESS_INSTANCE_ID = "PROCESS_INSTANCE_ID";

    public static final String TASK_ID = "TASK_ID";

    public static final String TASK_KEY = "TASK_KEY";

    public static final String TASK_NAME = "TASK_NAME";

    public static final String TASK_TITLE = "TASK_TITLE";

    public static final String TASK_DESCRIPTION = "TASK_DESCRIPTION";

    public static final String TASK_STAUTS = "TASK_STAUTS";

    public static final String APPROVER_ACOUNT = "APPROVER_ACOUNT";

    public static final String APPROVER_NAME = "APPROVER_NAME";

    public static final String APPROVER_STATION = "APPROVER_STATION";

    public static final String APPROVER_DESCRIPTION = "APPROVER_DESCRIPTION";

    public static final String OBJECT_ID = "OBJECT_ID";

    public static final String OBJECT_ORDER_NUM = "OBJECT_ORDER_NUM";

    public static final String OBJECT_STAUTS = "OBJECT_STAUTS";

    public static final String OBJECT_TYPE = "OBJECT_TYPE";

    public static final String OBJECT_URL = "OBJECT_URL";

    public static final String OBJECT_DESCRIPTION = "OBJECT_DESCRIPTION";

    public static final String OPINION = "OPINION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String DURATION_TIME = "DURATION_TIME";

    public static final String SUBMITTER_CODE = "SUBMITTER_CODE";

    public static final String SUBMITTER_NAME = "SUBMITTER_NAME";

    public static final String SUBMITTER_ORG = "SUBMITTER_ORG";

    public static final String APPROVER_ORG = "APPROVER_ORG";

    public static final String SOURCE_SYSTEM = "SOURCE_SYSTEM";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_BY_ORG_ID = "CREATE_BY_ORG_ID";

    public static final String PROCESS_CREATE_TIME = "PROCESS_CREATE_TIME";

    public static final String OBJECT_APP_URL = "OBJECT_APP_URL";

    public static final String EMERGENCY = "EMERGENCY";

    public static final String SUBMITTER_STATION = "SUBMITTER_STATION";

    public static final String CREATE_BY_NAME = "CREATE_BY_NAME";

    public static final String PREVIOUS_TASK_ID = "PREVIOUS_TASK_ID";

    public static final String MONEY = "MONEY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
