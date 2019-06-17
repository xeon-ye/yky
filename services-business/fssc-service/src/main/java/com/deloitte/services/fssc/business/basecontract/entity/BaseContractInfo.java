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
 * 合同信息头表
 * </p>
 *
 * @author qiliao
 * @since 2019-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_CONTRACT_INFO")
@ApiModel(value="BaseContractInfo对象", description="合同信息头表")
public class BaseContractInfo extends Model<BaseContractInfo> {

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

    @ApiModelProperty(value = "当前合同ID")
    @TableField("CONTRACT_ID")
    private Long contractId;

    @ApiModelProperty(value = "当前合同编号")
    @TableField("CONTRACT_NO")
    private String contractNo;

    @ApiModelProperty(value = "当前合同名称")
    @TableField("CONTRACT_NAME")
    private String contractName;


    @ApiModelProperty(value = "原合同ID")
    @TableField("OLD_CONTRACT_ID")
    private Long oldContractId;

    @ApiModelProperty(value = "原合同编号")
    @TableField("OLD_CONTRACT_NO")
    private String oldContractNo;

    @ApiModelProperty(value = "原合同名称")
    @TableField("OLD_CONTRACT_NAME")
    private String oldContractName;


    @ApiModelProperty(value = "对方签约主体名称")
    @TableField("SIDE_SUBJECT_NAME")
    private String sideSubjectName;

    @ApiModelProperty(value = "我方签约主体名称")
    @TableField("OUR_SUBJECT_NAME")
    private String ourSubjectName;

    @ApiModelProperty(value = "对方签约主体ID")
    @TableField("SIDE_SUBJECT_ID")
    private Long sideSubjectId;

    @ApiModelProperty(value = "我方签约主体ID")
    @TableField("OUR_SUBJECT_ID")
    private Long ourSubjectId;

    @ApiModelProperty(value = "生效日期")
    @TableField("EXECUTE_STATUE_TIME")
    private LocalDateTime executeStatueTime;

    @ApiModelProperty(value = "履行期限开始时间")
    @TableField("EXECUTE_START_TIME")
    private LocalDateTime executeStartTime;

    @ApiModelProperty(value = "履行期限结束时间")
    @TableField("EXECUTE_END_TIME")
    private LocalDateTime executeEndTime;

    @ApiModelProperty(value = "合同类型")
    @TableField("CONTRACT_TYPE")
    private String contractType;

    @ApiModelProperty(value = "合同状态/审批状态")
    @TableField("STATUE")
    private String statue;

    @ApiModelProperty(value = "合同状态/审批状态中文")
    @TableField("STATUE_NAME")
    private String statueName;

    @ApiModelProperty(value = "主办部门")
    @TableField("ORG")
    private String org;

    @ApiModelProperty(value = "主办部门code")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "经办人")
    @TableField("OPERATOR")
    private String operator;

    @ApiModelProperty(value = "是否缴纳印花税")
    @TableField("IS_PAY_STAMP_DUTY")
    private String isPayStampDuty;

    @ApiModelProperty(value = "是否涉外合同")
    @TableField("IS_FOREIGN_CONTRACT")
    private String isForeignContract;

    @ApiModelProperty(value = "合同类型(印花税)")
    @TableField("CONTRACT_TYPE_STAMP_DUTY")
    private String contractTypeStampDuty;

    @ApiModelProperty(value = "应税凭证名称")
    @TableField("NAME_OF_TAXABLE")
    private String nameOfTaxable;

    @ApiModelProperty(value = "印花税税额")
    @TableField("STAMP_DUTY")
    private BigDecimal stampDuty;

    @ApiModelProperty(value = "通用印花税税率")
    @TableField("GENERAL_STAMP_DUTY_RATE")
    private BigDecimal generalStampDutyRate;

    @ApiModelProperty(value = "计税金额或件数")
    @TableField("TAX_AMOUNT_OR_PIECES")
    private BigDecimal taxAmountOrPieces;

    @ApiModelProperty(value = "含税合同金额")
    @TableField("AMOUNT_INCLUD_TAX")
    private BigDecimal amountIncludTax;

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


    public static final String ID = "ID";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String CONTRACT_NO = "CONTRACT_NO";

    public static final String CONTRACT_NAME = "CONTRACT_NAME";

    public static final String SIDE_SUBJECT_NAME = "SIDE_SUBJECT_NAME";

    public static final String OUR_SUBJECT_NAME = "OUR_SUBJECT_NAME";

    public static final String EXECUTE_STATUE_TIME = "EXECUTE_STATUE_TIME";

    public static final String EXECUTE_START_TIME = "EXECUTE_START_TIME";

    public static final String EXECUTE_END_TIME = "EXECUTE_END_TIME";

    public static final String CONTRACT_TYPE = "CONTRACT_TYPE";

    public static final String STATUE_NAME = "STATUE_NAME";

    public static final String ORG = "ORG";

    public static final String OPERATOR = "OPERATOR";

    public static final String IS_PAY_STAMP_DUTY = "IS_PAY_STAMP_DUTY";

    public static final String IS_FOREIGN_CONTRACT = "IS_FOREIGN_CONTRACT";

    public static final String CONTRACT_TYPE_STAMP_DUTY = "CONTRACT_TYPE_STAMP_DUTY";

    public static final String NAME_OF_TAXABLE = "NAME_OF_TAXABLE";

    public static final String STAMP_DUTY = "STAMP_DUTY";

    public static final String GENERAL_STAMP_DUTY_RATE = "GENERAL_STAMP_DUTY_RATE";

    public static final String TAX_AMOUNT_OR_PIECES = "TAX_AMOUNT_OR_PIECES";

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
