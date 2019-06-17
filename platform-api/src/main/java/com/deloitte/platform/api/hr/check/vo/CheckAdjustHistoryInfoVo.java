package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckAdjustHistory返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAdjustHistoryInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "考核结果id")
    private String checkResultId;

    @ApiModelProperty(value = "考核组织")
    private String checkOrgName;

    @ApiModelProperty(value = "调整等级")
    private String adjustLevel;

    @ApiModelProperty(value = "调整等级名称")
    private String  AdjustLevelName;

    @ApiModelProperty(value = "调整原因")
    private String adjustReason;

    @ApiModelProperty(value = "系统等级")
    private String systemLevel;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "系统分数")
    private Double systemScore;

    @ApiModelProperty(value = "系统排名")
    private Long ranking;

    @ApiModelProperty(value = "调整时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "调整前系统等级")
    private String preSystemLevel;

    @ApiModelProperty(value = "调整前系统等级名称")
    private String preSystemLevelName;



}
