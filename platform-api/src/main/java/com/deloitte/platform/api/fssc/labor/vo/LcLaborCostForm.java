package com.deloitte.platform.api.fssc.labor.vo;

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
 * @Date : Create in 2019-03-25
 * @Description : LcLaborCost新增修改form对象
 * @Modified :
 */
@ApiModel("新增劳务费表单")
@Data
public class LcLaborCostForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目预算会计科目")
    private String projectBudgetAccountCode;

    @ApiModelProperty(value = "id数字,不填为新增,填了为修改")
    private Long id;

    @ApiModelProperty(value = "创建人姓名原则上必填,默认登陆人")
    private String createUserName;

    @ApiModelProperty(value = "版本修改时必填")
    private Long version;

    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;

    @ApiModelProperty(value = "单据编号新增时不填")
    private String documentNum;

    @ApiModelProperty(value = "单据状态,新增时默认新建")
    private String documentStatus;

    @ApiModelProperty(value = "付款状态,新增时默认有值")
    private String payStatus;

    @ApiModelProperty(value = "归属部门code",required = true)
    private String deptCode;

    @ApiModelProperty(value = "归属部门ID",required = true)
    private Long deptId;

    @ApiModelProperty(value = "归属单位名称",required = true)
    private String unitName;

    @ApiModelProperty(value = "归属部门名称",required = true)
    private String deptName;

    @ApiModelProperty(value = "付款方式",required = true)
    private String paymentType;

    @ApiModelProperty(value = "项目名称",required = true)
    private String projectName;

    @ApiModelProperty(value = "项目code",required = true)
    private String projectCode;

    @ApiModelProperty(value = "项目承担单位id",required = true)
    private Long projectUnitId;

    @ApiModelProperty(value = "项目承担单位code",required = true)
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位名称",required = true)
    private String projectUnitName;

    @ApiModelProperty(value = "项目ID",required = true)
    private Long projectId;

    @ApiModelProperty(value = "支出大类code",required = true)
    private String mainTypeCode;

    @ApiModelProperty(value = "大类名称",required = true)
    private String mainTypeName;

    @ApiModelProperty(value = "支出大类ID",required = true)
    private Long mainTypeId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "币种",required = true)
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "支持性附件数量",required = true)
    private Long attachCount;

    @ApiModelProperty(value = "发放人数",required = true)
    private BigDecimal givePeopleCount;

    @ApiModelProperty(value = "应发金额合计")
    private BigDecimal shouldGiveTotalAmount;

    @ApiModelProperty(value = "实发金额合计")
    private BigDecimal realGiveTotalAmount;

    @ApiModelProperty(value = "扣税金额合计")
    private BigDecimal deductedTotalAmount;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    @NotEmpty
    private String reSubmitType="FIRST_SUBMIT";

    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "发放明细中国籍")
    @Valid
    private List<LcLaborCostLineChinaForm> laborCostLineChinaForms;

    @ApiModelProperty(value = "发放明细外国籍")
    @Valid
    private List<LcLaborCostLineForeignForm> laborCostLineForeignForms;

    @ApiModelProperty(value = "对私付款清单")
    @Valid
    private List<GePrivatePaymentListForm> privatePaymentListForms;

    @ApiModelProperty(value = "是否预算检查超过80%")
    private String budgetWarningCheck;

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

    @ApiModelProperty(value = "未支付金额")
    private BigDecimal noPaidAmount;

    @ApiModelProperty(value = "文件id集合")
    private List<Long> fileIds;
}
