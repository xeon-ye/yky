package com.deloitte.services.fssc.business.borrow.entity;

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
 * 借款行表
 * </p>
 *
 * @author qiliao
 * @since 2019-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BM_BORROW_MONEY_LINE")
@ApiModel(value="BorrowMoneyLine对象", description="借款行表")
public class BorrowMoneyLine extends Model<BorrowMoneyLine> {

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

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

    @ApiModelProperty(value = "支出小类ID")
    @TableField("SUB_TYPE_ID")
    private Long subTypeId;

    @TableField("SUB_TYPE_CODE")
    private String subTypeCode;

    @ApiModelProperty("会计科目代码")
    @TableField("ACCOUNT_CODE")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "借款金额")
    @TableField("BORROW_AMOUNT")
    private BigDecimal borrowAmount;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "借款主表ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "支出小类名称")
    @TableField("SUB_TYPE_NAME")
    private String subTypeName;

    @TableField("LINE_NUMBER")
    @ApiModelProperty(value = "行号")
    private Long lineNumber;

    @TableField("IS_ASSOCIATED")
    @ApiModelProperty(value = "是否被报账单关联Y,N")
    private String isAssociated;

    @ApiModelProperty(value = "已核销金额")
    @TableField("HAS_VER_AMOUNT")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    @TableField("NO_VER_AMOUNT")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "付款方式")
    @TableField("PAYMENT_TYPE")
    private String paymentType;

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

    public static final String BORROW_AMOUNT = "BORROW_AMOUNT";

    public static final String REMARK = "REMARK";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE="DOCUMENT_TYPE";

    public static final String LINE_NUMBER="LINE_NUMBER";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
