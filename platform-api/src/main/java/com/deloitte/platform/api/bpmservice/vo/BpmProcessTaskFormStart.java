package com.deloitte.platform.api.bpmservice.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-25
 * @Description :   流程启动时的流程参数FORM对象
 * @Modified :
 */
@ApiModel("流程启动参数对象")
@Data
public class BpmProcessTaskFormStart extends BaseForm {

    private static final long serialVersionUID = 1L;

    @NotBlank(message="流程定义key不能为空")
    @ApiModelProperty(value = "流程定义key")
    private String processDefineKey;

//    @ApiModelProperty(value = "任务标题")
//    private String taskTitle;
//
//    @ApiModelProperty(value = "任务描述")
//    private String taskDescription;
    @NotEmpty(message="提交人账号不能为空")
    @ApiModelProperty(value = "提交人账号")
    private String submitterCode;

    @NotEmpty(message="提交人姓名不能为空")
    @ApiModelProperty(value = "提交人姓名")
    private String submitterName;

    @ApiModelProperty(value = "提交人岗位")
    private String submitterStation;

    @NotEmpty(message="提交人组织不能为空")
    @ApiModelProperty(value = "提交人组织")
    private String submitterOrg;

//    @ApiModelProperty(value = "提交人描述")
//    private String approverDescription;

    @NotEmpty(message="单据ID不能为空")
    @ApiModelProperty(value = "审批对象ID")
    private String objectId;

    @ApiModelProperty(value = "审批对象业务单据编号")
    private String objectOrderNum;

    @ApiModelProperty(value = "审批对象类型")
    private String objectType;

    @ApiModelProperty(value = "审批对象描述")
    private String objectDescription;

    @NotEmpty(message="审批单归属系统不能为空")
    @ApiModelProperty(value = "审批单归属系统")
    private String sourceSystem;

    @ApiModelProperty(value = "审批对象URL")
    private String objectUrl;

    @ApiModelProperty(value = "手机端访问地址")
    private String objectAppUrl;

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
