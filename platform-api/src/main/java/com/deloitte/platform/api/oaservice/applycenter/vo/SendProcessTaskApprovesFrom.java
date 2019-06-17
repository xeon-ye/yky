package com.deloitte.platform.api.oaservice.applycenter.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel("新增SendProcessTask表单")
@Data
public class SendProcessTaskApprovesFrom {

    @NotEmpty
    @ApiModelProperty(value = "阅办人账号")
    private String approverAcount;

    @NotEmpty
    @ApiModelProperty(value = "阅办人姓名")
    private String approverName;

    @ApiModelProperty(value = "阅办人岗位")
    private String approverStation;

    @ApiModelProperty(value = "阅办信息描述")
    private String approverDescription;

    @NotEmpty
    @ApiModelProperty(value = "阅办对象URL")
    private String objectUrl;

    @ApiModelProperty(value = "app地址")
    private String objectAppUrl;

    @NotEmpty
    @ApiModelProperty(value = "审批人组织")
    private String approverOrg;
}
