package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-11
 * @Description : CheckEvaluateMode返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckEvaluateModeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "考核模板id")
    private String checkTemplateId;

    @ApiModelProperty(value = "评估类别")
    private String modeType;

    @ApiModelProperty(value = "选项名称")
    private String optionName;

    @ApiModelProperty(value = "最小值")
    private String minScore;

    @ApiModelProperty(value = "最大值")
    private String maxScore;

    @ApiModelProperty(value = "步长")
    private String stepLength;

    @ApiModelProperty(value = "排序")
    private Long orderNumber;

    @ApiModelProperty(value = "选项类别（1下拉框2输入框）")
    private String optionType;

    @ApiModelProperty(value = "分值")
    private String optionScore;

    @ApiModelProperty(value = "是否默认")
    private String isDefault;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}
