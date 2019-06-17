package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :   CheckAchEvaluateUser查询from对象
 * @Modified :
 */
@ApiModel("CheckAchEvaluateUser查询表单")
@Data
public class CheckAchEvaluateUserListQueryForm extends BaseQueryForm<CheckAchEvaluateUserQueryParam>  {


    @ApiModelProperty(value = "考核组织id")
    private String checkWorkId;

    @ApiModelProperty(value = "考核期间id")
    private String checkTimeId;

    @ApiModelProperty(value = "业绩考核测评通知id")
    private String checkEvaluateNotifyId;

    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "提交状态")
    private String submitStatus;

}
