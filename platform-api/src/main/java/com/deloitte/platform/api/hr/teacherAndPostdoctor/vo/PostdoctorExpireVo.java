package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description : Postdoctor返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorExpireVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = " 姓名")
    private String name;

    @ApiModelProperty(value = "博士后状态（1在站，2出站，3退站，4延期）")
    private Integer status;

    @ApiModelProperty(value = "博士后状态（1在站，2出站，3退站，4延期）")
    private String pStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "进站时间")
    private LocalDate pullTime;

    @ApiModelProperty(value = "进站单位")
    private String attachUnit;

    @ApiModelProperty(value = "进站单位")
    private String attachUnitName;

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

    @ApiModelProperty(value = "是否提醒（1是，2否）")
    private Integer isExpire;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同开始时间")
    private LocalDate contractStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同结束时间")
    private LocalDate contractEndTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;


}
