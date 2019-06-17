package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : LIJUN
 * @Date : Create in 2019-05-07
 * @Description :  MprEvaBaseInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MprEvaBaseInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectNo;
    private String projectName;
    private String projectObjectives;
    private String jobQuantity;
    private String budget;
    private LocalDateTime executionStartTime;
    private LocalDateTime executionEndTime;
    private String projectCategory;
    private String projectClassification;
    private String leadUnit;
    private String takeUnit;
    private String chiefSpecialist;
    private String jointChiefSpecialist;
    private String progressState;
    private String workState;
    private String postdoctoralNum;
    private String doctoralNum;
    private String masterNum;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String annexStat;
    private String processStatus;
    private String projectType;
    private String projectTypeCode;
    private String reportType;
    private String applyDeptCode;
    private String otherCase;
    private Long reportId;
    private Long reportYear;
    private String reportTitle;
    private String repOtherType;
    private String tableFlag;

}
