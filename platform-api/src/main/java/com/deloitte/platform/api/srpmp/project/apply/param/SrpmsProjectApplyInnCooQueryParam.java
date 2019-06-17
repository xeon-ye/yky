package com.deloitte.platform.api.srpmp.project.apply.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : pengchao
 * @Date : Create in 2019-03-04
 * @Description :  SrpmsProjectApplyInnCoo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectApplyInnCooQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String direction;
    private String leadDeptName;
    private String teamDirection;
    private String teamEnName;
    private String teamConstitute;
    private String topExpertPersonIntro;
    private String teamMemberIntro;
    private String performanceLately;
    private String projectTarget;
    private String researchPlan;
    private String researchContentInterflow;
    private String researchContentInnovate;
    private String achievementForm;
    private String manageGuarantee;
    private String manageKnowledgeRight;
    private String manageRisk;
    private String guaranteePlan;
    private Double fundSourceAmount;
    private Double fundSourceProject;
    private Double fundSourceSelf;
    private Double fundSourceOther;
    private Double taskDiagram;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
