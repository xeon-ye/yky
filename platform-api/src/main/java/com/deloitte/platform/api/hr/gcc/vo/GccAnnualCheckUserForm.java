package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccAnnualCheckUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccAnnualCheckUser表单")
@Data
public class GccAnnualCheckUserForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "序号")
    private Long orderNumber;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申请项目名称")
    private String applyProjectName;

    @ApiModelProperty(value = "申请项目类别")
    private String applyType;

    @ApiModelProperty(value = "教职工id")
    private String userId;

    @ApiModelProperty(value = "考核年度")
    private String checkYear;

    @ApiModelProperty(value = "考核日期")
    private LocalDateTime checkTime;

    @ApiModelProperty(value = "考核结果")
    private String checkResult;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
