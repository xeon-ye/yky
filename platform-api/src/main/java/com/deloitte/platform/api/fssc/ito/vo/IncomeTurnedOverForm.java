package com.deloitte.platform.api.fssc.ito.vo;
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
 * @Date : Create in 2019-04-15
 * @Description : IncomeTurnedOver新增修改form对象
 * @Modified :
 */
@ApiModel("新增IncomeTurnedOver表单")
@Data
public class IncomeTurnedOverForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;



    @ApiModelProperty(value = "归属部门ID")
    private Long deptId;

    @ApiModelProperty(value = "收入大类ID")
    private Long incomeTypeId;

    @ApiModelProperty(value = "是否同意承诺书")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    private String remark;



    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "收入大类名称")
    private String incomeTypeName;

    @ApiModelProperty(value = "预留字段1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    private String ext5;

    @ApiModelProperty(value = "预留字段6")
    private String ext6;

    @ApiModelProperty(value = "预留字段7")
    private String ext7;

    @ApiModelProperty(value = "预留字段8")
    private String ext8;

    @ApiModelProperty(value = "预留字段9")
    private String ext9;

    @ApiModelProperty(value = "预留字段10")
    private String ext10;

    @ApiModelProperty(value = "预留字段11")
    private String ext11;

    @ApiModelProperty(value = "预留字段12")
    private String ext12;

    @ApiModelProperty(value = "预留字段13")
    private String ext13;

    @ApiModelProperty(value = "预留字段14")
    private String ext14;

    @ApiModelProperty(value = "预留字段15")
    private String ext15;


    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    @ApiModelProperty(value = "收入大类编码")
    private String incomeTypeCode;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "缴款码")
    private String paymentCode;

    @ApiModelProperty(value = "支持附件数量")
    private Long supportFileNum;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    @NotEmpty
    private String reSubmitType;

    @ApiModelProperty(value="是否附件驳回")
    private String isFileReject;

    @ApiModelProperty(value="驳回原因")
    private String rejectReason;

    @ApiModelProperty(value = "会计科目代码")
    private String bankSubjectCode;

    @ApiModelProperty(value = "预算会计科目代码")
    private String budgetBankSubjectCode;
    @ApiModelProperty(value = "银行ID")
    private Long bankId;

    @Valid
    @ApiModelProperty(value = "文件id集合")
    private List<Long> fileIds;

    @ApiModelProperty(value = "款项明细集合")
    private List<DetailsOfFundsForm> detailsOfFundsForms;

}
