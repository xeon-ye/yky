package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : jiangString
 * @Date : Create in 2019-04-01
 * @Description : GccSpecialistGroup返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccSpecialistGroupVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "小组名称")
    private String groupName;

    @ApiModelProperty(value = "评审项目")
    private String reviewProject;

    @ApiModelProperty(value = "评审项目名称")
    private String reviewProjectName;

    @ApiModelProperty(value = "评审年份")
    private String reviewYear;

    @ApiModelProperty(value = "所属评审单位")
    private String reviewUnit;

    @ApiModelProperty(value = "所属评审单位名称")
    private String reviewUnitName;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "类型（所院/院校）")
    private String type;

    @ApiModelProperty(value = "分类（评审/考核）")
    private String classify;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "修改人id")
    private String updateBy;

    @ApiModelProperty(value = "小组成员名称多个以,隔开")
    private String groupUserName;

    @ApiModelProperty(value ="提醒时间设置")
    private LocalDate remindTime;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentVo attachmentVo;
}
