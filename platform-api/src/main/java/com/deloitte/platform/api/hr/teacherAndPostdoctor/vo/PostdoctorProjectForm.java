package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorProject新增修改form对象
 * @Modified :
 */
@ApiModel("新增博士后项目信息表单")
@Data
public class PostdoctorProjectForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "年份")
    @NotNull(message = "年份不能为空")
    private String year;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "项目名称")
    @NotNull(message = "项目名称不能为空")
    private String projectName;

    @ApiModelProperty(value = "项目类型（1人才项目，2基金项目，3基本年薪）")
    @NotNull(message = "项目类型未选择")
    private Integer projectType;

    @ApiModelProperty(value = "类别（[类别code,性质code,项目性质分类code]）")
    private String type;

    @ApiModelProperty(value = "资助时长（年）")
    private Integer duration;

    @ApiModelProperty(value = "资助额度(万元)")
    @NotNull(message = "资助额度不能为空")
    private BigDecimal limitFunds;

    @ApiModelProperty(value = "已拨付金额(万元)")
    @NotNull(message = "已拨付金额不能为空")
    private BigDecimal payLimitMoney;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效（1有效，0无效）")
    @NotNull(message = "是否有效未选择")
    private Integer isValid;

}
