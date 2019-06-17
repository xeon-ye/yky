package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckResult返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckResultComputeVo extends BaseVo {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教职工id")
    private Long userId;

    @ApiModelProperty(value = "业绩测评通知id")
    private Long checkEvaluateNotifyId;

    @ApiModelProperty(value = "考核关系Id")
    private Long checkRelationId;

    @ApiModelProperty(value = "系统分数")
    private Double score;

    @ApiModelProperty(value = "个人绩效考核ID")
    private Long checkAchUserId;

}
