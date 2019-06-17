package com.deloitte.platform.api.hr.technology.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-15
 * @Description :  RecruitTechnology查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitTechnologyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String gender;
    private LocalDateTime birthday;
    private LocalDateTime joinworkDay;
    private String dep;
    private String education;
    private String degree;
    private LocalDateTime graduate;
    private String graduationMajor;
    private String graduationShool;
    private String incumbentMajor;
    private LocalDateTime incumbentStartdate;
    private LocalDateTime incumbentEnddate;
    private String technicalNot;
    private LocalDateTime getDate;
    private String approvalCompany;
    private String languageType;
    private String languageLevel;
    private String score;
    private String getLanguagedate;
    private String approvalCompanyLang;
    private String applyJob;
    private LocalDateTime createTime;
    private String careteBy;
    private LocalDateTime updateBy;
    private LocalDateTime updateTime;

}
