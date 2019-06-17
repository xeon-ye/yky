package com.deloitte.platform.api.fssc.general.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description : GeExpenseBorrowPrepay新增修改form对象
 * @Modified :
 */
@ApiModel("新增GeExpenseBorrowPrepay表单")
@Data
public class GeExpenseBorrowPrepayForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "修改时必填")
    private Long id;

    @ApiModelProperty(value = "创建人姓名",required = true)
    private String createUserName;

    @ApiModelProperty(value = "版本修改时必填")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "单据类型名称")
    private String documentTypeName;

    @ApiModelProperty(value = "支出大类ID",required = true)
    private Long mainTypeId;

    @ApiModelProperty(value = "支出大类code",required = true)
    private String mainTypeCode;

    @ApiModelProperty(value = "支出大类名称",required = true)
    private String mainTypeName;

    @ApiModelProperty(value = "核销日期")
    private LocalDateTime verficationDate;

    @ApiModelProperty(value = "借款或预付金额",required = true)
    private BigDecimal bpAmount;

    @ApiModelProperty(value = "已核销金额")
    private BigDecimal verficatedAmount;

    @ApiModelProperty(value = "本次核销金额",required = true)
    @NotNull
    private BigDecimal currentVerAmount;

    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "核销说明")
    private String verficationRemark;

    @ApiModelProperty(value = "支出小类ID",required = true)
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类code",required = true)
    private String subTypeCode;

    @ApiModelProperty(value = "支出小类名称",required = true)
    private String subTypeName;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "关联的单据ID 借款or对公付款",required = true)
    private Long borrowOrPrepayId;

    @ApiModelProperty(value = "关联的单据类型 借款or对公付款",required = true)
    private String connectDocumentType;

    @ApiModelProperty(value = "关联的单据号 借款or对公付款",required = true)
    private String connectDocumentNum;

    @ApiModelProperty(value = "行号",required = true)
    private Long lineNumber;

    @ApiModelProperty(value = "对方签约主体")
    private String subjectSuperficil;

    @ApiModelProperty(value = "约定付款批次")
    private String agreedPaymentLot;

    @ApiModelProperty(value = "履行计划ID")
    private Long travelPlanId;

    @ApiModelProperty(value = "付款方式")
    @NotBlank
    private String paymentType;

    @ApiModelProperty(value = "付款单银行账户财务会计科目")
    private String paymentAccountCode;

    @ApiModelProperty(value = "付款银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "付款银行账户")
    private String bankType;
}
