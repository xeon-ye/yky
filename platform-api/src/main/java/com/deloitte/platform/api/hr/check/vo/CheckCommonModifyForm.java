package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : 考核同意修改form对象
 * @Modified :
 */
@ApiModel("批量修改表单")
@Data
public class CheckCommonModifyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "id列表")
    private List<String> list;


    @ApiModelProperty(value = "考核领导小组审核意见")
    private String checkGroupLeaderEvaluate;

    @ApiModelProperty(value = "领导小组填写日期")
    private LocalDate checkGroupLeaderTime;

    @ApiModelProperty(value = "单位负责人意见")
    private String unitLeaderEvaluate;

    @ApiModelProperty(value = "单位负责人填写日期")
    private LocalDate unitLeaderEvaluateTime;

}
