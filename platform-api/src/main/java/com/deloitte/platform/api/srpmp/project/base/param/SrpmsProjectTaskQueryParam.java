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
 * @Description :  SrpmsProjectTask查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectTaskQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long projectId;
    private String taskName;
    private String taskCategory;
    private Integer taskYear;
    private Integer serial;
    private String taskTargetYear;
    private String importantPoint;
    private String taskContent;
    private String threeYearTarget;
    private String targetPerYear;
    private Long leadPersonId;
    private Long joinPersonId;
    private String budgetAllotRatio;
    private String deptName;
    private String groupLeaderMember;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String firstYearTarget;
    private String examMode;

}
