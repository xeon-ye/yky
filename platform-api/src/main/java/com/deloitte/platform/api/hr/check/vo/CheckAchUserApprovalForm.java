package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckAchUserApproval新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckAchUserApproval表单")
@Data
public class CheckAchUserApprovalForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教职工id")
    private String userId;

    @ApiModelProperty(value = "个人业绩考核id")
    private Long checkAchUserContentId;

    @ApiModelProperty(value = "审批人id")
    private Long approvalUserId;

    @ApiModelProperty(value = "审批时间")
    private LocalDate approvalTime;

    @ApiModelProperty(value = "审批状态")
    private String approvalStatus;

    @ApiModelProperty(value = "审批意见")
    private String approvalOpinion;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
