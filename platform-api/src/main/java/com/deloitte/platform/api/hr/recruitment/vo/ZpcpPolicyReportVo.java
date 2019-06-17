package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description : ZpcpPolicyReport返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZpcpPolicyReportVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "政策报告名称")
    private String reportName;

    @ApiModelProperty(value = "等级名称")
    private String levelName;

    @ApiModelProperty(value = "采用时间")
    private LocalDate adoptionTime;

    @ApiModelProperty(value = "采用单位")
    private String adoptionUnit;

    @ApiModelProperty(value = "级别")
    private String grade;

    @ApiModelProperty(value = "所获分数")
    private String score;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

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

    @ApiModelProperty(value = "文件对象")
    private HrAttachmentVo attachmentVo;

}
