package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckAchEvaluateUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckAchEvaluateUser表单")
@Data
public class CheckAchEvaluateUserForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核关系id(冗余便于查找被评价人)")
    private Long checkRelationId;

    @ApiModelProperty(value = "业绩考核测评通知id")
    private Long checkEvaluateNotifyId;

    @ApiModelProperty(value = "评价人id")
    private Long evaluateUserId;

    @ApiModelProperty(value = "提交状态")
    private String submitStatus;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
