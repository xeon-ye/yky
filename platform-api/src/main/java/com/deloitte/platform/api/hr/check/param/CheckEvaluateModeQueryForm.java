package com.deloitte.platform.api.hr.check.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-11
 * @Description :   CheckEvaluateMode查询from对象
 * @Modified :
 */
@ApiModel("CheckEvaluateMode查询表单")
@Data
public class CheckEvaluateModeQueryForm extends BaseQueryForm<CheckEvaluateModeQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

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
