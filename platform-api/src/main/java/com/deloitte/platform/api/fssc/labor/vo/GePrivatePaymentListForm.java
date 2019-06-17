package com.deloitte.platform.api.fssc.labor.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description : GePrivatePaymentList新增修改form对象
 * @Modified :
 */
@ApiModel("新增GePrivatePaymentList表单")
@Data
public class GePrivatePaymentListForm extends BaseForm {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id修改时必填")
    private Long id;

    @ApiModelProperty(value = "创建人姓名",required = true)
    private String createUserName;

    @ApiModelProperty(value = "版本修改时必填")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型表名")
    private String documentType;

    @ApiModelProperty(value = "付款金额",required = true)
    private BigDecimal payAmount;

    @ApiModelProperty(value = "收款人",required = true)
    private String recieveUserName;

    @ApiModelProperty(value = "收款人ID",required = true)
    private Long recieveUserId;

    @ApiModelProperty(value = "证件类型",required = true)
    private String certType;

    @ApiModelProperty(value = "证件号码",required = true)
    private String certNum;

    @ApiModelProperty(value = "银行代码",required = true)
    private String bankCode;

    @ApiModelProperty(value = "银行ID")
    private Long bankId;

    @ApiModelProperty(value = "开户行",required = true)
    private String bankBame;

    @ApiModelProperty(value = "银行账号",required = true)
    private String banAccount;

    @ApiModelProperty(value = "联行号",required = true)
    private String interBranchNumber;

    @ApiModelProperty(value = "支付时间不填")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "支付状态")
    private String payStatus;

    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "行号",required = true)
    private Long lineNumber;

}
