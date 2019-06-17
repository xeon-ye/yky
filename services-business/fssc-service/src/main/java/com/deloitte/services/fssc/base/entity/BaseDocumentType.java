package com.deloitte.services.fssc.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
 * 单据类型
 * </p>
 *
 * @author jaws
 * @since 2019-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_DOCUMENT_TYPE")
@ApiModel(value="BaseDocumentType对象", description="单据类型")
public class BaseDocumentType extends Model<BaseDocumentType> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "类型编码")
    @TableField("TYPE_CODE")
    private String typeCode;

    @ApiModelProperty(value = "单据名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "功能模块")
    @TableField("FUNCTION_MODULE")
    private String functionModule;

    @ApiModelProperty(value = "是否预算控制")
    @TableField("BUDGET_CONTROL_FLAG")
    private String budgetControlFlag;

    @ApiModelProperty(value = "是否预算保留")
    @TableField("BUDGET_REMAIN_FLAG")
    private String budgetRemainFlag;

    @ApiModelProperty(value = "是否占用预算")
    @TableField("BUDGET_OCCUPY_FLAG")
    private String budgetOccupyFlag;

    @ApiModelProperty(value = "是否需要审批")
    @TableField("AUDIT_FLAG")
    private String auditFlag;

    @ApiModelProperty(value = "是否需要移动审批")
    @TableField("PHONE_AUDIT_FLAG")
    private String phoneAuditFlag;

    @ApiModelProperty(value = "是否入账")
    @TableField("POST_FLAG")
    private String postFlag;

    @ApiModelProperty(value = "是否有效")
    @TableField("VALID_FLAG")
    private String validFlag;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "单位编码",required = true)
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;

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


    public static final String ID = "ID";

    public static final String TYPE_CODE = "TYPE_CODE";

    public static final String NAME = "NAME";

    public static final String FUNCTION_MODULE = "FUNCTION_MODULE";

    public static final String BUDGET_CONTROL_FLAG = "BUDGET_CONTROL_FLAG";

    public static final String BUDGET_REMAIN_FLAG = "BUDGET_REMAIN_FLAG";

    public static final String BUDGET_OCCUPY_FLAG = "BUDGET_OCCUPY_FLAG";

    public static final String AUDIT_FLAG = "AUDIT_FLAG";

    public static final String PHONE_AUDIT_FLAG = "PHONE_AUDIT_FLAG";

    public static final String POST_FLAG = "POST_FLAG";

    public static final String VALID_FLAG = "VALID_FLAG";

    public static final String ORG_ID = "ORG_ID";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

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
