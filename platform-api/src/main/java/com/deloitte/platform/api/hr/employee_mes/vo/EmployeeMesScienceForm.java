package com.deloitte.platform.api.hr.employee_mes.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-06-05
 * @Description : EmployeeMesScience新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeMesScience表单")
@Data
public class EmployeeMesScienceForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "科研经历起始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "科研经历结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "科研单位")
    private String scienceCompany;

    @ApiModelProperty(value = "科研项目")
    private String scienceProject;

    @ApiModelProperty(value = "项目类别")
    private String projectType;

    @ApiModelProperty(value = "项目下达单位名称")
    private String projectLowerCompany;

    @ApiModelProperty(value = "项目下达单位级别")
    private String projectLowerLevel;

    @ApiModelProperty(value = "项目中担任角色")
    private String projectLowerRole;

    @ApiModelProperty(value = "项目效益")
    private String projectBenefit;

    @ApiModelProperty(value = "项目水平")
    private String projectLevel;

    @ApiModelProperty(value = "一级学科")
    private String oneLevelSubject;

    @ApiModelProperty(value = "二级学科")
    private String twoLevelSubject;

    @ApiModelProperty(value = "导师情况")
    private String teachersSituation;

    @ApiModelProperty(value = "院士")
    private String academician;

    @ApiModelProperty(value = "高层次人才")
    private String highLevelTalents;

    @ApiModelProperty(value = "教学名师")
    private String masterTeachers;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
