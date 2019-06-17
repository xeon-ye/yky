package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : tankui
 * @Date : Create in 2019-04-25
 * @Description :  HrZpcpAssessment查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrZpcpAssessmentQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long empCode;
    private String assessmentType;
    private LocalDateTime assessmentTime;
    private String assessmentResult;
    private Long enclosure;
    private String remarks;
    private String organizationCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String status;

}
