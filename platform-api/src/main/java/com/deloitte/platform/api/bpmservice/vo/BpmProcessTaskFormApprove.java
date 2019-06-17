package com.deloitte.platform.api.bpmservice.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-25
 * @Description :   流程过程中的流程参数FORM对象
 * @Modified :
 */
@ApiModel("流程过程参数对象")
@Data
public class BpmProcessTaskFormApprove extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "指定节点")
    private String destTaskKey;

//    @ApiModelProperty(value = "任务标题")
//    private String taskTitle;
//
//    @ApiModelProperty(value = "任务描述")
//    private String taskDescription;

    @ApiModelProperty(value = "审批对象描述")
    private String objectDescription;

    @ApiModelProperty(value = "审批意见")
    private String opinion;

    @ApiModelProperty(value = "紧急程度")
    private String emergency;

    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText01;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText02;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText03;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText04;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText05;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText06;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText07;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText08;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText09;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private String customText10;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private Double customNumber01;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private Double customNumber02;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private LocalDateTime customDate01;
    @ApiModelProperty(value = "业务系统的自定义字段")
    private LocalDateTime customDate02;


}
