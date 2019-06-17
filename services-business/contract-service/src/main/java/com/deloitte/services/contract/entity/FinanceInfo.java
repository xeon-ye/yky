package com.deloitte.services.contract.entity;

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
import lombok.experimental.Accessors;

/**
 * <p>
 * 合同财务信息
 * </p>
 *
 * @author yangyq
 * @since 2019-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_FINANCE_INFO")
@ApiModel(value="FinanceInfo对象", description="合同财务信息")
public class FinanceInfo extends Model<FinanceInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "我方签约主体编号")
    @TableField("OUR_CONTRACT_ENTITY_CODE")
    private String ourContractEntityCode;

    @ApiModelProperty(value = "我方签约主体")
    @TableField("OUR_CONTRACT_ENTITY")
    private String ourContractEntity;

    @ApiModelProperty(value = "对方签约主体编号")
    @TableField("OTHER_CONTRACT_ENTITY_CODE")
    private String otherContractEntityCode;

    @ApiModelProperty(value = "对方签约主体")
    @TableField("OTHER_CONTRACT_ENTITY")
    private String otherContractEntity;

    @ApiModelProperty(value = "对方开户银行编码")
    @TableField("OTHER_BANK_CODE")
    private String otherBankCode;

    @ApiModelProperty(value = "对方开户银行")
    @TableField("OTHER_BANK")
    private String otherBank;

    @ApiModelProperty(value = "对方账户名称")
    @TableField("OTHER_ACCOUNT_NAME")
    private String otherAccountName;

    @ApiModelProperty(value = "对方银行账号")
    @TableField("OTHER_ACCOUNT")
    private String otherAccount;

    @ApiModelProperty(value = "约定付款批次")
    @TableField("APPOINT_PAY_STAGE")
    private String appointPayStage;

    @ApiModelProperty(value = "约定付款条件")
    @TableField("APPOINT_PAY_CONDITION")
    private String appointPayCondition;

    @ApiModelProperty(value = "约定付款比例")
    @TableField("APPOINT_PAY_RATE")
    private Long appointPayRate;

    @ApiModelProperty(value = "约定付款金额")
    @TableField("APPOINT_PAY_AMOUNT")
    private Double appointPayAmount;

    @ApiModelProperty(value = "实际付款金额")
    @TableField("ACT_PAY_RATE")
    private Double actPayRate;

    @ApiModelProperty(value = "计划付款时间")
    @TableField("PLAN_PAY_TIME")
    private LocalDateTime planPayTime;

    @ApiModelProperty(value = "实际付款时间")
    @TableField("ACT_PAY_TIME")
    private LocalDateTime actPayTime;

    @ApiModelProperty(value = "实际付款是否手工填报")
    @TableField("IS_MANUAL")
    private String isManual;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    @TableField("IS_USED")
    private String isUsed;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "合同编号")
    @TableField("CONTRACT_ID")
    private Long contractId;

    @ApiModelProperty(value = "合同名称")
    @TableField("CONTRACT_NAME")
    private String contractName;

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

    @ApiModelProperty(value = "约定收款批次")
    @TableField("APPOINT_INCOME_STAGE")
    private String appointIncomeStage;

    @ApiModelProperty(value = "约定收款条件")
    @TableField("APPOINT_INCOME_CONDITION")
    private String appointIncomeCondition;

    @ApiModelProperty(value = "约定收款比例")
    @TableField("APPOINT_INCOME_RATE")
    private Long appointIncomeRate;

    @ApiModelProperty(value = "约定收款金额")
    @TableField("APPOINT_INCOME_AMOUNT")
    private Double appointIncomeAmount;

    @ApiModelProperty(value = "实际收款金额")
    @TableField("ACT_INCOME_RATE")
    private Double actIncomeRate;

    @ApiModelProperty(value = "计划收款时间")
    @TableField("PLAN_INCOME_TIME")
    private LocalDateTime planIncomeTime;

    @ApiModelProperty(value = "实际收款时间")
    @TableField("ACT_INCOME_TIME")
    private LocalDateTime actIncomeTime;

    @ApiModelProperty(value = "实际收款是否手工填报")
    @TableField("IS_MANUAL_INCOME")
    private String isManualIncome;

    @ApiModelProperty(value = "合同类型（收入类，支出类等）")
    @TableField("CONTRACT_TYPE")
    private String contractType;

    @ApiModelProperty(value = "判断收付款标记(1付款；2收款)")
    @TableField("TYPE")
    private String type;


    public static final String ID = "ID";

    public static final String OUR_CONTRACT_ENTITY_CODE = "OUR_CONTRACT_ENTITY_CODE";

    public static final String OUR_CONTRACT_ENTITY = "OUR_CONTRACT_ENTITY";

    public static final String OTHER_CONTRACT_ENTITY_CODE = "OTHER_CONTRACT_ENTITY_CODE";

    public static final String OTHER_CONTRACT_ENTITY = "OTHER_CONTRACT_ENTITY";

    public static final String OTHER_BANK_CODE = "OTHER_BANK_CODE";

    public static final String OTHER_BANK = "OTHER_BANK";

    public static final String OTHER_ACCOUNT_NAME = "OTHER_ACCOUNT_NAME";

    public static final String OTHER_ACCOUNT = "OTHER_ACCOUNT";

    public static final String APPOINT_PAY_STAGE = "APPOINT_PAY_STAGE";

    public static final String APPOINT_PAY_CONDITION = "APPOINT_PAY_CONDITION";

    public static final String APPOINT_PAY_RATE = "APPOINT_PAY_RATE";

    public static final String APPOINT_PAY_AMOUNT = "APPOINT_PAY_AMOUNT";

    public static final String ACT_PAY_RATE = "ACT_PAY_RATE";

    public static final String PLAN_PAY_TIME = "PLAN_PAY_TIME";

    public static final String ACT_PAY_TIME = "ACT_PAY_TIME";

    public static final String IS_MANUAL = "IS_MANUAL";

    public static final String IS_USED = "IS_USED";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String CONTRACT_NAME = "CONTRACT_NAME";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    public static final String APPOINT_INCOME_STAGE = "APPOINT_INCOME_STAGE";

    public static final String APPOINT_INCOME_CONDITION = "APPOINT_INCOME_CONDITION";

    public static final String APPOINT_INCOME_RATE = "APPOINT_INCOME_RATE";

    public static final String APPOINT_INCOME_AMOUNT = "APPOINT_INCOME_AMOUNT";

    public static final String ACT_INCOME_RATE = "ACT_INCOME_RATE";

    public static final String PLAN_INCOME_TIME = "PLAN_INCOME_TIME";

    public static final String ACT_INCOME_TIME = "ACT_INCOME_TIME";

    public static final String IS_MANUAL_INCOME = "IS_MANUAL_INCOME";

    public static final String CONTRACT_TYPE = "CONTRACT_TYPE";

    public static final String TYPE = "TYPE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
