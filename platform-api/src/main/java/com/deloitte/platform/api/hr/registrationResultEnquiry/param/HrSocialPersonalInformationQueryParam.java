package com.deloitte.platform.api.hr.registrationResultEnquiry.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :  HrSocialPersonalInformation查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrSocialPersonalInformationQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String name;
    private String gender;
    private String nation;
    private LocalDateTime dateOfBirth;
    private Double height;
    private String bloodType;
    private String politicsStatus;
    private String maritalStatus;
    private String highestEducation;
    private String certificateType;
    private String certificateNumber;
    private String highestDegree;
    private String graduateSchool;
    private String major;
    private String photoPath;
    private String foreignLanguageLevel;
    private String computerAbility;
    private String householdLocation;
    private String householdType;
    private String currentEmployer;
    private String department;
    private String technicalLevel;
    private String duty;
    private Double expectedMonthlySalary;
    private String mailingAddress;
    private String cellPhoneNumber;
    private String landlineNumber;
    private String familyAddress;
    private String emailAddress;
    private String positionAndNumber;
    private String currentCity;
    private String mainAchievements;
    private String rewards;
    private String selfAssessment;
    private String status;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String orgCode;

}
