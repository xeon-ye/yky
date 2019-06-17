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
 * @Description :  EmployeeMesBank查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesBankQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String empCode;
    private String status;
    private String type;
    private String bankName;
    private String bankOpen;
    private String bankNumber;
    private String bankAccount;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
