package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccIncludeProject新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccIncludeProject表单")
@Data
public class GccIncludeProjectForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "项目编号")
    private Long projectId;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "入选时间")
    private LocalDateTime selectedTime;

    @ApiModelProperty(value = "项目名称")
    private String projectName;
}
