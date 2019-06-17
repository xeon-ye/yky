package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;


/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :
 * @Modified :
 */
@Data
@ApiModel("批量新增Teacher表单")
public class TeacherAddForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件路径")
    private String fileUrl;

}
