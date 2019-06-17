package com.deloitte.services.srpmp.project.mpr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 项目内人员牵头国家重大科研任务情况表
 * </p>
 *
 * @author LIJUN
 * @since 2019-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_RESEARCH_TASK_TABLE")
@ApiModel(value="MprEvaResearchTaskTable对象", description="项目内人员牵头国家重大科研任务情况表")
public class MprEvaResearchTaskTable extends Model<MprEvaResearchTaskTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "项目编号_科研")
    @TableField("PROJECT_NO_RESEARCH")
    private String projectNoResearch;

    @ApiModelProperty(value = "项目负责人")
    @TableField("PROJECT_MANAGER")
    private String projectManager;

    @ApiModelProperty(value = "项目来源")
    @TableField("PROJECT_SOURCE")
    private String projectSource;

    @ApiModelProperty(value = "实际获得经费（万元）")
    @TableField("ACTUAL_FUND")
    private String actualFund;

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

    @ApiModelProperty(value = "类型（承担/参与）")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "开始时间")
    @TableField("BEGIN_TIME")
    private String beginTime;

    @ApiModelProperty(value = "结束时间")
    @TableField("END_TIME")
    private String endTime;


    public static final String ID = "ID";

    public static final String PROJECT_NO = "PROJECT_NO";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PROJECT_NO_RESEARCH = "PROJECT_NO_RESEARCH";

    public static final String PROJECT_MANAGER = "PROJECT_MANAGER";

    public static final String PROJECT_SOURCE = "PROJECT_SOURCE";

    public static final String ACTUAL_FUND = "ACTUAL_FUND";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String TYPE = "TYPE";

    public static final String BEGIN_TIME = "BEGIN_TIME";

    public static final String END_TIME = "END_TIME";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
