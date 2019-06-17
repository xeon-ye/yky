package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description :  EmployeeWages查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWagesQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String employeeCode;
    private String yearDate;
    private String monthDate;
    private String label;
    private Double money;
    private String labelType;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
