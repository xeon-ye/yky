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
 * @Description : GccReviewOption返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccReviewOptionVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "申报人员编号")
    private String declareId;

    @ApiModelProperty(value = "申报人姓名")
    private String declareName;

    @ApiModelProperty(value = "评审人编号")
    private String reviewId;

    @ApiModelProperty(value = "评审人姓名")
    private String reviewName;

    @ApiModelProperty(value = "评审小组编号")
    private String groupId;


    @ApiModelProperty(value = "申报人单位")
    private String unit;

    @ApiModelProperty(value = "申报人职称")
    private String expertsTitles;


    @ApiModelProperty(value = "评审意见")
    private String reviewContent;

    @ApiModelProperty(value = "评审平台编号")
    private String reviewPlatformId;

    @ApiModelProperty(value = "类型 0 所院，1 院校 ")
    private String type;

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

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "评审状态")
    private String reviewStatus;

}
