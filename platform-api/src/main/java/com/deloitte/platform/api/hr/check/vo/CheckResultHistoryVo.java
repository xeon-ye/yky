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
public class CheckResultHistoryVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "教职工id")
    private String userId;

    @ApiModelProperty(value = "教职工姓名")
    private String name;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "考核组织名称")
    private String checkOrgName;

    @ApiModelProperty(value = "考核关系id")
    private String checkRelationId;

    @ApiModelProperty(value = "评价人层级")
    private String groupRole;

    @ApiModelProperty(value = "评价人权重")
    private String weight;

    @ApiModelProperty(value = "评价人id")
    private String evaluateUserId;

    @ApiModelProperty(value = "评价人id")
    private String evaluateEmpCode;

    @ApiModelProperty(value = "评价人姓名")
    private String evaluateUserName;

    @ApiModelProperty(value = "评价等级")
    private String evaluateLevel;

    @ApiModelProperty(value = "评价分数")
    private String evaluateScore;




}
