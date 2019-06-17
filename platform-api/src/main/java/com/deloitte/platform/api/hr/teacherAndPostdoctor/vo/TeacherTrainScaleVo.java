package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
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
public class TeacherTrainScaleVo extends BaseVo {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "人员信息id")
    private String aid;

    @ApiModelProperty(value = "作业一占比（%）")
    private BigDecimal taskOneScale;

    @ApiModelProperty(value = "作业一评分")
    private BigDecimal taskOneMark;

    @ApiModelProperty(value = "作业二占比（%）")
    private BigDecimal taskTwoScale;

    @ApiModelProperty(value = "作业二评分")
    private BigDecimal taskTwoMark;

    @ApiModelProperty(value = "考勤占比（%）")
    private BigDecimal attendanceScale;

    @ApiModelProperty(value = "考勤得分")
    private BigDecimal attendanceScore;


}
