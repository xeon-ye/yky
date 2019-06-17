package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprTaskEvaInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MprTaskEvaInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectNo;
    private String projectType;
    private String projectCategory;
    private String projectName;
    private String projectCycle;
    private String taskProgress;
    private String adjustState;
    private String landmarkAchieve;
    private String econSocialBenefits;
    private String capaImprove;
    private String deveProsAna;
    private String personFundUse;
    private String fundAdjust;
    private String problemSuggest;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
