package com.deloitte.services.fssc.system.dic.entity;

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

/**
 * <p>
 * 数据字典值表
 * </p>
 *
 * @author qiliao
 * @since 2019-02-20zzy
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_DIC_VALUE")
@ApiModel(value="DicValue对象", description="数据字典值表")
public class DicValue extends Model<DicValue> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典值ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "字典主表类型ID")
    @TableField("DIC_PARENT_ID")
    private Long dicParentId;

    @ApiModelProperty(value = "字段代码")
    @TableField("DIC_CODE")
    private String dicCode;

    @ApiModelProperty(value = "字段名称")
    @TableField("DIC_NAME")
    private String dicName;

    @ApiModelProperty(value = "字典值")
    @TableField("DIC_VALUE")
    private String dicValue;

    @ApiModelProperty(value = "字段描述")
    @TableField("DIC_DESCIPTION")
    private String dicDesciption;

    @ApiModelProperty(value = "排序编号")
    @TableField("DIC_ORDER")
    private Double dicOrder;

    @ApiModelProperty(value = "是否有效")
    @TableField("IS_VALID")
    private String isValid;

    @ApiModelProperty(value = "预留字段1")
    @TableField("EXT_1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EXT_2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EXT_3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EXT_4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EXT_5")
    private String ext5;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;


    public static final String ID = "ID";

    public static final String DIC_PARENT_ID = "DIC_PARENT_ID";

    public static final String DIC_CODE = "DIC_CODE";

    public static final String DIC_NAME = "DIC_NAME";

    public static final String DIC_VALUE = "DIC_VALUE";

    public static final String DIC_DESCIPTION = "DIC_DESCIPTION";

    public static final String DIC_ORDER = "DIC_ORDER";

    public static final String IS_VALID = "IS_VALID";

    public static final String EXT_1 = "EXT_1";

    public static final String EXT_2 = "EXT_2";

    public static final String EXT_3 = "EXT_3";

    public static final String EXT_4 = "EXT_4";

    public static final String EXT_5 = "EXT_5";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
