package com.deloitte.services.project.entity;

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
 * 科技经费
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_INSANDEQUIP")
@ApiModel(value="Insandequip对象", description="科技经费")
public class Insandequip extends Model<Insandequip> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

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

    @ApiModelProperty(value = "ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "仪器设备ID")
    @TableField("INSANDEQUIP_ID")
    private String insandequipId;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "分类统计Code")
    @TableField("CLASSIFIED_STATISTICS_CODE")
    private String classifiedStatisticsCode;

    @ApiModelProperty(value = "分类统计名称")
    @TableField("CLASSIFIED_STATISTICS_NAME")
    private String classifiedStatisticsName;

    @ApiModelProperty(value = "总数量")
    @TableField("TOTAL_NUMBER")
    private Long totalNumber;

    @ApiModelProperty(value = "在用数量")
    @TableField("USE_NUMBER")
    private Long useNumber;

    @ApiModelProperty(value = "总量原值")
    @TableField("ORIGINAL_VALUE")
    private Long originalValue;

    @ApiModelProperty(value = "在用原值")
    @TableField("USE_ORIGINAL_VALUE")
    private Long useOriginalValue;


    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String ID = "ID";

    public static final String INSANDEQUIP_ID = "INSANDEQUIP_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String CLASSIFIED_STATISTICS_CODE = "CLASSIFIED_STATISTICS_CODE";

    public static final String CLASSIFIED_STATISTICS_NAME = "CLASSIFIED_STATISTICS_NAME";

    public static final String TOTAL_NUMBER = "TOTAL_NUMBER";

    public static final String USE_NUMBER = "USE_NUMBER";

    public static final String ORIGINAL_VALUE = "ORIGINAL_VALUE";

    public static final String USE_ORIGINAL_VALUE = "USE_ORIGINAL_VALUE";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
