package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   Teacher导出from对象
 * @Modified :
 */
@ApiModel("Teacher导出表单")
@Data
public class TeacherExportForm  {


    @ApiModelProperty(value = "教师编号")
    private String teacherCode;

    @ApiModelProperty(value = "教师姓名")
    private String name;

    @ApiModelProperty(value = "所属单位")
    private String attachUnit;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申请任教学科")
    private String applyTeachSubject;

    @ApiModelProperty(value = "状态（1所院审核通过，2院校审核中，3院校审核通过，4院校审核不通过，5教委审核中，6教委审核通过，7教委审核不通过）  维护字典表")
    private Integer status;

}
