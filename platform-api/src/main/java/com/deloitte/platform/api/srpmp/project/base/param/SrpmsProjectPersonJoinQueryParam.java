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
 * @Description :  SrpmsProjectPersonJoin查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectPersonJoinQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long projectId;
    private String projectNum;
    private String joinWay;
    private String gender;
    private LocalDateTime birthDate;
    private String positionTitle;
    private String personCategory;
    private String degree;
    private String deptName;
    private String phone;
    private String idCard;
    private Integer workPerYear;
    private String belongTask;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private Long sourcePersonId;
    private String otherProjectRole;
    private String otherProjectName;
    private String otherProjectSource;
    private LocalDateTime otherProjectDateStart;
    private LocalDateTime otherProjectDateEnd;
    private String personName;
    private String major;
    private Double age;
    private String hasSalaryInput;

}
