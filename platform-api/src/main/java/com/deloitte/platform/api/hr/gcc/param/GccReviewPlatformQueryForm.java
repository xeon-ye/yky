package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-13
 * @Description :   GccReviewPlatform查询from对象
 * @Modified :
 */
@ApiModel("GccReviewPlatform查询表单")
@Data
public class GccReviewPlatformQueryForm extends BaseQueryForm<GccReviewPlatformQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "评审小组")
    private Long reviewId;

    @ApiModelProperty(value = "评审人员")
    private String reviewName;

    @ApiModelProperty(value = "申报项目名称")
    private String projectName;

    @ApiModelProperty(value = "申报项目编号")
    private Long projectId;

    @ApiModelProperty(value = "评审内容 ")
    private String reviewConten;

    @ApiModelProperty(value = "评审状态 0 未评审，1评审")
    private String reviewStatus;

    @ApiModelProperty(value = "是否提交")
    private String reviewSubmit;

    @ApiModelProperty(value = "类型 0 所院，1 院校 ")
    private String type;

    @ApiModelProperty(value = "推荐一览表(附件）")
    private Long recList;

    @ApiModelProperty(value = "申报人单位 ")
    private String applyUnit;

    @ApiModelProperty(value = "推荐报告(附件）")
    private Long recReport;

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

    @ApiModelProperty(value = "评审人员编号")
    private Long evaluationId;

}
