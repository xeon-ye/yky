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
 * @Description :  GccAnnualCheck查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccAnnualCheckQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long orderNumber;
    private String reportingYear;
    private String unitId;
    private String personnelProject;
    private String reportingContent;
    private String reportingUser;
    private LocalDateTime reportingTime;
    private String fileId;
    private String remarks;
    private String status;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
