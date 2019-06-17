package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
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
 * @Date : Create in 2019-04-23
 * @Description : ZpcpEngagementPeriod返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZpcpEngagementPeriodVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "教职工姓名")
    private String name;

    @ApiModelProperty(value = "单位")
    private String deptName;

    @ApiModelProperty(value = "岗位名称")
    private String stationName;

    @ApiModelProperty(value = "是否有效(0无效,1有效)")
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "聘用开始时间")
    private LocalDate startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "聘用结束时间")
    private LocalDate endTime;

    @ApiModelProperty(value = "任职年限(月)")
    private String tenureService;

    @ApiModelProperty(value = "聘用状态")
    private String employmentStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "在聘信息表id")
    private String infoId;

    @ApiModelProperty(value = "文件对象")
    private HrAttachmentVo attachmentVo;

}
