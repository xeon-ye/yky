package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckResult新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckResult表单")
@Data
public class CheckResultUpdateForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "部门负责人评语")
    private String departLeaderEvaluate;

    @ApiModelProperty(value = "绩效改进计划")
    private String achBetterPlan;

    @ApiModelProperty(value = "个人绩效考核ID")
    private Long checkAchUserId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评语填写日期")
    private LocalDate departLeaderEvaluateTime;


}
