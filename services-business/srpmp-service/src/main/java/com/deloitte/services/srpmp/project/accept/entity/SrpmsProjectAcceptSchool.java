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
 * 项目验收表-校基科费
 * </p>
 *
 * @author Apeng
 * @since 2019-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_ACCEPT_SCHOOL")
@ApiModel(value="SrpmsProjectAcceptSchool对象", description="项目验收表-校基科费")
public class SrpmsProjectAcceptSchool extends Model<SrpmsProjectAcceptSchool> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "验收ID")
    @TableField("ACCEPT_ID")
    private Long acceptId;

    @ApiModelProperty(value = "项目摘要")
    @TableField("PROJECT_ABSTRACT")
    private String projectAbstract;

    @ApiModelProperty(value = "研究内容及考核指标执行情况描述")
    @TableField("PROJECT_CONTENT_INDICATORS")
    private String projectContentIndicators;

    @ApiModelProperty(value = "研究工作取得的重要进展与成果")
    @TableField("PROJECT_GAIN_RESULT")
    private String projectGainResult;

    @ApiModelProperty(value = "团队建设，人才培养")
    @TableField("PROJECT_TEAM_CONSTRUCTION")
    private String projectTeamConstruction;

    @ApiModelProperty(value = "存在问题及建议")
    @TableField("PROJECT_QUESTION_ADVICE")
    private String projectQuestionAdvice;

    @ApiModelProperty(value = "项目成果目录")
    @TableField("PROJECT_RESULT_DIRECTORY")
    private String projectResultDirectory;

    @ApiModelProperty(value = "资助金额")
    @TableField("APPLY_FUNDS")
    private Double applyFunds;


    public static final String ID = "ID";

    public static final String ACCEPT_ID = "ACCEPT_ID";

    public static final String PROJECT_ABSTRACT = "PROJECT_ABSTRACT";

    public static final String PROJECT_CONTENT_INDICATORS = "PROJECT_CONTENT_INDICATORS";

    public static final String PROJECT_GAIN_RESULT = "PROJECT_GAIN_RESULT";

    public static final String PROJECT_TEAM_CONSTRUCTION = "PROJECT_TEAM_CONSTRUCTION";

    public static final String PROJECT_QUESTION_ADVICE = "PROJECT_QUESTION_ADVICE";

    public static final String PROJECT_RESULT_DIRECTORY = "PROJECT_RESULT_DIRECTORY";

    public static final String APPLY_FUNDS = "APPLY_FUNDS";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
