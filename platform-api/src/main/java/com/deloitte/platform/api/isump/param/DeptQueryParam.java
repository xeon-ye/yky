package com.deloitte.platform.api.isump.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jianglong
 * @Date : Create in 2019-03-07
 * @Description :  Dept查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String deptName;
    private String zipCode;
    private String address;
    private String contactsName;
    private String phone;
    private String email;
    private String faxNumber;
    private String deptQuality;
    private String deptManDepartment;
    private String deptSubjection;
    private String deptLegalPersonName;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String bankAccountNameLoose;
    private String bankNameLoose;
    private String bankAccountNumberLoose;
    private String bankAccountNameBase;
    private String bankNameBase;
    private String bankAccountNumberBase;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private Long sourceDeptId;
    private String orgCode;
    private String deptCode;
    private LocalDateTime establishedTime;
    private String ownershipType;
    private String adminName;
    private String deptLegalPersonGender;
    private LocalDateTime deptLegalPersonBothday;
    private String deptLegalPersonJob;
    private String deptLegalPersonEducation;
    private String deptLegalPersonJobtitle;
    private String mainName;
    private String mainGender;
    private LocalDateTime mainBothday;
    private String mainJob;
    private String mainTell;
    private String mainEmail;
    private String state;
    private String deptType;
    private String creditCode;
    private String taxpayerNumber;
    private String bankAccount;
    private String bankName;
    private String bankAccountNumber;
    private Integer groupType;
    private String mainPhone;

}
