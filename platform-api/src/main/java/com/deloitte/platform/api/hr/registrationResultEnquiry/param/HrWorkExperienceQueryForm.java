package com.deloitte.platform.api.hr.registrationResultEnquiry.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :   HrWorkExperience查询from对象
 * @Modified :
 */
@ApiModel("HrWorkExperience查询表单")
@Data
public class HrWorkExperienceQueryForm extends BaseQueryForm<HrWorkExperienceQueryParam>  {


    @ApiModelProperty(value = "主键")
    private String id;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "社会人员个人基本情况ID")
    private String socialPersonalInformationId;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;
}
