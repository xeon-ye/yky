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
@ApiModel("CheckAchUserListVoQueryForm查询表单")
@Data
public class CheckAchUserListVoQueryForm extends BaseQueryForm<CheckAchEvaluateUserQueryParam>  {



    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;

    @ApiModelProperty(value = "业绩考核通知id")
    private String checkAchNotifyId;

    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "考核期间")
    private String checkTimeId;

    @ApiModelProperty(value = "考核关系")
    private String checkRelationId;

    @ApiModelProperty(value = "测评人员id")
    private String evaluateUserId;


}
