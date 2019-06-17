package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-13
 * @Description :   GccReviewOption查询from对象
 * @Modified :
 */
@ApiModel("GccReviewOption查询表单")
@Data
public class GccReviewOptionQueryForm extends BaseQueryForm<GccReviewOptionQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "申报人员编号")
    private Long declareId;

    @ApiModelProperty(value = "申报人姓名")
    private String declareName;

    @ApiModelProperty(value = "评审人编号")
    private Long reviewId;

    @ApiModelProperty(value = "评审人姓名")
    private String reviewName;

    @ApiModelProperty(value = "评审小组编号")
    private Long groupId;

    @ApiModelProperty(value = "评审意见")
    private String reviewContent;

    @ApiModelProperty(value = "评审平台编号")
    private Long reviewPlatformId;

    @ApiModelProperty(value = "类型 0 所院，1 院校 ")
    private String type;

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

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "评审状态")
    private String reviewStatus;




}
