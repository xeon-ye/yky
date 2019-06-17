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
public class GccHighLevelPensonVo extends BaseVo {
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
    private LocalDateTime comeTime;

    @ApiModelProperty(value = "调出时间 ")
    private LocalDateTime transferTime;

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
    @ApiModelProperty(value = "申领情况 0未申领，1 申领")
    private String applicationSituation;

    @ApiModelProperty(value = "到账情况 0未到账，1 已到账")
    private String fundsAccount;

    @ApiModelProperty(value = "到账金额")
    private String arrivalAmount;

    @ApiModelProperty(value = "到账时间")
    private LocalDate accountingDate;


    @ApiModelProperty(value = "拨款情况 0未拨款，1 已拨款")
    private LocalDate fundingSituation;


    @ApiModelProperty(value = "拨款金额")
    private String allocateFunds;

    @ApiModelProperty(value = "拨款时间")
    private LocalDate allocateDate;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentVo attachmentVo;
}
