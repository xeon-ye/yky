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
 * 合同管理系统 业务流程表
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_PROCESS")
@ApiModel(value="Process对象", description="合同管理系统 业务流程表")
public class Process extends Model<Process> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "机构")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "收支类型")
    @TableField("INCOME_EXPENDITURE_TYPE_CODE")
    private String incomeExpenditureTypeCode;

    @ApiModelProperty(value = "流程类型(1：合同审批，2：合同打印签署，3：合同办结，4：合同作废，5：经办人移交，6：履行人移交)")
    @TableField("PROCESS_TYPE")
    private String processType;

    @ApiModelProperty(value = "最小合同金额")
    @TableField("CONTRACT_AMOUNT_MIN")
    private Long contractAmountMin;

    @ApiModelProperty(value = "最大合同金额")
    @TableField("CONTRACT_AMOUNT_MAX")
    private Long contractAmountMax;

    @ApiModelProperty(value = "流程key")
    @TableField("PROCESS_DEFINE_KEY")
    private String processDefineKey;

    @ApiModelProperty(value = "流程名称")
    @TableField("PROCESS_DEFINE_NAME")
    private String processDefineName;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    @TableField("IS_USED")
    private String isUsed;

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

    public static final String ORG_CODE = "ORG_CODE";

    public static final String INCOME_EXPENDITURE_TYPE_CODE = "INCOME_EXPENDITURE_TYPE_CODE";

    public static final String PROCESS_TYPE = "PROCESS_TYPE";

    public static final String CONTRACT_AMOUNT_MIN = "CONTRACT_AMOUNT_MIN";

    public static final String CONTRACT_AMOUNT_MAX = "CONTRACT_AMOUNT_MAX";

    public static final String PROCESS_DEFINE_KEY = "PROCESS_DEFINE_KEY";

    public static final String PROCESS_DEFINE_NAME = "PROCESS_DEFINE_NAME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String IS_USED = "IS_USED";

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
