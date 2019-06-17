package com.deloitte.platform.api.srpmp.project.base.vo;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-26
 * @Description : SrpmsProjectPerson返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SrpmsProjectPersonVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private String id;

    @ApiModelProperty(value = "姓名")
    private String personName;

    @ApiModelProperty(value = "性别CODE")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthDate;

    @ApiModelProperty(value = "职称CODE")
    private String positionTitle;

    @ApiModelProperty(value = "最高学历CODE")
    private String education;

    @ApiModelProperty(value = "从事专业CODE")
    private String major;

    @ApiModelProperty(value = "固定电话")
    private String telPhone;

    @ApiModelProperty(value = "移动电话")
    private String mobile;

    @ApiModelProperty(value = "传真号码")
    private String faxNumber;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "证件类型CODE")
    private String idCardType;

    @ApiModelProperty(value = "证件号码")
    private String idCard;

    @ApiModelProperty(value = "学位授予国别")
    private String educationCountry;

    @ApiModelProperty(value = "所在单位")
    private String deptName;

    @ApiModelProperty(value = "每年工作时间")
    private Integer workPerYear;

    @ApiModelProperty(value = "通讯地址及邮编")
    private String addressAndZip;

    @ApiModelProperty(value = "依托基地名称")
    private String liveBaseName;

    @ApiModelProperty(value = "入选人才计划CODE数组")
    private String talentPlan;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "源人员ID")
    private Long sourcePersonId;

    @ApiModelProperty(value = "学位")
    private String degree;

    @ApiModelProperty(value = "所在科室")
    private String officeName;

    @ApiModelProperty(value = "承担单位")
    private String projectCommitmentUnit;

    @ApiModelProperty(value = "通讯地址")
    private String address;

    @ApiModelProperty(value = "邮编")
    private String zipCode;

}
