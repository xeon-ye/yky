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
 * @Description :  EmployeeMesLearn查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesLearnQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String empCode;
    private String outcomeName;
    private String outcomeType;
    private String outcomeLevel;
    private String identificationUnit;
    private String paperTitle;
    private LocalDateTime publicationTime;
    private String publicationPlace;
    private String publicationSituation;
    private String remarks;
    private LocalDateTime awardTime;
    private String awardProjectName;
    private String awardName;
    private String awardProjectRole;
    private String awardUnit;
    private LocalDateTime patentTime;
    private String patentName;
    private String patentNumber;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
