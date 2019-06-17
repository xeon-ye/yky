package com.deloitte.services.fssc.system.bank.entity;

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
 * 单位银行信息主表
 * </p>
 *
 * @author qiliao
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_BANK_UNIT_INFO")
@ApiModel(value="BankUnitInfo对象", description="单位银行信息主表")
public class BankUnitInfo extends Model<BankUnitInfo> implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位ID")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "单位名称")
    @TableField("UNIT_NAME")
    private String unitName;

    @ApiModelProperty(value = "单位code")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "单位类型")
    @TableField("UNIT_TYPE")
    private String unitType;

    @ApiModelProperty(value = "统一信用代码")
    @TableField("COMMON_CREDIT_CODE")
    private String commonCreditCode;

    @ApiModelProperty(value = "银行账号")
    @TableField("BANK_ACCOUNT")
    private String bankAccount;

    @ApiModelProperty(value = "银行账号名称")
    @TableField("BANK_ACCOUNT_NAME")
    private String bankAccountName;

    @ApiModelProperty(value = "会计科目ID")
    @TableField("SUBJECT_ID")
    private Long subjectId;

    @ApiModelProperty(value = "会计科目名称")
    @TableField("SUBJECT_NAME")
    private String subjectName;

    @ApiModelProperty(value = "会计科目代码")
    @TableField("SUBJECT_CODE")
    private String subjectCode;

    @ApiModelProperty(value = "预算会计科目ID")
    @TableField("BUDGET_SUBJECT_ID")
    private Long budgetSubjectId;

    @ApiModelProperty(value = "预算会计科目名称")
    @TableField("BUDGET_SUBJECT_NAME")
    private String budgetSubjectName;

    @ApiModelProperty(value = "预算会计科目代码")
    @TableField("BUDGET_SUBJECT_CODE")
    private String budgetSubjectCode;

    @ApiModelProperty(value = "提交的审核状态")
    @TableField("AUDIT_STATUS")
    private String auditStatus;

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

    @ApiModelProperty(value = "预留字段1 是否被关联  Y N")
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

    @ApiModelProperty(value = "账户性质")
    @TableField("BANK_TYPE")
    private String bankType;

    @ApiModelProperty(value = "是否银企直联")
    @TableField("IS_BANK_DRECT_LINK")
    private String isBankDrectLink;

    @ApiModelProperty(value = "状态")
    @TableField("IS_VALID")
    private String isValid;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "银行ID")
    @TableField("BANK_ID")
    private Long bankId;

    @ApiModelProperty(value = "币种")
    @TableField("CURRENCY_CODE")
    private String currencyCode;

    public static final String ID = "ID";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String BANK_ACCOUNT = "BANK_ACCOUNT";

    public static final String SUBJECT_ID = "SUBJECT_ID";

    public static final String AUDIT_STATUS = "AUDIT_STATUS";

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

    public static final String BANK_TYPE = "BANK_TYPE";

    public static final String IS_BANK_DRECT_LINK = "IS_BANK_DRECT_LINK";

    public static final String IS_VALID = "IS_VALID";

    public static final String REMARK = "REMARK";

    public static final String BANK_ID = "BANK_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
