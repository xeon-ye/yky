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
 * @Description :  SrpmsProjectPerson查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectPersonQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String personName;
    private String gender;
    private LocalDateTime birthDate;
    private String positionTitle;
    private String education;
    private String major;
    private String telPhone;
    private String mobile;
    private String faxNumber;
    private String email;
    private String idCardType;
    private String idCard;
    private String educationCountry;
    private String deptName;
    private Integer workPerYear;
    private String addressAndZip;
    private String liveBaseName;
    private String talentPlan;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private Long sourcePersonId;
    private String degree;
    private String officeName;
    private String projectCommitmentUnit;
    private String address;
    private String zipCode;

}
