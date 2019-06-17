package com.deloitte.platform.api.fssc.labor.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description : LcLaborCostLineForeign新增修改form对象
 * @Modified :
 */
@ApiModel("新增LcLaborCostLineForeign表单")
@Data
public class LcLaborCostLineForeignForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id修改时必填")
    private Long id;

    @ApiModelProperty(value = "创建人姓名", required = true)
    private String createUserName;

    @ApiModelProperty(value = "版本修改时必填")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "支出小类ID", required = true)
    @NotNull
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类CODe", required = true)
    private String subTypeCode;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "收款人姓名", required = true)
    private String recieveUserName;

    @ApiModelProperty(value = "国籍", required = true)
    private String nationality;

    @ApiModelProperty(value = "首次来华时间yyyy-MM-dd HH:mm:ss", required = true)
    private LocalDateTime firstInChina;

    @ApiModelProperty(value = "出生年月yyyy-MM-dd HH:mm:ss", required = true)
    private LocalDateTime birthday;

    @ApiModelProperty(value = "出生地", required = true)
    private String birthAddress;

    @ApiModelProperty(value = "性别", required = true)
    private String gender;

    @ApiModelProperty(value = "有无境内住所", required = true)
    private String hasDomicileInChina;

    @ApiModelProperty(value = "预计离境时间yyyy-MM-dd HH:mm:ss", required = true)
    private LocalDateTime estimatedTimeOfDeparture;

    @ApiModelProperty(value = "任职受雇从业类型", required = true)
    private String typeOfEmployment;

    @ApiModelProperty(value = "任职受雇从业时间yyyy-MM-dd HH:mm:ss", required = true)
    private LocalDateTime typeOfEmploymentTime;

    @ApiModelProperty(value = "收款人ID", required = true)
    @NotNull
    private Long recieveUserId;

    @ApiModelProperty(value = "单位名称", required = true)
    private String unitName;

    @ApiModelProperty(value = "单位code", required = true)
    private String unitCode;

    @ApiModelProperty(value = "单位id", required = true)
    private Long unitId;

    @ApiModelProperty(value = "职称", required = true)
    private String position;

    @ApiModelProperty(value = "证件类型", required = true)
    private String certType;

    @ApiModelProperty(value = "证件号", required = true)
    private String certNum;

    @ApiModelProperty(value = "发放标准", required = true)
    private String distributionStandard;

    @ApiModelProperty(value = "开户行", required = true)
    private String bankName;

    @ApiModelProperty(value = "银行code没有可不填")
    private String bankCode;

    @ApiModelProperty(value = "银行账号", required = true)
    private String bankAccount;

    @ApiModelProperty(value = "联行号", required = true)
    private String interBranchNumber;

    @ApiModelProperty(value = "应发金额", required = true)
    @NotNull
    private BigDecimal shouldGiveAmount;

    @ApiModelProperty(value = "扣税金额", required = true)
    private BigDecimal deductedAmount;

    @ApiModelProperty(value = "实发金额")
    private BigDecimal realGiveAmount;

    @ApiModelProperty(value = "小类名称", required = true)
    private String subTypeName;

    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "行号", required = true)
    private Long lineNumber;

    @ApiModelProperty(value = "是否居民个人")
    @NotEmpty
    private String isSingle;

    @ApiModelProperty(value = "代收人证件号")
    private String takePlaceCertNum;

    @ApiModelProperty(value = "代收人名称")
    private String takePlaceName;

}
