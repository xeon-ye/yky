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
 * 附件表 附件表

 * </p>
 *
 * @author zhengchun
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_ENCLOSURE")
@ApiModel(value="Enclosure对象", description="附件表 附件表 ")
public class Enclosure extends Model<Enclosure> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号 ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "附件编号")
    @TableField("ENCLOSURE_ID")
    private String enclosureId;

    @ApiModelProperty(value = "附件名称")
    @TableField("ENCLOSURE_NAME")
    private String enclosureName;

    @ApiModelProperty(value = "附件类型")
    @TableField("ENCLOSURE_TYPE")
    private String enclosureType;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "数据权限维度字段org_id")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "数据权限维度字段org_path")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "附件文件服务地址")
    @TableField("ENCLOSURE_URL")
    private String enclosureUrl;

    @ApiModelProperty(value = "文件服务器文件ID")
    @TableField("FILE_ID")
    private String fileId;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "上传时间")
    @TableField("UPLOAD_TIME")
    private String uploadTime;

    @ApiModelProperty(value = "评审id")
    @TableField("REVIEW_ID")
    private String reviewId;

    @ApiModelProperty(value = "项目维护id")
    @TableField("MIAN_ID")
    private String mianId;

    @ApiModelProperty(value = "项目执行变更id")
    @TableField("CHANGE_ID")
    private String changeId;

    @ApiModelProperty(value = "项目评价id,区分字段")
    @TableField("PROJECT_EVALUATION_ID")
    private String projectEvaluationId;

    @ApiModelProperty(value = "项目（申报书）取消id,区分字段")
    @TableField("APPLICATION_CANCEL_ID")
    private String applicationCancelId;


    public static final String ID = "ID";

    public static final String ENCLOSURE_ID = "ENCLOSURE_ID";

    public static final String ENCLOSURE_NAME = "ENCLOSURE_NAME";

    public static final String ENCLOSURE_TYPE = "ENCLOSURE_TYPE";

    public static final String REMARK = "REMARK";

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

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String ENCLOSURE_URL = "ENCLOSURE_URL";

    public static final String FILE_ID = "FILE_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String UPLOAD_TIME = "UPLOAD_TIME";

    public static final String REVIEW_ID = "REVIEW_ID";

    public static final String MIAN_ID = "MIAN_ID";

    public static final String CHANGE_ID = "CHANGE_ID";

    public static final String PROJECT_EVALUATION_ID = "PROJECT_EVALUATION_ID";

    public static final String APPLICATION_CANCEL_ID = "APPLICATION_CANCEL_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
