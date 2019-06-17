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
 * 关联借款卡号信息
 * </p>
 *
 * @author qiliao
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BM_BORROW_BANK")
@ApiModel(value="BmBorrowBank对象", description="关联借款卡号信息")
public class BmBorrowBank extends Model<BmBorrowBank> {

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

    @ApiModelProperty(value = "预留字段1,用于关联是否被付款单关联")
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

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "付款状态")
    @TableField("PAY_STATUS")
    private String payStatus;
    @ApiModelProperty(value = "付款时间")
    @TableField("PAY_TIME")
    private LocalDateTime payTime;
    @ApiModelProperty(value = "借款原币金额")
    @TableField("BORROW_AMOUNT")
    private BigDecimal borrowAmount;

    @TableField("TRANSACTION_AMOUNT")
    @ApiModelProperty(value = "交易金额")
    private BigDecimal transactionAmount;

    @ApiModelProperty(value = "类型工资卡SALARY还是公务卡BUSINESS")
    @TableField("GET_OR_RETURN")
    private String getOrReturn;

    @ApiModelProperty(value = "借款单据ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "工号")
    @TableField("EMP_NO")
    private String empNo;
    @ApiModelProperty(value = "银行名称")
    @TableField("BANK_NAME")
    private String bankName;
    @ApiModelProperty(value = "银行账户")
    @TableField("BANK_ACCOUNT")
    private String bankAccount;
    @ApiModelProperty(value = "联行号")
    @TableField("INTER_BRANCH_NUMBER")
    private String interBranchNumber;
    @ApiModelProperty(value = "关联的表名")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "员工姓名")
    @TableField("EMP_NAME")
    private String empName;
    @TableField("EMP_ID")
    private Long empId;

    @TableField("LINE_NUMBER")
    @ApiModelProperty(value = "行号")
    private Long lineNumber;

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

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String PAY_STATUS = "PAY_STATUS";

    public static final String BORROW_AMOUNT = "BORROW_AMOUNT";

    public static final String GET_OR_RETURN = "GET_OR_RETURN";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE="DOCUMENT_TYPE";
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
