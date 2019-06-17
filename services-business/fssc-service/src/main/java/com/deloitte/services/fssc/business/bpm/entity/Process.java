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
 * 流程实例与单据关联表
 * </p>
 *
 * @author qiliao
 * @since 2019-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BPM_PROCESS")
@ApiModel(value="Process对象", description="流程实例与单据关联表")
public class Process extends Model<Process> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

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

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

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

    @ApiModelProperty(value = "预留字段6")
    @TableField("EX6")
    private String ex6;

    @ApiModelProperty(value = "预留字段7")
    @TableField("EX7")
    private String ex7;

    @ApiModelProperty(value = "预留字段8")
    @TableField("EX8")
    private String ex8;

    @ApiModelProperty(value = "预留字段9")
    @TableField("EX9")
    private String ex9;

    @ApiModelProperty(value = "预留字段10")
    @TableField("EX10")
    private String ex10;

    @ApiModelProperty(value = "预留字段11")
    @TableField("EX11")
    private String ex11;

    @ApiModelProperty(value = "预留字段12")
    @TableField("EX12")
    private String ex12;

    @ApiModelProperty(value = "预留字段13")
    @TableField("EX13")
    private String ex13;

    @ApiModelProperty(value = "预留字段14")
    @TableField("EX14")
    private String ex14;

    @ApiModelProperty(value = "预留字段15")
    @TableField("EX15")
    private String ex15;

    @ApiModelProperty(value = "流程实例ID")
    @TableField("PROCESS_ID")
    private String processId;

    @ApiModelProperty(value = "本地流程定义ID")
    @TableField("PROCESS_DEF_ID")
    private Long processDefId;

    @ApiModelProperty(value = "流程状态")
    @TableField("PROCESS_STATUS")
    private String processStatus;

    @ApiModelProperty(value = "是否结束流程")
    @TableField("IS_FINSHED")
    private String isFinshed;

    @ApiModelProperty(value = "单据ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型取表名")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "是否有效")
    @TableField("IS_VALID")
    private String isValid;

    @ApiModelProperty(value = "结束时间")
    @TableField("END_TIME")
    private LocalDateTime endTime;


    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String EX1 = "EX1";

    public static final String EX2 = "EX2";

    public static final String EX3 = "EX3";

    public static final String EX4 = "EX4";

    public static final String EX5 = "EX5";

    public static final String EX6 = "EX6";

    public static final String EX7 = "EX7";

    public static final String EX8 = "EX8";

    public static final String EX9 = "EX9";

    public static final String EX10 = "EX10";

    public static final String EX11 = "EX11";

    public static final String EX12 = "EX12";

    public static final String EX13 = "EX13";

    public static final String EX14 = "EX14";

    public static final String EX15 = "EX15";

    public static final String PROCESS_ID = "PROCESS_ID";

    public static final String PROCESS_DEF_ID = "PROCESS_DEF_ID";

    public static final String PROCESS_STATUS = "PROCESS_STATUS";

    public static final String IS_FINSHED = "IS_FINSHED";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String IS_VALID = "IS_VALID";

    public static final String END_TIME = "END_TIME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
