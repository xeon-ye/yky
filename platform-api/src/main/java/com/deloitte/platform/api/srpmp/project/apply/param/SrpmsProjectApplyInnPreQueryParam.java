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
 * @Description :  SrpmsProjectApplyInnPre查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectApplyInnPreQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String achievementType;
    private Long applyPostNumber;
    private Double applyFunds;
    private String projectAbstract;
    private String approvalNecessay;
    private String achievementForm;
    private String achievementBenefit;
    private String researchContentMain;
    private String researchContentMethod;
    private String projectTarget;
    private String superiorityDeptBasic;
    private Double fundSourceAmount;
    private Double fundSourceProject;
    private Double fundSourceSelf;
    private Double fundSourceOther;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
