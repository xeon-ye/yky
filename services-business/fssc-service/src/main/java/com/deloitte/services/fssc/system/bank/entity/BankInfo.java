package com.deloitte.services.fssc.system.bank.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 银行信息基础表
 * </p>
 *
 * @author qiliao
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_BANK_INFO")
@ApiModel(value="BankInfo对象", description="银行信息基础表")
public class BankInfo extends Model<BankInfo>  implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "银行ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "银行代码")
    @TableField("BANK_CODE")
    private String bankCode;

    @ApiModelProperty(value = "银行名称")
    @TableField("BANK_NAME")
    private String bankName;

    @ApiModelProperty(value = "银行简称")
    @TableField("BANK_SIMPLE_NAME")
    private String bankSimpleName;

    @ApiModelProperty(value = "分行名称")
    @TableField("BRANCH_BANK_NAME")
    private String branchBankName;

    @ApiModelProperty(value = "联行号")
    @TableField("INTER_BRANCH_NUMBER")
    private String interBranchNumber;

    @ApiModelProperty(value = "银行国际代码")
    @TableField("BANK_INTERNATIONAL_CODE")
    private String bankInternationalCode;
    
    @ApiModelProperty(value = "地址")
    @TableField("ADDRESS")
    private String address;

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

    @ApiModelProperty(value = "预留字段1 是否被关联 Y N")
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

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;
    @ApiModelProperty(value = "状态")
    private String isValid;
    @ApiModelProperty(value = "提交审核状态")
    @TableField("AUDIT_STATUS")
    private String auditStatus;

    public static final String ID = "ID";

    public static final String BANK_CODE = "BANK_CODE";

    public static final String BANK_NAME = "BANK_NAME";

    public static final String BRANCH_BANK_NAME = "BRANCH_BANK_NAME";

    public static final String INTER_BRANCH_NUMBER = "INTER_BRANCH_NUMBER";

    public static final String BANK_INTERNATIONAL_CODE = "BANK_INTERNATIONAL_CODE";

    public static final String ADDRESS = "ADDRESS";

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

    public static final String REMARK = "REMARK";

    public static final String IS_VALID="IS_VALID";

    public static final String BANK_SIMPLE_NAME="BANK_SIMPLE_NAME";
    @Override
    protected Serializable pkVal() {
        return null;
    }

}
