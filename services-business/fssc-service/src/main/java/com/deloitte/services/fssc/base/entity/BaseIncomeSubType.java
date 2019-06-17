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
 * 收入小类
 * </p>
 *
 * @author jaws
 * @since 2019-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_INCOME_SUB_TYPE")
@ApiModel(value="BaseIncomeSubType对象", description="收入小类")
public class BaseIncomeSubType extends Model<BaseIncomeSubType> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "编码")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "收入大类ID")
    @TableField("INCOME_MAIN_TYPE_ID")
    private Long incomeMainTypeId;

    @ApiModelProperty(value = "收入大类编码")
    @TableField(exist = false)
    private String incomeMainTypeCode;

    @ApiModelProperty(value = "收入大类名称")
    @TableField(exist = false)
    private String incomeMainTypeName;

    @ApiModelProperty(value = "财-会计科目编码")
    @TableField("C_ACCOUNT_CODE")
    private String cAccountCode;

    @ApiModelProperty(value = "预-会计科目编码")
    @TableField("Y_ACCOUNT_CODE")
    private String yAccountCode;

    @ApiModelProperty(value = "是否有效")
    @TableField("VALID_FLAG")
    private String validFlag;

    @ApiModelProperty(value = "生效日期")
    @TableField("VALID_DATE")
    private LocalDateTime validDate;

    @ApiModelProperty(value = "失效日期")
    @TableField("INVALID_DATE")
    private LocalDateTime invalidDate;

    @ApiModelProperty(value = "单位编码")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME" , fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime ;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

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

    public static final String CODE = "CODE";

    public static final String NAME = "NAME";

    public static final String INCOME_MAIN_TYPE_ID = "INCOME_MAIN_TYPE_ID";

    public static final String C_ACCOUNT_CODE = "C_ACCOUNT_CODE";

    public static final String Y_ACCOUNT_CODE = "Y_ACCOUNT_CODE";

    public static final String VALID_FLAG = "VALID_FLAG";

    public static final String VALID_DATE = "VALID_DATE";

    public static final String INVALID_DATE = "INVALID_DATE";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
