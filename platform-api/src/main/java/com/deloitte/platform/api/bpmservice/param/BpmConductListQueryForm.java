package com.deloitte.platform.api.bpmservice.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;

/**
 * @Author : jackliu
 * @Date : Create in 2019-03-22
 * @Description :   BpmProcessTask查询from对象
 * @Modified :
 */
@ApiModel("BpmConductListQueryForm查询表单")
@Data
public class BpmConductListQueryForm extends BaseQueryForm<BaseParam>  {

    @ApiModelProperty(value = "审批人账号")
    @NotEmpty
    private String approverAcount;

    @ApiModelProperty(value = "审批单归属系统")
    private String sourceSystem;

    @ApiModelProperty(value = "任务定义key")
    private String taskKey;

    @ApiModelProperty(value = "审批对象业务单据编号")
    private String objectOrderNum;

    @ApiModelProperty(value = "审批对象描述")
    private String objectDescription;

    @ApiModelProperty(value = "审批对象审批状态 【1审批中 2已通过 3 已驳回 4 停止审批】")
    private String objectStauts;

    @ApiModelProperty(value = "审批对象类型")
    private String objectType;

    @ApiModelProperty(value = "紧急程度")
    private String emergency;

    @ApiModelProperty(value = "上一任务id")
    private String previousTaskId;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createByName;

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
