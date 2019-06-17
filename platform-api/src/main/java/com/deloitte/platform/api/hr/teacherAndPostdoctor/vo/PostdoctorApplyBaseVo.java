package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : FlowStation返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorApplyBaseVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "博士后ID")
    private String postdoctorId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "出生年月")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ApiModelProperty(value = "身份类型")
    private String cardType;

    @ApiModelProperty(value = "招收类型")
    private String recruitType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "博管会进站时间")
    private LocalDate committeePullTime;

    @ApiModelProperty(value = "流动站（一级学科）")
    private String stationName;

    @ApiModelProperty(value = "专业（二级学科）")
    private String stationSubName;

    @ApiModelProperty(value = "进站单位")
    private String attachUnit;

    @ApiModelProperty(value = "原合作导师")
    private String oldTeacherName;


}
