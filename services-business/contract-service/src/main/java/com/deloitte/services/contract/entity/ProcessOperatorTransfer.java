package com.deloitte.services.contract.entity;

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
 * 经办人移交业务表
 * </p>
 *
 * @author hemingzheng
 * @since 2019-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROCESS_OPERATOR_TRANSFER")
@ApiModel(value="ProcessOperatorTransfer对象", description="经办人移交业务表")
public class ProcessOperatorTransfer extends Model<ProcessOperatorTransfer> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "合同编号")
    @TableField("CONTRACT_ID")
    private String contractId;

    @ApiModelProperty(value = "发起人编号")
    @TableField("OPERATOR_CODE")
    private String operatorCode;

    @ApiModelProperty(value = "发起人")
    @TableField("OPERATOR")
    private String operator;

    @ApiModelProperty(value = "新经办人编号")
    @TableField("NEW_OPERATOR_CODE")
    private String newOperatorCode;

    @ApiModelProperty(value = "新经办人")
    @TableField("NEW_OPERATOR")
    private String newOperator;

    @ApiModelProperty(value = "流程id")
    @TableField("PROCESS_ID")
    private String processId;

    @ApiModelProperty(value = "流程实例id")
    @TableField("PROCESS_INSTANCE_ID")
    private String processInstanceId;

    @ApiModelProperty(value = "审批状态")
    @TableField("STATUE")
    private String statue;

    @ApiModelProperty(value = "发起时间")
    @TableField("SEND_TIME")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_1")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_2")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_3")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_4")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_5")
    private Long spareField5;

    @ApiModelProperty(value = "经办人移交原因")
    @TableField("TRANSFER_RESULT")
    private String transferResult;

    @ApiModelProperty(value = "经办人部门ID")
    @TableField("ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "经办人部门")
    @TableField("ORG")
    private String org;

    @ApiModelProperty(value = "发起人部门Id")
    @TableField("OLD_ORG_ID")
    private String oldOrgId;

    @ApiModelProperty(value = "发起人部门")
    @TableField("OLD_ORG")
    private String oldOrg;

    @ApiModelProperty(value = "新经办人联系方式")
    @TableField("OPERATOR_INFOMATION")
    private String operatorInfomation;

    @ApiModelProperty(value = "经办人联系方式")
    @TableField("OLD_OPERATOR_INFOMATION")
    private String oldOperatorInfomation;




    public static final String ID = "ID";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String OPERATOR_CODE = "OPERATOR_CODE";

    public static final String OPERATOR = "OPERATOR";

    public static final String NEW_OPERATOR_CODE = "NEW_OPERATOR_CODE";

    public static final String NEW_OPERATOR = "NEW_OPERATOR";

    public static final String PROCESS_ID = "PROCESS_ID";

    public static final String PROCESS_INSTANCE_ID = "PROCESS_INSTANCE_ID";

    public static final String STATUE = "STATUE";

    public static final String SEND_TIME = "SEND_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    public static final String TRANSFER_RESULT = "TRANSFER_RESULT";

    public static final String OLD_ORG_ID = "OLD_ORG_ID";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG= "ORG";

    public static final String OLD_ORG = "OLD_ORG";

    public static final String OPERATOR_INFOMATION = "OPERATOR_INFOMATION";

    public static final String OLD_OPERATOR_INFOMATION = "OLD_OPERATOR_INFOMATION";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
