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
 * @Description :  EmployeeMesZpcp查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesZpcpQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String empCode;
    private String userName;
    private String headPhoto;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String careteBy;
    private String updateBy;
    private String applyState;
    private String empMesId;
    private String mesTid;
    private String unit;
    private String postion;
    private String year;
    private String batch;
    private String postionType;
    private String subject1;
    private String subject2;
    private String prolTechnicians;
    private String majorDirection;
    private String graduateQualification;
    private String majorSubject1;
    private String majorSubject2;
    private String majorSubject3;
    private String inspectionType;
    private String inspectionDate;
    private String inspectionResult;
    private String inspectionOption;
    private String employStatus;
    private LocalDateTime employStartDate;
    private LocalDateTime employEndDate;
    private String servedYear;
    private String salarySystem;
    private String annualPay;
    private String settleFree;
    private String contractType;
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    private String employDuration;

}
