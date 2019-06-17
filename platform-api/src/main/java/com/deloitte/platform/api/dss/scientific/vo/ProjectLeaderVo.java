package com.deloitte.platform.api.dss.scientific.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目负责人
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectLeaderVo {

    @ApiModelProperty(value = "项目负责人")
    private String leaderName;

    @ApiModelProperty(value = "项目状态")
    private Integer projectStatus;

    @ApiModelProperty(value = "研究方向（学科）")
    private String subjectCategory;

}
