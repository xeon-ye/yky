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
 * @Date : Create in 2019-04-23
 * @Description : ZpcpExperience返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZpcpExperienceVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间")
    private LocalDate startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "结束时间")
    private LocalDate endTime;

    @ApiModelProperty(value = "单位名称")
    private String companyName;

    @ApiModelProperty(value = "从事专业")
    private String profession;

    @ApiModelProperty(value = "职务")
    private String position;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "职称")
    private String jobTitle;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}
