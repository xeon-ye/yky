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
 * 合同终止业务表
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROCESS_CONTRACT_STOP")
@ApiModel(value="ProcessContractStop对象", description="合同终止业务表")
public class ProcessContractStop extends Model<ProcessContractStop> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "合同编号")
    @TableField("CONTRACT_ID")
    private Long contractId;

    @ApiModelProperty(value = "发起人编号")
    @TableField("SENDER_CODE")
    private String senderCode;

    @ApiModelProperty(value = "发起人")
    @TableField("SNEDER")
    private String sneder;

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


    public static final String ID = "ID";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String SENDER_CODE = "SENDER_CODE";

    public static final String SNEDER = "SNEDER";

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
