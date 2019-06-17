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
public class GccReviewOptionExportVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "申报人编号")
    private String userId;

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

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类别")
    private String projectCategroy;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "审核人总数")
    private String total;

    @ApiModelProperty(value = "审核过人数")
    private String total1;

    @ApiModelProperty(value = "审核通过的人数")
    private String total2;



}
