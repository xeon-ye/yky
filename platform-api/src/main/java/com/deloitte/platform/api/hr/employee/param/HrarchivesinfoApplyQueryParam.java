package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : LJH
 * @Date : Create in 2019-05-20
 * @Description :  HrarchivesinfoApply查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrarchivesinfoApplyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String empId;
    private String careteBy;
    private String updateBy;
    private String createTime;
    private String updateTime;
    private String type;

}
