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
@TableName("PROJECT_RESEARCHFUNDS")
@ApiModel(value="Researchfunds对象", description="科技经费")
public class Researchfunds extends Model<Researchfunds> {

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

    @ApiModelProperty(value = "时期Code")
    @TableField("PERIOD_CODE")
    private String periodCode;

    @ApiModelProperty(value = "时期名称")
    @TableField("PERIOD_NAME")
    private String periodName;

    @ApiModelProperty(value = "前五年期间项目数量（项")
    @TableField("NUM_PRO_FIVE_YEAR")
    private Long numProFiveYear;

    @ApiModelProperty(value = "前五年期间经费（万元")
    @TableField("FUND_FIVE_YEAR")
    private Long fundFiveYear;

    @ApiModelProperty(value = "上一年经费数（项")
    @TableField("FUNDS_LAST_YEAR")
    private Long fundsLastYear;

    @ApiModelProperty(value = "上一年经费(万元)")
    @TableField("EXPENDITURE")
    private Long expenditure;


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

    public static final String PERIOD_CODE = "PERIOD_CODE";

    public static final String PERIOD_NAME = "PERIOD_NAME";

    public static final String NUM_PRO_FIVE_YEAR = "NUM_PRO_FIVE_YEAR";

    public static final String FUND_FIVE_YEAR = "FUND_FIVE_YEAR";

    public static final String FUNDS_LAST_YEAR = "FUNDS_LAST_YEAR";

    public static final String EXPENDITURE = "EXPENDITURE";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
