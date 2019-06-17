package com.deloitte.platform.api.fssc.labor.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description : LcLaborCostLineChina新增修改form对象
 * @Modified :
 */
@ApiModel("新增LcLaborCostLineChina表单")
@Data
public class LcLaborCostLineChinaForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id修改时必填")
    private Long id;

    @ApiModelProperty(value = "创建人姓名",required = true)
    private String createUserName;

    @ApiModelProperty(value = "版本修改时必填")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "支出小类ID",required = true)
    @NotNull
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类CODe",required = true)
    private String subTypeCode;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "收款人姓名",required = true)
    private String recieveUserName;

    @ApiModelProperty(value = "收款人ID没有可不填")
    @NotNull
    private Long recieveUserId;

    @ApiModelProperty(value = "单位名称",required = true)
    private String unitName;

    @ApiModelProperty(value = "单位code",required = true)
    private String unitCode;

    @ApiModelProperty(value = "单位id",required = true)
    private Long unitId;

    @ApiModelProperty(value = "职称",required = true)
    private String position;

    @ApiModelProperty(value = "证件类型",required = true)
    private String certType;

    @ApiModelProperty(value = "证件号",required = true)
    private String certNum;

    @ApiModelProperty(value = "发放标准",required = true)
    private String distributionStandard;

    @ApiModelProperty(value = "开户行",required = true)
    private String bankName;

    @ApiModelProperty(value = "银行code没有可不填")
    private String bankCode;

    @ApiModelProperty(value = "银行账号",required = true)
    private String bankAccount;

    @ApiModelProperty(value = "联行号",required = true)
    private String interBranchNumber;

    @ApiModelProperty(value = "应发金额",required = true)
    @NotNull
    private BigDecimal shouldGiveAmount;

    @ApiModelProperty(value = "扣税金额",required = true)
    private BigDecimal deductedAmount;

    @ApiModelProperty(value = "实发金额")
    private BigDecimal realGiveAmount;

    @ApiModelProperty(value = "小类名称",required = true)
    private String subTypeName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "行号",required = true)
    private Long lineNumber;

}
