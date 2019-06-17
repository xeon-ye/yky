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
 * 合同其他监控信息
 * </p>
 *
 * @author yangyq
 * @since 2019-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_MONITOR_INFO")
@ApiModel(value="MonitorInfo对象", description="合同其他监控信息")
public class MonitorInfo extends Model<MonitorInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "监控事项")
    @TableField("MONITOR_NAME")
    private String monitorName;

    @ApiModelProperty(value = "计划完成日期")
    @TableField("PLAN_FINISH_TIME")
    private LocalDateTime planFinishTime;

    @ApiModelProperty(value = "计划完成金额/数量")
    @TableField("PLAN_FINISH_NUM")
    private Double planFinishNum;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    @TableField("IS_USED")
    private String isUsed;

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

    @ApiModelProperty(value = "合同编号")
    @TableField("CONTRACT_ID")
    private Long contractId;

    @ApiModelProperty(value = "合同名称")
    @TableField("CONTRACT_NAME")
    private String contractName;

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

    @ApiModelProperty(value = "实际完成日期")
    @TableField("ACT_FINISH_TIME")
    private LocalDateTime actFinishTime;

    @ApiModelProperty(value = "实际完成金额/数量")
    @TableField("ACT_FINISH_NUM")
    private Double actFinishNum;


    public static final String ID = "ID";

    public static final String MONITOR_NAME = "MONITOR_NAME";

    public static final String PLAN_FINISH_TIME = "PLAN_FINISH_TIME";

    public static final String PLAN_FINISH_NUM = "PLAN_FINISH_NUM";

    public static final String IS_USED = "IS_USED";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String CONTRACT_NAME = "CONTRACT_NAME";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    public static final String ACT_FINISH_TIME = "ACT_FINISH_TIME";

    public static final String ACT_FINISH_NUM = "ACT_FINISH_NUM";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
