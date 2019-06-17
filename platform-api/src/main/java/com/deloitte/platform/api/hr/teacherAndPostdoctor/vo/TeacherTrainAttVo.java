package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-20
 * @Description : TeacherTrainAtt返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherTrainAttVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考勤日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
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
