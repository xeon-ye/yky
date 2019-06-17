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
 * @Description :  EmployeePolitical查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePoliticalQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long empId;
    private String politicalOutlook;
    private LocalDateTime startTime;
    private LocalDateTime turnFormal;
    private String introducer;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;

}
