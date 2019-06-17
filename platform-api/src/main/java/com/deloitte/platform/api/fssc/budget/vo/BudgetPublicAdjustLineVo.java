package com.deloitte.platform.api.fssc.budget.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-30
 * @Description : BudgetPublicAdjustLine返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetPublicAdjustLineVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "预算科目ID")
    private Long budgetAccountId;

    @ApiModelProperty(value = "预算科目编码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预算科目名称")
    private String budgetAccountName;

    @ApiModelProperty(value = "当期预算金额")
    private BigDecimal periodBudgetAmount;

    @ApiModelProperty(value = "当期已用金额")
    private BigDecimal periodUsedAmount;

    @ApiModelProperty(value = "当期可用金额")
    private BigDecimal periodUsableAmount;

    @ApiModelProperty(value = "调整金额")
    private BigDecimal adjustAmount;

    @ApiModelProperty(value = "调整后当期预算金额")
    private BigDecimal adjustedPeriodBudgetAmount;

    @ApiModelProperty(value = "调整后当期可用金额")
    private BigDecimal adjustedPeriodUsableAmount;

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

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

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

    @ApiModelProperty(value = "版本")
    private Double version;

}
