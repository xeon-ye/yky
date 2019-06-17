package com.deloitte.services.isump.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jianglong
 * @since 2019-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_ATTACHMENT")
@ApiModel(value="Attachment对象", description="")
public class Attachment extends Model<Attachment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "文件系统ID")
    @TableField("FILE_ID")
    private String fileId;

    @ApiModelProperty(value = "主表类型")
    @TableField("MASTER_TYPE")
    private String masterType;

    @ApiModelProperty(value = "主表ID")
    @TableField("MASTER_ID")
    private Long masterId;

    @ApiModelProperty(value = "文件名称")
    @TableField("FILE_NAME")
    private String fileName;

    @ApiModelProperty(value = "文件地址")
    @TableField("FILE_URL")
    private String fileUrl;

    @ApiModelProperty(value = "序号")
    @TableField("SORT")
    private Integer sort;


    public static final String ID = "ID";

    public static final String FILE_ID = "FILE_ID";

    public static final String MASTER_TYPE = "MASTER_TYPE";

    public static final String MASTER_ID = "MASTER_ID";

    public static final String FILE_NAME = "FILE_NAME";

    public static final String FILE_URL = "FILE_URL";

    public static final String SORT = "SORT";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
