package com.deloitte.services.fssc.business.advance.entity;

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
 * 
 * </p>
 *
 * @author hjy
 * @since 2019-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ADP_ADVANCE_PAYMENT_INFO")
@ApiModel(value="ApAdvancePaymentInfo对象", description="")
public class AdvancePaymentInfo extends Model<AdvancePaymentInfo> {

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

    @ApiModelProperty(value = "更新人")
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

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    @TableField("DOCUMENT_STATUS")
    private String documentStatus;

    @ApiModelProperty(value = "付款状态")
    @TableField("PAY_STATUS")
    private String payStatus;

    @ApiModelProperty(value = "归属单位ID")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "归属单位名称")
    @TableField("UNIT_NAME")
    private String unitName;

    @ApiModelProperty(value = "归属部门id")
    @TableField("DEPT_ID")
    private Long deptId;

    @ApiModelProperty(value = "部门编码")
    @TableField("DEPT_CODE")
    private String deptCode;

    @ApiModelProperty(value = "归属部门名称")
    @TableField("DEPT_NAME")
    private String deptName;


    @ApiModelProperty(value = "项目ID")
    @TableField(value = "PROJECT_ID",strategy = FieldStrategy.IGNORED)
    private Long projectId;

    @ApiModelProperty(value = "项目编码")
    @TableField(value = "PROJECT_CODE",strategy = FieldStrategy.IGNORED)
    private String projectCode;

    @TableField("PROJECT_UNIT_ID")
    @ApiModelProperty(value = "项目承担单位ID")
    private Long projectUnitId;

    @TableField("PROJECT_UNIT_CODE")
    @ApiModelProperty(value = "项目承担单位编码")
    private String projectUnitCode;

    @TableField("PROJECT_UNIT_NAME")
    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;

    @ApiModelProperty(value = "支出大类ID")
    @TableField("MAIN_TYPE_ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "大类编码")
    @TableField("MAIN_TYPE_CODE")
    private String mainTypeCode;

    @ApiModelProperty(value = "合计金额")
    @TableField("TOTAL_SUM")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "已核销金额")
    @TableField("HAS_VER_AMOUNT")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    @TableField("NO_VER_AMOUNT")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "币种")
    @TableField("CURRENCY_CODE")
    private String currencyCode;

    @ApiModelProperty(value = "是否同意承诺书")
    @TableField("IS_AGREED_PROMIS")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "费率")
    @TableField("COST")
    private BigDecimal cost;



    @ApiModelProperty(value = "支出大类名称")
    @TableField("MAIN_TYPE_NAME")
    private String mainTypeName;

    @ApiModelProperty(value = "项目名称")
    @TableField(value = "PROJECT_NAME",strategy = FieldStrategy.IGNORED)
    private String projectName;

    @ApiModelProperty(value = "付款方式")
    @TableField("PAYMENT_TYPE")
    private String paymentType;

    @ApiModelProperty(value = "供应商")
    @TableField("SUPPLIER")
    private String supplier;

    @ApiModelProperty(value = "供应商编码")
    @TableField("SUPPLIER_CODE")
    private String supplierCode;

    @ApiModelProperty(value = "供应商id")
    @TableField("SUPPLIER_ID")
    private Long supplierId;

    @ApiModelProperty(value = "合同id")
    @TableField("CONTACT_NUMBER_ID")
    private Long contactNumberId;

    @ApiModelProperty(value = "合同编号")
    @TableField("CONTACT_NUMBER")
    private String contactNumber;

    @ApiModelProperty(value = "合同名称")
    @TableField("CONTACT_NAME")
    private String contactName;

    @ApiModelProperty(value = "支持附件数量")
    @TableField("SUPPORT_FILE_NUM")
    private Long supportFileNum;

    @ApiModelProperty(value = "合计金额本币位")
    @TableField("TOTAL_SUM_POSITION")
    private BigDecimal totalSumPosition;

    @ApiModelProperty(value = "已核销本币位")
    @TableField("HAS_VER_AMOUNT_POSITION")
    private BigDecimal hasVerAmountPosition;

    @ApiModelProperty(value = "未核销本币位")
    @TableField("NO_VER_AMOUNT_POSITION")
    private BigDecimal noVerAmountPosition;

    @ApiModelProperty(value="是否事后补单")
    @TableField("IS_AFTER_PATCH")
    private String isAfterPatch;

    @ApiModelProperty(value="已支付金额")
    @TableField("AMOUNT_PAID")
    private BigDecimal amountPaid;

    @ApiModelProperty(value="未支付金额")
    @TableField("UNPAID_AMOUNT")
    private BigDecimal unpaidAmount;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "组织PATH")
    @TableField("ORG_PATH")
    private String orgPath;


    @TableField("IS_GENERATE_PAY_ORDER")
    @ApiModelProperty("是否生成过付款单,提交时修改此状态 Y N")
    private String isGeneratePayOrder;

    @ApiModelProperty(value = "关联付款单Id")
    @TableField(value ="PAYMENT_ID",strategy = FieldStrategy.IGNORED)
    private Long payMentId;

    @TableField("FSSC_CODE")
    @ApiModelProperty(value = "项目预算编码")
    private String fsscCode;

    @ApiModelProperty(value = "项目预算会计科目")
    @TableField("PROJECT_BUDGET_ACCOUNT_CODE")
    private String projectBudgetAccountCode;

    @TableField("EXT1")
    private String ext1;

    @TableField("EXT2")
    private String ext2;

    @TableField("EXT3")
    private String ext3;

    @TableField("EXT4")
    private String ext4;

    @TableField("EXT5")
    private String ext5;

    @TableField("EXT6")
    private String ext6;

    @TableField("EXT7")
    private String ext7;

    @TableField("EXT8")
    private String ext8;

    @TableField("EXT9")
    private String ext9;

    @TableField("EXT10")
    private String ext10;

    @TableField("EXT11")
    private String ext11;

    @TableField("EXT12")
    private String ext12;

    @TableField("EXT13")
    private String ext13;

    @TableField("EXT14")
    private String ext14;

    @TableField("EXT15")
    private String ext15;


    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String DOCUMENT_STATUS = "DOCUMENT_STATUS";

    public static final String PAY_STATUS = "PAY_STATUS";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String MAIN_TYPE_ID = "MAIN_TYPE_ID";

    public static final String TOTAL_SUM = "TOTAL_SUM";

    public static final String HAS_VER_AMOUNT = "HAS_VER_AMOUNT";

    public static final String NO_VER_AMOUNT = "NO_VER_AMOUNT";

    public static final String CURRENCY_CODE = "CURRENCY_CODE";

    public static final String IS_AGREED_PROMIS = "IS_AGREED_PROMIS";

    public static final String REMARK = "REMARK";

    public static final String COST = "COST";

    public static final String UNIT_NAME = "UNIT_NAME";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String MAIN_TYPE_NAME = "MAIN_TYPE_NAME";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PAYMENT_TYPE = "PAYMENT_TYPE";

    public static final String SUPPLIER = "SUPPLIER";

    public static final String SUPPLIER_ID = "SUPPLIER_ID";

    public static final String CONTACT_NUMBER_ID = "CONTACT_NUMBER_ID";

    public static final String CONTACT_NUMBER = "CONTACT_NUMBER";

    public static final String SUPPORT_FILE_NUM = "SUPPORT_FILE_NUM";

    public static final String TOTAL_SUM_POSITION = "TOTAL_SUM_POSITION";

    public static final String HAS_VER_AMOUNT_POSITION = "HAS_VER_AMOUNT_POSITION";

    public static final String NO_VER_AMOUNT_POSITION = "NO_VER_AMOUNT_POSITION";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String EXT6 = "EXT6";

    public static final String EXT7 = "EXT7";

    public static final String EXT8 = "EXT8";

    public static final String EXT9 = "EXT9";

    public static final String EXT10 = "EXT10";

    public static final String EXT11 = "EXT11";

    public static final String EXT12 = "EXT12";

    public static final String EXT13 = "EXT13";

    public static final String EXT14 = "EXT14";

    public static final String EXT15 = "EXT15";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
