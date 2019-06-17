package com.deloitte.platform.api.fssc.rep.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description : RecievePayment新增修改form对象
 * @Modified :
 */
@ApiModel("新增RecievePayment表单")
@Data
public class RecievePaymentForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "收款单id")
    private Long id;

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

    @ApiModelProperty(value = "来源期间")
    private String sourceDuring;

    @ApiModelProperty(value = "收款方式")
    private String recievePaymentType;

    @ApiModelProperty(value = "款项类型")
    private String paymentType;

    @ApiModelProperty(value = "收入大类ID")
    private Long inComeMainTypeId;

    @ApiModelProperty(value = "收入大类CODE")
    private String inComeMainTypeCode;

    @ApiModelProperty(value = "收入大类名称")
    private String inComeMainTypeName;


    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目code")
    private String projectCode;

    @ApiModelProperty(value = "项目承担单位id")
    private Long projectUnitId;

    @ApiModelProperty(value = "项目承担单位code")
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "备注摘要")
    private String remark;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "是否需要开票")
    private String isNeedInvoice;

    @ApiModelProperty(value = "合计金额外币")
    private BigDecimal totalAmountOtherCurrency;

    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "文件id集合")
    private List<Long> fileIds;

    @ApiModelProperty(value = "提交类型保存时候随便传 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    @NotEmpty
    private String reSubmitType="FIRST_SUBMIT";

    @ApiModelProperty(value = "收款信息")
    private List<RecieveLineMsgForm>  recieveLineMsgForms;

    @ApiModelProperty(value = "收入信息")
    private List<RecieveIncomeMsgForm> recieveIncomeMsgForms;

    @ApiModelProperty(value = "认领范围")
    private List<RecieveClaimAreaForm> recieveClaimAreaForms;
    @ApiModelProperty(value = "支持性附件数量")
    private Long attachCount;
    @ApiModelProperty(value = "收入结转方式")
    private String paymentConfirmType;

    @ApiModelProperty(value = "抄送人工号 备注")
    private List<Map<String,String>> copyEmpNos;


    @ApiModelProperty(value = "预留字段1 是否推送")
    private String ex1;

}
