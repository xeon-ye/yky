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
public class CheckAchUserListVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "考核关系id（冗余便于在测评的时候被关联）")
    private String checkRelationId;

    @ApiModelProperty(value = "业绩考核通知id")
    private String checkAchNotifyId;

    @ApiModelProperty(value = "业绩考核模板id")
    private String checkAchTempateId;

    @ApiModelProperty(value = "个人业绩考核通知id")
    private String achUserId;

    @ApiModelProperty(value = "被评价人id")
    private String userId;

    @ApiModelProperty(value = "提交状态")
    private String evaluateStatus;

    @ApiModelProperty(value = "考核组织")
    private String checkOrgId;

    @ApiModelProperty(value = "考核组织名称")
    private String checkOrgName;

    @ApiModelProperty(value = "考核工作Id")
    private String checkWorkId;

    @ApiModelProperty(value = "考核工作名称")
    private String checkWorkName;

    @ApiModelProperty(value = "教职工姓名")
    private String name;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "部门编号")
    private String depCode;

    @ApiModelProperty(value = "岗位编号")
    private String positionCode;

    @ApiModelProperty(value = "个人测评内容")
    private String achEvaluateContentId;

    @ApiModelProperty(value = "个人测评通知id")
    private String achEvaluateUserId;

    @ApiModelProperty(value = "评价等级")
    private String grade;

    @ApiModelProperty(value = "评估选项")
    private String evaluateModeId;

    @ApiModelProperty(value = "备注")
    private String remark;


}
