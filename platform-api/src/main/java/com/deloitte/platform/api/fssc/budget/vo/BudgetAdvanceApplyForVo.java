package com.deloitte.platform.api.fssc.budget.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description : BudgetAdvanceApplyFor返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetAdvanceApplyForVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "申请金额")
    private BigDecimal applyForAmount;

    @ApiModelProperty(value = "预算类型")
    private String budgetType;

    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "基本预算科目ID")
    private Long basicBudgetAccountId;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目预算科目ID")
    private Long projectBudgetAccountId;

    @ApiModelProperty(value = "预算保留金额")
    private BigDecimal budgetRemainAmount;

    @ApiModelProperty(value = "可用保留金额")
    private BigDecimal usableRemainAmount;

    @ApiModelProperty(value = "已用保留金额")
    private BigDecimal usedRemainAmount;

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

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

}
