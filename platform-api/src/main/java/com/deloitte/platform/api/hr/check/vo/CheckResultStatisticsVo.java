package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckResult返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckResultStatisticsVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    private String orderNum;

    @ApiModelProperty(value = "教职工id")
    private String userId;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "考核组织")
    private String checkOrgName;

    @ApiModelProperty(value = "系统分数")
    private Double systemScore;

    @ApiModelProperty(value = "系统排名")
    private Long ranking;

    @ApiModelProperty(value = "系统等级")
    private String systemLevel;

    @ApiModelProperty(value = "系统等级")
    private String systemLevelName;

    @ApiModelProperty(value = "各阶段具体人数")
    private Map<String, List<CheckResultStatisticsInfoVo>> statisticsInfo;

}
