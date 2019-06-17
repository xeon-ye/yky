package com.deloitte.platform.api.fssc.advance.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description :   BmContactDetail查询from对象
 * @Modified :
 */
@ApiModel("BmContactDetail查询表单")
@Data
public class ContactDetailQueryForm extends BaseQueryForm<ContactDetailQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String ex1;

    @ApiModelProperty(value = "${field.comment}")
    private String ex2;

    @ApiModelProperty(value = "${field.comment}")
    private String ex3;

    @ApiModelProperty(value = "${field.comment}")
    private String ex4;

    @ApiModelProperty(value = "${field.comment}")
    private String ex5;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "支出小类ID")
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类编码")
    private String subTypeCode;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "对公预付款ID")
    private Long documentId;

    @ApiModelProperty(value = "支出大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "支出小类名称")
    private String subTypeName;

    @ApiModelProperty(value = "合同名称")
    private String contactName;

    @ApiModelProperty(value = "合同id")
    private Long contactNumberId;

    @ApiModelProperty(value = "合同编号")
    private String contactNumber;

    @ApiModelProperty(value = "对方签约主体")
    private String subjectSuperficil;

    @ApiModelProperty(value = "约定付款批次")
    private String agreedPaymentLot;

    @ApiModelProperty(value = "约定付款金额")
    private BigDecimal agreedPaymentAmount;

    @ApiModelProperty(value = "约定比例")
    private  String agreedPropertions;

    @ApiModelProperty(value = "计划还款时间")
    private LocalDateTime planPaymentTime;

    @ApiModelProperty(value = "关联的表名")
    private String documentType;

    @ApiModelProperty(value = "发票号")
    private String invoiceNumber;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxMmount;

    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;

    @ApiModelProperty(value="行号")
    private Long lineNumber;

    @ApiModelProperty(value="实际付款金额")
    private BigDecimal actualPlayAmount;

    @ApiModelProperty(value = "我方签约主体")
    private String ourSubjectMainstay;

    @ApiModelProperty(value = "履行部门")
    private String  performangeDepartment;

    @ApiModelProperty(value = "履行人")
    private String  executor;

    @ApiModelProperty(value = "已核销金额")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    @ApiModelProperty(value = "履行计划ID")
    private Long travelPlanId;

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;
}
