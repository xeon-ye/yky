package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckAchEvaluateContent新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckAchEvaluateContent表单")
@Data
public class CheckAchEvaluateContentForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "业绩考核测评通知id（冗余用于结果计算）")
    private Long checkEvaluateNotifyId;

    @ApiModelProperty(value = "个人测评通知id")
    private Long achEvaluateUserId;

    @ApiModelProperty(value = "评价人id")
    private Long evaluateUserId;

    @ApiModelProperty(value = "个人业绩考核id")
    private Long achUserId;

    @ApiModelProperty(value = "被评价人id")
    private Long userId;

    @ApiModelProperty(value = "评价等级")
    private String grade;

    @ApiModelProperty(value = "评估选项")
    private Long evaluateModeId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
