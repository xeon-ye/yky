package com.deloitte.platform.api.hr.check.param;

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
public class CheckComputeCheckRankFrom extends BaseVo {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "排名计算方式()")
    private String rankType;

    @ApiModelProperty(value = "业绩测评通知id")
    private Long checkEvaluateNotifyId;


}
