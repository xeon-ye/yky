package com.deloitte.services.fssc.system.file.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文件关联表
 * </p>
 *
 * @author qiliao
 * @since 2019-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_FILE")
@ApiModel(value="File对象", description="文件关联表")
public class File extends Model<File> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("CREATE_USER_NAME")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "预留字段1")
    @TableField("EX1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EX2")
    private String ex2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EX3")
    private String ex3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EX4")
    private String ex4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EX5")
    private String ex5;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

    @ApiModelProperty(value = "文件类型")
    @TableField("FILE_TYPE")
    private String fileType;

    @ApiModelProperty(value = "文件地址")
    @TableField("FILE_URL")
    private String fileUrl;

    @ApiModelProperty(value = "关联对象ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "关联对象类型表名")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "远程文件ID用于删除")
    @TableField("REMOTE_FILE_ID")
    private Long remoteFileId;

    @TableField("FILE_NAME")
    private String fileName;

    @ApiModelProperty(value = "文件类型定义行id")
    @TableField("FILE_DEF_LINE_ID")
    private Long fileDefLineId;

    public static final String FILE_DEF_LINE_ID="FILE_DEF_LINE_ID";

    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String EX1 = "EX1";

    public static final String EX2 = "EX2";

    public static final String EX3 = "EX3";

    public static final String EX4 = "EX4";

    public static final String EX5 = "EX5";

    public static final String VERSION = "VERSION";

    public static final String FILE_TYPE = "FILE_TYPE";

    public static final String FILE_URL = "FILE_URL";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
