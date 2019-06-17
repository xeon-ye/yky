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
 * @Date : Create in 2019-05-22
 * @Description :  HrdataApply查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrdataApplyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalDateTime endTime;
    private String company;
    private String popType;
    private String dataUsage;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String orgCode;
    private String empId;
    private String processNum;
    private String applyYear;
    private String applyState;
    private String remarks;

}
