package com.deloitte.platform.api.isump.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**

 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  User查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String username;
    private String empNo;
    private String phone;
    private String email;
    private String password;
    private String avatar;
    private String honor;
    private String type;
    private String state;
    private Long createBy;
    private LocalDateTime createTime;
    private String remark;
    private String reserve;
    private String version;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String expertise;
    private String subject;
    private String gender;
    private LocalDateTime birthDate;
    private String positionTitle;
    private String education;
    private String major;
    private String tel;
    private String fax;
    private String idCardType;
    private String idCard;
    private String educationCountry;
    private String dept;
    private Integer workPerYear;
    private String address;
    private String zipCode;
    private String liveBaseName;
    private String talentPlan;
    private String sourcePersonId;
    private String degree;
    private String officeName;
    private String projectCommitmentUnit;
    private String country;
    private String nation;
    private String educationYear;
    private List<String> excludeIds;
    private List<String> empNos;
    private String faculty;
    private String loginType;
    private String birthPlace;
    private String ploSta;
    private String managePositionRank;
    private Long deputyAccountId;
    private String researchResults;
    private List<Long> idList;

}
