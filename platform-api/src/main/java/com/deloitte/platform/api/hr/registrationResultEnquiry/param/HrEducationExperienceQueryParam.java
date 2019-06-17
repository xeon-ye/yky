package com.deloitte.platform.api.hr.registrationResultEnquiry.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :  HrEducationExperience查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrEducationExperienceQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long socialPersonalInformationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String graduationSchool;
    private String major;
    private String duty;
    private Long graduateId;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String orgCode;

}
