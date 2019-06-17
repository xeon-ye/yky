package com.deloitte.platform.api.hr.employee_mes.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-05-08
 * @Description :  EmployeeMesDis查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesDisQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalDateTime effectiveStartDate;
    private Integer assignmentStatusTypeId;
    private String employeeCategory;
    private String employmentCategory;
    private String changeSonType;
    private String company;
    private String column1;
    private String column2;
    private String assAttribute2;
    private Long positionId;
    private String assAttribute3;
    private String supervisorId;
    private String directorName;
    private String payrollId;
    private String assAttribute1;
    private String column3;
    private String postWages;
    private Long gradeId;
    private String column4;
    private String assAttribute4;
    private String assAttribute7;

}
