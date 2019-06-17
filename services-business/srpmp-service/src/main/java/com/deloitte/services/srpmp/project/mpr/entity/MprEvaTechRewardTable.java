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
 * 科技奖励信息详细表
 * </p>
 *
 * @author LIJUN
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_TECH_REWARD_TABLE")
@ApiModel(value="MprEvaTechRewardTable对象", description="科技奖励信息详细表")
public class MprEvaTechRewardTable extends Model<MprEvaTechRewardTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "成果名称")
    @TableField("ACHIEVE_NAME")
    private String achieveName;

    @ApiModelProperty(value = "获奖人")
    @TableField("OBTAIN_PERSON")
    private String obtainPerson;

    @ApiModelProperty(value = "获奖类别")
    @TableField("AWARD_CATEGORY")
    private String awardCategory;

    @ApiModelProperty(value = "获奖等级")
    @TableField("AWARD_LEVEL")
    private String awardLevel;

    @ApiModelProperty(value = "获奖年度")
    @TableField("AWARD_YEAR")
    private String awardYear;

    @ApiModelProperty(value = "奖项名称")
    @TableField("AWARD_NAME")
    private String awardName;

    @ApiModelProperty(value = "证书编号")
    @TableField("CERT_NUM")
    private String certNum;

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


    public static final String ID = "ID";

    public static final String PROJECT_NO = "PROJECT_NO";

    public static final String ACHIEVE_NAME = "ACHIEVE_NAME";

    public static final String OBTAIN_PERSON = "OBTAIN_PERSON";

    public static final String AWARD_CATEGORY = "AWARD_CATEGORY";

    public static final String AWARD_LEVEL = "AWARD_LEVEL";

    public static final String AWARD_YEAR = "AWARD_YEAR";

    public static final String AWARD_NAME = "AWARD_NAME";

    public static final String CERT_NUM = "CERT_NUM";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
