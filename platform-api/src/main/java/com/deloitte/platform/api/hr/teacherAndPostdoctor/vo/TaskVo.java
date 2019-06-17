package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description : TeacherTrainTask返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "培训年份")
    private String year;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "班号")
    private String classSerial;

    @ApiModelProperty(value = "学号")
    private String sno;

    @ApiModelProperty(value = "职工编号")
    private String teacherCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "所属单位")
    private String attachUnit;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "身份证号")
    private String idCode;

    @ApiModelProperty(value = "考勤占比（%）")
    private BigDecimal attendanceScale;

    @ApiModelProperty(value = "考勤得分")
    private String attendanceScore;

    @ApiModelProperty(value = "作业一评分")
    private BigDecimal taskOneMark;

    @ApiModelProperty(value = "作业一占比（%）")
    private BigDecimal taskOneScale;

    @ApiModelProperty(value = "作业二评分")
    private BigDecimal taskTwoMark;

    @ApiModelProperty(value = "作业二占比（%）")
    private BigDecimal taskTwoScale;

    @ApiModelProperty(value = "考勤成绩")
    private BigDecimal attScore;

    @ApiModelProperty(value = "作业成绩")
    private BigDecimal taskMark;

    @ApiModelProperty(value = "总成绩")
    private BigDecimal tatolMark;

    @ApiModelProperty(value = "考勤日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate attendanceDate1;

    @ApiModelProperty(value = "考勤日期上午是否出勤（1出勤，0未出勤）")
    private String dateMorning1;

    @ApiModelProperty(value = "考勤日期下午是否出勤（1出勤，0未出勤）")
    private String dateAfternoon1;

    @ApiModelProperty(value = "考勤日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate attendanceDate2;

    @ApiModelProperty(value = "考勤日期上午是否出勤（1出勤，0未出勤）")
    private String dateMorning2;

    @ApiModelProperty(value = "考勤日期下午是否出勤（1出勤，0未出勤）")
    private String dateAfternoon2;

    @ApiModelProperty(value = "考勤日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate attendanceDate3;

    @ApiModelProperty(value = "考勤日期上午是否出勤（1出勤，0未出勤）")
    private String dateMorning3;

    @ApiModelProperty(value = "考勤日期下午是否出勤（1出勤，0未出勤）")
    private String dateAfternoon3;
}
