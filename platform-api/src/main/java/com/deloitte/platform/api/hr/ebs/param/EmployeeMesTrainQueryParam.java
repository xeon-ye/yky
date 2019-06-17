package com.deloitte.platform.api.hr.ebs.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesTrain查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesTrainQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String empCode;
    private String userName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String careteBy;
    private String updateBy;
    private String applyState;
    private String empMesId;
    private String mesTid;
    private String trainProject;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String sponsorCategory;
    private String sponsor;
    private String trainCategory;
    private String trainForm;
    private String trainHours;
    private String trainRecord;
    private String trainCertificate;
    private LocalDateTime certificateStatrDate;
    private LocalDateTime certificateEndDate;
    private String authority;
    private String remarks;

}
