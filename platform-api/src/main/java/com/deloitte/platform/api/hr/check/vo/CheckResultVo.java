package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckResult返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckResultVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "教职工id")
    private String userId;

    @ApiModelProperty(value = "考核规则id")
    private String checkRuleId;

    @ApiModelProperty(value = "业绩通知id")
    private String achEvaluateNotifyId;

    @ApiModelProperty(value = "系统分数")
    private Double systemScore;

    @ApiModelProperty(value = "排名")
    private Long ranking;

    @ApiModelProperty(value = "系统等级")
    private String systemLevel;

    @ApiModelProperty(value = "部门负责人评语")
    private String departLeaderEvaluate;

    @ApiModelProperty(value = "绩效改进计划")
    private String achBetterPlan;

    @ApiModelProperty(value = "个人绩效考核ID")
    private String checkAchUserId;

    @ApiModelProperty(value = "评语填写日期")
    private LocalDate departLeaderEvaluateTime;

    @ApiModelProperty(value = "调整等级")
    private String adjustLevel;

    @ApiModelProperty(value = "调整原因")
    private String adjustReason;

    @ApiModelProperty(value = "考核领导小组审核意见")
    private String checkGroupLeaderEvaluate;

    @ApiModelProperty(value = "考核关系id")
    private String checkRelationId;

    @ApiModelProperty(value = "领导小组填写日期")
    private LocalDate checkGroupLeaderTime;

    @ApiModelProperty(value = "单位负责人意见")
    private String unitLeaderEvaluate;

    @ApiModelProperty(value = "单位负责人填写日期")
    private LocalDate unitLeaderEvaluateTime;

    @ApiModelProperty(value = "是否发布")
    private String isIssue;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}
