package com.deloitte.services.fssc.base.entity;

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

/**
 * <p>
 * 收入类型定义
 * </p>
 *
 * @author jaws
 * @since 2019-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_PERSON_INCOME_TYPE")
@ApiModel(value="BasePersonIncomeType", description="人员类型定义")
public class BasePersonIncomeType extends Model<BasePersonIncomeType> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单据类型ID")
    @TableField("DOCUMENT_TYPE_ID")
    private Long documentTypeId;

    @ApiModelProperty(value = "类型编码")
    @TableField("TYPE_CODE")
    private String typeCode;

    @ApiModelProperty(value = "类型名称")
    @TableField("TYPE_NAME")
    private String typeName;

    @ApiModelProperty(value = "类型说明")
    @TableField("TYPE_EXPLAIN")
    private String typeExplain;

    @ApiModelProperty(value = "是否有效")
    @TableField("VALID_FLAG")
    private String validFlag;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;


    public static final String ID = "ID";

    public static final String DOCUMENT_TYPE_ID = "DOCUMENT_TYPE_ID";

    public static final String TYPE_CODE = "TYPE_CODE";

    public static final String TYPE_NAME = "TYPE_NAME";

    public static final String TYPE_EXPLAIN = "TYPE_EXPLAIN";

    public static final String VALID_FLAG = "VALID_FLAG";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
