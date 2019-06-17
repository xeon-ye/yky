package com.deloitte.services.srpmp.project.mpr.entity;

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
 * 论文发表情况表
 * </p>
 *
 * @author LIJUN
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_TREA_TABLE")
@ApiModel(value="MprEvaTreaTable对象", description="论文发表情况表")
public class MprEvaTreaTable extends Model<MprEvaTreaTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "论文题目")
    @TableField("TREA_TOPIC")
    private String treaTopic;

    @ApiModelProperty(value = "期刊名称")
    @TableField("JOURNAL_NAME")
    private String journalName;

    @ApiModelProperty(value = "姓名")
    @TableField("AUTHOR")
    private String author;

    @ApiModelProperty(value = "作者类别")
    @TableField("AUTHOR_TYPE")
    private String authorType;

    @ApiModelProperty(value = "年")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "卷（期）")
    @TableField("VOLUME")
    private String volume;

    @ApiModelProperty(value = "页")
    @TableField("PAGE")
    private String page;

    @ApiModelProperty(value = "收录来源")
    @TableField("SOURCE_INCLUSION")
    private String sourceInclusion;

    @ApiModelProperty(value = "SCI分区")
    @TableField("SCI_PARTITION")
    private String sciPartition;

    @ApiModelProperty(value = "发表时间")
    @TableField("ISSU_TIME")
    private String issuTime;

    @ApiModelProperty(value = "期刊影响因子")
    @TableField("IMPACT_FACTOR")
    private String impactFactor;

    @ApiModelProperty(value = "引用频次")
    @TableField("REFE_FREQ")
    private String refeFreq;

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

    public static final String TREA_TOPIC = "TREA_TOPIC";

    public static final String JOURNAL_NAME = "JOURNAL_NAME";

    public static final String AUTHOR = "AUTHOR";

    public static final String AUTHOR_TYPE = "AUTHOR_TYPE";

    public static final String YEAR = "YEAR";

    public static final String VOLUME = "VOLUME";

    public static final String PAGE = "PAGE";

    public static final String SOURCE_INCLUSION = "SOURCE_INCLUSION";

    public static final String SCI_PARTITION = "SCI_PARTITION";

    public static final String ISSU_TIME = "ISSU_TIME";

    public static final String IMPACT_FACTOR = "IMPACT_FACTOR";

    public static final String REFE_FREQ = "REFE_FREQ";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
