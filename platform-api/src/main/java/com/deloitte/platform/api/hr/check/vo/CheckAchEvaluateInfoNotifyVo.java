package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckAchEvaluateNotify返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAchEvaluateInfoNotifyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "个人业绩考核id")
    private String id;

    @ApiModelProperty(value = "提交状态")
    private String submitStatus;

    @ApiModelProperty(value = "测评开始时间")
    private LocalDate startDate;

    @ApiModelProperty(value = "测评结束时间")
    private LocalDate endDate;

    @ApiModelProperty(value = "通知标题")
    private String evaluateName;

    @ApiModelProperty(value = "考核关系id")
    private String checkRelationId;

    @ApiModelProperty(value = "考核模板id")
    private String checkTemplateId;


    @ApiModelProperty(value = "考核关系名称")
    private String checkRelationName;

    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;

    @ApiModelProperty(value = "考核时间id")
    private String checkTimeId;

    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "业绩考核测评通知id")
    private String checkEvaluateNotifyId;

    @ApiModelProperty(value = "评价人id")
    private String evaluateUserId;

}
