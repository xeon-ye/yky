package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   PostdoctorProject查询from对象
 * @Modified :
 */
@ApiModel("博士后资助项目管理查询导出表单")
@Data
public class PostdoctorProjectExportForm {

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型（1人才项目，2基金项目）")
    private String projectType ;

    @ApiModelProperty(value = "是否有效（1有效，0无效）")
    private Integer isValid;


}
