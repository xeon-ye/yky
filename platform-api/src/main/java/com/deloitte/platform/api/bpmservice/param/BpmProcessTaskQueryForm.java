package com.deloitte.platform.api.bpmservice.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jackliu
 * @Date : Create in 2019-04-02
 * @Description :   BpmProcessTask查询from对象
 * @Modified :
 */
@ApiModel("BpmProcessTask查询表单")
@Data
public class BpmProcessTaskQueryForm extends BaseQueryForm<BpmProcessTaskQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "流程定义key")
    private String processDefineKey;

    @ApiModelProperty(value = "流程实例ID")
    private String processInstanceId;

    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "任务定义key")
    private String taskKey;

    @ApiModelProperty(value = "任务定义名称")
    private String taskName;

    @ApiModelProperty(value = "任务标题")
    private String taskTitle;

    @ApiModelProperty(value = "任务描述")
    private String taskDescription;

    @ApiModelProperty(value = "流程任务审批状态 【1待审批 2已批准 3已驳回 4已提交 5待提交 6已转办 7 终止流程 8 撤回】")
    private String taskStauts;

    @ApiModelProperty(value = "审批人账号")
    private String approverAcount;

    @ApiModelProperty(value = "审批人姓名")
    private String approverName;

    @ApiModelProperty(value = "审批人岗位")
    private String approverStation;

    @ApiModelProperty(value = "审批人描述")
    private String approverDescription;

    @ApiModelProperty(value = "审批对象ID")
    private String objectId;

    @ApiModelProperty(value = "审批对象业务单据编号")
    private String objectOrderNum;

    @ApiModelProperty(value = "审批对象审批状态 【1审批中 2已通过 3 已驳回 4 停止审批】")
    private String objectStauts;

    @ApiModelProperty(value = "审批对象类型")
    private String objectType;

    @ApiModelProperty(value = "审批对象URL")
    private String objectUrl;

    @ApiModelProperty(value = "审批对象描述")
    private String objectDescription;

    @ApiModelProperty(value = "审批意见")
    private String opinion;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "持续时间毫秒数")
    private Long durationTime;

    @ApiModelProperty(value = "提交人账号")
    private String submitterCode;

    @ApiModelProperty(value = "提交人姓名")
    private String submitterName;

    @ApiModelProperty(value = "提交人组织")
    private String submitterOrg;

    @ApiModelProperty(value = "审批人组织")
    private String approverOrg;

    @ApiModelProperty(value = "审批单归属系统")
    private String sourceSystem;

    @ApiModelProperty(value = "提交人岗位")
    private String submitterStation;

    @ApiModelProperty(value = "手机端访问地址")
    private String objectAppUrl;

    @ApiModelProperty(value = "紧急程度")
    private String emergency;

    @ApiModelProperty(value = "签收时间")
    private LocalDateTime signTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createByName;

    @ApiModelProperty(value = "创建人所在部门")
    private String createByOrgId;

    @ApiModelProperty(value = "上一任务id")
    private String previousTaskId;

    @ApiModelProperty(value = "流程创建时间")
    private LocalDateTime processCreateTime;

    @ApiModelProperty(value = "代理人id")
    private String agentId;

    @ApiModelProperty(value = "代理人名称")
    private String agentName;

    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText01;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText02;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText03;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText04;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText05;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText06;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText07;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText08;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText09;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText10;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private Double customNumber01;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private Double customNumber02;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private LocalDateTime customDate01;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private LocalDateTime customDate02;

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

    public static final String SUBMITTER_STATION = "SUBMITTER_STATION";

    public static final String OBJECT_APP_URL = "OBJECT_APP_URL";

    public static final String EMERGENCY = "EMERGENCY";

    public static final String SIGN_TIME = "SIGN_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_BY_NAME = "CREATE_BY_NAME";

    public static final String CREATE_BY_ORG_ID = "CREATE_BY_ORG_ID";

    public static final String PREVIOUS_TASK_ID = "PREVIOUS_TASK_ID";

    public static final String PROCESS_CREATE_TIME = "PROCESS_CREATE_TIME";

    public static final String AGENT_ID = "AGENT_ID";

    public static final String AGENT_NAME = "AGENT_NAME";

    public static final String 	CUSTOM_TEXT01	=	"CUSTOM_TEXT01";
    public static final String 	CUSTOM_TEXT02	=	"CUSTOM_TEXT02";
    public static final String 	CUSTOM_TEXT03	=	"CUSTOM_TEXT03";
    public static final String 	CUSTOM_TEXT04	=	"CUSTOM_TEXT04";
    public static final String 	CUSTOM_TEXT05	=	"CUSTOM_TEXT05";
    public static final String 	CUSTOM_TEXT06	=	"CUSTOM_TEXT06";
    public static final String 	CUSTOM_TEXT07	=	"CUSTOM_TEXT07";
    public static final String 	CUSTOM_TEXT08	=	"CUSTOM_TEXT08";
    public static final String 	CUSTOM_TEXT09	=	"CUSTOM_TEXT09";
    public static final String 	CUSTOM_TEXT10	=	"CUSTOM_TEXT10";
    public static final String 	CUSTOM_NUMBER01	=	"CUSTOM_NUMBER01";
    public static final String 	CUSTOM_NUMBER02	=	"CUSTOM_NUMBER02";
    public static final String 	CUSTOM_DATE01	=	"CUSTOM_DATE01";
    public static final String 	CUSTOM_DATE02	=	"CUSTOM_DATE02";
}
