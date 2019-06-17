package com.deloitte.platform.api.srpmp.project.budget.vo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.BudgetDetailVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectBudgetDetail返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SrpmsProjectBudgetDetailVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "预算类型CODE")
    private String budgetCategory;

    @ApiModelProperty(value = "预算年度")
    private Integer budgetYear;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "序号")
    private Integer serial;

    @ApiModelProperty(value = "参与任务的单位名称")
    private String taskDeptName;

    @ApiModelProperty(value = "任务负责人ID")
    private Long taskLeadPersonId;

    @ApiModelProperty(value = "任务参与人员ID")
    private Long taskJoinPersonId;

    @ApiModelProperty(value = "预算金额")
    private Double budgetAmount;

    @ApiModelProperty(value = "单位名称")
    private String deptName;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;

    @ApiModelProperty(value = "承担单位类型")
    private String deptCategory;

    @ApiModelProperty(value = "单位承担的任务分工")
    private String deptTaskContent;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "预算明细")
    private JSONArray budgetDetail;

    @ApiModelProperty(value = "预算明细")
    private List<BudgetDetailVo> budgetDetailVo;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}
