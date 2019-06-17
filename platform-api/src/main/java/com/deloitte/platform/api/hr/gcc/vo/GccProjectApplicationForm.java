package com.deloitte.platform.api.hr.gcc.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccProjectApplication新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccProjectApplication表单")
@Data
public class GccProjectApplicationForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "人才项目通知序号")
    private Long noticeId;

    @ApiModelProperty(value = "申报人编号")
    private Long userId;

    @ApiModelProperty(value = "申报人姓名")
    private String userName;

    @ApiModelProperty(value = "申报人单位")
    private String applyUnit;

    @ApiModelProperty(value = "申报人部门")
    private String applyDepart;

    @ApiModelProperty(value = "申报人职称")
    private String applyJob;

    @ApiModelProperty(value = "申报时间")
    private LocalDateTime applyTime;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "所院评审进度")
    private String syReviewProgress;

    @ApiModelProperty(value = "院校评审进度")
    private String syReviewResult;

    @ApiModelProperty(value = "院校评审进度")
    private String yxReviewProgress;

    @ApiModelProperty(value = "院校评审结果")
    private String yxReviewResult;

    @ApiModelProperty(value = "成员名")
    private String memberName;

    @ApiModelProperty(value = "项目编号")
    private Long projectId;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "是否归档")
    private String archive;

    @ApiModelProperty(value = "项目分类编号")
    private String categoryId;

    @ApiModelProperty(value = "学习经历审核状态 ")
    private String studyStatus;

    @ApiModelProperty(value = "学习经历审核意见 ")
    private String studyIdea;

    @ApiModelProperty(value = "工作经历审核状态 ")
    private String workStatus;

    @ApiModelProperty(value = "工作经历审核意见 ")
    private String workIdea;

    @ApiModelProperty(value = "科研项目审核状态 ")
    @TableField("PROJECT_STATUS")
    private String projectStatus;

    @ApiModelProperty(value = "科研项目审核意见 ")
    private String projectIdea;

    @ApiModelProperty(value = "获奖情况审核状态 ")
    private String awardStatus;

    @ApiModelProperty(value = "获奖情况审核意见 ")
    private String awardIdea;

    @ApiModelProperty(value = "论文情况审核状态 ")
    private String thesisStatus;

    @ApiModelProperty(value = "论文情况审核意见 ")
    private String thesisIdea;

    @ApiModelProperty(value = "专利情况审核状态 ")
    @TableField("PATENT_STATUS")
    private String patentStatus;

    @ApiModelProperty(value = "专利情况审核意见")
    private String patentIdea;

    @ApiModelProperty(value = "学术团体、学术刊物审核状态 ")
    private String socStatus;

    @ApiModelProperty(value = "学术团体、学术刊物审核意见")
    private String socIdea;


    @ApiModelProperty(value = "教学情况-概述 ")
    private String teach1Status;

    @ApiModelProperty(value = "教学情况-概述 意见 ")
    private String teach1Idea;

    @ApiModelProperty(value = "教学情况-本科审核状态 ")
    private String teach2Status;

    @ApiModelProperty(value = "教学情况-本科审核意见")
    private String teach2Idea;

    @ApiModelProperty(value = "教学情况-研究生课程审核状态 ")
    private String teach3Status;

    @ApiModelProperty(value = "教学情况-研究生课程审核意见 ")
    private String teach3Idea;

    @ApiModelProperty(value = "教学情况-科研育人课程审核状态 ")
    private String teach4Status;

    @ApiModelProperty(value = "教学情况-科研育人课程审核意见 ")
    private String teach4Idea;

    @ApiModelProperty(value = "教学情况-开设课程课程审核状态 ")
    private String teach5Status;

    @ApiModelProperty(value = "教学情况-开设课程课程审核意见 ")
    private String teach5Idea;

    @ApiModelProperty(value = "教学情况-指导研究生课程审核状态 ")
    private String teach6Status;

    @ApiModelProperty(value = "教学情况-指导研究生课程审核意见 ")
    private String teach6Idea;

    @ApiModelProperty(value = "教学情况-教学改革课程审核状态 ")
    private String teach7Status;

    @ApiModelProperty(value = "教学情况-教学改革课程审核意见 ")
    private String teach7Idea;

    @ApiModelProperty(value = "教学情况-教学成果课程审核状态 ")
    private String teach8Status;

    @ApiModelProperty(value = "教学情况-教学成果课程审核意见 ")
    private String teach8Idea;

    @ApiModelProperty(value = "教学情况-教学成果课程审核状态 ")
    private String teach9Status;

    @ApiModelProperty(value = "教学情况-教材编写课程审核意见 ")
    private String teach9Idea;

    @ApiModelProperty(value = "教学情况-其他教学活动审核状态 ")
    private String teach10Status;


    @ApiModelProperty(value = "教学情况-其他教学活动审核意见 ")
    private String teach10Idea;

    @ApiModelProperty(value = "学术创新-总体情况审核状态 ")
    private String academic1Status;

    @ApiModelProperty(value = "学术创新-总体情况审核意见 ")
    private String academic1Idea;

    @ApiModelProperty(value = "学术创新-平台团队审核状态 ")
    private String academic2Status;

    @ApiModelProperty(value = "学术创新-平台团队审核意见 ")
    private String academic2Idea;

    @ApiModelProperty(value = "学术创新-重要职务审核状态 ")
    private String academic3Status;

    @ApiModelProperty(value = "学术创新-重要职务审核意见 ")
    private String academic3Idea;

    @ApiModelProperty(value = "学术创新-会议报告审核状态 ")
    private String academic4Status;

    @ApiModelProperty(value = "学术创新-会议报告审核意见 ")
    private String academic4Idea;

    @ApiModelProperty(value = "学术创新-其他情况审核状态 ")
    private String academic5Status;

    @ApiModelProperty(value = "学术创新-其他情况审核意见 ")
    private String academic5Idea;

    @ApiModelProperty(value = "工作设想审核状态")
    private String work1Status;

    @ApiModelProperty(value = "工作设想审核意见")
    private String work1Idea;
}
