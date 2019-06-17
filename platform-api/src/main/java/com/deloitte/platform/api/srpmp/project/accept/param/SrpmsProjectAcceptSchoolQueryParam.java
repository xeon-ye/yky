package com.deloitte.platform.api.srpmp.project.accept.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description :  SrpmsProjectAcceptSchool查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectAcceptSchoolQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long acceptId;
    private String projectAbstract;
    private String projectContentIndicators;
    private String projectGainResult;
    private String projectTeamConstruction;
    private String projectQuestionAdvice;
    private String projectResultDirectory;

}
