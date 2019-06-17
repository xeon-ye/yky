package com.deloitte.services.fssc.performance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 绩效指标
 * </p>
 *
 * @author jaws
 * @since 2019-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PERFORMANCE_INDEX")
@ApiModel(value="PerformanceIndex对象", description="绩效指标")
public class PerformanceIndex extends Model<PerformanceIndex> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "编码")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "启用标志")
    @TableField("VALID_FLAG")
    private String validFlag;

    @ApiModelProperty(value = "关联指标库")
    @TableField("INDEX_LIBRARY_ID")
    private Long indexLibraryId;

    @ApiModelProperty(value = "指标判断条件")
    @TableField("VALUE_JUDGE_CONDITION")
    private String valueJudgeCondition;

    @ApiModelProperty(value = "指标单位ID")
    @TableField("VALUE_UNIT_ID")
    private Long valueUnitId;

    @ApiModelProperty(value = "指标级别")
    @TableField("VALUE_LEVEL")
    private String valueLevel;

    @ApiModelProperty(value = "对应一级指标")
    @TableField("LEVEL_1_ID")
    private Long level1Id;

    @ApiModelProperty(value = "对应二级指标")
    @TableField("LEVEL_2_ID")
    private Long level2Id;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;


    public static final String ID = "ID";

    public static final String CODE = "CODE";

    public static final String NAME = "NAME";

    public static final String VALID_FLAG = "VALID_FLAG";

    public static final String INDEX_LIBRARY_ID = "INDEX_LIBRARY_ID";

    public static final String VALUE_JUDGE_CONDITION = "VALUE_JUDGE_CONDITION";

    public static final String VALUE_UNIT_ID = "VALUE_UNIT_ID";

    public static final String VALUE_LEVEL = "VALUE_LEVEL";

    public static final String LEVEL_1_ID = "LEVEL_1_ID";

    public static final String LEVEL_2_ID = "LEVEL_2_ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
