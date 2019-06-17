package com.deloitte.platform.api.oaservice.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-27
 * @Description : OaScheduleTable新增修改领导视图form对象
 * @Modified :
 */
@ApiModel("新增OaScheduleTable表单")
@Data
public class OaScheduleTableLeadForm extends BaseForm {
    @ApiModelProperty(value = "行号")
    private int rowNum;

    @ApiModelProperty(value = "业务单据主键")
    private String businessId;

    @ApiModelProperty(value = "业务行状态")
    private String rowStatus;

    @ApiModelProperty(value = "日程对象")
    private String userId;

    @ApiModelProperty(value = "日程对象名称")
    private String userName;

    @ApiModelProperty(value = "日程对象所属部门ID")
    private String deptId;

    @ApiModelProperty(value = "日程对象所属部门名称")
    private String deptName;

    @ApiModelProperty(value = "行程集合")
    private List<OaScheduleTableForm> schedules;
}
