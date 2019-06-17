package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-03
 * @Description :  EmployeeTeach查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTeachQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long empId;
    private LocalDateTime entranceTime;
    private LocalDateTime graduationTime;
    private String graduateSchool;
    private String majorName;
    private String education;
    private String academicDegree;
    private String detailedDegree;
    private String learningForm;
    private String educSystem;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;

}
