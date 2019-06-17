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
 * @Description : GccTechnocracy返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccTechnocracyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "学员编号")
    private String userId;

    @ApiModelProperty(value = "学员姓名")
    private String userName;

    @ApiModelProperty(value = "所在单位")
    private String unit;

    @ApiModelProperty(value = "申请年度")
    private String year;

    @ApiModelProperty(value = "日期")
    private LocalDateTime dateTime;

    @ApiModelProperty(value = "组织部门")
    private String department;

    @ApiModelProperty(value = "项目")
    private String project;

    @ApiModelProperty(value = "项目类型 0表示 专家培训项目，1 表示专家休养项目")
    private String projectType;

    @ApiModelProperty(value = "地点")
    private String area;

    @ApiModelProperty(value = "附件")
    private String enclosure;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "修改人id")
    private String updateBy;

    @ApiModelProperty(value = "结束日期")
    private LocalDate endTime;

    @ApiModelProperty(value = "开始日期")
    private LocalDate startTime;

    @ApiModelProperty(value = "完成情况")
    private String performance;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentVo attachmentVo;
}
