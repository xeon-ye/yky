package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckRuleContent返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRuleContentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "考核等级规则id")
    private String checkRuleId;

    @ApiModelProperty(value = "等级名称")
    private String levelName;

    @ApiModelProperty(value = "最小分数")
    private Long minScore;

    @ApiModelProperty(value = "最大分数")
    private Long maxScore;

    @ApiModelProperty(value = "比例")
    private Double ratio;

    @ApiModelProperty(value = "人数")
    private Long peopleNumber;

    @ApiModelProperty(value = "排序")
    private String orderNumber;

    @ApiModelProperty(value = "计算系数")
    private Double computeRatio;

    @ApiModelProperty(value = "描述")
    private String describe;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}
