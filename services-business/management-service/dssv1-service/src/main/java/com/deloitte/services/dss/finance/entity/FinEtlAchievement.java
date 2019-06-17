package com.deloitte.services.dss.finance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 绩效表
 * </p>
 *
 * @author chitose
 * @since 2019-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DSS_FIN_ETL_ACHIEVEMENT")
@ApiModel(value="DssFinEtlAchievement对象", description="绩效表")
public class FinEtlAchievement extends Model<FinEtlAchievement> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "年度")
    @TableField("PERIOD")
    private String period;

    @ApiModelProperty(value = "项目编码")
    @TableField("PROJECT_CODE")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "单位编码")
    @TableField("ENTITY_CODE")
    private String entityCode;

    @ApiModelProperty(value = "单位名称")
    @TableField("ENTITY_NAME")
    private String entityName;

    @ApiModelProperty(value = "项目类别")
    @TableField("PROJECT_TYPE")
    private String projectType;

    @ApiModelProperty(value = "一级项目")
    @TableField("TITLE_L1")
    private String titleL1;

    @ApiModelProperty(value = "绩效类型")
    @TableField("PFM_TYPE")
    private String pfmType;

    @ApiModelProperty(value = "绩效考核结果（0-100）")
    @TableField("PTS")
    private Integer pts;

    @TableField("EX1")
    private String ex1;

    @TableField("EX2")
    private String ex2;

    @TableField("EX3")
    private String ex3;

    @TableField("EX4")
    private String ex4;

    @TableField("EX5")
    private String ex5;

    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    public static final String ID = "ID";

    public static final String PERIOD = "PERIOD";

    public static final String PROJECT_CODE = "PROJECT_CODE";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String ENTITY_CODE = "ENTITY_CODE";

    public static final String ENTITY_NAME = "ENTITY_NAME";

    public static final String PROJECT_TYPE = "PROJECT_TYPE";

    public static final String TITLE_L1 = "TITLE_L1";

    public static final String PFM_TYPE = "PFM_TYPE";

    public static final String PTS = "PTS";

    public static final String EX1 = "EX1";

    public static final String EX2 = "EX2";

    public static final String EX3 = "EX3";

    public static final String EX4 = "EX4";

    public static final String EX5 = "EX5";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
