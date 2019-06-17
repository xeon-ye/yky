package com.deloitte.platform.api.fssc.budget.vo;

import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description : BudgetProjectBudget返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetProjectBudgetVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "单位ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long orgUnitId;

    @ApiModelProperty(value = "单位编码")
    private String orgUnitCode;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "预算项目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long budgetProjectId;

    @ApiModelProperty(value = "项目")
    private String projectCode;

    @ApiModelProperty(value = "课题")
    private String subjectCode;

    @ApiModelProperty(value = "任务")
    private String taskCode;

    @ApiModelProperty(value = "状态")
    private String projectStatus;

    @ApiModelProperty(value = "预算科目")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预算科目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long budgetAccountId;

    @ApiModelProperty(value = "预算科目名称")
    private String budgetAccountName;

    @ApiModelProperty(value = "预算期间")
    private String budgetPeriod;

    @ApiModelProperty(value = "预算年度")
    private String budgetAnnual;

    @ApiModelProperty(value = "预算金额")
    private BigDecimal budgetAmount;

    @ApiModelProperty(value = "预算保留金额")
    private BigDecimal budgetRemainAmount;

    @ApiModelProperty(value = "预算占用金额")
    private BigDecimal budgetOccupyAmount;

    @ApiModelProperty(value = "预算可用金额")
    private BigDecimal budgetUsableAmount;

    @ApiModelProperty(value = "预算已使用金额")
    private BigDecimal budgetUsedAmount;

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

    @ApiModelProperty(value = "合计标志",notes = "合计标志位Y,是按照年合计的预算数据,才是实际使用的预算控制的数据")
    private String totalFlag;

    @ApiModelProperty(value = "科研立项/变更后的预算金额,预算导入不会改变此金额")
    private BigDecimal budgetSrmpsAmount;

}
