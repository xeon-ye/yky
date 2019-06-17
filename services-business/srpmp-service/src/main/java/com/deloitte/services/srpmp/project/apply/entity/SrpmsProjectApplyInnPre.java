package com.deloitte.services.srpmp.project.apply.entity;

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
 * 项目申请表-创新工程-先导专项
 * </p>
 *
 * @author pengchao
 * @since 2019-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_APPLY_INN_PRE")
@ApiModel(value="SrpmsProjectApplyInnPre对象", description="项目申请表-创新工程-先导专项")
public class SrpmsProjectApplyInnPre extends Model<SrpmsProjectApplyInnPre> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "预期成果类型CODE")
    @TableField("ACHIEVEMENT_TYPE")
    private String achievementType;

    @ApiModelProperty(value = "拟申请岗位数")
    @TableField("APPLY_POST_NUMBER")
    private Long applyPostNumber;

    @ApiModelProperty(value = "拟申请经费数")
    @TableField("APPLY_FUNDS")
    private Double applyFunds;

    @ApiModelProperty(value = "项目摘要")
    @TableField("PROJECT_ABSTRACT")
    private String projectAbstract;

    @ApiModelProperty(value = "立项必要性")
    @TableField("APPROVAL_NECESSAY")
    private String approvalNecessay;

    @ApiModelProperty(value = "项目成果的呈现形式及描述")
    @TableField("ACHIEVEMENT_FORM")
    private String achievementForm;

    @ApiModelProperty(value = "项目成果的预期经济、社会效益")
    @TableField("ACHIEVEMENT_BENEFIT")
    private String achievementBenefit;

    @ApiModelProperty(value = "主要研究内容")
    @TableField("RESEARCH_CONTENT_MAIN")
    private String researchContentMain;

    @ApiModelProperty(value = "拟采取的研究方法、技术路线及其可行性分析")
    @TableField("RESEARCH_CONTENT_METHOD")
    private String researchContentMethod;

    @ApiModelProperty(value = "项目总体目标、考核指标及测评方式/方法")
    @TableField("PROJECT_TARGET")
    private String projectTarget;

    @ApiModelProperty(value = "基础条件和优势")
    @TableField("SUPERIORITY_DEPT_BASIC")
    private String superiorityDeptBasic;

    @TableField("FUND_SOURCE_AMOUNT")
    private Double fundSourceAmount;

    @TableField("FUND_SOURCE_PROJECT")
    private Double fundSourceProject;

    @TableField("FUND_SOURCE_SELF")
    private Double fundSourceSelf;

    @TableField("FUND_SOURCE_OTHER")
    private Double fundSourceOther;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    public static final String ID = "ID";

    public static final String ACHIEVEMENT_TYPE = "ACHIEVEMENT_TYPE";

    public static final String APPLY_POST_NUMBER = "APPLY_POST_NUMBER";

    public static final String APPLY_FUNDS = "APPLY_FUNDS";

    public static final String PROJECT_ABSTRACT = "PROJECT_ABSTRACT";

    public static final String APPROVAL_NECESSAY = "APPROVAL_NECESSAY";

    public static final String ACHIEVEMENT_FORM = "ACHIEVEMENT_FORM";

    public static final String ACHIEVEMENT_BENEFIT = "ACHIEVEMENT_BENEFIT";

    public static final String RESEARCH_CONTENT_MAIN = "RESEARCH_CONTENT_MAIN";

    public static final String RESEARCH_CONTENT_METHOD = "RESEARCH_CONTENT_METHOD";

    public static final String PROJECT_TARGET = "PROJECT_TARGET";

    public static final String SUPERIORITY_DEPT_BASIC = "SUPERIORITY_DEPT_BASIC";

    public static final String FUND_SOURCE_AMOUNT = "FUND_SOURCE_AMOUNT";

    public static final String FUND_SOURCE_PROJECT = "FUND_SOURCE_PROJECT";

    public static final String FUND_SOURCE_SELF = "FUND_SOURCE_SELF";

    public static final String FUND_SOURCE_OTHER = "FUND_SOURCE_OTHER";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
