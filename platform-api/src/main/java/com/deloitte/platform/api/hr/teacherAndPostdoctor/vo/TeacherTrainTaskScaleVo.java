package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description :
 * @Modified :
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherTrainTaskScaleVo extends BaseVo {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "人员信息id")
    private String teacherTrainId;

    @ApiModelProperty(value = "作业一占比（%）")
    private BigDecimal taskOneScale;

    @ApiModelProperty(value = "作业二占比（%）")
    private BigDecimal taskTwoScale;

    @ApiModelProperty(value = "考勤占比（%）")
    private BigDecimal attendanceScale;

    @ApiModelProperty(value = "作业一教师职工ID")
    private String taskOneTeacherId;

    @ApiModelProperty(value = "作业二教师职工ID")
    private String taskTwoTeacherId;
}
