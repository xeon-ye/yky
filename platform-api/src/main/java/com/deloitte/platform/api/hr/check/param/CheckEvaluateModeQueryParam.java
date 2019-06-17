package com.deloitte.platform.api.hr.check.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : woo
 * @Date : Create in 2019-04-11
 * @Description :  CheckEvaluateMode查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckEvaluateModeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String checkTemplateId;
    private String modeType;
    private String optionName;
    private String minScore;
    private String maxScore;
    private String stepLength;
    private Long orderNumber;
    private String optionType;
    private String optionScore;
    private String isDefault;
    private String remark;
    private String orgCode;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
