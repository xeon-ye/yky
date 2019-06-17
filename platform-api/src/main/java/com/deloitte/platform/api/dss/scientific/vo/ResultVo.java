package com.deloitte.platform.api.dss.scientific.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类别")
    private String category;

    @ApiModelProperty(value = "数量")
    private String total;
}
