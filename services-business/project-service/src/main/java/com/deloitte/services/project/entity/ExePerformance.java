package com.deloitte.services.project.entity;

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
 * 项目执行绩效目标
 * </p>
 *
 * @author zhengchun
 * @since 2019-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_EXE_PERFORMANCE")
@ApiModel(value="ExePerformance对象", description="项目执行绩效目标")
public class ExePerformance extends Model<ExePerformance> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "批复id")
    @TableField("REPLY_ID")
    private String replyId;

    @ApiModelProperty(value = "项目执行id")
    @TableField("EXECUTION_ID")
    private String executionId;

    @ApiModelProperty(value = "一级指标")
    @TableField("INDICATORS1")
    private String indicators1;

    @ApiModelProperty(value = "二级指标")
    @TableField("INDICATORS2")
    private String indicators2;

    @ApiModelProperty(value = "三级指标")
    @TableField("INDICATORS3")
    private String indicators3;

    @ApiModelProperty(value = "年度指标值")
    @TableField("INDICATORS_YEAR")
    private String indicatorsYear;

    @ApiModelProperty(value = "1至7月执行情况")
    @TableField("EXE_CONDITION_1AND7")
    private String exeCondition1and7;

    @ApiModelProperty(value = "全年执行情况")
    @TableField("EXE_CONDITION_YEAR")
    private String exeConditionYear;

    @ApiModelProperty(value = "经费保障")
    @TableField("FUNDING_SEC")
    private String fundingSec;

    @ApiModelProperty(value = "制度保障")
    @TableField("SYSTEM_SEC")
    private String systemSec;

    @ApiModelProperty(value = "人员保障")
    @TableField("PERSON_SEC")
    private String personSec;

    @ApiModelProperty(value = "硬件保障")
    @TableField("HARDWARE_SEC")
    private String hardwareSec;

    @ApiModelProperty(value = "其他保障")
    @TableField("OTHER_SEC")
    private String otherSec;

    @ApiModelProperty(value = "原因说明")
    @TableField("REASON_INSTRUCTION")
    private String reasonInstruction;

    @ApiModelProperty(value = "完成可能性code")
    @TableField("TARGET_PLAN_CODE")
    private Long targetPlanCode;

    @ApiModelProperty(value = "完成可能行name")
    @TableField("TARGET_PLAN_NAME")
    private String targetPlanName;

    @ApiModelProperty(value = "备注")
    @TableField("NOTE")
    private String note;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展4")
    @TableField("ET4")
    private String et4;

    @ApiModelProperty(value = "orgid")
    @TableField("ORG_ID")
    private Long orgId;


    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String REPLY_ID = "REPLY_ID";

    public static final String EXECUTION_ID = "EXECUTION_ID";

    public static final String INDICATORS1 = "INDICATORS1";

    public static final String INDICATORS2 = "INDICATORS2";

    public static final String INDICATORS3 = "INDICATORS3";

    public static final String INDICATORS_YEAR = "INDICATORS_YEAR";

    public static final String EXE_CONDITION_1AND7 = "EXE_CONDITION_1AND7";

    public static final String EXE_CONDITION_YEAR = "EXE_CONDITION_YEAR";

    public static final String FUNDING_SEC = "FUNDING_SEC";

    public static final String SYSTEM_SEC = "SYSTEM_SEC";

    public static final String PERSON_SEC = "PERSON_SEC";

    public static final String HARDWARE_SEC = "HARDWARE_SEC";

    public static final String OTHER_SEC = "OTHER_SEC";

    public static final String REASON_INSTRUCTION = "REASON_INSTRUCTION";

    public static final String TARGET_PLAN_CODE = "TARGET_PLAN_CODE";

    public static final String TARGET_PLAN_NAME = "TARGET_PLAN_NAME";

    public static final String NOTE = "NOTE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String ET4 = "ET4";

    public static final String ORG_ID = "ORG_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
