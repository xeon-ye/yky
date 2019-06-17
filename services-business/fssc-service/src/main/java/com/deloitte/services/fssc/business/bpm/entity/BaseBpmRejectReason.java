package com.deloitte.services.fssc.business.bpm.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 单据驳回原因表
 * </p>
 *
 * @author qiliao
 * @since 2019-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_BPM_REJECT_REASON")
@ApiModel(value="BaseBpmRejectReason对象", description="单据驳回原因表")
public class BaseBpmRejectReason extends Model<BaseBpmRejectReason> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "驳回节点key")
    @TableField("REJECT_TASK_KEY")
    private String rejectTaskKey;

    @ApiModelProperty(value = "驳回节点id")
    @TableField("REJECT_TASK_ID")
    private String rejectTaskId;

    @ApiModelProperty(value = "驳回人id")
    @TableField("REJECT_USER_ID")
    private String rejectUserId;

    @ApiModelProperty(value = "驳回人姓名")
    @TableField("REJECT_USER_NAME")
    private String rejectUserName;

    @ApiModelProperty(value = "驳回原因key")
    @TableField("REJECT_REASON")
    private String rejectReason;

    @ApiModelProperty(value = "驳回原因中文")
    @TableField("REJECT_DESC")
    private String rejectDesc;

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

    @ApiModelProperty(value = "驳回人岗位id")
    @TableField("REJECT_STATION_ID")
    private String rejectStationId;

    public static final String ID = "ID";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String REJECT_TASK_KEY = "REJECT_TASK_KEY";

    public static final String REJECT_TASK_ID = "REJECT_TASK_ID";

    public static final String REJECT_USER_ID = "REJECT_USER_ID";

    public static final String REJECT_USER_NAME = "REJECT_USER_NAME";

    public static final String REJECT_REASON = "REJECT_REASON";

    public static final String REJECT_DESC = "REJECT_DESC";

    public static final String EX1 = "EX1";

    public static final String EX2 = "EX2";

    public static final String EX3 = "EX3";

    public static final String EX4 = "EX4";

    public static final String EX5 = "EX5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
