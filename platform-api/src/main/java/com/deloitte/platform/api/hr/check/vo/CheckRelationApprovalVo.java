package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckRelation返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRelationApprovalVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "考核期间id")
    private String checkTimeId;

    @ApiModelProperty(value = "考核期间名称")
    private String checkTimeName;

    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "考核组织名称")
    private String checkOrgName;

    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;

    @ApiModelProperty(value = "考核工作名称")
    private String checkWorkName;

    @ApiModelProperty(value = "考核关系名称")
    private String relationName;


    @ApiModelProperty(value = "被评价分组主键")
    private String beEvaluateGroupId;

    @ApiModelProperty(value = "被评价分组名称")
    private String beEvaluateGroupName;

    @ApiModelProperty(value = "被评价人")
    private String beEvaluateNames;

    @ApiModelProperty(value = "被评价人人数")
    private String beEvaluateNum;



    @ApiModelProperty(value = "评价人分组主键")
    private String evaluateGroupId;

    @ApiModelProperty(value = "评价人分组名称")
    private String evaluateGroupName;

    @ApiModelProperty(value = "评价人层级")
    private String evaluateLevel;

    @ApiModelProperty(value = "评价人权重")
    private String evaluateWeight;

    @ApiModelProperty(value = "评价人")
    private String evaluateNames;

    @ApiModelProperty(value = "评价人人数")
    private String evaluateNum;
}
