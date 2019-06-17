package com.deloitte.platform.api.dss.scientific.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ProjectMemberInformationVo
 * @Description: 项目成员信息列表
 * @Auther: wangyanyun
 * @Date: 2019-03-04
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberInformationVo {

    @ApiModelProperty(value = "姓名")
    private String personName;
    @ApiModelProperty(value = "单位")
    private String unit;
    @ApiModelProperty(value = "学位")
    private String degree;
    @ApiModelProperty(value = "获取国家荣誉数量")
    private Integer nationalNum;
    @ApiModelProperty(value = "获取省级荣誉数量")
    private Integer provincialNum;
    @ApiModelProperty(value = "博士毕业院校")
    private String phpCollege;
    @ApiModelProperty(value = "硕士毕业院校")
    private String masterCollege;
    @ApiModelProperty(value = "本科毕业院校")
    private String bachelorCollege;
    @ApiModelProperty(value = "人才类别")
    private String personCategory;
    @ApiModelProperty(value = "学科背景")
    private String subjectBackground;


}
