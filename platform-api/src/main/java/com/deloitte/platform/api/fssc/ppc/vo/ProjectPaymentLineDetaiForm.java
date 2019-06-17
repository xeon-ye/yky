package com.deloitte.platform.api.fssc.ppc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : ProjectPaymentLineDetai新增修改form对象
 * @Modified :
 */
@ApiModel("新增ProjectPaymentLineDetai表单")
@Data
public class ProjectPaymentLineDetaiForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "无ID新增有id修改")
    private Long id;

    @ApiModelProperty(value = "创建人姓名申请人",required = true)
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据ID后台填充")
    private Long documentId;

    @ApiModelProperty(value = "单据类型后台填充")
    private String documentType;

    @ApiModelProperty(value = "款项收入小类ID",required = true)
    private Long inComeSubTypeId;

    @ApiModelProperty(value = "款项收入小类科目CODE",required = true)
    private String inComeSubSubjectCode;

    @ApiModelProperty(value = "款项收入小类CODE",required = true)
    private String inComeSubTypeCode;

    @ApiModelProperty(value = "款项收入小类名称",required = true)
    private String inComeSubTypeName;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预计到款金额",required = true)
    private BigDecimal expectedAmount;

    @ApiModelProperty(value = "预计到款时间",required = true)
    private LocalDateTime expectedRecieveTime;

    @ApiModelProperty(value = "对方单位信息ID",required = true)
    private Long adverseUnitId;

    @ApiModelProperty(value = "对方单位信息CODE",required = true)
    private String adverseUnitCode;

    @ApiModelProperty(value = "对方单位信息名称",required = true)
    private String adverseUnitName;

    @ApiModelProperty(value = "资金来源",required = true)
    private String capitalSource;

    @ApiModelProperty(value = "社会统一信用代码",required = true)
    private String adverseCommonCreditCode;

    @ApiModelProperty(value = "对方银行账户名称",required = true)
    private String adverseBankName;

    @ApiModelProperty(value = "对方银行账号",required = true)
    private String adverseBankAccount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;

}
