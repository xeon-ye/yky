package com.deloitte.services.contract.entity;

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
 * 合同文件关系表
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_BASIC_ATTAMENT_MAP")
@ApiModel(value="BasicAttamentMap对象", description="合同文件关系表")
public class BasicAttamentMap extends Model<BasicAttamentMap> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "合同编号")
    @TableField("CONTRACT_ID")
    private Long contractId;

    @ApiModelProperty(value = "文件编号")
    @TableField("ATTAMENT_ID")
    private Long attamentId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    @TableField("IS_USED")
    private String isUsed;

    @ApiModelProperty(value = "文件名称")
    @TableField("FILE_NAME")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    @TableField("FILE_URL")
    private String fileUrl;

    @ApiModelProperty(value = "文件大小")
    @TableField("FILE_SIZE")
    private Long fileSize;

    @ApiModelProperty(value = "文件上传时间")
    @TableField("UPLOAD_TIME")
    private LocalDateTime uploadTime;

    @ApiModelProperty(value = "文件后缀")
    @TableField("FILE_EXT")
    private String fileExt;

    @ApiModelProperty(value = "文件类型(如：合同附件等)")
    @TableField("FILE_TYPE")
    private String fileType;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_1")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_2")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_3")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_4")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_5")
    private Long spareField5;

    public static final String ID = "ID";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String ATTAMENT_ID = "ATTAMENT_ID";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String IS_USED = "IS_USED";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    public static final String FILE_NAME = "FILE_NAME";

    public static final String FILE_URL = "FILE_URL";

    public static final String FILE_SIZE = "FILE_SIZE";

    public static final String UPLOAD_TIME = "UPLOAD_TIME";

    public static final String FILE_EXT = "FILE_EXT";

    public static final String FILE_TYPE = "FILE_TYPE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
