package com.deloitte.platform.api.hr.registrationResultEnquiry.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :  HrApplyingFlow查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrApplyingFlowQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String name;
    private String idNumber;
    private String technologicalProcess;
    private String result;
    private String remark;
    private LocalDateTime proposedEntryDate;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String orgCode;
    private String year;

}
