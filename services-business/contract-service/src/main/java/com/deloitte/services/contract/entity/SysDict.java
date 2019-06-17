package com.deloitte.services.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 合同管理字典表
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SYS_DICT")
@ApiModel(value="SysDict对象", description="合同管理字典表")
public class SysDict extends Model<SysDict> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "字典类型")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "字典编码")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "字典值")
    @TableField("VALUE")
    private String value;

    @ApiModelProperty(value = "父级字典编码，顶级为0")
    @TableField("PARENT_CODE")
    private Long parentCode;

    @ApiModelProperty(value = "字典生效时间")
    @TableField("ACTIVE_DATE")
    private LocalDateTime activeDate;

    @ApiModelProperty(value = "字典失效时间")
    @TableField("EXPIRED_DATE")
    private LocalDateTime expiredDate;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    @TableField("IS_UESD")
    private String isUesd;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIBE")
    private String describe;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "变更时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "变更人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

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

    public static final String TYPE = "TYPE";

    public static final String CODE = "CODE";

    public static final String VALUE = "VALUE";

    public static final String PARENT_CODE = "PARENT_CODE";

    public static final String ACTIVE_DATE = "ACTIVE_DATE";

    public static final String EXPIRED_DATE = "EXPIRED_DATE";

    public static final String IS_UESD = "IS_UESD";

    public static final String DESCRIBE = "DESCRIBE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
