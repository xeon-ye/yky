package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : yangyq
 * @Date : Create in 2019-04-17
 * @Description :   FinanceInfo查询from对象
 * @Modified :
 */
@ApiModel("FinanceInfo查询表单")
@Data
public class FinanceInfoQueryForm extends BaseQueryForm<FinanceInfoQueryParam>  {


    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "我方签约主体编号")
    private String ourContractEntityCode;

    @ApiModelProperty(value = "我方签约主体")
    private String ourContractEntity;

    @ApiModelProperty(value = "对方签约主体编号")
    private String otherContractEntityCode;

    @ApiModelProperty(value = "对方签约主体")
    private String otherContractEntity;

    @ApiModelProperty(value = "对方开户银行编码")
    private String otherBankCode;

    @ApiModelProperty(value = "对方开户银行")
    private String otherBank;

    @ApiModelProperty(value = "对方账户名称")
    private String otherAccountName;

    @ApiModelProperty(value = "对方银行账号")
    private String otherAccount;

    @ApiModelProperty(value = "约定付款批次")
    private String appointPayStage;

    @ApiModelProperty(value = "约定付款条件")
    private String appointPayCondition;

    @ApiModelProperty(value = "约定付款比例")
    private Long appointPayRate;

    @ApiModelProperty(value = "约定付款金额")
    private Double appointPayAmount;

    @ApiModelProperty(value = "实际付款金额")
    private Double actPayRate;

    @ApiModelProperty(value = "计划付款时间")
    private LocalDateTime planPayTime;

    @ApiModelProperty(value = "实际付款时间")
    private LocalDateTime actPayTime;

    @ApiModelProperty(value = "实际付款是否手工填报")
    private String isManual;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "合同编号")
    private Long contractId;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private Long spareField5;

    @ApiModelProperty(value = "约定收款批次")
    private String appointIncomeStage;

    @ApiModelProperty(value = "约定收款条件")
    private String appointIncomeCondition;

    @ApiModelProperty(value = "约定收款比例")
    private Long appointIncomeRate;

    @ApiModelProperty(value = "约定收款金额")
    private Double appointIncomeAmount;

    @ApiModelProperty(value = "实际收款金额")
    private Double actIncomeRate;

    @ApiModelProperty(value = "计划收款时间")
    private LocalDateTime planIncomeTime;

    @ApiModelProperty(value = "实际收款时间")
    private LocalDateTime actIncomeTime;

    @ApiModelProperty(value = "实际收款是否手工填报")
    private String isManualIncome;

    @ApiModelProperty(value = "合同类型（收入类，支出类等）")
    private String contractType;

    @ApiModelProperty(value = "判断收付款标记(1付款；2收款)")
    private String type;
}