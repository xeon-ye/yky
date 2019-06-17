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
@TableName("ADP_ADVANCE_PAYMENT_LINE")
@ApiModel(value="BmAdvancePaymentLine对象", description="")
public class AdvancePaymentLine extends Model<AdvancePaymentLine> {

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

    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更改人")
    private Long updateBy;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField("EX1")
    private String ex1;

    @TableField("EX2")
    private String ex2;

    @TableField("EX3")
    private String ex3;

    @TableField("EX4")
    private String ex4;

    @TableField("EX5")
    private String ex5;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

    @ApiModelProperty(value = "支出大类ID")
    @TableField("MAIN_TYPE_ID")
    private Long mainTypeId;

    @TableField("MAIN_TYPE_CODE")
    @ApiModelProperty(value = "大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "支出小类ID")
    @TableField("SUB_TYPE_ID")
    private Long subTypeId;

    @TableField("SUB_TYPE_CODE")
    @ApiModelProperty(value = "支出小类编码")
    private String subTypeCode;

    @TableField("ACCOUNT_CODE")
    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预付金额")
    @TableField("PREPAID_AMOUNT")
    private BigDecimal prepaidAmount;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "对公预付款Id")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "支出大类名称")
    @TableField("MAIN_TYPE_NAME")
    private String mainTypeName;

    @ApiModelProperty(value = "支出小类名称")
    @TableField("SUB_TYPE_NAME")
    private String subTypeName;

    @ApiModelProperty(value="行号")
    @TableField("LINE_NUMBER")
    private Long lineNumber;

    @ApiModelProperty(value="是否被报账单关联Y，N")
    @TableField("IS_ASSOCIATED")
    private String isAssociated;

    @ApiModelProperty(value = "已核销金额")
    @TableField("HAS_VER_AMOUNT")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    @TableField("NO_VER_AMOUNT")
    private BigDecimal noVerAmount;



    public static final String ID = "ID";

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

    public static final String MAIN_TYPE_ID = "MAIN_TYPE_ID";

    public static final String SUB_TYPE_ID = "SUB_TYPE_ID";

    public static final String PREPAID_AMOUNT = "PREPAID_AMOUNT";

    public static final String REMARK = "REMARK";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String MAIN_TYPE_NAME = "MAIN_TYPE_NAME";

    public static final String SUB_TYPE_NAME = "SUB_TYPE_NAME";
    public static final String LINE_NUMBER = "LINE_NUMBER";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
