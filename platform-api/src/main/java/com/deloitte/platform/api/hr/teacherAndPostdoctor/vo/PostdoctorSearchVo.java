package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : Postdoctor返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorSearchVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = " 姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "所属流动站")
    private String stationName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "进站时间")
    private LocalDate pullTime;

    @ApiModelProperty(value = "身份类型（1统招统分，2在职人员，3博新计划进站，4其他）")
    private String cardType;

    @ApiModelProperty(value = "招收类型（1流动站自主招收，2与工作站联合培养）")
    private String recruitType;

    @ApiModelProperty(value = "进站单位code")
    private String attachUnit;

    @ApiModelProperty(value = "进站单位")
    private String attachUnitName;

    @ApiModelProperty(value = "状态")
    private Integer status;


}
