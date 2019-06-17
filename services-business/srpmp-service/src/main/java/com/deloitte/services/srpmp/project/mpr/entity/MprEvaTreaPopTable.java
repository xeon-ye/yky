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
 * 论文发表情况表-科普
 * </p>
 *
 * @author LIJUN
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_TREA_POP_TABLE")
@ApiModel(value="MprEvaTreaPopTable对象", description="论文发表情况表-科普")
public class MprEvaTreaPopTable extends Model<MprEvaTreaPopTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "科普基地名称")
    @TableField("POPULAR_BASE_NAME")
    private String popularBaseName;

    @ApiModelProperty(value = "科普内容")
    @TableField("POPULAR_SCIENCE_CONTENT")
    private String popularScienceContent;

    @ApiModelProperty(value = "成立年份")
    @TableField("YEAR_ESTABLISHED")
    private String yearEstablished;

    @ApiModelProperty(value = "接待人次（千人）")
    @TableField("RECEPTION_NUMBER")
    private String receptionNumber;

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

    public static final String POPULAR_BASE_NAME = "POPULAR_BASE_NAME";

    public static final String POPULAR_SCIENCE_CONTENT = "POPULAR_SCIENCE_CONTENT";

    public static final String YEAR_ESTABLISHED = "YEAR_ESTABLISHED";

    public static final String RECEPTION_NUMBER = "RECEPTION_NUMBER";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
