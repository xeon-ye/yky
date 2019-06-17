package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccAcademicInnovation新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccAcademicInnovation表单")
@Data
public class GccAcademicInnovationForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人员编号")
    private Long id;

    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "总体情况")
    private String totalityCondition;

    @ApiModelProperty(value = "平台团队")
    private String platformTeam;

    @ApiModelProperty(value = "其他情况")
    private String otherCondition;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "项目情况成果转化")
    private String achievement;

    @ApiModelProperty(value = "工作设想")
    private String assumption;
}
