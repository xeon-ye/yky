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
 * 绩效自评表
 * </p>
 *
 * @author qiliao
 * @since 2019-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PER_SELF_ASSESSMENT")
@ApiModel(value="PerSelfAssessment对象", description="绩效自评表")
public class PerSelfAssessment extends Model<PerSelfAssessment> {

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

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    @TableField("DOCUMENT_STATUS")
    private String documentStatus;

    @ApiModelProperty(value = "项目负责人")
    @TableField("PROJECT_DUTY")
    private String projectDuty;

    @ApiModelProperty(value = "项目负责人ID")
    @TableField("PROJECT_DUTY_ID")
    private Long projectDutyId;

    @ApiModelProperty(value = "项目名称")
    @TableField(value = "PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "主管部门ID")
    @TableField("DO_DEPT_ID")
    private Long doDeptId;

    @ApiModelProperty(value = "主管部门code")
    @TableField("DO_DEPT_CODE")
    private String doDeptCode;

    @ApiModelProperty(value = "主管部门名称")
    @TableField("DO_DEPT_NAME")
    private String doDeptName;

    @ApiModelProperty(value = "实施单位ID")
    @TableField("DO_UNIT_ID")
    private Long doUnitId;

    @ApiModelProperty(value = "实施单位CODE")
    @TableField("DO_UNIT_CODE")
    private String doUnitCode;

    @ApiModelProperty(value = "实施单位名称")
    @TableField("DO_UNIT_NAME")
    private String doUnitName;

    @ApiModelProperty(value = "年初资金总额")
    @TableField("FUND_SOURCE_AMOUNT")
    private BigDecimal fundSourceAmount;

    @ApiModelProperty(value = "年初财政拨款")
    @TableField("FUND_SOURCE_PROJECT")
    private BigDecimal fundSourceProject;

    @ApiModelProperty(value = "年初其他经费")
    @TableField("FUND_SOURCE_OTHER")
    private BigDecimal fundSourceOther;

    @ApiModelProperty(value = "全年资金总额")
    @TableField("FUND_SOURCE_AMOUNT_YEAR")
    private BigDecimal fundSourceAmountYear;

    @ApiModelProperty(value = "全年财政拨款")
    @TableField("FUND_SOURCE_PROJECT_YEAR")
    private BigDecimal fundSourceProjectYear;

    @ApiModelProperty(value = "全年其他经费")
    @TableField("FUND_SOURCE_OTHER_YEAR")
    private BigDecimal fundSourceOtherYear;

    @ApiModelProperty(value = "全年执行总额")
    @TableField("EXECUTE_TOTAL")
    private BigDecimal executeTotal;

    @ApiModelProperty(value = "全年执行财政拨款")
    @TableField("EXECUTE_PROJECT")
    private BigDecimal executeProject;

    @ApiModelProperty(value = "全年执行其他资金")
    @TableField("EXECUTE_OTHER")
    private BigDecimal executeOther;

    @ApiModelProperty(value = "总额分值")
    @TableField("TOTAL_SCORE_VALUE")
    private BigDecimal totalScoreValue;

    @ApiModelProperty(value = "财政拨款分值")
    @TableField("PROJECT_SCORE_VALUE")
    private BigDecimal projectScoreValue;

    @ApiModelProperty(value = "其他资金分值")
    @TableField("OTHER_SCORE_VALUE")
    private BigDecimal otherScoreValue;

    @ApiModelProperty(value = "总额执行率")
    @TableField("EXECUTE_PERCENT_TOTAL")
    private BigDecimal executePercentTotal;

    @ApiModelProperty(value = "财政拨款执行率")
    @TableField("EXECUTE_PERCENT_PROJECT")
    private BigDecimal executePercentProject;

    @ApiModelProperty(value = "其他资金执行率")
    @TableField("EXECUTE_PERCENT_OTHER")
    private BigDecimal executePercentOther;

    @ApiModelProperty(value = "总额得分")
    @TableField("TOTAL_SCORED")
    private BigDecimal totalScored;

    @ApiModelProperty(value = "财政拨款得分")
    @TableField("PRJECT_SCORED")
    private BigDecimal prjectScored;

    @ApiModelProperty(value = "其他资金得分")
    @TableField("OTHER_SCORED")
    private BigDecimal otherScored;

    @ApiModelProperty(value = "预期目标")
    @TableField("PROJECT_TARGET")
    private String projectTarget;

    @ApiModelProperty(value = "实际完成情况")
    @TableField("REAL_COMPLETE_STATUS")
    private String realCompleteStatus;

    @ApiModelProperty(value = "说明")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "项目立项时间")
    @TableField("PROJECT_START_TIME")
    private LocalDateTime projectStartTime;

    @ApiModelProperty(value = "项目id")
    @TableField(value = "PROJECT_ID")
    private Long projectId;

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

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String PROJECT_ID="PROJECT_ID";

    public static final String DOCUMENT_STATUS = "DOCUMENT_STATUS";

    public static final String PROJECT_DUTY = "PROJECT_DUTY";

    public static final String PROJECT_DUTY_ID = "PROJECT_DUTY_ID";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String DO_DEPT_ID = "DO_DEPT_ID";

    public static final String DO_DEPT_CODE = "DO_DEPT_CODE";

    public static final String DO_DEPT_NAME = "DO_DEPT_NAME";

    public static final String DO_UNIT_ID = "DO_UNIT_ID";

    public static final String DO_UNIT_CODE = "DO_UNIT_CODE";

    public static final String DO_UNIT_NAME = "DO_UNIT_NAME";

    public static final String FUND_SOURCE_AMOUNT = "FUND_SOURCE_AMOUNT";

    public static final String FUND_SOURCE_PROJECT = "FUND_SOURCE_PROJECT";

    public static final String FUND_SOURCE_OTHER = "FUND_SOURCE_OTHER";

    public static final String FUND_SOURCE_AMOUNT_YEAR = "FUND_SOURCE_AMOUNT_YEAR";

    public static final String FUND_SOURCE_PROJECT_YEAR = "FUND_SOURCE_PROJECT_YEAR";

    public static final String FUND_SOURCE_OTHER_YEAR = "FUND_SOURCE_OTHER_YEAR";

    public static final String EXECUTE_TOTAL = "EXECUTE_TOTAL";

    public static final String EXECUTE_PROJECT = "EXECUTE_PROJECT";

    public static final String EXECUTE_OTHER = "EXECUTE_OTHER";

    public static final String TOTAL_SCORE_VALUE = "TOTAL_SCORE_VALUE";

    public static final String PROJECT_SCORE_VALUE = "PROJECT_SCORE_VALUE";

    public static final String OTHER_SCORE_VALUE = "OTHER_SCORE_VALUE";

    public static final String EXECUTE_PERCENT_TOTAL = "EXECUTE_PERCENT_TOTAL";

    public static final String EXECUTE_PERCENT_PROJECT = "EXECUTE_PERCENT_PROJECT";

    public static final String EXECUTE_PERCENT_OTHER = "EXECUTE_PERCENT_OTHER";

    public static final String TOTAL_SCORED = "TOTAL_SCORED";

    public static final String PRJECT_SCORED = "PRJECT_SCORED";

    public static final String OTHER_SCORED = "OTHER_SCORED";

    public static final String PROJECT_TARGET = "PROJECT_TARGET";

    public static final String REAL_COMPLETE_STATUS = "REAL_COMPLETE_STATUS";

    public static final String REMARK = "REMARK";

    public static final String PROJECT_START_TIME="PROJECT_START_TIME";

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
