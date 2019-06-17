package com.deloitte.services.srpmp.relust.entity;

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
 * 成果管理-奖励
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_RESULT_AWARD")
@ApiModel(value="SrpmsResultAward对象", description="成果管理-奖励")
public class SrpmsResultAward extends Model<SrpmsResultAward> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位CODE")
    @TableField("DEPT_CODE")
    private Long deptCode;

    @ApiModelProperty(value = "年")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "月")
    @TableField("MONTH")
    private String month;

    @ApiModelProperty(value = "项目编码")
    @TableField("PRO_NUM")
    private String proNum;

    @ApiModelProperty(value = "项目名称")
    @TableField("PRO_NAME")
    private String proName;

    @ApiModelProperty(value = "获奖成果")
    @TableField("AWARD_RESULTS")
    private String awardResults;

    @ApiModelProperty(value = "奖项类别")
    @TableField("AWARD_CAT")
    private String awardCat;

    @ApiModelProperty(value = "完成单位")
    @TableField("COMPLETION_ORG")
    private String completionOrg;

    @ApiModelProperty(value = "主要完成人")
    @TableField("COMPLETION_PERSON")
    private String completionPerson;

    @ApiModelProperty(value = "奖项名称")
    @TableField("AWARD_NAME")
    private String awardName;

    @ApiModelProperty(value = "奖项等级")
    @TableField("AWARD_LEVEL")
    private String awardLevel;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;


    public static final String ID = "ID";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String YEAR = "YEAR";

    public static final String MONTH = "MONTH";

    public static final String PRO_NUM = "PRO_NUM";

    public static final String PRO_NAME = "PRO_NAME";

    public static final String AWARD_RESULTS = "AWARD_RESULTS";

    public static final String AWARD_CAT = "AWARD_CAT";

    public static final String COMPLETION_ORG = "COMPLETION_ORG";

    public static final String COMPLETION_PERSON = "COMPLETION_PERSON";

    public static final String AWARD_NAME = "AWARD_NAME";

    public static final String AWARD_LEVEL = "AWARD_LEVEL";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
