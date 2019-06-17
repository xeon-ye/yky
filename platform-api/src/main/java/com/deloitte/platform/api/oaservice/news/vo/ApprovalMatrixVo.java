package com.deloitte.platform.api.oaservice.news.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接收前端传入的下一节点的审批人信息
 */
@Data
public class ApprovalMatrixVo {

    @ApiModelProperty(value = "审批人账号")
    private String accountNum;

    @ApiModelProperty(value = "审批人名称")
    private String accountName;

    @ApiModelProperty(value = "审批人部门")
    private String orgCode;
}
