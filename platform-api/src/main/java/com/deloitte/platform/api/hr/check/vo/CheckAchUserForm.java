package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckAchUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckAchUser表单")
@Data
public class CheckAchUserForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核关系id（冗余便于在测评的时候被关联）")
    private Long checkRelationId;

    @ApiModelProperty(value = "业绩通知id")
    private Long checkAchNotifyId;

    @ApiModelProperty(value = "教职工id")
    private Long userId;

    @ApiModelProperty(value = "填写时间")
    private LocalDate writeTime;

    @ApiModelProperty(value = "评估状态")
    private String evaluateStatus;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
