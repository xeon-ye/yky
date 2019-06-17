package com.deloitte.services.fssc.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author hjy
 * @since 2019-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_CONTENT_COMMITMENT")
@ApiModel(value="BaseContentCommitment对象", description="")
public class BaseContentCommitment extends Model<BaseContentCommitment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单据名称")
    @TableField("BILL_NAME")
    private String billName;

    @ApiModelProperty(value = "是否有效")
    @TableField("VALID_FLAG")
    private String validFlag;

    @ApiModelProperty(value = "承诺书内容")
    @TableField("CONTENT_COMMITMENT")
    private String contentCommitment;

    @ApiModelProperty(value = "生效时间")
    @TableField("VALID_DATE")
    private LocalDateTime validDate;

    @ApiModelProperty(value = "失效时间")
    @TableField("INVALID_DATE")
    private LocalDateTime invalidDate;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "单据类型ID")
    @TableField("DOCUMENT_TYPE_ID")
    private Long documentTypeId;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "单位编码")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "EXT1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "EXT2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "EXT3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "EXT4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "EXT5")
    @TableField("EXT5")
    private String ext5;


    public static final String ID = "ID";

    public static final String BILL_NAME = "BILL_NAME";

    public static final String VALID_FLAG = "VALID_FLAG";

    public static final String CONTENT_COMMITMENT = "CONTENT_COMMITMENT";

    public static final String DOCUMENT_TYPE_ID = "DOCUMENT_TYPE_ID";

    public static final String ORG_ID = "ORG_ID";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String VALID_DATE = "VALID_DATE";

    public static final String INVALID_DATE = "INVALID_DATE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
