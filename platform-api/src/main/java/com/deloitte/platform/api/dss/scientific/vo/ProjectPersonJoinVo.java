package com.deloitte.platform.api.dss.scientific.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 项目参与人员学位统计
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPersonJoinVo {

    @ApiModelProperty(value = "博士后数")
    private Integer postDoctor;

    @ApiModelProperty(value = "博士数量")
    private Integer doctor;

    @ApiModelProperty(value = "硕士数量")
    private Integer master;

    @ApiModelProperty(value = "本科生数")
    private Integer underGraduate;



}
