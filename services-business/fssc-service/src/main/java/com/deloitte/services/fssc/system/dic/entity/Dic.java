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
 * 数据字典主表
 * </p>
 *
 * @author qiliao
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_DIC")
@ApiModel(value="Dic对象", description="数据字典主表")
public class Dic extends Model<Dic> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典类型ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "字段代码")
    @TableField("EUM_CODE")
    private String eumCode;

    @ApiModelProperty(value = "字段名称")
    @TableField("EUM_NAME")
    private String eumName;

    @ApiModelProperty(value = "字段描述")
    @TableField("EUM_DESCIPTION")
    private String eumDesciption;

    @ApiModelProperty(value = "排序编号")
    @TableField("EUM_ORDER")
    private Double eumOrder;

    @ApiModelProperty(value = "关联表名(应用到哪个表)")
    @TableField("EUM_CONCAT_TAB")
    private String eumConcatTab;

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

    public static final String EUM_CODE = "EUM_CODE";

    public static final String EUM_NAME = "EUM_NAME";

    public static final String EUM_DESCIPTION = "EUM_DESCIPTION";

    public static final String EUM_ORDER = "EUM_ORDER";

    public static final String EUM_CONCAT_TAB = "EUM_CONCAT_TAB";

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
