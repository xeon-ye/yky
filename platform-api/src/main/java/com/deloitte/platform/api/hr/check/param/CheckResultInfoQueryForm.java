package com.deloitte.platform.api.hr.check.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :   CheckResult查询from对象
 * @Modified :
 */
@ApiModel("CheckResult查询表单")
@Data
public class CheckResultInfoQueryForm extends BaseQueryForm<CheckResultQueryParam>  {


    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "考核期间")
    private String checkTimeId;

    @ApiModelProperty(value = "业绩考核测评通知id")
    private String checkEvaluateNotifyId;

    @ApiModelProperty(value = "考核关系")
    private String checkRelationId;

    @ApiModelProperty(value = "教职工id")
    private String userId;

    @ApiModelProperty(value = "考核关系")
    private List<String> checkRelationIdList;

    @ApiModelProperty(value = "沟通状态")
    private String chatStatus;

    @ApiModelProperty(value = "是否同意考核结果")
    private String isAgree;

    @ApiModelProperty(value = "绩效结果确认")
    private String checkResultConfirm;

    @ApiModelProperty(value = "考核结果id集合")
    private List<String> checkResultIdList;


}
