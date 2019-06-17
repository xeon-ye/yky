package com.deloitte.platform.api.hr.registrationResultEnquiry.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description : HrEducationExperience新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrEducationExperience表单")
@Data
public class HrEducationExperienceForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "社会人员个人基本情况id")
    private Long socialPersonalInformationId;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "毕业学校(高中起）")
    private String graduationSchool;

    @ApiModelProperty(value = "所学专业")
    private String major;

    @ApiModelProperty(value = "职务")
    private String duty;

    @ApiModelProperty(value = "毕业生个人基本情况id")
    private Long graduateId;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

}
