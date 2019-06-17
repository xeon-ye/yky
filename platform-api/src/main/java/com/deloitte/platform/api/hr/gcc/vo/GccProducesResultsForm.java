package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccProducesResults新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccProducesResults表单")
@Data
public class GccProducesResultsForm extends BaseForm {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "概述")
    private String summarize;

    @ApiModelProperty(value = "本科教学情况（1对多课程）")
    private String undergraduateTeach;

    @ApiModelProperty(value = "科研育人情况（1对多课程）")
    private String scientificTeach;

    @ApiModelProperty(value = "其他情况")
    private String other;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "本科讲授课程")
    private String underCourse;

    @ApiModelProperty(value = "本科学时")
    private String underPeriod;

    @ApiModelProperty(value = "本科选学")
    private String underElementary;

    @ApiModelProperty(value = "研究生讲授课程")
    private String graduateCourse;

    @ApiModelProperty(value = "研究生学时")
    private String graduatePeriod;

    @ApiModelProperty(value = "研究生选学")
    private String graduateElementary;

}
