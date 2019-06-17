package com.deloitte.platform.api.hr.employee_mes.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-05-08
 * @Description :  EmployeeMesBase查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesBaseQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Double id;
    private String employeeNumber;
    private String fullName;
    private String attribute1;
    private String nationalIdentifier;
    private Long personTypeId;
    private String sex;
    private String attribute2;
    private String attribute3;
    private String townOfBirth;
    private String dateOfBirth;
    private Integer age;
    private String regionOfBirth;
    private String nationality;
    private String perInformation17;
    private String maritalStatus;
    private String emailType;
    private String emailAddress;
    private String phoneType;
    private String phoneNumber;
    private String perInformation4;
    private String perInformation5;
    private LocalDateTime attribute8;
    private LocalDateTime originalDateOfHire;
    private String segment1;
    private String segment2;
    private String segment3;
    private String segment4;
    private String segment5;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;

}
