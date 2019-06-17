package com.deloitte.platform.api.fssc.borrow.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-06
 * @Description : BmBorrowBank新增修改form对象
 * @Modified :
 */
@ApiModel("新增BmBorrowBank表单")
@Data
public class BmBorrowBankForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id修改时必填")
    private Long id;
    @ApiModelProperty(value = "创建人姓名",required = true)
    private String createUserName;

    @ApiModelProperty(value = "行号",required = true)
    private Long lineNumber;

    @ApiModelProperty(value = "版本修改时必填")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "付款状态默认值",required = true)
    private String payStatus;
    @ApiModelProperty(value = "付款时间不填")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "借款还款原币金额",required = true)
    @NotNull
    private BigDecimal borrowAmount;

    @ApiModelProperty(value = "交易金额")
    private BigDecimal transactionAmount;

    @ApiModelProperty(value = "类型工资卡SALARY还是公务卡BUSINESS",required = true)
    private String getOrReturn;

    @ApiModelProperty(value = "借款单据ID")
    private Long documentId;

    @ApiModelProperty(value = "工号",required = true)
    private String empNo;

    @ApiModelProperty(value = "银行名称",required = true)
    private String bankName;
    @ApiModelProperty(value = "银行账户",required = true)
    private String bankAccount;
    @ApiModelProperty(value = "联行号",required = true)
    private String interBranchNumber;
    @ApiModelProperty(value = "关联的表名")
    private String documentType;
    @ApiModelProperty(value = "员工姓名",required = true)
    @NotBlank
    private String empName;
    @ApiModelProperty(value = "员工ID",required = true)
    private Long empId;
}
