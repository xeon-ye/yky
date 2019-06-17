package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccGraduateCourse查询from对象
 * @Modified :
 */
@ApiModel("GccGraduateCourse查询表单")
@Data
public class GccGraduateCourseQueryForm extends BaseQueryForm<GccGraduateCourseQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "人员编号")
    private Long userId;

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
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "类型 0本科  1 研究生")
    private String type;
}
