package com.deloitte.platform.api.hr.ebs.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : JETVAE
 * @Date : Create in 2019-06-04
 * @Description : EmployeeAbroad返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAbroadVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "出国日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate abrTime;

    @ApiModelProperty(value = "回国日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate retTime;

    @ApiModelProperty(value = "停留时间")
    private String stayDur;

    @ApiModelProperty(value = "出境地区")
    private String depArea;

    @ApiModelProperty(value = "执行任务")
    private String perTas;

    @ApiModelProperty(value = "审批文号")
    private String appNo;

    @ApiModelProperty(value = "经费来源（1财政拨款，2经费自理）")
    private String souFund;

    @ApiModelProperty(value = "组团单位")
    private String groUnit;

    @ApiModelProperty(value = "审批单位")
    private String appUnit;

    @ApiModelProperty(value = "审批时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appTime;

    @ApiModelProperty(value = "护照/通行证号码")
    private String pasNum;

    @ApiModelProperty(value = "护照/通行证有效期")
    private String pasValPer;

    @ApiModelProperty(value = "人员信息CODE")
    private String userCode;

}
