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
 * 专著/教材发表情况表
 * </p>
 *
 * @author LIJUN
 * @since 2019-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_MONO_TABLE")
@ApiModel(value="MprEvaMonoTable对象", description="专著/教材发表情况表")
public class MprEvaMonoTable extends Model<MprEvaMonoTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "专著/教材名称")
    @TableField("MONOGRAPH_TEACH_NAME")
    private String monographTeachName;

    @ApiModelProperty(value = "完成人")
    @TableField("COMPLETE_PERSON")
    private String completePerson;

    @ApiModelProperty(value = "级别（主编、副主编、编委）")
    @TableField("POSITION_LEVEL")
    private String positionLevel;

    @ApiModelProperty(value = "ISBN")
    @TableField("ISBN")
    private String isbn;

    @ApiModelProperty(value = "专著类别（专著/译著/教材）")
    @TableField("MONOGRAPH_TYPE")
    private String monographType;

    @ApiModelProperty(value = "出版社")
    @TableField("PUB_HOUSE")
    private String pubHouse;

    @ApiModelProperty(value = "出版时间")
    @TableField("PUB_DATE")
    private String pubDate;

    @ApiModelProperty(value = "字数（万字）")
    @TableField("WORD_COUNT")
    private String wordCount;

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

    public static final String MONOGRAPH_TEACH_NAME = "MONOGRAPH_TEACH_NAME";

    public static final String COMPLETE_PERSON = "COMPLETE_PERSON";

    public static final String POSITION_LEVEL = "POSITION_LEVEL";

    public static final String ISBN = "ISBN";

    public static final String MONOGRAPH_TYPE = "MONOGRAPH_TYPE";

    public static final String PUB_HOUSE = "PUB_HOUSE";

    public static final String PUB_DATE = "PUB_DATE";

    public static final String WORD_COUNT = "WORD_COUNT";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
