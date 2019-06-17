package com.deloitte.platform.api.hr.gcc.vo;

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
 * @Description : GccProjectApplication返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccProjectApplication2Vo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "人才项目通知序号")
    private String noticeId;

    @ApiModelProperty(value = "申报人编号")
    private String userId;

    @ApiModelProperty(value = "申报人项目名称")
    private String projectName;

    @ApiModelProperty(value = "申报人项目编号")
    private String projectId;

    @ApiModelProperty(value = "申报通知名称")
    private String noticeName;

    @ApiModelProperty(value = "申报人项目分类")
    private String projectCategoryName;

    @ApiModelProperty(value = "申报人项目分类编号")
    private String categoryId;

    @ApiModelProperty(value = "申报人姓名")
    private String userName;

    @ApiModelProperty(value = "申报人单位")
    private String applyUnit;

    @ApiModelProperty(value = "申报人单位名称")
    private String applyUnitName;

    @ApiModelProperty(value = "申报人部门")
    private String applyDepart;

    @ApiModelProperty(value = "申报人职称")
    private String applyJob;

    @ApiModelProperty(value = "申报时间")
    private LocalDate applyTime;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "所院评审进度")
    private String syReviewProgress;

    @ApiModelProperty(value = "所院评审结果")
    private String syReviewResult;

    @ApiModelProperty(value = "院校评审进度")
    private String yxReviewProgress;

    @ApiModelProperty(value = "院校评审结果")
    private String yxReviewResult;

    @ApiModelProperty(value = "成员名")
    private String memberName;

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

    @ApiModelProperty(value = "通知时间")
    private LocalDate releaseTime;

    @ApiModelProperty(value = "申报年份")
    private String applyYear;

    @ApiModelProperty(value = "批次")
    private String batch;


}
