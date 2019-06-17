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
 * @Description :  SrpmsProjectApplyInnBcoo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectApplyInnBcooQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String belongDomain;
    private String activeType;
    private String achievementType;
    private Long applyPostNumber;
    private Double applyFunds;
    private String projectAbstract;
    private String approvalNecessay;
    private String achievementForm;
    private String achievementBenefit;
    private String researchContentMain;
    private String researchContentMethod;
    private String researchContentInterflow;
    private String researchContentUsePlan;
    private String researchContentInnovate;
    private String projectTarget;
    private String superiorityDeptChoose;
    private String superiorityDeptBasic;
    private String superiorityDeptSuport;
    private String superiorityDeptAbroad;
    private String mainstayMemberNote;
    private String manageGuarantee;
    private String manageKnowledgeRight;
    private String manageRisk;
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
