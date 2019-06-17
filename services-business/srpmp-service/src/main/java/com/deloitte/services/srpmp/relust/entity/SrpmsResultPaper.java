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
 * 成果管理-论文
 * </p>
 *
 * @author Apeng
 * @since 2019-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_RESULT_PAPER")
@ApiModel(value="SrpmsResultPaper对象", description="成果管理-论文")
public class SrpmsResultPaper extends Model<SrpmsResultPaper> {

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

    @ApiModelProperty(value = "通讯作者")
    @TableField("CORRESPONDENCE_AUTHOR")
    private String correspondenceAuthor;

    @ApiModelProperty(value = "第一作者")
    @TableField("FIRST_AUTHOR")
    private String firstAuthor;

    @ApiModelProperty(value = "其他作者")
    @TableField("OTHER_AUTHOR")
    private String otherAuthor;

    @ApiModelProperty(value = "类别")
    @TableField("PAPER_CAT")
    private String paperCat;

    @ApiModelProperty(value = "论文题目")
    @TableField("PAPER_TITLE")
    private String paperTitle;

    @ApiModelProperty(value = "期刊名称")
    @TableField("JOURNAL_TITLE")
    private String journalTitle;

    @ApiModelProperty(value = "发表单位")
    @TableField("PUBLICATION_ORG")
    private String publicationOrg;

    @ApiModelProperty(value = "卷")
    @TableField("PAPER_VOLUME")
    private String paperVolume;

    @ApiModelProperty(value = "期")
    @TableField("STAGE")
    private String stage;

    @ApiModelProperty(value = "页码")
    @TableField("PAGE")
    private String page;

    @ApiModelProperty(value = "影响因子")
    @TableField("INFLUENCE_FACTOR")
    private Double influenceFactor;

    @ApiModelProperty(value = "期刊性质 10-全国 20-省内")
    @TableField("PROPERTY")
    private String property;

    @ApiModelProperty(value = "期刊区域 10-国内 20-国外")
    @TableField("REGION")
    private String region;

    @ApiModelProperty(value = "等级")
    @TableField("PAPER_LEVEL")
    private String paperLevel;

    @ApiModelProperty(value = "项目负责人")
    @TableField("HPRO_IN_CHARGE")
    private String hproInCharge;

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

    public static final String CORRESPONDENCE_AUTHOR = "CORRESPONDENCE_AUTHOR";

    public static final String FIRST_AUTHOR = "FIRST_AUTHOR";

    public static final String OTHER_AUTHOR = "OTHER_AUTHOR";

    public static final String PAPER_CAT = "PAPER_CAT";

    public static final String PAPER_TITLE = "PAPER_TITLE";

    public static final String JOURNAL_TITLE = "JOURNAL_TITLE";

    public static final String PUBLICATION_ORG = "PUBLICATION_ORG";

    public static final String PAPER_VOLUME = "PAPER_VOLUME";

    public static final String STAGE = "STAGE";

    public static final String PAGE = "PAGE";

    public static final String INFLUENCE_FACTOR = "INFLUENCE_FACTOR";

    public static final String PROPERTY = "PROPERTY";

    public static final String REGION = "REGION";

    public static final String PAPER_LEVEL = "PAPER_LEVEL";

    public static final String HPRO_IN_CHARGE = "HPRO_IN_CHARGE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
