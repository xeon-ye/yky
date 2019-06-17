package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckAchEvaluateContent新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckAchEvaluateContent表单")
@Data
public class CheckAchEvaluateContentBatchForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "个人测评通知id")
    private String achEvaluateUserId;

    @ApiModelProperty(value = "通知提交状态")
    private String  submitStatus;

    @ApiModelProperty(value = "测评内容集合")
    private List<CheckAchEvaluateContentForm>  checkAchEvaluateContentList;

}
