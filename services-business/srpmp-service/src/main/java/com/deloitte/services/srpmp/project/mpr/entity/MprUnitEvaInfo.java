package com.deloitte.services.srpmp.project.mpr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.sql.Clob;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 承担单位中期绩效考评自评报告
 * </p>
 *
 * @author LIJUN
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_UNIT_EVA_INFO")
@ApiModel(value="MprUnitEvaInfo对象", description="承担单位中期绩效考评自评报告")
public class MprUnitEvaInfo extends Model<MprUnitEvaInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "承担单位（盖章）")
    @TableField("BEARER_UNIT")
    private String bearerUnit;

    @ApiModelProperty(value = "承担单位负责人（签章）")
    @TableField("BEARER_UNIT_MANAGER")
    private String bearerUnitManager;

    @ApiModelProperty(value = "报告时间")
    @TableField("REPORT_DATE")
    private String reportDate;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "审核状态")
    @TableField("PROCESS_STATUS")
    private String processStatus;

    @ApiModelProperty(value = "组织管理情况")
    @TableField("ORG_MANA_INFO")
    private String orgManaInfo;

    @ApiModelProperty(value = "服务支撑情况")
    @TableField("SERVICE_SUP_INFO")
    private String serviceSupInfo;

    @ApiModelProperty(value = "监督履责情况")
    @TableField("MONITOR_RESP_INFO")
    private String monitorRespInfo;

    @ApiModelProperty(value = "成果管理情况")
    @TableField("RESULT_MANA_INFO")
    private String resultManaInfo;

    @ApiModelProperty(value = "实施成效")
    @TableField("IMPL_EFFECT")
    private String implEffect;

    @ApiModelProperty(value = "实施建议")
    @TableField("IMPL_ADVICE")
    private String implAdvice;

    @ApiModelProperty(value = "单位code")
    @TableField("DEPT_CODE")
    private String deptCode;

    @ApiModelProperty(value = "单位名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "年度")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "文件信息")
    @TableField("FILE_INFO")
    private String fileInfo;

    @ApiModelProperty(value = "审核文件信息")
    @TableField("AUDIT_FILE_INFO")
    private String auditFileInfo;


    public static final String ID = "ID";

    public static final String PROJECT_NO = "PROJECT_NO";

    public static final String BEARER_UNIT = "BEARER_UNIT";

    public static final String BEARER_UNIT_MANAGER = "BEARER_UNIT_MANAGER";

    public static final String REPORT_DATE = "REPORT_DATE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String PROCESS_STATUS = "PROCESS_STATUS";

    public static final String ORG_MANA_INFO = "ORG_MANA_INFO";

    public static final String SERVICE_SUP_INFO = "SERVICE_SUP_INFO";

    public static final String MONITOR_RESP_INFO = "MONITOR_RESP_INFO";

    public static final String RESULT_MANA_INFO = "RESULT_MANA_INFO";

    public static final String IMPL_EFFECT = "IMPL_EFFECT";

    public static final String IMPL_ADVICE = "IMPL_ADVICE";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String YEAR = "YEAR";

    public static final String FILE_INFO = "FILE_INFO";

    public static final String AUDIT_FILE_INFO = "AUDIT_FILE_INFO";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
