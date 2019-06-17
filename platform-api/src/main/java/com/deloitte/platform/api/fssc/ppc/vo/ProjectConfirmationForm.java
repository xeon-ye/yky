package com.deloitte.platform.api.fssc.ppc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : ProjectConfirmation新增修改form对象
 * @Modified :
 */
@ApiModel("新增ProjectConfirmation表单")
@Data
public class ProjectConfirmationForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "无ID新增有id修改")
    private Long id;

    @ApiModelProperty(value = "修改时候必传版本")
    private Long version;

    @ApiModelProperty(value = "单据状态,新增默认新增")
    private String documentStatus;

    @ApiModelProperty(value = "收款情况收款状态,新增时默认未收款")
    private String recieveStatus;

    @ApiModelProperty(value = "款项类型",required = true)
    private String paymentType;

    @ApiModelProperty(value = "合同ID")
    private Long contractId;


    @ApiModelProperty(value = "项目ID",required = true)
    private Long projectId;

    @ApiModelProperty(value = "备注摘要")
    private String remark;

    @ApiModelProperty(value = "币种",required = true)
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "收入大类ID",required = true)
    private Long inComeMainTypeId;

    @ApiModelProperty(value = "收入大类CODE",required = true)
    private String inComeMainTypeCode;

    @ApiModelProperty(value = "收入大类名称",required = true)
    private String inComeMainTypeName;

    @ApiModelProperty(value = "款项确认方式",required = true)
    private String paymentConfirmType;

    @ApiModelProperty(value = "合计金额",required = true)
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "是否需要开票",required = true)
    private String isNeedInvoice;

    @ApiModelProperty(value = "合计金额外币")
    private BigDecimal totalAmountOtherCurrency;

    @ApiModelProperty(value = "是否事后补单",required = true)
    private String isAfterPatch;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
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

    @ApiModelProperty(value = "文件列表")
    private List<Long> fileIds;

    @ApiModelProperty(value = "支持性附件数量")
    private Long supportFileNum;
}
