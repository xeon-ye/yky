package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @Author : jetvae
 * @Date : Create in 2019-05-09
 * @Description : PostdoctorHAcademic新增修改form对象
 * @Modified :
 */
@ApiModel("新增PostdoctorHAcademic表单")
@Data
public class PostdoctorHForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学术报告")
    private String academic;

    @ApiModelProperty(value = "著作")
    private String book;

    @ApiModelProperty(value = "基金")
    private String fund;

    @ApiModelProperty(value = "奖励及荣誉")
    private String honor;

    @ApiModelProperty(value = "论文")
    private String paper;

    @ApiModelProperty(value = "专利")
    private String patent;

    @ApiModelProperty(value = "项目")
    private String project;

    @ApiModelProperty(value = "申请人ID")
    private Long postdoctorId;

    @ApiModelProperty(value = "申请信息ID")
    private Long appId;

    @NotNull(message = "保存类型不能为空")
    @Range(min = 1,max = 2,message = "保存类型值错误")
    @ApiModelProperty(value = "保存类型（1保存，2提交）")
    private Integer keep;

}
