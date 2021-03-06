package com.deloitte.platform.api.hr.employee_mes.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-14
 * @Description : EmployeeMesSkill新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeMesSkill表单")
@Data
public class EmployeeMesSkillForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "单位")
    private String campany;

    @ApiModelProperty(value = "部门")
    private String dept;

    @ApiModelProperty(value = "工勤岗位级别")
    private String segment1;

    @ApiModelProperty(value = "聘任文号")
    private String segment2;

    @ApiModelProperty(value = "取得技术职务时间")
    private LocalDateTime segment3;

    @ApiModelProperty(value = "批准技术职务单位")
    private String segment5;

    @ApiModelProperty(value = "聘任技术职务时间")
    private String segment4;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "${field.comment}")
    private String empMesId;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

}
