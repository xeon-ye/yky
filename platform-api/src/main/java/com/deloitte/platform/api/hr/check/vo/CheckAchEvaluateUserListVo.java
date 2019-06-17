package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckAchEvaluateUser返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAchEvaluateUserListVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "考核关系id(冗余便于查找被评价人)")
    private String checkRelationId;

    @ApiModelProperty(value = "业绩考核测评通知id")
    private String checkEvaluateNotifyId;

    @ApiModelProperty(value = "评价人id")
    private String evaluateUserId;

    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;

    @ApiModelProperty(value = "考核工作名称")
    private String checkWorkName;

    @ApiModelProperty(value = "提交状态")
    private String submitStatus;

    @ApiModelProperty(value = "考核期间名称")
    private String checkTimeId;

    @ApiModelProperty(value = "考核期间名称")
    private String checkTimeName;

    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "考核组织名称")
    private String checkOrgName;

    @ApiModelProperty(value = "考核分组名称")
    private String groupName;

    @ApiModelProperty(value = "考核分组角色")
    private String groupRole;

    @ApiModelProperty(value = "教职工姓名")
    private String name;

    @ApiModelProperty(value = "邮箱")
    private String email;




}
