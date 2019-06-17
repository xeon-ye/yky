package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-19
 * @Description : TeacherTrain返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherTrainVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "培训年份")
    private String year;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "班号")
    private String classSerial;

    @ApiModelProperty(value = "学号")
    private String sno;

    @ApiModelProperty(value = "教职工编号")
    private String teacherCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "所属单位")
    private String attachUnit;

    @ApiModelProperty(value = "免修情况（1是，0否）")
    private Integer notRepair;

    @ApiModelProperty(value = "缴费情况（1已缴费，0未交费）")
    private Integer payCost;

    @ApiModelProperty(value = "免修情况")
    private String repairText;

    @ApiModelProperty(value = "缴费情况")
    private String payCostText;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ApiModelProperty(value = "身份证号")
    private String idCode;

    @ApiModelProperty(value = "联系电话")
    private String telPhone;

    @ApiModelProperty(value = "最高学历")
    private String maxEducation;

    @ApiModelProperty(value = "职称")
    private String gainTitle;

    @ApiModelProperty(value = "所学专业")
    private String majorStudied;

    @ApiModelProperty(value = "考勤是否合格（1合格，0不合格）")
    private Integer isQualified;

    @ApiModelProperty(value = "是否取得资格（1合格，0不合格）")
    private Integer getQualifiy;

    @ApiModelProperty(value = "考勤得分")
    private String attendanceScore;

    @ApiModelProperty(value = "状态（1所院审核通过，2院校审核中，3院校审核通过，4院校审核不通过）")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否发送考勤通知（1是，0否）")
    private Integer isSend;

}
