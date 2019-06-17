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
 * @Description :  EmployeeLastwork查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLastworkQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long empId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String company;
    private String dep;
    private String position;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private String updateTime;

}
