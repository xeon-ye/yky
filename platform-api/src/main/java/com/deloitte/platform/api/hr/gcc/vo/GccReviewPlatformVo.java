package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-13
 * @Description : GccReviewPlatform返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccReviewPlatformVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "评审小组")
    private String reviewId;

    @ApiModelProperty(value = "评审人员")
    private String reviewName;

    @ApiModelProperty(value = "申报项目名称")
    private String projectName;

    @ApiModelProperty(value = "申报项目编号")
    private String projectId;

    @ApiModelProperty(value = "评审内容")
    private String reviewConten;

    @ApiModelProperty(value = "评审状态 0 未评审，1评审")
    private String reviewStatus;

    @ApiModelProperty(value = "是否提交")
    private String reviewSubmit;

    @ApiModelProperty(value = "类型 0 所院，1 院校 ")
    private String type;

    @ApiModelProperty(value = "推荐一览表(附件）")
    private String recList;

    @ApiModelProperty(value = "推荐报告(附件）")
    private String recReport;

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

    @ApiModelProperty(value = "申报人单位 ")
    private String applyUnit;

    @ApiModelProperty(value = "评审人员编号")
    private String evaluationId;

}
