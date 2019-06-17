package com.deloitte.services.fssc.business.pe.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 绩效自评-指标
 * </p>
 *
 * @author qiliao
 * @since 2019-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PER_SELF_TARGET")
@ApiModel(value="PerSelfTarget对象", description="绩效自评-指标")
public class PerSelfTarget extends Model<PerSelfTarget> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "一级指标")
    @TableField("FIRST_PER")
    private String firstPer;

    @ApiModelProperty(value = "二级指标")
    @TableField("SECOND_PER")
    private String secondPer;

    @ApiModelProperty(value = "三级指标")
    @TableField("THIRED_PER")
    private String thiredPer;

    @ApiModelProperty(value = "三级指标单位ID")
    @TableField("THIRED_UNIT_ID")
    private Long thiredUnitId;

    @ApiModelProperty(value = "三级指标单位code")
    @TableField("THIRED_UNIT_CODE")
    private String thiredUnitCode;

    @ApiModelProperty(value = "三级指标单位名称")
    @TableField("THIRED_UNIT_NAME")
    private String thiredUnitName;

    @ApiModelProperty(value = "年度指标值")
    @TableField("YEAR_PER_VALUE")
    private String yearPerValue;

    @ApiModelProperty(value = "实际完成值")
    @TableField("REAL_COMPLETE_VALUE")
    private String realCompleteValue;

    @ApiModelProperty(value = "分值")
    @TableField("SCORE_VALUE")
    private BigDecimal scoreValue;

    @ApiModelProperty(value = "得分")
    @TableField("SCORED")
    private BigDecimal scored;

    @ApiModelProperty(value = "未完成原因")
    @TableField("REASON")
    private String reason;

    @ApiModelProperty(value = "单据id")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

    @ApiModelProperty(value = "预留字段1")
    @TableField("EX1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EX2")
    private String ex2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EX3")
    private String ex3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EX4")
    private String ex4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EX5")
    private String ex5;


    public static final String ID = "ID";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String FIRST_PER = "FIRST_PER";

    public static final String SECOND_PER = "SECOND_PER";

    public static final String THIRED_PER = "THIRED_PER";

    public static final String THIRED_UNIT_ID = "THIRED_UNIT_ID";

    public static final String THIRED_UNIT_CODE = "THIRED_UNIT_CODE";

    public static final String THIRED_UNIT_NAME = "THIRED_UNIT_NAME";

    public static final String YEAR_PER_VALUE = "YEAR_PER_VALUE";

    public static final String REAL_COMPLETE_VALUE = "REAL_COMPLETE_VALUE";

    public static final String SCORE_VALUE = "SCORE_VALUE";

    public static final String SCORED = "SCORED";

    public static final String REASON = "REASON";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String VERSION = "VERSION";

    public static final String EX1 = "EX1";

    public static final String EX2 = "EX2";

    public static final String EX3 = "EX3";

    public static final String EX4 = "EX4";

    public static final String EX5 = "EX5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
