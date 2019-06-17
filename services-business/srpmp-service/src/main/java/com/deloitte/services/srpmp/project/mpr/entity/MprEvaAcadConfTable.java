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
 * 举办学术会议情况表
 * </p>
 *
 * @author LIJUN
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_ACAD_CONF_TABLE")
@ApiModel(value="MprEvaAcadConfTable对象", description="举办学术会议情况表")
public class MprEvaAcadConfTable extends Model<MprEvaAcadConfTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "会议名称")
    @TableField("CONFERENCE_NAME")
    private String conferenceName;

    @ApiModelProperty(value = "会议类型")
    @TableField("CONFERENCE_TYPE")
    private String conferenceType;

    @ApiModelProperty(value = "国外代表人数")
    @TableField("FORE_REPR_NUM")
    private String foreReprNum;

    @ApiModelProperty(value = "国内代表人数")
    @TableField("DOME_REPR_NUM")
    private String domeReprNum;

    @ApiModelProperty(value = "会议地点")
    @TableField("CONFERENCE_PLACE")
    private String conferencePlace;

    @ApiModelProperty(value = "举办时间")
    @TableField("HOLDING_TIME")
    private String holdingTime;

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

    public static final String CONFERENCE_NAME = "CONFERENCE_NAME";

    public static final String CONFERENCE_TYPE = "CONFERENCE_TYPE";

    public static final String FORE_REPR_NUM = "FORE_REPR_NUM";

    public static final String DOME_REPR_NUM = "DOME_REPR_NUM";

    public static final String CONFERENCE_PLACE = "CONFERENCE_PLACE";

    public static final String HOLDING_TIME = "HOLDING_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
