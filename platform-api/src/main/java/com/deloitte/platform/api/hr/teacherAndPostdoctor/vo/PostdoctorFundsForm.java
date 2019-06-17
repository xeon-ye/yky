package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorFunds新增修改form对象
 * @Modified :
 */
@ApiModel("博士后项目经费录入表单")
@Data
public class PostdoctorFundsForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "博士后信息ID")
    private Long postdoctorId;

    @ApiModelProperty(value = "博士后项目信息表ID")
    private Long postdoctorProjectId;

    @ApiModelProperty(value = "博士后经费总额（元）")
    private BigDecimal funds;

    @ApiModelProperty(value = "经费备注")
    private String remark;

    @ApiModelProperty(value = "经费类型（1人才项目，2基金项目，3基本年薪）")
    private Integer fundsType;

}
