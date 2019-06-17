package com.deloitte.platform.api.hr.check.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**

 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :  CheckResult查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckResultQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userId;
    private String achEvaluateNotifyId;
    private Double systemScore;
    private String checkRuleId;
    private Long ranking;
    private String systemLevel;
    private String departLeaderEvaluate;
    private String achBetterPlan;
    private String checkAchUserId;
    private LocalDate departLeaderEvaluateTime;
    private String adjustLevel;
    private String adjustReason;
    private String checkGroupLeaderEvaluate;
    private String checkRelationId;
    private LocalDate checkGroupLeaderTime;
    private String unitLeaderEvaluate;
    private LocalDate unitLeaderEvaluateTime;
    private String isIssue;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
