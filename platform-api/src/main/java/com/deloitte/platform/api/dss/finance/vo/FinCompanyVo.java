package com.deloitte.platform.api.dss.finance.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单位列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinCompanyVo extends BaseVo {

    @ApiModelProperty(value = "单位编码")
    private String comCode;

    @ApiModelProperty(value = "单位名称")
    private String comDes;
}
