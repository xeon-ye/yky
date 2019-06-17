package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : woo
 * @Date : Create in 2019-05-18
 * @Description :  TeacherdisApply查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherdisApplyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String applyState;
    private String remake;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String orgCode;
    private String processNum;
    private String empId;
    private String applyYear;
    private String disNum;
    private String sellingUnit;
    private String applyNum;
    private String sellingAddress;

}
