package com.deloitte.services.fssc.business.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 教育经费细化申请单行
 * </p>
 *
 * @author qiliao
 * @since 2019-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("EDU_FUNDS_APPLY_LINE")
@ApiModel(value="FundsApplyLine对象", description="教育经费细化申请单行")
public class FundsApplyLine extends Model<FundsApplyLine> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @TableField("DOCUMENT_ID")
    private Long documentId;

    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "创建人ID申请人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名申请人")
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

    @ApiModelProperty(value = "说明")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "分配学院ID")
    @TableField("SCHOOL_ID")
    private Long schoolId;
    @ApiModelProperty(value = "分配学院code")
    @TableField("SCHOOL_CODE")
    private String schoolCode;
    @ApiModelProperty(value = "分配学院名称")
    @TableField("SCHOOL_NAME")
    private String schoolName;

    @ApiModelProperty(value = "申请金额")
    @TableField("APPLY_AMOUNT")
    private BigDecimal applyAmount;

    @ApiModelProperty(value = "用途类型")
    @TableField("USE_TYPE")
    private String useType;

    @ApiModelProperty(value = "受款负责人ID")
    @TableField("RECIEVE_USER_ID")
    private Long recieveUserId;

    @ApiModelProperty(value = "受款负责人姓名")
    @TableField("RECIEVE_USER_NAME")
    private String recieveUserName;

    @ApiModelProperty(value = "受款负责人工号")
    @TableField("RECIEVE_EMP_NO")
    private String recieveEmpNo;

    @ApiModelProperty(value = "申请单位名称")
    @TableField("APPLY_UNIT_NAME")
    private String applyUnitName;

    @ApiModelProperty(value = "申请人名称")
    @TableField("APPLY_USER_NAME")
    private String applyUserName;

    @ApiModelProperty(value = "是否被调整申请关联")
    @TableField("IS_CONNECTED")
    private String isConnected;

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

    @TableField("LINE_NUMBER")
    @ApiModelProperty(value = "行号")
    private Long lineNumber;

    public static final String DOCUMENT_ID="DOCUMENT_ID";

    public static final String DOCUMENT_TYPE="DOCUMENT_TYPE";

    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String REMARK = "REMARK";

    public static final String SCHOOL_ID = "SCHOOL_ID";

    public static final String APPLY_AMOUNT = "APPLY_AMOUNT";

    public static final String USE_TYPE = "USE_TYPE";

    public static final String RECIEVE_USER_ID = "RECIEVE_USER_ID";

    public static final String RECIEVE_USER_NAME = "RECIEVE_USER_NAME";

    public static final String RECIEVE_EMP_NO = "RECIEVE_EMP_NO";

    public static final String APPLY_UNIT_NAME = "APPLY_UNIT_NAME";

    public static final String APPLY_USER_NAME = "APPLY_USER_NAME";

    public static final String IS_CONNECTED = "IS_CONNECTED";

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

    public static final String LINE_NUMBER = "LINE_NUMBER";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
