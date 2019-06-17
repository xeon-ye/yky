package com.deloitte.platform.api.hr.registrationResultEnquiry.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :  HrFamilyMember查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrFamilyMemberQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long socialId;
    private String name;
    private String relation;
    private Long age;
    private String companyAndDepartment;
    private String duty;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private Long graduateId;
    private String orgCode;

}
