package com.deloitte.platform.api.fssc.poi.vo;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-05-13
 * @Description : PepaymentOrderInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增PepaymentOrderInfo表单")
@Data
public class PepaymentOrderInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;


    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "是否同意承诺书")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "${field.comment}")
    private String projectName;

    @ApiModelProperty(value = "${field.comment}")
    private String ext1;

    @ApiModelProperty(value = "${field.comment}")
    private String ext2;

    @ApiModelProperty(value = "${field.comment}")
    private String ext3;

    @ApiModelProperty(value = "${field.comment}")
    private String ext4;

    @ApiModelProperty(value = "${field.comment}")
    private String ext5;

    @ApiModelProperty(value = "${field.comment}")
    private String ext6;

    @ApiModelProperty(value = "${field.comment}")
    private String ext7;

    @ApiModelProperty(value = "${field.comment}")
    private String ext8;

    @ApiModelProperty(value = "${field.comment}")
    private String ext9;

    @ApiModelProperty(value = "${field.comment}")
    private String ext10;

    @ApiModelProperty(value = "${field.comment}")
    private String ext11;

    @ApiModelProperty(value = "${field.comment}")
    private String ext12;

    @ApiModelProperty(value = "${field.comment}")
    private String ext13;

    @ApiModelProperty(value = "${field.comment}")
    private String ext14;

    @ApiModelProperty(value = "${field.comment}")
    private String ext15;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "还款方式")
    private String repaymentType;

    @ApiModelProperty(value = "还款金额合计")
    private BigDecimal reAmountTatol;

    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "${field.comment}")
    private Long orgId;

    @ApiModelProperty(value = "${field.comment}")
    private String orgPath;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    @NotEmpty
    private String reSubmitType="FIRST_SUBMIT";

    @ApiModelProperty(value = "关联借款单对公预付款单")
    private List<GeExpenseBorrowPrepayForm> borrowPrepayFormList;

    @ApiModelProperty(value = "是否预算检查超过80%")
    private String budgetWarningCheck;


}
