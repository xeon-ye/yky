package com.deloitte.platform.api.fssc.budget.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-30
 * @Description : BudgetPublicAdjust新增修改form对象
 * @Modified :
 */
@ApiModel("新增 对公预算调整 表单")
@Data
public class BudgetPublicAdjustForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID,修改时必填")
    private Long id;

    @ApiModelProperty(value = "版本(数字类型)修改时必填")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "归属部门id")
    private Long deptId;

    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "年度预算金额")
    private BigDecimal annualBudgetAmount;

    @ApiModelProperty(value = "当期预算金额")
    private BigDecimal periodBudgetAmount;

    @ApiModelProperty(value = "当期可用金额")
    private BigDecimal periodUsableAmount;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "申请人")
    private String applyForPerson;

    @ApiModelProperty(value = "申请人名称")
    private String applyForPersonName;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    private String ext5;

    @ApiModelProperty(value = "文件id集合")
    private List<Long> fileIds;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    @NotEmpty
    private String reSubmitType="FIRST_SUBMIT";

    @ApiModelProperty(value = "调整行信息")
    private List<BudgetPublicAdjustLineForm> lineFormList;

}
