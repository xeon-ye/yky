package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 科研项目及到位经费分布
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionalTotalVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总项目数量")
    private Long totalNum;

    @ApiModelProperty(value = "任务数量")
    private Long totalTaskNum;
}
