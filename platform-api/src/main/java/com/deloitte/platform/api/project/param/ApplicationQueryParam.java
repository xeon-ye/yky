package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-06-14
 * @Description :  Application查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectId;
    private String theApplicationYear;
    private String budBasis;
    private String budContent;
    private String budTargetMeasure;
    private String budCondition;
    private String sciBasis;
    private String sciContent;
    private String eduNeed;
    private String eduMaybe;
    private String eduCondi;
    private String eduBudge;
    private String eduPlan;
    private String eduAnalysis;
    private String eduBenefit;
    private String eduContent;
    private String budgetAccType;
    private String budgetAccValue;
    private String priority;
    private String ouBudgetCode;
    private String department;
    private String school;
    private String appStateCode;
    private String appStateName;
    private String departmentCode;
    private String projectAttribute;
    private String operationOu;
    private String proChange;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String orgId;
    private String orgPath;
    private String applicationId;
    private String subprojectTypeCode;
    private String subprojectTypeName;
    private String projectName;
    private String serviceNum;
    private String proAdress;
    private String createUserName;
    private String createUserId;

}
