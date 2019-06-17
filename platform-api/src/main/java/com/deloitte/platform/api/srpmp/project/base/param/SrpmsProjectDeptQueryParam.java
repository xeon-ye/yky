package com.deloitte.platform.api.srpmp.project.base.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : lixin
 * @Date : Create in 2019-02-26
 * @Description :  SrpmsProjectDept查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectDeptQueryParam extends BaseParam {
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

}
