package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description : TeacherTrainTask返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherTrainTaskVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String tid;

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

    @ApiModelProperty(value = "考勤占比（%）")
    private BigDecimal attendanceScale;

    @ApiModelProperty(value = "考勤得分")
    private String attendanceScore;

    @ApiModelProperty(value = "作业一占比（%）")
    private BigDecimal taskOneScale;

    @ApiModelProperty(value = "作业一评分")
    private BigDecimal taskOneMark;

    @ApiModelProperty(value = "作业一教师职工")
    private String taskOneTeacherName;

    @ApiModelProperty(value = "作业二占比（%）")
    private BigDecimal taskTwoScale;

    @ApiModelProperty(value = "作业二评分")
    private BigDecimal taskTwoMark;

    @ApiModelProperty(value = "作业二教师职工")
    private String taskTwoTeacherName;

    @ApiModelProperty(value = "总评分")
    private BigDecimal tatolMark;

    @ApiModelProperty(value = "是否取得资格（1合格，0不合格）")
    private String getQualifiy;

    @ApiModelProperty(value = "证书名称")
    private String certName;

    @ApiModelProperty(value = "获得日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate getDate;

    @ApiModelProperty(value = "培训结果备注")
    private String remark;

}
