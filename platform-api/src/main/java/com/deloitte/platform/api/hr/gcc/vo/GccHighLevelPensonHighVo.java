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
 * @Author : liangjinghai
 * @Date : Create in 2019-04-09
 * @Description : GccHighLevelPenson返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccHighLevelPensonHighVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "人员编号")
    private String userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "入选年份")
    private String choiceYear;

    @ApiModelProperty(value = "人才项目名称")
    private String projectName;

    @ApiModelProperty(value = "人才项目名称编号")
    private String projectId;

    @ApiModelProperty(value = "人才项目类别")
    private String projectCategory;

    @ApiModelProperty(value = "人才项目类别编号")
    private String projectCategoryId;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "一级学科")
    private String firstLevelSubject;

    @ApiModelProperty(value = "二级学科")
    private String secondLevelSubject;

    @ApiModelProperty(value = "研究方向")
    private String researchDirection;

    @ApiModelProperty(value = "人员状态 0在职 1离职 2离退 3去世")
    private String state;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "专业技术职务")
    private String prolTechnicians;

    @ApiModelProperty(value = "行政职务")
    private String executiveFunction;

    @ApiModelProperty(value = "从事专业")
    private String professial;

    @ApiModelProperty(value = "调入时间")
    private LocalDate comeTime;

    @ApiModelProperty(value = "调出时间 ")
    private LocalDate transferTime;

    @ApiModelProperty(value = "附件")
    private String enclosure;

    @ApiModelProperty(value = "备注")
    private String remarks;

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

    @ApiModelProperty(value = "性别 1 男 2女 3不详")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    private LocalDate birth;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "政治面貌")
    private String politicalOutlook;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "学位")
    private String academicDegree;

    @ApiModelProperty(value = "毕业学校")
    private String graduateSchool;

    @ApiModelProperty(value = "专业名称")
    private String majorName;

    @ApiModelProperty(value = "毕业时间")
    private LocalDate graduationTime;

    @ApiModelProperty(value = "参加工作时间")
    private LocalDate workingTime;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentVo attachmentVo;
}
