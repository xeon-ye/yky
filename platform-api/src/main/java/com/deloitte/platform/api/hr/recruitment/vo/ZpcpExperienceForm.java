package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description : ZpcpExperience新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpExperience表单")
@Data
public class ZpcpExperienceForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "开始时间")
    private LocalDate startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDate endTime;

    @ApiModelProperty(value = "单位名称")
    private String companyName;

    @ApiModelProperty(value = "从事专业")
    private String profession;

    @ApiModelProperty(value = "职务")
    private String position;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "职称")
    private String jobTitle;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
