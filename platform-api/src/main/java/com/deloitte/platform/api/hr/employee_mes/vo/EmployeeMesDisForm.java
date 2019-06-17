package com.deloitte.platform.api.hr.employee_mes.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-08
 * @Description : EmployeeMesDis新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeMesDis表单")
@Data
public class EmployeeMesDisForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "变动生效日期")
    private LocalDate effectiveStartDate;

    @ApiModelProperty(value = "分配状态")
    private Integer assignmentStatusTypeId;

    @ApiModelProperty(value = "在岗状态")
    private String employeeCategory;

    @ApiModelProperty(value = "变动类型")
    private String employmentCategory;

    @ApiModelProperty(value = "变动子类型")
    private String changeSonType;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "部门")
    private String dept;

    @ApiModelProperty(value = "聘用岗位类别（岗位设置聘用类别)")
    private String column2;

    @ApiModelProperty(value = "岗位级别（岗位设置聘用级别）")
    private String assAttribute2;

    @ApiModelProperty(value = "三定岗位名称")
    private String positionId;

    @ApiModelProperty(value = "工作地")
    private String assAttribute3;

    @ApiModelProperty(value = "主管编号")
    private String supervisorId;

    @ApiModelProperty(value = "主管姓名")
    private String directorName;

    @ApiModelProperty(value = "工资单")
    private String payrollId;

    @ApiModelProperty(value = "工资类别（工资系列")
    private String assAttribute1;

    @ApiModelProperty(value = "岗位级别（工资系列")
    private String column3;

    @ApiModelProperty(value = "岗位工资")
    private String postWages;

    @ApiModelProperty(value = "薪级")
    private Long gradeId;

    @ApiModelProperty(value = "薪级工资")
    private String column4;

    @ApiModelProperty(value = "调资起薪日期")
    private LocalDate assAttribute4;

    @ApiModelProperty(value = "工资变动依据文件")
    private String assAttribute7;

    private String empMesId;

    private String id;

    private String applyState;

    private String processNum;

}
