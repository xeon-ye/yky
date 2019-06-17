package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : Postdoctor返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = " 姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "博士后状态 （1在站，2出站，3退站，4延期）")
    private Integer status;

    @ApiModelProperty(value = "身份类型（1统招统分，2在职人员，3博新计划进站，4其他）")
    private String cardType;

    @ApiModelProperty(value = "招收类型（1流动站自主招收，2与工作站联合培养）")
    private String recruitType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "进站时间")
    private LocalDate pullTime;

    @ApiModelProperty(value = "进站单位")
    private String attachUnit;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "博管会进站时间")
    private LocalDate committeePullTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "博管会出站时间")
    private LocalDate committeePushTime;

    @ApiModelProperty(value = "是否延期（1是，0否）")
    private Integer isDelay;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "延期时间至")
    private LocalDate delayTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "流动站（一级学科）")
    private String stationName;

    @ApiModelProperty(value = "流动站code")
    private String stationCode;

    @ApiModelProperty(value = "专业（二级学科）")
    private String stationSubName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private LocalDate createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "最后更新时间")
    private LocalDate updateTime;


}
