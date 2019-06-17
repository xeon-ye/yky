package com.deloitte.platform.basic.bpmservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qifu
 * @since 2019-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BPM_PROCESS_TASK_APPROVE")
@ApiModel(value="BpmProcessTaskApprove对象", description="")
public class BpmProcessTaskApprove extends Model<BpmProcessTaskApprove> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "流程实例id")
    @TableField("PROCESS_INSTANCE_ID")
    private String processInstanceId;

    @ApiModelProperty(value = "上一任务的id")
    @TableField("PREVIOUS_TASK_ID")
    private String previousTaskId;

    @ApiModelProperty(value = "当前任务节点")
    @TableField("TASK_KEY")
    private String taskKey;

    @ApiModelProperty(value = "审批人id")
    @TableField("ACOUNT_ID")
    private String acountId;

    @ApiModelProperty(value = "审批人名称")
    @TableField("ACOUNT_NAME")
    private String acountName;

    @ApiModelProperty(value = "代理人id")
    @TableField("AGENT_ID")
    private String agentId;

    @ApiModelProperty(value = "代理人名称")
    @TableField("AGENT_NAME")
    private String agentName;

    @ApiModelProperty(value = "岗位id")
    @TableField("STATION_ID")
    private String stationId;

    @ApiModelProperty(value = "所属组织")
    @TableField("ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "审批描述")
    @TableField("APPROVER_DESCRIPTION")
    private String approverDescription;

    @ApiModelProperty(value = "业务访问地址")
    @TableField("OBJECT_URL")
    private String objectUrl;

    @ApiModelProperty(value = "app端业务访问地址")
    @TableField("OBJECT_APP_URL")
    private String objectAppUrl;

    @ApiModelProperty(value = "排序号")
    @TableField("ORDER_NUM")
    private int orderNum;

    @ApiModelProperty(value = "是否已结束{1：结束，0：未经过}")
    @TableField("END_FLAG")
    private String endFlag;


    public static final String ID = "ID";

    public static final String PROCESS_INSTANCE_ID = "PROCESS_INSTANCE_ID";

    public static final String PREVIOUS_TASK_ID = "PREVIOUS_TASK_ID";

    public static final String TASK_KEY = "TASK_KEY";

    public static final String ACOUNT_ID = "ACOUNT_ID";

    public static final String ACOUNT_NAME = "ACOUNT_NAME";

    public static final String AGENT_ID = "AGENT_ID";

    public static final String AGENT_NAME = "AGENT_NAME";

    public static final String STATION_ID = "STATION_ID";

    public static final String ORG_ID = "ORG_ID";

    public static final String APPROVER_DESCRIPTION = "APPROVER_DESCRIPTION";

    public static final String OBJECT_URL = "OBJECT_URL";

    public static final String OBJECT_APP_URL = "OBJECT_APP_URL";

    public static final String ORDER_NUM = "ORDER_NUM";

    public static final String END_FLAG = "END_FLAG";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
