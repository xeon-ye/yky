package com.deloitte.platform.api.hr.registrationResultEnquiry.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description : HrEducationExperience返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrEducationExperienceVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private Long id;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

}
