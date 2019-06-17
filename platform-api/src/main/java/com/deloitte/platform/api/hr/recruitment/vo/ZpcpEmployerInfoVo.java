package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-12
 * @Description : ZpcpEmployerInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZpcpEmployerInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "教职工姓名")
    private String name;

    @ApiModelProperty(value = "单位")
    private String deptName;

    @ApiModelProperty(value = "岗位名称")
    private String stationName;

    @ApiModelProperty(value = "聘用状态")
    private String employmentStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "聘用时间起")
    private LocalDate startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "聘用时间止")
    private LocalDate endTime;

    @ApiModelProperty(value = "薪酬制度")
    private String salarySystem;

    @ApiModelProperty(value = "基本年薪")
    private Long totalSalay;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织编码")
    private String organizationCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "任职年限")
    private String appointmentTime;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "有效状态(0无效,1有效)")
    private String status;

    @ApiModelProperty(value = "一级学科")
    private String firstClass;

    @ApiModelProperty(value = "一级学科名称")
    private String subjectName;


}
