package com.deloitte.services.srpmp.project.mpr.entity;

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
 * 中期绩效考评文件表
 * </p>
 *
 * @author LIJUN
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_FILE_INFO")
@ApiModel(value="MprEvaFileInfo对象", description="中期绩效考评文件表")
public class MprEvaFileInfo extends Model<MprEvaFileInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "附件二")
    @TableField("ANNEX_TWO")
    private String annexTwo;

    @ApiModelProperty(value = "附件三")
    @TableField("ANNEX_THREE")
    private String annexThree;

    @ApiModelProperty(value = "附件四")
    @TableField("ANNEX_FOUR")
    private String annexFour;

    @ApiModelProperty(value = "附件五")
    @TableField("ANNEX_FIVE")
    private String annexFive;

    @ApiModelProperty(value = "附件七")
    @TableField("ANNEX_SEVEN")
    private String annexSeven;

    @ApiModelProperty(value = "附件八")
    @TableField("ANNEX_EIGHT")
    private String annexEight;

    @ApiModelProperty(value = "附件十")
    @TableField("ANNEX_TEN")
    private String annexTen;

    @ApiModelProperty(value = "附件十一")
    @TableField("ANNEX_ELEVEN")
    private String annexEleven;

    @ApiModelProperty(value = "附件十二")
    @TableField("ANNEX_TWELVE")
    private String annexTwelve;

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

    @ApiModelProperty(value = "附件一")
    @TableField("ANNEX_ONE")
    private String annexOne;

    @ApiModelProperty(value = "附件六")
    @TableField("ANNEX_SIX")
    private String annexSix;

    @ApiModelProperty(value = "其他附件")
    @TableField("ANNEX_OTHER")
    private String annexOther;


    public static final String ID = "ID";

    public static final String PROJECT_NO = "PROJECT_NO";

    public static final String ANNEX_TWO = "ANNEX_TWO";

    public static final String ANNEX_THREE = "ANNEX_THREE";

    public static final String ANNEX_FOUR = "ANNEX_FOUR";

    public static final String ANNEX_FIVE = "ANNEX_FIVE";

    public static final String ANNEX_SEVEN = "ANNEX_SEVEN";

    public static final String ANNEX_EIGHT = "ANNEX_EIGHT";

    public static final String ANNEX_TEN = "ANNEX_TEN";

    public static final String ANNEX_ELEVEN = "ANNEX_ELEVEN";

    public static final String ANNEX_TWELVE = "ANNEX_TWELVE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String ANNEX_ONE = "ANNEX_ONE";

    public static final String ANNEX_SIX = "ANNEX_SIX";

    public static final String ANNEX_OTHER = "ANNEX_OTHER";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
