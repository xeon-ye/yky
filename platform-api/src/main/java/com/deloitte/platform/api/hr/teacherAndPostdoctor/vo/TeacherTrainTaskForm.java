package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description : TeacherTrainTask新增修改form对象
 * @Modified :
 */
@ApiModel("录入作业占比表单")
@Data
public class TeacherTrainTaskForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "人员信息id")
    private Long teacherTrainId;

    @ApiModelProperty(value = "作业一占比（%）")
    @NotNull(message = "作业一占比不能为空")
    @Range(min = 0,max = 100)
    private BigDecimal taskOneScale;

    @ApiModelProperty(value = "作业二占比（%）")
    @NotNull(message = "作业二占比不能为空")
    @Range(min = 0,max = 100)
    private BigDecimal taskTwoScale;

    @ApiModelProperty(value = "考勤占比（%）")
    @NotNull(message = "考勤占比不能为空")
    @Range(min = 0,max = 100)
    private BigDecimal attendanceScale;

    @ApiModelProperty(value = "作业一教师职工ID")
    @NotNull(message = "作业一老师未选择")
    private String taskOneTeacherId;

    @ApiModelProperty(value = "作业二教师职工ID")
    @NotNull(message = "作业二老师未选择")
    private String taskTwoTeacherId;
}
