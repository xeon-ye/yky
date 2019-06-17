package com.deloitte.platform.api.srpmp.project.accept.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description :  SrpmsProjectAcceptAcademy查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectAcceptAcademyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long acceptId;
    private String assessmentIndicators;
    private String projectCompletionCase;
    private String projectUnfinishReason;
    private String projectImplementExperience;

}
