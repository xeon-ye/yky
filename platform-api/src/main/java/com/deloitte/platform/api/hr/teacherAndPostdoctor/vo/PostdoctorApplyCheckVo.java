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
 * @Description : 在站成果审核列表返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorApplyCheckVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String aId;

    @ApiModelProperty(value = "申请人姓名")
    private String postdoctorName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "出生年月")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ApiModelProperty(value = "流动站名称")
    private String stationName;

    @ApiModelProperty(value = "研究专业")
    private String stationSubName;

    @ApiModelProperty(value = "身份类型")
    private String cardType;

    @ApiModelProperty(value = "招收类型")
    private String recruitType;

    @ApiModelProperty(value = "申请状态（1已申请保存，2已申报提交，3合作导师审核通过，4合作导师审核不通过，5所院审核通过，6所院审核未通过，7院校人力资源处审核通过，8院校人力资源处审核未通过）")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "申请时间")
    private LocalDate applyTime;

    @ApiModelProperty(value = "在站时长（月）")
    private String pullStationHow;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "博管会进站时间")
    private LocalDate committeePullTime;


}
