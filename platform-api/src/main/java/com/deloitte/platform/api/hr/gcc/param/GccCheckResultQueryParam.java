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
 * @Description :  GccCheckResult查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccCheckResultQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectUnit;
    private String projectName;
    private String projectCatagroy;
    private Long userId;
    private String userName;
    private String userUnit;
    private String checkType;
    private String checkYear;
    private LocalDateTime checkTime;
    private String checkResult;
    private String checkScroe;
    private String syCheckProgress;
    private String syCheckResult;
    private String yxCheckProgress;
    private String yxCheckResult;
    private String remarks;
    private Long enclosure;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;

}
