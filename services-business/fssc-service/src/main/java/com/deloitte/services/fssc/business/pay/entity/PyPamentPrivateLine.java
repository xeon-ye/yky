package com.deloitte.services.fssc.business.pay.entity;

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
 * 付款单对私付款
 * </p>
 *
 * @author qiliao
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PY_PAMENT_PRIVATE_LINE")
@ApiModel(value="PyPamentPrivateLine对象", description="付款单对私付款")
public class PyPamentPrivateLine extends Model<PyPamentPrivateLine> {

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

    @ApiModelProperty(value = "付款单ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "付款单表名")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "关联的单据编号")
    @TableField("CONNECT_DOCUMENT_NUM")
    private String connectDocumentNum;

    @ApiModelProperty(value = "关联的单据类型")
    @TableField("CONNECT_DOCUMENT_TYPE")
    private String connectDocumentType;

    @ApiModelProperty(value = "付款金额")
    @TableField("PAY_AMOUNT")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "收款人")
    @TableField("RECIEVE_EMP_NAME")
    private String recieveEmpName;

    @ApiModelProperty(value = "工号/证件号")
    @TableField("EMP_NO")
    private String empNo;

    @ApiModelProperty(value = "汇入银行账号")
    @TableField("BANK_ACCOUNT")
    private String bankAccount;

    @ApiModelProperty(value = "汇入银行名称")
    @TableField("BANK_NAME")
    private String bankName;

    @ApiModelProperty(value = "汇入分行")
    @TableField("BRANCH_BANK_NAME")
    private String branchBankName;

    @ApiModelProperty(value = "联行号")
    @TableField("INTER_BRANCH_NUMBER")
    private String interBranchNumber;

    @ApiModelProperty(value = "国际银行代码")
    @TableField("BANK_INTERNATIONAL_CODE")
    private String bankInternationalCode;

    @ApiModelProperty(value = "支付状态")
    @TableField("PAY_STATUS")
    private String payStatus;

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

    public static final String CONNECT_DOCUMENT_NUM = "CONNECT_DOCUMENT_NUM";

    public static final String CONNECT_DOCUMENT_TYPE = "CONNECT_DOCUMENT_TYPE";

    public static final String PAY_AMOUNT = "PAY_AMOUNT";

    public static final String RECIEVE_EMP_NAME = "RECIEVE_EMP_NAME";

    public static final String EMP_NO = "EMP_NO";

    public static final String BANK_ACCOUNT = "BANK_ACCOUNT";

    public static final String BANK_NAME = "BANK_NAME";

    public static final String BRANCH_BANK_NAME = "BRANCH_BANK_NAME";

    public static final String INTER_BRANCH_NUMBER = "INTER_BRANCH_NUMBER";

    public static final String BANK_INTERNATIONAL_CODE = "BANK_INTERNATIONAL_CODE";

    public static final String PAY_STATUS = "PAY_STATUS";

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
