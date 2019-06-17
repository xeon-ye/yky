package com.deloitte.services.fssc.business.ppc.entity;

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
 * 项目款项确认单款项信息行表
 * </p>
 *
 * @author qiliao
 * @since 2019-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PPC_PROJECT_PAYMENT_LINE_DETAI")
@ApiModel(value="ProjectPaymentLineDetai对象", description="项目款项确认单款项信息行表")
public class ProjectPaymentLineDetai extends Model<ProjectPaymentLineDetai> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

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

    @ApiModelProperty(value = "单据ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "款项收入小类ID")
    @TableField("IN_COME_SUB_TYPE_ID")
    private Long inComeSubTypeId;

    @ApiModelProperty(value = "款项收入小类科目CODE")
    @TableField("IN_COME_SUB_SUBJECT_CODE")
    private String inComeSubSubjectCode;

    @ApiModelProperty(value = "款项收入小类CODE")
    @TableField("IN_COME_SUB_TYPE_CODE")
    private String inComeSubTypeCode;

    @ApiModelProperty(value = "款项收入小类名称")
    @TableField("IN_COME_SUB_TYPE_NAME")
    private String inComeSubTypeName;

    @TableField("ACCOUNT_CODE")
    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;


    @ApiModelProperty(value = "预计到款金额")
    @TableField("EXPECTED_AMOUNT")
    private BigDecimal expectedAmount;

    @ApiModelProperty(value = "预计到款时间")
    @TableField("EXPECTED_RECIEVE_TIME")
    private LocalDateTime expectedRecieveTime;

    @ApiModelProperty(value = "对方单位信息ID")
    @TableField("ADVERSE_UNIT_ID")
    private Long adverseUnitId;

    @ApiModelProperty(value = "对方单位信息CODE")
    @TableField("ADVERSE_UNIT_CODE")
    private String adverseUnitCode;

    @ApiModelProperty(value = "对方单位信息名称")
    @TableField("ADVERSE_UNIT_NAME")
    private String adverseUnitName;

    @ApiModelProperty(value = "资金来源")
    @TableField("CAPITAL_SOURCE")
    private String capitalSource;

    @ApiModelProperty(value = "社会统一信用代码")
    @TableField("ADVERSE_COMMON_CREDIT_CODE")
    private String adverseCommonCreditCode;

    @ApiModelProperty(value = "对方银行账户名称")
    @TableField("ADVERSE_BANK_NAME")
    private String adverseBankName;

    @ApiModelProperty(value = "对方银行账号")
    @TableField("ADVERSE_BANK_ACCOUNT")
    private String adverseBankAccount;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

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

    @ApiModelProperty(value = "行号")
    @TableField("LINE_NUMBER")
    private Long lineNumber;


    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String IN_COME_SUB_TYPE_ID = "IN_COME_SUB_TYPE_ID";

    public static final String IN_COME_SUB_SUBJECT_CODE = "IN_COME_SUB_SUBJECT_CODE";

    public static final String IN_COME_SUB_TYPE_CODE = "IN_COME_SUB_TYPE_CODE";

    public static final String IN_COME_SUB_TYPE_NAME = "IN_COME_SUB_TYPE_NAME";

    public static final String EXPECTED_AMOUNT = "EXPECTED_AMOUNT";

    public static final String EXPECTED_RECIEVE_TIME = "EXPECTED_RECIEVE_TIME";

    public static final String ADVERSE_UNIT_ID = "ADVERSE_UNIT_ID";

    public static final String ADVERSE_UNIT_CODE = "ADVERSE_UNIT_CODE";

    public static final String ADVERSE_UNIT_NAME = "ADVERSE_UNIT_NAME";

    public static final String CAPITAL_SOURCE = "CAPITAL_SOURCE";

    public static final String ADVERSE_COMMON_CREDIT_CODE = "ADVERSE_COMMON_CREDIT_CODE";

    public static final String ADVERSE_BANK_NAME = "ADVERSE_BANK_NAME";

    public static final String ADVERSE_BANK_ACCOUNT = "ADVERSE_BANK_ACCOUNT";

    public static final String REMARK = "REMARK";

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
