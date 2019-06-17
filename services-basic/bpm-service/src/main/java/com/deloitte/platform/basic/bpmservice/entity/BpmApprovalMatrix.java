package com.deloitte.platform.basic.bpmservice.entity;

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
 * 
 * </p>
 *
 * @author jackliu
 * @since 2019-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BPM_APPROVAL_MATRIX")
@ApiModel(value="BpmApprovalMatrix对象", description="")
public class BpmApprovalMatrix extends Model<BpmApprovalMatrix> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "流程定义")
    @TableField("PROCESS_DEFINE_KEY")
    private String processDefineKey;

    @ApiModelProperty(value = "节点ID")
    @TableField("ACTIVITY_ID")
    private String activityId;

    @ApiModelProperty(value = "节点名称")
    @TableField("ACTIVITY_NAME")
    private String activityName;

    @ApiModelProperty(value = "负责组织")
    @TableField("CHARGE_ORG")
    private String chargeOrg;

    @ApiModelProperty(value = "负责业务")
    @TableField("CHARGE_BUSINESS")
    private String chargeBusiness;


    @ApiModelProperty(value = "审批人员工编号")
    @TableField("ACCOUNT_EMP_NO")
    private String accountEmpNo;

    @ApiModelProperty(value = "审批人ID")
    @TableField("ACCOUNT_NUM")
    private String accountNum;

    @ApiModelProperty(value = "审批人名称")
    @TableField("ACCOUNT_NAME")
    private String accountName;

    @ApiModelProperty(value = "审批人部门")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "审批人岗位")
    @TableField("POSITION")
    private String position;

    @ApiModelProperty(value = "是否有效")
    @TableField("EFFECTIVE")
    private String effective;

    @ApiModelProperty(value = "生效开始时间")
    @TableField("EFFECTIVE_START")
    private LocalDateTime effectiveStart;

    @ApiModelProperty(value = "失效结束时间")
    @TableField("EFFECTIVE_END")
    private LocalDateTime effectiveEnd;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "部门集合")
    @TableField("ORG_NO_LIST")
    private String orgNoList;

    @ApiModelProperty(value = "岗位集合")
    @TableField("POSITION_LIST")
    private String positionList;

    @ApiModelProperty(value = "角色编号集合")
    @TableField("ROLE_NO_LIST")
    private String roleNoList;

    @ApiModelProperty(value = "规则类型")
    @TableField("RULE_TYPE")
    private String ruleType;

    @ApiModelProperty(value = "自定义规则")
    @TableField("CUSTOM_RULE")
    private String customRule;

    @ApiModelProperty(value = "审批排序")
    @TableField("ORDER_NUM")
    private int orderNum;

    @ApiModelProperty(value = "所属系统")
    @TableField("SYSTEM_CODE")
    private String systemCode;

    public static final String ID = "ID";

    public static final String PROCESS_DEFINE_KEY = "PROCESS_DEFINE_KEY";

    public static final String ACTIVITY_ID = "ACTIVITY_ID";

    public static final String ACTIVITY_NAME = "ACTIVITY_NAME";

    public static final String CHARGE_ORG = "CHARGE_ORG";

    public static final String CHARGE_BUSINESS = "CHARGE_BUSINESS";

    public static final String ACCOUNT_EMP_NO = "ACCOUNT_EMP_NO";

    public static final String ACCOUNT_NUM = "ACCOUNT_NUM";

    public static final String ACCOUNT_NAME = "ACCOUNT_NAME";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String POSITION = "POSITION";

    public static final String EFFECTIVE = "EFFECTIVE";

    public static final String EFFECTIVE_START = "EFFECTIVE_START";

    public static final String EFFECTIVE_END = "EFFECTIVE_END";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String ORG_NO_LIST = "ORG_NO_LIST";

    public static final String POSITION_LIST = "POSITION_LIST";

    public static final String ROLE_NO_LIST = "ROLE_NO_LIST";

    public static final String RULE_TYPE = "RULE_TYPE";

    public static final String CUSTOM_RULE = "CUSTOM_RULE";

    public static final String ORDER_NUM = "ORDER_NUM";

    public static final String SYSTEM_CODE = "SYSTEM_CODE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
