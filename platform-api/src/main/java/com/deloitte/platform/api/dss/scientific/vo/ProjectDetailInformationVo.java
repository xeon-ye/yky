package com.deloitte.platform.api.dss.scientific.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ProjectDetailInformationVo
 * @Description: 项目明细信息列表
 * @Auther: wangyanyun
 * @Date: 2019-03-04
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailInformationVo {

    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "项目负责人")
    private String projectPI;
    @ApiModelProperty(value = "项目金额")
    private Double fund;
    @ApiModelProperty(value = "项目状态")
    private Integer status;
    @ApiModelProperty(value = "博士后数量")
    private Integer postDoctorNum;
    @ApiModelProperty(value = "博士和硕士数量")
    private Integer doctorAndMasterNum;
    @ApiModelProperty(value = "博士数量")
    private Integer doctorNum;
    @ApiModelProperty(value = "硕士数量")
    private Integer MasterNum;
    @ApiModelProperty(value = "本科生数量")
    private Integer undergraduateNum;
    @ApiModelProperty(value = "项目PI承担项目数量")
    private Integer undertakeProjectNum;
    @ApiModelProperty(value = "研究方向（学科）")
    private String subjectCategory;
    @ApiModelProperty(value="研究方向code")
    private String code;



}
