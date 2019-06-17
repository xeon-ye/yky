package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckResult返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckResultStatisticsInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人数")
    private int num;

    @ApiModelProperty(value = "评估选项名称")
    private String  evaluateModeName;

    @ApiModelProperty(value = "评估选项id")
    private String evaluateModeId;

}
