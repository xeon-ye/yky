package com.deloitte.services.project.entity;

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
 * 维护项目审批记录表
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_MAINT_REPLY_NOTE")
@ApiModel(value="MaintReplyNote对象", description="维护项目审批记录表")
public class MaintReplyNote extends Model<MaintReplyNote> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "审批id")
    @TableField("RELY_ID")
    private String relyId;

    @ApiModelProperty(value = "维护项目id")
    @TableField("MAINT_ID")
    private String maintId;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "审批code")
    @TableField("REPLY_CODE")
    private String replyCode;

    @ApiModelProperty(value = "审批name")
    @TableField("REPLY_NAME")
    private String replyName;

    @ApiModelProperty(value = "审批意见")
    @TableField("REPLY_ADVICE")
    private String replyAdvice;

    @ApiModelProperty(value = "审批部门id")
    @TableField("REPLY_PART_ID")
    private String replyPartId;

    @ApiModelProperty(value = "审批部门")
    @TableField("REPLY_PART_NAME")
    private String replyPartName;

    @ApiModelProperty(value = "审批人id")
    @TableField("REPLY_PERSON_ID")
    private String replyPersonId;

    @ApiModelProperty(value = "审批人")
    @TableField("REPLY_PERSON_NAME")
    private String replyPersonName;

    @ApiModelProperty(value = "审批时间")
    @TableField("REPLY_TIME")
    private LocalDateTime replyTime;

    @ApiModelProperty(value = "上一个审批人id")
    @TableField("REPLY_LAST_ID")
    private String replyLastId;

    @ApiModelProperty(value = "上一个审批人")
    @TableField("REPLY_LAST_NAME")
    private String replyLastName;

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

    @ApiModelProperty(value = "拓展1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    @TableField("EXT2")
    private String ext2;


    public static final String ID = "ID";

    public static final String RELY_ID = "RELY_ID";

    public static final String MAINT_ID = "MAINT_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String REPLY_CODE = "REPLY_CODE";

    public static final String REPLY_NAME = "REPLY_NAME";

    public static final String REPLY_ADVICE = "REPLY_ADVICE";

    public static final String REPLY_PART_ID = "REPLY_PART_ID";

    public static final String REPLY_PART_NAME = "REPLY_PART_NAME";

    public static final String REPLY_PERSON_ID = "REPLY_PERSON_ID";

    public static final String REPLY_PERSON_NAME = "REPLY_PERSON_NAME";

    public static final String REPLY_TIME = "REPLY_TIME";

    public static final String REPLY_LAST_ID = "REPLY_LAST_ID";

    public static final String REPLY_LAST_NAME = "REPLY_LAST_NAME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
