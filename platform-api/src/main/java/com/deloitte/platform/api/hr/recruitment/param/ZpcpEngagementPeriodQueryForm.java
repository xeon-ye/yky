package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpEngagementPeriod查询from对象
 * @Modified :
 */
@ApiModel("ZpcpEngagementPeriod查询表单")
@Data
public class ZpcpEngagementPeriodQueryForm extends BaseQueryForm<ZpcpEngagementPeriodQueryParam>  {


    @ApiModelProperty(value = "员工编码")
    private String empCoe;

    @ApiModelProperty(value = "再评信息表id")
    private Long infoId;


    /*@ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "是否有效(0无效,1有效)")
    private String status;

    @ApiModelProperty(value = "聘用开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "聘用结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "任职年限(月)")
    private String tenureService;

    @ApiModelProperty(value = "聘用状态")
    private String employmentStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;*/


}
