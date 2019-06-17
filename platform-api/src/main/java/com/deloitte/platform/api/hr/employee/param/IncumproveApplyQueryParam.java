package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description :  IncumproveApply查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncumproveApplyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String applyReason;
    private String applyState;
    private String applyDep;
    private String remake;
    private String sign;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String orgCode;
    private String applyYear;
    private String processNum;
    private String name;
}
