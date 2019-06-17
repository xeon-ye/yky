package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccAnnualCheckUser查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccAnnualCheckUserQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long orderNumber;
    private String applyYear;
    private String applyProjectName;
    private String applyType;
    private String userId;
    private String checkYear;
    private LocalDateTime checkTime;
    private String checkResult;
    private String remarks;
    private String type;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
