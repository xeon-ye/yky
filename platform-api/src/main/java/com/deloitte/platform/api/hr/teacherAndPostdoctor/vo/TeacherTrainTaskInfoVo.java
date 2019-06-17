package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
public class TeacherTrainTaskInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String teacherTrainId;

    @ApiModelProperty(value = "作业ID")
    private String tid;

    @ApiModelProperty(value = "培训年份")
    private String year;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "班号")
    private String classSerial;

    @ApiModelProperty(value = "职工编号")
    private String teacherCode;

    @ApiModelProperty(value = "查看权限（[1,2]作业一作业二，[1]作业一，[2]作业二）")
    private String see;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "作业一评分")
    private BigDecimal taskOneMark;

    @ApiModelProperty(value = "作业二评分")
    private BigDecimal taskTwoMark;

    @ApiModelProperty(value = "作业一")
    private String taskOne;

    @ApiModelProperty(value = "作业二")
    private String taskTwo;

    @ApiModelProperty(value = "作业一附件url")
    private String taskOneAttachmentUrl;

    @ApiModelProperty(value = "作业二附件url")
    private String taskTwoAttachmentUrl;

}
