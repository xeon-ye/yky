package com.deloitte.platform.api.oaservice.news.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RollBackProcessVo {

    /**
     * 查询历史，为已提交的节点id.
     */
    @ApiModelProperty(value = "流程审批id")
    private long id;

    @ApiModelProperty(value = "token")
    private String token;
}
