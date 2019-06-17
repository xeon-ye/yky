package com.deloitte.services.fssc.business.labor.entity;

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
 * 劳务费报账单行表--外国籍
 * </p>
 *
 * @author qiliao
 * @since 2019-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("LC_LABOR_COST_LINE_FOREIGN")
@ApiModel(value="LcLaborCostLineForeign对象", description="劳务费报账单行表--外国籍")
public class LcLaborCostLineForeign extends Model<LcLaborCostLineForeign> {

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

    @TableField("DOCUMENT_TYPE")
    private String documentType;

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

    @ApiModelProperty(value = "支出小类ID")
    @TableField("SUB_TYPE_ID")
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类CODe")
    @TableField("SUB_TYPE_CODE")
    private String subTypeCode;

    @TableField("ACCOUNT_CODE")
    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "收款人姓名")
    @TableField("RECIEVE_USER_NAME")
    private String recieveUserName;

    @ApiModelProperty(value = "国籍")
    @TableField("NATIONALITY")
    private String nationality;

    @ApiModelProperty(value = "首次来华时间")
    @TableField("FIRST_IN_CHINA")
    private LocalDateTime firstInChina;

    @ApiModelProperty(value = "出生年月")
    @TableField("BIRTHDAY")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "出生地")
    @TableField("BIRTH_ADDRESS")
    private String birthAddress;

    @ApiModelProperty(value = "性别")
    @TableField("GENDER")
    private String gender;

    @ApiModelProperty(value = "有无境内住所")
    @TableField("HAS_DOMICILE_IN_CHINA")
    private String hasDomicileInChina;

    @ApiModelProperty(value = "预计离境时间")
    @TableField("ESTIMATED_TIME_OF_DEPARTURE")
    private LocalDateTime estimatedTimeOfDeparture;

    @ApiModelProperty(value = "任职受雇从业类型")
    @TableField("TYPE_OF_EMPLOYMENT")
    private String typeOfEmployment;

    @ApiModelProperty(value = "任职受雇从业时间")
    @TableField("TYPE_OF_EMPLOYMENT_TIME")
    private LocalDateTime typeOfEmploymentTime;

    @ApiModelProperty(value = "收款人ID没有可不填")
    @TableField("RECIEVE_USER_ID")
    private Long recieveUserId;

    @ApiModelProperty(value = "单位名称")
    @TableField("UNIT_NAME")
    private String unitName;

    @ApiModelProperty(value = "单位code")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "单位id")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "职称")
    @TableField("POSITION")
    private String position;

    @ApiModelProperty(value = "证件类型")
    @TableField("CERT_TYPE")
    private String certType;

    @ApiModelProperty(value = "证件号")
    @TableField("CERT_NUM")
    private String certNum;

    @ApiModelProperty(value = "发放标准")
    @TableField("DISTRIBUTION_STANDARD")
    private String distributionStandard;

    @ApiModelProperty(value = "开户行")
    @TableField("BANK_NAME")
    private String bankName;

    @ApiModelProperty(value = "银行code没有可不填")
    @TableField("BANK_CODE")
    private String bankCode;

    @ApiModelProperty(value = "银行账号")
    @TableField("BANK_ACCOUNT")
    private String bankAccount;

    @ApiModelProperty(value = "联行号")
    @TableField("INTER_BRANCH_NUMBER")
    private String interBranchNumber;

    @ApiModelProperty(value = "应发金额")
    @TableField("SHOULD_GIVE_AMOUNT")
    private BigDecimal shouldGiveAmount;

    @ApiModelProperty(value = "扣税金额")
    @TableField("DEDUCTED_AMOUNT")
    private BigDecimal deductedAmount;

    @ApiModelProperty(value = "实发金额")
    @TableField("REAL_GIVE_AMOUNT")
    private BigDecimal realGiveAmount;

    @ApiModelProperty(value = "小类名称")
    @TableField("SUB_TYPE_NAME")
    private String subTypeName;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @TableField("IS_SINGLE")
    @ApiModelProperty(value = "是否居民个人")
    private String isSingle;

    @TableField("TAKE_PLACE_CERT_NUM")
    @ApiModelProperty(value = "代收人证件号")
    private String takePlaceCertNum;


    @TableField("TAKE_PLACE_NAME")
    @ApiModelProperty(value = "代收人名称")
    private String takePlaceName;

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

    public static final String SUB_TYPE_ID = "SUB_TYPE_ID";

    public static final String SUB_TYPE_CODE = "SUB_TYPE_CODE";

    public static final String RECIEVE_USER_NAME = "RECIEVE_USER_NAME";

    public static final String NATIONALITY = "NATIONALITY";

    public static final String FIRST_IN_CHINA = "FIRST_IN_CHINA";

    public static final String BIRTHDAY = "BIRTHDAY";

    public static final String BIRTH_ADDRESS = "BIRTH_ADDRESS";

    public static final String GENDER = "GENDER";

    public static final String HAS_DOMICILE_IN_CHINA = "HAS_DOMICILE_IN_CHINA";

    public static final String ESTIMATED_TIME_OF_DEPARTURE = "ESTIMATED_TIME_OF_DEPARTURE";

    public static final String TYPE_OF_EMPLOYMENT = "TYPE_OF_EMPLOYMENT";

    public static final String TYPE_OF_EMPLOYMENT_TIME = "TYPE_OF_EMPLOYMENT_TIME";

    public static final String RECIEVE_USER_ID = "RECIEVE_USER_ID";

    public static final String UNIT_NAME = "UNIT_NAME";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String POSITION = "POSITION";

    public static final String CERT_TYPE = "CERT_TYPE";

    public static final String CERT_NUM = "CERT_NUM";

    public static final String DISTRIBUTION_STANDARD = "DISTRIBUTION_STANDARD";

    public static final String BANK_NAME = "BANK_NAME";

    public static final String BANK_CODE = "BANK_CODE";

    public static final String BANK_ACCOUNT = "BANK_ACCOUNT";

    public static final String INTER_BRANCH_NUMBER = "INTER_BRANCH_NUMBER";

    public static final String SHOULD_GIVE_AMOUNT = "SHOULD_GIVE_AMOUNT";

    public static final String DEDUCTED_AMOUNT = "DEDUCTED_AMOUNT";

    public static final String REAL_GIVE_AMOUNT = "REAL_GIVE_AMOUNT";

    public static final String SUB_TYPE_NAME = "SUB_TYPE_NAME";

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
