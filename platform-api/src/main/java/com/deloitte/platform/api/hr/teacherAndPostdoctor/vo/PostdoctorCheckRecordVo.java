package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
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
 * @Description : PostdoctorCheckRecord返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorCheckRecordVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "博士后信息ID")
    private String postdoctorId;

    @ApiModelProperty(value = "考核类型（1入站考核，2中期考核，3出战考核）")
    private Integer checkType;

    @ApiModelProperty(value = "当前状态")
    private String status;

    @ApiModelProperty(value = "所属单位")
    private String attachUnit;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "拟出站时间")
    private LocalDate expectPushTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "考核时间")
    private LocalDate checkTime;

    @ApiModelProperty(value = "考核结果")
    private String checkResult;

    @ApiModelProperty(value = "备注")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "入站时间")
    private LocalDate pullTime;

    @ApiModelProperty(value = "博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "所属流动站")
    private String stationName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
