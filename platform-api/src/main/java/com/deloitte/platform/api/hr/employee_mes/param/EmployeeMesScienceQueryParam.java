package com.deloitte.platform.api.hr.employee_mes.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : woo
 * @Date : Create in 2019-06-05
 * @Description :  EmployeeMesScience查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesScienceQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String empCode;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String scienceCompany;
    private String scienceProject;
    private String projectType;
    private String projectLowerCompany;
    private String projectLowerLevel;
    private String projectLowerRole;
    private String projectBenefit;
    private String projectLevel;
    private String oneLevelSubject;
    private String twoLevelSubject;
    private String teachersSituation;
    private String academician;
    private String highLevelTalents;
    private String masterTeachers;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
