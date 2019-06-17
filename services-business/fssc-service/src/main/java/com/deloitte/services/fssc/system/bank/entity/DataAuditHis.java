package com.deloitte.services.fssc.system.bank.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 基础信息审核历史表
 * </p>
 *
 * @author qiliao
 * @since 2019-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_DATA_AUDIT_HIS")
@ApiModel(value="DataAuditHis对象", description="基础信息审核历史表")
public class DataAuditHis extends Model<DataAuditHis> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "历史记录ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "对象ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "对象类型即表名")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "审核意见")
    @TableField("AUDIT_OPIN")
    private String auditOpin;
    @ApiModelProperty(value = "拒绝原因")
    @TableField("REFUSED_RESON")
    private String refusedReson;
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


    public static final String ID = "ID";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String AUDIT_OPIN = "AUDIT_OPIN";

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

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
