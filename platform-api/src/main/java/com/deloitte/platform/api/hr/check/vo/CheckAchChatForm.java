package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckAchChat新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckAchChat表单")
@Data
public class CheckAchChatForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核结果id")
    private Long checkResultId;

    @ApiModelProperty(value = "绩效沟通通知名称")
    private String chatName;

    @ApiModelProperty(value = "沟通状态")
    private String chatStatus;

    @ApiModelProperty(value = "绩效结果确认")
    private String checkResultConfirm;

    @ApiModelProperty(value = "是否同意考核结果")
    private String isAgree;

    @ApiModelProperty(value = "绩效申述")
    private String achAppeal;

    @ApiModelProperty(value = "附件id")
    private Long fileId;

    @ApiModelProperty(value = "申诉处理意见")
    private String appealHandleOpinion;

    @ApiModelProperty(value = "申诉结果")
    private String appeaResult;

    @ApiModelProperty(value = "组织code")
    private String orgCode;


}
