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
 * 项目执行变更表
 * </p>
 *
 * @author zhengchun
 * @since 2019-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_CHANGE_PRO")
@ApiModel(value="ChangePro对象", description="项目执行变更表")
public class ChangePro extends Model<ChangePro> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "变更id")
    @TableField("CHANGE_ID")
    private String changeId;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "变更原因")
    @TableField("CHANGE_ADV")
    private String changeAdv;

    @ApiModelProperty(value = "变更类型code")
    @TableField("CHANGE_CODE")
    private String changeCode;

    @ApiModelProperty(value = "变更类型name")
    @TableField("CHANGE_NAME")
    private String changeName;

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

    @ApiModelProperty(value = "拓展3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "维度id")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "维度path")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "业务单号流水号")
    @TableField("SERVICE_NUM")
    private String serviceNum;

    @ApiModelProperty(value = "变更时间")
    @TableField("CHANGE_DATE")
    private LocalDateTime changeDate;

    @ApiModelProperty(value = "批复id")
    @TableField("REPLY_ID")
    private String replyId;

    @ApiModelProperty(value = "项目维护id")
    @TableField("MAINTENCEID")
    private String maintenceid;

    @ApiModelProperty(value = "提交状态code")
    @TableField("CHANGE_STATUS_CODE")
    private String changeStatusCode;

    @ApiModelProperty(value = "提交状态name")
    @TableField("CHANGE_STATUS_NAME")
    private String changeStatusName;


    public static final String ID = "ID";

    public static final String CHANGE_ID = "CHANGE_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String CHANGE_ADV = "CHANGE_ADV";

    public static final String CHANGE_CODE = "CHANGE_CODE";

    public static final String CHANGE_NAME = "CHANGE_NAME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String SERVICE_NUM = "SERVICE_NUM";

    public static final String CHANGE_DATE = "CHANGE_DATE";

    public static final String REPLY_ID = "REPLY_ID";

    public static final String MAINTENCEID = "MAINTENCEID";

    public static final String CHANGE_STATUS_CODE = "CHANGE_STATUS_CODE";

    public static final String CHANGE_STATUS_NAME = "CHANGE_STATUS_NAME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
