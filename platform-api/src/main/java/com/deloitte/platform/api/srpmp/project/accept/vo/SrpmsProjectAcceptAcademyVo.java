package com.deloitte.platform.api.srpmp.project.accept.vo;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description : SrpmsProjectAcceptAcademy返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectAcceptAcademyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目考核指标")
    private String assessmentIndicators;

    @ApiModelProperty(value = "研究内容考核指标完成情况")
    private String projectCompletionCase;

    @ApiModelProperty(value = "分析未完成原因")
    private String projectUnfinishReason;

    @ApiModelProperty(value = "项目实施经验")
    private String projectImplementExperience;

}
