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
public class TeacherTrainTaskSubmitVo extends BaseVo {
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

    @ApiModelProperty(value = "状态（1未提交，2已提交/未评分，3已评分,4已计算）")
    private Integer status;

}
