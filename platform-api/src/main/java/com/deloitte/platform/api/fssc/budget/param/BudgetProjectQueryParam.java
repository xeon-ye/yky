package com.deloitte.platform.api.fssc.budget.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description :  BudgetProject查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetProjectQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目状态")
    private String projectStatus;

    @ApiModelProperty(value = "单位")
    private String responsibleUnitName;

    @ApiModelProperty(value = "依托单位id")
    private Long unitId;

    @ApiModelProperty(value = "财务编码")
    private String fsscCode;

    @ApiModelProperty(value = "计划号")
    private String planNum;

    @ApiModelProperty(value = "关联号")
    private String connectNum;

    @ApiModelProperty(value = "负责人编码")
    private String projectDuty;

    @ApiModelProperty(value = "金额开始")
    private BigDecimal moneyStart;

    @ApiModelProperty(value = "金额结束")
    private BigDecimal moneyEnd;

    @ApiModelProperty(value = "时间范围开始")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "时间范围结束")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "父项目ID")
    private Long parentId;

    @ApiModelProperty(value = "财务-会计科目")
    private String accountCode;

    private int currentPage;

    private int pageSize;
}
