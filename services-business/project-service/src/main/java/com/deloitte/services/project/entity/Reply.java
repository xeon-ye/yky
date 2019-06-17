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
 * 项目批复书
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_REPLY")
@ApiModel(value="Reply对象", description="项目批复书")
public class Reply extends Model<Reply> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目批复书ID")
    @TableField("REPLY_DOCUMENT_ID")
    private String replyDocumentId;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "项目执行年度")
    @TableField("OPERATION_YEAR")
    private String operationYear;

    @ApiModelProperty(value = "批复年度")
    @TableField("REPLY_YEAR")
    private String replyYear;

    @ApiModelProperty(value = "计划开始年度")
    @TableField("BEGIN_YEAR")
    private String beginYear;

    @ApiModelProperty(value = "项目周期")
    @TableField("CYCLE")
    private String cycle;

    @ApiModelProperty(value = "创建")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "批复编码")
    @TableField("REPLY_CODE")
    private String replyCode;

    @ApiModelProperty(value = "业务单号流水号")
    @TableField("SERVICE_NUM")
    private String serviceNum;

    @ApiModelProperty(value = "批复状态code")
    @TableField("REPLY_STATUS_CODE")
    private String replyStatusCode;

    @ApiModelProperty(value = "批复状态name")
    @TableField("REPLY_STATUS_NAME")
    private String replyStatusName;

    @ApiModelProperty(value = "批复时间")
    @TableField("REPLY_TIME")
    private LocalDateTime replyTime;

    @ApiModelProperty(value = "批复人id")
    @TableField("REPLY_PERSON_ID")
    private String replyPersonId;

    @ApiModelProperty(value = "批复人name")
    @TableField("REPLY_PERSON_NAME")
    private String replyPersonName;


    public static final String ID = "ID";

    public static final String REPLY_DOCUMENT_ID = "REPLY_DOCUMENT_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String OPERATION_YEAR = "OPERATION_YEAR";

    public static final String REPLY_YEAR = "REPLY_YEAR";

    public static final String BEGIN_YEAR = "BEGIN_YEAR";

    public static final String CYCLE = "CYCLE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String REPLY_CODE = "REPLY_CODE";

    public static final String SERVICE_NUM = "SERVICE_NUM";

    public static final String REPLY_STATUS_CODE = "REPLY_STATUS_CODE";

    public static final String REPLY_STATUS_NAME = "REPLY_STATUS_NAME";

    public static final String REPLY_TIME = "REPLY_TIME";

    public static final String REPLY_PERSON_ID = "REPLY_PERSON_ID";

    public static final String REPLY_PERSON_NAME = "REPLY_PERSON_NAME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
