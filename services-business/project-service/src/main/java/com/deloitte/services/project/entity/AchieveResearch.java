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
 * 科技成果
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_ACHIEVE_RESEARCH")
@ApiModel(value="AchieveResearch对象", description="科技成果")
public class AchieveResearch extends Model<AchieveResearch> {

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

    @ApiModelProperty(value = "科技经费ID")
    @TableField("RESEARCHFUNDS_ID")
    private String researchfundsId;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "奖项专利论文Code")
    @TableField("AWARD_PAPER_CODE")
    private String awardPaperCode;

    @ApiModelProperty(value = "奖项专利论文名称")
    @TableField("AWARD_PAPER_NAME")
    private String awardPaperName;

    @ApiModelProperty(value = "前五年期间")
    @TableField("DURING_FIVE_YEAR")
    private Long duringFiveYear;

    @ApiModelProperty(value = "上一年")
    @TableField("LAST_YEAR")
    private Long lastYear;


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

    public static final String RESEARCHFUNDS_ID = "RESEARCHFUNDS_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String AWARD_PAPER_CODE = "AWARD_PAPER_CODE";

    public static final String AWARD_PAPER_NAME = "AWARD_PAPER_NAME";

    public static final String DURING_FIVE_YEAR = "DURING_FIVE_YEAR";

    public static final String LAST_YEAR = "LAST_YEAR";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
