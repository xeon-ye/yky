package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-19
 * @Description : TeacherTrain新增修改form对象
 * @Modified :
 */
@ApiModel("录入考勤信息表单")
@Data
public class TeacherTrainAttInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考勤日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate attendanceDate;

    @ApiModelProperty(value = "考勤日期上午是否出勤（1出勤，0未出勤）")
    private Integer dateMorning;

    @ApiModelProperty(value = "考勤日期下午是否出勤（1出勤，0未出勤）")
    private Integer dateAfternoon;

    @ApiModelProperty(value = "出勤学时（小时）")
    private Long attendanceHour;

    @ApiModelProperty(value = "考勤备注")
    private String remark;

}
