package com.deloitte.platform.api.fssc.borrow.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-06
 * @Description :   BmBorrowBank查询from对象
 * @Modified :
 */
@ApiModel("BmBorrowBank查询表单")
@Data
public class BmBorrowBankQueryForm extends BaseQueryForm<BmBorrowBankQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "预留字段1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2")
    private String ex2;

    @ApiModelProperty(value = "预留字段3")
    private String ex3;

    @ApiModelProperty(value = "预留字段4")
    private String ex4;

    @ApiModelProperty(value = "预留字段5")
    private String ex5;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;
    @ApiModelProperty(value = "付款时间")
    private LocalDateTime payTime;
    @ApiModelProperty(value = "借款原币金额")
    private BigDecimal borrowAmount;

    @ApiModelProperty(value = "类型收款还是还款")
    private String getOrReturn;

    @ApiModelProperty(value = "借款单据ID")
    private Long documentId;


    @ApiModelProperty(value = "员工姓名")
    private String empName;
    @ApiModelProperty(value = "工号")
    private String empNo;
    private Long empId;
    @ApiModelProperty(value = "银行名称")
    private String bankName;
    @ApiModelProperty(value = "银行账户")
    private String bankAccount;
    @ApiModelProperty(value = "联行号")
    private String interBranchNumber;
    @ApiModelProperty(value = "关联的表名")
    private String documentType;
}
