package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-20
 * @Description : TeacherTrainAtt返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherTrainAttBaseVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "培训年份")
    private String year;

    @ApiModelProperty(value = "人员信息ID")
    private String tid;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "班号")
    private String classSerial;

    @ApiModelProperty(value = "职工编号")
    private String teacherCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "考勤是否合格（1合格，0不合格）")
    private Integer isQualified;

    @ApiModelProperty(value = "考勤得分")
    private String attendanceScore;

    @ApiModelProperty(value = "考勤信息")
    private List<TeacherTrainAttVo> attVoList;


}
