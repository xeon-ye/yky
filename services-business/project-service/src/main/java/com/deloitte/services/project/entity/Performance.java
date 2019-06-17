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
 * 项目绩效
 * </p>
 *
 * @author zhengchun
 * @since 2019-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_PERFORMANCE")
@ApiModel(value="Performance对象", description="项目绩效")
public class Performance extends Model<Performance> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目绩效ID")
    @TableField("PERFORMANCE_ID")
    private String performanceId;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "项目日期")
    @TableField("PROJECT_DATE")
    private LocalDateTime projectDate;

    @ApiModelProperty(value = "年度绩效目标")
    @TableField("ANNUAL_PERFORMANCE_TARGET")
    private String annualPerformanceTarget;

    @ApiModelProperty(value = "指标类别（年度/中期）")
    @TableField("INDEX_TYPE")
    private String indexType;

    @ApiModelProperty(value = "一级指标")
    @TableField("INDEX_1ST")
    private String index1st;

    @ApiModelProperty(value = "二级指标")
    @TableField("INDEX_2ND")
    private String index2nd;

    @ApiModelProperty(value = "三级指标")
    @TableField("INDEX_3ST")
    private String index3st;

    @ApiModelProperty(value = "三级指标CODE")
    @TableField("INDEX_3ST_CODE")
    private String index3stCode;

    @ApiModelProperty(value = "指标值")
    @TableField("INDEX_PER")
    private String indexPer;

    @ApiModelProperty(value = "项目绩效_总体目标_ID")
    @TableField("PERFORMANCE_TARGET_ID")
    private String performanceTargetId;

    @ApiModelProperty(value = "批复ID")
    @TableField("REPLAY_ID")
    private String replayId;

    @ApiModelProperty(value = "创建")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField("CARETE_BY")
    private String careteBy;

    @ApiModelProperty(value = "更新")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "项目大类CODE")
    @TableField("IDX_MAIN_CODE")
    private String idxMainCode;

    @ApiModelProperty(value = "项目大类名称")
    @TableField("IDX_MAIN_NAME")
    private String idxMainName;

    @ApiModelProperty(value = "项目小类CODE")
    @TableField("IDX_SUB_CODE")
    private String idxSubCode;

    @ApiModelProperty(value = "项目小类名称")
    @TableField("IDX_SUB_NAME")
    private String idxSubName;

    @ApiModelProperty(value = "指标库CODE")
    @TableField("IDX_LIB_CODE")
    private String idxLibCode;

    @ApiModelProperty(value = "指标库名称")
    @TableField("IDX_LIB_NAME")
    private String idxLibName;

    @ApiModelProperty(value = "一级指标CODE")
    @TableField("INDEX_1ST_CODE")
    private String index1stCode;

    @ApiModelProperty(value = "二级指标CODE")
    @TableField("INDEX_2ND_CODE")
    private String index2ndCode;

    @ApiModelProperty(value = "三级指标值的值")
    @TableField("PER")
    private String per;

    @ApiModelProperty(value = "三级指标值的值CODE")
    @TableField("PER_CODE")
    private String perCode;


    public static final String ID = "ID";

    public static final String PERFORMANCE_ID = "PERFORMANCE_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String PROJECT_DATE = "PROJECT_DATE";

    public static final String ANNUAL_PERFORMANCE_TARGET = "ANNUAL_PERFORMANCE_TARGET";

    public static final String INDEX_TYPE = "INDEX_TYPE";

    public static final String INDEX_1ST = "INDEX_1ST";

    public static final String INDEX_2ND = "INDEX_2ND";

    public static final String INDEX_3ST = "INDEX_3ST";

    public static final String INDEX_3ST_CODE = "INDEX_3ST_CODE";

    public static final String INDEX_PER = "INDEX_PER";

    public static final String PERFORMANCE_TARGET_ID = "PERFORMANCE_TARGET_ID";

    public static final String REPLAY_ID = "REPLAY_ID";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CARETE_BY = "CARETE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String IDX_MAIN_CODE = "IDX_MAIN_CODE";

    public static final String IDX_MAIN_NAME = "IDX_MAIN_NAME";

    public static final String IDX_SUB_CODE = "IDX_SUB_CODE";

    public static final String IDX_SUB_NAME = "IDX_SUB_NAME";

    public static final String IDX_LIB_CODE = "IDX_LIB_CODE";

    public static final String IDX_LIB_NAME = "IDX_LIB_NAME";

    public static final String INDEX_1ST_CODE = "INDEX_1ST_CODE";

    public static final String INDEX_2ND_CODE = "INDEX_2ND_CODE";

    public static final String PER = "PER";

    public static final String PER_CODE = "PER_CODE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
