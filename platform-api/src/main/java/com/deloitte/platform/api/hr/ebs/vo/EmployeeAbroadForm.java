package com.deloitte.platform.api.hr.ebs.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : JETVAE
 * @Date : Create in 2019-06-04
 * @Description : EmployeeAbroad新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeAbroad表单")
@Data
public class EmployeeAbroadForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "出国日期")
    private LocalDateTime abrTime;

    @ApiModelProperty(value = "回国日期")
    private LocalDateTime retTime;

    @ApiModelProperty(value = "停留时间")
    private String stayDur;

    @ApiModelProperty(value = "出境地区")
    private String depArea;

    @ApiModelProperty(value = "执行任务")
    private String perTas;

    @ApiModelProperty(value = "审批文号")
    private String appNo;

    @ApiModelProperty(value = "经费来源（1财政拨款，经费自理）")
    private String souFund;

    @ApiModelProperty(value = "组团单位")
    private String groUnit;

    @ApiModelProperty(value = "审批单位")
    private String appUnit;

    @ApiModelProperty(value = "审批时间")
    private LocalDateTime appTime;

    @ApiModelProperty(value = "护照/通行证号码")
    private String pasNum;

    @ApiModelProperty(value = "护照/通行证有效期")
    private String pasValPer;

    @ApiModelProperty(value = "人员信息CODE")
    private String userCode;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;

}
