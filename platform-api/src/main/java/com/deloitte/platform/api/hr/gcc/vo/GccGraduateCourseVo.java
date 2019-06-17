package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : jiangString
 * @Date : Create in 2019-04-01
 * @Description : GccGraduateCourse返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccGraduateCourseVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "人员编号")
    private String userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "课程名称（上传附件）")
    private String courseName;

    @ApiModelProperty(value = "学期数")
    private Integer semesterNumber;

    @ApiModelProperty(value = "总学时数")
    private Integer totalSemester;

    @ApiModelProperty(value = "选学总人次")
    private Double totalEnrollment;

    @ApiModelProperty(value = "是否为核心课程 0 否，1是")
    private String coreCourse;

    @ApiModelProperty(value = "是否为精品课程 0 否，1是")
    private String qualityCourse;

    @ApiModelProperty(value = "附件")
    private String enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "修改人id")
    private String updateBy;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentVo attachmentVo;
}
