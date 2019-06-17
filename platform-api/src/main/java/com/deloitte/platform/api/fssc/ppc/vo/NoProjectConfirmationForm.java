package com.deloitte.platform.api.fssc.ppc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : NoProjectConfirmation新增修改form对象
 * @Modified :
 */
@ApiModel("新增NoProjectConfirmation表单")
@Data
public class NoProjectConfirmationForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "收款情况收款状态")
    private String recieveStatus;

    @ApiModelProperty(value = "款项类型")
    private String paymentType;

    @ApiModelProperty(value = "归属单位code")
    private String unitCode;

    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "管理部门code")
    private String deptCode;

    @ApiModelProperty(value = "管理部门ID")
    private Long deptId;

    @ApiModelProperty(value = "管理部门名称")
    private String deptName;

    @ApiModelProperty(value = "合同编号")
    private String contractNum;

    @ApiModelProperty(value = "备注摘要")
    private String remark;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "收入大类ID")
    private Long inComeMainTypeId;

    @ApiModelProperty(value = "收入大类CODE")
    private String inComeMainTypeCode;

    @ApiModelProperty(value = "收入大类名称")
    private String inComeMainTypeName;

    @ApiModelProperty(value = "款项确认方式")
    private String paymentConfirmType;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "是否需要开票")
    private String isNeedInvoice;

    @ApiModelProperty(value = "合计金额外币")
    private BigDecimal totalAmountOtherCurrency;

    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    @NotEmpty
    private String reSubmitType;

    @ApiModelProperty(value = "项目款项确认单款项信息",required = true)
    @Valid
    private List<ProjectPaymentLineDetaiForm> projectPaymentLineDetaiForms;

    @ApiModelProperty(value = "收款明细",required = true)
    @Valid
    private List<ProjectRecieveDetailForm> projectRecieveDetailForms;

    @ApiModelProperty(value = "开票明细",required = true)
    @Valid
    private List<ProjectBilingInfoForm> projectBilingInfoForms;

    @ApiModelProperty(value = "合同明细",required = true)
    @Valid
    private List<ContractInformationForm> contractInformationForms;

}
