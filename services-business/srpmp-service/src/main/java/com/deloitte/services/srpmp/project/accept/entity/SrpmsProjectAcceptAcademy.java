package com.deloitte.services.srpmp.project.accept.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 项目验收表-院基科(科技体制改革)
 * </p>
 *
 * @author Apeng
 * @since 2019-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_ACCEPT_ACADEMY")
@ApiModel(value="SrpmsProjectAcceptAcademy对象", description="项目验收表-院基科(科技体制改革)")
public class SrpmsProjectAcceptAcademy extends Model<SrpmsProjectAcceptAcademy> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "验收ID")
    @TableField("ACCEPT_ID")
    private Long acceptId;

    @ApiModelProperty(value = "项目考核指标")
    @TableField("ASSESSMENT_INDICATORS")
    private String assessmentIndicators;

    @ApiModelProperty(value = "研究内容考核指标完成情况")
    @TableField("PROJECT_COMPLETION_CASE")
    private String projectCompletionCase;

    @ApiModelProperty(value = "分析未完成原因")
    @TableField("PROJECT_UNFINISH_REASON")
    private String projectUnfinishReason;

    @ApiModelProperty(value = "项目实施经验")
    @TableField("PROJECT_IMPLEMENT_EXPERIENCE")
    private String projectImplementExperience;


    public static final String ID = "ID";

    public static final String ACCEPT_ID = "ACCEPT_ID";

    public static final String ASSESSMENT_INDICATORS = "ASSESSMENT_INDICATORS";

    public static final String PROJECT_COMPLETION_CASE = "PROJECT_COMPLETION_CASE";

    public static final String PROJECT_UNFINISH_REASON = "PROJECT_UNFINISH_REASON";

    public static final String PROJECT_IMPLEMENT_EXPERIENCE = "PROJECT_IMPLEMENT_EXPERIENCE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
