package com.deloitte.platform.api.fssc.pe.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description :   PerSelfAssessment查询from对象
 * @Modified :
 */
@ApiModel("PerSelfAssessment查询表单")
@Data
public class PerSelfAssessmentQueryForm extends BaseQueryForm<PerSelfAssessmentQueryParam>  {




    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "项目负责人")
    private String projectDuty;

    @ApiModelProperty(value = "项目负责人ID")
    private Long projectDutyId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目id")
    private Long projectId;


    @ApiModelProperty(value = "主管部门ID")
    private Long doDeptId;

    @ApiModelProperty(value = "主管部门code")
    private String doDeptCode;

    @ApiModelProperty(value = "主管部门名称")
    private String doDeptName;

    @ApiModelProperty(value = "实施单位ID")
    private Long doUnitId;

    @ApiModelProperty(value = "实施单位CODE")
    private String doUnitCode;

    @ApiModelProperty(value = "实施单位名称")
    private String doUnitName;

    @ApiModelProperty(value = "金额开始范围")
    private BigDecimal moneyStart;

    @ApiModelProperty(value = "金额结束范围")
    private BigDecimal moneyEnd;

    @ApiModelProperty(value = "时间")
    private LocalDateTime timeStart;

    @ApiModelProperty(value = "时间")
    private LocalDateTime timeEnd;
}
