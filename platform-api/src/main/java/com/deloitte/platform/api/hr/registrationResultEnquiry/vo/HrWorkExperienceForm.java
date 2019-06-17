package com.deloitte.platform.api.hr.registrationResultEnquiry.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description : HrWorkExperience新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrWorkExperience表单")
@Data
public class HrWorkExperienceForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "工作单位")
    private String company;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "岗位")
    private String position;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "社会人员个人基本情况ID")
    private Long socialPersonalInformationId;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

}
