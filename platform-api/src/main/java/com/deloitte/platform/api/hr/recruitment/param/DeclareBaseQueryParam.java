package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : tankui
 * @Date : Create in 2019-04-21
 * @Description :  DeclareBase查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeclareBaseQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalDateTime birthday;
    private String politics;
    private String department;
    private String nation;
    private String education;
    private String degree;
    private String academy;
    private String major;
    private LocalDateTime graduationDate;
    private String remarks;
    private Long enclosure;
    private String organizationCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long userId;
    private Long createBy;
    private Long updateBy;
    private String name;
    private String deptCode;
    private String empCode;
    private String gender;
    private String idType;
    private String idNumber;
    private String specTechJob;
    private String researchDir;
    private String tutorQualification;
    private String profession;
    private String subject1;
    private String subject2;
    private String subject3;
    private String personalEmail;
    private String mobilePhone;

}
