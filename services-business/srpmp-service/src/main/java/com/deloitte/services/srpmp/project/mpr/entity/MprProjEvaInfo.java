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
 * 中期绩效考评自评报告表
 * </p>
 *
 * @author LIJUN
 * @since 2019-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_PROJ_EVA_INFO")
@ApiModel(value="MprProjEvaInfo对象", description="中期绩效考评自评报告表")
public class MprProjEvaInfo extends Model<MprProjEvaInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "项目类别")
    @TableField("PROJECT_TYPE")
    private String projectType;

    @ApiModelProperty(value = "项目分类")
    @TableField("PROJECT_CATEGORY")
    private String projectCategory;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "项目周期")
    @TableField("PROJECT_CYCLE")
    private String projectCycle;

    @ApiModelProperty(value = "项目首席专家")
    @TableField("PROJECT_CHIEF_EXPERT")
    private String projectChiefExpert;

    @ApiModelProperty(value = "项目承担单位")
    @TableField("PROJECT_UNDERTAKER")
    private String projectUndertaker;

    @ApiModelProperty(value = "报告时间")
    @TableField("REPORT_DATE")
    private String reportDate;

    @ApiModelProperty(value = "项目总体进展情况")
    @TableField("TASK_PROGRESS")
    private String taskProgress;

    @ApiModelProperty(value = "项目实施过程中进行的重要调整情况")
    @TableField("ADJUST_STATE")
    private String adjustState;

    @ApiModelProperty(value = "取得的重要进展及标志性成果")
    @TableField("LANDMARK_ACHIEVE")
    private String landmarkAchieve;

    @ApiModelProperty(value = "经济社会效益")
    @TableField("ECON_SOCIAL_BENEFITS")
    private String econSocialBenefits;

    @ApiModelProperty(value = "能力提升情况")
    @TableField("CAPA_IMPROVE")
    private String capaImprove;

    @ApiModelProperty(value = "团队建设情况")
    @TableField("TEAM_BUILD")
    private String teamBuild;

    @ApiModelProperty(value = "发展前景分析")
    @TableField("DEVE_PROS_ANA")
    private String deveProsAna;

    @ApiModelProperty(value = "人员及资金投入使用情况")
    @TableField("PERSON_FUND_USE")
    private String personFundUse;

    @ApiModelProperty(value = "资金调整情况")
    @TableField("FUND_ADJUST")
    private String fundAdjust;

    @ApiModelProperty(value = "组织实施管理情况及重大问题和对策建议")
    @TableField("PROBLEM_SUGGEST")
    private String problemSuggest;

    @ApiModelProperty(value = "根据项目自评专家组意见阐述有关项目内容和经费的调整建议")
    @TableField("PROJ_CONT")
    private String projCont;

    @ApiModelProperty(value = "项目任务书中有特殊约定或其他需要说明的事项")
    @TableField("INSTRUCTIONS")
    private String instructions;

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

    public static final String PROJECT_TYPE = "PROJECT_TYPE";

    public static final String PROJECT_CATEGORY = "PROJECT_CATEGORY";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PROJECT_CYCLE = "PROJECT_CYCLE";

    public static final String PROJECT_CHIEF_EXPERT = "PROJECT_CHIEF_EXPERT";

    public static final String PROJECT_UNDERTAKER = "PROJECT_UNDERTAKER";

    public static final String REPORT_DATE = "REPORT_DATE";

    public static final String TASK_PROGRESS = "TASK_PROGRESS";

    public static final String ADJUST_STATE = "ADJUST_STATE";

    public static final String LANDMARK_ACHIEVE = "LANDMARK_ACHIEVE";

    public static final String ECON_SOCIAL_BENEFITS = "ECON_SOCIAL_BENEFITS";

    public static final String CAPA_IMPROVE = "CAPA_IMPROVE";

    public static final String TEAM_BUILD = "TEAM_BUILD";

    public static final String DEVE_PROS_ANA = "DEVE_PROS_ANA";

    public static final String PERSON_FUND_USE = "PERSON_FUND_USE";

    public static final String FUND_ADJUST = "FUND_ADJUST";

    public static final String PROBLEM_SUGGEST = "PROBLEM_SUGGEST";

    public static final String PROJ_CONT = "PROJ_CONT";

    public static final String INSTRUCTIONS = "INSTRUCTIONS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
