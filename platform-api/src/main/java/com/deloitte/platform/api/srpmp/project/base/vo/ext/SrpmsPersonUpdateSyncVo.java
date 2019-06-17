package com.deloitte.platform.api.srpmp.project.base.vo.ext;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:LIJUN
 * Date:23/04/2019
 * Description:
 */
@ApiModel("人员变更同步VO")
@Data
public class SrpmsPersonUpdateSyncVo {

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "项目变更单号")
    private String updateNumber;

    @ApiModelProperty(value = "人员JSON")
    private String person;
}
