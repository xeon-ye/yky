package com.deloitte.services.fssc.business.basecontract.entity;

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
 * 合同履行计划
 * </p>
 *
 * @author qiliao
 * @since 2019-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_CONTRACT_PLAN_LINE")
@ApiModel(value="BaseContractPlanLine对象", description="合同履行计划")
public class BaseContractPlanLine extends Model<BaseContractPlanLine> {

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
    @Version
    private Long version;

    @ApiModelProperty(value = "合同ID")
    @TableField("CONTRACT_ID")
    private Long contractId;

    @ApiModelProperty(value = "合同名称")
    @TableField("CONTRACT_NAME")
    private String contractName;

    @ApiModelProperty(value = "履行计划ID")
    @TableField("TRAVEL_PLAN_ID")
    private Long travelPlanId;

    @ApiModelProperty(value = "履行人")
    @TableField("TRAVEL_USER_NAME")
    private String travelUserName;

    @ApiModelProperty(value = "履行部门")
    @TableField("TRAVEL_DEPT_NAME")
    private String travelDeptName;


    @ApiModelProperty(value = "对方签约主体")
    @TableField("SIDE_SUBJECT_NAME")
    private String sideSubjectName;

    @ApiModelProperty(value = "对方签约主体CODE 其实就是id")
    @TableField("SIDE_SUBJECT_CODE")
    private String sideSubjectCode;

    @ApiModelProperty(value = "约定付款批次")
    @TableField("APPOINT_PAY_STAGE")
    private String appointPayStage;

    @ApiModelProperty(value = "约定付款比例")
    @TableField("AGREED_PROPERTIONS")
    private String agreedPropertions;

    @ApiModelProperty(value = "约定付款金额")
    @TableField("AGREED_PAYMENT_AMOUNT")
    private BigDecimal agreedPaymentAmount;

    @ApiModelProperty(value = "约定收款批次")
    @TableField("APPOINT_RECIEVE_STAGE")
    private String appointRecieveStage;

    @ApiModelProperty(value = "约定收款比例")
    @TableField("AGREED_RECIEVE_PROPERTIONS")
    private String agreedRecievePropertions;

    @ApiModelProperty(value = "约定收款金额")
    @TableField("AGREED_RECIEVE_AMOUNT")
    private BigDecimal agreedRecieveAmount;

    @ApiModelProperty(value = "计划收款时间")
    @TableField("PLAN_RECIEVE_TIME")
    private LocalDateTime planRecieveTime;

    @ApiModelProperty(value = "计划付款时间")
    @TableField("PLAN_PAY_TIME")
    private LocalDateTime planPayTime;

    @ApiModelProperty(value = "付款状态")
    @TableField("PAY_STATUS")
    private String payStatus;


    @ApiModelProperty(value = "实际收款是否手工填报")
    @TableField("IS_MANUAL_INCOME")
    private String isManualIncome;

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

    @ApiModelProperty(value = "已核销金额")
    @TableField("HAS_VER_AMOUNT")
    private BigDecimal hasVerAmount;
    @ApiModelProperty(value="实际付款金额")
    @TableField("ACTUAL_PLAY_AMOUNT")
    private BigDecimal actualPlayAmount;

    @ApiModelProperty(value="实际付款金额/发票金额")
    @TableField("RECEIP_PLAY_AMOUNT")
    private BigDecimal receipPlayAmount;

    @TableField("PAID_AMOUNT")
    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String CONTRACT_NAME = "CONTRACT_NAME";

    public static final String TRAVEL_PLAN_ID = "TRAVEL_PLAN_ID";

    public static final String UNIT_NAME = "UNIT_NAME";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String SIDE_SUBJECT_NAME = "SIDE_SUBJECT_NAME";

    public static final String APPOINT_PAY_STAGE = "APPOINT_PAY_STAGE";

    public static final String AGREED_PROPERTIONS = "AGREED_PROPERTIONS";

    public static final String AGREED_PAYMENT_AMOUNT = "AGREED_PAYMENT_AMOUNT";

    public static final String PLAN_PAY_TIME = "PLAN_PAY_TIME";

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
